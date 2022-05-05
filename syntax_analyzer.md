```tefcha
main
start
idx = 0;
if data_types(tokens, idx)
    tmp = identifier(tokens, idx);
    idx += tmp.length();
    String[] sub_tokens = Arrays.copyOfRange(tokens, idx + 1, tokens.length);
    if sub_tokens.length < 2
        isTerminator(tokens, tokens.length - 1);
    dec(sub_tokens, 0, tmp);
    return tokens;
else
    isTerminator(tokens, tokens.length - 1);
end

start
String[] data_type = { "boolean", "float", "int", "String", "byte", "long", "short" };
boolean is_data_type = Arrays.asList(data_type).contains(tokens[idx]);
String tmp = forming_identifier(tokens, idx, "");
int len = tmp.length();
String[] sub_tokens = Arrays.copyOfRange(tokens, len, tokens.length);
System.out.println("sub_tokens: " + Arrays.toString(sub_tokens));
// if tokens[i] is Data_Type
if is_data_type 
    return true;
else
    is_data_type = Arrays.asList(data_type).contains(tmp);
    if (!is_data_type)
        System.out.println("Error: " + tmp + " is not a data type");
        System.exit(1);
        end
    len = tmp.length();
    sub_tokens = Arrays.copyOfRange(tokens, len, tokens.length);
    System.out.println("sub_tokens: " + Arrays.toString(sub_tokens));
    if sub_tokens.length <= 3
        System.out.println("Error: " + sub_tokens[0] + " expected \"Identifier\"");
        System.exit(1);
    else
        return is_data_type;

String tmp2 = tmp;
String[] sub_tokens;
if lexical_analyzer.getTokenType(tokens[idx]) == "Equals_op"
    exp(tokens, idx+1);
elif lexical_analyzer.getTokenType(tokens[idx]) == "Comma"
    tmp = identifier(tokens, idx);
    idx += tmp.length();
    sub_tokens = Arrays.copyOfRange(tokens, idx+1, tokens.length);
    if sub_tokens.length < 2
        isTerminator(tokens, tokens.length - 1);
    else
        System.out.println("sub_tokens== " + Arrays.toString(sub_tokens));
        dec(sub_tokens, 0, tmp);
elif lexical_analyzer.getTokenType(tokens[idx]) == "Operator"
    System.out.println("Syntax error on token \""+ tokens[idx]+"\", invalid AssignmentOperator");
    System.exit(0);

if lexical_analyzer.getTokenType(tokens[idx]) == "Terminator"
    System.out.println("Syntax Accepted");
    System.exit(0);
else 
    System.out.println("Syntax error, insert \";\" to complete LocalVariableDeclarationStatement");
System.exit(0);

String tmp = "";
String[] sub_tokens;
if tokens[idx].equals("(")
    exp_par(tokens, idx);
elif tokens[1].equals(")")
    System.out.println("Syntax error on token \""+ tokens[idx]+"\", expected Identifier or Constant or \"(\"");
    System.exit(1);
elif lexical_analyzer.getTokenType(tokens[idx]) == "Identifier"
    tmp = identifier(tokens, idx);
    idx += tmp.length();
    if lexical_analyzer.getTokenType(tokens[2]) == "Operator"
        tmp = operator(tokens, 3);
        sub_tokens = Arrays.copyOfRange(tokens, tmp.length(), tokens.length);
        idx += tmp.length();
    else
        sub_tokens = Arrays.copyOfRange(tokens, tmp.length(), tokens.length);
        if sub_tokens.length < 2
            isTerminator(tokens, tokens.length - 1);
        else
            exp(sub_tokens, 1);
elif lexical_analyzer.getTokenType(tokens[idx]) == "Constant"
    tmp = constants(tokens, idx);
    idx += tmp.length();
    sub_tokens = Arrays.copyOfRange(tokens, tmp.length(), tokens.length);
    if sub_tokens.length <= 2
        isTerminator(tokens, tokens.length - 1);
    else
        exp(sub_tokens, 1);

String tmp = "";
if lexical_analyzer.getTokenType(tokens[idx]) == "Identifier"
    tmp = identifier(tokens, idx);
    idx += tmp.length();
    String[] sub_tokens = Arrays.copyOfRange(tokens, tmp.length()+1, tokens.length);
    if sub_tokens.length <= 2
        isTerminator(sub_tokens, sub_tokens.length - 1);
    else
        if lexical_analyzer.getTokenType(sub_tokens[1]) == "Operator"
            sub_tokens = Arrays.copyOfRange(tokens, tmp.length()+2, tokens.length);
            if sub_tokens.length == 2)
                System.out.println("Syntax error on token \""+ sub_tokens[0]+"\", expected Identifier or Constant");
                System.exit(0);
            else
                tmp = operator(sub_tokens, idx);
                idx += tmp.length();
        elif sub_tokens[1].equals("(")
            exp_par(sub_tokens, idx);
        elif sub_tokens[1].equals(")")
            System.out.println("Syntax error on token \""+ sub_tokens[0]+"\", expected Identifier or Constant or \"(\"");
            System.exit(0);
elif lexical_analyzer.getTokenType(tokens[idx]) == "Constant"
    tmp = constants(tokens, idx);
    idx += tmp.length();
    String[] sub_tokens = Arrays.copyOfRange(tokens, tmp.length()+1, tokens.length);
    if sub_tokens.length <= 2
        isTerminator(sub_tokens, sub_tokens.length - 1);
    else
        if lexical_analyzer.getTokenType(sub_tokens[1]) == "Operator"
            sub_tokens = Arrays.copyOfRange(tokens, tmp.length()+2, tokens.length);
            if sub_tokens.length == 2
                System.out.println("Syntax error on token \""+ sub_tokens[0]+"\", expected Identifier or Constant");
                System.exit(0);
            else
                tmp = operator(sub_tokens, idx);
                idx += tmp.length();
        elif sub_tokens[1].equals("(")
            exp_par(sub_tokens, idx);
        elif sub_tokens[1].equals(")")
            System.out.println("Syntax error on token \""+ sub_tokens[0]+"\", expected Identifier or Constant or \"(\"");
            System.exit(0);
return tmp;

String tmp;
String[] sub_tokens;
String tmp_str = "";
while tokens.length > i
    tmp_str += tokens[i];
    i++
tmp = tokens[idx];
isBalancedPar(tmp_str);
sub_tokens = Arrays.copyOfRange(tokens, idx + 1, tokens.length);
if sub_tokens.length < 2
    isTerminator(sub_tokens, sub_tokens.length - 1);

int count = 0, idx = 0;
int first, second = 0;
int[] arr = new int[str.length()];
while str.length > i
    if str.charAt(i) == '('
        arr[i] = 0;
        count++;
    elif str.charAt(i) == ')'
        arr[i] = 1;
        count--;
    else
        if isIdentifier_or_constant(str.charAt(i))
            arr[i] = 2;
        else
            arr[i] = 3;
    i++
if str.charAt(0) == ')'
    System.out.println("Syntax error on token \""+ str.charAt(0)+"\", expected \"(\"");
    System.exit(0);
while arr.length > i
    if arr[i] == 0 || arr[i] == 1
        if arr[i] == 0
            if arr[i + 1] != 2 && arr[i + 1] != 0
                System.out.println("Syntax error on token sd\"" + str.charAt(i)+ "\", identifier or constant or \"(\" after this token");
                System.exit(1);
        elif arr[i] == 1
            if arr[i - 1] != 2 && arr[i - 1] != 1
                System.out.println("Syntax error on token s\"" + str.charAt(i)+ "\", identifier or constant or \")\" before this token");
                System.exit(1);
if count != 0
    System.out.println("Syntax error, insert \")\", to complete Expression");
    System.exit(1);

if Character.isLetter(c) || Character.isDigit(c))
    return true;
else
    return false;
end

String tmp = "";
if lexical_analyzer.getTokenType(tokens[1]) != "Identifier"
    if lexical_analyzer.getTokenType(tokens[1]) == "Constant"
        return tmp;
    else
        System.out.println("Error: " + tokens[idx + 1] + " is not an identifier");
        System.exit(0);
else
    tmp += tokens[idx + 1];
    tmp = forming_identifier(tokens, idx, tmp);
    return tmp;

String tmp2 = tmp;
if lexical_analyzer.getTokenType(tokens[idx]) == "Identifier"
    while lexical_analyzer.getTokenType(tokens[idx]) == "Identifier" || lexical_analyzer.getTokenType(tokens[idx]) == "Constant"
        tmp2 += tokens[idx];
        idx++;
return tmp2;


String tmp = "";
if lexical_analyzer.getTokenType(tokens[idx]) != "Constant"
    System.out.println("Error: " + tokens[idx] + " is not a constant");
    System.exit(0);
else
    tmp += tokens[idx + 1];
    tmp = forming_constants(tokens, idx, tmp);
    return tmp;


String tmp2 = tmp;
if lexical_analyzer.getTokenType(tokens[idx]) == "Identifier"
    while lexical_analyzer.getTokenType(tokens[idx]) == "Constant"
        tmp2 += tokens[idx];
        idx++;
return tmp2;
```
