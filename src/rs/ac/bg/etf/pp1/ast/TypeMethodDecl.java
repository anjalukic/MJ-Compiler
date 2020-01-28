// generated with ast extension for cup
// version 0.8
// 26/0/2020 13:46:58


package rs.ac.bg.etf.pp1.ast;

public class TypeMethodDecl extends MethodDecl {

    private Type Type;
    private String methName;
    private FormPars FormPars;
    private VarMulDeclList VarMulDeclList;
    private StatementList StatementList;

    public TypeMethodDecl (Type Type, String methName, FormPars FormPars, VarMulDeclList VarMulDeclList, StatementList StatementList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.methName=methName;
        this.FormPars=FormPars;
        if(FormPars!=null) FormPars.setParent(this);
        this.VarMulDeclList=VarMulDeclList;
        if(VarMulDeclList!=null) VarMulDeclList.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getMethName() {
        return methName;
    }

    public void setMethName(String methName) {
        this.methName=methName;
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
        if(Type!=null) Type.accept(visitor);
        if(FormPars!=null) FormPars.accept(visitor);
        if(VarMulDeclList!=null) VarMulDeclList.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(FormPars!=null) FormPars.traverseTopDown(visitor);
        if(VarMulDeclList!=null) VarMulDeclList.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(FormPars!=null) FormPars.traverseBottomUp(visitor);
        if(VarMulDeclList!=null) VarMulDeclList.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TypeMethodDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+methName);
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
        buffer.append(") [TypeMethodDecl]");
        return buffer.toString();
    }
}
