public class LinkedQueueOfStrings
{
    private Node first, last;

    private class Node
    {
        private Node(String item, Node next)
        {
            this.item = item;
            this.next = next;
        }

        String item;
        Node next;
    }

    public boolean isEmpty()
    {
        return first == null;
    }

    public void enqueue(String item)
    {
        Node oldLast = last;
        last = new Node(item, null);
        if (isEmpty())
        {
            first = last;
        }
        else
        {
            oldLast.next = last;
        }
    }

    public String dequeue()
    {
        String item  = first.item;
        first = first.next;
        if (isEmpty()) { last = null; }
        
        return item;
    }
}