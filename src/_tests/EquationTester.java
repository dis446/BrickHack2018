package _tests;
import model.Equation;
import model.Vector;

public class EquationTester {
	public static void main(String[] args){
		Equation eq = (Vector v) -> {
			return new Vector(v.getX()+1,v.getY()+2);
		};
		System.out.println(eq.evaluate(new Vector(1,2)).getX());
	}
}
