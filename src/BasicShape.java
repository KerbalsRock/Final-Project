import java.awt.Color;
import java.util.ArrayList;
import processing.core.PGraphics;

public abstract class BasicShape {
	private int x, y, width, height;
	private Color color;
	private String tag;
	private boolean bottomlessCanCollide;
	private int damage;
	public BasicShape(int xPos, int yPos, int width, int height, Color color, String tag){
		x = xPos;
		y = yPos;
		this.width = width;
		this.height = height;
		this.color = color;
		bottomlessCanCollide = false;
		this.tag = tag;
		damage = 100;
	}
	
	abstract void draw(PGraphics g);
	abstract void update();
	
    boolean collidesWith(BasicShape s){
		return (x <= s.getX2()&& x + width >= s.getX()&&
				y <= s.getY2()&& y + height >= s.getY())&&
				!((x==s.getX2() || x + width == s.getX())&&(y==s.getY2()||y+height == s.getY()));//corners
	}
    
    public String sideCollision(BasicShape s){
    	if(!collidesWith(s)){
    		return "NONE";
    	}else{
    		int tolerance = 0;
    		while (true){
    			if(y + height - tolerance == s.getY()){
    				return "BOTTOM";
    			}else if (y + tolerance  == s.getY() + s.getHeight()){
    				return "TOP";
    			}else if(x + width - tolerance == s.getX()){
    				return "RIGHT";
    			}else if (x + tolerance == s.getX() + s.getWidth() ){
    				return "LEFT";
    			}
    			tolerance++;
    		}
    	}
    }
    
    public String bottomlessSideCollision(BasicShape s){
    	if(!collidesWith(s)){
    		bottomlessCanCollide = true;
    		return "NONE";
    	}else{
    		int tolerance = 0;
    		while (true){
    			if(y + height - tolerance == s.getY()){
    				bottomlessCanCollide = false;
    				return "BOTTOM";
    			}else if (y + tolerance  == s.getY() + s.getHeight()){
    				if(tolerance==0){
    					bottomlessCanCollide = true;
    				}
    				return "TOP";
    			}else if(x + width - tolerance == s.getX()){
    				if(bottomlessCanCollide){
    					return "RIGHT";
    				}else{
    					return "BOTTOM";
    				}
    			}else if (x + tolerance == s.getX() + s.getWidth() ){
    				if(bottomlessCanCollide){
    					return "LEFT";
    				}else{
    					return "BOTTOM";
    				}
    			}
    			tolerance++;
    		}
    	}
    }
    
    public void stopCollision(ArrayList<BasicShape> list){
		for(BasicShape s : list){
			String collided = s.sideCollision(this);
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
	
	public void setTag(String tag){
		this.tag = tag;
	}public String getTag(){
		return tag;
	}
	
	public boolean getBottomlessCanCollide(){
		return bottomlessCanCollide;
	}
	public int getDamage(){
		return damage;
	}
	public void setDamage(int damage){
		this.damage = damage;
	}
}
