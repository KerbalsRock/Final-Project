import java.awt.Color;
import java.util.ArrayList;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;


public class Camera extends PApplet {
	private PGraphics g;
	private ScrollingCharacter shrek;
	private Enemy nogenders;
	private ArrayList<Image> idle;
	private ArrayList<Image> walkingLeft;
	private ArrayList<Image> walkingRight;
	private ArrayList<Image> climbing;
	private ArrayList<Image> surrealAnimation1;
	private ArrayList<Image> surrealAnimation2;
	private ArrayList<Animation> shrekAnimations;
	private ArrayList<Animation> enemyAnimations;
	private ArrayList<BasicShape> allShapes;
	private HealthBar shrekHealth;
	private PImage background;
	private int levelLength;
	private boolean a;
	private boolean d;
	private boolean w;
	private boolean s;
	private boolean space;
	private Minim minim;
	private AudioPlayer player;
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
		shrek = new ScrollingCharacter(width/4,height -100, 1, 1, 0, 0, .25, shrekAnimations, "<ENEMYBOUND>");
		shrekHealth = new HealthBar(width-300, 0, 300, 50, shrek.getHealth());
		minim = new Minim(this);
		player = minim.loadFile("Bag Raiders - Shooting Stars.mp3");
	}
	public void draw(){
		update();
        g.beginDraw();
        g.noStroke();
        g.background(background);
        for(BasicShape s : allShapes){
        	s.draw(g);
        }
        shrekHealth.draw(g);
        shrek.draw(g);
		g.endDraw();
		image(g, 0, 0);
	}
	
	private void update(){
		//shrek.setScale((double)player.mix.level()+.1,(double)player.mix.level()+.1);
		shrekHealth.setHealth(shrek.getHealth());
		if(nogenders.getXSpeed() < 0){
			nogenders.setAnimation(1);
		}else{
			nogenders.setAnimation(0);
		}
		if(player.mix.level() > .35){
			background.filter(INVERT);
		}
		if(player.position()>=player.length()){
			player.rewind();
		}
		if(a){
			shrek.setXSpeed(-5);
			if(shrek.getCanClimb()){
				shrek.setAnimation(3);
			}else{
				shrek.setAnimation(1);
			}
			if(!player.isPlaying()){
				player.play();
			}
		}else if(d){
			shrek.setXSpeed(5);
			if(shrek.getCanClimb()){
				shrek.setAnimation(3);
			}else{
				shrek.setAnimation(2);
			}if(!player.isPlaying()){
				player.play();
			}
		}else{
			shrek.setXSpeed(0);
			if(player.isPlaying()){
				player.pause();
			}
		}
		if(space){
			shrek.jump(-10);
		}
		if(w){
			if(shrek.getCanClimb()){
				shrek.setAnimation(3);
			}
			shrek.climb(-4);
		}else if(s){
			if(shrek.getCanClimb()){
				shrek.setAnimation(3);
				shrek.climb(3);
			}else{
				shrek.drop();
			}
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
		idle = new ArrayList<Image>();
		walkingLeft = new ArrayList<Image>();
		walkingRight = new ArrayList<Image>();
		climbing = new ArrayList<Image>();
		surrealAnimation1 = new ArrayList<Image>();
		surrealAnimation2 = new ArrayList<Image>();
		shrekAnimations = new ArrayList<Animation>();
		enemyAnimations = new ArrayList<Animation>();

		
		allShapes.add(new Rectangle(0, height - 10, levelLength, 10, Color.green, "<WALL><ENEMYBOUND>"));
		allShapes.add(new Rectangle(-width, -height, width, height*2, Color.darkGray, "<WALL>"));
		allShapes.add(new Rectangle(levelLength, -height, width, height*2, Color.darkGray, "<WALL>"));
		allShapes.add(new Rectangle(0, -height, levelLength, 10, Color.darkGray, "<WALL>"));
		allShapes.add(new Rectangle(200, height - 130, 100, 10, Color.orange, "<WALL>"));
		allShapes.add(new Rectangle(400, height - 600, 10, 590, Color.orange, "<WALL>"));
		allShapes.add(new Rectangle(0, height - 260, 70, 10, Color.orange, "<WALL>"));
		allShapes.add(new Rectangle(500, height - 100, 720, 10, Color.orange, "<WALL>"));
		allShapes.add(new Rectangle(1220, -height, 20, height*2 - 90, Color.orange, "<WALL>"));
		allShapes.add(new Rectangle(1300, 100,10, height - 110  , Color.orange, "<WALL><ENEMYBOUND>"));
		allShapes.add(new Rectangle(1890, 100,10, height - 110  , Color.orange, "<WALL><ENEMYBOUND>"));
		allShapes.add(new Rectangle(500, height - 300, 720, 10, Color.orange, "<WALL>"));
		allShapes.add(new Rectangle(270, height - 600, 850, 10, Color.orange, "<WALL>"));
		
		allShapes.add(new Rectangle(500, height - 500, 720, 10, Color.blue, "<BOTTOMLESS>"));
		allShapes.add(new Rectangle(410, height - 400, 710, 10, Color.blue, "<BOTTOMLESS>"));
		allShapes.add(new Rectangle(410, height - 200, 710, 10, Color.blue, "<BOTTOMLESS>"));
		allShapes.add(new Rectangle(0, height - 550, 70, 10, Color.blue, "<BOTTOMLESS>"));
		allShapes.add(new Rectangle(0, height - 410, 400, 10, Color.blue, "<BOTTOMLESS>"));
		allShapes.add(new Rectangle(0, height - 440, 400, 10, Color.blue, "<BOTTOMLESS>"));
		
		allShapes.add(new Rectangle(1240, 0, 60, height - 10  , Color.gray, "<LADDER>"));
		allShapes.add(new Rectangle(1300, 0, 600, 100 , Color.gray, "<LADDER>"));
		allShapes.add(new Rectangle(1900, 0, 60, height - 10  , Color.gray, "<LADDER>"));

		
		
		idle.add(new Image(1, 1, loadImage("Shrek.png"), ""));
		idle.add(new Image(1, 1, loadImage("Shrek2.png"), ""));
		
		walkingLeft.add(new Image(1, 1, loadImage("Shrekwalkingleft1.png"), ""));
		walkingLeft.add(new Image(1, 1, loadImage("Shrekwalkingleft2.png"), ""));
		walkingLeft.add(new Image(1, 1, loadImage("Shrekwalkingleft3.png"), ""));
		walkingLeft.add(new Image(1, 1, loadImage("Shrekwalkingleft2.png"), ""));
		
		walkingRight.add(new Image(1, 1, loadImage("Shrekwalking1.png"), ""));
		walkingRight.add(new Image(1, 1, loadImage("Shrekwalking2.png"), ""));
		walkingRight.add(new Image(1, 1, loadImage("Shrekwalking3.png"), ""));
		walkingRight.add(new Image(1, 1, loadImage("Shrekwalking2.png"), ""));
		
		climbing.add(new Image(1, 1, loadImage("ShrekClimbing1.png"), ""));
		climbing.add(new Image(1, 1, loadImage("ShrekClimbing2.png"), ""));
		surrealAnimation1.add(new Image(1, 1, loadImage("nogenders.jpg"), ""));
		surrealAnimation2.add(new Image(1, 1, loadImage("nogenders (2).jpg"), ""));

		enemyAnimations.add(new Animation(0,0,1,1,1000,surrealAnimation1, ""));
		enemyAnimations.add(new Animation(0,0,1,1,1000,surrealAnimation2, ""));
		shrekAnimations.add(new Animation(0,0,1,1,400,idle, ""));
		shrekAnimations.add(new Animation(0,0,1,1,200,walkingLeft, ""));
		shrekAnimations.add(new Animation(0,0,1,1,200,walkingRight, ""));
		shrekAnimations.add(new Animation(0,0,1,1,100,climbing, ""));
	
		nogenders = new Enemy(1500, height-100, .2, .2, 5, 0, enemyAnimations, 20, .2,  "<ENEMY>");
		allShapes.add(nogenders);
	}
	
	
}
