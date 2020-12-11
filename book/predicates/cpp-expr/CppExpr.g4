grammar CppExpr;

expr:  {isfunc(ID)}? ID '(' expr ')'
    |  {istype(ID)}? ID '(' expr ')'   // ctor-style ctype cast of expr
    |  INT
    |  ID
    ;

ID  :  [a-zA-Z]+ ;
INT :  [0-9]+ ;
WS  :  [ \t\r\n]+ -> skip ;
