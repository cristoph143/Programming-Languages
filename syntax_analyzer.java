import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;

import org.json.simple.JSONObject;

public class syntax_analyzer {
    static List<String> GrammarString = new ArrayList<String>();
    static String[] header_list = { "var_dec", "data_type", "dec", "exp", "identifier" };
    static String[][] table_list;

    static HashMap<String, String> map = new HashMap<String, String>();

    public static String[] syntax_analyzer(String[] tokens) {
        int idx = 0;
        String[] data_type = { "boolean", "float", "int", "String", "byte", "long", "short" };
        // add the tokens[1] to the node if it exist in data_type[i]
        data_types(tokens, idx, data_type);
        String tmp = identifier(tokens, idx);
        idx += tmp.length();
        // int op=(op+);
        System.out.println("idx: " + idx + " tokens: " + tokens[idx] + " tmp: " + tmp);
        // get the substring of tokens from 0 to idx
        String[] sub_tokens = Arrays.copyOfRange(tokens, idx + 1, tokens.length);
        System.out.println("sub_tokens: " + Arrays.toString(sub_tokens));
        dec(sub_tokens, 0, tmp);
        return tokens;
    }

    private static void dec(String[] tokens, int idx, String tmp) {
        String tmp2 = tmp;
        String[] sub_tokens;
        System.out.println(">idxjj: " + idx + " tokens: " + tokens[idx + 1] + " tmp: " + tmp);
        if (lexical_analyzer.getTokenType(tokens[idx]) == "Equals_op") {
            System.out.println(">idxx: " + idx + " tokens: " + tokens[idx] + " tmp: " + tmp);
            exp(tokens, idx);
        } else if (lexical_analyzer.getTokenType(tokens[idx]) == "Comma") {
            System.out.println(">idx: " + idx + " tokens: " + tokens[idx] + " tmp: " + tmp);
            tmp = identifier(tokens, idx);
            idx += tmp.length();
            // int op=(op+);
            System.out.println("idx: " + idx + " tokens: " + tokens[idx] + " tmp: " + tmp);
            // get the substring of tokens from 0 to idx
            sub_tokens = Arrays.copyOfRange(tokens, idx + 1, tokens.length);
            System.out.println("sub_tokens: " + Arrays.toString(sub_tokens));
            dec(sub_tokens, 0, tmp);
        } else if (lexical_analyzer.getTokenType(tokens[idx]) == "Terminator") {
            System.out.println("Syntax Accepted");
        }
    }

    private static void exp(String[] tokens, int idx) {
        String tmp;
        String[] sub_tokens;
        if (tokens[idx + 1].equals("(")) {
            exp_par(tokens, idx);
        }
        if (tokens[1].equals(")")) {
            System.out.println("Syntax error on token \"" + tokens[idx + 1] + "\", invalid Expression");
            System.exit(1);
        }
    }

    private static void exp_par(String[] tokens, int idx) {
        String tmp;
        String[] sub_tokens;
        // if next token is (

        // get the substring of tokens from 0 to idx
        sub_tokens = Arrays.copyOfRange(tokens, idx + 1, tokens.length);
        System.out.println("sub_tokensassas: " + Arrays.toString(sub_tokens));
        // append sub_tokens to tmp_str
        String tmp_str = "";
        // iterate over sub_tokens and append to tmp_str
        for (int i = 0; i < sub_tokens.length; i++) {
            tmp_str += sub_tokens[i];
        }
        System.out.println("tmp_str: " + tmp_str);
        tmp = tokens[idx];
        isBalancedPar(tmp_str);
        // get the substring of tokens from 0 to idx
        sub_tokens = Arrays.copyOfRange(tokens, idx + 1, tokens.length);
        System.out.println("sub_tokens: " + Arrays.toString(sub_tokens));
        System.out.println("idx:s " + idx + " tokens: " + tokens[idx] + " tmp: " + tmp);
    }

    public static void isBalancedPar(String str) {
        int count = 0, idx = 0;
        int first, second = 0;
        int[] arr = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                arr[i] = 0;
                count++;
            } else if (str.charAt(i) == ')') {
                arr[i] = 1;
                count--;
            } else {
                // check if str.charAt(i) is constant or identifier
                if (isIdentifier_or_constant(str.charAt(i))) {
                    arr[i] = 2;
                } else {
                    arr[i] = 3;
                }
            }
        }
        // print arr
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        for (int i = 0; i < arr.length; i++) {
            // check if arr[i] is 0 or 1
            if (arr[i] == 0 || arr[i] == 1) {
                if (arr[i] == 0) {
                    // check if next arr[i] is not 2 or 0
                    System.out.println("sdsdsdsds");
                    if (arr[i + 1] != 2 && arr[i + 1] != 0) {
                        System.out.println("Syntax error on token sd\"" + str.charAt(i)
                                + "\", identifier or constant or \"(\" after this token");
                        System.exit(1);
                    }
                }
                if (arr[i] == 1) {
                    // check if next arr[i] is not 2 or 0
                    System.out.println("sdsdsdsds");
                    if (arr[i - 1] != 2 && arr[i - 1] != 1) {
                        System.out.println("Syntax error on token s\"" + str.charAt(i)
                                + "\", identifier or constant or \")\" before this token");
                        System.exit(1);
                    }
                }
            }
        }
        if (count != 0) {
            System.out.println("Syntax error, insert \")\", to complete Expression");
            System.exit(1);
        }
    }

    public static boolean isIdentifier_or_constant(char c) {
        if (Character.isLetter(c) || Character.isDigit(c)) {
            return true;
        }
        return false;
    }

    private static String identifier(String[] tokens, int idx) {
        String tmp = "";
        // check if getTokenType(tokens[1]) is "Identifier"
        if (lexical_analyzer.getTokenType(tokens[1]) != "Identifier") {
            System.out.println("Error: " + tokens[idx + 1] + " is not an identifier");
            System.exit(0);
        }
        tmp += tokens[idx + 1];
        for (int i = 2; i < tokens.length;) {
            if (lexical_analyzer.getTokenType(tokens[i]) == "Identifier"
                    || lexical_analyzer.getTokenType(tokens[i]) == "Constant") {
                System.out.println("i: " + i + " tokens: " + tokens[i] + " tmp: " + tmp);
                tmp += tokens[i];
                i++;
            } else {
                break;
            }
        }
        System.out.println("idx: " + idx + " tokens: " + tokens[idx] + " tmp: " + tmp);
        return tmp;
    }

    private static void data_types(String[] tokens, int idx, String[] data_type) {
        for (int i = 0; i < data_type.length; i++) {
            if (tokens[idx].equals(data_type[i])) {
                idx++;
                break;
            }
            // else throw error
            else if (i == data_type.length - 1) {
                System.out.println("Error: " + tokens[idx] + " is not a valid data type");
                System.exit(0);
            }
        }
    }

    /* ---------- Return the parse tree structure for the given grammar --------- */
    public static String[] ops_exp() {
        String[] temp = table_list[3][1].split("\s");
        return temp;
    }

    public static String[] split_by_or(int inx, String str) {
        String[] temp = table_list[inx][1].split("\s\\|\s");
        return temp;
    }
}