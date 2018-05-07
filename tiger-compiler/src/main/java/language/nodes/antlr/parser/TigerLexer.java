// Generated from Tiger.g4 by ANTLR 4.7.1

package language.nodes.antlr.parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TigerLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		REAL=1, INTEGER=2, XOR=3, SHIFT_LEFT=4, SHIFT_RIGHT=5, LBRACE=6, RBRACE=7, 
		ASTER=8, PLUS=9, MINUS=10, SLASH=11, BACKSLASH=12, LPAR=13, RPAR=14, LBRACK=15, 
		RBRACK=16, SEMICOLON=17, COMMA=18, DOT=19, DOTS=20, ASSIGN=21, EQUAL=22, 
		INEQUAL=23, LESST=24, GREATT=25, GREATE=26, LESSE=27, PIPE=28, AMPERSAN=29, 
		FOR=30, BREAK=31, ARRAY=32, IF=33, ELSE=34, THEN=35, END=36, IN=37, LET=38, 
		FUNCTION=39, NIL=40, TYPE=41, VAR=42, WHILE=43, OF=44, DO=45, TO=46, DEBUG=47, 
		ID=48, WS=49, PRINTABLE=50, STRING=51, COMMENT=52;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"REAL", "INTEGER", "XOR", "SHIFT_LEFT", "SHIFT_RIGHT", "LBRACE", "RBRACE", 
		"ASTER", "PLUS", "MINUS", "SLASH", "BACKSLASH", "LPAR", "RPAR", "LBRACK", 
		"RBRACK", "SEMICOLON", "COMMA", "DOT", "DOTS", "ASSIGN", "EQUAL", "INEQUAL", 
		"LESST", "GREATT", "GREATE", "LESSE", "PIPE", "AMPERSAN", "FOR", "BREAK", 
		"ARRAY", "IF", "ELSE", "THEN", "END", "IN", "LET", "FUNCTION", "NIL", 
		"TYPE", "VAR", "WHILE", "OF", "DO", "TO", "DEBUG", "ID", "DIGIT", "LETTER", 
		"SCAPE", "WS", "PRINTABLE", "STRING", "COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, "'^'", "'<<'", "'>>'", "'{'", "'}'", "'*'", "'+'", "'-'", 
		"'/'", "'\\'", "'('", "')'", "'['", "']'", "';'", "','", "'.'", "':'", 
		"':='", "'='", "'<>'", "'<'", "'>'", "'>='", "'<='", "'|'", "'&'", "'for'", 
		"'break'", "'array'", "'if'", "'else'", "'then'", "'end'", "'in'", "'let'", 
		"'function'", "'nil'", "'type'", "'var'", "'while'", "'of'", "'do'", "'to'", 
		"'DEBUG'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "REAL", "INTEGER", "XOR", "SHIFT_LEFT", "SHIFT_RIGHT", "LBRACE", 
		"RBRACE", "ASTER", "PLUS", "MINUS", "SLASH", "BACKSLASH", "LPAR", "RPAR", 
		"LBRACK", "RBRACK", "SEMICOLON", "COMMA", "DOT", "DOTS", "ASSIGN", "EQUAL", 
		"INEQUAL", "LESST", "GREATT", "GREATE", "LESSE", "PIPE", "AMPERSAN", "FOR", 
		"BREAK", "ARRAY", "IF", "ELSE", "THEN", "END", "IN", "LET", "FUNCTION", 
		"NIL", "TYPE", "VAR", "WHILE", "OF", "DO", "TO", "DEBUG", "ID", "WS", 
		"PRINTABLE", "STRING", "COMMENT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	 // keywords map used in lexer to assign token types
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


	public TigerLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Tiger.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\66\u0142\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\3\2\6\2s\n\2\r\2\16\2t\3\2\3"+
		"\2\6\2y\n\2\r\2\16\2z\3\3\6\3~\n\3\r\3\16\3\177\3\4\3\4\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3"+
		"\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3"+
		"\25\3\25\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3"+
		"\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3\37\3"+
		" \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3"+
		"$\3$\3$\3%\3%\3%\3%\3&\3&\3&\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3(\3"+
		"(\3)\3)\3)\3)\3*\3*\3*\3*\3*\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3-\3-\3-\3"+
		".\3.\3.\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\7\61"+
		"\u0115\n\61\f\61\16\61\u0118\13\61\3\62\3\62\3\63\3\63\3\64\3\64\3\64"+
		"\3\65\6\65\u0122\n\65\r\65\16\65\u0123\3\65\3\65\3\66\3\66\3\67\3\67\3"+
		"\67\3\67\7\67\u012e\n\67\f\67\16\67\u0131\13\67\3\67\3\67\38\38\38\38"+
		"\78\u0139\n8\f8\168\u013c\138\38\38\38\38\38\4\u012f\u013a\29\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C"+
		"#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\2e\2g\2i\63k\64m\65o\66\3\2\5"+
		"\4\2C\\c|\6\2$$ppttvv\5\2\13\f\17\17\"\"\2\u0149\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2"+
		"\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2"+
		"Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2i\3\2\2\2\2k\3"+
		"\2\2\2\2m\3\2\2\2\2o\3\2\2\2\3r\3\2\2\2\5}\3\2\2\2\7\u0081\3\2\2\2\t\u0083"+
		"\3\2\2\2\13\u0086\3\2\2\2\r\u0089\3\2\2\2\17\u008b\3\2\2\2\21\u008d\3"+
		"\2\2\2\23\u008f\3\2\2\2\25\u0091\3\2\2\2\27\u0093\3\2\2\2\31\u0095\3\2"+
		"\2\2\33\u0097\3\2\2\2\35\u0099\3\2\2\2\37\u009b\3\2\2\2!\u009d\3\2\2\2"+
		"#\u009f\3\2\2\2%\u00a1\3\2\2\2\'\u00a3\3\2\2\2)\u00a5\3\2\2\2+\u00a7\3"+
		"\2\2\2-\u00aa\3\2\2\2/\u00ac\3\2\2\2\61\u00af\3\2\2\2\63\u00b1\3\2\2\2"+
		"\65\u00b3\3\2\2\2\67\u00b6\3\2\2\29\u00b9\3\2\2\2;\u00bb\3\2\2\2=\u00bd"+
		"\3\2\2\2?\u00c1\3\2\2\2A\u00c7\3\2\2\2C\u00cd\3\2\2\2E\u00d0\3\2\2\2G"+
		"\u00d5\3\2\2\2I\u00da\3\2\2\2K\u00de\3\2\2\2M\u00e1\3\2\2\2O\u00e5\3\2"+
		"\2\2Q\u00ee\3\2\2\2S\u00f2\3\2\2\2U\u00f7\3\2\2\2W\u00fb\3\2\2\2Y\u0101"+
		"\3\2\2\2[\u0104\3\2\2\2]\u0107\3\2\2\2_\u010a\3\2\2\2a\u0110\3\2\2\2c"+
		"\u0119\3\2\2\2e\u011b\3\2\2\2g\u011d\3\2\2\2i\u0121\3\2\2\2k\u0127\3\2"+
		"\2\2m\u0129\3\2\2\2o\u0134\3\2\2\2qs\5c\62\2rq\3\2\2\2st\3\2\2\2tr\3\2"+
		"\2\2tu\3\2\2\2uv\3\2\2\2vx\5\'\24\2wy\5c\62\2xw\3\2\2\2yz\3\2\2\2zx\3"+
		"\2\2\2z{\3\2\2\2{\4\3\2\2\2|~\5c\62\2}|\3\2\2\2~\177\3\2\2\2\177}\3\2"+
		"\2\2\177\u0080\3\2\2\2\u0080\6\3\2\2\2\u0081\u0082\7`\2\2\u0082\b\3\2"+
		"\2\2\u0083\u0084\7>\2\2\u0084\u0085\7>\2\2\u0085\n\3\2\2\2\u0086\u0087"+
		"\7@\2\2\u0087\u0088\7@\2\2\u0088\f\3\2\2\2\u0089\u008a\7}\2\2\u008a\16"+
		"\3\2\2\2\u008b\u008c\7\177\2\2\u008c\20\3\2\2\2\u008d\u008e\7,\2\2\u008e"+
		"\22\3\2\2\2\u008f\u0090\7-\2\2\u0090\24\3\2\2\2\u0091\u0092\7/\2\2\u0092"+
		"\26\3\2\2\2\u0093\u0094\7\61\2\2\u0094\30\3\2\2\2\u0095\u0096\7^\2\2\u0096"+
		"\32\3\2\2\2\u0097\u0098\7*\2\2\u0098\34\3\2\2\2\u0099\u009a\7+\2\2\u009a"+
		"\36\3\2\2\2\u009b\u009c\7]\2\2\u009c \3\2\2\2\u009d\u009e\7_\2\2\u009e"+
		"\"\3\2\2\2\u009f\u00a0\7=\2\2\u00a0$\3\2\2\2\u00a1\u00a2\7.\2\2\u00a2"+
		"&\3\2\2\2\u00a3\u00a4\7\60\2\2\u00a4(\3\2\2\2\u00a5\u00a6\7<\2\2\u00a6"+
		"*\3\2\2\2\u00a7\u00a8\7<\2\2\u00a8\u00a9\7?\2\2\u00a9,\3\2\2\2\u00aa\u00ab"+
		"\7?\2\2\u00ab.\3\2\2\2\u00ac\u00ad\7>\2\2\u00ad\u00ae\7@\2\2\u00ae\60"+
		"\3\2\2\2\u00af\u00b0\7>\2\2\u00b0\62\3\2\2\2\u00b1\u00b2\7@\2\2\u00b2"+
		"\64\3\2\2\2\u00b3\u00b4\7@\2\2\u00b4\u00b5\7?\2\2\u00b5\66\3\2\2\2\u00b6"+
		"\u00b7\7>\2\2\u00b7\u00b8\7?\2\2\u00b88\3\2\2\2\u00b9\u00ba\7~\2\2\u00ba"+
		":\3\2\2\2\u00bb\u00bc\7(\2\2\u00bc<\3\2\2\2\u00bd\u00be\7h\2\2\u00be\u00bf"+
		"\7q\2\2\u00bf\u00c0\7t\2\2\u00c0>\3\2\2\2\u00c1\u00c2\7d\2\2\u00c2\u00c3"+
		"\7t\2\2\u00c3\u00c4\7g\2\2\u00c4\u00c5\7c\2\2\u00c5\u00c6\7m\2\2\u00c6"+
		"@\3\2\2\2\u00c7\u00c8\7c\2\2\u00c8\u00c9\7t\2\2\u00c9\u00ca\7t\2\2\u00ca"+
		"\u00cb\7c\2\2\u00cb\u00cc\7{\2\2\u00ccB\3\2\2\2\u00cd\u00ce\7k\2\2\u00ce"+
		"\u00cf\7h\2\2\u00cfD\3\2\2\2\u00d0\u00d1\7g\2\2\u00d1\u00d2\7n\2\2\u00d2"+
		"\u00d3\7u\2\2\u00d3\u00d4\7g\2\2\u00d4F\3\2\2\2\u00d5\u00d6\7v\2\2\u00d6"+
		"\u00d7\7j\2\2\u00d7\u00d8\7g\2\2\u00d8\u00d9\7p\2\2\u00d9H\3\2\2\2\u00da"+
		"\u00db\7g\2\2\u00db\u00dc\7p\2\2\u00dc\u00dd\7f\2\2\u00ddJ\3\2\2\2\u00de"+
		"\u00df\7k\2\2\u00df\u00e0\7p\2\2\u00e0L\3\2\2\2\u00e1\u00e2\7n\2\2\u00e2"+
		"\u00e3\7g\2\2\u00e3\u00e4\7v\2\2\u00e4N\3\2\2\2\u00e5\u00e6\7h\2\2\u00e6"+
		"\u00e7\7w\2\2\u00e7\u00e8\7p\2\2\u00e8\u00e9\7e\2\2\u00e9\u00ea\7v\2\2"+
		"\u00ea\u00eb\7k\2\2\u00eb\u00ec\7q\2\2\u00ec\u00ed\7p\2\2\u00edP\3\2\2"+
		"\2\u00ee\u00ef\7p\2\2\u00ef\u00f0\7k\2\2\u00f0\u00f1\7n\2\2\u00f1R\3\2"+
		"\2\2\u00f2\u00f3\7v\2\2\u00f3\u00f4\7{\2\2\u00f4\u00f5\7r\2\2\u00f5\u00f6"+
		"\7g\2\2\u00f6T\3\2\2\2\u00f7\u00f8\7x\2\2\u00f8\u00f9\7c\2\2\u00f9\u00fa"+
		"\7t\2\2\u00faV\3\2\2\2\u00fb\u00fc\7y\2\2\u00fc\u00fd\7j\2\2\u00fd\u00fe"+
		"\7k\2\2\u00fe\u00ff\7n\2\2\u00ff\u0100\7g\2\2\u0100X\3\2\2\2\u0101\u0102"+
		"\7q\2\2\u0102\u0103\7h\2\2\u0103Z\3\2\2\2\u0104\u0105\7f\2\2\u0105\u0106"+
		"\7q\2\2\u0106\\\3\2\2\2\u0107\u0108\7v\2\2\u0108\u0109\7q\2\2\u0109^\3"+
		"\2\2\2\u010a\u010b\7F\2\2\u010b\u010c\7G\2\2\u010c\u010d\7D\2\2\u010d"+
		"\u010e\7W\2\2\u010e\u010f\7I\2\2\u010f`\3\2\2\2\u0110\u0116\5e\63\2\u0111"+
		"\u0115\5e\63\2\u0112\u0115\5c\62\2\u0113\u0115\7a\2\2\u0114\u0111\3\2"+
		"\2\2\u0114\u0112\3\2\2\2\u0114\u0113\3\2\2\2\u0115\u0118\3\2\2\2\u0116"+
		"\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117b\3\2\2\2\u0118\u0116\3\2\2\2"+
		"\u0119\u011a\4\62;\2\u011ad\3\2\2\2\u011b\u011c\t\2\2\2\u011cf\3\2\2\2"+
		"\u011d\u011e\7^\2\2\u011e\u011f\t\3\2\2\u011fh\3\2\2\2\u0120\u0122\t\4"+
		"\2\2\u0121\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0121\3\2\2\2\u0123"+
		"\u0124\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0126\b\65\2\2\u0126j\3\2\2\2"+
		"\u0127\u0128\4\"\u0080\2\u0128l\3\2\2\2\u0129\u012f\7$\2\2\u012a\u012e"+
		"\5g\64\2\u012b\u012e\5k\66\2\u012c\u012e\5i\65\2\u012d\u012a\3\2\2\2\u012d"+
		"\u012b\3\2\2\2\u012d\u012c\3\2\2\2\u012e\u0131\3\2\2\2\u012f\u0130\3\2"+
		"\2\2\u012f\u012d\3\2\2\2\u0130\u0132\3\2\2\2\u0131\u012f\3\2\2\2\u0132"+
		"\u0133\7$\2\2\u0133n\3\2\2\2\u0134\u0135\7\61\2\2\u0135\u0136\7,\2\2\u0136"+
		"\u013a\3\2\2\2\u0137\u0139\13\2\2\2\u0138\u0137\3\2\2\2\u0139\u013c\3"+
		"\2\2\2\u013a\u013b\3\2\2\2\u013a\u0138\3\2\2\2\u013b\u013d\3\2\2\2\u013c"+
		"\u013a\3\2\2\2\u013d\u013e\7,\2\2\u013e\u013f\7\61\2\2\u013f\u0140\3\2"+
		"\2\2\u0140\u0141\b8\2\2\u0141p\3\2\2\2\f\2tz\177\u0114\u0116\u0123\u012d"+
		"\u012f\u013a\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}