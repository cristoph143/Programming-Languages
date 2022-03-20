# Programming-Languages
## Lexical Analyzer

```
Enter the source code: 
int  a = b1 *b2;
int     Data Type int
a       Identifier a
=       Equals_op =
b1      Identifier b1
Tokenize Ex -> 
        temp_str -> *b2;
        getTokenType(*b2;) == null  
        temp_str[lastChar] -> ; 
        tmptst -> ;  
        temp_str[remaining] -> *b2
        temp_str -> *b2
        getTokenType(*b2) == null  
        temp_str[lastChar] -> 2 
        tmptst -> ;2  
        temp_str[remaining] -> *b
        temp_str -> *b
        getTokenType(*b) == null  
        temp_str[lastChar] -> b 
        tmptst -> ;2b  
        temp_str[remaining] -> *  
        temp_str[remaining] -> getTokenType(*) == Operator 
        temp_str[remaining] -> *
        tmpst -> ;2b
        Reverse of tmpst -> b2;
Return Last Char -> b2;
tmp_array[i] -> *
Tokenize Ex -> 
        temp_str -> b2;
        getTokenType(b2;) == null  
        temp_str[lastChar] -> ; 
        tmptst -> ;  
        temp_str[remaining] -> b2  
        temp_str[remaining] -> getTokenType(b2) == Identifier 
        temp_str[remaining] -> b2
        tmpst -> ;
        Reverse of tmpst -> ;
2nd.Return temp_str -> ;

Subset of token and temp_str -> b2
Temp_array[i] -> *
j tmp_array[j]
0 int
1 a
2 =
3 b1
4 *
Insert tmp_array[i] -> b2
Tokenize Ex -> 
        tmpst ->
        Reverse of tmpst ->
2nd.Return temp_str ->

Subset of token and temp_str -> ;
Temp_array[i] -> *
j tmp_array[j]
0 int
1 a
2 =
3 b1
4 *
5 b2
Insert tmp_array[i] -> ;
--tmp_array*
int a = b1 * b2 ; null
Token   Type
int     Data Type
a       Identifier
=       Equals_op
b1      Identifier
*       Operator
b2      Identifier
;       Terminator
```
