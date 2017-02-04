package view.containers;

import java.awt.Point;

import model.Functions;
import model.Pixel;
import processing.core.PApplet;
import processing.core.PConstants;
import view.View;

public class FigureText extends FigureRectangle {

	private String text = "";

	public FigureText(View p, int x, int y, int width, int height, Pixel pixel) {
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

	public void addText(char c) {
		// Backspace
		System.out.println((int) c);
		if ((int) c == 8) {
			this.text = this.text.substring(0, this.text.length() - 1);
		} else {
			this.text += c;
		}
	}

	@Override
	public void lock() {
		super.lock();
		if (this.isSelected(this.p.mouseX, this.p.mouseY) && this.p.mouseButton == PConstants.RIGHT) {
			Functions.launchKeyboard();
			((View) this.p).setFocus(this);
		}
	}
}
