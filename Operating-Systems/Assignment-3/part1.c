#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define PAGE_SIZE 128    //(2^7), 1288 bytes
#define OFFSET_BITS 7    // exponent of 2^7 bits, 128bytes
#define OFFSET_MASK 0x7F // 0111 1111 - masks bottom 7 bits to extract offset
#define NUM_PAGES 32     // 4k virtual mem / 128 bytes (2^7)

int main(int argc, char *argv[]) {

  if (argc != 3) {
    fprintf(stderr, "Usage: %s <infile> <outfile>\n", argv[0]);
    return 1;
  }

  // opening argv[1] in binary read mode
  FILE *infile = fopen(argv[1], "rb");
  if (infile == NULL) {
    perror("Error opening input file");
    return 1;
  }

  // opening argv[2] in binary write
  FILE *outfile = fopen(argv[2], "wb");
  if (outfile == NULL) {
    perror("Error opening output file");
    fclose(infile); // close infile as it opened successfully
    return EXIT_FAILURE;
  }

  int page_table[NUM_PAGES] = {
      [0] = 2,   [1] = 4,   [2] = 1,   [3] = 7,   [4] = 3,   [5] = 5,
      [6] = 6,   [7] = -1,  [8] = -1,  [9] = -1,  [10] = -1, [11] = -1,
      [12] = -1, [13] = -1, [14] = -1, [15] = -1, [16] = -1, [17] = -1,
      [18] = -1, [19] = -1, [20] = -1, [21] = -1, [22] = -1, [23] = -1,
      [24] = -1, [25] = -1, [26] = -1, [27] = -1, [28] = -1, [29] = -1,
      [30] = -1, [31] = -1};

  unsigned long address;
  while ((fread(&address, sizeof(unsigned long), 1, infile) == 1)) {
    unsigned long page_num = address >> OFFSET_BITS;
    unsigned long offset = address & OFFSET_MASK;
    if (page_table[page_num] == -1) {
      fprintf(stderr, "ERROR: invalid page %lu\n", page_num);
      continue;
    }
    // printf("Logical address: 0x%016lx\n", address);
    unsigned long physical_address =
        (page_table[page_num] * PAGE_SIZE) + offset;
    fwrite(&physical_address, sizeof(unsigned long), 1, outfile);
  }

  fclose(infile);
  fclose(outfile);
  return 0;
}