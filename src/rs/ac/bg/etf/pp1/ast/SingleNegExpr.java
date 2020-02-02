// generated with ast extension for cup
// version 0.8
// 2/1/2020 1:19:26


package rs.ac.bg.etf.pp1.ast;

public class SingleNegExpr extends Expr {

    private NegTerm NegTerm;

    public SingleNegExpr (NegTerm NegTerm) {
        this.NegTerm=NegTerm;
        if(NegTerm!=null) NegTerm.setParent(this);
    }

    public NegTerm getNegTerm() {
        return NegTerm;
    }

    public void setNegTerm(NegTerm NegTerm) {
        this.NegTerm=NegTerm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(NegTerm!=null) NegTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NegTerm!=null) NegTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NegTerm!=null) NegTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleNegExpr(\n");

        if(NegTerm!=null)
            buffer.append(NegTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleNegExpr]");
        return buffer.toString();
    }
}
