// generated with ast extension for cup
// version 0.8
// 28/0/2020 0:55:17


package rs.ac.bg.etf.pp1.ast;

public class GreaterOp extends Relop {

    private String GtString;

    public GreaterOp (String GtString) {
        this.GtString=GtString;
    }

    public String getGtString() {
        return GtString;
    }

    public void setGtString(String GtString) {
        this.GtString=GtString;
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
        buffer.append("GreaterOp(\n");

        buffer.append(" "+tab+GtString);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GreaterOp]");
        return buffer.toString();
    }
}
