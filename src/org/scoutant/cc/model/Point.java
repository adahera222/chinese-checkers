package org.scoutant.cc.model;

public class Point implements org.scoutant.Serializable {
	public int i;
	public int j;
	
	public Point(int i, int j){
		this.i = i ;
		this.j = j;
	}
	public Point(Point p){
		this( p.i, p.j);
	}
	public Point(Peg peg){
		this(peg.point);
	}
	public Point clone() {
		return new Point(i,j);
	}
	
	public String toString(){
		return String.format("(%d, %d)", i, j);
	}
	
	/** --> */ 
	public void increment() {
		if ( odd(j)) i++;
	}
	/** <-- */ 
	public void decrement() {
		if ( even(j)) i--;
	}
	
	private static boolean odd(int value) {
		return (value % 2 > 0);
	}
	private static boolean even(int value) {
		return (value % 2 == 0);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		Point other = (Point) obj;
		return (i==other.i) && (j==other.j);
	}
	
	public boolean isOdd() {
		return j%2 == 1; 
	}
	public boolean isEven() {
		return j%2 == 0; 
	}
	@Override
	public String serialize() {
		return String.format( "%d,%d", i, j);
	}
}

///** @return a representation of the peg */
//public static String serialize(Point p) {
//	return String.format( ":%d:%d", p.i, p.j);
//}
