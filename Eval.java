// implementation of eval as a visitor
public class Eval implements Formula.Visitor<Boolean> {
    private Env env;
    
    public Eval(Env env) { this.env = env; }
    
    public static eval(Formula f, Env env) {
        return f.accept(new Eval(env));
    }
    
    public Boolean on(Var v) { return env.get(v.name); }
    
    public Boolean on(Not n) { return !eval(n.f, env); }
    
    public Boolean on(And a) {
        return eval(a.left, env) && eval(a.right, env);
    }
    
    public Boolean on(Or o) {
        return eval(a.left, env) || eval(a.right, env);
    }
    
}