#include "nickle.h"

const size_t PROGRAM_USER_REGS = 4;
const size_t PROGRAM_MEM_SIZE  = 64;

const data_desc_t STATIC_DATA[] = {
    { .kind = D_CHAR,   .v.c = 65 },
    { .kind = D_INT,    .v.i = 42 },
    { .kind = D_CHAR,   .v.c = 66 },
    { .kind = D_STRING, .v.s = "Hi" },
};

const size_t STATIC_COUNT = sizeof(STATIC_DATA) / sizeof(STATIC_DATA[0]);

const int64_t PROGRAM[] = {
  op_p_prompt, 0, 
  op_loadAI, PROGRAM_USER_REGS+R_STATIC_OFFSET, 1, 1, 
  op_p_int, 1, 
  op_loadAI, PROGRAM_USER_REGS+R_STATIC_OFFSET, 0, 1, 
  op_p_char, 1, 
  op_loadAI, PROGRAM_USER_REGS+R_STATIC_OFFSET, 9, 1, 
  op_p_char, 1, 
  op_loadAI, PROGRAM_USER_REGS+R_STATIC_OFFSET, 10, 1, 
  op_p_str, 1, 
  op_loadI, 0, 1, 
  op_cmp_LT, 1, PROGRAM_USER_REGS+R_ARGC_OFFSET, 2, 
  op_cbr, 2, 37, 56, 
  op_multI, 1, 8, 3, 
  op_add, PROGRAM_USER_REGS+R_ARGV_OFFSET, 3, 3, 
  op_load, 3, 3, 
  op_p_str, 3, 
  op_addI, 1, 1, 1, 
  op_jumpI, 29, 
  op_p_prompt, 1, 
  op_halt, 
  op_halt
}; 

const char* PROMPTS[] = {
    "here we go!\n"
  , "we're done\n"
};

