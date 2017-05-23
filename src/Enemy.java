import java.util.ArrayList;

public class Enemy extends Character{
	public boolean canUpdateX;
	private double pseudoY, gravity;
	public Enemy(int xPos, int yPos, double xScale, double yScale, double xSpeed, double ySpeed,
			ArrayList<Animation> list,  int damage, double gravity, String tag) {
		super(xPos, yPos, xScale, yScale, xSpeed, ySpeed, list, "<DAMAGING>"+tag);
		setDamage(damage);
		canUpdateX = true;
		this.gravity = gravity;
		pseudoY = getY();
	}
	
	
	public void boundTrigger(){
		setXSpeed(getXSpeed()*-1);
	}
	
	public void checkCollisionAndDoStuff(ArrayList<BasicShape> list){
		for(BasicShape s : list){
			if(s.getTag().toUpperCase().contains("<ENEMYBOUND>")){
				if(s.sideCollision(this).equals("LEFT") || s.sideCollision(this).equals("RIGHT")){
					boundTrigger();
				}else if(s.sideCollision(this).equals("TOP")){
					setY(s.getY() - getHeight());
					pseudoY = getY();
					if(getYSpeed() >= 0){
						setYSpeed(0);
					}
				}
			}
		}
	}
	public void update(){
		setX((int)(getX() + getXSpeed()));
		setYSpeed(getYSpeed()+gravity);
		pseudoY+=getYSpeed();
		setY((int)pseudoY);
	}
	

}
