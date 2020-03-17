import edu.princeton.cs.algs4.StdIn;

public class Permutation
{
    int K;
    public Permutation(int k, int n)
    {
        K = k;
        q = new RandomizedQueue<String>(n);
    }

    private RandomizedQueue<String> q;

    public String getItem()
    {
        return q.dequeue();
    }

    public void addItem(String item)
    {
        q.enqueue(item);
    }

    public static void main(String[] args)
    {
        int k = Integer.parseInt(args[0]);
        String[] s = StdIn.readLine().split("\\s+");
        Permutation p = new Permutation(k, s.length);
        for (int i = 0; i < s.length; i++)
        {
            p.addItem(s[i]);
        }

        for (int i = 0; i < k; i++)
        {
            System.out.println(p.getItem());
        }
    }
}