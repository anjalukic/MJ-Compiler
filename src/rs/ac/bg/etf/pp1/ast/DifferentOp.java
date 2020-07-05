// generated with ast extension for cup
// version 0.8
// 22/5/2020 16:56:38


package rs.ac.bg.etf.pp1.ast;

public class DifferentOp extends Relop {

    private String DiffString;

    public DifferentOp (String DiffString) {
        this.DiffString=DiffString;
    }

    public String getDiffString() {
        return DiffString;
    }

    public void setDiffString(String DiffString) {
        this.DiffString=DiffString;
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
        buffer.append("DifferentOp(\n");

        buffer.append(" "+tab+DiffString);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DifferentOp]");
        return buffer.toString();
    }
}
