import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;

public class Calc {
    public static void main(String[] args) throws Exception {
        InputStream is = System.in;
        if (args.length > 0) {
            String inputFile = args[0];
            is = new FileInputStream(inputFile);
        }
        CharStream input = CharStreams.fromStream(is);
        LabeledExprLexer lexer = new LabeledExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LabeledExprParser parser = new LabeledExprParser(tokens);
        ParseTree tree = parser.prog();
        System.out.println(tree.toStringTree(parser));

        EvalVisitor eval = new EvalVisitor();
        eval.visit(tree);
    }
}
