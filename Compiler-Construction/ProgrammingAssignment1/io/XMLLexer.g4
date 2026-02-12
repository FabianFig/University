lexer grammar XMLLexer;

OPEN_TAG : '<' TAGNAME '>' ;
CLOSE_TAG : '</' TAGNAME '>' ;
SELF_CLOSING : '<' TAGNAME '/>' ;

fragment TAGNAME : [a-z][a-z0-9_]* ;

TEXT : ~[<>]+ ;
WS : [ \t\r\n]+ -> skip;

ERROR : UNMATCHED-CLOSE </name> ;

ERROR : UNMATCHED-OPEN <name> ;
