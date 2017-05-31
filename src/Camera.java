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
	private Enemy dropInEnemy;
	private Enemy mob1;
	private Enemy mob2;
	private Enemy mob3;
	private Enemy mob4;
	private Enemy mob5;
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
	private AudioPlayer allStar;
	private AudioPlayer shootingStars;
	private AudioPlayer earRape;
	double fontSize;
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
		levelLength = 8000;
		background = loadImage("sky.jpg");
		g = createGraphics(width, height);
		listsInit();
		shrek = new ScrollingCharacter(width/4,height -100, 1, 1, 0, 0, .25, shrekAnimations, "<ENEMYBOUND>");
		shrekHealth = new HealthBar(width-300, 0, 300, 50, shrek.getHealth());
		minim = new Minim(this);
		allStar = minim.loadFile("All Star - Smash Mouth [Lyrics].mp3");
		shootingStars = minim.loadFile("Bag Raiders - Shooting Stars.mp3");
		earRape = minim.loadFile("Smash Mouth - Allstar (Earrape).mp3");
		fontSize = 60;
		allStar.play();
	}
	public void draw(){
		if(shrek.getHealth()>0){
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
		else if(shrek.levelCompleted){
			background(Color.blue.getRGB());
			if(allStar.isPlaying()){
				allStar.pause();
				shootingStars.cue(23500);
			}
			shootingStars.play();
			if(shootingStars.mix.level()>.35){
				background(Color.green.getRGB());
			}
			textSize((int)fontSize);
			fontSize += .05;
			textAlign(CENTER,CENTER);
			text("You Win!", width/2, height/2);
		}
		else{
			background(Color.red.getRGB());
			if(allStar.isPlaying()){
				allStar.pause();
				earRape.cue(allStar.position());
			}
			earRape.play();
			textSize(69);
			textAlign(CENTER,CENTER);
			text("You Lose", width/2, height/2);
		}
	}
	
	private void update(){
		shrekHealth.setHealth(shrek.getHealth());
		for(BasicShape s : allShapes){
			try{
				if(((Enemy)s).getXSpeed() < 0){
					((Enemy)s).setAnimation(1);
				}else{
					((Enemy)s).setAnimation(0);
				}
			}catch(ClassCastException e){}
			
		}
		if(allStar.position()>=allStar.length()){
			allStar.rewind();
		}
		if(a){
			shrek.setXSpeed(-5);
			if(shrek.getCanClimb()){
				shrek.setAnimation(3);
			}else{
				shrek.setAnimation(1);
			}
		}else if(d){
			shrek.setXSpeed(5);
			if(shrek.getCanClimb()){
				shrek.setAnimation(3);
			}else{
				shrek.setAnimation(2);
			}
		}else{
			shrek.setXSpeed(0);
		}
		if(space){
			shrek.jump(-10);
		}
		if(w){
			if(shrek.getCanClimb()){
				shrek.setAnimation(3);
			}
			shrek.climb(-3);
		}else if(s){
			if(shrek.getCanClimb()){
				shrek.setAnimation(3);
				shrek.climb(2);
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
		if (allStar.position() == allStar.length()){
			allStar.rewind();
			allStar.play();
		}
	
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

		
		allShapes.add(new Rectangle(0, height, levelLength, 10, Color.green, "<WALL>"));
		allShapes.add(new Rectangle(-width, -height, width, height*2, Color.darkGray, "<WALL>"));
		allShapes.add(new Rectangle(levelLength, -height, width, height*2, Color.darkGray, "<WALL>"));
		allShapes.add(new Rectangle(0, -10, levelLength, 10, Color.darkGray, "<WALL>"));

		allShapes.add(new Rectangle(0, height - 25, 600, 25, Color.green, "<WALL>"));//starting plat
		allShapes.add(new Image(600, height - 20, 200, 20, loadImage("lava.jpg"), "<DAMAGING>"));//first lava
		allShapes.add(new Rectangle(800, height - 25, 300, 25, Color.green, "<WALL>"));//second plat
		allShapes.add(new Image(1100, height - 20, 350, 20, loadImage("lava.jpg"), "<DAMAGING>"));//second lava
		allShapes.add(new Rectangle(1450, height - 25, 300, 25, Color.green, "<WALL>"));//third plat
		allShapes.add(new Image(1750, height - 20, 550, 20, loadImage("lava.jpg"), "<DAMAGING>"));//third lava
		allShapes.add(new Rectangle(1650, height - 170, 750, 10, new Color(139,69,19), "<BOTTOMLESS>"));//first bottomless
		allShapes.add(new Rectangle(2300, height - 25, 500, 25, Color.green, "<WALL>"));//fourth plat
		allShapes.add(new Image(2750, height - 225, 50, 200, loadImage("ladder.png"), "<LADDER>"));//ladder 1 part 1
		allShapes.add(new Image(2750, height - 425, 50, 200, loadImage("ladder.png"), "<LADDER>"));//ladder 1 part 2
		allShapes.add(new Image(2750, height - 625, 50, 200, loadImage("ladder.png"), "<LADDER>"));//ladder 1 part 3
		allShapes.add(new Rectangle(2800, height - 540, 10, 540, Color.darkGray, "<WALL><ENEMYBOUND>"));//ladder blocker
		allShapes.add(new Rectangle(2800, height - 550, 600, 10, new Color(139,69,19), "<BOTTOMLESS>"));//second bottomless
		allShapes.add(new Rectangle(3400, height - height, 10, height - 540, Color.darkGray, "<WALL>"));//other blocker
		allShapes.add(new Rectangle(2810, height - 25, 690, 25, Color.green, "<WALL><ENEMYBOUND>"));//fifth plat
		
		allShapes.add(new Rectangle(3500, height - 100, 100, 100, Color.green, "<WALL><ENEMYBOUND>"));//parkour square
		allShapes.add(new Image(3600, height - 20, 500, 20, loadImage("lava.jpg"), "<DAMAGING>"));//parkour lava 1
		allShapes.add(new Image(4100, height - 20, 500, 20, loadImage("lava.jpg"), "<DAMAGING>"));//parkour lava 2
		allShapes.add(new Image(4600, height - 20, 500, 20, loadImage("lava.jpg"), "<DAMAGING>"));//parkour lava 3
		allShapes.add(new Image(5100, height - 20, 400, 20, loadImage("lava.jpg"), "<DAMAGING>"));//parkour lava 4
		allShapes.add(new Rectangle(3800, height - 200, 25, 10, new Color(139,69,19), "<BOTTOMLESS>"));//first parkour block
		allShapes.add(new Rectangle(4100, height - 300, 25, 10, new Color(139,69,19), "<BOTTOMLESS>"));//second parkour block
		allShapes.add(new Rectangle(4250, height - 450, 40, 10, new Color(139,69,19), "<BOTTOMLESS>"));//third parkour block
		allShapes.add(new Rectangle(4800, height - 100, 40, 10, new Color(139,69,19), "<BOTTOMLESS>"));//fourth parkour block
		allShapes.add(new Rectangle(4810, height - 280, 30, 10, new Color(139,69,19), "<BOTTOMLESS>"));//fifth parkour block
		allShapes.add(new Rectangle(5050, height - 460, 40, 10, new Color(139,69,19), "<BOTTOMLESS>"));//final parkour block
		
		allShapes.add(new Rectangle(5500, height - 25, 1600, 25, Color.green, "<WALL>"));//fifth plat
		allShapes.add(new Image(6050, height - 225, 50, 200, loadImage("ladder.png"), "<LADDER>"));//ladder 2 part 1
		allShapes.add(new Image(6050, height - 425, 50, 200, loadImage("ladder.png"), "<LADDER>"));//ladder 2 part 2
		allShapes.add(new Image(6050, height - 625, 50, 200, loadImage("ladder.png"), "<LADDER><ENEMYBOUND>"));//ladder 2 part 3
		allShapes.add(new Image(6050, height - 825, 50, 200, loadImage("ladder.png"), "<LADDER>"));//ladder 2 part 4
		allShapes.add(new Rectangle(6100, height - 450, 600, 25, Color.darkGray, "<WALL><ENEMYBOUND>"));//split divider
		
		allShapes.add(new Rectangle(6130, height - 320, 10, 295, Color.darkGray, "<WALL>"));//ladder blocke
		allShapes.add(new Image(6140, height - 225, 70, 200, loadImage("ladder.png"), "<LADDER>"));//evil ladder -1 part 1
		allShapes.add(new Image(6140, height - 425, 70, 200, loadImage("ladder.png"), "<LADDER>"));//evil ladder -1 part 2
		allShapes.add(new Rectangle(6210, height - 425, 10, 295, Color.darkGray, "<WALL>"));//ladder blocker
		allShapes.add(new Image(6220, height - 225, 70, 200, loadImage("ladder.png"), "<LADDER>"));//evil ladder 0 part 1
		allShapes.add(new Image(6220, height - 425, 70, 200, loadImage("ladder.png"), "<LADDER>"));//evil ladder 0 part 2
		allShapes.add(new Rectangle(6290, height - 320, 10, 295, Color.darkGray, "<WALL>"));//ladder blocker
		allShapes.add(new Image(6300, height - 225, 70, 200, loadImage("ladder.png"), "<LADDER>"));//evil ladder 1 part 1
		allShapes.add(new Image(6300, height - 425, 70, 200, loadImage("ladder.png"), "<LADDER>"));//evil ladder 1 part 2
		allShapes.add(new Rectangle(6370, height - 425, 10, 295, Color.darkGray, "<WALL>"));//ladder blocker
		allShapes.add(new Image(6380, height - 225, 70, 200, loadImage("ladder.png"), "<LADDER>"));//evil ladder 2 part 1
		allShapes.add(new Image(6380, height - 425, 70, 200, loadImage("ladder.png"), "<LADDER>"));//evil ladder 2 part 2
		allShapes.add(new Rectangle(6450, height - 320, 10, 295, Color.darkGray, "<WALL>"));//ladder blocker
		allShapes.add(new Image(6460, height - 225, 70, 200, loadImage("ladder.png"), "<LADDER>"));//evil ladder 3 part 1
		allShapes.add(new Image(6460, height - 425, 70, 200, loadImage("ladder.png"), "<LADDER>"));//evil ladder 3 part 2
		allShapes.add(new Rectangle(6530, height - 425, 10, 295, Color.darkGray, "<WALL>"));//ladder blocker
		allShapes.add(new Image(6540, height - 225, 70, 200, loadImage("ladder.png"), "<LADDER>"));//evil ladder 4 part 1
		allShapes.add(new Image(6540, height - 425, 70, 200, loadImage("ladder.png"), "<LADDER>"));//evil ladder 4 part 2
		allShapes.add(new Rectangle(6610, height - 320, 10, 295, Color.darkGray, "<WALL>"));//ladder blocker
		allShapes.add(new Image(6620, height - 225, 70, 200, loadImage("ladder.png"), "<LADDER>"));//evil ladder 5 part 1
		allShapes.add(new Image(6620, height - 425, 70, 200, loadImage("ladder.png"), "<LADDER>"));//evil ladder 5 part 2
		allShapes.add(new Rectangle(6690, height - 425, 10, 295, Color.darkGray, "<WALL>"));//ladder blocker
		
		allShapes.add(new Rectangle(6100, height - 500, 10, 50, Color.darkGray, "<WALL><ENEMYBOUND>"));//left enemy blocker
		allShapes.add(new Rectangle(6250, height - height, 10, height - 550, Color.darkGray, "<WALL>"));//ladder blocker
		allShapes.add(new Rectangle(6375, height - height, 10, height - 550, Color.darkGray, "<WALL>"));//ladder blocker
		allShapes.add(new Rectangle(6500, height - height, 10, height - 550, Color.darkGray, "<WALL>"));//ladder blocker
		allShapes.add(new Rectangle(6625, height - height, 10, height - 550, Color.darkGray, "<WALL>"));//ladder blocker
		allShapes.add(new Rectangle(6690, height - 500, 10, 50, Color.darkGray, "<WALL><ENEMYBOUND>"));//right enemy blocker
		
		allShapes.add(new Image(7100, height-25, 300, 25, loadImage("finishline.png"), "<END>"));
		allShapes.add(new Image(7400, height-25, 300, 25, loadImage("finishline.png"), "<END>"));
		allShapes.add(new Image(7700, height-25, 300, 25, loadImage("finishline.png"), "<END>"));
		

		
		
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
		surrealAnimation1.add(new Image(1, 1, loadImage("nogenders.png"), ""));
		surrealAnimation2.add(new Image(1, 1, loadImage("nogenders (2).png"), ""));

		enemyAnimations.add(new Animation(0,0,1,1,1000,surrealAnimation1, ""));
		enemyAnimations.add(new Animation(0,0,1,1,1000,surrealAnimation2, ""));
		shrekAnimations.add(new Animation(0,0,1,1,400,idle, ""));
		shrekAnimations.add(new Animation(0,0,1,1,200,walkingLeft, ""));
		shrekAnimations.add(new Animation(0,0,1,1,200,walkingRight, ""));
		shrekAnimations.add(new Animation(0,0,1,1,100,climbing, ""));
	
		allShapes.add(new Enemy(2900, height-100, .2, .2, 3, 0, enemyAnimations, 20, .2, "<ENEMY>"));//drop in
		
		allShapes.add(new Enemy(6100, height-550, .1, .1, 1, 0, enemyAnimations, 20, .2, "<ENEMY>"));//mob 1
		allShapes.add(new Enemy(6100, height-550, .1, .1, 2, 0, enemyAnimations, 20, .2, "<ENEMY>"));//mob 2
		allShapes.add(new Enemy(6100, height-550, .1, .1, 3, 0, enemyAnimations, 20, .2, "<ENEMY>"));//mob 3
		allShapes.add(new Enemy(6100, height-550, .1, .1, 4, 0, enemyAnimations, 20, .2, "<ENEMY>"));//mob 4
		allShapes.add(new Enemy(6500, height-550, .1, .1, 5, 0, enemyAnimations, 20, .2, "<ENEMY>"));//mob 5
		
		allShapes.add(new Enemy(6500, height-550, .1, .1, -1, 0, enemyAnimations, 20, .2, "<ENEMY>"));//mob 6
		allShapes.add(new Enemy(6500, height-550, .1, .1, -2, 0, enemyAnimations, 20, .2, "<ENEMY>"));//mob 7
		allShapes.add(new Enemy(6500, height-550, .1, .1, -3, 0, enemyAnimations, 20, .2, "<ENEMY>"));//mob 8
		allShapes.add(new Enemy(6500, height-550, .1, .1, -4, 0, enemyAnimations, 20, .2, "<ENEMY>"));//mob 9
		allShapes.add(new Enemy(6500, height-550, .1, .1, -5, 0, enemyAnimations, 20, .2, "<ENEMY>"));//mob 10

	}
	
	
}
