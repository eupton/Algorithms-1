//the nodes are connected when they share the same root
//better than the quickfind version but still too slow for large problems

public class UnionFindQuickUnion {
    public UnionFindQuickUnion(int N) {
        id = new int[N];
        for (var i = 0; i < N; i++) {
            id[i] = i;
        }
    }
    private int id[];

    private int root(int i) {
        while(id[i] != i) {
            i = id[i];
        }
        return i;
    }

    public void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);
        if (pRoot == qRoot) return; //they are already connected
        id[pRoot] = qRoot;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public static void main(String[] args) {
        UnionFindQuickUnion uf = new UnionFindQuickUnion(10);
        System.out.println(uf.connected(1, 9));
        uf.union(1, 9);
        System.out.println(uf.connected(1, 9));
        uf.union(7, 6);
        System.out.println(uf.connected(7, 6));
        System.out.println(uf.connected(1, 0));
        uf.union(0, 9);
        System.out.println(uf.connected(0, 9));
        System.out.println(uf.connected(0, 1));
    }
}
