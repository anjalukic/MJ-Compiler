// generated with ast extension for cup
// version 0.8
// 2/1/2020 21:38:29


package rs.ac.bg.etf.pp1.ast;

public class MatchedStatement extends Statement {

    private IfT IfT;
    private Condition Condition;
    private RParenT RParenT;
    private Statement Statement;
    private ElseT ElseT;
    private Statement Statement1;

    public MatchedStatement (IfT IfT, Condition Condition, RParenT RParenT, Statement Statement, ElseT ElseT, Statement Statement1) {
        this.IfT=IfT;
        if(IfT!=null) IfT.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.RParenT=RParenT;
        if(RParenT!=null) RParenT.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.ElseT=ElseT;
        if(ElseT!=null) ElseT.setParent(this);
        this.Statement1=Statement1;
        if(Statement1!=null) Statement1.setParent(this);
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

    public ElseT getElseT() {
        return ElseT;
    }

    public void setElseT(ElseT ElseT) {
        this.ElseT=ElseT;
    }

    public Statement getStatement1() {
        return Statement1;
    }

    public void setStatement1(Statement Statement1) {
        this.Statement1=Statement1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfT!=null) IfT.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
        if(RParenT!=null) RParenT.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(ElseT!=null) ElseT.accept(visitor);
        if(Statement1!=null) Statement1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfT!=null) IfT.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(RParenT!=null) RParenT.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(ElseT!=null) ElseT.traverseTopDown(visitor);
        if(Statement1!=null) Statement1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfT!=null) IfT.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(RParenT!=null) RParenT.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(ElseT!=null) ElseT.traverseBottomUp(visitor);
        if(Statement1!=null) Statement1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MatchedStatement(\n");

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

        if(ElseT!=null)
            buffer.append(ElseT.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement1!=null)
            buffer.append(Statement1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MatchedStatement]");
        return buffer.toString();
    }
}
