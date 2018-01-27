package model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Equation {

	private Entity entity;
	private Method equation;

	public Equation(Method equation){
		this.equation = equation;
	}

	public void setEntity(Entity entity){
		this.entity = entity;
	}

	public Vector computeFunction(Vector vector) throws InvocationTargetException, IllegalAccessException {
		return (Vector) equation.invoke(vector);
	}


}
