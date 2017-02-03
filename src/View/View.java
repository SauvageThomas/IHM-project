package View;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Model.Pixel;
import processing.core.PApplet;

public class View extends PApplet {

	List<Figure> figures = new ArrayList<>();
	FigureText focused;

	public void settings() {
		fullScreen();
	}

	public void setup() {
		int posXImage = width - 150 - 15;
		int posYImage = 175;

		smooth();
		frame.removeNotify();
		frame.setAlwaysOnTop(true);
		frame.setFocusableWindowState(false);
		frame.setFocusable(false);
		frame.enableInputMethods(false);

		Figure rect = new FigureRectangle(this, width / 2, height / 2, 240, 190, new Pixel(255));
		this.figures.add(rect);
		rect.addSlave(new FigureText(rect, new Point(5, 5), 0, 30, new Pixel(125)));

		Figure paintFigureImage = new FigureImage(this, posXImage, posYImage - 150, 150, 94, new Pixel(0), "paint.jpg");
		this.figures.add(paintFigureImage);

		Figure ideaFigureImage = new FigureImage(this, posXImage, posYImage, 150, 145, new Pixel(0), "idea_box.png");
		this.figures.add(ideaFigureImage);

		Figure textFigureImage = new FigureImage(this, posXImage, posYImage * 2, 105, 131, new Pixel(0), "text.png");
		this.figures.add(textFigureImage);

		Figure ideaFigure = new FigureIdeaBox(this, width / 2 + 60, height / 2 + 60, 500, 250, new Pixel(255));
		this.figures.add(ideaFigure);
	}

	public void draw() {
		background(0);
		/*
		 * try { Thread.sleep(500); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 */

		fill(255);
		for (Figure c : this.figures) {
			c.display();
		}

		// println(rect.isSelected(mouseX, mouseY));

	}

	public void keyPressed() {
		if (this.focused != null && key != 65535) {
			this.focused.addText(Character.toString(key));
		}
	}

	public void mousePressed() {
		for (Figure c : this.figures) {
			if (c.isSelected(mouseX, mouseY)) {
				c.lock();
			} else {
				c.unlock();
			}
		}
	}

	public void mouseDragged() {
		for (Figure c : this.figures) {
			if (c.isLocked()) {
				c.setPosition(mouseX, mouseY);
			}
		}
	}

	public void setFocus(FigureText figure) {
		this.focused = figure;
	}

	public static void main(String[] args) {
		PApplet.main("View.View");
	}
}
