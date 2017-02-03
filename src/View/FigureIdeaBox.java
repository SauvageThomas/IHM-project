package View;

import java.awt.Point;

import Model.Pixel;
import processing.core.PApplet;

public class FigureIdeaBox extends FigureRectangle {

	public FigureIdeaBox(PApplet p, int x, int y, int width, int height, Pixel pixel) {
		super(p, x, y, width, height, pixel);
		this.init();
	}

	public FigureIdeaBox(Figure master, Point pos, int width, int height, Pixel pixel) {
		super(master, pos, width, height, pixel);
		this.init();
	}

	private void init() {
		for (int i = 0; i < 5; i += 1) {
			this.addSlave(
					new FigureText(this, new Point(5, i * this.height / 5 + 5), 0, this.height / 6, new Pixel(128)));
			System.out.println(i);
		}
	}

	@Override
	protected void draw() {
		for (int i = 0; i < 5; i += 1) {
			this.p.fill(this.pixel.r, this.pixel.g, this.pixel.b);
			this.p.rect(this.x, this.y, this.width, this.height);
		}
	}

}
