import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Deque<Item> implements Iterable<Item> {
   
	private List<Item> deque; 
	private int size; 
	
	public Deque() {                           // construct an empty deque
      deque=new LinkedList<Item>();
      size=0;
   }
   public boolean isEmpty(){                 // is the deque empty?
      return size==0;
   }
   public int size(){                        // return the number of items on the deque
      return size;
   }
   public void addFirst(Item item) throws java.lang.NullPointerException{          // insert the item at the front
      if (item==null) throw new java.lang.NullPointerException("null element");
	   deque.add(0,item);
       size++;
   }
   public void addLast(Item item) throws java.lang.NullPointerException{           // insert the item at the end
	   if (item==null) throw new java.lang.NullPointerException("null element");
	   deque.add(item);
	   size++;
   }
   public Item removeFirst() throws java.util.NoSuchElementException{                // delete and return the item at the front
       if (this.size==0) throw new java.util.NoSuchElementException("empty deque");
	   size--;
	   return deque.remove(0);
   }
   public Item removeLast()throws java.util.NoSuchElementException{             // delete and return the item at the end
	   if (this.size==0) throw new java.util.NoSuchElementException("empty deque");
	   return deque.remove(--size);
   }
   
   public Iterator<Item> iterator(){         // return an iterator over items in order from front to end
	   return new dequeIterator();
   }
   
   private class dequeIterator implements Iterator<Item>{
    Iterator<Item> it=deque.iterator();
	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public Item next() {
    	if(!hasNext()) throw new java.util.NoSuchElementException();
		return it.next();
	}

	@Override
	public void remove() {
		throw new java.lang.UnsupportedOperationException();
		
	}
	   
	   
   }
 
   
   public static void main(String[] args){   // unit testing
   
   Deque<Integer> d1=new Deque<Integer>();
   d1.addFirst(0);
   d1.addFirst(1);
   d1.addLast(2);
   d1.removeFirst();
   
   Iterator<Integer> iterator1=d1.iterator();
   while (iterator1.hasNext()){
	   //iterator1.remove();
	   System.out.println(iterator1.next());
   }
   
   }
}
