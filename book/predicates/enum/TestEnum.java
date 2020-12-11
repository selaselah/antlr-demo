import org.antlr.v4.runtime.*;
import java.io.InputStream;
import java.io.FileInputStream;

public class TestEnum {
    public static void main(String[] args) throws Exception {
        EnumParser.java5 = false;
        String filename = null;
        for (String arg: args) {
            if (arg.equals("-java5")) {
                EnumParser.java5 = true;
            } else {
                filename = arg;
            }
        }

        InputStream is = System.in;
        if (filename != null) {
            is = new FileInputStream(filename);
        }

        CharStream input = CharStreams.fromStream(is);
        EnumLexer lexer = new EnumLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EnumParser parser = new EnumParser(tokens);
        parser.prog();
    }
}
