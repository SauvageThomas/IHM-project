package view.containers;

import java.awt.Point;

import model.Pixel;
import processing.core.PApplet;
import view.View;

public class FigureRectangle extends Figure {

	public FigureRectangle(View p, int x, int y, int width, int height, Pixel pixel) {
		super(p, x, y, width, height, pixel);
	}

	public FigureRectangle(Figure master, Point pos, int width, int height, Pixel pixel) {
		super(master, pos, width, height, pixel);
	}

	@Override
	protected void draw() {
		this.p.fill(this.pixel.r, this.pixel.g, this.pixel.b);
		this.p.rect(this.x, this.y, this.width, this.height);
	}

}
