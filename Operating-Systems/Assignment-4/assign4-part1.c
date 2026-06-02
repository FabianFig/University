#include <errno.h>
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

static const char *AUTHOR = "Fabian Figueroa";

void *philosopherThread(void *pVoid)
{
    int philosopher = *(int *)pVoid;

    printf("This is philosopher %d\n", philosopher);
    return NULL;
}

void creatPhilosophers(int nthreads)
{
    pthread_t *threads = malloc((size_t)nthreads * sizeof(*threads));
    int *indices = malloc((size_t)nthreads * sizeof(*indices));

    if (threads == NULL || indices == NULL) {
        perror("malloc");
        free(threads);
        free(indices);
        exit(EXIT_FAILURE);
    }

    for (int i = 0; i < nthreads; ++i) {
        indices[i] = i;
        if (pthread_create(&threads[i], NULL, philosopherThread, &indices[i]) != 0) {
            perror("pthread_create");
            free(threads);
            free(indices);
            exit(EXIT_FAILURE);
        }
    }

    for (int i = 0; i < nthreads; ++i) {
        if (pthread_join(threads[i], NULL) != 0) {
            perror("pthread_join");
            free(threads);
            free(indices);
            exit(EXIT_FAILURE);
        }
    }

    printf("%d threads have been completed/joined successfully!\n", nthreads);

    free(threads);
    free(indices);
}

int main(int argc, char *argv[])
{
    if (argc != 2) {
        fprintf(stderr, "Usage: %s <nthreads>\n", argv[0]);
        return EXIT_FAILURE;
    }

    int nthreads = atoi(argv[1]);
    if (nthreads <= 0) {
        fprintf(stderr, "nthreads must be a positive integer\n");
        return EXIT_FAILURE;
    }

    printf("%s Assignment 4: # of threads = %d\n", AUTHOR, nthreads);
    creatPhilosophers(nthreads);
    return EXIT_SUCCESS;
}