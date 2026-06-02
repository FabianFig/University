#ifndef PHYPAGES_H
#define PHYPAGES_H

#include "pagetable.h"

/*
 * Initialize the frame allocator.
 * Frame 0 is reserved for the OS; frames 1–7 start free.
 */
void init_frames(void);

/*
 * Allocate a frame for the given page.
 * Uses in-order allocation (1, 2, 3, …) until frames are exhausted,
 * then evicts the least-recently-used frame.
 * Automatically invalidates the evicted page's PTE.
 * Returns the frame number assigned to page_num.
 */
int alloc_frame(int page_num);

/*
 * Record that the given frame was just accessed.
 * Moves it to the most-recently-used position in the LRU list.
 */
void access_frame(int frame);

#endif
