package view;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.Pixel;
import processing.core.PApplet;
import processing.core.PConstants;
import view.containers.Figure;
import view.containers.FigureImage;
import view.containers.FigurePaint;
import view.containers.FigureRectangle;
import view.containers.FigureText;

public class View extends PApplet {

	List<Figure> figures = new ArrayList<>();
	FigureText focused;
	Figure buffer;
	Figure trash;

	public void settings() {
		fullScreen(SPAN);
	}

	public void setup() {
		int posXImage = width - 150 - 15;
		int posYImage = height;

		smooth();
		frame.removeNotify();
		frame.setAlwaysOnTop(true);
		frame.setFocusableWindowState(false);
		frame.setFocusable(false);
		frame.enableInputMethods(false);

		Figure paintFigureImage = new FigureImage(this, posXImage, posYImage - 150*7, 150, 94, new Pixel(0), "paint.jpg");
		this.figures.add(paintFigureImage);

		Figure ideaFigureImage = new FigureImage(this, posXImage-100, posYImage, 150*6, 145, new Pixel(0), "idea_box.png");
		this.figures.add(ideaFigureImage);

		Figure textFigureImage = new FigureImage(this, posXImage, posYImage -150*5, 105, 131, new Pixel(0), "text.png");
		this.figures.add(textFigureImage);

		Figure agendaFigureImage = new FigureImage(this, posXImage, posYImage -150* 4, 150, 113, new Pixel(0),
				"agenda.png");
		this.figures.add(agendaFigureImage);
		
		Figure cafeFigureImage = new FigureImage(this, posXImage, posYImage -150* 3, 150, 113, new Pixel(0),
				"cafe.jpg");
		this.figures.add(cafeFigureImage);
		
		Figure menuFigureImage = new FigureImage(this, posXImage, posYImage -150* 2, 150, 113, new Pixel(0),
				"menu.jpg");
		this.figures.add(menuFigureImage);

		trash = new FigureImage(this, posXImage, posYImage-150, 150, 159, new Pixel(0), "trash.jpg");

		Figure rect = new FigureRectangle(this, width / 2, height / 2, 240, 190, new Pixel(255));
		//this.figures.add(rect);
		rect.addSlave(new FigureText(rect, new Point(5, 5), 0, 30, new Pixel(125)));
	}

	public void draw() {
		background(0);
		/*
		 * try { Thread.sleep(500); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 */

		fill(255);
		trash.display();
		for (Figure c : this.figures) {
			c.display();
		}

		// println(rect.isSelected(mouseX, mouseY));

	}

	public void keyPressed() {
		if (this.focused != null && key != 65535) {
			this.focused.addText(key);
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

	public void mouseReleased() {
		System.out.println("Mouse released");

		if (this.buffer != null) {
			this.figures.add(this.buffer);
			this.buffer = null;
		}

		if (trash.isSelected(mouseX, mouseY)) {
			this.deleteFigure();
		}

	}

	public void mouseDragged() {
		for (Figure c : this.figures) {
			if (c.isLocked()) {
				if (c instanceof FigurePaint && this.mouseButton == PConstants.RIGHT) {
					((FigurePaint) c).paint(mouseX, mouseY);
				} else {
					c.setPosition(mouseX, mouseY);
				}
			}
		}
	}

	private void deleteFigure() {

		int i = 0;
		boolean found = false;
		for (Figure c : this.figures) {
			if (c.isLocked()) {
				found = true;
				break;
			}
			i += 1;
		}
		if (found) {
			this.figures.remove(i);
		}
	}

	public void setFocus(FigureText figure) {
		this.focused = figure;
	}

	public void addFigure(Figure figure) {
		this.buffer = figure;
	}

	public static void main(String[] args) {
		PApplet.main("view.View");
	}
}
