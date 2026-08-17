#include "nickle.h"

const size_t PROGRAM_USER_REGS = 6;
const size_t PROGRAM_MEM_SIZE  = 65536;

const data_desc_t STATIC_DATA[] = {
};

const size_t STATIC_COUNT = sizeof(STATIC_DATA) / sizeof(STATIC_DATA[0]);

const int64_t PROGRAM[] = {
  op_load, PROGRAM_USER_REGS+R_ARGV_OFFSET, 1, 
  op_atoi, 1, 2, 
  op_loadI, 1, 3, 
  op_loadI, 0, 5, 
  op_cmp_GT, 3, 2, 4, 
  op_cbr, 4, 30, 20, 
  op_add, 5, 3, 5, 
  op_addI, 3, 1, 3, 
  op_jumpI, 12, 
  op_p_int, 5, 
  op_halt, 
  op_halt
}; 

const char* PROMPTS[] = {
};

