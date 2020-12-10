package tools;

import org.antlr.v4.runtime.*;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Calc {
    public static void main(String[] args) throws Exception {
        InputStream is = System.in;
        if (args.length > 0) {
            is = new FileInputStream(args[0]);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String expr = br.readLine();
        int line = 1;
        ExprParser parser = new ExprParser(null);
        parser.setBuildParseTree(false);
        while (expr != null) {
            CharStream input = CharStreams.fromString(expr + "\n");
            ExprLexer lexer = new ExprLexer(input);
            lexer.setLine(line);
            lexer.setCharPositionInLine(0);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            parser.setInputStream(tokens);
            parser.stat();
            expr = br.readLine();
            line++;
        }
    }
}
