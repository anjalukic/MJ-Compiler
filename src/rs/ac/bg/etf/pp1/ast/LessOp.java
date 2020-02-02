// generated with ast extension for cup
// version 0.8
// 2/1/2020 1:19:26


package rs.ac.bg.etf.pp1.ast;

public class LessOp extends Relop {

    private String LtString;

    public LessOp (String LtString) {
        this.LtString=LtString;
    }

    public String getLtString() {
        return LtString;
    }

    public void setLtString(String LtString) {
        this.LtString=LtString;
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
        buffer.append("LessOp(\n");

        buffer.append(" "+tab+LtString);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [LessOp]");
        return buffer.toString();
    }
}
