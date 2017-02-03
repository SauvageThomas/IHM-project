package View;

import java.awt.Point;

import Model.Pixel;
import processing.core.PApplet;
import processing.core.PImage;

public class FigureImage extends Figure {
	
	private PImage image;
	

	public FigureImage(PApplet p, int x, int y, int width, int height, Pixel pixel, String image) {
		super(p, x, y, width, height, pixel);
		this.image = this.p.loadImage(image);
	}

	public FigureImage(Figure master, Point pos, int width, int height, Pixel pixel, String image) {
		super(master, pos, width, height, pixel);
		this.image = this.p.loadImage(image);
	}

	@Override
	protected void draw() {
		this.p.image(image, x, y);
	}
	
	@Override
	public void setPosition(int x, int y) {
		//This can't be dragged
	}

}
