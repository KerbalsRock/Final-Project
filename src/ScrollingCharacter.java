import java.util.ArrayList;

public class ScrollingCharacter extends Character{
	double pseudoY, gravity;
	boolean canJump;
	boolean canClimb;
	boolean dropping;
	boolean immune;
	long immuneStart;
	int immuneTime;
	int health;
	int originalX;
	public ScrollingCharacter(int xPos, int yPos, double xScale, double yScale, double xSpeed, double ySpeed,
			double gravity, ArrayList<Animation> list, String tag) {
		super(xPos, yPos, xScale, yScale, xSpeed, ySpeed, list, tag);
		this.gravity = gravity;
		pseudoY = yPos;
		canJump = false;
		canClimb = false;
		dropping = false;
		health = 100;
		immune = false;
		immuneTime = 2000;
		immuneStart = System.currentTimeMillis()-immuneTime;
		originalX = xPos;
	}
	
	public void update(){
		autoToNext();
		setYSpeed(getYSpeed()+gravity);
		pseudoY+=getYSpeed();
		setY((int)pseudoY);
		setX((int)(getX()+getXSpeed()));
	}
	
	public void scrollerUpdate(ArrayList<BasicShape> allObjects){
		autoToNext();
		if(!canClimb){
			setYSpeed(getYSpeed()+gravity);
		}
		pseudoY+=getYSpeed();
		setY((int)pseudoY);
		boolean updateX = true;
		canJump = false;
		canClimb = false;
		
		if(immuneStart + immuneTime < System.currentTimeMillis()){
			immune = false;
		}
		
		for(BasicShape s : allObjects){
			s.update();
			if(s.getTag().toUpperCase().contains("<WALL>")){
				String collided = s.sideCollision(this);
				if(collided.equals("LEFT")){
					setX(s.getX() - getWidth());
					if(getXSpeed() >= 0){
						updateX = false;
					}
				}
				else if(collided.equals("RIGHT")){
					setX(s.getX2());
					if(getXSpeed() <= 0){
						updateX = false;
					}
				}
				else if(collided.equals("TOP")){
					dropping = false;
					canJump = true;
					setY(s.getY() - getHeight());
					pseudoY = getY();
					if(getYSpeed() >= 0){
						setYSpeed(0);
					}
				}
				else if(collided.equals("BOTTOM")){
					setY(s.getY2());
					pseudoY = getY();
					if(getYSpeed() <= 0){
						setYSpeed(0);
					}
				}	
			}
			if(s.getTag().toUpperCase().contains("<BOTTOMLESS>")){ 
				String collided = s.bottomlessSideCollision(this);
				if(collided.equals("NONE") && s.getTag().toUpperCase().contains("<DROPPED>")){
					s.setTag(s.getTag().toUpperCase().replaceAll("<DROPPED>", ""));
				}
				else if(collided.equals("LEFT") && !s.getTag().toUpperCase().contains("<DROPPED>")){
					setX(s.getX() - getWidth());
					if(getXSpeed() >= 0){
						updateX = false;
					}
				}
				else if(collided.equals("RIGHT") && !s.getTag().toUpperCase().contains("<DROPPED>")){
					setX(s.getX2());
					if(getXSpeed() <= 0){
						updateX = false;
					}
				}
				else if(collided.equals("TOP")){
					if(s.getBottomlessCanCollide()){
						if(!dropping && !s.getTag().toUpperCase().contains("<DROPPED>")){
							canJump = true;
							setY(s.getY() - getHeight());
							pseudoY = getY();
							if(getYSpeed() >= 0){
								setYSpeed(0);
							}
						}else{
							s.setTag(s.getTag()+"<DROPPED>");
							dropping = false;
						}
					}
				}	 
			}
			if(s.getTag().toUpperCase().contains("<LADDER>")){
				if(collidesWith(s)){
					setYSpeed(0);
					canClimb = true;
				}
			}
			if(s.getTag().toUpperCase().contains("<DAMAGING>")){
				if(collidesWith(s) && !immune){
					health -= s.getDamage();
					System.out.println(health);
					immune = true;
					immuneStart = System.currentTimeMillis();
				}
			}
			try{
				((Enemy)s).checkCollisionAndDoStuff(allObjects);
			}catch(ClassCastException e){}
		}
		if(updateX){
			for(BasicShape s : allObjects){
				s.setX((int)(s.getX() - getXSpeed()));
			}
		}
		int offset = originalX - getX();
		setX(getX()+offset);
		for(BasicShape s : allObjects){
			s.setX(s.getX()+offset);
		}
	}
	
	public void jump(double jumpSpeed){
		if(canJump){
			if(getYSpeed() > 0){
				setYSpeed(0);
			}
			setYSpeed(jumpSpeed);
		}
	}
	public void climb(double climbSpeed){
		if (canClimb){
			setYSpeed(climbSpeed);
		}
	}
	
	public void drop(){
		if(canJump){
			dropping = true;
		}
	}
	
	public boolean getCanClimb(){
		return canClimb;
	}
	
	public void goToNext(){
		int prevHeight = getHeight();
		super.goToNext();
		pseudoY += (prevHeight - getHeight());
		
	}
	public int getHealth(){
		return health;
	}
	public void setAnimation(int index){
		int prevHeight = getHeight();
		super.setAnimation(index);
		pseudoY += (prevHeight - getHeight());
	}
	public void setScale(double xScale, double yScale){
		int prevHeight = getHeight();
		super.setScale(xScale, yScale);
		pseudoY += (prevHeight - getHeight());
	}

}
