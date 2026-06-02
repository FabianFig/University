#ifndef PAGETABLE_H
#define PAGETABLE_H

#define PAGE_SIZE    128   // 2^7 bytes per page/frame
#define OFFSET_BITS  7     // bits needed to address within a page
#define OFFSET_MASK  0x7F  // mask for bottom 7 bits (the offset)
#define NUM_PAGES    32    // 4K virtual / 128 = 32 virtual pages
#define NUM_FRAMES   8     // 1K physical / 128 = 8 physical frames

/* One entry in the page table */
typedef struct {
    int valid;   /* 1 if this page is currently mapped, 0 otherwise */
    int frame;   /* physical frame number; meaningful only when valid == 1 */
} PTE;

extern PTE page_table[NUM_PAGES];

/* Initialize all entries to invalid */
void init_page_table(void);

/* Mark a page table entry as invalid (called by phypages on eviction) */
void invalidate_pte(int page_num);

/*
 * Translate a logical address to a physical address.
 * Sets *physical to the result, *fault to 1 if a page fault occurred.
 * Returns 0 on success, -1 if the logical address is out of range.
 */
int translate_address(unsigned long logical,
                      unsigned long *physical,
                      int *fault);

#endif
