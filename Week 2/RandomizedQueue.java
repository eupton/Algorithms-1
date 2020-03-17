import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int N = 0;
    // construct an empty randomized queue
    public RandomizedQueue(int capacity)
    {
        s = (Item[]) new Object[capacity];
    }

    // is the randomized queue empty?
    public boolean isEmpty()
    {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size()
    {
        return N;
    }

    // add the item
    public void enqueue(Item item)
    {
        if (item == null) {
            throw new IllegalArgumentException("Item parameter can't be null.");          
        }

        //if we fill the array completely then double its size
        if (N == s.length)
        {
            resize(N*2);            
        }

        //just add the new item to the end of the array
        //we'll be returning items randomly later
        s[N++] = item;
    }

    // remove and return a random item
    public Item dequeue()
    {
        if (isEmpty()) { 
            throw new NoSuchElementException("There are no items to remove. The queue is empty");
        }

        int r = getRandomIndex();
        Item item = s[r];
        swap(r, --N);

        //quarter full then resize to half
        if (N > 0 && N < s.length/4)
        {
            resize(N/2);            
        }
        
        return item;
    }



    // return a random item (but do not remove it)
    public Item sample()
    {
        if (isEmpty()) { 
            throw new NoSuchElementException("There are no items to sample. The queue is empty");
        }

        return s[getRandomIndex()];
    }

    private int getRandomIndex()
    {
        int r = 0;
        int upper = N-2 == 0 ? 1 : N-2;
        if (upper > 0)
        {
            r = StdRandom.uniform(0, upper);
        }
        return r;
    }

    //swap the values of the current last index with the index we are removing
    private void swap(int i, int j)
    {
        s[i] = s[j];
    }

    private void resize(int capacity)
    {
        Item[] copy  = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++)
        {
            copy[i] = s[i];
        }

        s = copy;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item>
    {
        int[] idx;
        int idxCount = N;

        public QueueIterator()
        {
            idx = new int[N];
            
            //initialize the index array with the used indices of the primary Item[] array (ie. s)
            for (int i = 0; i < N; i++)
            {
                idx[i] = i;
            }
        }

        //essentially using this to remove the iterated indices to the end of the idx array
        private void swapIndex(int i, int j)
        {
            idx[i] = idx[j];
        }

        private int getRandomIteratorIndex()
        {
            int r = 0;
            int upper = idxCount-2 == 0 ? 1 : idxCount-2;
            if (upper > 0)
            {
                r = StdRandom.uniform(0, upper);
            }
            return r;
        }
        
        public boolean hasNext()
        {
            return idxCount > 0;
        }

        public Item next()
        {
            if (!hasNext()) { 
                throw new NoSuchElementException("There are no more items in the queue.");
            }
            
            int r = getRandomIteratorIndex();
            Item item = s[idx[r]];
            swapIndex(r, --idxCount);

            return item;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>(20);
        for (int i  = 0; i < 20; i++)
        {
            q.enqueue(i);
        }

        for (int i = 0; i < 5; i++)
        {
            System.out.println("Sample: " + q.sample());
        }

        System.out.println("Size: " + q.size());

        int counter = 1;
        for (int i: q)
        {
            System.out.println(counter++ + ":" + i);
        }

        for (int i = 0; i < 20; i++)
        {
            System.out.println("Removed: " + q.dequeue());
        }
    }
}