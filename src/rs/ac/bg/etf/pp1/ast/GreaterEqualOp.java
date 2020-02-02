// generated with ast extension for cup
// version 0.8
// 2/1/2020 21:38:29


package rs.ac.bg.etf.pp1.ast;

public class GreaterEqualOp extends Relop {

    private String GteString;

    public GreaterEqualOp (String GteString) {
        this.GteString=GteString;
    }

    public String getGteString() {
        return GteString;
    }

    public void setGteString(String GteString) {
        this.GteString=GteString;
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
        buffer.append("GreaterEqualOp(\n");

        buffer.append(" "+tab+GteString);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GreaterEqualOp]");
        return buffer.toString();
    }
}
