import java.awt.Color;

public abstract class MovingShape extends BasicShape{
	private double xSpeed, ySpeed;
	public MovingShape(int xPos, int yPos, int width, int height, double xSpeed, double ySpeed, Color color){
		super(xPos, yPos, width, height, color);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
	public void update(){
		setPos(getX()+(int)xSpeed, getY()+(int)ySpeed);
	}
	
	
	public void setXSpeed(double d){
		this.xSpeed = d;
	}
	public void setYSpeed(double ySpeed){
		this.ySpeed = ySpeed;
	}
	public double getXSpeed(){
		return xSpeed;
	}
	public double getYSpeed(){
		return ySpeed;
	}
	
}
