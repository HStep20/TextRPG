import java.util.Random;

public class weapons{
	private String Type;
	private String Name;
	private int attack;
	private int cost;

	public weapons(){
		this.attack = setInitialAttack();
		this.Type = setType();
	}
	public int setInitialAttack(){
		Random r = new Random();
		int atk = r.nextInt(2)+2;//TODO 9, not 2
		return atk;
	}
	public void newAttack(){
		Random r = new Random();
		this.attack = r.nextInt(15)+1;
	}
	public void newWeapon(){
		newAttack();
		this.Type = setType();
	}
	public int getCost(){
		return this.cost;
	}
	public String setType(){
		Random r = new Random();
		if(this.attack<2){
			this.cost = r.nextInt(3)+1;
			return "Stick";
		}else if(this.attack >=2 && this.attack <= 4){
			this.cost = r.nextInt(12)+6;
			return "Shoddy Knife";
		}else if(this.attack >=5 && this.attack <= 7){
			this.cost = r.nextInt(20)+10;
			return "Mediocre Shortsword";
		}else if(this.attack >=8 && this.attack < 10){
			this.cost = r.nextInt(40)+20;
			return "Decent Longsword";
		}else 
			this.cost = r.nextInt(100)+50;
			return "Good LongLongsword";
		}
	public String getType() {
		return this.Type;
	}
	public int getAttack(){
		return this.attack;
	}
	public String shopToString(){
		return "Type: " + this.Type + ", Attack = " + this.attack + ", Cost: " + this.cost +" Gold";
	}
}