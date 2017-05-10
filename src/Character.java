import java.util.ArrayList;
import processing.core.PGraphics;

public class Character extends Animation{
	private ArrayList<Animation> animList;
	int currentIndex;
	double pseudoY, gravity;
	public Character(int xPos, int yPos, double xScale, double yScale, double xSpeed, double ySpeed, double gravity, ArrayList<Animation> list) {
		super(xPos, yPos, xScale, yScale, xSpeed, ySpeed, list.get(0).getMillisBetweenAnimation(), list.get(0).getList());
		this.gravity = gravity;
		animList = list;
		currentIndex = 0;
	}
	
	public void draw(PGraphics g){
		super.draw(g);
	}
	
	public void update(){
		setYSpeed(getYSpeed()+gravity);
		pseudoY+=getYSpeed();
		setPos((int)(getX()+getXSpeed()), (int)(pseudoY));
		setWidth((int)(animList.get(currentIndex).getWidth()*getXScale()));
		setHeight((int)(animList.get(currentIndex).getHeight()*getYScale()));
	}
	
	public void switchToAnimation(int index){
		if(index!=currentIndex){		
			currentIndex = index;
			setList(animList.get(currentIndex).getList());
			setMillisBetweenAnimation(animList.get(currentIndex).getMillisBetweenAnimation());
		}
	}
	
	public void stopCollision(ArrayList<BasicShape> list){
		for(BasicShape s : list){
			String collided = sideCollision(s);
			if(collided.equals("LEFT")){
				setX(s.getX() - getWidth());
			}
			else if(collided.equals("RIGHT")){
				setX(s.getX2());
			}
			else if(collided.equals("TOP")){
				setYSpeed(0);
				setY(s.getY() - getHeight());
			}
			else if(collided.equals("BOTTOM")){
				setY(s.getY2());
			}	
		}
    }

}
