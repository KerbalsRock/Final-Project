import java.awt.Color;

import processing.core.PGraphics;

public class HealthBar extends BasicShape{
	private Rectangle healthBorder;
	private Rectangle healthBackground;
	private Rectangle healthDisplay;
	private int health;
	private int initialHealth;
	public HealthBar(int xPos, int yPos, int width, int height, int health){
		super(xPos, yPos, width, height, Color.black, "");
		healthBorder = new Rectangle(xPos, yPos, width, height, Color.black, "");
		healthBackground = new Rectangle(xPos+1, yPos+1, width-2, height-2, Color.red, "");
		healthDisplay = new Rectangle(xPos+1, yPos+1, width-2, height-2, Color.green, "");
		this.health = health;
		initialHealth = health;
	}
	public void setHealth(int health){
		this.health = health;
		healthDisplay.setWidth((getWidth()-2)*health/initialHealth);
	}
	public void setPos(int xPos, int yPos){
		super.setPos(xPos, yPos);
		healthBorder.setPos(xPos, yPos);
		healthBackground.setPos(xPos+1, yPos+1);
		healthDisplay.setPos(xPos+1, yPos+1);
	}
	public void setWidth(int width){
		super.setWidth(width);
		healthBorder.setWidth(width);
		healthBackground.setWidth(width-2);
		healthDisplay.setWidth((width-2)*health/initialHealth);
	}
	public void setHeight(int height){
		super.setHeight(height);
		healthBorder.setHeight(height);
		healthBackground.setHeight(height-2);
		healthDisplay.setHeight(height-2);
	}
	public void update(){
	}
	public void draw(PGraphics g){
		healthBorder.draw(g);
		healthBackground.draw(g);
		healthDisplay.draw(g);
	}
}
