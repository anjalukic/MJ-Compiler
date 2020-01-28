package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;

public class RuleVisitor extends VisitorAdaptor{

	int printCallCount = 0;
	int varDeclCount = 0;
	int exprCount = 0;
	int formParsCount = 0;
	int forCount = 0;
	int uIf = 0;
	int mIf = 0;
	
	Logger log = Logger.getLogger(getClass());

	public void visit(VarDeclSingle vardecl){
		varDeclCount++;
	}
	
	public void visit(AddExpr expr){
		exprCount++;
	}
	
	public void visit(FormalParamDeclNoBrackets f){
		formParsCount++;
	}
	
    public void visit(PrintStmt print) {
		printCallCount++;
	}
    
    public void visit(MatchedStatement m) {
		mIf++;
	}
    public void visit(UnmatchedStatement u) {
		uIf++;
	}
    public void visit(ForStatement f) {
		forCount++;
	}

}
