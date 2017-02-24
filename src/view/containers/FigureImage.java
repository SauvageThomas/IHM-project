package view.containers;

import java.awt.Point;

import model.Pixel;
import processing.core.PApplet;
import processing.core.PImage;
import view.View;

public class FigureImage extends Figure {

	private PImage image;
	protected String type;
	private boolean son = false;

	public FigureImage(View p, int x, int y, int width, int height, Pixel pixel, String image) {
		super(p, x, y, width, height, pixel);

		this.init(image);
	}

	public FigureImage(View p, int x, int y, int width, int height, Pixel pixel, String image, boolean son) {
		super(p, x, y, width, height, pixel);
		this.son = son;
		this.init(image);
	}

	public FigureImage(Figure master, Point pos, int width, int height, Pixel pixel, String image) {
		super(master, pos, width, height, pixel);

		this.init(image);
	}

	private void init(String image) {
		this.type = image.split("\\.")[0];
		this.image = this.p.loadImage(image);
	}

	@Override
	protected void draw() {
		this.p.image(image, x, y);
	}

	@Override
	public void setPosition(int x, int y) {
		super.setPosition(x, y);
		if (this.son) {
			return;
		}
		// This can't be dragged so no super
		Figure figure = null;

		switch (type) {
		case "paint":
			System.out.println("paint !");
			figure = new FigurePaint(this.p, this.p.mouseX, this.p.mouseY, 500, 250, new Pixel(255));
			break;
		case "text":
			System.out.println("text !");
			figure = new FigureTextContainer(this.p, this.p.mouseX, this.p.mouseY, 500, 250, new Pixel(255));
			break;
		case "idea_box":
			System.out.println("idea box !");
			figure = new FigureIdeaBox(this.p, this.p.mouseX, this.p.mouseY, 500, 250, new Pixel(255));
			break;
		case "agenda":
			System.out.println("agenda !");
			figure = new FigureAgenda(this.p, this.p.mouseX, this.p.mouseY, 500, 250, new Pixel(255));
			break;
		case "cafe":
			System.out.println("café !");
			figure = new FigureImage(this.p, this.p.mouseX, this.p.mouseY, 100, 100, new Pixel(255), "cafe.jpg", true);
			break;
		case "menu":
			System.out.println("menu !");
			figure = new FigureImage(this.p, this.p.mouseX, this.p.mouseY, 100, 100, new Pixel(255), "menu.jpg", true);
			break;
		default:
			break;
		}
		if (figure != null) {
			this.p.addFigure(figure);
		}
	}

	public boolean isSon() {
		return this.son;
	}

}
