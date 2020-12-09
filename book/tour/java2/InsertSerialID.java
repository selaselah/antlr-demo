import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.InputStream;
import java.io.FileInputStream;

public class InsertSerialID {
    public static void main(String[] args) throws Exception {
        InputStream is = System.in;
        if (args.length > 0) {
            String inputFile = args[0];
            is = new FileInputStream(inputFile);
        }

        CharStream input = CharStreams.fromStream(is);
        JavaLexer lexer = new JavaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        /*
         * // starting point for parsing a java file
         * compilationUnit
         *     :   packageDeclaration? importDeclaration* typeDeclaration*
         *         EOF
         *     ;
         */
        ParseTree tree = parser.compilationUnit();

        ParseTreeWalker walker = new ParseTreeWalker();
        InsertSerialIDListener insertor = new InsertSerialIDListener(tokens);
        walker.walk(insertor, tree);
        System.out.println(insertor.rewriter.getText());
    }
}
