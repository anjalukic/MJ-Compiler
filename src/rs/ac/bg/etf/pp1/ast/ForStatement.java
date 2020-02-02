// generated with ast extension for cup
// version 0.8
// 2/1/2020 21:38:29


package rs.ac.bg.etf.pp1.ast;

public class ForStatement extends Statement {

    private ForT ForT;
    private DesignatorStmtOptional DesignatorStmtOptional;
    private SemiT SemiT;
    private ConditionOptional ConditionOptional;
    private SemiTT SemiTT;
    private DesignatorStmtOptional DesignatorStmtOptional1;
    private RParenTT RParenTT;
    private Statement Statement;

    public ForStatement (ForT ForT, DesignatorStmtOptional DesignatorStmtOptional, SemiT SemiT, ConditionOptional ConditionOptional, SemiTT SemiTT, DesignatorStmtOptional DesignatorStmtOptional1, RParenTT RParenTT, Statement Statement) {
        this.ForT=ForT;
        if(ForT!=null) ForT.setParent(this);
        this.DesignatorStmtOptional=DesignatorStmtOptional;
        if(DesignatorStmtOptional!=null) DesignatorStmtOptional.setParent(this);
        this.SemiT=SemiT;
        if(SemiT!=null) SemiT.setParent(this);
        this.ConditionOptional=ConditionOptional;
        if(ConditionOptional!=null) ConditionOptional.setParent(this);
        this.SemiTT=SemiTT;
        if(SemiTT!=null) SemiTT.setParent(this);
        this.DesignatorStmtOptional1=DesignatorStmtOptional1;
        if(DesignatorStmtOptional1!=null) DesignatorStmtOptional1.setParent(this);
        this.RParenTT=RParenTT;
        if(RParenTT!=null) RParenTT.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ForT getForT() {
        return ForT;
    }

    public void setForT(ForT ForT) {
        this.ForT=ForT;
    }

    public DesignatorStmtOptional getDesignatorStmtOptional() {
        return DesignatorStmtOptional;
    }

    public void setDesignatorStmtOptional(DesignatorStmtOptional DesignatorStmtOptional) {
        this.DesignatorStmtOptional=DesignatorStmtOptional;
    }

    public SemiT getSemiT() {
        return SemiT;
    }

    public void setSemiT(SemiT SemiT) {
        this.SemiT=SemiT;
    }

    public ConditionOptional getConditionOptional() {
        return ConditionOptional;
    }

    public void setConditionOptional(ConditionOptional ConditionOptional) {
        this.ConditionOptional=ConditionOptional;
    }

    public SemiTT getSemiTT() {
        return SemiTT;
    }

    public void setSemiTT(SemiTT SemiTT) {
        this.SemiTT=SemiTT;
    }

    public DesignatorStmtOptional getDesignatorStmtOptional1() {
        return DesignatorStmtOptional1;
    }

    public void setDesignatorStmtOptional1(DesignatorStmtOptional DesignatorStmtOptional1) {
        this.DesignatorStmtOptional1=DesignatorStmtOptional1;
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
        if(ForT!=null) ForT.accept(visitor);
        if(DesignatorStmtOptional!=null) DesignatorStmtOptional.accept(visitor);
        if(SemiT!=null) SemiT.accept(visitor);
        if(ConditionOptional!=null) ConditionOptional.accept(visitor);
        if(SemiTT!=null) SemiTT.accept(visitor);
        if(DesignatorStmtOptional1!=null) DesignatorStmtOptional1.accept(visitor);
        if(RParenTT!=null) RParenTT.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForT!=null) ForT.traverseTopDown(visitor);
        if(DesignatorStmtOptional!=null) DesignatorStmtOptional.traverseTopDown(visitor);
        if(SemiT!=null) SemiT.traverseTopDown(visitor);
        if(ConditionOptional!=null) ConditionOptional.traverseTopDown(visitor);
        if(SemiTT!=null) SemiTT.traverseTopDown(visitor);
        if(DesignatorStmtOptional1!=null) DesignatorStmtOptional1.traverseTopDown(visitor);
        if(RParenTT!=null) RParenTT.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForT!=null) ForT.traverseBottomUp(visitor);
        if(DesignatorStmtOptional!=null) DesignatorStmtOptional.traverseBottomUp(visitor);
        if(SemiT!=null) SemiT.traverseBottomUp(visitor);
        if(ConditionOptional!=null) ConditionOptional.traverseBottomUp(visitor);
        if(SemiTT!=null) SemiTT.traverseBottomUp(visitor);
        if(DesignatorStmtOptional1!=null) DesignatorStmtOptional1.traverseBottomUp(visitor);
        if(RParenTT!=null) RParenTT.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForStatement(\n");

        if(ForT!=null)
            buffer.append(ForT.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStmtOptional!=null)
            buffer.append(DesignatorStmtOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SemiT!=null)
            buffer.append(SemiT.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionOptional!=null)
            buffer.append(ConditionOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SemiTT!=null)
            buffer.append(SemiTT.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStmtOptional1!=null)
            buffer.append(DesignatorStmtOptional1.toString("  "+tab));
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
        buffer.append(") [ForStatement]");
        return buffer.toString();
    }
}
