import java.awt.Color;
import processing.core.PGraphics;
import processing.core.PImage;

public class Image extends MovingShape{
	private PImage image;
	public Image(int xPos, int yPos, double xScale, double yScale, int xSpeed, int ySpeed, PImage image){
		super(xPos, yPos, (int)(image.width*xScale), (int)(image.height*yScale), xSpeed, ySpeed, Color.white);
		this.image = image;
	}
	public Image(int xPos, int yPos, double xScale, double yScale, PImage image){
		super(xPos, yPos, (int)(image.width*xScale), (int)(image.height*yScale), 0, 0, Color.white);
		this.image = image;
	}
	
	public void draw(PGraphics g){
		g.image(image,getX(),getY(),getWidth(),getHeight());
	}

	public PImage getImage(){
		return image;
	}
}
