#include "phypages.h"
#include "pagetable.h"  /* for invalidate_pte() */

/*
 * LRU list: holds frame numbers for frames 1–7 in access order.
 * Index 0 = least recently used (eviction candidate).
 * Last valid index = most recently used.
 */
static int lru_list[NUM_FRAMES - 1];
static int lru_size  = 0;  /* how many frames are currently in the list */
static int next_free = 1;  /* next frame to hand out during initial fill */

/* reverse mapping: which page currently lives in each frame (-1 = none) */
static int frame_to_page[NUM_FRAMES];

void init_frames(void) {
    lru_size  = 0;
    next_free = 1;
    for (int i = 0; i < NUM_FRAMES; i++) {
        frame_to_page[i] = -1;
    }
}

/* Move a frame that is already in the LRU list to the MRU end */
void access_frame(int frame) {
    int pos = -1;
    for (int i = 0; i < lru_size; i++) {
        if (lru_list[i] == frame) { pos = i; break; }
    }
    if (pos == -1) return; /* should not happen */

    /* shift everything after pos one step left */
    for (int i = pos; i < lru_size - 1; i++) {
        lru_list[i] = lru_list[i + 1];
    }
    lru_list[lru_size - 1] = frame; /* place at MRU end */
}

int alloc_frame(int page_num) {
    int frame;

    if (next_free < NUM_FRAMES) {
        /*
         * Still have free frames — hand out the next one in order.
         * Append to the MRU end of the LRU list.
         */
        frame = next_free++;
        lru_list[lru_size++] = frame;

    } else {
        /*
         * All frames occupied — evict the LRU frame (index 0).
         * Slide the list left, then put the recycled frame at the MRU end.
         */
        frame = lru_list[0];
        for (int i = 0; i < lru_size - 1; i++) {
            lru_list[i] = lru_list[i + 1];
        }
        lru_list[lru_size - 1] = frame;

        /* Invalidate the page that was using this frame */
        int old_page = frame_to_page[frame];
        if (old_page != -1) {
            invalidate_pte(old_page);
        }
    }

    frame_to_page[frame] = page_num;
    return frame;
}
