#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <inttypes.h>

// programme will be taking in multiple file names
int main(int argc, char *argv[]) {
    
    if (argc < 2) {
        fprintf(stderr, "Usage: %s <file1> [file2 ...]\n", argv[0]);
        return EXIT_FAILURE;
    }

    //file opening
    for(int i = 1; i < argc; i++) {
        const char *filename = argv[i];

        // filename pass check
        FILE *fp = fopen(filename, "rb");

        if(fp == NULL) {
            perror(filename);
            continue;
        }
        uint64_t value;
        int line = 1;

        // reading file
        // fread(pointer_to_memory, size_of_each_element, number_of_elements, file)
        while(fread(&value, sizeof(uint64_t), 1, fp) == 1) {
            value = __builtin_bswap64(value); // swap FIRST
            uint8_t low = value & 0xFF;
            uint64_t high = value >> 8;

            printf("Address %4d: 0x%016" PRIx64 " -> 0x%014" PRIx64 " : 0x%02" PRIx8 "\n", line, value, high, low);
            line++;
        }

        // after loop ends check why it ended
        if (feof(fp)){
                printf("EOF %s \n", filename);
        } else {
            perror("Error reading file");
        }
    
        
        /* if(fread != 0) {

        }

        printf("64-bit val is %" PRIu64 "\n", buffer[i]);
        */
        fclose(fp);

    }

    return 0;
}
