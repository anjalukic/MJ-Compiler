// generated with ast extension for cup
// version 0.8
// 22/5/2020 16:56:38


package rs.ac.bg.etf.pp1.ast;

public class VarMultiDeclList extends VarMulDeclList {

    private VarMulDeclList VarMulDeclList;
    private Type Type;
    private VarDeclList VarDeclList;

    public VarMultiDeclList (VarMulDeclList VarMulDeclList, Type Type, VarDeclList VarDeclList) {
        this.VarMulDeclList=VarMulDeclList;
        if(VarMulDeclList!=null) VarMulDeclList.setParent(this);
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
    }

    public VarMulDeclList getVarMulDeclList() {
        return VarMulDeclList;
    }

    public void setVarMulDeclList(VarMulDeclList VarMulDeclList) {
        this.VarMulDeclList=VarMulDeclList;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarMulDeclList!=null) VarMulDeclList.accept(visitor);
        if(Type!=null) Type.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarMulDeclList!=null) VarMulDeclList.traverseTopDown(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarMulDeclList!=null) VarMulDeclList.traverseBottomUp(visitor);
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarMultiDeclList(\n");

        if(VarMulDeclList!=null)
            buffer.append(VarMulDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarMultiDeclList]");
        return buffer.toString();
    }
}
