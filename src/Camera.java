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
	private ArrayList<Animation> shrekAnimations;
	private ArrayList<BasicShape> allShapes;
	private ArrayList<BasicShape> solidObstacles;
	private ArrayList<BasicShape> bottomlessObstacles;
	private PImage background;
	private int levelLength;
	private boolean a;
	private boolean d;
	private boolean space;
	public static void main(String[] args) {
		PApplet.main("Camera");
	}
	public void settings(){
		size(1200,800);

	}
	public void setup(){
		a = false;
		d = false;
		space = false;
		frameRate(60);
		levelLength = width;
		background = loadImage("sky.jpg");
		g = createGraphics(width, height);
		listsInit();
		shrek = new Character(width/4,height -100, .2, .2, 0, 0, .2, shrekAnimations, allShapes);
		
	}
	public void draw(){
		update();
        g.beginDraw();
        g.noStroke();
        g.background(background);
        for(BasicShape s : allShapes){
        	s.draw(g);
        }
        shrek.draw(g);
		g.endDraw();
		image(g, 0, 0);
	}
	
	private void update(){
		if(a){
			shrek.setXSpeed(-3);
			shrek.setAnimation(1);
		}else if(d){
			shrek.setXSpeed(3);
			shrek.setAnimation(1);
		}else{
			shrek.setXSpeed(0);
			shrek.setAnimation(0);
		}
		if(space){
			shrek.jump(-8);
		}
		shrek.scrollerUpdate(allShapes, solidObstacles, bottomlessObstacles);
	}
	
	public void keyPressed(){
		if(key == ' '){
			space = true;
		}if(key == 'a'){
			a = true;
		}if(key == 'd'){
			d = true;
		}
	}
	
	public void keyReleased(){
		if(key == 'a'){
			a = false;
		}else if(key == 'd'){
			d = false;
		}else if(key == ' '){
			space = false;;
		}
	}
	
	private void listsInit(){
		int solidIndex = 7;
		allShapes = new ArrayList<BasicShape>();
		solidObstacles = new ArrayList<BasicShape>();
		bottomlessObstacles = new ArrayList<BasicShape>();
		animationList = new ArrayList<Image>();
		animationList2 = new ArrayList<Image>();
		shrekAnimations = new ArrayList<Animation>();
		
		allShapes.add(new Rectangle(0, height - 10, levelLength, 10, Color.green));//0
		allShapes.add(new Rectangle(-width, -height, width, height*2, Color.darkGray));//1
		allShapes.add(new Rectangle(levelLength, -height, width, height*2, Color.darkGray));//2
		allShapes.add(new Rectangle(0, -height, levelLength, 10, Color.darkGray));//3
		allShapes.add(new Rectangle(200, height - 130, 100, 10, Color.orange));//4
		allShapes.add(new Rectangle(400, height - 600, 10, 590, Color.orange));//5
		allShapes.add(new Rectangle(0, height - 260, 70, 10, Color.orange));//6
		allShapes.add(new Rectangle(500, height - 100, levelLength - 500, 10, Color.orange));//7
		
		allShapes.add(new Rectangle(270, height - 600, 850, 10, Color.orange));
		allShapes.add(new Rectangle(500, height - 500, levelLength - 500, 10, Color.orange));
		allShapes.add(new Rectangle(400, height - 400, 720, 10, Color.orange));
		allShapes.add(new Rectangle(500, height - 300, levelLength - 500, 10, Color.orange));
		allShapes.add(new Rectangle(400, height - 200, 720, 10, Color.orange));
		allShapes.add(new Rectangle(0, height - 410, 400, 10, Color.orange));
		allShapes.add(new Rectangle(0, height - 565, 70, 10, Color.orange));
		
		for(int i = 0; i <= solidIndex; i++){
			solidObstacles.add(allShapes.get(i));
		}
		for(int i = solidIndex+1; i < allShapes.size(); i++){
			bottomlessObstacles.add(allShapes.get(i));
		}
		
		animationList.add(new Image(1, 1, loadImage("Shrek.png")));
		animationList.add(new Image(1, 1, loadImage("Shrek2.png")));
		animationList2.add(new Image(1, 1, loadImage("Shrekcharacter.png")));
		animationList2.add(new Image(1, 1, loadImage("Shrekcharacter1.png")));
		shrekAnimations.add(new Animation(0,0,10,10,400,animationList));
		shrekAnimations.add(new Animation(0,0,1,1,400,animationList2));
	}
	
	
}
