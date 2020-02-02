// generated with ast extension for cup
// version 0.8
// 2/1/2020 18:25:26


package rs.ac.bg.etf.pp1.ast;

public class SimpleDesignator extends Designator {

    private String DesignatorName;

    public SimpleDesignator (String DesignatorName) {
        this.DesignatorName=DesignatorName;
    }

    public String getDesignatorName() {
        return DesignatorName;
    }

    public void setDesignatorName(String DesignatorName) {
        this.DesignatorName=DesignatorName;
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
        buffer.append("SimpleDesignator(\n");

        buffer.append(" "+tab+DesignatorName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SimpleDesignator]");
        return buffer.toString();
    }
}
