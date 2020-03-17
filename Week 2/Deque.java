import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node
    {
        Item item;
        Node next;
        protected void setNext(Node node)
        {
            this.next = node;
            if (node != null)
            {
                node.prev = this;
            }
        }

        Node prev;
        protected void setPrev(Node node)
        {
            this.prev = node;
            if (node != null)
            {
                node.next = this;
            }
        }
    }

    private Node first;
    private Node last;
    
    private int nodeCount;

    // construct an empty deque
    public Deque() {}

    // is the deque empty?
    public boolean isEmpty()
    {
        return nodeCount == 0;
    }

    // return the number of items on the deque
    public int size()
    {
        return nodeCount;
    }

    // add the item to the front
    public void addFirst(Item item)
    {
        if (item == null) {
            throw new IllegalArgumentException("Item parameter can't be null.");
        }

        Node oldFirst = this.first;
        this.first = new Node();
        this.first.item = item;
        
        if (!isEmpty())
        {
            this.first.setNext(oldFirst);
            if (nodeCount == 1) { this.last = oldFirst; }
        }
        else
        {
            this.last = this.first;
        }

        nodeCount++;
    }

    // add the item to the back
    public void addLast(Item item)
    {
        if (item == null) {
            throw new IllegalArgumentException("Item parameter can't be null.");          
        }

        Node oldLast = this.last;
        
        this.last = new Node();
        this.last.item = item;
        if (!isEmpty())
        {
            this.last.setPrev(oldLast);            
            if (nodeCount == 1) { this.first = oldLast; }
        }
        else
        {
            this.first = this.last;
        }

        nodeCount++;
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (isEmpty()) { 
            throw new NoSuchElementException("There are no items to remove. The list is empty");
        }

        Node removed = this.first;
        this.first = this.first.next;
        this.first.prev = null;
        nodeCount--;
        return removed.item;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if (isEmpty()) { 
            throw new NoSuchElementException("There are no items to remove. The list is empty");
        }

        Node removed = this.last;
        this.last = this.last.prev;
        this.last.next = null;
        nodeCount--;
        return removed.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>
    {
        Node current = first;
        public boolean hasNext()
        {
            return current != null;
        }

        public Item next()
        {
            if (!hasNext()) { 
                throw new NoSuchElementException("There are no more items in the list.");
            }
            Node prev = current;
            current = current.next;
            return prev.item;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args)
    {        
        Deque<Integer> d = new Deque<Integer>();
        for (int i = 1; i < 30; i++)
        {
            if (i % 2 == 0) 
            {
                d.addLast(i);
            }
            else
            {
                d.addFirst(i);
            }
        }

        for (int i = 1; i < 30; i+=3)
        {
            if (i % 2 == 0) 
            {
                d.removeLast();
            }
            else
            {
                d.removeFirst();
            }
        }

        for(Integer val : d)
        {
            System.out.println(val);
        }

        System.out.println("Size: " + d.size());

        System.out.println("Empty: " + d.isEmpty());
     }
}