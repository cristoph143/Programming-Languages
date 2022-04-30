import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class syntax_analyzer{
    static List<String> GrammarString = new ArrayList<String>();
    static String[] header_list = {"var_dec", "data_type", "dec", "exp", "identifier"};

    // return the syntax_analyzer
    public static String[] syntax_analyzer(String[] tokens) {
        // create a 2d array
        // String[][] table = new String[tokens.length][2];
        read_grammar_file();
        // conv_grammar_to_table(table);
        // get the value of table[0][1]
        // String value = table[0][1];
        // print value
        // System.out.println(value);
        // split the value into tokens
        // String[] value_tokens = value.split(" ");
        // // check if the getTokenType(tokens[0]) is "Data Type"
        // if (!lexical_analyzer.getTokenType(tokens[0]).equals("Data Type")) {
        //     // print me
        //     System.out.println("Error: Data Type expected");
        // }

            
        return tokens;
    }

    private static void conv_grammar_to_table(String[][] table) {
        String[] grammar;
        // iterate header_list and send each value to readGrammar
        for (int i = 0; i < header_list.length; i++) {
            System.out.println("\n" + header_list[i] + "\tGrammar\t" + GrammarString.get(i));
            // split grammar by "|" and send each value to readGrammar
            grammar = GrammarString.get(i).split("\\:");
            // print the grammar
            for (int j = 0; j < grammar.length; j++) {
                System.out.println(grammar[j] + "\t--> " + lexical_analyzer.getTokenType(grammar[j]));
            }
            table[i][0] = grammar[0];
            table[i][1] = grammar[1];
        }
        // print the table
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j] + " ==>");
            }
            System.out.println();
        }
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

    // funtion that contructs parse tree from the table and the tokens

}