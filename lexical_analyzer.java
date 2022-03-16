import java.util.Scanner;

/*
    Create a lexical_analyzer that tokenizes the declaration and initialization statements
    of java variables. Store the results in dictionary.
    Here is an example of lexical_analyzer:
    Given source soude: int a = 1;
    Output:
    Token	Type
    int	    Data Type
    a	    identifier
    =	    Equals_op
    1	    Constant
    ;	    Terminator
*/

public class lexical_analyzer {
    public static void main(String[] args) {
        try (
                Scanner scan = new Scanner(System.in);) {
            String lexemes = new String();

        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}