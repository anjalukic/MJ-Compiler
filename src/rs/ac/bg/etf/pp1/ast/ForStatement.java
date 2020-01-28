// generated with ast extension for cup
// version 0.8
// 26/0/2020 13:46:58


package rs.ac.bg.etf.pp1.ast;

public class ForStatement extends Statement {

    private DesignatorStmtOptional DesignatorStmtOptional;
    private ConditionOptional ConditionOptional;
    private DesignatorStmtOptional DesignatorStmtOptional1;
    private Statement Statement;

    public ForStatement (DesignatorStmtOptional DesignatorStmtOptional, ConditionOptional ConditionOptional, DesignatorStmtOptional DesignatorStmtOptional1, Statement Statement) {
        this.DesignatorStmtOptional=DesignatorStmtOptional;
        if(DesignatorStmtOptional!=null) DesignatorStmtOptional.setParent(this);
        this.ConditionOptional=ConditionOptional;
        if(ConditionOptional!=null) ConditionOptional.setParent(this);
        this.DesignatorStmtOptional1=DesignatorStmtOptional1;
        if(DesignatorStmtOptional1!=null) DesignatorStmtOptional1.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public DesignatorStmtOptional getDesignatorStmtOptional() {
        return DesignatorStmtOptional;
    }

    public void setDesignatorStmtOptional(DesignatorStmtOptional DesignatorStmtOptional) {
        this.DesignatorStmtOptional=DesignatorStmtOptional;
    }

    public ConditionOptional getConditionOptional() {
        return ConditionOptional;
    }

    public void setConditionOptional(ConditionOptional ConditionOptional) {
        this.ConditionOptional=ConditionOptional;
    }

    public DesignatorStmtOptional getDesignatorStmtOptional1() {
        return DesignatorStmtOptional1;
    }

    public void setDesignatorStmtOptional1(DesignatorStmtOptional DesignatorStmtOptional1) {
        this.DesignatorStmtOptional1=DesignatorStmtOptional1;
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
        if(DesignatorStmtOptional!=null) DesignatorStmtOptional.accept(visitor);
        if(ConditionOptional!=null) ConditionOptional.accept(visitor);
        if(DesignatorStmtOptional1!=null) DesignatorStmtOptional1.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStmtOptional!=null) DesignatorStmtOptional.traverseTopDown(visitor);
        if(ConditionOptional!=null) ConditionOptional.traverseTopDown(visitor);
        if(DesignatorStmtOptional1!=null) DesignatorStmtOptional1.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStmtOptional!=null) DesignatorStmtOptional.traverseBottomUp(visitor);
        if(ConditionOptional!=null) ConditionOptional.traverseBottomUp(visitor);
        if(DesignatorStmtOptional1!=null) DesignatorStmtOptional1.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForStatement(\n");

        if(DesignatorStmtOptional!=null)
            buffer.append(DesignatorStmtOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionOptional!=null)
            buffer.append(ConditionOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStmtOptional1!=null)
            buffer.append(DesignatorStmtOptional1.toString("  "+tab));
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
