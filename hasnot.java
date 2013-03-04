public interface Visitor<R> {
    public R onVar(Var v);
    
    public R onNot(Not n);
    
    public R onAnd(And a);
    
    public R onOr(Or o);
}

class HasNot implements Visitor<Boolean> {
    public Boolean onVar(Var v) { return false; }
    
    public Boolean onNot(Not n) { return true; }
    
    public Boolean onAnd(And a) {
        return hasNot(a.left) || hasNot(a.right);
    }
    
    public Boolean onOr(Or o) {
        return hasNot(o.left) || hasNot(o.right);
    }
    
    public static boolean hasNot(Formula f) {
        return f.accept(new HasNot());
    }
}


