public class Quick {
	public static void quickSort(int[] arr){
		quickSorter(arr, 0, arr.length);
	}
	
	public static void quickSorter(int[] arr, int s, int e){
		if (s<e-1){
			int[] x = partition(arr, s, e);
			quickSorter(arr, s, x[0]);
			quickSorter(arr, x[1]+1, e);
		}
	}
	
	public static int quickSelect(int[] arr, int k){
		k--;
		int s = 0;
		int e = arr.length;
		int[] x=partition(arr, s, e);
		while((x[0]>k || x[1]<k) && s<e-1){
			if (k<x[0]){
				e=x[0];
			} else {
				s=x[1]+1;
			}
			x=partition(arr, s, e);
		}
		return arr[k];
	}
	
	private static int[] partition(int[] arr, int start, int end){
		int part=(int)(Math.random()*(end-start))+start;
		int temp = arr[end-1];
		arr[end-1]=arr[part];
		arr[part]=temp;
		int less=start;
		int greater=end-2;
		int i = start;
		while (i<=greater){
			if (arr[i]==arr[end-1]){
				i++;
			} else if(arr[i]<arr[end-1]){
				temp=arr[less];
				arr[less]=arr[i];
				arr[i]=temp;
				less++;
				i++;
			} else {
				temp=arr[greater];
				arr[greater]=arr[i];
				arr[i]=temp;
				greater--;
			}
		}
		greater++;
		temp=arr[greater];
		arr[greater]=arr[end-1];
		arr[end-1]=temp;
		int[] result = {less, greater};
		return result;
	}
}
