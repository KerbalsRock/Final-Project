import java.util.ArrayList;
import processing.core.PGraphics;

public class Animation extends Image{
	private ArrayList<Image> list;
	private int currentIndex, millisBetweenAnimation;
	private long prevSwitch;
	public Animation(int xPos, int yPos, double xScale, double yScale, double xSpeed, double ySpeed, int millisBetweenAnimation, ArrayList<Image>list) {
		super(xPos, yPos, list.get(0).getXScale()*xScale, list.get(0).getYScale()*yScale, xSpeed, ySpeed, list.get(0).getImage());
		this.list = list;
		this.millisBetweenAnimation = millisBetweenAnimation;
		currentIndex = 0;
		prevSwitch = System.currentTimeMillis();
	}
	
	public Animation(int xPos, int yPos, double xScale, double yScale, int millisBetweenAnimation, ArrayList<Image>list){
		super(xPos, yPos, (int)(list.get(0).getYScale()*xScale), (int)(list.get(0).getYScale()*yScale), 0, 0, list.get(0).getImage());
		this.list = list;
		this.millisBetweenAnimation = millisBetweenAnimation;
		currentIndex = 0;
		prevSwitch = System.currentTimeMillis();
		
	}
	
	public void draw(PGraphics g){
		super.draw(g);
	}
	
	public void goToNext(){
		if(currentIndex != list.size() - 1){
			currentIndex++;
		}else{
			currentIndex = 0;
		}
		setImage(list.get(currentIndex).getImage());
		prevSwitch = System.currentTimeMillis();
		setWidth((int)(list.get(currentIndex).getWidth()*getXScale()));
		setHeight((int)(list.get(currentIndex).getHeight()*getYScale()));
		
	}
	protected void autoToNext(){
		if(System.currentTimeMillis() - prevSwitch >= millisBetweenAnimation){
			goToNext();
		}
	}
	
	
	
	public void update(){
		super.update();
		autoToNext();
	}
	
	public ArrayList<Image> getList(){
		return list;
	}
	public void setList(ArrayList<Image> list){
		this.list = list;
		currentIndex = 0;
		setImage(list.get(currentIndex).getImage());
		setWidth((int)(list.get(currentIndex).getWidth()*getXScale()));
		setHeight((int)(list.get(currentIndex).getHeight()*getYScale()));
		prevSwitch = System.currentTimeMillis();
	}
	
	public void setScale(double xScale, double yScale){
		super.setXScale(xScale);
		super.setYScale(yScale);
		setWidth((int)(list.get(currentIndex).getWidth()*xScale));
		setHeight((int)(list.get(currentIndex).getHeight()*yScale));
	}
	public void setXScale(double xScale){
		super.setXScale(xScale);
		setWidth((int)(list.get(currentIndex).getWidth()*xScale));
	}
	public void setYScale(double yScale){
		super.setYScale(yScale);
		setHeight((int)(list.get(currentIndex).getHeight()*yScale));
	}
		
	public int getMillisBetweenAnimation(){
		return millisBetweenAnimation;
	}public void setMillisBetweenAnimation(int millisBetweenAnimation){
		this.millisBetweenAnimation = millisBetweenAnimation;
	}
	
	public int getCurrentIndex(){
		return currentIndex;
	}
	
	

}
