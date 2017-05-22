import java.util.NoSuchElementException;

public class MyHeap {
	private int minMax;
	private int size;
	private Location[] heap;
	
	public MyHeap(){
		size=0;
		minMax=-1;
		heap=new Location[2];
	}
	
	public MyHeap(boolean isMax){
		if (isMax){
			minMax=-1;
		} else {
			minMax=1;
		}
		size=0;
		heap=new Location[2];
	}
	
	private int indexOfMaxChild(int i){
		if (i*2>size){
			return 0;
		} else if (i*2+1>size){
			return i*2;
		} else if (heap[i*2].compareTo(heap[i*2+1])*minMax > 0){
			return i*2+1;
		} else {
			return i*2;
		}
	}
	
	private int pushDown(int i){
		int index=indexOfMaxChild(i);
		if (index==0){
			return 0;
		} else if (heap[index].compareTo(heap[i])*minMax < 0) {
			Location temp=heap[i];
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
		} else if (heap[i/2].compareTo(heap[i])*minMax > 0){
			Location temp=heap[i];
			heap[i]=heap[i/2];
			heap[i/2]=temp;
			return i/2;
		} else {
			return 0;
		}
	}
	
	private void resize(){
		Location[] temp=heap;
		heap=new Location[heap.length*2];
		for (int i = 0; i<temp.length; i++){
			heap[i]=temp[i];
		}
	}
	
	public void add(Location value){
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
	
	public Location remove() throws NoSuchElementException {
		if (size<=0){
			throw new NoSuchElementException();
		} else {
			Location toReturn=heap[1];
			heap[1]=heap[size];
			size--;
			int i=1;
			while(i>0){
				i=pushDown(i);
			}
			return toReturn;
		}
	}
	
	public Location peek() throws NoSuchElementException {
		if (size<=0){
			throw new NoSuchElementException();
		} else {
			return heap[1];
		}
	}
}
