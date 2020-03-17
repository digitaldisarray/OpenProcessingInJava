import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

/*
 * Original code from user Michael Groufsky on openprocessing.org
 * 
 * https://www.openprocessing.org/sketch/105956
 * 
 * Modifications by me:
 *  - flicker
 *  - circleExp
 * 
 */

public class EXP012Separation extends PApplet {

	private PImage redImg, greenImg, blueImg;

	private boolean flicker = false;
	private int flickerAmmount = 10;

	private boolean circleExp = false;
	private float angle = 90;
	private int factor = 20;

	public void setup() {
		redImg = createChannel(255, 0, 0);
		greenImg = createChannel(0, 255, 0);
		blueImg = createChannel(0, 0, 255);
	}

	public void draw() {
		int x0 = (int) (.5 * width + .5);
		int y0 = (int) (.5 * height + .5);

		int dmouseX = 0;
		int dmouseY = 0;

		if (flicker) {
			dmouseX = (int) random(-flickerAmmount, flickerAmmount);
			dmouseY = (int) random(-flickerAmmount, flickerAmmount);
		} else if (circleExp) {
			dmouseX = (int) (factor * cos(angle));
			dmouseY = (int) (factor * sin(angle));
			angle -= .1f;
		} else {
			dmouseX = mouseX - pmouseX;
			dmouseY = mouseY - pmouseY;
		}

		background(255);
		drawChannel(redImg, x0 - dmouseX, y0 - dmouseY);
		drawChannel(greenImg, x0, y0);
		drawChannel(blueImg, x0 + dmouseX, y0 + dmouseY);
	}

	public void mouseClicked() {
		flicker = !flicker;
	}

	private PImage createChannel(float r, float g, float b) {
		PGraphics pg = createGraphics(200, 200);
		pg.beginDraw();
		pg.background(0x00);
		pg.stroke(r, g, b);
		pg.strokeWeight(20);
		pg.noFill();
		pg.ellipse(0.5f * pg.width, 0.5f * pg.height, pg.width - 22f, pg.height - 22f);
		pg.endDraw();
		return pg;
	}

	private void drawChannel(PImage img, int x, int y) {
		int u = img.width;
		int v = img.height;
		blend(img, 0, 0, u, v, (int) (x - 0.5 * u), (int) (y - 0.5 * v), u, v, SUBTRACT);
	}

}
