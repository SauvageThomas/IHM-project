package view.containers;

import java.awt.Point;

import model.Pixel;
import view.View;

public class FigureAgenda extends FigureTextContainer {

	public FigureAgenda(View p, int x, int y, int width, int height, Pixel pixel) {
		super(p, x, y, width, height, pixel);
	}

	public FigureAgenda(Figure master, Point pos, int width, int height, Pixel pixel) {
		super(master, pos, width, height, pixel);
	}

	@Override
	protected void init() {
		this.nbTexts = 2;

		this.addSlave(new FigureText(this, new Point(5, 5), 0, this.height / 6, new Pixel(128)));
		this.addSlave(new FigureText(this, new Point(5, this.height / 6 + 30), 0,
				this.height - (this.height / 6 + 30 + 5), new Pixel(128)));
	}
}
