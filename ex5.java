import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/*EX5 Luke Kraus*/

public class ex5{
    public static long calculate(long n){
        long inside = 0;
        double x;
		double y;
        for (int i = 0; i < n; i ++){
            x = ThreadLocalRandom.current().nextDouble(1);
            y = ThreadLocalRandom.current().nextDouble(1);
            if (((x*x) + (y*y)) < 1){ 
			inside++;}
        }
        return inside;
    }
    
    public static void main(String[] args) {
	
		long numThreads = 0;			
		long numIterations = 0;
		AtomicLong inside = new AtomicLong(0);
	    double pi = 0;

   
        numThreads = Long.parseLong(args[0]);
		numIterations = Long.parseLong(args[1]);
	        
	    long iterations = numIterations / numThreads;		

	    
	    Thread[] threads = new Thread[(int)numThreads];

	    for (int j = 0; j < (int)numThreads; j++){
	        final int w = j;
	        threads[w] = new Thread(() ->
	            {
	                inside.addAndGet(calculate(iterations));
	            });
	    }    

		try {
		    for (int i =0; i < numThreads; i++){
			threads[i].start();}
		    for (int i =0; i < numThreads; i++)
		    	threads[i].join();
		} catch (InterruptedException iex) {}

		// Print values
		System.out.println("Total:  " + numIterations);
		System.out.println("Inside " + inside.get());
		double ratio = (double)inside.get() / numIterations;
		System.out.println("Ratio: = " + ratio);
		System.out.println("Pi: " + ratio * 4.00);
    }	
}