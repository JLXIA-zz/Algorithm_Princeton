
public class Subset {

	public static void main(String[] args){
		
	     int k=Integer.parseInt(args[0]);
	     RandomizedQueue<String> strings=new RandomizedQueue<String>();
	     for (int i=0; i<k; i++){
	    	 strings.enqueue(StdIn.readString());
	     }
	     for (int j=0; j<k; j++){
	    	 StdOut.println(strings.dequeue());
	     }
	     
	}
	
}
