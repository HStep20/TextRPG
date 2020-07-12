import java.util.Random;

public class monster{
	private final String name;
	private int health;
	private int defense;
	private int attack;
	private int level;
	
	
	public monster(){
		Random r = new Random();
		health = r.nextInt(14)+1;
		defense = r.nextInt(4)+1;
		attack = r.nextInt(5)+1;
		name = setName();
		level = this.level;

	}
	public static void levelUp(){
		
	}
	public void setHealth(){
		Random r = new Random();
		this.health = this.health + (r.nextInt(2*level)+1);
	}
	public void attacked(int dmg){
		this.health= this.health -dmg;
	}
	public int getHealth(){
		return this.health;
	}
	public void setAttack(){
		int newAttack = (this.attack);
		this.attack =  (int) ((this.attack + newAttack) /1.7)+1;
	}
	public int getAttack(){
		return this.attack;
	}
	public void setDefense(){
		int newDefense = (this.defense/(2*(level/2)));
		this.defense = this.defense + newDefense;
	}
	public int getDefense(){
		return this.defense;
	}
	
	public static String setName(){
		Random r = new Random();
		String[] monsterNames = {"Slime", "Zombie", "Skeleton", "Creeper", "Enderman"};
		int i = r.nextInt(5);
		return monsterNames[i];
	}
	public void setLevel(Object p){
		this.level = ((character) p).getLevel();
	}
	public int getLevel(){
		return this.level;
	}
	public String getName() {
		return this.name;
	}
}