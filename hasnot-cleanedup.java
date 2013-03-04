

class HasNot implements Visitor<Boolean> {
    public Boolean on(Var v) { return false; }
    
    public Boolean on(Not n) { return true; }
    
    public Boolean on(And a) {
        return hasNot(a.left) || hasNot(a.right);
    }
    
    public Boolean on(Or o) {
        return hasNot(o.left) || hasNot(o.right);
    }

    public static boolean hasNot(Formula f) {
        return f.accept(new HasNot());
    }
}


