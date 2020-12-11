grammar CppStat;

stat:  decl ';'  {System.out.println("decl "+$decl.text);}
    |  expr ';'  {System.out.println("expr "+$expr.text);}
    ;

decl:  ID ID
    |  ID '(' ID ')'
    ;

expr:  INT
    |  ID
    |  ID '(' expr ')'
    ;

ID  :  [a-zA-Z]+ ;
INT :  [0-9]+ ;
WS  :  [ \t\n\r]+ -> skip ;
