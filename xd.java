// public import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;


// public class syntax_analyzer {
//     static List<String> GrammarString = new ArrayList<String>();
//     static String[] header_list = { "var_dec", "data_type", "dec", "exp", "identifier" };
//     static String[][] table_list;
    
// 	private List<Token> source;
// 	private Token token;
// 	private int position;

//     public static String[] syntax_analyzer(String[] tokens) {
//         // read_grammar_file();

//         // table_list = new String[GrammarString.size()][2];
//         // for (int i = 0; i < GrammarString.size(); i++) {
//         //     String[] temp = GrammarString.get(i).split(":");
//         //     table_list[i][0] = temp[0];
//         //     table_list[i][1] = temp[1];
//         // }
//         // printAST

//         return tokens;
//     }

//     static class Node{
//         public String value;
//         public Node left, right;
//         // access the value from enum NodeType
//         public NodeType type;

//         Node(){
//             this.type = null;
//             this.value = null;
//             this.left = null;
//             this.right = null;
//         }
//         Node(NodeType type, Node left, Node right, String value){
//             this.type = type;
//             this.value = value;
//             this.left = left;
//             this.right = right;
//         }

//         public static Node make_node(NodeType nodetype, Node left, Node right) {
// 			return new Node(nodetype, left, right, "");
// 		}
// 		public static Node make_node(NodeType nodetype, Node left) {
// 			return new Node(nodetype, left, null, "");
// 		}
// 		public static Node make_leaf(NodeType nodetype, String value) {
// 			return new Node(nodetype, null, null, value);
// 		}
        

//     }

//     static class Token{
//         TokenType type;
//         String value;
//         int line;
//         int pos;
//         Token(TokenType type, String value, int line, int pos){
//             this.type = type;
//             this.value = value;
//             this.line = line;
//             this.pos = pos;
//         }
//         @Override
//         public String toString(){
//             return String.format("%5d  %5d %-15s %s", 
//                 this.line, this.pos, this.type, this.value);
//         }
//     }

//     static enum TokenType{
//         End_of_input(NodeType.End_of_input),
//         Identifier(NodeType.ng_ident),
//         Number(NodeType.ng_num),
//         OP_Add(NodeType.ng_add),
//         OP_Sub(NodeType.ng_sub),
//         OP_Mul(NodeType.ng_mul),
//         OP_Div(NodeType.ng_div),
//         Integer(NodeType.ng_int),
//         Float(NodeType.ng_float),
//         String(NodeType.ng_str),
//         Boolean(NodeType.ng_bool),
//         Char(NodeType.ng_char),
//         Double(NodeType.ng_double),
//         Left_Parenthesis(NodeType.ng_lparen),
//         Right_Parenthesis(NodeType.ng_rparen),
//         Comma(NodeType.ng_comma),
//         Equal(NodeType.ng_equal),
//         Double_Quote(NodeType.ng_dob_qout),
//         Single_Quote(NodeType.ng_sng_qout),
//         Dot(NodeType.ng_dot);

//         private final NodeType node_type;

//         TokenType(NodeType node) {
//             this.node_type = node;
//         }
//         NodeType getNodeType(){
//             return this.node_type;
//         }
//     }

//     static enum NodeType {
//         ng_ident("Identifier"), ng_num("Number"), 
//         ng_op("Operator"), ng_int("Integer"), 
//         ng_str("String"), ng_bool("Boolean"), 
//         ng_char("Char"), ng_float("Float"), 
//         ng_double("Double"), ng_lparen("Left_Paren"), 
//         ng_rparen("Right_Paren"), ng_semi("Semi_Colon"), 
//         ng_comma("Comma"), ng_equal("Equal"), 
//         ng_dob_qout("Double_Quote"), ng_sng_qout("Single_Quote"),
//         ng_dot("Dot"), ng_add("Add"), ng_sub("Sub"), 
//         ng_mul("Mul"), ng_div("Div"), End_of_input(""),
//         nd_Sequence("Sequence");
//         private final String s;
//         NodeType(String s){
//             this.s = s;
//         }
//         @Override
//         public String toString(){
//             return s;
//         }
//     }

//     Node expr(int p){
//         Node result = null, node;
//         TokenType op;
//         int q;

//         if(this.token.type == TokenType.Left_Parenthesis){
//             result = paren_expr();
//         }
//         else if (this.token.type == TokenType.OP_Add || 
//                 this.token.type == TokenType.OP_Sub ||
//                 this.token.type == TokenType.OP_Mul ||
//                 this.token.type == TokenType.OP_Div){
//             op = this.token.type;
//             getNextToken();
//         }
//         else if (this.token.type == TokenType.Identifier){
//             result = Node.make_leaf(NodeType.ng_ident, this.token.value);
//             getNextToken();
//         }
//         else if (this.token.type == TokenType.Number){
//             result = Node.make_leaf(NodeType.ng_num, this.token.value);
//             getNextToken();
//         }
//         else if (this.token.type == TokenType.Double_Quote){
//             result = Node.make_leaf(NodeType.ng_str, this.token.value);
//             getNextToken();
//         }
//         else if (this.token.type == TokenType.Single_Quote){
//             result = Node.make_leaf(NodeType.ng_char, this.token.value);
//             getNextToken();
//         }
//         else if (this.token.type == TokenType.Boolean){
//             result = Node.make_leaf(NodeType.ng_bool, this.token.value);
//             getNextToken();
//         }
//         else if (this.token.type == TokenType.Integer){
//             result = Node.make_leaf(NodeType.ng_int, this.token.value);
//             getNextToken();
//         }
//         else if (this.token.type == TokenType.Float){
//             result = Node.make_leaf(NodeType.ng_float, this.token.value);
//             getNextToken();
//         }
//         else if (this.token.type == TokenType.Double){
//             result = Node.make_leaf(NodeType.ng_double, this.token.value);
//             getNextToken();
//         }
//         else if (this.token.type == TokenType.Dot){
//             result = Node.make_leaf(NodeType.ng_dot, this.token.value);
//             getNextToken();
//         }
//         else if (this.token.type == TokenType.Left_Parenthesis){
//             result = paren_expr();
//         }
//         else{
//             error(this.token.line, this.token.pos, "Expecting a primary, found: " + this.token.type);
//         }
//         return result;
//     }

//     void printAST(Node t){
//         int i = 0;
//         if(t == null){
//             return;
//         }
//         else{
//             System.out.printf("%-14s", t.type);
// 			if (t.type == NodeType.ng_ident || t.type == NodeType.ng_ident || t.type == NodeType.ng_str) {
// 				System.out.println(" " + t.value);
// 			} else {
// 				System.out.println();
// 				printAST(t.left);
// 				printAST(t.right);
// 			}
//         }
//     }
//     static void error(int line, int pos, String msg){
//         System.out.println("Error at line " + line + " pos " + pos + ": " + msg);
//     }

//     Node paren_expr(){
//         expect("paren_expr", TokenType.Left_Parenthesis);
//         Node result = expr(0);
//         expect("paren_expr", TokenType.Right_Parenthesis);
//         return result;
//     }
//     void expect(String msg, TokenType op){
//         if(this.token.type == op){
//             getNextToken();
//             return;
//         }
//     }
//     Token getNextToken(){
//         this.token = this.source.get(this.position++);
//         return this.token;
//     }

//     /* -------------------------------------------------------------------------- */
//     /* Read the grammar file and store each line as a List of Strings */
//     /* -------------------------------------------------------------------------- */
//     public static void read_grammar_file() {
//         // read the grammar.txt file until eof
//         try {
//             Scanner scan = new Scanner(new java.io.File("grammar.txt"));
//             while (scan.hasNextLine()) {
//                 GrammarString.add(scan.nextLine());
//             }
//             scan.close();
//         } catch (Exception e) {
//             System.out.println("Error");
//         }
//     }
// }class xd {
    
// }
