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
            // call tokenize(lexemes) to tokenize the source code and save it as a variable
            String[] tokens = tokenize(lexemes);
            // print the tokens
            System.out.println("\nToken\tType");
            for (int i = 0; i < tokens.length; i++) {
                System.out.println(tokens[i] + "\t" + getTokenType(tokens[i]));
            }
            // System.out.println(lastChar + "\t" + getTokenType(lastChar));
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    // return the tokenized form of the lexemes by tokenizing the space and ";"
    public static String[] tokenize(String lexemes) {
        //tokenize the lexeme by one or multiple space.
        String[] tokens = lexemes.split("\\s+");
        String[] tmp_array = new String[lexemes.length()]; 
        // iterate the tokens
        for (int i = 0; i < tokens.length; i++) {
            // if the returned value from getTokenType(tokens[i]) is not null then add
            // index value to new array.
            if (getTokenType(tokens[i]) != null) {
                getTokenNull(tokens, tmp_array, i);
            }
            // else if the returned value is null then add the last character of the token
            // to new array and remove it to the tokens.
            else {
                getTokenEqualNull(tokens, tmp_array, i);
            }
        }
        // print the tokens
        for (int i = 0; i < tmp_array.length; i++) {
            System.out.print(tmp_array[i] + " ");
            //if tmp_array is null, trim the array from the first null to end
            if (tmp_array[i] == null) {
                String[] new_array = new String[i];
                for (int j = 0; j < i; j++) {
                    new_array[j] = tmp_array[j];
                }
                return new_array;
            }
        }
        return tmp_array;
    }

    private static void getTokenNull(String[] tokens, String[] tmp_array, int i) {
        tokens[i] = tokens[i];
        // add tokens[i] to tmp_a;
        tmp_array[i] = tokens[i];
        System.out.println(tokens[i] + "\t" + getTokenType(tokens[i]) + " " 
            + tmp_array[i]);
    }

    private static void getTokenEqualNull(String[] tokens, String[] tmp_array, int i) {
        String temp_str = tokens[i];
        temp_str = tokenized_extended(tokens, i, temp_str);
        System.out.println("\nReturn Last Char -> " + temp_str);
        // check if the tokens[i] contains temp_str
        if (tokens[i].contains(temp_str)) {
            // remove the temp_str from the tokens[i]
            tokens[i] = tokens[i].replace(temp_str, "");
            if (tmp_array[i] == null) {
                tmp_array[i] = tokens[i];
                System.out.println("tmp_array[i] -> " + tokens[i]);
            }
        }
        tmp_array = iterate_array(tokens, tmp_array, i, temp_str);
        System.out.println("--tmp_array" + tmp_array[i]);
    }

    private static String[] iterate_array(String[] tokens, String[] tmp_array, int i, String temp_str) {
        while (temp_str.length() > 0) {
            tokens[i] = temp_str;
            temp_str = tokenized_extended(tokens, i, temp_str);
            System.out.println("\n2nd.Return temp_str -> " + temp_str);
            // check if the tokens[i] contains temp_str
            int j = 0;
            if (tokens[i].contains(temp_str)) {
                // remove the temp_str from the tokens[i]
                tokens[i] = tokens[i].replace(temp_str, "");
                System.out.println("\nSubset of token and temp_str -> " 
                    + tokens[i] + " \nTemp_array[i] -> " + tmp_array[i]);
                System.out.println("j tmp_array[j]");                        
                while (tmp_array[j] != null) {
                    System.out.println(j + " " + tmp_array[j]);
                    j++;
                }
                tmp_array[j] = tokens[i];
                System.out.println("Insert tmp_array[i] -> " + tmp_array[j]);
            }
        }
        return tmp_array;
    }

    private static String tokenized_extended(String[] tokens, int i, String temp_str) {
        System.out.print("Tokenize Ex -> ");
        String tmpst = "";
        tmpst = getTokenMethod(tokens, i, temp_str, tmpst);
        // reverse the tmpst
        String tmpst_rev = "";
        for (int j = tmpst.length() - 1; j >= 0; j--) {
            tmpst_rev += tmpst.charAt(j);
        }
        // add the tmpst_rev to the tokens[i]
        tokens[i] += tmpst_rev;
        System.out.print( "\n\ttmpst -> " + tmpst + " "
            + " \n\tReverse of tmpst -> " + tmpst_rev + " ");
        // return the tmpst_rev
        return tmpst_rev;
    }

    private static String getTokenMethod(String[] tokens, int i, String temp_str, String tmpst) {
        // while temp_str is null, remove the last character and save it to temp_arr.
        while (getTokenType(temp_str) == null) {
            // get the last character of temp_str
            String lastChar = temp_str.substring(temp_str.length() - 1);
            System.out.print("\n\ttemp_str -> "+ temp_str + " \n\tgetTokenType(" + temp_str +") == " 
            + getTokenType(temp_str) + " ");
            // print the last character
            System.out.print(" \n\ttemp_str[lastChar] -> " + lastChar);
            tmpst += lastChar;
            System.out.print(" \n\ttmptst -> " + tmpst + " ");
            temp_str = temp_str.substring(0, temp_str.length() - 1);
            System.out.print(" \n\ttemp_str[remaining] -> " + temp_str + " ");
            // if the returned value from getTokenType(temp_str) is not null then add
            // index value to new array.
            if (getTokenType(temp_str) != null) {
                tokens[i] = temp_str;
                System.out.print(" \n\ttemp_str[remaining] -> " 
                    + "getTokenType(" + temp_str +") == " + getTokenType(temp_str));
                System.out.print(" \n\ttemp_str[remaining] -> " + temp_str);
                break;
            }
        }
        return tmpst;
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
        //else if the token is equal to special characters then return special characters
        else if (token.equals("{") || token.equals("}") || token.equals("[")
                || token.equals("]") || token.equals("(") || token.equals(")")
                || token.equals(".") || token.equals(",")) {
            return "Special Character";
        }
        // else if the token is equal to another "[a-zA-Z_][a-zA-Z0-9_]*" then return
        // "identifier"
        else if (token.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
            // System.out.println("gettoken: " + token);
            return "Identifier";
        }
        // else if the token is equal to "[0-9]+" then return "Constant"
        else if (token.matches("[0-9]+")) {
            return "Constant";
        } else {
            return null;
        }
    }
}