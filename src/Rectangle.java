import processing.core.PGraphics;
import java.awt.Color;

public class Rectangle extends MovingShape {
	public Rectangle(int xPos, int yPos, int width, int height, int xSpeed, int ySpeed, Color color){
		super(xPos, yPos, width, height, xSpeed, ySpeed, color);
	}
	public Rectangle(int xPos, int yPos, int width, int height, Color color){
		super(xPos, yPos, width, height,0,0, color);
	}
	
	void draw(PGraphics g){
		g.fill(getColor().getRGB());
		g.rect(getX(), getY(), getWidth(), getHeight());
	}

}
