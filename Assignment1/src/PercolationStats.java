import java.util.Random;

public class PercolationStats {

	
	private double[] thresholds;
	private Percolation experiments;
	
	public PercolationStats(int N, int T) throws java.lang.IllegalArgumentException{
		if (N<=0 ||T<=0)  throw new java.lang.IllegalArgumentException("Check N and T");
		thresholds=new double[T];
		Random random1=new Random();
		Random random2=new Random();
		for (int i=0; i<T; i++){
			double threshold=0;
			experiments=new Percolation(N);
            while (! experiments.percolates()){
            	int row= random1.nextInt(N)+1;
            	int column=random2.nextInt(N)+1;
            	if (! experiments.isOpen(row, column)){    
            		experiments.open(row, column);
            	    threshold++;
            	}
            	}
            thresholds[i]=threshold/(N*N);
		}
	}
	
	public double mean(){
		double sum=0;
		for (int i=0; i<thresholds.length; i++ ){
			sum+=thresholds[i];
		}
		return sum/thresholds.length;
	}
	
	public double stddev(){
		double average=this.mean();
		double dev=0;
		for (int i=0; i<thresholds.length; i++){
			dev+=Math.pow((thresholds[i]-average), 2.0);
		}
		return Math.sqrt(dev/(thresholds.length-1));
	}
	
	public double confidenceLo(){
		 if (thresholds.length>30) return this.mean()-1.96*this.stddev()/Math.sqrt(thresholds.length);
		 else return Double.NaN ; 
	}
	
	public double confidenceHi() {
		if (thresholds.length>30) return this.mean()+1.96*this.stddev()/Math.sqrt(thresholds.length);
		else return Double.NaN ; 
	}
	
	public static void main (String[] args){
		PercolationStats experiment=new PercolationStats(200,100);
		System.out.println("mean:                     ="+ experiment.mean());
		System.out.println("stddev:                   ="+ experiment.stddev());
		System.out.println("95% confidence interval:  ="+ experiment.confidenceLo()+", "+experiment.confidenceHi());
  
 	}
}
