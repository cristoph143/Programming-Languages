import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class syntax_analyzer{
    static List<String> GrammarString = new ArrayList<String>();
    static String[] header_list = {"var_dec", "data_type", "dec", "exp", "identifier"};
    static String[][] table_list;
    // TODO:
    // 1. Add the grammar rules to the GrammarString
    // 2. create a parse tree
    // 3. print the parse tree in a tree format with indentation
    // 4. check the parse tree if it is valid grammar from GrammarString
    // return the syntax_analyzer
    public static String[] syntax_analyzer(String[] tokens) {
        read_grammar_file();
        table_list = new String[GrammarString.size()][2];
        for (int i = 0; i < GrammarString.size(); i++) {
            String[] temp = GrammarString.get(i).split(":");
            table_list[i][0] = temp[0];
            table_list[i][1] = temp[1];
        }
        // if check_grammar(tokens) == false, then return tokens
        if (check_grammar(tokens) == false) {
            return tokens;
        }
        parseTree(tokens,0);

        // int 8 = ( ;
        // print the table
        System.out.println("\nTable");
        for (int i = 0; i < table_list.length; i++) {
            for (int j = 0; j < table_list[i].length; j++) {
                System.out.print(table_list[i][j] + "\t\t");
            }
            System.out.println();
        }
        System.out.println("\n");
        parseTree(tokens,0);
        return tokens;
    }

    // Read the grammar file and store each line as a List of Strings
    public static void read_grammar_file() {
        // read the grammar.txt file until eof 
        try {
            Scanner scan = new Scanner(new java.io.File("grammar.txt"));
            while (scan.hasNextLine()) {
                GrammarString.add(scan.nextLine());
            }
            // print the grammar
            System.out.println("\nGrammar\tType");
            for (int i = 0; i < GrammarString.size(); i++) {
                System.out.println(GrammarString.get(i) + "\t" + lexical_analyzer.getTokenType(GrammarString.get(i)));
            }
            scan.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    
    // parseTree
    public static void parseTree(String[] tokens, int idx) {
        String[] var_dec = table_list[0][1].split("\s");
        String[] data_type = split_by_or(1, "Data_type");
        String[] dec = split_by_or(2, "Dec");
        String[] dec2 = dec[0].split(" ");
        String[] exp = split_by_or(3, "Exp");
        String[] ops_exp = ops_exp();
        // split GrammarString[0] by ":"
        String[] temp = GrammarString.get(0).split(":");
        String[] temp2 = temp[1].split("\s");
        String parse;
        String[] parse_gram = new String[temp2.length];
        // print temp
        parse = temp[0] + " ::= " + temp[1];
        parse_gram[0] = parse;
        System.out.println("\n" + parse);
        parse = "";
        // if tokens[0] is in data_type
        if (check_in_list(tokens[0], data_type)) {
            parse = temp[0] + " ::= " + tokens[0] + " " + var_dec[1] + " " + var_dec[2];
            parse_gram[1] = parse;
            System.out.println(parse);
            parse = "";
            // if getTokenType(tokens[1]) == "Identifier"
            if (lexical_analyzer.getTokenType(tokens[idx+1]) == "Identifier") {
                if (lexical_analyzer.getTokenType(tokens[idx+2]) == "Operator" || 
                    lexical_analyzer.getTokenType(tokens[idx+2]) == "Equals_op") {
                    System.out.println(temp[0] + " ::= " + tokens[idx] + " " + dec[0] + " " + var_dec[2]);
                    // if getTokenType(tokens[2]) == "Operator"
                    if (lexical_analyzer.getTokenType(tokens[idx+2]) == "Operator") {
                        operator_parsing(tokens);
                    }
                }
                // if getTokenType(tokens[2]) == "Comma"
                if (lexical_analyzer.getTokenType(tokens[2]) == "Comma") {
                    System.out.println(temp[0] + " ::= " + tokens[idx] + " " + tokens[idx+1] + " " + tokens[idx+2] + " " + var_dec[1] + " " + var_dec[2]);
                }
            }
        }
        // iterate parse_gram
        for (int i = 0; i < parse_gram.length; i++) {
            if (parse_gram[i] != null) {
                System.out.println(parse_gram[i] + "kkk");
            }
        }
    }

    private static void operator_parsing(String[] tokens) {
        // get the previous token until the last-1 token
        String[] temp_tokens = new String[tokens.length-1];
        for (int i = 1; i < tokens.length-1; i++) {
            temp_tokens[i] = tokens[i];
            // print temp_tokens
            System.out.print(temp_tokens[i] + " ");
        }
        System.out.println();
    }

    // use ast parse tree for parsing operation


    public static String[] ops_exp(){
        String[] temp = table_list[3][1].split("\s");
        return temp;
    }

    private static boolean check_in_list(String string, String[] data_type) {
        for (int i = 0; i < data_type.length; i++) {
            if (string.equals(data_type[i])) {
                return true;
            }
        }
        return false;
    }

    public static String[] split_by_or(int inx, String str){
        System.out.println(table_list[inx][1]+"\n");
        // split table_list[1][1] by " | "
        String[] temp = table_list[inx][1].split("\s\\|\s");
        // print the temp
        System.out.println("\n"+ str);
        for (int i = 0; i < temp.length; i++) {
            System.out.println(temp[i] + "\t" + lexical_analyzer.getTokenType(temp[i]));
        }
        return temp;
    }

    // choose the right grammar rule
    public static void grammar_choice(String[] tokens) {
        
        // return true;
    }


    // check if the parse tree is valid grammar
    public static boolean check_grammar(String[] tokens) {
        // print the first index of GrammarString
        System.out.println(GrammarString.get(0));
        // check if getTokenType(tokens[0]) is not "Data_Type"
        if (!lexical_analyzer.getTokenType(tokens[0]).equals("Data_Type")) {
            System.out.println("Syntax error, insert \"VariableDeclarators\" to complete LocalVariableDeclaration");
            return false;
        }
        // if getTokenType(tokens[length-1]) is not "Terminator"
        if (!lexical_analyzer.getTokenType(tokens[tokens.length-1]).equals("Terminator")) {
            System.out.println("Syntax error, insert \";\" to complete LocalVariableDeclarationStatement");
            return false;
        }
        // if getTokenType(tokens[2]) is  not "Identifier" and the getTokenType(tokens[3]) is "Equals_op"
        if (!lexical_analyzer.getTokenType(tokens[2]).equals("Identifier") 
            && lexical_analyzer.getTokenType(tokens[3]).equals("Equals_op")) {
            System.out.println("Syntax error, insert \"Identifier\" to complete VariableDeclarator");
            System.out.println("The left-hand side of an assignment must be a variable");
            return false;
        }
        // check if tokens[i] is = "(" or "\"" or "\'"
        for (int i = 0; i < tokens.length; i++) {
            // if getTokenType(tokens[i]) is operator
            if (tokens[i].equals("=")) {
                // check if the previous token and the next token are not identifier or constant
                if (!lexical_analyzer.getTokenType(tokens[i-1]).equals("Identifier") 
                    || lexical_analyzer.getTokenType(tokens[i-1]).equals("Constant")) {
                    System.out.println("Syntax error on token "+ tokens[i] +", Expression expected before this token");
                    return false;
                }
                if(!lexical_analyzer.getTokenType(tokens[i+1]).equals("Identifier")
                    && !lexical_analyzer.getTokenType(tokens[i+1]).equals("Constant")){
                    System.out.println("Syntax error on token "+ tokens[i] +", Expression expected after this token");
                    return false;
                }
            }
            // if tokens[i] is ","
            if (tokens[i].equals(",")) {
                // check if the previous token is identifier
                if (lexical_analyzer.getTokenType(tokens[i-1]).equals("Identifier")) {
                    // check if the next token is not identifier
                    if (!lexical_analyzer.getTokenType(tokens[i+1]).equals("Identifier")) {
                        System.out.println("Syntax error on token \""+ tokens[i] +"\" , Identifier expected after this token");
                        return false;
                    }
                }
                // check if the previous token is constant
                if (lexical_analyzer.getTokenType(tokens[i-1]).equals("Constant") ||
                    !lexical_analyzer.getTokenType(tokens[i-1]).equals("Identifier")) {
                    System.out.println("Syntax error on token \""+ tokens[i] +"\" , Identifier expected before this token");
                    return false;
                }
            }
            if (tokens[i].equals("(") || tokens[i].equals("\"") || tokens[i].equals("\'")) {
                // if previous token is data_type
                if (lexical_analyzer.getTokenType(tokens[i-1]).equals("Data_Type")) {
                    System.out.println("Syntax error on token "+ tokens[i] +", AnnotationName expected after this token");
                    return false;
                }
                // if the the token[i] is "(" and the next token is not identifier or constant
                if (tokens[i].equals("(") && !lexical_analyzer.getTokenType(tokens[i+1]).equals("Identifier")
                    && !lexical_analyzer.getTokenType(tokens[i+1]).equals("Constant")) {
                    System.out.println("Syntax error on token \""+ tokens[i] +"\" , Identifier expected after this token");
                    return false;
                }
                // FIXME: 
                // if the the token[i] is "(" heck if ")" exist
                // if the the token[i] is "\"" heck if "\"" exist
                // if the the token[i] is "\'" heck if "\'" exist
            }
            // if getTokenType(tokens[i]) is "Operator"
            if (lexical_analyzer.getTokenType(tokens[i]).equals("Operator")) {
                // check if the previous token is not identifier or constant
                if (!lexical_analyzer.getTokenType(tokens[i-1]).equals("Identifier") 
                    || lexical_analyzer.getTokenType(tokens[i-1]).equals("Constant")) {
                    System.out.println("Syntax error on token "+ tokens[i] +", Expression expected before this token");
                    return false;
                }
                // check if the next token is not identifier or constant
                if (!lexical_analyzer.getTokenType(tokens[i+1]).equals("Identifier") 
                    || lexical_analyzer.getTokenType(tokens[i+1]).equals("Constant")) {
                    System.out.println("Syntax error on token "+ tokens[i] +", Expression expected after this token");
                    return false;
                }
            }
        }
        return true;
    }
}