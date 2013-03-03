import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
    private int N;
    private Node first;
    private Node last;
    
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }
    
    // construct an empty deque
    public Deque(){
        first = null;
        last = null;
        N = 0;
    }
       
   // is the deque empty?
   public boolean isEmpty() {
       return first == null;
       
   }
   // return the number of items on the deque
   public int size() {
       return N;
   }
   // insert the item at the front
   public void addFirst(Item item) {
       if (item == null) throw new NullPointerException();
       Node oldfirst = first;
       first = new Node();
       first.item = item;
       first.prev = null;
       if (oldfirst == null) {
           first.next = null;
       }
       else {
           first.next = oldfirst;
           oldfirst.prev = first;        
       }
       if (last == null) {
           last = first;
       }
       N++;
   }
   // insert the item at the end
   public void addLast(Item item) {
       if (item == null) throw new NullPointerException("Object to add is NULL!");
       Node oldlast = last;
       last = new Node();
       if (oldlast == null) {
           last.prev = null;
       }
       else { 
           last.prev = oldlast;
           oldlast.next = last;
       }
       last.next = null;
       last.item = item;
       if (first == null) {
           first = last;
       }
       N++;    
   }
   // delete and return the item at the front
   public Item removeFirst() {
       if (first == null) throw new NoSuchElementException();
       Item item = first.item;
       first = first.next;
       if (first != null && first.prev != null) {
           first.prev = null;
       }
       if (first == null) {
           last = null;
       }
       N--;
       return item;
   }
   // delete and return the item at the end
   public Item removeLast() {
       if (last == null) throw new NoSuchElementException();
       Item item = last.item;
       last = last.prev;
       if (last != null && last.next != null){
           last.next = null;
       }
       if (last == null) {
           first = null;
       }
       
       N--;
       return item;
       
       
   }
   // return an iterator over items in order from front to end
   public Iterator<Item> iterator() {
       return new DequeIterator();
   }
   
   private class DequeIterator implements Iterator<Item> {
       private Node current = first;
       public boolean hasNext() {
           return current != null;
       }
       public Item next () { 
           if (!hasNext()) {
               throw new NoSuchElementException();
           }
           Item item = current.item;
           current = current.next;
           return item;
       }
       public void remove () {
           throw new UnsupportedOperationException();
       }
   }

   
   
   public static void main(String[] args) {
       Deque<Integer> adeque = new Deque<Integer>();
       adeque.addFirst(1);
       adeque.addFirst(2);
       adeque.addFirst(3);
       adeque.addFirst(4);
       Iterator<Integer> it = adeque.iterator();
       System.out.println(adeque.removeLast());
       System.out.println(adeque.removeLast());

   }
   
}