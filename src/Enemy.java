import java.util.ArrayList;

public class Enemy extends Character{
	public boolean canUpdateX;
	public Enemy(int xPos, int yPos, double xScale, double yScale, double xSpeed, double ySpeed,
			ArrayList<Animation> list, String tag, int damage) {
		super(xPos, yPos, xScale, yScale, xSpeed, ySpeed, list, "<DAMAGING>"+tag);
		setDamage(damage);
		canUpdateX = true;
	}
	
	
	public void boundTrigger(){
		setXSpeed(getXSpeed()*-1);
	}
	
	public void enemyUpdate(ArrayList<BasicShape> list){
		for(BasicShape s : list){
			if(s.getTag().toUpperCase().contains("<ENEMYBOUND>")){
				if(collidesWith(s)){
					boundTrigger();
				}
			}
		}
	}
	

}
