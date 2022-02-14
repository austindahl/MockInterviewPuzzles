import Tree;

public class AustinsTree extends Tree {
    public AustinsTree(float val) {
        value = val;
        left = NULL;
        right = NULL;
    }

    protected float value;
    protected Tree left;
    protected Tree right;

    public AustinsTree insert(float val) {
        AustinsTree parent = NULL;
        AustinsTree t = this;
        while (t != NULL) {
            parent = t;
            if (val >= t.value) {
                t = t.right;
            } else {
                t = t.left;
            }
        }
        AustinsTree n = AustinsTree(val);
        if (val >= parent) {
            parent.right = n;
        } else {
            parent.left = n;
        }
        return this;
    }

    public boolean contains(float val) {
        AustinsTree t = this;
        while (t != NULL) {
            if (val == t.value) {
                return true;
            } elseif (val > t.value) {
                t = t.right;
            } else {
                t = t.left;
            }
        }
        return false;
    }

    public int depth() {
        int l = 0;
        if (this.left != NULL) {
            l = left.depth();
        }
        int r = 0;
        if (this.right != 0) {
            r = right.depth();
        }
        return 1 + Math.max(l, r);
    }

    public static void main(String[] args) throws Exception {
        testInsert();
        testContains();
        testDepth();
    }

    private void testInsert() {
        float values[] = {5.0, 3.0, 7.0, 2.0, 1.0, 4.0}
        AustinsTree t = new AustinsTree(values[0])
        for (int i = 1; i < values.length; i++) {
            t.insert(values[i])
        }
        assert(t.left.value == 3.0, "t.left.value should be 3.0, not " + t.left.value);
    }

    private static void assert(boolean condition, String msg) {
        if (!condition) {
            System.out.println(msg);
        }
    }
}