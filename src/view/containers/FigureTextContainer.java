package view.containers;

import java.awt.Point;

import model.Pixel;
import view.View;

public class FigureTextContainer extends FigureRectangle {

	protected int nbTexts;

	public FigureTextContainer(View p, int x, int y, int width, int height, Pixel pixel) {
		super(p, x, y, width, height, pixel);
		this.init();
	}

	public FigureTextContainer(Figure master, Point pos, int width, int height, Pixel pixel) {
		super(master, pos, width, height, pixel);
		this.init();
	}

	protected void init() {
		this.nbTexts = 1;

		this.addSlave(new FigureText(this, new Point(0, 0), this.width, this.height, new Pixel(128)));
	}

	@Override
	protected void draw() {
		for (int i = 0; i < this.nbTexts; i += 1) {
			this.p.fill(this.pixel.r, this.pixel.g, this.pixel.b);
			this.p.rect(this.x, this.y, this.width, this.height);
		}
	}
}
