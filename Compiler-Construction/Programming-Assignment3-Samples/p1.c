#include "nickle.h"

const size_t PROGRAM_USER_REGS = 8;
const size_t PROGRAM_MEM_SIZE  = 65536;

const data_desc_t STATIC_DATA[] = {
};

const size_t STATIC_COUNT = sizeof(STATIC_DATA) / sizeof(STATIC_DATA[0]);

const int64_t PROGRAM[] = {
  op_cmp_LT, 0, PROGRAM_USER_REGS+R_ARGC_OFFSET, 1, 
  op_cbr, 1, 8, 101, 
  op_load, PROGRAM_USER_REGS+R_ARGV_OFFSET, 2, 
  op_p_str, 2, 
  op_loadI, 0, 3, 
  op_loadI, 0, 5, 
  op_cloadAO, 2, 3, 4, 
  op_cmp_EQ, 4, 5, 1, 
  op_cbr, 1, 99, 31, 
  op_loadI, 65, 6, 
  op_cmp_GE, 4, 6, 7, 
  op_cbr, 7, 42, 63, 
  op_loadI, 90, 6, 
  op_cmp_LE, 4, 6, 7, 
  op_cbr, 7, 53, 63, 
  op_addI, 4, 32, 4, 
  op_cstoreAO, 4, 2, 3, 
  op_jumpI, 93, 
  op_loadI, 97, 6, 
  op_cmp_GE, 4, 6, 7, 
  op_cbr, 7, 74, 93, 
  op_loadI, 122, 6, 
  op_cmp_LE, 4, 6, 7, 
  op_cbr, 7, 85, 93, 
  op_subI, 4, 32, 4, 
  op_cstoreAO, 4, 2, 3, 
  op_addI, 3, 1, 3, 
  op_jumpI, 19, 
  op_p_str, 2, 
  op_halt, 
  op_halt
}; 

const char* PROMPTS[] = {
};

