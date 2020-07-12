import java.util.Random;

public class world{
	String startingBiome;
	String currentBiome;
	String foundInBiome;
	
	public world(){
		Random r = new Random();
		String [] biomes = {"Desert", "Forest", "Mountians", "Swamp", "Mesa", "Beach"};
		this.startingBiome = biomes[r.nextInt(biomes.length-1)];
	}
	public String changeBiome(){
		Random r = new Random();
		String [] biomes = {"Desert", "Forest", "Mountians", "Swamp", "Mesa", "Beach"};
		this.startingBiome = biomes[r.nextInt(biomes.length-1)];
		return this.startingBiome;
	}
	public String getStartBiome(){
		return startingBiome;
	}
}