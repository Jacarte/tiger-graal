grammar Tiger;


tokens
{
	NEG,
	ACCESS,
	FUNCCALL,
	EXPRSEQ,
	RECASSIGN,
	RECFIELD,
	FUNARG,
	ARRAYTDEC,
	ARRCREATION,
	FUNBLOCK,
	VARBLOCK,
	TYPEDEFBLOCK,
	DECLARATIONS,
	PROGRAM,
	IFTHEN,
	IFTHENELSE,
	SIMPLEASSIGN,
	FUNPARAMS,
	RECPARAMS,
	FUNARGUMENTS,
	RETURNTYPE


}

@lexer::header{
package com.xppcoder.parser.grammar;

import java.util.HashMap;
import java.util.Map;
}

@parser::header{
package com.xppcoder.parser.grammar;
}

@lexer::members { // keywords map used in lexer to assign token types
Map<String,Integer> keywords = new HashMap<String,Integer>() {{

    put("{", TigerParser.LBRACE);
    put("}", TigerParser.RBRACE);
    put("*", TigerParser.ASTER);
    put("+", TigerParser.PLUS);
    put("-", TigerParser.MINUS);
    put("/", TigerParser.SLASH);
    put("\\", TigerParser.BACKSLASH);
    put("(", TigerParser.LPAR);
    put(")", TigerParser.RPAR);
    put("[", TigerParser.LBRACK);
    put("]", TigerParser.RBRACK);
    put(";", TigerParser.SEMICOLON);
    put(",", TigerParser.COMMA);
    put(".", TigerParser.DOT);
    put(":", TigerParser.DOTS);

	put("&", TigerParser.AMPERSAN);
	put("|", TigerParser.PIPE);
	put("<=", TigerParser.LESSE);
	put(">=", TigerParser.GREATE);
	put(">", TigerParser.GREATT);
	put("<", TigerParser.LESST);
	put("<>", TigerParser.INEQUAL);
	put("=", TigerParser.EQUAL);


	put("if", TigerParser.IF);
	put(":=", TigerParser.ASSIGN);
	put("for", TigerParser.FOR);
	put("break", TigerParser.BREAK);
	put("array", TigerParser.ARRAY);
	put("else", TigerParser.ELSE);
	put("then", TigerParser.THEN);
	put("end", TigerParser.END);
	put("in", TigerParser.IN);
	put("let", TigerParser.LET);
	put("function", TigerParser.FUNCTION);
	put("nil", TigerParser.NIL);
	put("type", TigerParser.TYPE);
	put("var", TigerParser.VAR);
	put("while", TigerParser.WHILE);
	put("of", TigerParser.OF);
	put("do", TigerParser.DO);
	put("to", TigerParser.TO);
}};
}

/*

 * Parser Rules

 */

program	:
 	evaluation EOF;

evaluation
	:
	  left=evaluation op=(AMPERSAN|PIPE|XOR|SHIFT_LEFT|SHIFT_RIGHT) right=evaluation # BitOp
    | left=evaluation op=(ASTER|SLASH) right=evaluation # ArithOp
    | left=evaluation op=(PLUS|MINUS) right=evaluation # ArithOp
    | left=evaluation op=(EQUAL |INEQUAL|GREATE|LESSE|GREATT|LESST) right=evaluation # BoolOp
	| STRING # StringNode
	| REAL # RealNode
	|INTEGER # IntegerNode
	| NIL # NilNode
	| (ID LBRACK evaluation RBRACK OF evaluation) # IndexNode
	| id=ID ASSIGN eval=evaluation # MemberCallNode
	| ID # IdNode
	| MINUS eval=evaluation # UnaryNode
	| LPAR list=expr_seq? RPAR # ExprListNode
	//| record_assign # RecordAssignNode
	| function_call # CallNode
	| if_ # IfNode
	| while_  # Statement
	| for_ # EvalNode
	| BREAK # BreakNode
	| let # LetNode
	| DEBUG # DebugNode
	;

function_call//Ver despues si se puede ligar con member_call
	:	ID LPAR expr_list? RPAR # FUNCALL // print
	;
expr_list:
	evaluation (COMMA evaluation)*;
expr_seq:
	 evaluation (SEMICOLON evaluation)*
	 ;

if_	:
	 IF cond=evaluation THEN et=evaluation # IfThenNode

|      IF cond=evaluation THEN et=evaluation ELSE els=evaluation # IfThenElseNode

	;
for_	:
	FOR init=simple_assign TO final_=evaluation DO doex=evaluation # ForNode
	;

simple_assign:
	ID ASSIGN evaluation # SAssignNode;
while_	:
	WHILE cond=evaluation DO dob=evaluation # WhileNode;
declaration
	//:	typedef_declaration_block
	:	FUNCTION il=ID LPAR type_fields? RPAR EQUAL evaluation # FuncDefineNode
	|	VAR ID ASSIGN evaluation # VariableAssign
	;

func_arg
    : name=ID;

type_fields
	:	func_arg (COMMA func_arg )*
	;

variable_type
	: DOTS ID
	;

let:
	LET declaration+ IN expr_seq? END
	;

REAL: DIGIT+ DOT DIGIT+;
INTEGER	:	DIGIT+;

XOR: '^';
SHIFT_LEFT: '<<';
SHIFT_RIGHT: '>>';
LBRACE	:'{';
RBRACE	:'}';
ASTER	:'*';
PLUS	:'+';
MINUS	:'-';
SLASH	:'/';
BACKSLASH:'\\';
LPAR	:'(';
RPAR	:')';
LBRACK	:'[';
RBRACK	: ']';
SEMICOLON : ';';
COMMA	: ',';
DOT		: '.';
DOTS	: ':';


ASSIGN	: ':=';
EQUAL	: '=';
INEQUAL	: '<>';
LESST	: '<';
GREATT	: '>';
GREATE	: '>=';
LESSE	: '<=';
PIPE	: '|';
AMPERSAN : '&';

FOR		:	'for';
BREAK	:	'break';
ARRAY	:	'array';
IF		:	'if';
ELSE	:	'else';
THEN	:	'then';
END		:	'end';
IN		:	'in';
LET		:	'let';
FUNCTION:	'function';
NIL		:	'nil';
TYPE	:	'type';
VAR		:	'var';
WHILE	:	'while';
OF		:	'of';
DO		:	'do';
TO		:	'to';

DEBUG: 'DEBUG';

ID	:	LETTER(LETTER|DIGIT|'_' )*;

fragment
	DIGIT: '0'..'9';
fragment
	LETTER: 'a'..'z'|'A'..'Z';

fragment
	SCAPE	: '\\'('t'|'n'|'r'|'"')
	;

WS	:	(' '|'\n'|'\r' |'\t')+ -> skip;//aclaracion y modificacion 1




PRINTABLE
	:	(' '..'~');//aclaracion y modificacion 2, modificar

STRING 	:	 '"'(SCAPE|PRINTABLE|WS)*? '"';
COMMENT	:	'/*' (.)*? '*/' -> skip;


//Poner los caracteres especiales q signifiquen algo como imprimir el caracter especial /ddd


