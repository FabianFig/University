grammar PA4 ; 

program: definition+ ;

definition: ID '(' params ')' '=' expr ';' ;

params: | ID (',' ID) *;

expr
    : cond
    ;

cond
    : add
    | add '?' expr ':' cond
    ;

add : mul (('+'|'-') mul)* ;
mul : unary (('*'|'/') unary)* ;

unary
    : primary
    ;

primary
    : INT
    | ID
    | fncall
    | '(' expr ')'
    ;

fncall: ID '(' args ')' ;
args: | expr (',' expr)* ;

INT : [0-9]+ ;
ID  : [a-zA-Z_][a-zA-Z_0-9]* ;
WS  : [ \t\r\n]+ -> skip ;
