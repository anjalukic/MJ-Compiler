package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;
import rs.ac.bg.etf.pp1.ast.*;

public class SemanticAnalyzer extends VisitorAdaptor {
	Obj currentMethod = null;
	Struct currType = null;
	boolean returnFound = false;
	boolean errorDetected = false;
	int nParams = 0;
	int nArgs = 0;
	boolean inFuncCall = false;
	int nVars;
	Stack<Struct> actualPars = new Stack<Struct>();
	Stack<Integer> actualParsNum = new Stack<Integer>();
	int forLevel = 0;
	Logger log = Logger.getLogger(getClass());

	// --------------------------LOG----------------------------------

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}

	// -------------------------VISIT----------------------------------

	public void visit(ProgramName progName) {
		progName.obj = TabExt.insert(Obj.Prog, progName.getProgramName(), TabExt.noType);
		TabExt.openScope();
	}

	public void visit(Program program) {
		nVars = TabExt.currentScope.getnVars();
		Obj mainFunc = TabExt.find("main");
		if (mainFunc == TabExt.noObj || mainFunc.getLevel() != 0 || mainFunc.getType() != TabExt.noType) {
			report_error("Greska : ne postoji main funkcija bez argumenata i sa povratnom vrednoscu tipa void! ", null);
		}
		TabExt.chainLocalSymbols(program.getProgramName().obj);
		TabExt.closeScope();
	}

	public void visit(Type type) {
		Obj typeNode = TabExt.find(type.getTypeName());
		if (typeNode == TabExt.noObj) {
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", null);
			type.struct = TabExt.noType;
		} else {
			if (Obj.Type == typeNode.getKind()) {
				type.struct = typeNode.getType();
				currType = typeNode.getType();
			} else {
				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
				type.struct = TabExt.noType;
			}
		}
	}

	public void visit(MethodTypeName methodTypeName) {
		currentMethod = TabExt.insert(Obj.Meth, methodTypeName.getMethName(), methodTypeName.getType().struct);
		methodTypeName.obj = currentMethod;
		TabExt.openScope();
		report_info("Obradjuje se funkcija " + methodTypeName.getMethName(), methodTypeName);
	}

	public void visit(TypeMethodDecl methodDecl) {
		if (!returnFound && currentMethod.getType() != TabExt.noType) {
			report_error("Semanticka greska na liniji " + methodDecl.getLine() + ": funkcija " + currentMethod.getName()
					+ " nema return iskaz!", null);
		}
		currentMethod.setLevel(nParams);
		nParams = 0;
		TabExt.chainLocalSymbols(currentMethod);
		TabExt.closeScope();

		returnFound = false;
		currentMethod = null;
	}

	public void visit(MethodVoidName methodTypeName) {
		currentMethod = TabExt.insert(Obj.Meth, methodTypeName.getMethName(), TabExt.noType);
		methodTypeName.obj = currentMethod;
		TabExt.openScope();
		report_info("Obradjuje se funkcija " + methodTypeName.getMethName(), methodTypeName);
	}

	public void visit(VoidMethodDecl methodDecl) {
		TabExt.chainLocalSymbols(currentMethod);
		TabExt.closeScope();
		currentMethod.setLevel(nParams);
		nParams = 0;
		currentMethod = null;

		returnFound = false;
	}

	public void visit(SimpleDesignator designator) {
		Obj obj = TabExt.find(designator.getDesignatorName());
		if (obj == TabExt.noObj) {
			report_error("Greska na liniji " + designator.getLine() + " : ime " + designator.getDesignatorName()
					+ " nije deklarisano! ", null);
		} else {
			if (obj.getKind() == Obj.Con)
				report_info("Pronadjena upotreba konstante " + designator.getDesignatorName() + " na liniji "
						+ designator.getLine(), null);
			else if (obj.getKind() == Obj.Var)
				report_info("Pronadjena upotreba promenljive " + designator.getDesignatorName() + " na liniji "
						+ designator.getLine(), null);
			else {
				if (obj.getKind() == Obj.Meth) {
					if (inFuncCall) {
						actualParsNum.push(nArgs);
					}
					nArgs = 0;
					inFuncCall = true;
				}
			}
		}
		designator.obj = obj;
	}

	public void visit(DesignatorArray designator) {
		Obj obj = TabExt.find(designator.getDesignatorName());
		if (obj == TabExt.noObj) {
			report_error("Greska na liniji " + designator.getLine() + " : ime " + designator.getDesignatorName()
					+ " nije deklarisano! ", null);
			designator.obj = obj;
		} else {
			if (obj.getType().getKind() != Struct.Array) {
				report_error("Greska na liniji " + designator.getLine() + " : promenljiva "
						+ designator.getDesignatorName() + " nije niz! ", null);
				designator.obj = obj;
			} else {
				if (designator.getExpr().struct != TabExt.intType) {
					report_error("Greska na liniji " + designator.getLine() + " : indeks u promenljivoj "
							+ designator.getDesignatorName() + " nije int tipa! ", null);
					designator.obj = obj;
				} else {
					report_info("Pronadjena upotreba promenljive (pristup nizu) " + designator.getDesignatorName()
							+ " na liniji " + designator.getLine(), null);
					designator.obj = TabExt.insert(Obj.Elem,
							designator.getDesignatorName() + "[" + designator.getLine() + "]",
							obj.getType().getElemType());// vrednost indeksa?
				}
			}
		}
	}

	public void visit(FuncCallFactor funcCall) {
		Obj func = funcCall.getDesignator().obj;
		if (Obj.Meth == func.getKind()) {
			report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + funcCall.getLine(), null);
			funcCall.struct = func.getType();

			// provera argumenata
			int nFuncPars = func.getLevel();
			int nFuncArgs = nArgs;
			if (nFuncArgs != nFuncPars) {
				report_error("Greska na liniji " + funcCall.getLine() + " : pogresan broj argumenata u funkciji "
						+ func.getName(), null);
				for (int i = 0; i < nFuncArgs; i++)
					actualPars.pop();
			} else {
				Collection<Obj> locals = func.getLocalSymbols();
				if (!locals.isEmpty() && nFuncArgs > 0) {
					if (func.getName() == "len") {
						Struct arg = actualPars.pop();
						if (arg.getKind() != Struct.Array) {
							report_error("Greska na liniji " + funcCall.getLine()
									+ " : argument pogresnog tipa u funkciji " + func.getName(), null);
						} else {
							report_info("Poklapaju se tipovi argumenta i parametra " + func.getName() + " na liniji "
									+ funcCall.getLine(), null);
						}
					}
					else {
						ArrayList<Obj> params = new ArrayList<Obj>();
						locals.forEach(i -> params.add(i));
						ListIterator<Obj> iter = params.listIterator(nFuncArgs);
						while (iter.hasPrevious()) {
							Struct arg = actualPars.pop();
							Obj param = iter.previous();
							if (!param.getType().compatibleWith(arg)) {
								report_error("Greska na liniji " + funcCall.getLine()
										+ " : argument pogresnog tipa u funkciji " + func.getName(), null);
							} else {
								report_info("Poklapaju se tipovi argumenta i parametra " + func.getName() + " na liniji "
										+ funcCall.getLine(), null);
	
							}
						}
					}
				}
			}
			if (!actualParsNum.empty()) {
				nArgs = actualParsNum.pop();
				report_info("Unutrasnji poziv sa " + nArgs + " argumenata na steku " + func.getName() + " na liniji "
						+ funcCall.getLine(), null);
			} else {
				inFuncCall = false;
				nArgs = 0;
				report_info("Poslednji poziv obradjen " + func.getName() + " na liniji " + funcCall.getLine(), null);
			}
		} else {
			report_error("Greska na liniji " + funcCall.getLine() + " : ime " + func.getName() + " nije funkcija!",
					null);
			funcCall.struct = TabExt.noType;
		}
	}

	public void visit(Var var) {
		var.struct = var.getDesignator().obj.getType();
	}

	public void visit(NumConst cnst) {
		cnst.struct = TabExt.intType;
	}

	public void visit(CharConst cnst) {
		cnst.struct = TabExt.charType;
	}

	public void visit(BoolConst cnst) {
		cnst.struct = TabExt.boolType;
	}

	public void visit(NewType newType) {
		newType.struct = newType.getType().struct;
		report_error("Greska na liniji " + newType.getLine() + " : ne postoje klasni tipovi! ", null);
	}

	public void visit(NewArray newType) {
		newType.struct = new Struct(Struct.Array, newType.getType().struct);
		if (newType.getExpr().struct != TabExt.intType) {
			report_error("Greska na liniji " + newType.getLine() + " : izraz nije int tipa! ", null);
		} else {
			report_info("Pronadjena alokacija niza na liniji " + newType.getLine(), null);
		}

	}

	public void visit(NestedExpr nestedExpr) {
		nestedExpr.struct = nestedExpr.getExpr().struct;
	}

	public void visit(NegTerm term) {
		term.struct = term.getTerm().struct;
		if (term.struct != TabExt.intType) {
			report_error("Greska na liniji " + term.getLine() + " : tip nekompatibilan sa minusom", null);
		}
	}

	public void visit(SingleTerm term) {
		term.struct = term.getFactor().struct;
	}

	public void visit(MulTerm term) {
		Struct te = term.getTerm().struct;
		Struct t = term.getFactor().struct;
		if (te.equals(t) && te == TabExt.intType) {
			term.struct = te;
		} else {
			report_error(
					"Greska na liniji " + term.getLine() + " : nekompatibilni tipovi u izrazu za mnozenje i deljenje",
					null);
			term.struct = TabExt.noType;
		}
	}

	public void visit(SingleExpr expr) {
		expr.struct = expr.getTerm().struct;
	}

	public void visit(SingleNegExpr expr) {
		expr.struct = expr.getNegTerm().struct;
	}

	public void visit(AddExpr addExpr) {
		Struct te = addExpr.getExpr().struct;
		Struct t = addExpr.getTerm().struct;
		if (te.equals(t) && te == TabExt.intType) {
			addExpr.struct = te;
		} else {
			report_error("Greska na liniji " + addExpr.getLine() + " : nekompatibilni tipovi u izrazu za sabiranje",
					null);
			addExpr.struct = TabExt.noType;
		}
	}

	public void visit(ReturnExpr returnExpr) {
		if (currentMethod==null) {
			report_error("Greska na liniji " + returnExpr.getLine() + " : "
					+ "return van funkcije", null);
		} else {
			returnFound = true;
			Struct currMethType = currentMethod.getType();
			if (!currMethType.compatibleWith(returnExpr.getExpr().struct)) {
				report_error("Greska na liniji " + returnExpr.getLine() + " : "
						+ "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije "
						+ currentMethod.getName(), null);
			}
		}
	}

	public void visit(ReturnNoExpr ret) {
		if (currentMethod==null) {
			report_error("Greska na liniji " + ret.getLine() + " : "
					+ "return van funkcije", null);
		} else {
			Struct currMethType = currentMethod.getType();
			if (currMethType != TabExt.noType) {
				report_error("Greska na liniji " + ret.getLine() + " : "
						+ "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije "
						+ currentMethod.getName(), null);
			}
		}
	}

	public void visit(Assignment assignment) {
		if (!assignment.getExpr().struct.assignableTo(assignment.getDesignator().obj.getType()))
			report_error(
					"Greska na liniji " + assignment.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ",
					null);
	}

	public void visit(FunctionCallStmt funcCall) {
		Obj func = funcCall.getDesignator().obj;
    	if(Obj.Meth == func.getKind()){
			report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + funcCall.getLine(), null);
			
			//provera argumenata
			int nFuncPars = func.getLevel();
			int nFuncArgs = nArgs;
			if (nFuncArgs!=nFuncPars) {
    			report_error("Greska na liniji " + funcCall.getLine()+ " : pogresan broj argumenata u funkciji "+func.getName(), null);
    			for (int i=0; i<nFuncArgs; i++) actualPars.pop();
			} else { 			
				Collection<Obj> locals = func.getLocalSymbols();
				// provera tipa argumenata i parametara fje
				if (!locals.isEmpty() && nFuncArgs>0) {
					if (func.getName() == "len"){
						Struct arg = actualPars.pop();
						if (arg.getKind() != Struct.Array) {
							report_error("Greska na liniji " + funcCall.getLine()
									+ " : argument pogresnog tipa u funkciji " + func.getName(), null);
						} else {
							report_info("Poklapaju se tipovi argumenta i parametra " + func.getName() + " na liniji "
									+ funcCall.getLine(), null);
						}
					}
					else {
						ArrayList<Obj> params = new ArrayList<Obj>();
						locals.forEach(i -> params.add(i));
						ListIterator<Obj> iter = params.listIterator(nFuncArgs);
						while (iter.hasPrevious()) {
							Struct arg = actualPars.pop();
							Obj param = iter.previous();
							if (!param.getType().compatibleWith(arg)) {
								report_error("Greska na liniji " + funcCall.getLine()+ " : argument pogresnog tipa u funkciji "+func.getName(), null);
							} else {
								report_info("Poklapaju se tipovi argumenta i parametra " + func.getName() + " na liniji " + funcCall.getLine(), null);
		
							}
						}
					}
				}
			}
			inFuncCall = false;
			nArgs = 0;
			report_info("Naredba poziva funkcije " + func.getName() + " obradjena na liniji " + funcCall.getLine(), null);
    	}else{
			report_error("Greska na liniji " + funcCall.getLine()+" : ime " + func.getName() + " nije funkcija!", null);
    	}
	}

	public void visit(Increment incr) {
		if (incr.getDesignator().obj.getType() != TabExt.intType)
			report_error("Greska na liniji " + incr.getLine() + " : " + "inkrementiranje ne-int vrednosti! ", null);
	}

	public void visit(Decrement incr) {
		if (incr.getDesignator().obj.getType() != TabExt.intType)
			report_error("Greska na liniji " + incr.getLine() + " : " + "dekrementiranje ne-int vrednosti! ", null);
	}

	public void visit(VarDeclSingle varDecl) {
		Obj obj = TabExt.currentScope().findSymbol(varDecl.getVarName());
		if (obj == null) {
			report_info("Deklarisana promenljiva " + varDecl.getVarName(), varDecl);
			Obj varNode = TabExt.insert(Obj.Var, varDecl.getVarName(), currType);
		} else {
			report_error("Greska na liniji " + varDecl.getLine() + " : " + "vise puta deklarisana promenljiva! ", null);
		}
	}

	public void visit(VarDeclBrackets varDecl) {
		Obj obj = TabExt.currentScope().findSymbol(varDecl.getVarName());
		if (obj == null) {
			report_info("Deklarisana niz-promenljiva " + varDecl.getVarName(), varDecl);
			Obj varNode = TabExt.insert(Obj.Var, varDecl.getVarName(), new Struct(Struct.Array, currType));
		} else {
			report_error("Greska na liniji " + varDecl.getLine() + " : " + "vise puta deklarisana promenljiva! ", null);
		}
	}

	public void visit(ConstDeclNumber cnstDecl) {
		if (currType != TabExt.intType)
			report_error("Greska na liniji " + cnstDecl.getLine() + " : "
					+ "deklarisana konstanta nije odgovarajuceg tipa! ", null);
		else {
			Obj obj = TabExt.find(cnstDecl.getConstName());
			if (obj == TabExt.noObj) {
				report_info("Deklarisana konstanta " + cnstDecl.getConstName(), cnstDecl);
			} else {
				report_error("Greska na liniji " + cnstDecl.getLine() + " : " + "vise puta deklarisana konstanta! ",
						null);
			}
		}
		Obj varNode = TabExt.insert(Obj.Con, cnstDecl.getConstName(), currType);
	}

	public void visit(ConstDeclBool cnstDecl) {
		if (currType != TabExt.boolType)
			report_error("Greska na liniji " + cnstDecl.getLine() + " : "
					+ "deklarisana konstanta nije odgovarajuceg tipa! ", null);
		else {
			Obj obj = TabExt.find(cnstDecl.getConstName());
			if (obj == TabExt.noObj) {
				report_info("Deklarisana konstanta " + cnstDecl.getConstName(), cnstDecl);
			} else {
				report_error("Greska na liniji " + cnstDecl.getLine() + " : " + "vise puta deklarisana konstanta! ",
						null);
			}
		}
		Obj varNode = TabExt.insert(Obj.Con, cnstDecl.getConstName(), currType);
	}

	public void visit(ConstDeclChar cnstDecl) {
		if (currType != TabExt.charType)
			report_error("Greska na liniji " + cnstDecl.getLine() + " : "
					+ "deklarisana konstanta nije odgovarajuceg tipa! ", null);
		else {
			Obj obj = TabExt.find(cnstDecl.getConstName());
			if (obj == TabExt.noObj) {
				report_info("Deklarisana konstanta " + cnstDecl.getConstName(), cnstDecl);
			} else {
				report_error("Greska na liniji " + cnstDecl.getLine() + " : " + "vise puta deklarisana konstanta! ",
						null);
			}
		}
		Obj varNode = TabExt.insert(Obj.Con, cnstDecl.getConstName(), currType);
	}

	public void visit(FormalParamDeclNoBrackets fp) {
		nParams++;
		report_info("Deklarisan formalni parametar " + fp.getVarName(), fp);
		Obj varNode = TabExt.insert(Obj.Var, fp.getVarName(), currType);
	}

	public void visit(FormalParamDeclBrackets fp) {
		nParams++;
		report_info("Deklarisan formalni parametar-niz " + fp.getVarName(), fp);
		Obj varNode = TabExt.insert(Obj.Var, fp.getVarName(), new Struct(Struct.Array, currType));
	}

	public void visit(RelCondFact cond) {
		if (cond.getRelop() instanceof EqualOp || cond.getRelop() instanceof DifferentOp) {
			if (!cond.getExpr().struct.equals(cond.getExpr1().struct))
				report_error(
						"Greska na liniji " + cond.getLine() + " : " + "nekompatibilni tipovi u uslovu for petlje! ",
						null);
		} else {
			if (!(cond.getExpr().struct.equals(cond.getExpr1().struct) && cond.getExpr().struct == TabExt.intType))// equal
																													// i
																													// different
																													// moze
																													// i
																													// char,
																													// bool
				report_error(
						"Greska na liniji " + cond.getLine() + " : " + "nekompatibilni tipovi u uslovu for petlje! ",
						null);
		}
	}

	public void visit(SingleCondFact cond) {
		if (!cond.getExpr().struct.equals(TabExt.boolType))
			report_error("Greska na liniji " + cond.getLine() + " : tip u uslovu for petlje mora biti bool! ", null);
	}

	public void visit(ForStatement fs) {
		report_info("Zavrsila for petlja ", fs);
		forLevel--;
	}
	
	public void visit(ForT fort) {
		forLevel++;
		report_info("Pocela for petlja ", fort);
	}
	
	public void visit(BreakStatement bs) {
		if (forLevel<=0) {
			report_error("Greska : break van for petlje! ", null);
		}
	}
	
	public void visit(ContinueStatement bs) {
		if (forLevel<=0) {
			report_error("Greska : continue van for petlje! ", null);
		}
	}

	public void visit(ActualParams ap) {
		actualPars.push(ap.getExpr().struct); // na steku argumenti unazad (poslednji je na vrhu)
		report_info("Argument ubacen na stek ", ap);
		nArgs++;

	}

	public void visit(ActualParam ap) {
		actualPars.push(ap.getExpr().struct);
		report_info("Argument ubacen na stek ", ap);
		nArgs++;

	}
	
	public void visit(PrintStmt ps) {
		if (!(ps.getExpr().struct==TabExt.intType || 
				ps.getExpr().struct==TabExt.boolType || ps.getExpr().struct==TabExt.charType)) {
			report_error("Greska na liniji " + ps.getLine() + " : neodgovarajuci tip za print! ", null);
		}
	}
	
	public void visit(ReadStmt ps) {
		if (!(ps.getDesignator().obj.getType()==TabExt.intType || 
				ps.getDesignator().obj.getType()==TabExt.boolType ||ps.getDesignator().obj.getType()==TabExt.charType)) {
			report_error("Greska na liniji " + ps.getLine() + " : neodgovarajuci tip za read! ", null);
		}
	}

	// -------------------VISIT-------------------------

	public boolean passed() {
		return !errorDetected;
	}

}
