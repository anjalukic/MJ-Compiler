// generated with ast extension for cup
// version 0.8
// 2/1/2020 1:19:26


package rs.ac.bg.etf.pp1.ast;

public class LessEqualOp extends Relop {

    private String LteString;

    public LessEqualOp (String LteString) {
        this.LteString=LteString;
    }

    public String getLteString() {
        return LteString;
    }

    public void setLteString(String LteString) {
        this.LteString=LteString;
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
        buffer.append("LessEqualOp(\n");

        buffer.append(" "+tab+LteString);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [LessEqualOp]");
        return buffer.toString();
    }
}
