import java.util.Stack;

public class StackCalc {
	public static double eval(String expression){
		String[] tokens = expression.split(" ");
		Stack<Double> values = new Stack<Double>();
		for (String token:tokens){
			if (isOp(token)){
				values.push(evaluate(token, values.pop(), values.pop()));
			} else {
				values.push(Double.parseDouble(token));
			}
		}
		return values.pop();
	}
	
	private static double evaluate (String op, double num2, double num1){
		if (op.equals("+")){
			return num1 + num2;
		} else if (op.equals("-")){
			return num1 - num2;
		} else if (op.equals("*")){
			return num1 * num2;
		} else if (op.equals("/")){
			return num1 / num2;
		} else {
			return num1 % num2;
		}
	}
	
	private static boolean isOp(String s){
		if (s.equals("+") || s.equals("-") ||
				s.equals("*") || s.equals("/") || s.equals("%")){
			return true;
		} else {
			return false;
		}
	}
}
