import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class Camera extends PApplet {
	private PGraphics g;
	private ScrollingCharacter shrek;
	private ArrayList<Image> animationList;
	private ArrayList<Image> animationList2;
	private ArrayList<Image> climbingAnimations;
	private ArrayList<Animation> shrekAnimations;
	private ArrayList<BasicShape> allShapes;
	private PImage background;
	private int levelLength;
	private boolean a;
	private boolean d;
	private boolean w;
	private boolean s;
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
		w = false;
		s = false;
		space = false;
		noCursor();
		frameRate(60);
		levelLength = width*2;
		background = loadImage("sky.jpg");
		g = createGraphics(width, height);
		listsInit();
		shrek = new ScrollingCharacter(width/4,height -100, .2, .2, 0, 0, .2, shrekAnimations, "SHREK");
		
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
			shrek.setXSpeed(-5);
			if(shrek.getCanClimb()){
				shrek.setAnimation(2);
			}else{
				shrek.setAnimation(1);
			}
		}else if(d){
			shrek.setXSpeed(5);
			if(shrek.getCanClimb()){
				shrek.setAnimation(2);
			}else{
				shrek.setAnimation(1);
			}
		}else{
			shrek.setXSpeed(0);
		}
		if(space){
			shrek.jump(-8);
		}
		if(w){
			if(shrek.getCanClimb()){
				shrek.setAnimation(2);
			}
			shrek.climb(-4);
		}else if(s){
			if(shrek.getCanClimb()){
				shrek.setAnimation(2);
			}
			shrek.climb(3);
		}else{
			shrek.climb(0);
		}
		if(!w && !a && !s && !d){
			shrek.setAnimation(0);
		}
		shrek.scrollerUpdate(allShapes);
	}
	
	public void keyPressed(){
		if(key == ' '){
			space = true;
		}if(key == 'a'){
			a = true;
		}if(key == 'd'){
			d = true;
		}if(key == 'w'){
			w = true;
		}if(key == 's'){
			s = true;
		}
	}
	
	public void keyReleased(){
		if(key == 'a'){
			a = false;
		}else if(key == 'd'){
			d = false;
		}else if(key == 'w'){
			w = false;
		}else if(key == 's'){
			s = false;
		}else if(key == ' '){
			space = false;;
		}
	}
	
	private void listsInit(){
		allShapes = new ArrayList<BasicShape>();
		animationList = new ArrayList<Image>();
		animationList2 = new ArrayList<Image>();
		climbingAnimations = new ArrayList<Image>();
		shrekAnimations = new ArrayList<Animation>();
		
		allShapes.add(new Rectangle(0, height - 10, levelLength, 10, Color.green, "WALL"));//0
		allShapes.add(new Rectangle(-width, -height, width, height*2, Color.darkGray, "WALL"));//1
		allShapes.add(new Rectangle(levelLength, -height, width, height*2, Color.darkGray, "WALL"));//2
		allShapes.add(new Rectangle(0, -height, levelLength, 10, Color.darkGray, "WALL"));//3
		allShapes.add(new Rectangle(200, height - 130, 100, 10, Color.orange, "WALL"));//4
		allShapes.add(new Rectangle(400, height - 600, 10, 590, Color.orange, "WALL"));//5
		allShapes.add(new Rectangle(0, height - 260, 70, 10, Color.orange, "WALL"));//6
		allShapes.add(new Rectangle(500, height - 100, 720, 10, Color.orange, "WALL"));//7
		allShapes.add(new Rectangle(1220, -height, 20, height*2 - 90, Color.orange, "WALL"));//8
		allShapes.add(new Rectangle(1300, 100,10, height - 110  , Color.orange, "WALL"));//9
		allShapes.add(new Rectangle(1890, 100,10, height - 110  , Color.orange, "WALL"));//10
		
		allShapes.add(new Rectangle(270, height - 600, 850, 10, Color.blue, "BOTTOMLESS"));//11
		allShapes.add(new Rectangle(500, height - 500, 720, 10, Color.blue, "BOTTOMLESS"));//12
		allShapes.add(new Rectangle(410, height - 400, 710, 10, Color.blue, "BOTTOMLESS"));//13
		allShapes.add(new Rectangle(500, height - 300, 720, 10, Color.blue, "BOTTOMLESS"));//14
		allShapes.add(new Rectangle(410, height - 200, 710, 10, Color.blue, "BOTTOMLESS"));//15
		allShapes.add(new Rectangle(0, height - 410, 400, 10, Color.blue, "BOTTOMLESS"));//16
		allShapes.add(new Rectangle(0, height - 565, 70, 10, Color.blue, "BOTTOMLESS"));//17
		
		allShapes.add(new Rectangle(1240, 0, 60, height - 10  , Color.gray, "LADDER"));//18
		allShapes.add(new Rectangle(1300, 0, 600, 100 , Color.gray, "LADDER"));//19
		allShapes.add(new Rectangle(1900, 0, 60, height - 10  , Color.gray, "LADDER"));//20
		
		
		animationList.add(new Image(1, 1, loadImage("Shrek.png"), ""));
		animationList.add(new Image(1, 1, loadImage("Shrek2.png"), ""));
		animationList2.add(new Image(1, 1, loadImage("Shrekcharacter.png"), ""));
		animationList2.add(new Image(1, 1, loadImage("Shrekcharacter1.png"), ""));
		climbingAnimations.add(new Image(1, 1, loadImage("ShrekClimbing1.png"), ""));
		climbingAnimations.add(new Image(1, 1, loadImage("ShrekClimbing2.png"), ""));
		shrekAnimations.add(new Animation(0,0,10,10,400,animationList, ""));
		shrekAnimations.add(new Animation(0,0,1,1,400,animationList2, ""));
		shrekAnimations.add(new Animation(0,0,10,10,200,climbingAnimations, ""));
	}
	
	
}
