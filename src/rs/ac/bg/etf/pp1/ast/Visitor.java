// generated with ast extension for cup
// version 0.8
// 2/1/2020 21:38:30


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(MethodDecl MethodDecl);
    public void visit(Relop Relop);
    public void visit(FormalParamDecl FormalParamDecl);
    public void visit(ConditionOptional ConditionOptional);
    public void visit(StatementList StatementList);
    public void visit(Factor Factor);
    public void visit(CondTerm CondTerm);
    public void visit(DeclList DeclList);
    public void visit(Designator Designator);
    public void visit(Term Term);
    public void visit(Condition Condition);
    public void visit(VarMulDeclList VarMulDeclList);
    public void visit(ConstDeclList ConstDeclList);
    public void visit(ActualParamList ActualParamList);
    public void visit(VarDeclList VarDeclList);
    public void visit(FormalParamList FormalParamList);
    public void visit(Expr Expr);
    public void visit(DesignatorStmtOptional DesignatorStmtOptional);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(ActualPars ActualPars);
    public void visit(Decl Decl);
    public void visit(Statement Statement);
    public void visit(VarDecl VarDecl);
    public void visit(ConstDecl ConstDecl);
    public void visit(CondFact CondFact);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(FormPars FormPars);
    public void visit(ModOp ModOp);
    public void visit(DivOp DivOp);
    public void visit(Mulop Mulop);
    public void visit(MinusOp MinusOp);
    public void visit(Addop Addop);
    public void visit(LessEqualOp LessEqualOp);
    public void visit(LessOp LessOp);
    public void visit(GreaterEqualOp GreaterEqualOp);
    public void visit(GreaterOp GreaterOp);
    public void visit(DifferentOp DifferentOp);
    public void visit(EqualOp EqualOp);
    public void visit(DesignatorArray DesignatorArray);
    public void visit(SimpleDesignator SimpleDesignator);
    public void visit(NestedExpr NestedExpr);
    public void visit(NewArray NewArray);
    public void visit(NewType NewType);
    public void visit(BoolConst BoolConst);
    public void visit(CharConst CharConst);
    public void visit(NumConst NumConst);
    public void visit(FuncCallFactor FuncCallFactor);
    public void visit(Var Var);
    public void visit(SingleTerm SingleTerm);
    public void visit(MulTerm MulTerm);
    public void visit(SingleNegExpr SingleNegExpr);
    public void visit(SingleExpr SingleExpr);
    public void visit(AddExpr AddExpr);
    public void visit(NegTerm NegTerm);
    public void visit(RelCondFact RelCondFact);
    public void visit(SingleCondFact SingleCondFact);
    public void visit(SingleCondTerm SingleCondTerm);
    public void visit(MultipleConditionsAND MultipleConditionsAND);
    public void visit(ErrorCondition ErrorCondition);
    public void visit(NoCondition NoCondition);
    public void visit(OptionalCondition OptionalCondition);
    public void visit(SingleCondition SingleCondition);
    public void visit(MultipleConditionsOR MultipleConditionsOR);
    public void visit(ActualParam ActualParam);
    public void visit(ActualParams ActualParams);
    public void visit(NoActuals NoActuals);
    public void visit(Actuals Actuals);
    public void visit(NoDesignatorStmt NoDesignatorStmt);
    public void visit(OneDesignatorStmt OneDesignatorStmt);
    public void visit(Decrement Decrement);
    public void visit(Increment Increment);
    public void visit(FunctionCallStmt FunctionCallStmt);
    public void visit(Assignment Assignment);
    public void visit(RParenTT RParenTT);
    public void visit(SemiTT SemiTT);
    public void visit(SemiT SemiT);
    public void visit(RParenT RParenT);
    public void visit(ForT ForT);
    public void visit(ElseT ElseT);
    public void visit(IfT IfT);
    public void visit(ErrorStmt ErrorStmt);
    public void visit(BlockOfStmts BlockOfStmts);
    public void visit(PrintStmtArg PrintStmtArg);
    public void visit(PrintStmt PrintStmt);
    public void visit(ReadStmt ReadStmt);
    public void visit(ReturnNoExpr ReturnNoExpr);
    public void visit(ReturnExpr ReturnExpr);
    public void visit(ContinueStatement ContinueStatement);
    public void visit(BreakStatement BreakStatement);
    public void visit(ForStatement ForStatement);
    public void visit(UnmatchedStatement UnmatchedStatement);
    public void visit(MatchedStatement MatchedStatement);
    public void visit(DesignatorStmt DesignatorStmt);
    public void visit(NoStmt NoStmt);
    public void visit(Statements Statements);
    public void visit(ErrorFormalPars ErrorFormalPars);
    public void visit(FormalParamDeclBrackets FormalParamDeclBrackets);
    public void visit(FormalParamDeclNoBrackets FormalParamDeclNoBrackets);
    public void visit(SingleFormalParamDecl SingleFormalParamDecl);
    public void visit(FormalParamDecls FormalParamDecls);
    public void visit(NoFormParam NoFormParam);
    public void visit(FormParams FormParams);
    public void visit(MethodVoidName MethodVoidName);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(VoidMethodDecl VoidMethodDecl);
    public void visit(TypeMethodDecl TypeMethodDecl);
    public void visit(NoMethodDecl NoMethodDecl);
    public void visit(MethodDeclarations MethodDeclarations);
    public void visit(Type Type);
    public void visit(NoVarDecls NoVarDecls);
    public void visit(VarMultiDeclList VarMultiDeclList);
    public void visit(ConstDeclChar ConstDeclChar);
    public void visit(ConstDeclBool ConstDeclBool);
    public void visit(ConstDeclNumber ConstDeclNumber);
    public void visit(OneSingleConstDecl OneSingleConstDecl);
    public void visit(OneConstDeclList OneConstDeclList);
    public void visit(ErrorVarDecl ErrorVarDecl);
    public void visit(VarDeclBrackets VarDeclBrackets);
    public void visit(VarDeclSingle VarDeclSingle);
    public void visit(OneSingleVarDecl OneSingleVarDecl);
    public void visit(OneVarDeclList OneVarDeclList);
    public void visit(OneConstDeclListEnded OneConstDeclListEnded);
    public void visit(OneVarDeclListEnded OneVarDeclListEnded);
    public void visit(NoDecl NoDecl);
    public void visit(AllDeclarationsList AllDeclarationsList);
    public void visit(ProgramName ProgramName);
    public void visit(Program Program);

}
