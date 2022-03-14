import java.util.Scanner;

public class lexical_analyzer {
    public static void main(String[] args) {
        try (
                Scanner scan = new Scanner(System.in);) {
            String lexemes = new String();

            String[] keywords = { "int", "float", "double", "boolean", "char", "void", "if", "else", "while", "do",
                    "for",
                    "return", "break", "continue", "switch", "case", "default", "true", "false", "null", "this", "new",
                    "class",
                    "extends", "implements", "interface", "package", "import", "static", "final", "abstract", "enum",
                    "volatile", "const", "goto", "super", "throws", "instanceof" };
            String[] operators = { "+", "-", "*", "/", "=", "==", "!=", ">", "<", ">=", "<=", "&&", "||" };
            String[] delimiters = { "(", ")", "{", "}", ";", ",", "[", "]", "." };
            String[] special_symbols = { "\"", "\'", "\\" };

            // input the sequence of lexemes
            System.out.println("Enter the sequence of lexemes");
            lexemes = scan.nextLine();
            // iterate the delimiters array and check if the end of the lexeme string is
            // equal to the index of delimiters array
            for (int j = 0; j < delimiters.length; j++) {
                //if last index of lexeme is equal to the index of delimiters array
                if (lexemes.charAt(lexemes.length() - 1) == delimiters[j].charAt(0)) {
                    // print the lexeme
                    System.out.println(lexemes.charAt(lexemes.length() - 1));
                    // break the loop
                    break;
                }
            }
            // check the lexemes
            
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}