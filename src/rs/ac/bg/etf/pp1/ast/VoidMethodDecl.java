// generated with ast extension for cup
// version 0.8
// 2/1/2020 1:19:26


package rs.ac.bg.etf.pp1.ast;

public class VoidMethodDecl extends MethodDecl {

    private MethodVoidName MethodVoidName;
    private FormPars FormPars;
    private VarMulDeclList VarMulDeclList;
    private StatementList StatementList;

    public VoidMethodDecl (MethodVoidName MethodVoidName, FormPars FormPars, VarMulDeclList VarMulDeclList, StatementList StatementList) {
        this.MethodVoidName=MethodVoidName;
        if(MethodVoidName!=null) MethodVoidName.setParent(this);
        this.FormPars=FormPars;
        if(FormPars!=null) FormPars.setParent(this);
        this.VarMulDeclList=VarMulDeclList;
        if(VarMulDeclList!=null) VarMulDeclList.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public MethodVoidName getMethodVoidName() {
        return MethodVoidName;
    }

    public void setMethodVoidName(MethodVoidName MethodVoidName) {
        this.MethodVoidName=MethodVoidName;
    }

    public FormPars getFormPars() {
        return FormPars;
    }

    public void setFormPars(FormPars FormPars) {
        this.FormPars=FormPars;
    }

    public VarMulDeclList getVarMulDeclList() {
        return VarMulDeclList;
    }

    public void setVarMulDeclList(VarMulDeclList VarMulDeclList) {
        this.VarMulDeclList=VarMulDeclList;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodVoidName!=null) MethodVoidName.accept(visitor);
        if(FormPars!=null) FormPars.accept(visitor);
        if(VarMulDeclList!=null) VarMulDeclList.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodVoidName!=null) MethodVoidName.traverseTopDown(visitor);
        if(FormPars!=null) FormPars.traverseTopDown(visitor);
        if(VarMulDeclList!=null) VarMulDeclList.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodVoidName!=null) MethodVoidName.traverseBottomUp(visitor);
        if(FormPars!=null) FormPars.traverseBottomUp(visitor);
        if(VarMulDeclList!=null) VarMulDeclList.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VoidMethodDecl(\n");

        if(MethodVoidName!=null)
            buffer.append(MethodVoidName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormPars!=null)
            buffer.append(FormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarMulDeclList!=null)
            buffer.append(VarMulDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VoidMethodDecl]");
        return buffer.toString();
    }
}
