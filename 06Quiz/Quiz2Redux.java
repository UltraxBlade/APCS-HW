
import java.util.ArrayList;
import java.util.Collections;

public class Quiz2Redux {
	public static ArrayList<String> combinations(String s){
		ArrayList<String> words = new ArrayList<String>();
		combiner(s, words, "", 0);
		Collections.sort(words);
		return words;
	}
	
	public static void combiner (String s, ArrayList<String> words, String combination, int i){
		if (i<s.length()){
			combiner(s, words, combination, i+1);
			combiner(s, words, combination+s.charAt(i), i+1);
		} else {
			words.add(combination);
		}
	}
}
