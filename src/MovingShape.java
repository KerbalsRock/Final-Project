import java.awt.Color;

public abstract class MovingShape extends BasicShape{
	private int xSpeed, ySpeed;
	public MovingShape(int xPos, int yPos, int width, int height, int xSpeed, int ySpeed, Color color){
		super(xPos, yPos, width, height, color);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
	public void update(){
		setPos(getX()+xSpeed, getY()+ySpeed);
	}
	
	public void setXSpeed(int xSpeed){
		this.xSpeed = xSpeed;
	}
	public void setYSpeed(int ySpeed){
		this.ySpeed = ySpeed;
	}
	public int getXSpeed(){
		return xSpeed;
	}
	public int getYSpeed(){
		return ySpeed;
	}
	
}
