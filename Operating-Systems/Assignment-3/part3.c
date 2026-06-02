#include <stdio.h>
#include <stdlib.h>

typedef struct {
  int valid;
  int frame;
} PTE;

// runtie memory config (set by init_all)
static int page_size;
static int num_pages;
static int num_frames;
static int offset_bits;
static unsigned long offset_mask;

/* Dynamically allocated structures */
static PTE *page_table;
static int *lru_list;      // frames 1..n-1
static int *frame_to_page; // reverse map: frame -> page (-1 if empty)
static int lru_size = 0;
static int next_free = 1; // next frame to hand out during init fill

// Computing log2 of a power-of-2 int
static int log2_int(int n) {
  int bits = 0;
  while (n > 1) {
    n >>= 1;
    bits++;
  }
  return bits;
}

static void init_all(int bpp, int virt_size, int phys_size) {
  page_size = bpp;
  num_pages = virt_size / bpp;
  num_frames = phys_size / bpp;
  offset_bits = log2_int(bpp);
  offset_mask = (unsigned long)(bpp - 1);

  page_table = malloc(num_pages * sizeof(PTE));
  lru_list = malloc((num_frames - 1) * sizeof(int));
  frame_to_page = malloc(num_frames * sizeof(int));

  for (int i = 0; i < num_pages; i++) {
    page_table[i].valid = 0;
    page_table[i].frame = -1;
  }
  for (int i = 0; i < num_frames; i++) {
    frame_to_page[i] = -1;
  }
  lru_size = 0;
  next_free = 1;
}

static void invalidate_pte(int page_num) {
  page_table[page_num].valid = 0;
  page_table[page_num].frame = -1;
}

static void access_frame(int frame) {
  int pos = -1;
  for (int i = 0; i < lru_size; i++) {
    if (lru_list[i] == frame) {
      pos = i;
      break;
    }
  }
  if (pos == -1)
    return;
  for (int i = pos; i < lru_size - 1; i++)
    lru_list[i] = lru_list[i + 1];
  lru_list[lru_size - 1] = frame;
}

static int alloc_frame(int page_num) {
  int frame;
  if (next_free < num_frames) {
    frame = next_free++;
    lru_list[lru_size++] = frame;
  } else {
    // evictLRU
    frame = lru_list[0];
    for (int i = 0; i < lru_size - 1; i++)
      lru_list[i] = lru_list[i + 1];
    lru_list[lru_size - 1] = frame;

    int old_page = frame_to_page[frame];
    if (old_page != -1)
      invalidate_pte(old_page);
  }
  frame_to_page[frame] = page_num;
  return frame;
}

static int translate_address(unsigned long logical, unsigned long *physical,
                             int *fault) {
  unsigned long page_num = logical >> offset_bits;
  unsigned long offset = logical & offset_mask;

  if ((int)page_num >= num_pages) {
    fprintf(stderr, "ERROR: page %lu out of range\n", page_num);
    return -1;
  }

  int frame;
  if (page_table[page_num].valid) {
    frame = page_table[page_num].frame;
    *fault = 0;
    access_frame(frame);
  } else {
    frame = alloc_frame((int)page_num);
    page_table[page_num].valid = 1;
    page_table[page_num].frame = frame;
    *fault = 1;
  }

  *physical = (unsigned long)frame * page_size + offset;
  return 0;
}

int main(int argc, char *argv[]) {
  if (argc != 6) {
    fprintf(stderr,
            "Usage: %s BytesPerPage SizeOfVirtualMemory SizeOfPhysicalMemory "
            "SequenceFile OutputFile\n",
            argv[0]);
    return 1;
  }

  int bpp = atoi(argv[1]);
  int virt_size = atoi(argv[2]);
  int phys_size = atoi(argv[3]);

  if (bpp <= 0 || virt_size <= 0 || phys_size <= 0) {
    fprintf(stderr, "ERROR: memory parameters must be positive integers\n");
    return 1;
  }

  FILE *infile = fopen(argv[4], "rb");
  if (!infile) {
    perror("Error opening input file");
    return 1;
  }

  FILE *outfile = fopen(argv[5], "wb");
  if (!outfile) {
    perror("Error opening output file");
    fclose(infile);
    return 1;
  }

  init_all(bpp, virt_size, phys_size);

  unsigned long logical;
  int total_faults = 0;

  while (fread(&logical, sizeof(unsigned long), 1, infile) == 1) {
    unsigned long physical;
    int fault;
    if (translate_address(logical, &physical, &fault) == 0) {
      fwrite(&physical, sizeof(unsigned long), 1, outfile);
      if (fault)
        total_faults++;
    }
  }

  printf("Total page faults: %d\n", total_faults);

  free(page_table);
  free(lru_list);
  free(frame_to_page);

  fclose(infile);
  fclose(outfile);
  return 0;
}