// generated with ast extension for cup
// version 0.8
// 2/1/2020 1:19:26


package rs.ac.bg.etf.pp1.ast;

public class FormalParamDeclNoBrackets extends FormalParamDecl {

    private Type Type;
    private String VarName;

    public FormalParamDeclNoBrackets (Type Type, String VarName) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarName=VarName;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getVarName() {
        return VarName;
    }

    public void setVarName(String VarName) {
        this.VarName=VarName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormalParamDeclNoBrackets(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+VarName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalParamDeclNoBrackets]");
        return buffer.toString();
    }
}
