public interface Formula {
    public interface Visitor<R> {
        public R on(Var v);

        public R on(Not n);

        public R on(And a);

        public R on(Or o);
    }
    
    
    /**
     * Evaluate the formula with the environment env.
     *
     * @param env the environment, which is a Java Map
     * @return true if the formula evaluates to true, false otherwise
     */
    public boolean eval(Env env);
    
    /**
     * Invoke the passed-in visitor on the formula.
     *
     * @param v the visitor
     */
    public <R> R accept(Visitor<R> v);
}

public class Var implements Formula {
    private final String name;
    
    public Var(String name) {
        this.name = name;
    }
    
    public boolean eval(Env env) {
        return env.get(this.name);
    }
    
    // the big piece of "magic" here is that when we call
    // onVar with "this", the declared type of "this" is
    // Var.  So the approach preserves static type checking.
    public <R> R accept(Visitor<R> v) {
        return v.onVar(this);
    }
}

public class Not implements Formula {
    private final Formula f;
    
    public Not(Formula f) {
        this.f = f;
    }
    
    public boolean eval(Env env) {
        return !(f.eval(env));
    }
    
    public <R> R accept(Visitor<R> v) {
        return v.onNot(this);
    }
}

public class And implements Formula {
    private final Formula left;
    private final Formula right;
    
    public And(Formula left, Formula right) {
        this.left = left;
        this.right = right;
    }
    
    public boolean eval(Env env) {
        return left.eval(env) && right.eval(env);
    }
    
    public <R> R accept(Visitor<R> v) {
        return v.onAnd(this);
    }
}

public class Or implements Formula {
    private final Formula left;
    private final Formula right;
    
    public Or(Formula left, Formula right) {
        this.left = left;
        this.right = right;
    }
    
    public boolean eval(Env env) {
        return left.eval(env) || right.eval(env);
    }
    
    public <R> R accept(Visitor<R> v) {
        return v.onOr(this);
    }
}