//the nodes are connected when they share the same root
//limiting the depth of the tree by always putting the smaller tree under the larger during union()
//this limits the distance from root and reduces the number of traversals needed for any one node to reach the root

public class UnionFindWeightedQuickUnion {
    public UnionFindWeightedQuickUnion(int N) {
        id = new int[N];
        sz = new int[N];
        for (var i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }
    private int id[];
    private int sz[];

    private int size(int i) {
        return sz[root(i)];
    }

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
        if (sz[pRoot] > sz[qRoot]) { 
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        } else {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public static void main(String[] args) {
        UnionFindWeightedQuickUnion uf = new UnionFindWeightedQuickUnion(10);
        System.out.println(uf.connected(1, 9));
        System.out.println(uf.size(1));
        uf.union(1, 9);
        System.out.println(uf.size(1));
        System.out.println(uf.connected(1, 9));
        uf.union(7, 6);
        uf.union(6, 8);
        System.out.println(uf.size(7));
        System.out.println(uf.connected(7, 6));
        System.out.println(uf.connected(1, 0));
        uf.union(0, 9);
        System.out.println(uf.size(1));
        System.out.println(uf.connected(0, 9));
        System.out.println(uf.connected(0, 1));
        uf.union(7, 9);
        System.out.println(uf.size(0));
    }
}
