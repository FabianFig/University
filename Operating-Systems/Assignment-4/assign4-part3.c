#include <errno.h>
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

static const char *AUTHOR = "Fabian Figueroa";

static pthread_mutex_t randomMutex = PTHREAD_MUTEX_INITIALIZER;
static pthread_mutex_t orderMutex = PTHREAD_MUTEX_INITIALIZER;
static pthread_cond_t orderCond = PTHREAD_COND_INITIALIZER;
static int nextIndex = 0;

static int delayUsec(void)
{
    pthread_mutex_lock(&randomMutex);
    long value = random();
    pthread_mutex_unlock(&randomMutex);
    return (int)((value % 500L) + 1L) * 1000;
}

void thinking(void)
{
    usleep((useconds_t)delayUsec());
}

void eating(void)
{
    usleep((useconds_t)delayUsec());
}

void *philosopherThread(void *pVoid)
{
    int philosopher = *(int *)pVoid;

    printf("Philosopher #%d: starts thinking\n", philosopher);
    thinking();
    printf("Philosopher #%d: ends thinking\n", philosopher);

    pthread_mutex_lock(&orderMutex);
    while (nextIndex != philosopher) {
        pthread_cond_wait(&orderCond, &orderMutex);
    }

    printf("Philosopher #%d: starts eating\n", philosopher);
    eating();
    printf("Philosopher #%d: ends eating\n", philosopher);

    nextIndex++;
    pthread_cond_broadcast(&orderCond);
    pthread_mutex_unlock(&orderMutex);
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

    nextIndex = 0;
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

    srandom((unsigned int)(time(NULL) ^ (unsigned int)getpid()));
    printf("%s Assignment 4: # of threads = %d\n", AUTHOR, nthreads);
    creatPhilosophers(nthreads);
    return EXIT_SUCCESS;
}