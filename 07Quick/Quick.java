
public class Quick {
	public static void quickSort(int[] arr){
		quickSorter(arr, 0, arr.length);
	}
	
	public static void quickSorter(int[] arr, int s, int e){
		if (s<e){
			int x = partition(arr, s, e);
			quickSorter(arr, s, x);
			quickSorter(arr, x+1, e);
		}
	}
	
	public static int quickSelect(int[] arr, int k){
		k--;
		int s = 0;
		int e = arr.length;
		int x=partition(arr, s, e);
		while(x!=k){
			if (k<x){
				e=x;
			} else {
				s=x+1;
			}
			x=partition(arr, s, e);
		}
		return arr[k];
	}
	
	private static int partition(int[] arr, int start, int end){
		int part=(int)(Math.random()*(end-start))+start;
		int temp = arr[end-1];
		arr[end-1]=arr[part];
		arr[part]=temp;
		int current=start;
		for (int i = start; i<end-1; i++){
			if(arr[i]<arr[end-1]){
				temp=arr[current];
				arr[current]=arr[i];
				arr[i]=temp;
				current++;
			}
		}
		temp=arr[current];
		arr[current]=arr[end-1];
		arr[end-1]=temp;
		return current;
	}
	
	public static void main(String[] args){
		int[] L = new int[10];
		int[] arr = new int[10];
		for (int i = 0; i<L.length; i++){
			L[i]=(int)(Math.random()*201)-100;
			arr[i]=L[i];
			System.out.print(L[i]);
			System.out.print(" ");
		}
		System.out.println();
		System.out.println(quickSelect(L, 4));
		quickSort(arr);
		for (int i = 0; i<arr.length; i++){
			System.out.print(arr[i]);
			System.out.print(" ");
		}
	}
}
