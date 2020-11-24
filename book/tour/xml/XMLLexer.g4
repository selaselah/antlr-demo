lexer grammar XMLLexer;

// OUTSIDE
OPEN        :  '<'                -> pushMode(INSIDE) ;
COMMENT     :  '<!--' .*? '-->'   -> skip ;
EntiryRef   :  '&' [a-z]+ ';' ;
TEXT        :  ~('<'|'&')+ ;

// INSIDE
mode INSIDE;
CLOSE       :  '>'                -> popMode ;
SLASH_CLOSE :  '/>'               -> popMode ;
EQUALS      :  '=' ;
STRING      :  '"' .*? '"' ;
SlashName   :  '/' Name ;
Name        :  ALPHA (ALPHA|DIGIT)* ;
S           :  [ \t\r\n]          -> skip ;

fragment
ALPHA       :  [a-zA-Z] ;
DIGIT       :  [0-9] ;
