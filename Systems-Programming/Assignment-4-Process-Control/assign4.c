#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <unistd.h>

#define MAX_CMDS 6
#define MAX_ARGS 32

typedef struct {
    char* args[MAX_ARGS];
    char* cmd_name;
} Command;

int main(int argc, char* argv[]) {
        
    if(argc < 2) {
        fprintf(stderr, "Usage: %s cmd1 [args] , cmd2 [args] , ...\n", argv[0]); 
        exit(EXIT_FAILURE);
    }

    Command commands[MAX_CMDS];
    int cmdCnt = 0;
    int argIndx = 0;

    //1. parse argv into command structs ("," as delimiter)
    for(int i = 1; i < argc; ++i) {
        if(strcmp(argv[i], ",") == 0) {
            if(cmdCnt >= MAX_CMDS) {
                fprintf(stderr, "Error! Too many commands (max is %d).", MAX_CMDS);
                exit(EXIT_FAILURE);
            }
            commands[cmdCnt].args[argIndx] = NULL;
            argIndx = 0;
            cmdCnt++;
        } else {
            if (argIndx == 0) {
                commands[cmdCnt].cmd_name = argv[i];
            }
            commands[cmdCnt].args[argIndx++] = argv[i];
        }
    }

    if (cmdCnt >=  MAX_CMDS) {
        fprintf(stderr, "Error: Too many commands (max is %d)\n", MAX_CMDS);
        exit(EXIT_FAILURE);  
    }
    commands[cmdCnt].args[argIndx] = NULL;
    cmdCnt++;

    pid_t pids[MAX_CMDS];
    for (int i = 0; i < cmdCnt; i++) {
        pid_t pid = fork();
        if (pid < 0) {
            perror("fork");
            exit(EXIT_FAILURE);
        } else if (pid == 0) {
            fprintf(stderr, "PID: %d, PPID: %d, CMD: %s\n", getpid(), getppid(), commands[i].cmd_name);
            execvp(commands[i].args[0], commands[i].args);
            perror("execvp failed");
            exit(EXIT_FAILURE);
        } else {
            pids[i] = pid;
        }
    }

    for (int i = 0; i < cmdCnt; i++) {
        waitpid(pids[i], NULL, 0);
    }

    return 0;
}
