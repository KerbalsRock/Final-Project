import java.awt.Color;
import processing.core.PGraphics;
import processing.core.PImage;

public class Image extends MovingShape{
	private PImage image;
	private double xScale;
	private double yScale;
	public Image(int xPos, int yPos, double xScale, double yScale, double xSpeed, double ySpeed, PImage image){
		super(xPos, yPos, (int)(image.width*xScale), (int)(image.height*yScale), xSpeed, ySpeed, Color.white);
		this.image = image;
		this.xScale = xScale;
		this.yScale = yScale;
	}
	public Image(int xPos, int yPos, double xScale, double yScale, PImage image){
		super(xPos, yPos, (int)(image.width*xScale), (int)(image.height*yScale), 0, 0, Color.white);
		this.image = image;
		this.xScale = xScale;
		this.yScale = yScale;
	}
	public Image(double xScale, double yScale, PImage image){
		super(0, 0, (int)(image.width*xScale), (int)(image.height*yScale), 0, 0, Color.white);
		this.image = image;
		this.xScale = xScale;
		this.yScale = yScale;
	}
	
	public void draw(PGraphics g){
		g.image(image, getX(), getY(), getWidth(), getHeight());
	}

	public PImage getImage(){
		return image;
	}public void setImage(PImage img){
		image = img;
	}
	public void setScale(double xScale, double yScale){
		this.xScale = xScale;
		this.yScale = yScale;
		setWidth((int)(image.width*xScale));
		setHeight((int)(image.height*yScale));
	}
	public void setXScale(double xScale){
		this.xScale = xScale;
		setWidth((int)(image.width*xScale));
	}
	public void setYScale(double yScale){
		this.yScale = yScale;
		setHeight((int)(image.height*yScale));
	}
	
	public double getXScale(){
		return xScale;
	}
	
	public double getYScale(){
		return yScale;
	}
}
