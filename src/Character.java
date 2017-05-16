import java.util.ArrayList;
import processing.core.PGraphics;

public class Character extends Animation{
	private ArrayList<Animation> animList;
	int currentIndex;

	
	public Character(int xPos, int yPos, double xScale, double yScale, double xSpeed, double ySpeed, ArrayList<Animation> list, ArrayList<BasicShape> obstacles) {
		super(xPos, yPos, xScale, yScale, xSpeed, ySpeed, list.get(0).getMillisBetweenAnimation(), list.get(0).getList());
		animList = list;
		currentIndex = 0;
		setWidth((int)(animList.get(currentIndex).getList().get(getCurrentIndex()).getWidth()*animList.get(currentIndex).getXScale()*getXScale()));
		setHeight((int)(animList.get(currentIndex).getList().get(getCurrentIndex()).getHeight()*animList.get(currentIndex).getYScale()*getYScale()));
	}
	
	public void draw(PGraphics g){
		super.draw(g);
	}
	
	public void update(){
		autoToNext();
		super.update();
	}
	
	
	
	public void goToNext(){
		super.goToNext();
		setWidth((int)(animList.get(currentIndex).getList().get(getCurrentIndex()).getWidth()*animList.get(currentIndex).getXScale()*getXScale()));
		setHeight((int)(animList.get(currentIndex).getList().get(getCurrentIndex()).getHeight()*animList.get(currentIndex).getYScale()*getYScale()));
	}
		
	
	public void setAnimation(int index){
		if(index!=currentIndex){
			int prevHeight = getHeight();
			currentIndex = index;
			setList(animList.get(currentIndex).getList());
			setMillisBetweenAnimation(animList.get(currentIndex).getMillisBetweenAnimation());
			setWidth((int)(animList.get(currentIndex).getList().get(getCurrentIndex()).getWidth()*animList.get(currentIndex).getXScale()*getXScale()));
			setHeight((int)(animList.get(currentIndex).getList().get(getCurrentIndex()).getHeight()*animList.get(currentIndex).getYScale()*getYScale()));
			setY(getY() + (prevHeight - getHeight()));
		}
	}
	
	public ArrayList<Animation> getAnimList(){
		return animList;
	}
	

}
