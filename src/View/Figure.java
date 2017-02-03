package View;

import java.util.HashMap;
import java.util.Map;

import Model.Pixel;
import processing.core.PApplet;

import java.awt.Point;

public abstract class Figure {
	protected PApplet p;

	protected int x;
	protected int y;

	protected int width;
	protected int height;

	private int xOffset;
	private int yOffset;

	private boolean locked;

	protected Pixel pixel;

	private Map<Figure, Point> slaves;

	public Figure(PApplet p, int x, int y, int width, int height, Pixel pixel) {
		this.p = p;

		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;

		this.slaves = new HashMap<>();

		this.pixel = pixel;
	}

	public Figure(Figure master, Point pos, int width, int height, Pixel pixel) {
		this.p = master.p;

		this.x = master.x + pos.x;
		this.y = master.y + pos.y;

		this.width = width;
		this.height = height;

		this.pixel = pixel;

		this.slaves = new HashMap<>();
	}

	public boolean isSelected(int x, int y) {
		p.println(x + " " + y + " " + this.x + " " + this.x + " " + this.width + " " + this.height);
		if (x > this.x && x < this.x + this.width && y > this.y && y < this.y + this.height) {
			return true;
		} else {
			return false;
		}
	}

	public void display() {
		this.p.fill(this.pixel.r, this.pixel.g, this.pixel.b);

		this.draw();

		for (Figure c : slaves.keySet()) {
			c.display();
		}
	}

	protected abstract void draw();

	public void setPosition(int x, int y) {
		System.out.println("drag !");
		this.x = x - this.xOffset;
		this.y = y - this.yOffset;

		for (Map.Entry<Figure, Point> c : slaves.entrySet()) {
			c.getKey().setPosition(x, y);
		}
	}

	public void lock() {
		this.locked = true;
		this.xOffset = this.p.mouseX - this.x;
		this.yOffset = this.p.mouseY - this.y;

		for (Figure c : slaves.keySet()) {
			c.lock();
		}
	}

	public void unlock() {
		this.locked = false;

		for (Figure c : slaves.keySet()) {
			c.unlock();
		}
	}

	public void addSlave(Figure slave/* , int width, int height, Pixel pixel */) {
		slave.setSize(this.width - 10, slave.height);
		this.slaves.put(slave, new Point(slave.x, slave.y));
	}

	private void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public boolean isLocked() {
		return this.locked;
	}
}
