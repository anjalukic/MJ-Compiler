package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROG, yytext()); }
"break"   	{ return new_symbol(sym.BREAK, yytext()); }
"else"   	{ return new_symbol(sym.ELSE, yytext()); }
"const"  	{ return new_symbol(sym.CONST, yytext()); }
"if"   		{ return new_symbol(sym.IF, yytext()); }
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"read"   	{ return new_symbol(sym.READ, yytext()); }
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"for"   	{ return new_symbol(sym.FOR, yytext()); }
"continue" 	{ return new_symbol(sym.CONTINUE, yytext()); }
"new" 		{ return new_symbol(sym.NEW, yytext()); }
"foreach"   { return new_symbol(sym.FOREACH, yytext()); }
"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"-" 		{ return new_symbol(sym.MINUS, yytext()); }
"*" 		{ return new_symbol(sym.MUL, yytext()); }
"/" 		{ return new_symbol(sym.DIV, yytext()); }
"%" 		{ return new_symbol(sym.MOD, yytext()); }
"==" 		{ return new_symbol(sym.EQUAL, yytext()); }
"!=" 		{ return new_symbol(sym.DIFF, yytext()); }
">" 		{ return new_symbol(sym.GT, yytext()); }
"<" 		{ return new_symbol(sym.LT, yytext()); }
">=" 		{ return new_symbol(sym.GTE, yytext()); }
"<=" 		{ return new_symbol(sym.LTE, yytext()); }
"&&" 		{ return new_symbol(sym.AND, yytext()); }
"||" 		{ return new_symbol(sym.OR, yytext()); }
"=" 		{ return new_symbol(sym.ASSIGN, yytext()); }
"+=" 		{ return new_symbol(sym.ADDASSIGN, yytext()); }
"-=" 		{ return new_symbol(sym.SUBASSIGN, yytext()); }
"*=" 		{ return new_symbol(sym.MULASSIGN, yytext()); }
"/=" 		{ return new_symbol(sym.DIVASSIGN, yytext()); }
"%=" 		{ return new_symbol(sym.MODASSIGN, yytext()); }
"++" 		{ return new_symbol(sym.INCR, yytext()); }
"--" 		{ return new_symbol(sym.DECR, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"." 		{ return new_symbol(sym.DOT, yytext()); }
"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }
"[" 		{ return new_symbol(sym.LBRACKET, yytext()); }
"]" 		{ return new_symbol(sym.RBRACKET, yytext()); }
":" 		{ return new_symbol(sym.COLON, yytext()); }

"//" 		     { yybegin(COMMENT); }
<COMMENT> .      { yybegin(COMMENT); }
<COMMENT> "\r\n" { yybegin(YYINITIAL); }

[0-9]+  { return new_symbol(sym.NUMCONST, new Integer (yytext())); }
'.'  { return new_symbol(sym.CHARCONST, new Character (yytext().charAt(1))); }
("true"|"false")  { return new_symbol(sym.BOOLCONST, new String (yytext())); }
([a-z]|[A-Z])[a-z|A-Z|0-9|_]* 	{return new_symbol (sym.IDENT, yytext()); }

. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }






