#include "nickle.h"

const size_t PROGRAM_USER_REGS = 16;
const size_t PROGRAM_MEM_SIZE  = 65536;

const data_desc_t STATIC_DATA[] = {
};

const size_t STATIC_COUNT = sizeof(STATIC_DATA) / sizeof(STATIC_DATA[0]);

const int64_t PROGRAM[] = {
  op_loadI, 1024, 3, 
  op_loadI, 0, 1, 
  op_loadI, 0, 2, 
  op_cmp_LT, 2, PROGRAM_USER_REGS+R_ARGC_OFFSET, 4, 
  op_cbr, 4, 17, 59, 
  op_multI, 2, 8, 5, 
  op_add, PROGRAM_USER_REGS+R_ARGV_OFFSET, 5, 5, 
  op_load, 5, 6, 
  op_is_i, 6, 7, 
  op_cbr, 7, 35, 53, 
  op_atoi, 6, 8, 
  op_multI, 1, 8, 9, 
  op_add, 3, 9, 10, 
  op_store, 8, 10, 
  op_addI, 1, 1, 1, 
  op_addI, 2, 1, 2, 
  op_jumpI, 9, 
  op_loadI, 0, 2, 
  op_loadI, 0, 11, 
  op_multI, 1, 8, 9, 
  op_add, 3, 9, 12, 
  op_cmp_LT, 2, 1, 4, 
  op_cbr, 4, 81, 109, 
  op_multI, 2, 8, 9, 
  op_add, 3, 9, 10, 
  op_load, 10, 8, 
  op_add, 11, 8, 11, 
  op_add, 12, 9, 10, 
  op_store, 11, 10, 
  op_addI, 2, 1, 2, 
  op_jumpI, 73, 
  op_loadI, 1024, 13, 
  op_multI, 1, 16, 14, 
  op_add, 13, 14, 15, 
  op_d_mem, 13, 15, 
  op_halt, 
  op_halt
}; 

const char* PROMPTS[] = {
};

