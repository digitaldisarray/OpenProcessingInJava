import processing.core.PApplet;
import processing.core.PConstants;

/*
 * Original code from user Joseph Aronson on openprocessing.org
 * 
 * https://www.openprocessing.org/sketch/681455
 * 
 */

public class SpiralPulse extends PApplet {

	private float a;
	private float h;
	private float d;

	public void setup() {
		colorMode(PConstants.HSB, 1);
		a = 0;
		h = 1;
		d = 1000;
	}

	public void draw() {
		translate(d / 2, d / 2);
		o(d);
		a += .001f;
		h += .003f;
	}

	private void o(float s) {
		rotate(a);
		fill((h + s / d) % 1, 1, 1);
		ellipse(s / cos(h), s / sin(h), s, s / cos(a));

		if (s > 9)
			o(s * .95f);
	}

}
