import java.util.ArrayList;

public class ScrollingCharacter extends Character{
	double pseudoY, gravity;
	boolean canJump;
	boolean canClimb;
	public ScrollingCharacter(int xPos, int yPos, double xScale, double yScale, double xSpeed, double ySpeed,
			double gravity, ArrayList<Animation> list, String tag) {
		super(xPos, yPos, xScale, yScale, xSpeed, ySpeed, list, tag);
		this.gravity = gravity;
		pseudoY = yPos;
		canJump = false;
		canClimb = false;
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
		boolean updateY = true;
		boolean updateX = true;
		canJump = false;
		canClimb = false;
		for(BasicShape s : allObjects){
			if(s.getTag().equals("WALL")){
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
					canJump = true;
					setY(s.getY() - getHeight());
					pseudoY = getY();
					if(getYSpeed() >= 0){
						updateY = false;
					}
				}
				else if(collided.equals("BOTTOM")){
					setY(s.getY2());
					pseudoY = getY();
					if(getYSpeed() <= 0){
						updateY = false;
					}
				}	
			}
			else if(s.getTag().equals("BOTTOMLESS")){ 
				String collided = s.bottomlessSideCollision(this);
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
					if(s.getBottomlessCanCollide()){
						canJump = true;
						setY(s.getY() - getHeight());
						pseudoY = getY();
						if(getYSpeed() >= 0){
							updateY = false;
						}
					}
				}	 
			}
			else if(s.getTag().equals("LADDER")){
				if(collidesWith(s)){
					canClimb = true;
				}
			}
		}
		if(updateY){
			if(!canClimb){
				setYSpeed(getYSpeed()+gravity);
			}
			pseudoY+=getYSpeed();
			setY((int)pseudoY);
		}else{
			setYSpeed(0);
		}
		
		if(updateX){
			for(BasicShape s : allObjects){
				s.setX((int)(s.getX() - getXSpeed()));
			}
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
	
	public boolean getCanClimb(){
		return canClimb;
	}
	
	public void goToNext(){
		int prevHeight = getHeight();
		super.goToNext();
		setY(getY() + (prevHeight - getHeight()));
	}

}
