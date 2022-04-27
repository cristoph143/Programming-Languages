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
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
