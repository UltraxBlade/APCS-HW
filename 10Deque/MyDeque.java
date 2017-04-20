import java.util.NoSuchElementException;

public class MyDeque {
	private String[] arr;
	private int front;
	private int back;
	
	public MyDeque(){
		arr=new String[1];
	}
	
	private void resize(){
		String[] temp = arr;
		arr=new String[temp.length*2];
		int n=0;
		for (int i = front; i!=back; i++){
			if (i>=temp.length){
				i=0;
			}
			arr[n]=temp[i];
			n++;
		}
		arr[n]=temp[back];
		front=0;
		back=n;
	}
	
	public void addFirst(String s){
		if (front==0){
			front=arr.length;
		}
		front--;
		if (front==back){
			resize();
			front=arr.length-1;
		}
		arr[front]=s;
	}
	
	public void addLast(String s){
		if (back==arr.length-1){
			back=-1;
		}
		back++;
		if (front==back){
			resize();
			back++;
		}
		arr[back]=s;
	}
	
	public String getFirst() throws NoSuchElementException{
		if (front==back){
			throw new NoSuchElementException();
		}
		return arr[front];
	}
	
	public String getLast() throws NoSuchElementException{
		if (front==back){
			throw new NoSuchElementException();
		}
		return arr[back];
	}
	
	public String removeFirst() throws NoSuchElementException{
		if (front==back){
			throw new NoSuchElementException();
		}
		String temp = arr[front];
		front++;
		if (front>=arr.length){
			front=0;
		}
		return temp;
	}
	
	public String removeLast() throws NoSuchElementException{
		if (front==back){
			throw new NoSuchElementException();
		}
		String temp = arr[back];
		back--;
		if (back<0){
			back=arr.length-1;
		}
		return temp;
	}
}

