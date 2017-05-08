import java.awt.Color;
import java.util.ArrayList;
import processing.core.PGraphics;

public class Animation extends MovingShape{
	private ArrayList<BasicShape> list;
	private int currentIndex;
	private int millisBetweenAnimation;
	private long prevSwitch;
	public Animation(int xPos, int yPos, int xSpeed, int ySpeed,ArrayList<BasicShape>list, int millisBetweenAnimation) {
		super(xPos, yPos, list.get(0).getWidth(), list.get(0).getHeight(), xSpeed, ySpeed, Color.white);
		this.list = list;
		currentIndex = 0;
		this.millisBetweenAnimation = millisBetweenAnimation;
		prevSwitch = System.currentTimeMillis();
	}
	
	public Animation(int xPos, int yPos, ArrayList<BasicShape> list, int millisBetweenAnimation) {
		super(xPos, yPos, list.get(0).getWidth(), list.get(0).getHeight(), 0, 0, Color.white);
		this.list = list;
		currentIndex = 0;
		this.millisBetweenAnimation = millisBetweenAnimation;
	}
	
	public void draw(PGraphics g){
		list.get(currentIndex).draw(g);
	}
	
	public void goToNext(){
		if(currentIndex != list.size() - 1){
			currentIndex++;
		}else{
			currentIndex = 0;
		}
		prevSwitch = System.currentTimeMillis();
		setWidth(list.get(currentIndex).getWidth());
		setHeight(list.get(currentIndex).getHeight());
	}
	
	public void update(){
		super.update();
		for(BasicShape b : list){
			b.setPos(getX(),getY());
		}
		if(System.currentTimeMillis() - prevSwitch >= millisBetweenAnimation){
			goToNext();
		}
	}
	
	public void stopCollision(ArrayList<BasicShape> colList){
		super.stopCollision(colList);
		for(BasicShape b : list){
			b.setPos(getX(),getY());
		}
	}
	

}
