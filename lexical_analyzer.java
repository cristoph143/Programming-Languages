import java.util.Scanner;

/*
    Create a lexical_analyzer that tokenizes the declaration and initialization statements
    of java variables. Store the results in dictionary.
    The basic form of declaring a variable is:
        type identifier [= value] [, identifier [= value]]…]; // int x = y = z = 1; or int x, y, z;
            OR
        data_type variable_name = value; // int x = 1; or int x;
        where,
        type = Data type of the variable // int
        identifier = Variable name // x
        value = Data to be stored in the variable (Optional field)
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
            //input the source code
            System.out.println("Enter the source code: ");
            lexemes = scan.nextLine();
            //if isTerminator(lexemes) is false then exit the program.
            if(!isTerminator(lexemes)){
                System.out.println("Syntax error, insert \";\" to complete Local Variable Declaration Statement"); 
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    //return true if the last character of the string is a ";"
    public static boolean isTerminator(String s) {
        if (s.charAt(s.length() - 1) == ';') {
            return true;
        }
        return false;
    }
}