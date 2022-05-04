// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;

// public class copy{
//     static List<String> GrammarString = new ArrayList<String>();
//     static String[] header_list = {"var_dec", "data_type", "dec", "exp", "identifier"};
//     static String[][] table_list;

//     /* -------------------------------------------------------------------------- */
//     /*             initialize a node with left, right, and center node            */
//     /* -------------------------------------------------------------------------- */
//     static class Node{
//         String left;
//         String right;
//         String center;
//         Node(String left, String center, String right){
//             this.left = left;
//             this.center = center;
//             this.right = right;
//         }

//         /* -------------------- Check if the node is a leaf node -------------------- */
//         boolean isLeaf(){
//             return (this.left == null && this.right == null);
//         }

//         /* ------------------- Check if the node is a parent node ------------------- */
//         boolean isParent(){
//             return (this.left != null && this.right != null);
//         }

//         /* ----------- Check if the node is the root node ---------- */
//         boolean isRoot(){
//             return (this.left == " " && this.right == " " && this.center == " ");
//         }

//         /* -------------------------------------------------------------------------- */
//         /*                  print the node information in tree format                 */
//         /*               and print the node information in table format               */
//         /* -------------------------------------------------------------------------- */
//         void printNode(int level){

//             if(level==0)
//                 System.out.println(this.center+":");
//             else{
//                 System.out.print("|");
//                 for (int i = 0; i < level; i++){
//                     System.out.print("__");
//                 }
//                 System.out.println(this.left + " " + this.center + " " + this.right);
//             }
//         }

//         public void insert(syntax_analyzer.Node node) {
//             if(this.isLeaf()){
//                 this.left = node.left;
//                 this.center = node.center;
//                 this.right = node.right;
//             }
//             else if(this.isParent()){
//                 this.left = node.left;
//                 this.center = node.center;
//                 this.right = node.right;
//             }
//         }

//         // search the value in the node tree
//         public void search(){
            
//         }
//     }
    
//     /**
//          TODO:
//         1. Add the grammar rules to the GrammarString
//         2. create a parse tree
//         3. print the parse tree in a tree format with indentation
//         4. check the parse tree if it is valid grammar from GrammarString
//         return the syntax_analyzer
//      */
    
//     static Node node = new Node("", "", "");
//     public static String[] syntax_analyzer(String[] tokens) {
//         read_grammar_file();

//         table_list = new String[GrammarString.size()][2];
//         for (int i = 0; i < GrammarString.size(); i++) {
//             String[] temp = GrammarString.get(i).split(":");
//             table_list[i][0] = temp[0];
//             table_list[i][1] = temp[1];
//         }
//         parseTree(tokens,0);
        
//         return tokens;
//     }

//     /* -------------------------------------------------------------------------- */
//     /*       Read the grammar file and store each line as a List of Strings       */
//     /* -------------------------------------------------------------------------- */
//     public static void read_grammar_file() {
//         // read the grammar.txt file until eof 
//         try {
//             Scanner scan = new Scanner(new java.io.File("grammar.txt"));
//             while (scan.hasNextLine()) {
//                 GrammarString.add(scan.nextLine());
//             }
//             /**
//              // print the grammar
//              System.out.println("\nGrammar\tType");
//              for (int i = 0; i < GrammarString.size(); i++) {
//                  System.out.println(GrammarString.get(i) + "\t" + lexical_analyzer.getTokenType(GrammarString.get(i)));
//              }
//              */
//             scan.close();
//         } catch (Exception e) {
//             System.out.println("Error");
//         }
//     }
    
//     /* -------------------------------------------------------------------------- */
//     /*         Create Parse Tree that tokenizes the given array of tokens         */
//     /* -------------------------------------------------------------------------- */
//     public static void parseTree(String[] tokens, int idx) {
//         // var_dec: data_type dec
//         String[] var_dec = table_list[0][1].split("\s"); 
//          // data_type: int | float | char
//         String[] data_type = split_by_or(1, "Data_type");
//          // dec: identifier [=exp] | identifier, dec
//         String[] dec = split_by_or(2, "Dec");
//          // dec2[0] -> identifier, dec2[1] -> [=exp]
//         String[] dec2 = dec[0].split(" ");
//         // (+|*|/|-) exp | '(' exp ')' | a-z-A-Z [exp] | 0 - 9 [exp] | " exp " | ' exp ' | . exp
//         String[] exp = split_by_or(3, "Exp");
//         String[] ops_exp = ops_exp();
//         // split GrammarString[0] by ":"
//         String[] temp = GrammarString.get(0).split(":");
//         String[] temp2 = temp[1].split("\s");
//         Node node = new Node(" "," "," ");
//         // add the var_dec to root
//         root(temp, node);
//         // insert var_dec into node
//         // node.insert(new Node(var_dec[0], var_dec[1], var_dec[2]));
//         // print the node
//         node.printNode(1);
//         // add the tokens[1] to the node if it exist in data_type[i]
//         data_types(tokens, idx, data_type, node);
//         String tmp = identifier(tokens, idx);
//         idx += tmp.length();
//         // int op=(op+);
//         System.out.println("idx: " + idx + " tokens: " + tokens[idx]+ " tmp: " + tmp);
//         dec(tokens, idx, tmp, dec);
//     }

//     private static void dec(String[] tokens, int idx, String tmp, String[] dec) {
//         // check if the lexical_analyzer.getTokenType(tokens[idx+1]) == "Operator"
//         if(lexical_analyzer.getTokenType(tokens[idx+1])=="Operator"){
//             System.out.println("idx: " + idx + " tokens+: " + tokens[idx]+ " tmp: " + tmp);

//         }
//         else if(lexical_analyzer.getTokenType(tokens[idx+1])=="Equals_op"){
            
//         }
//         else if(lexical_analyzer.getTokenType(tokens[idx+1])=="Comma"){
//             // identifier(tokens, idx);
//         }
//     }

//     private static String identifier(String[] tokens, int idx) {
//         String tmp = "";
//         // check if getTokenType(tokens[1]) is "Identifier"
//         if(lexical_analyzer.getTokenType(tokens[1])!="Identifier"){
//             System.out.println("Error: " + tokens[idx+1] + " is not an identifier");
//             System.exit(0);
//         }
//         tmp += tokens[idx+1];
//         for (int i = 2; i < tokens.length;) {
//             if(lexical_analyzer.getTokenType(tokens[i])=="Identifier"
//             || lexical_analyzer.getTokenType(tokens[i])=="Constant") {
//                 System.out.println("i: " + i+" tokens: " + tokens[i]+" tmp: " + tmp);
//                 tmp+= tokens[i];
//                 i++;
//             }
//             else{
//                 break;
//             }
//         }
//         System.out.println("idx: " + idx + " tokens: " + tokens[idx]+ " tmp: " + tmp);
//         return tmp;
//     }

//     private static void root(String[] temp, Node node) {
//         if (node.isRoot()) {
//             // add temp[0] to the root node
//             node.center = temp[0];
//             // print node.
//             node.printNode(0);
//         }
//     }

//     private static void data_types(String[] tokens, int idx, String[] data_type, Node node) {
//         for (int i = 0; i < data_type.length; i++) {
//             if (tokens[idx].equals(data_type[i])) {
//                 // node.insert(new Node(tokens[idx], " ", " "));
//                 node.printNode(2);
//                 idx++;
//                 break;
//             }
//             // else throw error
//             else if (i == data_type.length - 1) {
//                 System.out.println("Error: " + tokens[idx] + " is not a valid data type");
//                 System.exit(0);
//             }
//         }
//     }
    
//     /* ---------- Return the parse tree structure for the given grammar --------- */
//     public static String[] ops_exp(){
//         String[] temp = table_list[3][1].split("\s");
//         return temp;
//     }

//     public static String[] split_by_or(int inx, String str){
//         String[] temp = table_list[inx][1].split("\s\\|\s");
//         return temp;
//     }
// }