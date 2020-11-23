import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.InputStream;
import java.io.FileInputStream;

public class Col {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.err.println("Usage: java Col <col-num> <inp>");
        }
        int col = Integer.valueOf(args[0]);

        InputStream is = System.in;
        if (args.length > 1) {
            String inputFile = args[1];
            is = new FileInputStream(inputFile);
        }
        CharStream input = CharStreams.fromStream(is);

        RowsLexer lexer = new RowsLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RowsParser parser = new RowsParser(tokens, col);
        parser.setBuildParseTree(false);
        parser.file();
    }
}
