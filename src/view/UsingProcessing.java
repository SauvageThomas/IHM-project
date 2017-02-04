package view;
import processing.core.PApplet;
import processing.core.PFont;

import java.awt.Robot;
import java.awt.AWTException;

public class UsingProcessing extends PApplet {

	Robot robot;
	PFont font;
	Boxgrid b;
	int x, y, row, col;
	// int i = 0, j = 20, px, py, r = 5;
	int i = width, j = height , px, py, r = 5;
	String text = "";

	float ibx;
	float iby;
	int boxSize = 75;
	boolean overBox = false;
	boolean locked = false;
	float xOffset = 0;
	float yOffset = 0;

	int mX;
	int mY;

	int[][] letter = { { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, }, { 75, 76, 77, 32, 32, 78, 79, 80, 81, 82 },
			{ 83, 84, 85, 86, 87, 88, 89, 90, 8, 8 } };

	public void settings() {
		fullScreen();
	}

	public void setup() {
		noStroke();
		ibx = (float) (width / 2);
		iby = (float) (height / 2);
		// rectMode(RADIUS);

		smooth();
		frame.removeNotify();
		frame.setAlwaysOnTop(true);
		frame.setFocusableWindowState(false);
		frame.setFocusable(false);
		frame.enableInputMethods(false);
		try {
			robot = new Robot();
		} catch (AWTException a) {
			println(a);
			a.printStackTrace();
		}
		font = createFont("HelveticaNeueLight", 48);
		textFont(font);
		px = i;
		py = j;

	}

	public void draw() {
		background(0);

		rect(width - 21, 1, 19, 19, r, r, r, r);
		fill(150);
		textSize(18);
		text("X", width - 17, 17);
		fill(255);
		text(text, height / 2, width / 2);
		/*-----------------------------------------------------*/
		if (mousePressed && width - 21 < mouseX && mouseY < 20) {
			exit();
		}
		b = new Boxgrid(px, py);
		b.display();
		/*-----------------------------------------------------*/

		for (y = 20; y < height; y += 50) {
			for (x = 0; x < width; x += 50) {
				if (mouseX >= x && mouseX <= x + 50 && mouseY >= y && mouseY <= y + 50) {
					fill(0, 50);
					noStroke();
					if (x > 350 && y == 120) {
						if (x == 400) {
							rect(x, y, 100, 50, r, r, r, r);
						} else if (x == 450) {
							rect(x - 50, y, 100, 50, r, r, r, r);
						}
					} else if ((x == 150 || x == 200) && y == 70) {
						if (x == 150) {
							rect(x, y, 100, 50, r, r, r, r);
						} else if (x == 200) {
							rect(x - 50, y, 100, 50, r, r, r, r);
						}
					} else {
						rect(x, y, 50, 50, r, r, r, r);
					}
				}
			}
		}

		// Test if the cursor is over the box
		if (mouseX > ibx - boxSize && mouseX < ibx + boxSize && mouseY > iby - boxSize && mouseY < iby + boxSize) {
			overBox = true;
			if (!locked) {
				stroke(255);
				fill(153);
			}
		} else {
			stroke(153);
			fill(153);
			overBox = false;
		}
		// Draw the box
		rect(ibx, iby, boxSize, boxSize);

	}

	public void mousePressed() {

		if (overBox) {
			locked = true;
			fill(255, 255, 255);
		} else {
			locked = false;
		}
		xOffset = mouseX - ibx;
		yOffset = mouseY - iby;

		mX = mouseX;
		mY = mouseY;
		if (mouseY > 20) {
			for (y = 20; y < height - 20; y += 50) {
				for (x = 0; x < width; x += 50) {
					if (mouseX >= x && mouseX <= x + 50 && mouseY >= y && mouseY <= y + 50) {
						row = (y - 20) / 50;
						col = x / 50;
						if (row >= letter.length || col >= letter[0].length) {
							return;
						}
						println(row + " " + col);
						robot.keyPress(letter[row][col]);
						robot.keyRelease(letter[row][col]);
						println(Character.toString((char) letter[row][col]));
						text = Character.toString((char) letter[row][col]);

					}
				}
			}
		}
	}

	public void mouseDragged() {
		if (locked) {
			ibx = mouseX - xOffset;
			iby = mouseY - yOffset;
		}
		java.awt.Point p = java.awt.MouseInfo.getPointerInfo().getLocation();
		frame.setLocation(p.x - mX, p.y - mY);

	}

	public void mouseReleased() {
		locked = false;
	}

	class Boxgrid {
		int bx, by;
		int lx, ly;
		int r = 5;
		String[] words = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", " ", " ", "M", "N", "O", "P",
				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", " ", " " };

		Boxgrid(int _x, int _y) {
			bx = _x;
			by = _y;
		}

		void display() {
			for (int j = 0; j < 3; j++) {
				for (int i = 0; i < 10; i++) {
					lx = i * 50 + bx;
					ly = j * 50 + by;
					fill(255, 176, 3);
					stroke(0);
					strokeWeight(1);
					if (i == 3 && j == 1) {
						fill(0, 155, 255);
						rect(lx, ly, 100, 50, r, r, r, r);
						fill(255);
						textSize(25);
						text("SPACE", lx + 7, ly + 35);
						i = 4;
					} else if (i == 8 && j == 2) {
						fill(0, 155, 255);
						rect(lx, ly, 100, 50, r, r, r, r);
						fill(255);
						textSize(25);
						text("DEL", lx + 30, ly + 35);
						i = 9;
					} else {
						rect(lx, ly, 50, 50, r, r, r, r);
					}
					fill(255);
					textSize(30);
					if (j == 0) {
						text(words[i], lx + 15, ly + 35);
					}
					if (j == 1) {
						if (i == 4) {
							text(words[i + 10], lx - 35, ly + 35);
						} else {
							text(words[i + 10], lx + 15, ly + 35);
						}
					}
					if (j == 2) {
						text(words[i + 20], lx + 15, ly + 35);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		PApplet.main("UsingProcessing");
	}
}
