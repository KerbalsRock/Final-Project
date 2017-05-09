import java.awt.Color;
import java.util.ArrayList;
import processing.core.PGraphics;

public abstract class BasicShape {
	private int x, y, width, height;
	private Color color;
	public BasicShape(int xPos, int yPos, int width, int height, Color color){
		x = xPos;
		y = yPos;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	abstract void draw(PGraphics g);
	
    boolean collidesWith(BasicShape s){
		return (x <= s.getX2()&& x + width >= s.getX()&&
				y <= s.getY2()&& y + height >= s.getY())&&
				!((x==s.getX2() || x + width == s.getX())&&(y==s.getY2()||y+width == s.getY()));//corners
	}
    
    public String sideCollision(BasicShape s){
    	if(!collidesWith(s)){
    		return "NONE";
    	}else{
    		int tolerance = 0;
    		while (true){
    			if(y + height - tolerance == s.getY()){
    				return "TOP";
    			}else if (y + tolerance  == s.getY() + s.getHeight()){
    				return "BOTTOM";
    			}else if(x + width - tolerance == s.getX()){
    				return "LEFT";
    			}else if (x + tolerance == s.getX() + s.getWidth() ){
    				return "RIGHT";
    			}
    			tolerance++;
    		}
    	}
    }
    
    public void stopCollision(ArrayList<BasicShape> list){
		for(BasicShape s : list){
			String collided = sideCollision(s);
			if(collided.equals("LEFT")){
				x = s.getX() - width;
			}
			else if(collided.equals("RIGHT")){
				x = s.getX2();
			}
			else if(collided.equals("TOP")){
				y = s.getY() - height;
			}
			else if(collided.equals("BOTTOM")){
				y = s.getY2();
			}	
		}
    }
	
	public int getX(){
		return x;
	}public int getX2(){
		return x + width;
	}
	
	public int getY(){
		return y;
	}public int getY2(){
		return y + height;
	}
	
	public int getWidth(){
		return width;
	}public void setWidth(int width){
		this.width = width;
	}
	
	public int getHeight(){
		return height;
	}public void setHeight(int height){
		this.height = height;
	}
	
	public Color getColor(){
		return color;
	}public void setColor(Color color){
		this.color = color;
	}
	
	public void setPos(int xPos, int yPos){
		x = xPos;
		y = yPos;
	}public void setX(int xPos){
		x = xPos;
	}public void setY(int yPos){
		y = yPos;
	}
}
