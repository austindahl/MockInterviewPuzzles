public class Tree {
    
    public Tree(float val) {
        value = val;
        left = NULL;
        right = NULL;
    }

    protected float value;
    protected Tree left;
    protected Tree right;


    public abstract Tree insert(float val);
    public abstract boolean contains(float val);
    public abstract int depth();
}
