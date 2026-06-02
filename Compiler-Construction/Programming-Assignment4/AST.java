import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

abstract class AST {
    abstract void emit(Emitter e) ;
    abstract void getCalls(Set<String> calls);
}

class Program extends AST {
    final List<Definition> definitions ; 
    Program(List<Definition> definitions) {
        this.definitions = definitions ; 
    }
    void emit(Emitter e) {
        for (Definition d : definitions) d.emit(e) ; 
    }
    void getCalls(Set<String> calls) {
        for (Definition d : definitions) d.getCalls(calls);
    }
}

// class Definition must be modified to perform Task #4. See class Var too.
class Definition extends AST {
    final String name ; 
    final List<String> params ;
    final Expr expr ;

    Definition(String name, List<String> params, Expr expr) {
        this.name = name ; 
        this.params = params ;
        this.expr = expr ;
    }

    void emit(Emitter e) {
        e.emit(name + ": nop") ;
        
        e.setParams(params);
        expr.emit(e);
        e.popTo("r2"); 
        
        e.emit("ret r_ra") ; 
    }

    void getCalls(Set<String> calls) {
        expr.getCalls(calls);
    }
}

abstract class Expr extends AST {}

class Int extends Expr {
    final long value;
    Int(long value) { this.value = value; }
    void emit(Emitter e) {
        e.emit("loadI " + value + " => r2");
        e.pushReg("r2");
    }
    void getCalls(Set<String> calls) {}
}

// class Var must be modified to perform Tasks #4 and #6. See class Definition and Emitter::emitLoadParam
class Var extends Expr {
    final String name;
    Var(String name) { this.name = name; }
    void emit(Emitter e) {
        e.emitLoadParam(name, "r2");
        e.pushReg("r2");
    }
    void getCalls(Set<String> calls) {}
}

class Bin extends Expr {
    final String op;
    final Expr left, right;
    Bin(String op, Expr left, Expr right) {
        this.left = left;
        this.right = right;
        this.op = switch(op) {
            case "+" -> "add" ;
            case "-" -> "sub" ;
            case "*" -> "mult" ;
            case "/" -> "div" ;
            default -> throw new RuntimeException("unknown binary op: " + op);
        } ; 
    }

    void emit(Emitter e) {
        left.emit(e);
        right.emit(e);

        e.popTo("r3"); // right
        e.popTo("r2"); // left
        e.emit(op+" r2, r3 => r2");
        e.pushReg("r2");
    }
    void getCalls(Set<String> calls) {
        left.getCalls(calls);
        right.getCalls(calls);
    }
}

class Cond extends Expr {
    final Expr cond, thenExpr, elseExpr;
    Cond(Expr cond, Expr thenExpr, Expr elseExpr) {
        this.cond = cond;
        this.thenExpr = thenExpr;
        this.elseExpr = elseExpr;
    }
    void emit(Emitter e) {
        String lTrue = e.getLabel();
        String lFalse = e.getLabel();
        String lJoin = e.getLabel();

        cond.emit(e) ;
        e.popTo("r2");
        e.emit("cbr r2 => " + lTrue + ", " + lFalse);

        e.emit(lTrue + ": nop");
        thenExpr.emit(e);
        e.emit("jumpI => " + lJoin);

        e.emit(lFalse + ": nop");
        elseExpr.emit(e);

        e.emit(lJoin + ": nop");
    }
    void getCalls(Set<String> calls) {
        cond.getCalls(calls);
        thenExpr.getCalls(calls);
        elseExpr.getCalls(calls);
    }
}

// You'll have to modify class FnCall to satisfy tasks #5 and #7
class FnCall extends Expr {
    final String name ; 
    final List<Expr> args ;
    FnCall(String name, List<Expr> args) {
        this.name = name ; 
        this.args = args ;
    }

    void emit(Emitter e) {

        /* Algorithm: 
            1. save top of stack before args were pushed
            2. Evaluate all arguments. Keep them pushed on the stack. 
               Ensure order is consistent with what you'll be doing when recalling a Var. 
            3. jsr into the appropriate label. 
            4. restore top of stack as it was (as in step #1)
            5. Don't forget to push result (r2) on stack (since function call is an expression evaluation)        
        */
       
        e.emit("i2i r1 => r3"); 
        
        for (int i = args.size() - 1; i >= 0; i--) {
            args.get(i).emit(e);
        }

        e.emit("loadI " + name + " => r2") ; 
        e.emit("jsr sc_call") ; 
        
        e.emit("i2i r3 => r1");
        
        e.pushReg("r2");
    }
    void getCalls(Set<String> calls) {
        calls.add(name);
        for (Expr arg : args) arg.getCalls(calls);
    }
}
