package Statistics;

public class Statistics {
	
	DoublyLinkedList<Double> dll = new DoublyLinkedList<Double>();
	
	//Given a DLL will return mean of set of numbers 
	public double mean(DoublyLinkedList<Double> dll) {
		
		LinkedListIterator<Double> listIterator = dll.iterator();
		
		double sum = listIterator.getCurrentElement();
		int count = 1;
		
		while(listIterator.hasNext()) {
			sum+=listIterator.next();
			count++;
		}
		
		return sum/count;
	}
	
	//Given a DLL will return standard deviation of a set of numbers
	public double standardDev(DoublyLinkedList<Double> dll) {
		
		LinkedListIterator<Double> listIterator = dll.iterator();
		
		double average = mean(dll);
		double sum = Math.pow(listIterator.getCurrentElement() - average, 2);
		int count = 0;
		
		while(listIterator.hasNext()) {
			sum += Math.pow(listIterator.next() - average, 2);
			count++;
		}
		
		return Math.sqrt((sum) / (count));
	}
	
	//returns covariance of x and y data sets which will be used to calculate correlation
	public double covariance(DoublyLinkedList<Double> x, DoublyLinkedList<Double> y){
		
		//create iterators for each list
		LinkedListIterator<Double> xIterator = x.iterator();
		LinkedListIterator<Double> yIterator = y.iterator();
		
		//calculate means for each list 
		double xAverage = mean(x);
		double yAverage = mean(y);
		
		double sumX = (xIterator.getCurrentElement() - xAverage);
		double sumY = (yIterator.getCurrentElement() - yAverage);
		double totalSum = sumX * sumY; 
		double count = 1;
		
		while(xIterator.hasNext() && yIterator.hasNext()) {
			totalSum += ((xIterator.next() - xAverage) * (yIterator.next() - yAverage));
			count++; 
		}
		
		return totalSum / (count-1);
	}
	
	public double variance(DoublyLinkedList<Double> list) {
		
		double average = mean(list);
		LinkedListIterator<Double> listIterator = list.iterator();
		double count = 1;
		
		double sum = (listIterator.getCurrentElement() - average);
		
		while(listIterator.hasNext()) {
			sum += (listIterator.next() - average);
			count ++;
		}
		
		return sum / (count -1);
	}

	//returns correlation of data by calling covariance method and standard dev of both data sets
	public double correlation(DoublyLinkedList<Double> x, DoublyLinkedList<Double> y) {
			
		double covariance = covariance(x, y);
		double stdX = standardDev(x);
		double stdY = standardDev(y);
			
		//r squared value 
		return covariance/(stdX * stdY);		
	}
	
	//returns zscore of list of data
    public DoublyLinkedList<Double> zScore(DoublyLinkedList<Double> dll) {
    	DoublyLinkedList<Double> zList = new DoublyLinkedList<Double>();
    	LinkedListIterator<Double> listIterator = dll.iterator();
        double mean = mean(dll);
        double standardDeviation = standardDev(dll);
        
        zList.addFirst((listIterator.getCurrentElement() - mean)/standardDeviation);
        while(listIterator.hasNext()) {
                zList.addLast((listIterator.next() - mean)/standardDeviation);
        }
        return zList;   
    }
    
    //returns list of normal probability distributions 
    public DoublyLinkedList<Double> normProb(DoublyLinkedList<Double> dll) {
    	DoublyLinkedList<Double> npList = new DoublyLinkedList<Double>();
    	LinkedListIterator<Double> listIterator = dll.iterator();
        
        npList.addFirst(riemann(listIterator.getCurrentElement()));
        while(listIterator.hasNext()) {
                npList.addLast(riemann(listIterator.next()));
        }         
        return npList;   
    }
    
    //returns riemann sum of data 
    public double riemann(double b) {
    	int n = 4;
    	double threshold = 0.00001;
    	double newEstimate = 0;
        double oldEstimate;
        double deltaX;
        
        deltaX = Math.abs(b) / n;
        for(int k = 1; k < n ; k++){
        	double i = (((2 * k - 1) / 2) * deltaX);
        	double j = (1 / (Math.sqrt(2 * Math.PI) * Math.exp((i * i) / 2)));
        	newEstimate = newEstimate + j * deltaX;
        }  
        do {
        		oldEstimate = newEstimate;
        		newEstimate = 0;
        		n = n * 2;
        		deltaX = Math.abs(b) / n;
        		
                for(int k = 1; k < n ; k++){
                	double i = (((2 * k - 1) / 2) * deltaX);
                	double j = (1 / (Math.sqrt(2 * Math.PI) * Math.exp((i * i) / 2)));
                	newEstimate = newEstimate + j * deltaX;
                }
       
        } while(Math.abs(oldEstimate - newEstimate) >= threshold);
        
        if(b < 0){
        	return (0.5 - newEstimate);
        }
        return (0.5 + newEstimate);   
    }
	
}
