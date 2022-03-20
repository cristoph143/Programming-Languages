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
            // store the last char of the lexemes and remove it to the original lexemes
            // String lastChar = lexemes.substring(lexemes.length() - 1);
            // lexemes = lexemes.substring(0, lexemes.length() - 1);

            // call tokenize(lexemes) to tokenize the source code and save it as a variable
            String[] tokens = tokenize(lexemes);
            // print the tokens
            System.out.println("Token\tType");
            for (int i = 0; i < tokens.length; i++) {
                System.out.println(tokens[i] + "\t" + getTokenType(tokens[i]));
            }
            // System.out.println(lastChar + "\t" + getTokenType(lastChar));
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
        String[] tokens = lexemes.split(" ");
        String[] tmp_array = new String[lexemes.length()];
        // String[] newTokens = new String[tokens.length + 2];
        // iterate the tokens
        for (int i = 0; i < tokens.length; i++) {
            // if the returned value from getTokenType(tokens[i]) is not null then add
            // index value to new array.
            if (getTokenType(tokens[i]) != null) {
                tokens[i] = tokens[i];
                // add tokens[i] to tmp_a;
                tmp_array[i] = tokens[i];
                System.out.println(tokens[i] + "\t" + getTokenType(tokens[i]) + " " + tmp_array[i]);
            }

            // else if the returned value is null then add the last character of the token
            // to new array
            // and remove it to the tokens.
            else {
                String temp_str = tokens[i];
                temp_str = tokenized_extended(tokens, i, temp_str);
                System.out.println("\n02.  " + temp_str);
                // check if the tokens[i] contains temp_str
                if (tokens[i].contains(temp_str)) {
                    // remove the temp_str from the tokens[i]
                    tokens[i] = tokens[i].replace(temp_str, "");
                    if (tmp_array[i] == null) {
                        tmp_array[i] = tokens[i];
                        System.out.println("\n0?3.  " + tokens[i] + " " + tmp_array[i] + " i " + i);
                    }
                }
                // tokens[i] = temp_str;
                while (temp_str.length() > 0) {
                    tokens[i] = temp_str;
                    temp_str = tokenized_extended(tokens, i, temp_str);
                    System.out.println("\n03.  " + temp_str + "\n" + tokens[i]);
                    // check if the tokens[i] contains temp_str
                    if (tokens[i].contains(temp_str)) {
                        // remove the temp_str from the tokens[i]
                        tokens[i] = tokens[i].replace(temp_str, "");
                        System.out.println("\n0004.  " + tokens[i] + " " + tmp_array[i] + " i " + i);
                        int j = 0;
                        while (tmp_array[j] != null) {
                            System.out.println(j + " " + tmp_array[j]);
                            j++;
                        }
                        tmp_array[j] = tokens[i];
                        System.out.println("\n0006." + tmp_array[j]);
                    }
                }
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
        System.out.println();
        return tmp_array;
    }

    private static String tokenized_extended(String[] tokens, int i, String temp_str) {
        System.out.print("2.  " + temp_str + " getTokenType " + getTokenType(temp_str) + " ");
        String tmpst = "";
        // while temp_str is null, remove the last character and save it to temp_arr.
        while (getTokenType(temp_str) == null) {
            // get the last character of temp_str
            String lastChar = temp_str.substring(temp_str.length() - 1);
            // print the last character
            System.out.print("3. -- " + lastChar);
            tmpst += lastChar;
            System.out.print(" 5.  " + tmpst + " ");
            temp_str = temp_str.substring(0, temp_str.length() - 1);
            System.out.print(" 3.  " + temp_str + " ");
            // if the returned value from getTokenType(temp_str) is not null then add
            // index value to new array.
            if (getTokenType(temp_str) != null) {
                tokens[i] = temp_str;
                System.out.print(" 4.  " + temp_str + " ");
                break;
                // return tokens[i];
            }
        }
        // tokens[i] = tmpst;
        // System.out.print(" 9. "+tokens[i] + " ");
        // reverse the tmpst
        String tmpst_rev = "";
        for (int j = tmpst.length() - 1; j >= 0; j--) {
            tmpst_rev += tmpst.charAt(j);
        }
        // add the tmpst_rev to the tokens[i]
        tokens[i] += tmpst_rev;
        System.out.print(" 6.  " + tokens[i] + " ");
        // return the tmpst_rev
        return tmpst_rev;

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