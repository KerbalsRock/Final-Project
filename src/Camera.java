import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class Camera extends PApplet {
	private PGraphics g;
	private Character shrek;
	private ArrayList<Image> animationList;
	private ArrayList<Image> animationList2;
	ArrayList<Animation> shrekAnimations;
	private ArrayList<BasicShape> allShapes;
	private PImage background;
	int levelLength = width;
	public static void main(String[] args) {
		PApplet.main("Camera");
	}
	public void settings(){
		size(1200,800);

	}
	public void setup(){
		frameRate(60);
		background = loadImage("sky.jpg");
		g = createGraphics(width, height);
		allShapes = new ArrayList<BasicShape>();
		animationList = new ArrayList<Image>();
		animationList2 = new ArrayList<Image>();
		shrekAnimations = new ArrayList<Animation>();
		listsInit();
		shrek = new Character(width/4,height -100, .2, .2, 0, 0, .2, shrekAnimations, allShapes);
	}
	public void draw(){
		update();
        g.beginDraw();
        g.noStroke();
        g.background(background);
        shrek.draw(g);
        for(BasicShape s : allShapes){
        	s.draw(g);
        }
		g.endDraw();
		image(g, 0, 0);
	}
	
	private void update(){
		shrek.scrollerUpdate(allShapes);
	}
	
	public void keyPressed(){
		if(key == ' '){
			shrek.jump(-8);
		}if(key == 'a'){
			shrek.setXSpeed(-3);
			shrek.switchToAnimation(1);
		}if(key == 'd'){
			shrek.setXSpeed(3);
			shrek.switchToAnimation(0);
		}
	}
	
	public void keyReleased(){
		if(key == 'a'||key == 'd'){
			shrek.setXSpeed(0);
		}
	}
	
	private void listsInit(){
		allShapes.add(new Rectangle(0, height - 10, levelLength, 10, Color.green));
		allShapes.add(new Rectangle(-width, -height, width, height*2, Color.darkGray));
		allShapes.add(new Rectangle(levelLength, -height, width, height*2, Color.darkGray));
		allShapes.add(new Rectangle(0, -height, levelLength, 10, Color.darkGray));
		allShapes.add(new Rectangle(200, height - 130, 100, 10, Color.orange));
		allShapes.add(new Rectangle(0, height - 260, 30, 10, Color.orange));
		allShapes.add(new Rectangle(0, height - 410, 30, 10, Color.orange));
		allShapes.add(new Rectangle(0, height - 560, 30, 10, Color.orange));
		allShapes.add(new Rectangle(270, height - 600, 850, 10, Color.orange));
		allShapes.add(new Rectangle(400, height - 600, 10, 590, Color.orange));
		
		
		
		animationList.add(new Image(1, 1, loadImage("Shrekcharacter1.png")));
		animationList.add(new Image(1, 1, loadImage("Shrekcharacter2.png")));
		animationList2.add(new Image(1, 1, loadImage("Shrekcharacter.png")));
		animationList2.add(new Image(1, 1, loadImage("Shrekcharacter3.png")));
		shrekAnimations.add(new Animation(0,0,1,1,400,animationList));
		shrekAnimations.add(new Animation(0,0,1,1,400,animationList2));
	}
	
	
}
