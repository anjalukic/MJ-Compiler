// generated with ast extension for cup
// version 0.8
// 22/5/2020 16:56:38


package rs.ac.bg.etf.pp1.ast;

public class NoCondition extends ConditionOptional {

    public NoCondition () {
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
        buffer.append("NoCondition(\n");

        buffer.append(tab);
        buffer.append(") [NoCondition]");
        return buffer.toString();
    }
}
