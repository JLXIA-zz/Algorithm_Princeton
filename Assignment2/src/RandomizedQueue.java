import java.util.Arrays;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
   private Item[] a;
   private int size;
   
   public RandomizedQueue(){                 // construct an empty randomized queue
      a= (Item[]) new Object[2];
      size=0;
   }
     
   public boolean isEmpty(){                 // is the queue empty?
       return size==0;
   }
   public int size(){                        // return the number of items on the queue
       return size;
   }
   public void enqueue(Item item){           // add the item
       if (item == null) throw new java.lang.NullPointerException();
	   if (size==a.length) resize(2*a.length);
       a[size++]=item;
   }
   public Item dequeue(){                    // delete and return a random item
       if (isEmpty()) throw new java.util.NoSuchElementException();
       int i = StdRandom.uniform(size);
       Item it=a[i];
       a[i]=a[--size];
       a[size]=null;
       if (size > 0 && size == a.length/4) resize(a.length/2);
       return it;
   }
   public Item sample(){                     // return (but do not delete) a random item
	   if (size == 0) throw new java.util.NoSuchElementException();
	   int i = StdRandom.uniform(size);
	   return a[i];
   }
   public Iterator<Item> iterator(){         // return an independent iterator over items in random order
     return  new randomizedQueueIterator();
   }
   
   private class randomizedQueueIterator implements Iterator<Item>{
       private int index;
       private Item[] list;
       public randomizedQueueIterator(){
    	   index=0;
    	   list=(Item[]) new Object[size];
    	   for (int i=0; i<size;i++){
    		   list[i]=a[i];
    		   StdRandom.shuffle(list);
    	   }
       }
	   @Override
	   public boolean hasNext() {
		  return index<size;
	   }

	    @Override
	    public Item next() {
	    	if(!hasNext()) throw new java.util.NoSuchElementException();
	    	Item item = list[index++];
	    	return item;
	   }

	    @Override
	    public void remove() {
	    	throw new java.lang.UnsupportedOperationException();
	    }
	   
	   
   }
   
   // resize the underlying array holding the elements
   private void resize(int capacity) {
       assert capacity >= size;
       Item[] temp = (Item[]) new Object[capacity];
       for (int i = 0; i < size; i++) {
           temp[i] = a[i];
       }
       a = temp;
   }
   
   
   public static void main(String[] args){   // unit testing
       RandomizedQueue<Integer> rq=new RandomizedQueue<Integer>();
	   for (int i=0; i<10;i++){
		   rq.enqueue(i);
	   }
	   System.out.println(Arrays.toString(rq.a));
	   rq.dequeue();
	   rq.dequeue();
	   rq.dequeue();
	   rq.dequeue();
	   rq.dequeue();
	   rq.dequeue();
	   System.out.println(Arrays.toString(rq.a));
	   
	   Iterator<Integer> it=rq.iterator();
	   while (it.hasNext()){
		   System.out.println(it.next());
	   }
	   
   }
}