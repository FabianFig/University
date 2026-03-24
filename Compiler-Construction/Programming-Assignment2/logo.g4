grammar logo;

stmt
  : 'fd' expr                       #fd
  | 'bk' expr                       #bk
  | 'rt' expr                       #rt
  | 'lt' expr                       #lt
  | 'pu'                            #pu
  | 'pd'                            #pd
  | 'hm'                            #hm
  | 'sc' expr                       #sc
  | 'rp' expr stmts                 #repeat
  | 'ifz' expr stmts stmts          #ifz
  | 'as' ID expr                    #as
  | 'wd' expr                       #wd
  ;

expr
  : <assoc=right> e1=expr '^' e2=expr   #pow
  | e1=expr '*' e2=expr                  #mul
  | e1=expr '/' e2=expr                  #div
  | e1=expr '%' e2=expr                  #mod
  | e1=expr '+' e2=expr                #add
  | e1=expr '-' e2=expr                  #sub
  | '+' e=expr                           #unaryPlus
  | '-' e=expr                           #unaryMinus
  | '(' e=expr ')'                       #parens
  | INT                                  #int
  | ID                                   #id
  ;

INT : [0-9]+ ;

WS      : [ \t\r\n]+ -> skip ;
stmts : '[' stmt* ']' ;
ID : [A-Za-z_][A-Za-z0-9_]* ;