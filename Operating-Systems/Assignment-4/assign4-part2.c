#include <errno.h>
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

static const char *AUTHOR = "Fabian Figueroa";

static pthread_mutex_t randomMutex = PTHREAD_MUTEX_INITIALIZER;
static pthread_mutex_t *chopsticks = NULL;
static int philosopherCount = 0;

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

void pickUpChopsticks(int threadIndex)
{
    int left = threadIndex;
    int right = (threadIndex + 1) % philosopherCount;

    if (left == right) {
        pthread_mutex_lock(&chopsticks[left]);
        return;
    }

    pthread_mutex_lock(&chopsticks[left]);
    pthread_mutex_lock(&chopsticks[right]);
}

void putDownChopsticks(int threadIndex)
{
    int left = threadIndex;
    int right = (threadIndex + 1) % philosopherCount;

    if (left == right) {
        pthread_mutex_unlock(&chopsticks[left]);
        return;
    }

    pthread_mutex_unlock(&chopsticks[right]);
    pthread_mutex_unlock(&chopsticks[left]);
}

void *philosopherThread(void *pVoid)
{
    int philosopher = *(int *)pVoid;

    printf("Philosopher #%d: starts thinking\n", philosopher);
    thinking();
    printf("Philosopher #%d: ends thinking\n", philosopher);

    pickUpChopsticks(philosopher);

    printf("Philosopher #%d: starts eating\n", philosopher);
    eating();
    printf("Philosopher #%d: ends eating\n", philosopher);

    putDownChopsticks(philosopher);
    return NULL;
}

void creatPhilosophers(int nthreads)
{
    philosopherCount = nthreads;
    chopsticks = malloc((size_t)philosopherCount * sizeof(*chopsticks));
    pthread_t *threads = malloc((size_t)philosopherCount * sizeof(*threads));
    int *indices = malloc((size_t)philosopherCount * sizeof(*indices));

    if (chopsticks == NULL || threads == NULL || indices == NULL) {
        perror("malloc");
        free(chopsticks);
        free(threads);
        free(indices);
        exit(EXIT_FAILURE);
    }

    for (int i = 0; i < philosopherCount; ++i) {
        if (pthread_mutex_init(&chopsticks[i], NULL) != 0) {
            perror("pthread_mutex_init");
            free(chopsticks);
            free(threads);
            free(indices);
            exit(EXIT_FAILURE);
        }
    }

    for (int i = 0; i < philosopherCount; ++i) {
        indices[i] = i;
        if (pthread_create(&threads[i], NULL, philosopherThread, &indices[i]) != 0) {
            perror("pthread_create");
            free(chopsticks);
            free(threads);
            free(indices);
            exit(EXIT_FAILURE);
        }
    }

    for (int i = 0; i < philosopherCount; ++i) {
        if (pthread_join(threads[i], NULL) != 0) {
            perror("pthread_join");
            free(chopsticks);
            free(threads);
            free(indices);
            exit(EXIT_FAILURE);
        }
    }

    for (int i = 0; i < philosopherCount; ++i) {
        pthread_mutex_destroy(&chopsticks[i]);
    }

    free(chopsticks);
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