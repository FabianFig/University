// Generated from PA4.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class PA4Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, INT=12, ID=13, WS=14;
	public static final int
		RULE_program = 0, RULE_definition = 1, RULE_params = 2, RULE_expr = 3, 
		RULE_cond = 4, RULE_add = 5, RULE_mul = 6, RULE_unary = 7, RULE_primary = 8, 
		RULE_fncall = 9, RULE_args = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "definition", "params", "expr", "cond", "add", "mul", "unary", 
			"primary", "fncall", "args"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'='", "';'", "','", "'?'", "':'", "'+'", "'-'", 
			"'*'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"INT", "ID", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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

	@Override
	public String getGrammarFileName() { return "PA4.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PA4Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public List<DefinitionContext> definition() {
			return getRuleContexts(DefinitionContext.class);
		}
		public DefinitionContext definition(int i) {
			return getRuleContext(DefinitionContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PA4Visitor ) return ((PA4Visitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(22);
				definition();
				}
				}
				setState(25); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefinitionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(PA4Parser.ID, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public DefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PA4Visitor ) return ((PA4Visitor<? extends T>)visitor).visitDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefinitionContext definition() throws RecognitionException {
		DefinitionContext _localctx = new DefinitionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_definition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			match(ID);
			setState(28);
			match(T__0);
			setState(29);
			params();
			setState(30);
			match(T__1);
			setState(31);
			match(T__2);
			setState(32);
			expr();
			setState(33);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamsContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(PA4Parser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PA4Parser.ID, i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PA4Visitor ) return ((PA4Visitor<? extends T>)visitor).visitParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_params);
		int _la;
		try {
			setState(44);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(36);
				match(ID);
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(37);
					match(T__4);
					setState(38);
					match(ID);
					}
					}
					setState(43);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PA4Visitor ) return ((PA4Visitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			cond();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CondContext extends ParserRuleContext {
		public AddContext add() {
			return getRuleContext(AddContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public CondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cond; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PA4Visitor ) return ((PA4Visitor<? extends T>)visitor).visitCond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondContext cond() throws RecognitionException {
		CondContext _localctx = new CondContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_cond);
		try {
			setState(55);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				add();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(49);
				add();
				setState(50);
				match(T__5);
				setState(51);
				expr();
				setState(52);
				match(T__6);
				setState(53);
				cond();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AddContext extends ParserRuleContext {
		public List<MulContext> mul() {
			return getRuleContexts(MulContext.class);
		}
		public MulContext mul(int i) {
			return getRuleContext(MulContext.class,i);
		}
		public AddContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_add; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PA4Visitor ) return ((PA4Visitor<? extends T>)visitor).visitAdd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddContext add() throws RecognitionException {
		AddContext _localctx = new AddContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_add);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			mul();
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7 || _la==T__8) {
				{
				{
				setState(58);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(59);
				mul();
				}
				}
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MulContext extends ParserRuleContext {
		public List<UnaryContext> unary() {
			return getRuleContexts(UnaryContext.class);
		}
		public UnaryContext unary(int i) {
			return getRuleContext(UnaryContext.class,i);
		}
		public MulContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mul; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PA4Visitor ) return ((PA4Visitor<? extends T>)visitor).visitMul(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MulContext mul() throws RecognitionException {
		MulContext _localctx = new MulContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_mul);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			unary();
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9 || _la==T__10) {
				{
				{
				setState(66);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==T__10) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(67);
				unary();
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnaryContext extends ParserRuleContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public UnaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PA4Visitor ) return ((PA4Visitor<? extends T>)visitor).visitUnary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryContext unary() throws RecognitionException {
		UnaryContext _localctx = new UnaryContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_unary);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			primary();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(PA4Parser.INT, 0); }
		public TerminalNode ID() { return getToken(PA4Parser.ID, 0); }
		public FncallContext fncall() {
			return getRuleContext(FncallContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PA4Visitor ) return ((PA4Visitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_primary);
		try {
			setState(82);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				match(INT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(76);
				match(ID);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(77);
				fncall();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(78);
				match(T__0);
				setState(79);
				expr();
				setState(80);
				match(T__1);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FncallContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(PA4Parser.ID, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public FncallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fncall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PA4Visitor ) return ((PA4Visitor<? extends T>)visitor).visitFncall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FncallContext fncall() throws RecognitionException {
		FncallContext _localctx = new FncallContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_fncall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(ID);
			setState(85);
			match(T__0);
			setState(86);
			args();
			setState(87);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PA4Visitor ) return ((PA4Visitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_args);
		int _la;
		try {
			setState(98);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case T__0:
			case INT:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(90);
				expr();
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(91);
					match(T__4);
					setState(92);
					expr();
					}
					}
					setState(97);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u000ee\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0001\u0000\u0004\u0000\u0018"+
		"\b\u0000\u000b\u0000\f\u0000\u0019\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002(\b\u0002\n\u0002\f\u0002"+
		"+\t\u0002\u0003\u0002-\b\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003"+
		"\u00048\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005=\b\u0005"+
		"\n\u0005\f\u0005@\t\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006"+
		"E\b\u0006\n\u0006\f\u0006H\t\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\bS\b\b\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0005\n^\b"+
		"\n\n\n\f\na\t\n\u0003\nc\b\n\u0001\n\u0000\u0000\u000b\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0000\u0002\u0001\u0000\b\t\u0001"+
		"\u0000\n\u000bd\u0000\u0017\u0001\u0000\u0000\u0000\u0002\u001b\u0001"+
		"\u0000\u0000\u0000\u0004,\u0001\u0000\u0000\u0000\u0006.\u0001\u0000\u0000"+
		"\u0000\b7\u0001\u0000\u0000\u0000\n9\u0001\u0000\u0000\u0000\fA\u0001"+
		"\u0000\u0000\u0000\u000eI\u0001\u0000\u0000\u0000\u0010R\u0001\u0000\u0000"+
		"\u0000\u0012T\u0001\u0000\u0000\u0000\u0014b\u0001\u0000\u0000\u0000\u0016"+
		"\u0018\u0003\u0002\u0001\u0000\u0017\u0016\u0001\u0000\u0000\u0000\u0018"+
		"\u0019\u0001\u0000\u0000\u0000\u0019\u0017\u0001\u0000\u0000\u0000\u0019"+
		"\u001a\u0001\u0000\u0000\u0000\u001a\u0001\u0001\u0000\u0000\u0000\u001b"+
		"\u001c\u0005\r\u0000\u0000\u001c\u001d\u0005\u0001\u0000\u0000\u001d\u001e"+
		"\u0003\u0004\u0002\u0000\u001e\u001f\u0005\u0002\u0000\u0000\u001f \u0005"+
		"\u0003\u0000\u0000 !\u0003\u0006\u0003\u0000!\"\u0005\u0004\u0000\u0000"+
		"\"\u0003\u0001\u0000\u0000\u0000#-\u0001\u0000\u0000\u0000$)\u0005\r\u0000"+
		"\u0000%&\u0005\u0005\u0000\u0000&(\u0005\r\u0000\u0000\'%\u0001\u0000"+
		"\u0000\u0000(+\u0001\u0000\u0000\u0000)\'\u0001\u0000\u0000\u0000)*\u0001"+
		"\u0000\u0000\u0000*-\u0001\u0000\u0000\u0000+)\u0001\u0000\u0000\u0000"+
		",#\u0001\u0000\u0000\u0000,$\u0001\u0000\u0000\u0000-\u0005\u0001\u0000"+
		"\u0000\u0000./\u0003\b\u0004\u0000/\u0007\u0001\u0000\u0000\u000008\u0003"+
		"\n\u0005\u000012\u0003\n\u0005\u000023\u0005\u0006\u0000\u000034\u0003"+
		"\u0006\u0003\u000045\u0005\u0007\u0000\u000056\u0003\b\u0004\u000068\u0001"+
		"\u0000\u0000\u000070\u0001\u0000\u0000\u000071\u0001\u0000\u0000\u0000"+
		"8\t\u0001\u0000\u0000\u00009>\u0003\f\u0006\u0000:;\u0007\u0000\u0000"+
		"\u0000;=\u0003\f\u0006\u0000<:\u0001\u0000\u0000\u0000=@\u0001\u0000\u0000"+
		"\u0000><\u0001\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000?\u000b\u0001"+
		"\u0000\u0000\u0000@>\u0001\u0000\u0000\u0000AF\u0003\u000e\u0007\u0000"+
		"BC\u0007\u0001\u0000\u0000CE\u0003\u000e\u0007\u0000DB\u0001\u0000\u0000"+
		"\u0000EH\u0001\u0000\u0000\u0000FD\u0001\u0000\u0000\u0000FG\u0001\u0000"+
		"\u0000\u0000G\r\u0001\u0000\u0000\u0000HF\u0001\u0000\u0000\u0000IJ\u0003"+
		"\u0010\b\u0000J\u000f\u0001\u0000\u0000\u0000KS\u0005\f\u0000\u0000LS"+
		"\u0005\r\u0000\u0000MS\u0003\u0012\t\u0000NO\u0005\u0001\u0000\u0000O"+
		"P\u0003\u0006\u0003\u0000PQ\u0005\u0002\u0000\u0000QS\u0001\u0000\u0000"+
		"\u0000RK\u0001\u0000\u0000\u0000RL\u0001\u0000\u0000\u0000RM\u0001\u0000"+
		"\u0000\u0000RN\u0001\u0000\u0000\u0000S\u0011\u0001\u0000\u0000\u0000"+
		"TU\u0005\r\u0000\u0000UV\u0005\u0001\u0000\u0000VW\u0003\u0014\n\u0000"+
		"WX\u0005\u0002\u0000\u0000X\u0013\u0001\u0000\u0000\u0000Yc\u0001\u0000"+
		"\u0000\u0000Z_\u0003\u0006\u0003\u0000[\\\u0005\u0005\u0000\u0000\\^\u0003"+
		"\u0006\u0003\u0000][\u0001\u0000\u0000\u0000^a\u0001\u0000\u0000\u0000"+
		"_]\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`c\u0001\u0000\u0000"+
		"\u0000a_\u0001\u0000\u0000\u0000bY\u0001\u0000\u0000\u0000bZ\u0001\u0000"+
		"\u0000\u0000c\u0015\u0001\u0000\u0000\u0000\t\u0019),7>FR_b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}