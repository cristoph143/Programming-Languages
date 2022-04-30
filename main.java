import java.util.Scanner;

public class main {
    public static void main(String[] args){
        try (
            Scanner scan = new Scanner(System.in);) {
            String lexemes = new String();
            // input the source code
            System.out.println("Enter the source code: ");
            lexemes = scan.nextLine();
            // call lexical_analyzer and store the results to a variable
            String[] tokens = lexical_analyzer.tokenize(lexemes);
            // print the tokens
            System.out.println("\nToken\tType");
            for (int i = 0; i < tokens.length; i++) {
                System.out.println(tokens[i] + "\t" + lexical_analyzer.getTokenType(tokens[i]));
            }
            // call final_tokens
            String[] final_tokens = lexical_analyzer.final_tokens(tokens);
            // print the final_tokens
            System.out.println("\nFinal Token\tType");
            for (int i = 0; i < final_tokens.length; i++) {
                System.out.println(final_tokens[i] + "\t" + lexical_analyzer.getTokenType(final_tokens[i]));
            }
            // call syntax_analyzer and store the results to a variable
            String[] syntax = syntax_analyzer.syntax_analyzer(final_tokens);
            // print the syntax
            System.out.println("\nSyntax\tType");
            for (int i = 0; i < syntax.length; i++) {
                System.out.println(syntax[i] + "\t" + lexical_analyzer.getTokenType(syntax[i]));
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
