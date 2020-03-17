import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;

public class LoopsTwo extends PApplet {

	OpenSimplexNoise noise;
	boolean in_color = true;

	public void setup() {
		noise = new OpenSimplexNoise(69);
		background(10);
	}

	public void draw() {
		colorMode(RGB, 255);
		background(10);
		fill(20);
		noStroke();
		rect(0f, 0f, (float) width, (float) height, 80f);

		stroke(255, 128);
		noFill();
		translate(width / 2, height / 2);

		float a, x, y;
		float ts = 0.005f;
		float ns = 0.3f;
		int size = 350;
		int c = 30;

		colorMode(HSB, 100);
		Color col = new Color(0, 0, 100, 50);
		for (int i = 0; i < c; i++) {
			if (in_color)
				col = new Color(i, 70, 100, 100);

			stroke(col.getRGB());
//			Vector[] verts = new Vector[c];
			ArrayList<Vector> verts = new ArrayList<>();
			for (int r = 0; r < c; r++) {
				a = r / c * TWO_PI;
				x = (float) (noise.eval(cos(a) * ns, sin(a) * ns, 1.5 + frameCount * ts, i * 0.01) * size);
				y = (float) (noise.eval(cos(a) * ns, sin(a) * ns, 2.5 + frameCount * ts, i * 0.01) * size);

//				verts[r] = new Vector(x, y);
				verts.add(new Vector(x, y));
			}

			beginShape();
			for (int r = 0; r < c; r++) {
//				vertex(-verts[r].x, verts[r].y);
				vertex(-verts.get(r).x, verts.get(r).y);
			}
			endShape(CLOSE);

			beginShape();
			for (int r = 0; r < c; r++) {
//				vertex(verts[r].x, verts[r].y);
				vertex(verts.get(r).x, verts.get(r).y);
			}
			endShape(CLOSE);
		}
	}

}
