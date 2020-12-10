grammar CSV;

@header {
import java.util.*;
}

/** 由规则 "file : hdr row+ ;" 衍生而来 */
file
locals [int i=0]
    :  hdr (rows+=row[$hdr.text.split(",")] {$i++;} )+
       {
       System.out.println($i+" rows");
       for (RowContext r : $rows) {  // rows is a List<RowContext>
           System.out.println("row token interval: "+r.getSourceInterval());
       }
       }
    ;

hdr : row[null] {System.out.println("header: '"+$text.trim()+"'");} ;

row[String[] columns] returns [Map<String,String> values]
locals [int col=0]
@init {
    $values = new HashMap<>();
}
@after {
    if ($values!=null && $values.size()>0) {
        System.out.println("values = "+$values);
    }
}
    :  field
       {
       if ($columns!=null) {
           $values.put($columns[$col++].trim(), $field.text.trim());
       }
       }
       ( ',' field
         {
         if ($columns!=null) {
             $values.put($columns[$col++].trim(), $field.text.trim());
         }
         }
       )* '\r'? '\n'
    ;

field
    : TEXT
    | STRING
    |
    ;

TEXT : ~[,\n\r"]+ ;
STRING : '"' ('""'|~'"')* '"' ;

