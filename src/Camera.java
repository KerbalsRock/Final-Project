import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Camera extends PApplet {
	private PGraphics g;
	private Character shrek;
	private ArrayList<Image> animationList;
	private ArrayList<Image> animationList2;
	ArrayList<Animation> shrekAnimations;
	private ArrayList<BasicShape> shapeList;
	private Color background;
	public static void main(String[] args) {
		PApplet.main("Camera");
	}
	public void settings(){
		size(1200,800);

	}
	public void setup(){
		frameRate(60);
		background = new Color(44, 176, 55);
		g = createGraphics(width, height);
		shapeList = new ArrayList<BasicShape>();
		animationList = new ArrayList<Image>();
		animationList2 = new ArrayList<Image>();
		shrekAnimations = new ArrayList<Animation>();
		shapeList.add(new Rectangle(width/2 - 200, height/2 - 75, 400, 150, Color.orange));
		shapeList.add(new Rectangle(width/2, height/2, 100, 200, Color.orange));
		shapeList.add(new Rectangle(100, 100, 100, 100, Color.orange));
		shapeList.add(new Rectangle(1000, 150, 35, 10, Color.blue));
		shapeList.add(new Rectangle(1000, 150, 10, 75, Color.blue));
		shapeList.add(new Rectangle(1035, 100, 10, 175, Color.blue));
		shapeList.add(new Rectangle(965, 275, 80, 10, Color.blue));
		shapeList.add(new Rectangle(965, 150, 10, 125, Color.blue));
		shapeList.add(new Rectangle(900, 100, 140, 10, Color.blue));
		shapeList.add(new Rectangle(900, 100, 10, 185, Color.blue));
		animationList.add(new Image(1, 1, loadImage("Shrekcharacter.jpg")));
		animationList.add(new Image(1, 1, loadImage("Shrekcharacter1.jpg")));
		animationList2.add(new Image(1, 1, loadImage("Shrekcharacter2.jpg")));
		animationList2.add(new Image(1, 1, loadImage("Shrekcharacter3.jpg")));
		shrekAnimations.add(new Animation(0,0,1,1,400,animationList));
		shrekAnimations.add(new Animation(0,0,1,1,400,animationList2));
		shrek = new Character(0,0, .2, .2, 0, 0, .01 shrekAnimations);
	}
	public void draw(){
		update();
        g.beginDraw();
        g.noStroke();
        g.background(background.getRGB());
        shrek.draw(g);
        for(BasicShape s : shapeList){
        	s.draw(g);
        }
		g.endDraw();
		image(g, 0, 0);
	}
	
	private void update(){
		shrek.update();
		shrek.stopCollision(shapeList);
		shrek.setPos(constrain(shrek.getX(), 0, width - shrek.getWidth()),constrain(shrek.getY(), 0, height - shrek.getHeight()));
	}
	
	public void keyPressed(){
		if(key == 'w'){
			shrek.setYSpeed(-3);
		}if(key == 's'){
			shrek.setYSpeed(3);
		}if(key == 'a'){
			shrek.setXSpeed(-3);
			shrek.switchToAnimation(1);
		}if(key == 'd'){
			shrek.setXSpeed(3);
			shrek.switchToAnimation(0);
		}if(key == 'p'){
			shrek.setScale(shrek.getXScale()*1.02,shrek.getYScale()*1.02);
		}if(key == 'o'){
			shrek.setScale(shrek.getXScale()*.98,shrek.getYScale()*.98);
		}
	}
	
	public void keyReleased(){
		if(key == 'a'||key == 'd'){
			shrek.setXSpeed(0);
		}
		if(key == 'w'||key == 's'){
			shrek.setYSpeed(0);
		}
	}
	
	
}
