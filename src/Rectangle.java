import processing.core.PGraphics;
import java.awt.Color;

public class Rectangle extends MovingShape {
	public Rectangle(int xPos, int yPos, int width, int height, double xSpeed, double ySpeed, Color color, String tag){
		super(xPos, yPos, width, height, xSpeed, ySpeed, color, tag);
	}
	public Rectangle(int xPos, int yPos, int width, int height, Color color, String tag){
		super(xPos, yPos, width, height, 0, 0, color, tag);
	}
	void draw(PGraphics g){
		g.fill(getColor().getRGB());
		g.rect(getX(), getY(), getWidth(), getHeight());
	}

}
