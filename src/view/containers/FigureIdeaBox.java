package view.containers;

import java.awt.Point;

import model.Pixel;
import processing.core.PApplet;
import view.View;

public class FigureIdeaBox extends FigureTextContainer {

	public FigureIdeaBox(View p, int x, int y, int width, int height, Pixel pixel) {
		super(p, x, y, width, height, pixel);
	}

	public FigureIdeaBox(Figure master, Point pos, int width, int height, Pixel pixel) {
		super(master, pos, width, height, pixel);
	}

	@Override
	protected void init() {
		this.nbTexts = 5;

		for (int i = 0; i < this.nbTexts; i += 1) {
			this.addSlave(
					new FigureText(this, new Point(5, i * this.height / 5 + 5), 0, this.height / 6, new Pixel(128)));
		}
	}
}
