
public class Recursion {
	
	/* Floating point values cannot be directly compared with == due to possible 
	 * roundoff errors in the way they are stored. Instead, they must be compared by
	 * testing the difference against a predefined acceptable error value. Int values
	 * do not have this problem.
	 */
	public static final double ERROR = Math.pow(10, -15);
	
	public static String name(){
		return "Matthew Rabinowitz";
	}
	
	public static double sqrt (double n){
		if (n<0){
			throw new IllegalArgumentException();
		}
		return sqrtHelper(n, 1);
	}
	
	public static double sqrtHelper (double n, double guess){
		if (Math.abs(((guess * guess) - n)) <= ERROR){
			return guess;
		} else {
			return sqrtHelper(n, ((n/guess)+guess)/2);
		}
	}
	
	public static void main (String[] args){
		System.out.println(sqrt (100));
		System.out.println(Math.sqrt(100));
		System.out.println(sqrt (2));
		System.out.println(Math.sqrt(2));
	}
}
