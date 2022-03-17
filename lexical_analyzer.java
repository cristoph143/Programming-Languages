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
    Problems Encountered:
    1. The space between characters is subtle since some keywords are joined with other keyword. Example "a++;" or "a;"
*/

public class lexical_analyzer {
    public static void main(String[] args) {
        try (
                Scanner scan = new Scanner(System.in);) {
            String lexemes = new String();
            // input the source code
            System.out.println("Enter the source code: ");
            lexemes = scan.nextLine();
            // if isTerminator(lexemes) is false then exit the program.
            if (!isTerminator(lexemes)) {
                System.out.println("Syntax error, insert \";\" to complete Local Variable Declaration Statement");
                System.exit(0);
            }
            // call tokenize(lexemes) to tokenize the source code and save it as a variable
            String[] tokens = tokenize(lexemes);
            // print the tokens
            System.out.println("Token\tType");
            for (int i = 0; i < tokens.length; i++) {
                System.out.println(tokens[i] + "\t" + getTokenType(tokens[i]));
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    // return true if the last character of the string is a ";"
    public static boolean isTerminator(String s) {
        if (s.charAt(s.length() - 1) == ';') {
            return true;
        }
        return false;
    }

    // return the tokenized form of the lexemes by tokenizing the space and ";"
    public static String[] tokenize(String lexemes) {
        String[] tokens;
        //check if lexemes has a space or ";"
        if (lexemes.contains(" ") || lexemes.contains(";")) {
            // if lexemes has a space or ";" then split the string by space and ";"
            tokens = lexemes.split(" ");
            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i].contains(";")) {
                    //remove the ";"
                    tokens[i] = tokens[i].substring(0, tokens[i].length() - 1);
                    System.out.println(tokens[i]);
                    //add the ";" to last token
                    // tokens[tokens.length] = ";";
                }
            }
        } 
        else {
            // if lexemes does not have a space or ";" then return the lexemes as a string array
            tokens = new String[1];
            tokens[0] = lexemes;
        }
        return tokens;
    }
    
    
    // getTokenType
    public static String getTokenType(String token) {
        if (token.equals("int") || token.equals("float") || token.equals("double")
                || token.equals("char") || token.equals("boolean") || token.equals("byte")
                || token.equals("short") || token.equals("long")) {
            return "Data Type";
        } else if (token.equals("=")) {
            return "Equals_op";
        } else if (token.equals(";")) {
            return "Terminator";
        } else if (token.equals("+") || token.equals("-") || token.equals("*")
                || token.equals("/") || token.equals("%") || token.equals("++")
                || token.equals("--") || token.equals("==") || token.equals("!=")
                || token.equals("<") || token.equals(">") || token.equals("<=")
                || token.equals(">=") || token.equals("&&") || token.equals("||")
                || token.equals("!")) {
            return "Operator";
        }
        return "identifier";
    }

}