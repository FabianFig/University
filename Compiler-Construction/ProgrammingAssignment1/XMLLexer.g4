lexer grammar XMLLexer;


SELF_CLOSING : '<' TAGNAME '/>' ;
OPEN_TAG : '<' TAGNAME '>' ;
CLOSE_TAG : '</' TAGNAME '>' ;

fragment TAGNAME : [a-z_][a-z0-9_]* ;

TEXT : ~[<>]+ ;
WS : [ \t\r\n]+ -> skip ;