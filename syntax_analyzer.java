import java.util.Scanner;

public class syntax_analyzer{
    // return the syntax_analyzer
    public static String[] syntax_analyzer(String[] tokens) {
        // print tokens
        System.out.println("\nToken\tType");
        for (int i = 0; i < tokens.length; i++) {
            System.out.println(tokens[i] + "\t" + lexical_analyzer.getTokenType(tokens[i]));
        }
        return tokens;
    }
}