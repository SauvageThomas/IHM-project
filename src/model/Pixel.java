package model;

public class Pixel {
	public int r;
	public int g;
	public int b;

	public Pixel(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public Pixel(int grey) {
		this.r = grey;
		this.g = grey;
		this.b = grey;
	}
}
