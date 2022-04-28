# Programming-Languages
## Lexical Analyzer

```
Enter the source code: 
int temp2+=(temp23*23.4%)/2;

        int     Data Type int 
        t       1?Identifier  
        t       1111Identifier
        t       Identifier    
        e       1?Identifier  
        e       1111Identifier
        e       Identifier
        m       1?Identifier
        m       1111Identifier
        m       Identifier
        p       1?Identifier
        p       1111Identifier
        p       Identifier
        2       1?Constant
        2       1111Constant
        2       Constant
        +       1?Operator
        +       1111Operator
        +=      1Assignment_op
        +=      2Assignment_op
        +=      Assignment_op
        (       1?Left_parenthesis
        (       1111Left_parenthesis
        (       Left_parenthesis
        t       1?Identifier
        t       1111Identifier
        t       Identifier
        e       1?Identifier
        e       1111Identifier
        e       Identifier
        m       1?Identifier
        m       1111Identifier
        m       Identifier
        p       1?Identifier
        p       1111Identifier
        p       Identifier
        2       1?Constant
        2       1111Constant
        2       Constant
        3       1?Constant
        3       1111Constant
        3       Constant
        *       1?Operator
        *       1111Operator
        *2      1null
        *       Operator
        2       1?Constant
        2       1111Constant
        2       Constant
        3       1?Constant
        3       1111Constant
        3       Constant
        .       1?Dot
        .       1111Dot
        .       Dot
        4       1?Constant
        4       1111Constant
        4       Constant
        %       1?Operator
        %       1111Operator
        %)      1null
        %       Operator
        )       1?Right_parenthesis
        )       1111Right_parenthesis
        )       Right_parenthesis
        /       1?Operator
        /       1111Operator
        /2      1null
        /       Operator
        2       1?Constant
        2       1111Constant
        2       Constant
        ;       1?Terminator
        ;       1111Terminator
        ;       Terminator
        int t e m p 2 += ( t e m p 2 3 * 2 3 . 4 % ) / 2 ; null 
        Token   Type
        int     Data Type
        t       Identifier
        e       Identifier
        m       Identifier
        p       Identifier
        2       Constant
        +=      Assignment_op
        (       Left_parenthesis
        t       Identifier
        e       Identifier
        m       Identifier
        p       Identifier
        2       Constant
        3       Constant
        *       Operator
        2       Constant
        3       Constant
        .       Dot
        4       Constant
        %       Operator
        )       Right_parenthesis
        /       Operator
        2       Constant
        ;       Terminator
        int     jaaData Type
        temp2   jhIdentifier
        +=      jaaAssignment_op
        (       jaaLeft_parenthesis
        temp23  jhIdentifier
        *       jaaOperator
        23      -->Constant
        .       jaaDot
        4       -->Constant
        %       jaaOperator
        )       jaaRight_parenthesis
        /       jaaOperator
        2       -->Constant
        ;       jaaTerminator

        Final Token     Type
        int     Data Type
        temp2   Identifier
        +=      Assignment_op
        (       Left_parenthesis
        temp23  Identifier
        *       Operator
        23      Constant
        .       Dot
        4       Constant
        %       Operator
        )       Right_parenthesis
        /       Operator
        2       Constant
        ;       Terminator

        Token   Type
        int     Data Type
        temp2   Identifier
        +=      Assignment_op
        (       Left_parenthesis
        temp23  Identifier
        *       Operator
        23      Constant
        .       Dot
        4       Constant
        %       Operator
        )       Right_parenthesis
        /       Operator
        2       Constant
        ;       Terminator

        Syntax  Type
        int     Data Type
        temp2   Identifier
        (       Left_parenthesis
        temp23  Identifier
        *       Operator
        23      Constant
        .       Dot
        4       Constant
        %       Operator
        )       Right_parenthesis
        /       Operator
        2       Constant
        ;       Terminator
```
