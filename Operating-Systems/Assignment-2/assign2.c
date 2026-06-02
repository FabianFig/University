#include "pslibrary.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_SIZE 512
#define QUANTUM 3

// counting occurrences of char c in the null-term string s
static int countChar(const char *s, char c) {
  int n = 0;
  while (*s) {
    if (*s == c)
      n++;
    s++;
  }
  return n;
}

// printing the stats of wait1, wait2, avg wait, CPU util
static void printStats(const char *s1, const char *s2) {
  int wait1 = countChar(s1, 'r');
  int wait2 = countChar(s2, 'r');
  int runAll = countChar(s1, 'R') + countChar(s2, 'R');
  int len1 = (int)strlen(s1);
  int len2 = (int)strlen(s2);
  int longer = (len1 >= len2) ? len1 : len2;
  printf("%d %d %.1f %.5f\n", wait1, wait2, (wait1 + wait2) / 2.0,
         (longer > 0) ? (runAll / (double)longer) : 0.0);
}

int main(int argc, char *argv[]) {
  char s1[MAX_SIZE];
  char s2[MAX_SIZE];
  int q, x1, y1, z1, x2, y2, z2;

  if (argc != 8) {
    fprintf(stderr, "Usage: %s q x1 y1 z1 x2 y2 z2\n", argv[0]);
    return EXIT_FAILURE;
  }

  q = atoi(argv[1]);
  x1 = atoi(argv[2]);
  y1 = atoi(argv[3]);
  z1 = atoi(argv[4]);
  x2 = atoi(argv[5]);
  y2 = atoi(argv[6]);
  z2 = atoi(argv[7]);

  printf("CS 3733 / Assignment 2 / written by Fabian Figueroa / sop310\n");
  printf("%d %d %d %d %d %d %d\n", q, x1, y1, z1, x2, y2, z2);

  // FCFS
  memset(s1, 0, MAX_SIZE);
  memset(s2, 0, MAX_SIZE);
  fcfs(s1, s2, x1, y1, z1, x2, y2, z2);
  printf("\nsscheduler FCFS:\n");
  printf("%s\n", s1);
  printf("%s\n", s2);
  printStats(s1, s2);

  // SJF
  memset(s1, 0, MAX_SIZE);
  memset(s2, 0, MAX_SIZE);
  sjf(s1, s2, x1, y1, z1, x2, y2, z2);
  printf("\nScheduler SJF:\n");
  printf("%s\n", s1);
  printf("%s\n", s2);
  printStats(s1, s2);

  // PSJF
  memset(s1, 0, MAX_SIZE);
  memset(s2, 0, MAX_SIZE);
  psjf(s1, s2, x1, y1, z1, x2, y2, z2);
  printf("\nScheduler PSJF:\n");
  printf("%s\n", s1);
  printf("%s\n", s2);
  printStats(s1, s2);

  // RR
  memset(s1, 0, MAX_SIZE);
  memset(s2, 0, MAX_SIZE);
  rr(s1, s2, q, x1, y1, z1, x2, y2, z2);
  printf("\nScheduler RR (quantum=%d):\n", q);
  printf("%s\n", s1);
  printf("%s\n", s2);
  printStats(s1, s2);

  return EXIT_SUCCESS;
}
