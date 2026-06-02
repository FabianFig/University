#include "pslibrary.h"

#define READY 0
#define RUNNING 1
#define WAITING 2
#define DONE 3

static char stateChars[] = {'r', 'R', 'w', '\0'};

// FCFS scherduling
void fcfs(char *s1, char *s2, int x1, int y1, int z1, int x2, int y2, int z2) {
  int i;
  int state1 = READY;
  int state2 = READY;
  int cpuLeft1 = x1;
  int cpuLeft2 = x2;
  int ioLeft1 = y1;
  int ioLeft2 = y2;

  for (i = 0; (state1 != DONE) || (state2 != DONE); i++) {
    if ((state1 == RUNNING) && (cpuLeft1 == 0)) {
      if (ioLeft1 == 0) {
        state1 = DONE;
        s1[i] = stateChars[state1];
      } else
        state1 = WAITING;
    } else if ((state2 == RUNNING) && (cpuLeft2 == 0)) {
      if (ioLeft2 == 0) {
        state2 = DONE;
        s2[i] = stateChars[state2];
      } else
        state2 = WAITING;
    }
    if ((state1 == WAITING) && (ioLeft1 == 0)) {
      state1 = READY;
      cpuLeft1 = z1;
    }
    if ((state2 == WAITING) && (ioLeft2 == 0)) {
      state2 = READY;
      cpuLeft2 = z2;
    }
    if ((state1 == READY) && (state2 == READY))
      state1 = RUNNING;
    else if ((state1 == READY) && (state2 != RUNNING))
      state1 = RUNNING;
    else if ((state2 == READY) && (state1 != RUNNING))
      state2 = RUNNING;
    if (state1 != DONE)
      s1[i] = stateChars[state1];
    if (state2 != DONE)
      s2[i] = stateChars[state2];
    if (state1 == RUNNING)
      cpuLeft1--;
    if (state1 == WAITING)
      ioLeft1--;
    if (state2 == RUNNING)
      cpuLeft2--;
    if (state2 == WAITING)
      ioLeft2--;
  }
}

// SJF scheduling
void sjf(char *s1, char *s2, int x1, int y1, int z1, int x2, int y2, int z2) {
  int i;
  int state1 = READY;
  int state2 = READY;
  int cpuLeft1 = x1;
  int cpuLeft2 = x2;
  int ioLeft1 = y1;
  int ioLeft2 = y2;

  for (i = 0; (state1 != DONE) || (state2 != DONE); i++) {
    if ((state1 == RUNNING) && (cpuLeft1 == 0)) {
      if (ioLeft1 == 0) {
        state1 = DONE;
        s1[i] = stateChars[state1];
      } else
        state1 = WAITING;
    } else if ((state2 == RUNNING) && (cpuLeft2 == 0)) {
      if (ioLeft2 == 0) {
        state2 = DONE;
        s2[i] = stateChars[state2];
      } else
        state2 = WAITING;
    }
    if ((state1 == WAITING) && (ioLeft1 == 0)) {
      state1 = READY;
      cpuLeft1 = z1;
    }
    if ((state2 == WAITING) && (ioLeft2 == 0)) {
      state2 = READY;
      cpuLeft2 = z2;
    }
    if ((state1 == READY) && (state2 == READY)) {
      if (cpuLeft1 <= cpuLeft2)
        state1 = RUNNING;
      else
        state2 = RUNNING;
    } else if ((state1 == READY) && (state2 != RUNNING))
      state1 = RUNNING;
    else if ((state2 == READY) && (state1 != RUNNING))
      state2 = RUNNING;
    if (state1 != DONE)
      s1[i] = stateChars[state1];
    if (state2 != DONE)
      s2[i] = stateChars[state2];
    if (state1 == RUNNING)
      cpuLeft1--;
    if (state1 == WAITING)
      ioLeft1--;
    if (state2 == RUNNING)
      cpuLeft2--;
    if (state2 == WAITING)
      ioLeft2--;
  }
}

// PSJF scheduling
void psjf(char *s1, char *s2, int x1, int y1, int z1, int x2, int y2, int z2) {
  int i;
  int state1 = READY;
  int state2 = READY;
  int cpuLeft1 = x1;
  int cpuLeft2 = x2;
  int ioLeft1 = y1;
  int ioLeft2 = y2;

  for (i = 0; (state1 != DONE) || (state2 != DONE); i++) {
    if ((state1 == RUNNING) && (cpuLeft1 == 0)) {
      if (ioLeft1 == 0) {
        state1 = DONE;
        s1[i] = stateChars[state1];
      } else
        state1 = WAITING;
    } else if ((state2 == RUNNING) && (cpuLeft2 == 0)) {
      if (ioLeft2 == 0) {
        state2 = DONE;
        s2[i] = stateChars[state2];
      } else
        state2 = WAITING;
    }
    if ((state1 == WAITING) && (ioLeft1 == 0)) {
      state1 = READY;
      cpuLeft1 = z1;
    }
    if ((state2 == WAITING) && (ioLeft2 == 0)) {
      state2 = READY;
      cpuLeft2 = z2;
    }
    /* both ready: shorter burst first, tie -> P1 wins */
    if ((state1 == READY) && (state2 == READY)) {
      if (cpuLeft1 <= cpuLeft2)
        state1 = RUNNING;
      else
        state2 = RUNNING;
    }
    /* preemption: newly-ready preempts running if strictly shorter */
    else if ((state1 == RUNNING) && (state2 == READY) &&
             (cpuLeft2 < cpuLeft1)) {
      state1 = READY;
      state2 = RUNNING;
    } else if ((state2 == RUNNING) && (state1 == READY) &&
               (cpuLeft1 < cpuLeft2)) {
      state2 = READY;
      state1 = RUNNING;
    } else if ((state1 == READY) && (state2 != RUNNING))
      state1 = RUNNING;
    else if ((state2 == READY) && (state1 != RUNNING))
      state2 = RUNNING;
    if (state1 != DONE)
      s1[i] = stateChars[state1];
    if (state2 != DONE)
      s2[i] = stateChars[state2];
    if (state1 == RUNNING)
      cpuLeft1--;
    if (state1 == WAITING)
      ioLeft1--;
    if (state2 == RUNNING)
      cpuLeft2--;
    if (state2 == WAITING)
      ioLeft2--;
  }
}

// RR scheduling
void rr(char *s1, char *s2, int quantum, int x1, int y1, int z1, int x2, int y2,
        int z2) {
  int i;
  int state1 = READY;
  int state2 = READY;
  int cpuLeft1 = x1;
  int cpuLeft2 = x2;
  int ioLeft1 = y1;
  int ioLeft2 = y2;
  int qleft = quantum;
  int readySince1 = 0; // both arrive at t=0 asme time
  int readySince2 = 0;

  for (i = 0; (state1 != DONE) || (state2 != DONE); i++) {
    // running process completes CPU burst
    if ((state1 == RUNNING) && (cpuLeft1 == 0)) {
      if (ioLeft1 == 0) {
        state1 = DONE;
        s1[i] = stateChars[state1];
      } else
        state1 = WAITING;
      readySince1 = -1;
    } else if ((state2 == RUNNING) && (cpuLeft2 == 0)) {
      if (ioLeft2 == 0) {
        state2 = DONE;
        s2[i] = stateChars[state2];
      } else
        state2 = WAITING;
      readySince2 = -1;
    }
    // running process quantum expire
    if ((state1 == RUNNING) && (qleft == 0)) {
      state1 = READY;
      readySince1 = i;
    }
    if ((state2 == RUNNING) && (qleft == 0)) {
      state2 = READY;
      readySince2 = i;
    }
    /* handle IO complete */
    if ((state1 == WAITING) && (ioLeft1 == 0)) {
      state1 = READY;
      cpuLeft1 = z1;
      readySince1 = i;
    }
    if ((state2 == WAITING) && (ioLeft2 == 0)) {
      state2 = READY;
      cpuLeft2 = z2;
      readySince2 = i;
    }
    // both are ready: oldest wins; tie ---> P1
    if ((state1 == READY) && (state2 == READY)) {
      if (readySince1 <= readySince2) {
        state1 = RUNNING;
        readySince1 = -1;
        qleft = quantum;
      } else {
        state2 = RUNNING;
        readySince2 = -1;
        qleft = quantum;
      }
    } else if ((state1 == READY) && (state2 != RUNNING)) {
      state1 = RUNNING;
      readySince1 = -1;
      qleft = quantum;
    } else if ((state2 == READY) && (state1 != RUNNING)) {
      state2 = RUNNING;
      readySince2 = -1;
      qleft = quantum;
    }
    // insert chars in string, but avoid putting in extra string terminators
    if (state1 != DONE)
      s1[i] = stateChars[state1];
    if (state2 != DONE)
      s2[i] = stateChars[state2];
    // decrement cnts
    qleft--; // fine to decrement even if nothign running
    if (state1 == RUNNING)
      cpuLeft1--;
    if (state1 == WAITING)
      ioLeft1--;
    if (state2 == RUNNING)
      cpuLeft2--;
    if (state2 == WAITING)
      ioLeft2--;
  }
}
