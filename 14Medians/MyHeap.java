import java.util.NoSuchElementException;

public class MyHeap {
	private boolean isMax;
	private int size;
	private int[] heap;
	
	public MyHeap(){
		size=0;
		isMax=true;
		heap=new int[2];
	}
	
	public MyHeap(boolean maxHeap){
		if (maxHeap){
			isMax=true;
		} else {
			isMax=false;
		}
		size=0;
		heap=new int[2];
	}
	
	private int indexOfMaxChild(int i){
		if (i*2>size){
			return 0;
		} else if (i*2+1>size){
			return i*2;
		} else if ((isMax && heap[i*2] > heap[i*2+1]) || (!isMax && heap[i*2] < heap[i*2+1])){
			return i*2;
		} else {
			return i*2+1;
		}
	}
	
	private int pushDown(int i){
		int index=indexOfMaxChild(i);
		if (index==0){
			return 0;
		} else if ((isMax && heap[i] < heap[index]) || (!isMax && heap[i] > heap[index])) {
			int temp=heap[i];
			heap[i]=heap[index];
			heap[index]=temp;
			return index;
		} else {
			return 0;
		}
	}
	
	private int pushUp(int i){
		if (i/2==0){
			return 0;
		} else if ((isMax && heap[i] > heap[i/2]) || (!isMax && heap[i] < heap[i/2])){
			int temp=heap[i];
			heap[i]=heap[i/2];
			heap[i/2]=temp;
			return i/2;
		} else {
			return 0;
		}
	}
	
	private void resize(){
		int[] temp=heap;
		heap=new int[heap.length*2];
		for (int i = 0; i<temp.length; i++){
			heap[i]=temp[i];
		}
	}
	
	public void add(int value){
		size++;
		if(size>=heap.length){
			resize();
		}
		heap[size]=value;
		int i = size;
		while (i>0){
			i=pushUp(i);
		}
	}
	
	public int remove() throws NoSuchElementException {
		if (size<=0){
			throw new NoSuchElementException();
		} else {
			int toReturn=heap[1];
			heap[1]=heap[size];
			size--;
			int i=1;
			while(i>0){
				i=pushDown(i);
			}
			return toReturn;
		}
	}
	
	public int peek() throws NoSuchElementException {
		if (size<=0){
			throw new NoSuchElementException();
		} else {
			return heap[1];
		}
	}
}
