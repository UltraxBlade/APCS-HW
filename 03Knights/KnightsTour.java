
public class KnightsTour {
	public static void main (String[] args){
		KnightBoard b = new KnightBoard(10,10);
		b.solve();
		System.out.println(b);
		//System.out.println(b.movesBoardTest());
	}
}
