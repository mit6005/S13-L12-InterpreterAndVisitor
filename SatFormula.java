public interface Formula {
    /**
     * Evaluate the formula with the environment env.
     *
     * @param env the environment, which is a Java Map
     * @return true if the formula evaluates to true, false otherwise
     */
    public boolean eval(Env env);
}

public class Var implements Formula {
    private final String name;
    
    public Var(String name) {
        this.name = name;
    }
    
    public boolean eval(Env env) {
        return env.get(this.name);
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
}