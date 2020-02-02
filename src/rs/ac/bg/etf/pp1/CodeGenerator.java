package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;
	
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
		Code.put(Code.inc);
		Code.put(assignment.getDesignator().obj.getAdr());
		Code.put(1);
	}
	
	public void visit(Decrement assignment) {
		Code.put(Code.inc);
		Code.put(assignment.getDesignator().obj.getAdr());
		Code.put(-1);
	}
	
	public void visit(SimpleDesignator designator) {
		SyntaxNode parent = designator.getParent();
		if (Assignment.class != parent.getClass() && FuncCallFactor.class != parent.getClass()
				&& FunctionCallStmt.class != parent.getClass() && Increment.class != parent.getClass()
				&& Decrement.class != parent.getClass()) {
			Code.load(designator.obj);
		}
	}
	
	public void visit(DesignatorArray designator) {
		SyntaxNode parent = designator.getParent();
		if (Assignment.class != parent.getClass() && FuncCallFactor.class != parent.getClass()
				&& FunctionCallStmt.class != parent.getClass() && Increment.class != parent.getClass()
				&& Decrement.class != parent.getClass()) {
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
	
	public void visit(ConstDeclNumber cnstDecl) {
		//Obj obj = TabExt.find(cnstDecl.getConstName());
		//obj.setAdr(cnstDecl.getN1());
	}
	
	public void visit(ConstDeclBool cnstDecl) {
		//int boolVal = cnstDecl.getB1().equals("true")?1:0;
		//Obj obj = TabExt.find(cnstDecl.getConstName());
		//obj.setAdr(boolVal);
	}
	
	public void visit(ConstDeclChar cnstDecl) {
		//Obj obj = TabExt.find(cnstDecl.getConstName());
		//obj.setAdr(cnstDecl.getC1());
	}
	
}
