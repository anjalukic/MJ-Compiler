package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.FormalParamDecl;
import rs.ac.bg.etf.pp1.ast.FormalParamDeclBrackets;
import rs.ac.bg.etf.pp1.ast.FormalParamDeclNoBrackets;
import rs.ac.bg.etf.pp1.ast.MultipleConditionsAND;
import rs.ac.bg.etf.pp1.ast.MultipleConditionsOR;
import rs.ac.bg.etf.pp1.ast.SingleCondTerm;
import rs.ac.bg.etf.pp1.ast.VarDecl;
import rs.ac.bg.etf.pp1.ast.VarDeclBrackets;
import rs.ac.bg.etf.pp1.ast.VarDeclSingle;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class CounterVisitor extends VisitorAdaptor {

	protected int count;
	protected boolean orExists=false;
	
	public int getCount(){
		return count;
	}
	
	public boolean orExists(){
		return orExists;
	}
	
	public static class OrChecker extends CounterVisitor{
		
		public void visit(MultipleConditionsOR s){
			orExists=true;
		}
		
	}

	public static class FormParamCounter extends CounterVisitor{
	
		public void visit(FormalParamDeclNoBrackets formParamDecl){
			count++;
		}
		
		public void visit(FormalParamDeclBrackets formParamDecl){
			count++;
		}
	}
	
	public static class VarCounter extends CounterVisitor{
		
		public void visit(VarDeclSingle varDecl){
			count++;
		}
		
		public void visit(VarDeclBrackets varDecl){
			count++;
		}
	}
}
