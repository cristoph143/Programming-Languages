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
    // return the tokenized form of the lexemes by tokenizing the space and ";"
    public static String[] tokenize(String lexemes) {
        // tokenize the lexeme by one or multiple space.
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
            // if tmp_array is null, trim the array from the first null to end
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
        String tmp_strs = "";
        String tmp_id = "";
        // scan the temp_str from the first character to the last character
        for (int j = 0; j < temp_str.length(); j++) {
            tmp_strs = temp_str.substring(j, j + 1);
            // print tmp_strs
            System.out.println(tmp_strs + "\t1?" + getTokenType(tmp_strs));
            // if tmps_strs is alpha or digit then check until index value of tmps_strs is
            // alpha or digit

            // print tmp_strs
            System.out.println(tmp_strs + "\t1111" + getTokenType(tmp_strs));
            // if tmp_strs is = "+" or "-" or "&" or "|" or "=" or ">" or "<" or
            // "!" or "*" or "/" or "%" then check the next index if it is
            // the same character mention above then add the two characters to
            // tmp_strs
            if (tmp_strs.equals("+") || tmp_strs.equals("-") || tmp_strs.equals("&")
                    || tmp_strs.equals("|") || tmp_strs.equals("=") || tmp_strs.equals(">")
                    || tmp_strs.equals("<") || tmp_strs.equals("!") || tmp_strs.equals("*")
                    || tmp_strs.equals("/") || tmp_strs.equals("%")) {
                tmp_strs = temp_str.substring(j, j + 2);
                System.out.println(tmp_strs + "\t1" + getTokenType(tmp_strs));
                // if tmp_strs is "++" or "--" or "&&" or "||" or "==" or "!=" or
                // ">=" or "<=" or "!=" or "*=" or "/=" or "%=" then add the two characters
                // to tmp_strs
                if (tmp_strs.equals("++") || tmp_strs.equals("--") || tmp_strs.equals("&&")
                        || tmp_strs.equals("||") || tmp_strs.equals("==") || tmp_strs.equals("!=")
                        || tmp_strs.equals(">=") || tmp_strs.equals("<=") || tmp_strs.equals("!=")
                        || tmp_strs.equals("*=") || tmp_strs.equals("/=") || tmp_strs.equals("%=")
                        || tmp_strs.equals("+=")) {
                    tmp_strs = temp_str.substring(j, j + 2);
                    System.out.println(tmp_strs + "\t2" + getTokenType(tmp_strs));
                    j++;
                }
                // if tmp_strs is null
                else if ((getTokenType(tmp_strs) == null)) {
                    tmp_strs = temp_str.substring(j, j + 1);
                }
            }
            System.out.println(tmp_strs + "\t" + getTokenType(tmp_strs));
            // while tmp_array is not null, increment the index value of tmp_array
            while (tmp_array[i] != null) {
                i++;
            }
            // add the tmp_strs to tmp_array
            tmp_array[i] = tmp_strs;
        }
    }

    public static String[] final_tokens(String[] tokens) {
        String[] final_tokens = new String[tokens.length];
        String finals = "";
        int j = 0;
        // print the tokens
        for (int i = 0; i < tokens.length; i++) {
            // check if the index is "Identifier" from getTokenType
            if (getTokenType(tokens[i]) == "Identifier") {
                // check until the index is not "Identifier" or "Constant"
                while (getTokenType(tokens[i]) == "Identifier" ||
                        getTokenType(tokens[i]) == "Constant") {
                    finals = tokens[i] + finals;
                    i++;
                }
                // reverse the string
                finals = new StringBuffer(finals).reverse().toString();
                // increment idx if final_tokens[idx] is not null
                while (final_tokens[j] != null) {
                    j++;
                }

                // add it to final_tokens
                final_tokens[j] = finals;
                finals = "";
                // print the final string
                System.out.println(final_tokens[j] + "\tjh" + getTokenType(final_tokens[j]));
            }
            if (getTokenType(tokens[i]) == "Constant") {
                // check until the index is not "Identifier" or "Constant"
                while (getTokenType(tokens[i]) == "Constant") {
                    finals = tokens[i] + finals;
                    i++;
                }
                // reverse the string
                finals = new StringBuffer(finals).reverse().toString();
                while (final_tokens[j] != null) {
                    j++;
                }
                // add it to final_tokens
                final_tokens[j] = finals;
                finals = "";
                // print the final string
                System.out.println(final_tokens[j] + "\t-->" + getTokenType(final_tokens[j]));
            }
            while (final_tokens[j] != null) {
                j++;
            }
            // add tokens[i] to final_tokens
            final_tokens[j] = tokens[i];
            // print the final string
            System.out.println(final_tokens[j] + "\tjaa" + getTokenType(final_tokens[j]));
        }
        // iterate final_tokens
        for (int i = 0; i < final_tokens.length; i++) {
            // if final_tokens is null then trim it
            if (final_tokens[i] == null) {
                String[] new_array = new String[i];
                for (int k = 0; k < i; k++) {
                    new_array[k] = final_tokens[k];
                }
                return new_array;
            }
        }
        return final_tokens;
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
        } else if (token.equals("+=") || token.equals("-=") || token.equals("*=")
                || token.equals("/=") || token.equals("%=") || token.equals("^=")) {
            return "Assignment_op";
        } else if (token.equals("++") || token.equals("--")) {
            return "Increment_op";
        } else if (token.equals("+") || token.equals("-") || token.equals("*")
                || token.equals("/") || token.equals("%") || token.equals("^")) {
            return "Operator";
        } else if (token.equals("=") || token.equals("==") || token.equals("!=")
                || token.equals("<") || token.equals(">") || token.equals("<=")
                || token.equals(">=")) {
            return "Comparison_op";
        } else if (token.equals("\"") || token.equals("'")) {
            return "Quotation Mark";
        } else if (token.equals("&&") || token.equals("||")
                || token.equals("!")) {
            return "Logical_op";
        } else if (token.equals("(")) {
            return "Left_parenthesis";
        } else if (token.equals(")")) {
            return "Right_parenthesis";
        } else if (token.equals("{")) {
            return "Left_brace";
        } else if (token.equals("}")) {
            return "Right_brace";
        } else if (token.equals("[")) {
            return "Left_bracket";
        } else if (token.equals("]")) {
            return "Right_bracket";
        } else if (token.equals(";")) {
            return "Terminator";
        } else if (token.equals(".")) {
            return "Dot";
        } else if (token.equals(",")) {
            return "Comma";
        }
        // else if the token is equal to another "[a-zA-Z_][a-zA-Z0-9_]*" then return
        // "identifier"
        else if (token.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
            // System.out.println("gettoken: " + token);
            return "Identifier";
        }
        // else if the token is equal to "[0-9]*" then return "Constant"
        else if (token.matches("[0-9]*")) {
            return "Constant";
        } else {
            return null;
        }
    }
}