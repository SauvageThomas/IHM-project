package View;

import java.awt.Point;

import Model.Functions;
import Model.Pixel;
import processing.core.PApplet;

public class FigureText extends FigureRectangle {

	private String text = "";

	public FigureText(PApplet p, int x, int y, int width, int height, Pixel pixel) {
		super(p, x, y, width, height, pixel);
	}

	public FigureText(Figure master, Point pos, int width, int height, Pixel pixel) {
		super(master, pos, width, height, pixel);
	}

	@Override
	public void draw() {
		super.draw();
		this.p.fill(255);
		if (this.text != null) {
			this.p.text(this.text, this.x + 5, this.y + 12);
		}

	}

	public void addText(String text) {
		this.text += text;
	}

	@Override
	public void lock() {
		super.lock();
		if (this.isSelected(this.p.mouseX, this.p.mouseY) && this.p.mouseButton == this.p.RIGHT) {
			Functions.launchKeyboard();
			((View) this.p).setFocus(this);
		}
	}
}
