
public class RunningMedians {
	MyHeap smaller;
	MyHeap larger;
	int sizeOfSmaller;
	int sizeOfLarger;
	
	public RunningMedians(){
		smaller=new MyHeap();
		sizeOfSmaller = 0;
		larger=new MyHeap(false);
		sizeOfLarger = 0;
	}
	
	public void add(int value){
		if (sizeOfLarger>0 && value>=larger.peek()){
			larger.add(value);
			if (sizeOfLarger>=sizeOfSmaller){
				smaller.add(larger.remove());
				sizeOfSmaller++;
			} else {
				sizeOfLarger++;
			}
		} else {
			smaller.add(value);
			if (sizeOfLarger>=sizeOfSmaller){
				sizeOfSmaller++;
			} else {
				larger.add(smaller.remove());
				sizeOfLarger++;
			}
		}
	}
	
	public double getMedian(){
		if (sizeOfSmaller==sizeOfLarger){
			return (smaller.peek()+larger.peek())/2.0;
		} else {
			return smaller.peek();
		}
	}
}
