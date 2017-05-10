import java.util.ArrayList;
import processing.core.PGraphics;

public class Character extends Animation{
	private ArrayList<Animation> animList;
	int currentIndex;
	public Character(int xPos, int yPos, double xScale, double yScale, double xSpeed, double ySpeed, ArrayList<Animation> list) {
		super(xPos, yPos, xScale, yScale, xSpeed, ySpeed, list.get(0).getMillisBetweenAnimation(), list.get(0).getList());
		animList = list;
		currentIndex = 0;
	}
	
	public void draw(PGraphics g){
		super.draw(g);
	}
	
	public void update(){
		super.update();
	}
	
	public void switchToAnimation(int index){
		if(index!=currentIndex){		
			currentIndex = index;
			setList(animList.get(currentIndex).getList());
			setWidth((int)(animList.get(currentIndex).getWidth()*getXScale()));
			setHeight((int)(animList.get(currentIndex).getHeight()*getYScale()));
		}
	}

}
