
public class Merge {
	private static void merge(int[] arr1, int[] arr2, int[] destination){
		int i = 0;
		int j = 0;
		int n = 0;
		while(i<arr1.length && j<arr2.length && n<destination.length){
			if (arr1[i]<arr2[j]){
				destination[n]=arr2[j];
				j++;
			} else {
				destination[n]=arr1[i];
				i++;
			}
			n++;
		}
		while (i<arr1.length){
			destination[n]=arr1[i];
			i++;
		}
		while (i<arr2.length){
			destination[n]=arr2[i];
			i++;
		}
	}
	
	public static void mergesort(int[] arr){
		if (!(arr.length<=1)){
			int m = arr.length/2;
			int[] left = new int[m];
			int[] right = new int[arr.length-m];
			for (int i = 0; i<m; i++){
				left[i]=arr[i];
			}
			for (int i = 0; i<arr.length-m; i++){
				right[i]=arr[i+m];
			}
			mergesort(left);
			mergesort(right);
			merge(left, right, arr);
		}
	}
}
