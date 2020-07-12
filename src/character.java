import java.util.Random;
import java.util.*;

public class character{
	private final String name;
	private int defense;
	private int attack;
	private int health;
	private int level;
	private int experience;
	private final int maxHealth;
	private int gold;
	private int weaponDamage;
	public character(String name){
		this.level = 1;
		this.experience = 0;
		this.name = name;
		this.gold = initialGold();
		this.attack = initialAttack() + weaponDamage;
		this.defense = initialDefense();
		this.health = initialHealth();
		this.level = initialLevel();
		maxHealth = this.health;
	
	}
	public int getMaxHealth(){
		return maxHealth;
	}
	public int initialLevel(){
		return 1;
	}
	public void setExp(int exp){
		this.experience = exp;
	}
	public void levelUp(){
		this.level = this.level + 1;
		setAttack();
		setDefense();
		setHealth();
	}
	public void takeDamage(int dmg){
		this.health = this.health -dmg;
	}
	public void setAttack(){
		int newAttack = (this.attack);
		this.attack =  (int) ((this.attack + newAttack) /1.7)+1;
	}
	public void newWeapon(int attack){
		weaponDamage = attack;
	}
	public void setDefense(){
		int newDefense = (this.defense/(2*(level/2)));
		this.defense = this.defense + newDefense;
	}
	public void setHealth(){
		int newHealth = (this.health/4);
		this.health = this.health + newHealth;
	}
	public void setGold(int gold){
		this.gold = gold;
	}
	public int getGold(){
		return this.gold;
	}
	public int initialGold(){
		Random r = new Random();
		int gold = r.nextInt(50)+50;
		return gold;
	}
	public int initialDefense() {
		Random r = new Random();
		int def = r.nextInt(4)+2;
		return def;
	}
	public int initialAttack(){
		Random r = new Random();
		int atk = r.nextInt(2)+1;
		return atk;
	}
	public int initialHealth(){
		Random r = new Random();
		int hlth = r.nextInt(4)+8;
		return hlth;
	}
	public String getName() {
		return this.name;
	}
	public int getAttack(){
		return this.attack;
	}
	public int getHealth(){
		return this.health;
	}
	public int getDefense(){
		return this.defense;
	}
	public int getLevel() {
		return this.level;
	}

}