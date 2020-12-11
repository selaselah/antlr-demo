grammar PredCppStat;

@parser::header {
import java.util.*;
}

@parser::members {
Set<String> types = new HashSet<String>() {{add("T");}};
boolean istype() { return types.contains(getCurrentToken().getText()); }
}

stat:  decl ';' {System.out.println("decl "+$decl.text);}
    |  expr ';' {System.out.println("expr "+$expr.text);}
    ;

decl:  ID ID
    |  {istype()}? ID '(' ID ')'
    ;

expr:  INT
    |  ID
    |  {!istype()}? ID '(' expr ')'
    ;

ID  :  [a-zA-Z]+ ;
INT :  [0-9]+ ;
WS  :  [ \t\n\r]+ -> skip ;
