// generated with ast extension for cup
// version 0.8
// 2/1/2020 18:25:26


package rs.ac.bg.etf.pp1.ast;

public class UnmatchedStatement extends Statement {

    private IfT IfT;
    private Condition Condition;
    private RParenT RParenT;
    private Statement Statement;

    public UnmatchedStatement (IfT IfT, Condition Condition, RParenT RParenT, Statement Statement) {
        this.IfT=IfT;
        if(IfT!=null) IfT.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.RParenT=RParenT;
        if(RParenT!=null) RParenT.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public IfT getIfT() {
        return IfT;
    }

    public void setIfT(IfT IfT) {
        this.IfT=IfT;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public RParenT getRParenT() {
        return RParenT;
    }

    public void setRParenT(RParenT RParenT) {
        this.RParenT=RParenT;
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
        if(IfT!=null) IfT.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
        if(RParenT!=null) RParenT.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfT!=null) IfT.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(RParenT!=null) RParenT.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfT!=null) IfT.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(RParenT!=null) RParenT.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("UnmatchedStatement(\n");

        if(IfT!=null)
            buffer.append(IfT.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(RParenT!=null)
            buffer.append(RParenT.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [UnmatchedStatement]");
        return buffer.toString();
    }
}
