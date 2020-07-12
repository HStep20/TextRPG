import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class game{
	public static void main(String[] args) throws InterruptedException, IOException{
		weapons w = new weapons();
		monster m = new monster();
		world wor = new world();
		//System.out.println(wor.getStartBiome());
		createCharacter(w, m, wor);
	}
	public static void createCharacter(Object w, Object m, Object wor) throws InterruptedException, IOException{
		Scanner scan = new Scanner(System.in);
		System.out.print("What is your hero's name? (Type 'random' for a random name) ");
		String playerName = scan.next();
		if(playerName.equalsIgnoreCase("random")){
			playerName = randomName();
		} 
		character p = new character(playerName);
		//levelTest(p,w);
		//setEnemyLevel(m, p);
		if(playerName.equalsIgnoreCase("skip")){
			begin(p,m,w, wor);
		}else{
		backStory(p);
		}
		boolean boredom = false;
		int counter = 0;
		while(boredom == false){
		begin(p,m,w, wor);
		
		}
	}
	public static void begin(Object p, Object m, Object w, Object wor) throws InterruptedException, IOException{
		Scanner scan = new Scanner(System.in);
		clearConsole();
		System.out.println("\n\nWhat would you like to do?");
			System.out.printf("%9s", "Explore");
			System.out.printf("%9s", "Search\n");	
			System.out.printf("%9s", "Battle");
			System.out.printf("%9s", "Info\n");
			System.out.printf("%9s", "Gather");
			System.out.printf("%9s", "Travel\n");
			System.out.printf("%9s", "Shop");
			System.out.printf("%9s", "End\n");
			System.out.println("Current Health: " + ((character)p).getHealth() + "/" + ((character)p).getMaxHealth());

			System.out.println("Current Biome: " + ((world)wor).getStartBiome());
						
			String choice = scan.next();
				if(choice.equalsIgnoreCase("Explore")){
					explore(p,m,w, wor);
					TimeUnit.SECONDS.sleep(3);
					return;
				}else if(choice.equalsIgnoreCase("Search")){
					System.out.println("Incomplete");
					investigate(p,m,w); //TODO
					TimeUnit.SECONDS.sleep(3);
					return;
				}else if(choice.equalsIgnoreCase("Battle")){
					battle(p,m,w);	
				}else if(choice.equalsIgnoreCase("Info")){
					clearConsole();
					characterStatTest(p, w);
					System.out.println("\nType 'Back' to return");
					String back = scan.next();
					if(back.equalsIgnoreCase("back")){
						return;
					}
					int skipRepeat = 0;
					while(back != "back"){
					
						if(skipRepeat >= 1){
							System.out.println("Type 'Back' to return");
						}
						skipRepeat++;
						back = scan.nextLine();
						if(back.equalsIgnoreCase("back")){
							return;
						}
					}
					return;
				}else if(choice.equalsIgnoreCase("gather")){
					gather(p, wor);
				}else if(choice.equalsIgnoreCase("travel")){
					travel(p,wor);
				}else if(choice.equalsIgnoreCase("shop")){
					shop(p, wor);
				}else if(choice.equalsIgnoreCase("end")){
					System.exit(0);
				}else{
					
					System.out.println("Not a valid choice");
					waitTime();
					return;
				}


		
		
		System.out.println("I RETURNED");
	}
	private static void shop(Object p, Object wor) throws InterruptedException {
		Random r = new Random();
		Scanner scan = new Scanner(System.in);
		System.out.println("You have " + ((character)p).getGold() + " gold");
		System.out.println("The shop is selling: ");
		weapons w = new weapons();
		int inventory = r.nextInt(10)+1;
		int [] atk = new int[5];
		int [] cost = new int[5];
		String [] inven = new String[5];
		for(int i = 1; i<=4; i++){
			System.out.println(i +": " + w.shopToString());
			inven[i] = w.shopToString();
			cost[i] = w.getCost();
			atk[i] = w.getAttack();
			w.newWeapon();
		}
		System.out.println("5: Exit shop");
		System.out.println("Would you like to purchase anything?(enter a number)");
		int ans = scan.nextInt();
		if(ans == 1){
			if(((character)p).getGold()<cost[1]){
				System.out.println("You cannot afford that");
				waitTime();
				return;
			}else{
			((character)p).setGold(((character)p).getGold()-cost[1]);
			((character)p).newWeapon(atk[1]);
			System.out.println("You now have " + ((character)p).getAttack() + " Attack and " + ((character)p).getGold() + " Gold");
			waitTime();
			}
		}else if(ans == 2){
			if(((character)p).getGold()<cost[2]){
				System.out.println("You cannot afford that");
				waitTime();
				return;
			}else{
			((character)p).setGold(((character)p).getGold()-cost[2]);
			((character)p).newWeapon(atk[2]);
			System.out.println("You now have "+ ((character)p).getAttack() + " Attack and " + ((character)p).getGold() + " Gold");
			waitTime();
			}
		}else if(ans == 3){
			if(((character)p).getGold()<cost[3]){
				System.out.println("You cannot afford that");
				waitTime();
				return;
			}else{
			((character)p).setGold(((character)p).getGold()-cost[3]);
			((character)p).newWeapon(atk[3]);
			System.out.println("You now have "+ ((character)p).getAttack() + " Attack and " + ((character)p).getGold() + " Gold");
			waitTime();
			}
		}else if(ans == 4){
			if(((character)p).getGold()<cost[4]){
				System.out.println("You cannot afford that");
				waitTime();
				return;
			}else{
			((character)p).setGold(((character)p).getGold()-cost[4]);
			((character)p).newWeapon(atk[4]);
			System.out.println("You now have "+ ((character)p).getAttack() + " Attack and " + ((character)p).getGold() + " Gold");
			waitTime();
			}
		}else if(ans == 5){
			return;
		}
		
	}
	private static void travel(Object p, Object wor) throws InterruptedException {
		String firstBiome = ((world)wor).getStartBiome();
		String newBiome = ((world)wor).changeBiome();
		String [] direction = {"North", "South", "East", "West"};
		Random r = new Random();
		if(firstBiome.equals(newBiome)){
			System.out.println("You still find yourself in a " + newBiome);
		}else{
		System.out.println("You travel " + direction[r.nextInt(direction.length)] + " from the " + firstBiome + ", and find yourself in a " + newBiome);
		
		}
		TimeUnit.SECONDS.sleep(2);
		waitTime();		
	}
	public static void waitTime() throws InterruptedException{
		TimeUnit.SECONDS.sleep(1);
	}
	private static void explore(Object p, Object m, Object w, Object wor) throws InterruptedException {
		// TODO Auto-generated method stub
		Random r = new Random();
		int life = r.nextInt(3)+1;
		String biome = ((world)wor).getStartBiome();
		//System.out.println(biome);
		
		if(biome.equalsIgnoreCase("Desert")){
			String [] desert = {
					"You see sand. Lots and lots of sand...",
					"A scorpion stings your foot and you take " + life + " damage."
					
			};
			int instance = r.nextInt(desert.length);
			System.out.println(desert[instance]);
			if(instance == 1){
				((character)p).takeDamage(life);
				testDeath(p);
				System.out.println("You took " + life + " from the sting, and are now at " + ((character)p).getHealth() + "/"+ ((character)p).getMaxHealth()+ " health");
			}
			waitTime();
		}else if(biome.equalsIgnoreCase("Forest")){
			String [] forest = {
					"You see trees. Lots and lots of trees..."
			};
			int instance = r.nextInt(forest.length);
			System.out.println(forest[instance]);
			waitTime();
		}else if(biome.equalsIgnoreCase("Mountians")){
			
			String [] mountians = {
					"You are at the top of the world and can see everything",
					"It's cold and snowing, making you lose "+life+" health"
			};
			int instance = r.nextInt(mountians.length);
			System.out.println(mountians[instance]);
			if(instance == 1){
				((character)p).takeDamage(life);
				testDeath(p);
				System.out.println("You took " + life + " from the cold, and are now at " + ((character)p).getHealth() + "/"+ ((character)p).getMaxHealth()+ " health");
			}
			waitTime();
		}else if(biome.equalsIgnoreCase("Swamp")){
			String [] swamp = {
					"All of the mosquitos in this swamp have probably given you many diseases by now.",
					"Your socks are soaked after trudging through the swamp"
			};
			int instance = r.nextInt(swamp.length);
			System.out.println(swamp[instance]);
			waitTime();
		}else if(biome.equalsIgnoreCase("mesa")){
			String [] mesa = {
					"The sand almost looks to be painted red with blood",
					"A large bird swoops at you and attacks doing " + life + " damage",
					"Steep canyons drop off to your left"
			};
			int instance = r.nextInt(mesa.length);
			System.out.println(mesa[instance]);
			if(instance == 1){
				((character)p).takeDamage(life);
				testDeath(p);
				System.out.println("You took " + life + " from the bird, and are now at " + ((character)p).getHealth() + "/"+ ((character)p).getMaxHealth()+ " health");
			}
			waitTime();
		}else if(biome.equalsIgnoreCase("beach")){
			String [] beach = {
					"The ocean crashing against the shore soothes you"
			};
			int instance = r.nextInt(beach.length);
			System.out.println(beach[instance]);
			waitTime();
		}
		
	}
	public static void testDeath(Object p) throws InterruptedException{
		if(((character)p).getHealth() <= 0){
			System.out.println("Your health reached 0, and you perished");
			waitTime();
			System.exit(0);
			
		}
	}
	private static void investigate(Object p, Object m, Object w) throws InterruptedException {
		System.out.println("Feature Not Complete");
		waitTime();
		return;
	}
	private static void clearConsole() throws IOException {
		/*
		for(int i = 0; i <= 30; i++){
			System.out.println();
		}
		*/
	}
	private static void gather(Object p, Object wor) throws InterruptedException {
		System.out.println("Feature not complete");
		waitTime();
	}
	public static void backStory(Object p) throws InterruptedException{

		System.out.println("\nHello...");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Your name is " + ((character) p).getName() + "...");
		TimeUnit.SECONDS.sleep(2);
		fate();
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Now the time has come for you to become a hero");
		TimeUnit.SECONDS.sleep(3);

	}
	private static void fate() {
		Random r = new Random();
		String [] fates = {//10 total
				"You were born to a poor farmer who literally worked himself to death...",
				"You don't remember who you once were...",
				"Your mother was a tailor in the great city of Keusik, and your father was a blacksmith",
				"All you ever remember is being raised by wolves in the forest",
				"You were abandoned near a riverbank as a baby, and were raised by the kings daughter",
				"Insert creative backstory here",
				"Born in the desert of Scara, you have traveled with the caravans for as long as you can remember",
				"You were kicked out of the Mage's College for turning a professor's nose into a turnip",
				"Born a Halfling, yet raised by giants",
				"You fell from the sky, and from your celestial home"
		};
		
		int fate = r.nextInt(9)+1;
		System.out.println(fates[fate]);

	}
	private static void setEnemyLevel(Object m, Object p){
		((monster) m).setLevel(p);
		System.out.println("\nEnemy Level: " +((monster) m).getLevel());
	}
	public static void battle(Object p, Object m, Object w) throws InterruptedException{
		boolean dead = false;
		System.out.println("\n\nA wild " + ((monster)m).getName() + " appears!\nWhat do you want to do?\n");
		while(dead == false){
		dead = attack(dead,p,m,w);
		}
		return;
	}
	public static boolean attack(Boolean ded, Object p, Object m, Object w) throws InterruptedException{
		Scanner scan = new Scanner(System.in);
		Random r = new Random();
		System.out.printf("%-6s", "Fight");
		System.out.printf("%-6s", "Block\n");
		System.out.printf("%-6s", "Flee");
		System.out.printf("%-6s", "Help\n");
		String choice = scan.next();
		if(choice.equalsIgnoreCase("Fight")){
			((monster) m).attacked(totalAttack(p,w));
			System.out.println("You hit the " + ((monster)m).getName() + " and dealt " + totalAttack(p,w) + " damage");
			if(((monster)m).getHealth()<= 0){
				System.out.println("The " + ((monster)m).getName()+" has been defeated");
				TimeUnit.SECONDS.sleep(3);
				return ded = true;
			}else{
				System.out.println("The " + ((monster)m).getName()+ " hit you for " + r.nextInt(((monster)m).getAttack())+1);
				((character)p).takeDamage(((monster)m).getAttack());
				System.out.println("Your health is now " + ((character)p).getHealth());
				if(((character)p).getHealth() <= 0){
					System.out.println("You have perished");
					TimeUnit.SECONDS.sleep(2);
					System.exit(0);
				}
				return ded = false;
			}
		}else if(choice.equalsIgnoreCase("Block")){
			System.out.println("The " + ((monster)m).getName() + " attacked you for " + r.nextInt(((monster)m).getAttack())+1);
			int blockChance = r.nextInt(4)+1;
			if(blockChance == 1){
				System.out.println("You managed to block all damage");
			}else{
				int dmg = (((monster)m).getAttack() - ((character)p).getDefense());
				if(dmg <=0){
					dmg = r.nextInt(((monster)m).getAttack());
				}
				System.out.println("The " + ((monster)m).getName() + " got through your shield and did " + dmg + " damage");
				((character)p).takeDamage(dmg);
			
				if(((character)p).getHealth() <= 0){
					System.out.println("\nYour health is 0");
					System.out.println("\nYou have perished");
					TimeUnit.SECONDS.sleep(2);
					System.exit(0);
				}else{
					System.out.println("Your health is now " + ((character)p).getHealth());
					TimeUnit.SECONDS.sleep(3);
					return ded;
				}
			}
		}else if(choice.equalsIgnoreCase("Flee")){
			int fleeChance = r.nextInt(3)+1;
			if(fleeChance == 1){
				System.out.println("Congratulations! You escaped the " + (((monster)m).getName()));
				TimeUnit.SECONDS.sleep(3);
				return ded = true;
			}else{
				int dmg1 = r.nextInt(((monster)m).getAttack())+1;
				System.out.println("You failed to escape the "+ ((monster)m).getName());
				System.out.println("The " + ((monster)m).getName() + " attacked you for " + dmg1);
				((character)p).takeDamage(dmg1);
				if(((character)p).getHealth() <= 0){
					System.out.println("\nYour health is 0");
					System.out.println("\nYou have perished");
					TimeUnit.SECONDS.sleep(2);
					System.exit(0);
				}else{
					System.out.println("Your health is now " + ((character)p).getHealth());
					TimeUnit.SECONDS.sleep(3);
					return ded;
				}
			}
		}else if(choice.equalsIgnoreCase("Help")){
			
		}
		TimeUnit.SECONDS.sleep(3);
		return ded = false;
	}
	private static void levelTest(Object p, Object w) {
		characterStatTest(p, w);
		for(int i = 0; i <= 10 ; i++){
		((character) p).levelUp();
		characterStatTest(p, w);
		}
	}
	public static int totalAttack(Object p, Object w){
		int totalAtk = ((character)p).getAttack() + ((weapons)w).getAttack();
		return totalAtk;
	}
	public static String randomName(){
		String[] names = {"Godrick", "Feiin", "Strautt", "Kelsier", "Ayena", "Arrietty", "Raechal", "Vin", "Rogan", "Grognoir", "Reiyn", "Kandrae", "Brothkrau", "Riggnor", "Mustafar", "Jeydizle", "Cludge"};
		Random rand = new Random();  
		int randName = rand.nextInt(names.length-1);
		String playerName = names[randName]; 
		return playerName;
	}
	public static void characterStatTest(Object p, Object w){
		System.out.println("\nName: " + ((character) p).getName());
		System.out.println("Attack: " + ((character) p).getAttack());
		System.out.println("Weapons attack is: " + ((weapons) w).getAttack() + "\nWeapons type is: " + ((weapons) w).getType());
		System.out.println("Total Attack is: " + totalAttack(p,w));
		System.out.println("Defense: " + ((character) p).getDefense());
		System.out.println("Health: " + ((character) p).getHealth());
		System.out.println("Level: "+ ((character) p).getLevel());
		
	}

}		