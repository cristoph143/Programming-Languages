import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class syntax_analyzer {
    static List<String> GrammarString = new ArrayList<String>();
    static String[] header_list = { "var_dec", "data_type", "dec", "exp", "identifier" };
    static String[][] table_list;

    public static String[] syntax_analyzer(String[] tokens) {
        int idx = 0;
        // add the tokens[1] to the node if it exist in data_type[i]
        String tmp = "";
        if (!data_types(tokens, idx)) {
            System.out.println("\nSyntax\tType");
            isTerminator(tokens, tokens.length - 1);
        }
        tmp = identifier(tokens, idx);
        System.out.println("-----------------------------------------------------");
        idx += tmp.length();
        // int op=(op+);
        System.out.println("idx: " + idx + " tokens: " + tokens[idx] + " tmp: " +
        tmp);
        // get the substring of tokens from 0 to idx
        String[] sub_tokens = Arrays.copyOfRange(tokens, idx + 1, tokens.length);
        if(sub_tokens.length < 2){
            isTerminator(tokens, tokens.length - 1);
        }
        dec(sub_tokens, 0, tmp);
        return tokens;
    }

    private static void dec(String[] tokens, int idx, String tmp) {
        String tmp2 = tmp;
        String[] sub_tokens;
        System.out.println("dec: " + idx + " tokens: " + tokens[idx + 1] + " tmp: " + tmp);
        if (lexical_analyzer.getTokenType(tokens[idx]) == "Equals_op") {
            System.out.println("equals: " + idx + " tokens: " + tokens[idx] + " tmp: " + tmp);
            exp(tokens, idx+1);
        } else if (lexical_analyzer.getTokenType(tokens[idx]) == "Comma") {
            System.out.println("comma: " + idx + " tokens: " + tokens[idx] + " tmp: " + tmp);
            tmp = identifier(tokens, idx);
            idx += tmp.length();
            // int op=(op+);
            System.out.println("idx: " + idx + " tokens: " + tokens[idx] + " tmp: " + tmp);
            // get the substring of tokens from 0 to idx
            sub_tokens = Arrays.copyOfRange(tokens, idx+1, tokens.length);
            if(sub_tokens.length < 2){
                isTerminator(tokens, tokens.length - 1);
            }
            System.out.println("sub_tokens== " + Arrays.toString(sub_tokens));
            dec(sub_tokens, 0, tmp);
        }
        else if(lexical_analyzer.getTokenType(tokens[idx]) == "Operator"){
            System.out.println("Syntax error on token \""+ tokens[idx]+"\", invalid AssignmentOperator");
            System.exit(0);
        }
    }

    public static void isTerminator(String[] tokens, int idx) {
        if (lexical_analyzer.getTokenType(tokens[idx]) == "Terminator") {
            System.out.println("Syntax Accepted");
            System.exit(0);
        } else {
            System.out.println("Syntax error, insert \";\" to complete LocalVariableDeclarationStatement");
        }
        System.exit(0);
    }

    private static void exp(String[] tokens, int idx) {
        String tmp = "";
        String[] sub_tokens;
        System.out.println("tokens: " + Arrays.toString(tokens));
        if (tokens[idx].equals("(")) {
            exp_par(tokens, idx);
        }
        if (tokens[1].equals(")")) {
            System.out.println("Syntax error on token \"" + tokens[idx + 1] + "\", invalid Expression");
            System.exit(1);
        }
        if(lexical_analyzer.getTokenType(tokens[idx]) == "Identifier" 
        || lexical_analyzer.getTokenType(tokens[idx]) == "Constant"){
            System.out.println("identifier: " + idx + " tokens: " + tokens[idx] + " tmp:ss " + tmp);
            tmp = identifier(tokens, idx);
            idx += tmp.length();
            // if getTokenType(tokens[idx+1]) == "Operator"
            if (lexical_analyzer.getTokenType(tokens[2]) == "Operator") {
                System.out.println("operator: " + idx + " tokens: " + tokens[2] + " tmp: " + tmp);
                tmp = operator(tokens, 3);
                sub_tokens = Arrays.copyOfRange(tokens, tmp.length(), tokens.length);
                idx += tmp.length();
            }
            // int op=(op+);
            // get the substring of tokens from 0 to idx
            sub_tokens = Arrays.copyOfRange(tokens, tmp.length(), tokens.length);
            if(sub_tokens.length < 2){
                isTerminator(tokens, tokens.length - 1);
            }
            System.out.println("sub_tokens: ss" + Arrays.toString(sub_tokens) + sub_tokens.length);
            exp(sub_tokens, 0);
        }
    }

    private static String operator(String[] tokens, int idx) {
        // check if getTokenType(tokens[idx]) == "Identifier" or "Constant"
        String tmp = "";
        if (lexical_analyzer.getTokenType(tokens[idx]) == "Identifier" || 
        lexical_analyzer.getTokenType(tokens[idx]) == "Constant") {
            System.out.println("identifier_op: " + idx + " tokens: " + tokens[idx] + " tmp: " + tmp);
            tmp = identifier(tokens, idx);
            idx += tmp.length();
            String[] sub_tokens = Arrays.copyOfRange(tokens, tmp.length()+1, tokens.length);
            System.out.println("sub_tokens:" + Arrays.toString(sub_tokens) + sub_tokens.length+ tmp.length());
            System.out.println("sub_tokens: " + sub_tokens[1]);
            if(sub_tokens.length <= 2){
                isTerminator(sub_tokens, sub_tokens.length - 1);
            }
            else{
                if (lexical_analyzer.getTokenType(sub_tokens[1]) == "Operator") {
                    sub_tokens = Arrays.copyOfRange(tokens, tmp.length()+2, tokens.length);
                    System.out.println("sub_tokens:" + Arrays.toString(sub_tokens) + sub_tokens.length+ tmp.length());
                    System.out.println("sub_tokens: " + sub_tokens[1]);
                    if(sub_tokens.length == 2){
                        System.out.println("Syntax error on token \""+ sub_tokens[0]+"\", expected Identifier or Constant");
                        System.exit(0);
                    }
                    System.out.println("operator_op: " + idx + " tokens: " + sub_tokens + " tmp: " + tmp);
                    tmp = operator(sub_tokens, idx);
                    idx += tmp.length();
                }
            }
        }
        return tmp;
    }

    private static void exp_par(String[] tokens, int idx) {
        String tmp;
        String[] sub_tokens;
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
        tmp = forming_identifier(tokens, idx, tmp);
        System.out.println("form_ident: " + idx + " tokens:-- " + tokens[idx] + " tmp: " + tmp);
        return tmp;
    }

    private static String forming_identifier(String[] tokens, int idx, String tmp) {
        String tmp2 = tmp;
        System.out.println("form_ident2: " + idx + " tokens:?--> " + tokens.length + " tmp: " + tmp2);
        if (lexical_analyzer.getTokenType(tokens[idx]) == "Identifier") {
            while (lexical_analyzer.getTokenType(tokens[idx]) == "Identifier" ||
                    lexical_analyzer.getTokenType(tokens[idx]) == "Constant") {
                tmp2 += tokens[idx];
                idx++;
            }
            System.out.println("tm: " + tmp2);
        }
        return tmp2;
    }

    private static boolean data_types(String[] tokens, int idx) {
        String[] data_type = { "boolean", "float", "int", "String", "byte", "long", "short" };
        boolean is_data_type = Arrays.asList(data_type).contains(tokens[idx]);
        String tmp = forming_identifier(tokens, idx, "");
        
        // get the length of tmp
        int len = tmp.length();
        // split the tokens from len to the token length-1
        String[] sub_tokens = Arrays.copyOfRange(tokens, len, tokens.length);
        System.out.println("sub_tokens: " + Arrays.toString(sub_tokens));
        // 
        // if tokens[i] is Data_Type
        if (is_data_type) {
            return true;
        }
        System.out.println("is_data_type: " + is_data_type + " tokens: " + tokens[idx]);
        // check if the first token is "Identifier"

        is_data_type = Arrays.asList(data_type).contains(tmp);
        System.out.println("is_data_type: " + is_data_type + " tokens:1 " + tmp);
        if (!is_data_type) {
            System.out.println("is_data_type: " + is_data_type + " tokens: " + tmp);
            System.out.println("Error: " + tmp + " is not a data type");
            System.exit(1);
        }
        // get the length of tmp
        len = tmp.length();
        // split the tokens from len to the token length-1
        sub_tokens = Arrays.copyOfRange(tokens, len, tokens.length);
        System.out.println("sub_tokens: " + Arrays.toString(sub_tokens));
        // if length of sub_tokens is less than 2 then is_data_type = false
        if (sub_tokens.length < 3) {
            System.out.println("Error: " + sub_tokens[0] + " expected \"Identifier\"");
            is_data_type = false;
        }
        return is_data_type;
    }
}