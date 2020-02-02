package rs.ac.bg.etf.pp1;

import java.util.Stack;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.OrChecker;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;
	
	private Stack<Integer> endIfAddresses = new Stack<Integer>();
	private Stack<Integer> endIfAddressesPopCount = new Stack<Integer>();
	private Stack<Integer> startIfAddresses = new Stack<Integer>();
	private Stack<Integer> elseAddresses = new Stack<Integer>();
	//private Stack<Integer> startForAddresses = new Stack<Integer>();
	private int startForAddress;
	private Stack<Integer> endForAddresses = new Stack<Integer>();
	//private Stack<Integer> skipIncrAddresses = new Stack<Integer>();		
	private int skipIncrAddress;
	private Stack<Integer> incrAddressesToFillEndWith = new Stack<Integer>();
	private Stack<Integer> breakAddresses = new Stack<Integer>();
	private Stack<Integer> breakCount = new Stack<Integer>();
	private boolean orExists;
	private int notLastOrCounter = 0;
	
	public int getMainPc() {
		return mainPc;
	}
	
	public void visit(PrintStmt printStmt) {
		if (printStmt.getExpr().struct == TabExt.intType) {
			Code.loadConst(5);
			Code.put(Code.print);
		} else if (printStmt.getExpr().struct == TabExt.charType){
			Code.loadConst(1);
			Code.put(Code.bprint);
		} else {
			Code.loadConst(5);
			Code.put(Code.print);
		}
	}
	
	public void visit(ReadStmt readStmt) {
		if (readStmt.getDesignator().obj.getType()==TabExt.charType) {
			Code.put(Code.bread);
		}else {
			Code.put(Code.read);
		}
		Code.store(readStmt.getDesignator().obj);
	}
	
	public void visit(NumConst cnst) {
		Obj con =TabExt.insert(Obj.Con, "$num", cnst.struct);
		con.setLevel(0);
		con.setAdr(cnst.getN1());
		Code.load(con);
	}
	
	public void visit(CharConst cnst) {
		Obj con = new Obj(Obj.Con, "$char", cnst.struct, cnst.getC1(), 0);
		Code.load(con);
	}
	
	public void visit(BoolConst cnst) {
		int boolVal = cnst.getB1().equals("true")?1:0;
		Obj con = new Obj(Obj.Con, "$bool", cnst.struct, boolVal, 0);
		Code.load(con);
	}
	
	public void visit(MethodTypeName methodTypeName) {
		methodTypeName.obj.setAdr(Code.pc);
		// Collect arguments and local variables
		SyntaxNode methodNode = methodTypeName.getParent();

		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);

		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);

		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());

	}
	
	public void visit(MethodVoidName methodTypeName) {
		if ("main".equalsIgnoreCase(methodTypeName.getMethName())) {
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);
		// Collect arguments and local variables
		SyntaxNode methodNode = methodTypeName.getParent();

		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);

		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);

		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());

	}

	public void visit(TypeMethodDecl methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(VoidMethodDecl methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	
	public void visit(Assignment assignment) {
		Code.store(assignment.getDesignator().obj);
		
	}
	
	public void visit(Increment assignment) {
		if (assignment.getDesignator().obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
			Code.load(assignment.getDesignator().obj);
		}
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(assignment.getDesignator().obj);
		/*
		Code.put(Code.inc);
		Code.put(assignment.getDesignator().obj.getAdr());
		Code.put(1);
		*/
	}
	
	public void visit(Decrement assignment) {
		if (assignment.getDesignator().obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
			Code.load(assignment.getDesignator().obj);
		}
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(assignment.getDesignator().obj);
		/*
		Code.put(Code.inc);
		Code.put(assignment.getDesignator().obj.getAdr());
		Code.put(-1);
		*/
	}
	
	public void visit(SimpleDesignator designator) {
		SyntaxNode parent = designator.getParent();
		if (Assignment.class != parent.getClass() && FuncCallFactor.class != parent.getClass()
				&& FunctionCallStmt.class != parent.getClass()
			&& ReadStmt.class!=parent.getClass()) {
			Code.load(designator.obj);
		}
	}
	
	public void visit(DesignatorArray designator) {
		SyntaxNode parent = designator.getParent();
		if (Assignment.class != parent.getClass() && FuncCallFactor.class != parent.getClass()
				&& FunctionCallStmt.class != parent.getClass()
			&& Increment.class != parent.getClass() && Decrement.class != parent.getClass()
			&& ReadStmt.class!=parent.getClass()) {
			Code.load(designator.obj);
		}	
		
	}
	
	public void visit(FuncCallFactor funcCall) {
		Obj functionObj = funcCall.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		if (!isStandardMethod(functionObj)) {
			Code.put(Code.call);
			Code.put2(offset);
		}
	}
	
	private boolean isStandardMethod(Obj fnc) {
		if (fnc.getName().equals("len")) {
			Code.put(Code.arraylength);
			return true;
		}else if (fnc.getName().equals("chr")) {
			//?
			return true;
		}else if (fnc.getName().equals("ord")) {
			//?
			return true;
		}
		return false;
	}

	public void visit(FunctionCallStmt procCall) {
		Obj functionObj = procCall.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		if (!isStandardMethod(functionObj)) {
			Code.put(Code.call);
			Code.put2(offset);
		}
		if (procCall.getDesignator().obj.getType() != TabExt.noType) {
			Code.put(Code.pop);
		}
	}

	public void visit(ReturnExpr returnExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	public void visit(ReturnNoExpr returnNoExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	public void visit(AddExpr addExpr) {
		if (addExpr.getAddop() instanceof MinusOp) {
			Code.put(Code.sub);
		} else {
			Code.put(Code.add);
		}
	}
	
	public void visit(MulTerm mulTerm) {
		if (mulTerm.getMulop() instanceof DivOp) {
			Code.put(Code.div);
		} else if (mulTerm.getMulop() instanceof ModOp){
			Code.put(Code.rem);
		} else {
			Code.put(Code.mul);
		}
	}
	
	public void visit(NegTerm neg) {
		Code.put(Code.neg);
	}
	
	public void visit(NewArray newArray) {
		Code.put(Code.newarray);
		if(newArray.getType().struct == Tab.charType)
			Code.put(0);//element size = 1 B
		else 
			Code.put(1);//element size = 1 word
	}
	/*
	 //CONSTANT INIT
	public void visit(ConstDeclNumber cnstDecl) {
		Obj obj = TabExt.find(cnstDecl.getConstName());
		obj.setAdr(cnstDecl.getN1());
	}
	
	public void visit(ConstDeclBool cnstDecl) {
		int boolVal = cnstDecl.getB1().equals("true")?1:0;
		Obj obj = TabExt.find(cnstDecl.getConstName());
		obj.setAdr(boolVal);
	}
	
	public void visit(ConstDeclChar cnstDecl) {
		Obj obj = TabExt.find(cnstDecl.getConstName());
		obj.setAdr(cnstDecl.getC1());
	}
	*/
	
	private int getRelopKind(Relop relop) {
		if (relop instanceof EqualOp) {
			return Code.eq;
		} else if (relop instanceof DifferentOp){
			return Code.ne;
		} else if (relop instanceof GreaterOp){
			return Code.gt;
		}else if (relop instanceof GreaterEqualOp){
			return Code.ge;
		}else if (relop instanceof LessOp){
			return Code.lt;
		}else {//LessEqualOp
			return Code.le;
		}
	}
	
	public void visit(IfT ift) {
		endIfAddressesPopCount.push(0);
		OrChecker orCnt = new OrChecker();
		if (ift.getParent() instanceof MatchedStatement) {
			((MatchedStatement)(ift.getParent())).getCondition().traverseTopDown(orCnt);
		}else {
			((UnmatchedStatement)(ift.getParent())).getCondition().traverseTopDown(orCnt);
		}
		orExists = orCnt.orExists();
	}
	
	public void visit(RParenT rp) {
		for (int i=0; i<notLastOrCounter;i++) {
			int patchAdr = startIfAddresses.pop();
			Code.fixup(patchAdr);
		}
		notLastOrCounter = 0;
	}
	
	public void visit(SingleCondTerm sct) {
		if (!orExists || sct.getParent().getParent() instanceof MatchedStatement ||
				sct.getParent().getParent() instanceof UnmatchedStatement || 
				sct.getParent().getParent() instanceof OptionalCondition){//if not part of OR or if last in OR
			if (sct.getCondFact() instanceof RelCondFact) {//if relop
				RelCondFact temp = (RelCondFact) sct.getCondFact();
				Code.putFalseJump(getRelopKind(temp.getRelop()),0);
				endIfAddresses.push(Code.pc-2);
				endIfAddressesPopCount.push(endIfAddressesPopCount.pop()+1);
			} else {//if boolean
				Obj temp = new Obj(Obj.Con, "$true", TabExt.boolType, 1, 0);
				Code.load(temp);
				Code.putFalseJump(Code.eq, 0);
				endIfAddresses.push(Code.pc-2);
				endIfAddressesPopCount.push(endIfAddressesPopCount.pop()+1);
			}
		}else {//in OR but not last
			if (sct.getCondFact() instanceof RelCondFact) {//if relop
				RelCondFact temp = (RelCondFact) sct.getCondFact();
				Code.putFalseJump(Code.inverse[getRelopKind(temp.getRelop())],0);
				startIfAddresses.push(Code.pc-2);
				notLastOrCounter++;
			} else {//if boolean
				Obj temp = new Obj(Obj.Con, "$true", TabExt.boolType, 1, 0);
				Code.load(temp);
				Code.putFalseJump(Code.inverse[Code.eq], 0);
				startIfAddresses.push(Code.pc-2);
				notLastOrCounter++;
				
			}
		}
		
	}
	
	public void visit(MultipleConditionsAND sct) {
		if (!orExists || sct.getParent().getParent() instanceof MatchedStatement ||
				sct.getParent().getParent() instanceof UnmatchedStatement || 
				sct.getParent().getParent() instanceof OptionalCondition){//if not part of OR or if last in OR
			if (sct.getCondFact() instanceof RelCondFact) {//if relop
				RelCondFact temp = (RelCondFact) sct.getCondFact();
				Code.putFalseJump(getRelopKind(temp.getRelop()),0);
				endIfAddresses.push(Code.pc-2);
				endIfAddressesPopCount.push(endIfAddressesPopCount.pop()+1);
			} else {//if boolean
				Obj temp = new Obj(Obj.Con, "$true", TabExt.boolType, 1, 0);
				Code.load(temp);
				Code.putFalseJump(Code.eq, 0);
				endIfAddresses.push(Code.pc-2);
				endIfAddressesPopCount.push(endIfAddressesPopCount.pop()+1);
			}
		}else {//in OR but not last
			if (sct.getCondFact() instanceof RelCondFact) {//if relop
				RelCondFact temp = (RelCondFact) sct.getCondFact();
				Code.putFalseJump(Code.inverse[getRelopKind(temp.getRelop())],0);
				startIfAddresses.push(Code.pc-2);
				notLastOrCounter++;
			} else {//if boolean
				Obj temp = new Obj(Obj.Con, "$true", TabExt.boolType, 1, 0);
				Code.load(temp);
				Code.putFalseJump(Code.inverse[Code.eq], 0);
				startIfAddresses.push(Code.pc-2);
				notLastOrCounter++;
			}
		}
	}
	
	public void visit(UnmatchedStatement unIf) {
		int border = endIfAddressesPopCount.pop();
		for (int i=0; i<border; i++) {
			int patchAdr = endIfAddresses.pop();
			Code.fixup(patchAdr);
		}
	}
	
	public void visit(ElseT mIf) {
		Code.putJump(0);
		elseAddresses.push(Code.pc-2);
		
		int border = endIfAddressesPopCount.pop();
		for (int i=0; i<border; i++) {
			int patchAdr = endIfAddresses.pop();
			Code.fixup(patchAdr);
		}
	}
	
	public void visit(MatchedStatement ms) {
		int patchAdr = elseAddresses.pop();
		Code.fixup(patchAdr);
	}
	
	public void visit(SemiT s) {
		//startForAddresses.push(Code.pc);
		startForAddress = Code.pc;
		endIfAddressesPopCount.push(0);
		OrChecker orCnt = new OrChecker();
		SyntaxNode parent = s.getParent();
		if (((ForStatement)parent).getConditionOptional() instanceof OptionalCondition) {
			((OptionalCondition)((ForStatement)parent).getConditionOptional()).getCondition().traverseTopDown(orCnt);
			orExists = orCnt.orExists();
		}
	}
	
	public void visit(SemiTT s) {
		Code.putJump(0);
		skipIncrAddress = Code.pc-2;
		incrAddressesToFillEndWith.push(Code.pc);
		
	}
	
	public void visit(RParenTT rp) {
		//if not first loop jump to condition after increment
		Code.putJump(startForAddress);
		//patch skipping increment
		Code.fixup(skipIncrAddress);
		//patch conditions if there's OR
		for (int i=0; i<notLastOrCounter;i++) {
			int patchAdr = startIfAddresses.pop();
			Code.fixup(patchAdr);
		}
		notLastOrCounter = 0;
	}
	
	public void visit(ForStatement fs) {
		//jump to increment in for loop
		Code.putJump(incrAddressesToFillEndWith.pop());
		//fix condition exits
		int border = endIfAddressesPopCount.pop();
		for (int i=0; i<border; i++) {
			int patchAdr = endIfAddresses.pop();
			Code.fixup(patchAdr);
		}
		border = breakCount.pop();
		for (int i=0; i<border; i++) {
			int patchAdr =breakAddresses.pop();
			Code.fixup(patchAdr);
		}
	}
	
	public void visit(ContinueStatement cs) {
		Code.putJump(incrAddressesToFillEndWith.peek());
	}
	
	public void visit(BreakStatement cs) {
		Code.putJump(0);
		breakAddresses.push(Code.pc-2);
		breakCount.push(breakCount.pop()+1);
	}
	
	public void visit(ForT f) {
		breakCount.push(0);
	}
	
	
}
