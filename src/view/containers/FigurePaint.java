package view.containers;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.Pixel;
import processing.core.PConstants;
import view.View;

public class FigurePaint extends FigureRectangle {

	private List<Point> points;

	public FigurePaint(View p, int x, int y, int width, int height, Pixel pixel) {
		super(p, x, y, width, height, pixel);
		this.points = new ArrayList<>();
	}

	public FigurePaint(Figure master, Point pos, int width, int height, Pixel pixel) {
		super(master, pos, width, height, pixel);
		this.points = new ArrayList<>();
	}

	public void paint(int x, int y) {
		this.points.add(new Point(x - this.x, y - this.y));
	}

	@Override
	public void draw() {
		super.draw();
		this.p.ellipseMode(PConstants.CENTER);
		this.p.noStroke();
		this.p.fill(0);
		for (Point p : this.points) {
			this.p.ellipse(this.x + p.x, this.y + p.y, 5, 5);
		}
	}

}
