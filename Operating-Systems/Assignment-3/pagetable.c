#include "pagetable.h"
#include "phypages.h" /* for alloc_frame() and access_frame() */
#include <stdio.h>

PTE page_table[NUM_PAGES];

void init_page_table(void) {
  for (int i = 0; i < NUM_PAGES; i++) {
    page_table[i].valid = 0;
    page_table[i].frame = -1;
  }
}

void invalidate_pte(int page_num) {
  page_table[page_num].valid = 0;
  page_table[page_num].frame = -1;
}

int translate_address(unsigned long logical, unsigned long *physical,
                      int *fault) {
  unsigned long page_num = logical >> OFFSET_BITS;
  unsigned long offset = logical & OFFSET_MASK;

  if (page_num >= NUM_PAGES) {
    fprintf(stderr,
            "ERROR: logical address 0x%016lx -> page %lu out of range\n",
            logical, page_num);
    return -1;
  }

  int frame;
  if (page_table[page_num].valid) {
    /* Page is resident — look up frame and record access for LRU */
    frame = page_table[page_num].frame;
    *fault = 0;
    access_frame(frame);
  } else {
    /* Page fault — allocate (or evict-and-reuse) a frame */
    frame = alloc_frame((int)page_num);
    page_table[page_num].valid = 1;
    page_table[page_num].frame = frame;
    *fault = 1;
  }

  *physical = (unsigned long)frame * PAGE_SIZE + offset;
  return 0;
}
