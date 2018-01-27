package model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface Equation {

	Vector evaluate(Vector v);


//	private Entity entity;
//	private Method equation;
//
//	public Equation(Method equation){
//		this.equation = equation;
//	}
//
//	public void setEntity(Entity entity){
//		this.entity = entity;
//	}
//
//	public Vector computeFunction(Vector vector) throws InvocationTargetException, IllegalAccessException {
//		return (Vector) equation.invoke(vector);
//	}
//
//	public static void main(String[] args){
//		Equation eq = new Equation((Vector v) -> {return (v.getX()+v.getY());} );
//	}
}
