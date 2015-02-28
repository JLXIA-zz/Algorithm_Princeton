
public class Percolation {
	
	private int rows;
	private int[] opens;
	private WeightedQuickUnionUF material;
	
	public Percolation(int N) throws java.lang.IllegalArgumentException{
		if (N<=0) throw  new java.lang.IllegalArgumentException("N must be larger than zero!");
	    material=new WeightedQuickUnionUF(N*N+2);
	    rows=N;
	    opens=new int[N*N];
	    
	}
	
	public void open(int i, int j) throws java.lang.IndexOutOfBoundsException{
		  if (i<1 || i> rows || j<1 || j> rows) throw new java.lang.IndexOutOfBoundsException("check input [i, j]");
		 
		 opens[(i-1)*rows+j-1]=1;
		 if (i==1) material.union((i-1)*rows+j-1, rows*rows);
		 if (i==rows) material.union((i-1)*rows+j-1, rows*rows+1);
		
		 if (i>1)     {if (opens[(i-2)*rows+j-1]==1) material.union((i-1)*rows+j-1, (i-2)*rows+j-1);}
		 if (i<rows)  {if (opens[(i-0)*rows+j-1]==1) material.union((i-1)*rows+j-1, (i-0)*rows+j-1);}
		 if (j>1)     {if (opens[(i-1)*rows+j-2]==1)  material.union((i-1)*rows+j-1, (i-1)*rows+j-2);}
		 if (j<rows)  {if (opens[(i-1)*rows+j-0]==1) material.union((i-1)*rows+j-1, (i-1)*rows+j-0);}

	}
	public boolean isOpen(int i, int j) throws java.lang.IndexOutOfBoundsException{
		if (i<1 || i> rows || j<1 || j> rows) throw new java.lang.IndexOutOfBoundsException("check input [i, j]");
		return opens[(i-1)*rows+j-1]==1;
	}
	public boolean isFull(int i, int j)throws java.lang.IndexOutOfBoundsException{
		if (i<1 || i> rows || j<1 || j> rows) throw new java.lang.IndexOutOfBoundsException("check input [i, j]");
		return material.connected((i-1)*rows+j-1, rows*rows);
	}
	public boolean percolates(){
		return material.connected(rows*rows, rows*rows+1);
	}
}
