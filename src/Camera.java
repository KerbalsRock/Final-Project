import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Camera extends PApplet {
	private PGraphics g;
	private Animation mouseRect;
	private ArrayList<BasicShape> animationList;
	private ArrayList<BasicShape> shapeList;
	public static void main(String[] args) {
		PApplet.main("Camera");
	}
	public void settings(){
		size(1200,800);

	}
	public void setup(){
		frameRate(60);
		g = createGraphics(width, height);
		shapeList = new ArrayList<BasicShape>();
		animationList = new ArrayList<BasicShape>();
		shapeList.add(new Rectangle(width/2 - 200, height/2 - 75, 400, 150, Color.orange));
		shapeList.add(new Rectangle(width/2, height/2, 100, 200, Color.orange));
		shapeList.add(new Rectangle(100, 100, 100, 100, Color.orange));
		shapeList.add(new Rectangle(1000, 150, 35, 10, Color.blue));
		shapeList.add(new Rectangle(1000, 150, 10, 100, Color.blue));
		shapeList.add(new Rectangle(1035, 100, 10, 175, Color.blue));
		shapeList.add(new Rectangle(965, 275, 80, 10, Color.blue));
		shapeList.add(new Rectangle(965, 150, 10, 125, Color.blue));
		shapeList.add(new Rectangle(900, 100, 140, 10, Color.blue));
		shapeList.add(new Rectangle(900, 100, 10, 185, Color.blue));
		animationList.add(new Image(0, 0, .2, .2, 0, 0, loadImage("Shrekcharacter.jpg")));
		animationList.add(new Image(0, 0, .2, .2, 0, 0, loadImage("Shrekcharacter1.jpg")));
		mouseRect = new Animation(0,0, animationList, 250);
	}
	public void draw(){
		update();
        g.beginDraw();
        g.noStroke();
        g.background(Color.white.getRGB());
        mouseRect.draw(g);
        for(BasicShape s : shapeList){
        	s.draw(g);
        }
		g.endDraw();
		image(g, 0, 0);
	}
	
	private void update(){
		mouseRect.update();
		mouseRect.stopCollision(shapeList);
		mouseRect.setPos(constrain(mouseRect.getX(), 0, width - mouseRect.getWidth()),constrain(mouseRect.getY(), 0, height - mouseRect.getHeight()));
	}
	
	public void keyPressed(){
		if(key == 'w'){
			mouseRect.setYSpeed(-3);
		}if(key == 's'){
			mouseRect.setYSpeed(3);
		}if(key == 'a'){
			mouseRect.setXSpeed(-3);
		}if(key == 'd'){
			mouseRect.setXSpeed(3);
		}
	}
	
	public void keyReleased(){
		if(key == 'w'|| key == 's'){
			mouseRect.setYSpeed(0);
		}if(key == 'a'||key == 'd'){
			mouseRect.setXSpeed(0);
		}
	}
	
	
}
