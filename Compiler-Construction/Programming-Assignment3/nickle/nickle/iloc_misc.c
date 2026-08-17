#include "iloc.h"
#include "cpu.h"

#include <ctype.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

void iloc_i2i() {
    size_t r1 = r() ; size_t r2 = r() ; 
    cpu->regs[r2] = cpu->regs[r1];
}

void iloc_c2c() {
    size_t r1 = r() ; size_t r2 = r() ; 
    cpu->regs[r2] = cpu->regs[r1] & 0xFFu;
}

void iloc_c2i() {
    size_t r1 = r() ; size_t r2 = r() ; 
    cpu->regs[r2] = cpu->regs[r1] & 0xFFu;
}

void iloc_i2c() {
    size_t r1 = r() ; size_t r2 = r() ; 
    cpu->regs[r2] = cpu->regs[r1] & 0xFFu;
}

static int is_bin_digit(char c){ return c=='0' || c=='1'; }
static int is_oct_digit(char c){ return c>='0' && c<='7'; }
static int is_dec_digit(char c){ return c>='0' && c<='9'; }
static int is_hex_digit(char c){ return (c>='0'&&c<='9') || (c>='a'&&c<='f') || (c>='A'&&c<='F'); }

static int64_t parse_u64(const char* s) {
    if (*s == '\0') nickle_trap("atoi: empty string");

    int base = 10;
    if (s[0]=='0' && (s[1]=='x' || s[1]=='X')) { base = 16; s += 2; if (*s=='\0') nickle_trap("atoi: invalid hex"); }
    else if (s[0]=='0' && (s[1]=='b' || s[1]=='B')) { base = 2; s += 2; if (*s=='\0') nickle_trap("atoi: invalid binary"); }
    else if (s[0]=='0' && s[1] != '\0') { base = 8; s += 1; }

    int64_t v = 0;
    for (; *s; s++) {
        char c = *s;
        int digit;
        if (base == 2)  { if (!is_bin_digit(c)) nickle_trap("atoi: invalid binary"); digit = c - '0'; }
        else if (base == 8) { if (!is_oct_digit(c)) nickle_trap("atoi: invalid octal"); digit = c - '0'; }
        else if (base == 10) { if (!is_dec_digit(c)) nickle_trap("atoi: invalid decimal"); digit = c - '0'; }
        else { // 16
            if (!is_hex_digit(c)) nickle_trap("atoi: invalid hex");
            if (c>='0'&&c<='9') digit = c-'0';
            else if (c>='a'&&c<='f') digit = 10 + (c-'a');
            else digit = 10 + (c-'A');
        }
        v = v * base + digit;
    }
    return v;
}

void iloc_atoi() {
    size_t r_src = r() ; size_t r_dst = r() ; 

    uint64_t addr = cpu->regs[r_src];

    // read null-terminated string within bounds
    uint64_t i = addr;
    while (1) {
        nickle_check_mem(i, 1);
        if (cpu->mem[i] == 0) break;
        i++;
    }
    size_t len = (size_t)(i - addr);
    // copy to local buffer
    char* tmp = (char*)malloc(len + 1);
    if (!tmp) nickle_trap("atoi: allocation failed");
    memcpy(tmp, cpu->mem + addr, len);
    tmp[len] = 0;

    int64_t v = parse_u64(tmp);
    free(tmp);
    cpu->regs[r_dst] = v;
}


static int64_t is_u64(const char* s) {
    if (*s == '\0') return 0;

    int base = 10;
    if (s[0]=='0' && (s[1]=='x' || s[1]=='X')) { base = 16; s += 2; if (*s=='\0') return 0 ; }
    else if (s[0]=='0' && (s[1]=='b' || s[1]=='B')) { base = 2; s += 2; if (*s=='\0') return 0 ; }
    else if (s[0]=='0' && s[1] != '\0') { base = 8; s += 1; }

    for (; *s; s++) {
        char c = *s;
        if (base == 2)  { if (!is_bin_digit(c)) return 0 ; }
        else if (base == 8) { if (!is_oct_digit(c)) return 0 ; }
        else if (base == 10) { if (!is_dec_digit(c)) return 0 ; }
        else { // 16
            if (!is_hex_digit(c)) return 0 ;
        }
    }
    return 1;
}



void iloc_is_i() {
    size_t r_src = r() ; size_t r_dst = r() ; 
    uint64_t addr = cpu->regs[r_src];

    // read null-terminated string within bounds
    uint64_t i = addr;
    while (1) {
        nickle_check_mem(i, 1);
        if (cpu->mem[i] == 0) break;
        i++;
    }
    size_t len = (size_t)(i - addr);
    // copy to local buffer
    char* tmp = (char*)malloc(len + 1);
    if (!tmp) nickle_trap("atoi: allocation failed");
    memcpy(tmp, cpu->mem + addr, len);
    tmp[len] = 0;

    uint64_t v = is_u64(tmp);
    free(tmp);
    cpu->regs[r_dst] = v;
}


void iloc_halt() {
    cpu->halted = true;
}
