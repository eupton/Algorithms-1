//nodes are connected when they share the same id value
//this is not ideal
//connected(p,q) runs in constant time, but union runs in quadratic when coupled with nodes * unions

public class UnionFindQuickFind {
    public UnionFindQuickFind(int N) {
        id = new int[N];
        for (var i = 0; i < N; i++) {
            id[i] = i;
        }
    }
    private int id[];

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (var i = 0; i < id.length; i++) {
            if(id[i] == pid) {id[i] = qid;}
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public static void main(String[] args) {
        UnionFindQuickFind uf = new UnionFindQuickFind(10);
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