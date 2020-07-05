// generated with ast extension for cup
// version 0.8
// 22/5/2020 16:56:38


package rs.ac.bg.etf.pp1.ast;

public class ForEachStatement extends Statement {

    private ForEachT ForEachT;
    private String iterName;
    private Designator Designator;
    private RParenTT RParenTT;
    private Statement Statement;

    public ForEachStatement (ForEachT ForEachT, String iterName, Designator Designator, RParenTT RParenTT, Statement Statement) {
        this.ForEachT=ForEachT;
        if(ForEachT!=null) ForEachT.setParent(this);
        this.iterName=iterName;
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.RParenTT=RParenTT;
        if(RParenTT!=null) RParenTT.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ForEachT getForEachT() {
        return ForEachT;
    }

    public void setForEachT(ForEachT ForEachT) {
        this.ForEachT=ForEachT;
    }

    public String getIterName() {
        return iterName;
    }

    public void setIterName(String iterName) {
        this.iterName=iterName;
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public RParenTT getRParenTT() {
        return RParenTT;
    }

    public void setRParenTT(RParenTT RParenTT) {
        this.RParenTT=RParenTT;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ForEachT!=null) ForEachT.accept(visitor);
        if(Designator!=null) Designator.accept(visitor);
        if(RParenTT!=null) RParenTT.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForEachT!=null) ForEachT.traverseTopDown(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(RParenTT!=null) RParenTT.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForEachT!=null) ForEachT.traverseBottomUp(visitor);
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(RParenTT!=null) RParenTT.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForEachStatement(\n");

        if(ForEachT!=null)
            buffer.append(ForEachT.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+iterName);
        buffer.append("\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(RParenTT!=null)
            buffer.append(RParenTT.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForEachStatement]");
        return buffer.toString();
    }
}
