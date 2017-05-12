import java.util.ArrayList;
import processing.core.PGraphics;

public class Character extends Animation{
	private ArrayList<Animation> animList;
	ArrayList<BasicShape> obstacleList;
	int currentIndex;
	double pseudoY, gravity;
	boolean canJump;
	
	public Character(int xPos, int yPos, double xScale, double yScale, double xSpeed, double ySpeed, double gravity, ArrayList<Animation> list, ArrayList<BasicShape> obstacles) {
		super(xPos, yPos, xScale, yScale, xSpeed, ySpeed, list.get(0).getMillisBetweenAnimation(), list.get(0).getList());
		this.gravity = gravity;
		animList = list;
		pseudoY = yPos;
		currentIndex = 0;
		obstacleList = obstacles;
		canJump = false;
	}
	
	public void draw(PGraphics g){
		super.draw(g);
	}
	
	public void update(){
		autoToNext();
		pseudoY+=getYSpeed();
		setY((int)pseudoY);
		setX((int)(getX()+getXSpeed()));
	}
	
	public void scrollerUpdate(ArrayList<BasicShape> list){
		autoToNext();
		boolean updateY = true;
		boolean updateX = true;
		canJump = false;
		setYSpeed(getYSpeed()+gravity);
		for(BasicShape s : obstacleList){
			String collided = sideCollision(s);
			if(collided.equals("LEFT")){
				setX(s.getX() - getWidth());
				if(getXSpeed() >= 0){
					updateX = false;
				}
			}
			else if(collided.equals("RIGHT")){
				setX(s.getX2());
				if(getXSpeed() <= 0){
					updateX = false;
				}
			}
			else if(collided.equals("TOP")){
				canJump = true;
				setY(s.getY() - getHeight());
				if(getYSpeed() >= 0){
					updateY = false;
				}
			}
			else if(collided.equals("BOTTOM")){
				setY(s.getY2());
				if(getYSpeed() <= 0){
					updateY = false;
				}
			}	
		}
		if(updateY){
			pseudoY+=getYSpeed();
			setY((int)pseudoY);
		}else{
			setYSpeed(0);
		}
		if(updateX){
			for(BasicShape s : list){
				s.setX((int)(s.getX() - getXSpeed()));
			}
		}
	}
	
	public void jump(double jumpSpeed){
		if(canJump){
			if(getYSpeed() > 0){
				setYSpeed(0);
			}
			setYSpeed(getYSpeed() + jumpSpeed);
		}
	}
	
	public void goToNext(){
		super.goToNext();
		setWidth((int)(animList.get(currentIndex).getList().get(getCurrentIndex()).getWidth()*animList.get(currentIndex).getXScale()*getXScale()));
		setHeight((int)(animList.get(currentIndex).getList().get(getCurrentIndex()).getHeight()*animList.get(currentIndex).getYScale()*getYScale()));
		}
		
	
	public void switchToAnimation(int index){
		if(index!=currentIndex){		
			currentIndex = index;
			setList(animList.get(currentIndex).getList());
			setMillisBetweenAnimation(animList.get(currentIndex).getMillisBetweenAnimation());
		}
	}
	

}
