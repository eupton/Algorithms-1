//the nodes are connected when they share the same root
//each time we go through the search for root, we set the node value to the grandparent id so that we are closer to the root next time through
//limiting the depth of the tree by always putting the smaller tree under the larger during union()
//this limits the distance from root and reduces the number of traversals needed for any one node to reach the root

public class UnionFindWeightedQuickUnionWithSimplePathCompression {
    public UnionFindWeightedQuickUnionWithSimplePathCompression(int N) {
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
            id[i] = id[id[i]]; //compress the path by updating the id value to the grandparent and now the next pass through this node will be a step closer to the root
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
        UnionFindWeightedQuickUnionWithSimplePathCompression uf = new UnionFindWeightedQuickUnionWithSimplePathCompression(20);
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
        uf.union(10, 19);
        uf.union(12, 11);
        uf.union(13, 14);
        uf.union(10, 13);
        uf.union(11, 14);
        uf.union(5, 18);
        uf.union(5, 19);
        uf.union(10, 9);
    }
}
