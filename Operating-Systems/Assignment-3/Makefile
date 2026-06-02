CC     = gcc
CFLAGS = -Wall -Wextra -g

all: part1 part2 part3

part1: part1.c
	$(CC) $(CFLAGS) -o part1 part1.c

part2: part2.c pagetable.c phypages.c
	$(CC) $(CFLAGS) -o part2 part2.c pagetable.c phypages.c

part3: part3.c
	$(CC) $(CFLAGS) -o part3 part3.c

clean:
	rm -f part1 part2 part3