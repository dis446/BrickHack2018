package model;

import java.util.Objects;

public class Vector {
	double x;
	double y;

	public Vector(double x, double y){
		this.x = x;
		this.y = y;
	}

	/**
	 * Null vector constructor.
	 */
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

	/**
	 * Mutate this vector with another vector.
	 * @param delta
	 */
	public void change(Vector delta){
		x+= delta.x;
		y+= delta.y;
	}


	/**
	 * v1 + v2
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static Vector add(Vector v1,Vector v2){
		return new Vector(v1.x+v2.x,v1.y+v2.y);
	}

	/**
	 * v1 - v2.
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static Vector sub(Vector v1,Vector v2){
		return new Vector(v1.x-v2.x,v1.y-v2.y);
	}

	/**
	 * Get the unit vector for this.
	 * @return
	 */
	public Vector getUnitVector(){
		double length = getLength();
		if(length == 0){
			return new Vector();
		}
		return scalar(1/getLength(),this);
	}

	/**
	 * Scalar multiplication of a vector.
	 * @param scalar
	 * @param v
	 * @return
	 */
	public static Vector scalar(double scalar, Vector v){
		return new Vector(v.x*scalar,v.y*scalar);
	}

	/**
	 * Dot product two given vectors
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double dotProduct(Vector v1, Vector v2){
		return v1.getX()*v2.getX() + v1.getY()*v2.getY();
	}

	/**
	 * Return the pythagorean length of this vector
	 * @return
	 */
	public double getLength(){
		//Pythagoras thank you
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	/**
	 * Return a new vector that is the vector sum given a collection of vectors.
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

	/**
	 * Two vectors are equal if they have the same x and y component.
	 * @param o
	 * @return
	 */
	@Override
	public boolean equals(Object o) {
		if(o instanceof Vector){
			Vector v = (Vector) o;
			return v.x==x && v.y==y;
		}
		return false;
	}

	/**
	 * The hash code is based of the x and y components.
	 * @return
	 */
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	/**
	 * The string format is <x , y>
	 * @return
	 */
	@Override
	public String toString() {
		return "<"+x+" , "+y+">";
	}
}
