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
        parseTree(tokens);
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
    public static void parseTree(String[] tokens) {
        // iterate tokens
        String[] parse_gram = new String[20];; 
        // get the first index of GrammarString and print it
        String line = GrammarString.get(0);
        String []tmp = new String[tokens.length];
        // add line to index of tmp
        parse_gram[0] = line;
        // print tmp
        System.out.println(parse_gram[0]);
        int index = 0;
        // split table_list[0][1]
        String[] temp = table_list[0][1].split(" ");
        System.out.println(table_list[1][0] + "<--\t-->" + table_list[1][1]);
        // Data_Type
        // check if temp[0] == table_list[i][0]
        if (temp[0].equals(table_list[1][0])) {
            // check if the table_list[i][1] has values in tokens[1]
            if (table_list[1][1].contains(tokens[index])) {
                // if it has values, then add it to parse_gram
                parse_gram[index+1] = "var_dec: "+tokens[index]+" dec;";
                // print the parse_gram
                System.out.println(parse_gram[index]);
            }
        }
        // Dec
        dec_loop(tokens, parse_gram, index, temp);
        // if parse_gram[4] contains ", dec"
        if (parse_gram[4].contains(", dec")) {
            index = 3;
            dec_loop(tokens, parse_gram, index, temp);
        }
        // if parse_gram[4] contains "[=exp]"
        if (parse_gram[4].contains("[=exp]")) {
            // print table_list[3][1]
            System.out.println(table_list[3][0] + "<--=\t=-->" + table_list[3][1]);
            // check if the table_list[i][1] has values in tokens[1]
            if (table_list[1][1].contains(tokens[index])) {
                // split table_list[2][1] by "\s\\|\s"
                String[] temp2 = table_list[3][1].split("\\s\\|\\s");
                // if remaining tokens has + or - or * or /
            }
        }
        // iterate parse_gram
        for (int i = 0; i < parse_gram.length; i++) {
            // print the parse_gram
            System.out.println(parse_gram[i] + "\tdd");
        }
    }

    private static void dec_loop(String[] tokens, String[] parse_gram, int index, String[] temp) {
        int tok_id=0;
        // check if temp[0] == table_list[2][0]
        if (temp[1].equals(table_list[2][0])) {
            // check if getTokenType(tokens[1]) is identifier
            if (lexical_analyzer.getTokenType(tokens[tok_id+1]).equals("Identifier")) {
                // split table_list[2][1] by "\s\\|\s"
                String[] temp2 = table_list[2][1].split("\\s\\|\\s");
                // check if getTokenType(tokens[2]) is Equals_op
                if (lexical_analyzer.getTokenType(tokens[tok_id+2]).equals("Equals_op")) {
                    // print temp2
                    System.out.println(temp2[0] + " temp2");
                    parse_gram[index+2] = "var_dec: "+tokens[tok_id]+" "+temp2[0]+" ;";
                    // split the temp2[0] by " "
                    String[] temp3 = temp2[0].split(" ");
                    
                    System.out.println(table_list[4][1] + " temp-");
                    parse_gram[index+3] = "var_dec: "+tokens[tok_id]+" "+table_list[4][1]+" ;";
                    parse_gram[index+4] = "var_dec: "+tokens[tok_id]+" "+tokens[1]+ " "+ temp3[1]+" ;";
                }
                // check if getTokenType(tokens[2]) is "Comma"
                if (lexical_analyzer.getTokenType(tokens[tok_id+2]).equals("Comma")) {
                    // print temp2
                    System.out.println(temp2[1]);
                    parse_gram[index+2] = "var_dec: "+tokens[tok_id]+" "+temp2[1]+" ;";
                    // split the temp2[0] by " "
                    String[] temp3 = temp2[1].split(" ");
                    
                    System.out.println(table_list[4][1] + " temp-");
                    parse_gram[index+3] = "var_dec: "+tokens[tok_id]+" "+table_list[4][1]+" "+temp2[1]+" ;";
                    parse_gram[index+4] = "var_dec: "+tokens[tok_id]+" "+tokens[1]+ ", "+ temp3[1]+" ;";
                }
            }
        }
    }
    

    // print parse tree
    public static void print_parse_tree(String[] tokens) {


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