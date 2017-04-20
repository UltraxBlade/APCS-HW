import java.util.Iterator;

public class MyLinkedList implements Iterable<Integer>{
	private LNode start;
	private LNode end;
	private int size;
	
	private LNode getNthNode(int index) throws IndexOutOfBoundsException {
		if (index>=0 && index<size){
			if (index<size/2){
				LNode current=start;
				for(int i = 0; i<index; i++){
					current=current.next;
				}
				return current;
			} else {
				LNode current=end;
				for(int i = size-1; i>index; i--){
					current=current.prev;
				}
				return current;
			}
		} else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public Iterator<Integer> iterator(){
		return new MyLinkedListIterator();
	}
	
	private void addAfter(LNode location, LNode toBeAdded){
		if (location==end){
			location.next=toBeAdded;
			toBeAdded.prev=location;
			end=toBeAdded;
		} else {
			location.next.prev=toBeAdded;
			toBeAdded.next=location.next;
			toBeAdded.prev=location;
			location.next=toBeAdded;
		}
		size++;
	}
	
	private void addBefore(LNode location, LNode toBeAdded){
		if (location==start){
			location.prev=toBeAdded;
			toBeAdded.next=location;
			start=toBeAdded;
		} else {
			location.prev.next=toBeAdded;
			toBeAdded.next=location;
			toBeAdded.prev=location.prev;
			location.prev=toBeAdded;
		}
		size++;
	}
	
	private void remove(LNode toRemove){
		if (end==toRemove){
			toRemove.prev.next=null;
			end=toRemove.prev;
		} else if (start==toRemove){
			toRemove.next.prev=null;
			start=toRemove.next;
		} else {
			toRemove.next.prev=toRemove.prev;
			toRemove.prev.next=toRemove.prev;
		}
		size--;
	}
	
	public boolean add(int value){
		add(size, value);
		return true;
	}
	
	public void add(int index, int value) throws IndexOutOfBoundsException{
		if (index>=0 && index<size){
			addBefore(getNthNode(index), new LNode(value));
		} else if (index==size){
			LNode toBeAdded=new LNode(value);
			if (end==null){
				start=toBeAdded;
				end=start;
				size++;
			} else {
				addAfter(end, toBeAdded);
			}
		}
	}
	
	public int remove(int index) throws IndexOutOfBoundsException{
		int toReturn=getNthNode(index).data;
		remove(getNthNode(index));
		return toReturn;
	}
	
	public int get(int index) throws IndexOutOfBoundsException{
		return getNthNode(index).data;
	}
	
	public int set (int index, int value) throws IndexOutOfBoundsException{
		int toReturn=getNthNode(index).data;
		getNthNode(index).data=value;
		return toReturn;
	}
	
	public int indexOf(int value){
		LNode current=start;
		for(int i = 0; i<size; i++){
			if (current.data==value){
				return current.data;
			}
			current=current.next;
		}
		return -1;
	}
	
	public int size(){
		return size;
	}
	
	public String toString(){
		if (start!=null){
			String s = "[";
			LNode current=start;
			for(int i = 0; i<size; i++){
				s=s+current;
				if(i<size-1){
					s=s+",";
				}
				current=current.next;
			}
			s=s+"]";
			return s;
		} else {
			return "[]";
		}
	}
	
	private class LNode{
		public int data;
		public LNode next;
		public LNode prev;
		
		public LNode(int n){
			data=n;
		}
		
		public String toString(){
			return data+"";
		}
	}
	
	private class MyLinkedListIterator implements Iterator<Integer>{
		private LNode next;
		
		public MyLinkedListIterator(){
			next=start;
		}
		
		public boolean hasNext(){
			return !(next==null);
		}
		
		public Integer next(){
			Integer toReturn=new Integer(next.data);
			next=next.next;
			return toReturn;
		}
	}
}