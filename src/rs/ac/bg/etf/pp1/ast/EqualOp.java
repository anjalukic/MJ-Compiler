// generated with ast extension for cup
// version 0.8
// 28/0/2020 0:55:17


package rs.ac.bg.etf.pp1.ast;

public class EqualOp extends Relop {

    private String EqualString;

    public EqualOp (String EqualString) {
        this.EqualString=EqualString;
    }

    public String getEqualString() {
        return EqualString;
    }

    public void setEqualString(String EqualString) {
        this.EqualString=EqualString;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EqualOp(\n");

        buffer.append(" "+tab+EqualString);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EqualOp]");
        return buffer.toString();
    }
}
