package model;

import java.util.Iterator;

public class Vector {
	double x;
	double y;

	public Vector(double x, double y){
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void change(Vector delta){
		x+= delta.x;
		y+= delta.y;
	}

	public static Vector scalar(double scalar, Vector v){
		return new Vector(v.x*scalar,v.y*scalar);
	}

	public static double dotProduct(Vector v1, Vector v2){
		return v1.getX()*v2.getX() + v1.getY()*v2.getY();
	}

	public double getLength(){
		//Pythagoras
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	/**
	 * Add a collection
	 * @param vectors
	 * @return
	 */
	public static Vector addVectors(Iterable<Vector> vectors) {
		double xResult = 0;
		double yResult = 0;
		for (Vector vector : vectors) {
			xResult += vector.getX();
			yResult += vector.getY();
		}
		return new Vector(xResult, yResult);
	}
}
