
import processing.core.PApplet;
import processing.core.PConstants;

/*
 * Original code from user yasai on openprocessing.org
 * 
 * https://www.openprocessing.org/sketch/494102
 * 
 */

public class PerlinNoise extends PApplet {

	public int nums = 200;
	public static int noiseScale = 800;
	Particle[] particles_a = new Particle[nums];
	Particle[] particles_b = new Particle[nums];
	Particle[] particles_c = new Particle[nums];

	public void setup() {
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		background(21, 8, 50);
		for (int i = 0; i < nums; i++) {
			particles_a[i] = new Particle(random(0, width), random(0, height));
			particles_b[i] = new Particle(random(0, width), random(0, height));
			particles_c[i] = new Particle(random(0, width), random(0, height));
		}
	}

	public void draw() {
		noStroke();
		smooth();

		for (int i = 0; i < nums; i++) {
			float radius = map(i, 0, nums, 1, 2);
			float alpha = map(i, 0, nums, 0, 250);

			fill(69, 33, 124, alpha);
			particles_a[i].move(this);
			particles_a[i].draw(this, radius);
			particles_a[i].checkEdge(this);

			fill(7, 153, 242, alpha);
			particles_b[i].move(this);
			particles_b[i].draw(this, radius);
			particles_b[i].checkEdge(this);

			fill(255, 255, 255, alpha);
			particles_c[i].move(this);
			particles_c[i].draw(this, radius);
			particles_c[i].checkEdge(this);
		}
	}
}

class Particle {
	private Vector dir;
	private Vector vel;
	private Vector pos;
	private float speed = 0.4f;

	public Particle(float x, float y) {
		dir = new Vector(0, 0);
		vel = new Vector(0, 0);
		pos = new Vector(x, y);
	}

	public void move(PApplet g) {
		double angle = g.noise(pos.x / PerlinNoise.noiseScale, pos.y / PerlinNoise.noiseScale) * PConstants.TWO_PI
				* PerlinNoise.noiseScale;
		dir.x = (float) Math.cos(angle);
		dir.y = (float) Math.sin(angle);
		vel = new Vector(dir.x, dir.y);
		vel.mult(speed);
		pos.add(vel);
	}

	public void checkEdge(PApplet g) {
		if (pos.x > g.width || pos.x < 0 || pos.y > g.height || pos.y < 0) {
			pos.x = g.random(50, g.width);
			pos.y = g.random(50, g.height);
		}
	}

	public void draw(PApplet g, float radius) {
		g.ellipse(pos.x, pos.y, radius, radius);
	}
}