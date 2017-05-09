import java.awt.Color;
import java.util.ArrayList;
import processing.core.PGraphics;

public class Character extends Animation{
	private ArrayList<Animation> list;
	int currentIndex;
	public Character(int xPos, int yPos, double xScale, double yScale, double xSpeed, double ySpeed, ArrayList<Animation> list) {
		super(xPos, yPos, xScale, yScale, xSpeed, ySpeed, list.get(0).getMillisBetweenAnimation(), list.get(0).getList());
		this.list = list;
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
			setList(list.get(index).getList());
			setWidth((int)(list.get(index).getWidth()*getXScale()));
			setHeight((int)(list.get(index).getHeight()*getYScale()));
			setMillisBetweenAnimation(list.get(index).getMillisBetweenAnimation());
		}
	}

}
