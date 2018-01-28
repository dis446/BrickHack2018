package model;

import java.util.Iterator;

public class Vector {
	double x;
	double y;

	public Vector(double x, double y){
		this.x = x;
		this.y = y;
	}

	public Vector(){
		this(0,0);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x){
		this.x = x;
	}

	public void setY(double y){
		this.y = y;
	}

	public void change(Vector delta){
		x+= delta.x;
		y+= delta.y;
	}

	public static Vector add(Vector v1,Vector v2){
		return new Vector(v1.x+v2.x,v1.y+v2.y);
	}

	public static Vector sub(Vector v1,Vector v2){
		return new Vector(v1.x-v2.x,v1.y-v2.y);
	}

	public Vector getUnit(){
		double length = getLength();
		if(length == 0){
			return new Vector();
		}
		return scalar(1/getLength(),this);
	}

	public static Vector scalar(double scalar, Vector v){
		return new Vector(v.x*scalar,v.y*scalar);
	}

	public static double dotProduct(Vector v1, Vector v2){
		return v1.getX()*v2.getX() + v1.getY()*v2.getY();
	}

	public double getLength(){
		//Pythagora
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	/**
	 * Add a collection
	 * @param vectors
	 * @return
	 */
	public static Vector addVectors(Iterable<Vector> vectors) {
		Vector result = new Vector();
		for (Vector vector : vectors) {
			result.change(vector);
		}
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Vector){
			Vector v = (Vector) o;
			return v.x==x && v.y==y;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (int) (x+y);
	}

	@Override
	public String toString() {
		return "<"+x+" , "+y+">";
	}
}
