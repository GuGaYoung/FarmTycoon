package Farm;

import java.util.Random;

public class Player {

	public int speed = 5;
	public static int energy = 100;
	public static int money = 0;
	
	public int amountPumpkinSeed = 10; //가지고 있는 호박씨의 수
	public int amountOnionSeed = 10; //가지고 있는 양파씨의 수 
	public int amountCabbageSeed = 10; //가지고 있는 양배추씨의 수
	public int amountCarrotSeed = 10; //가지고 있는 당근씨의 수
	public int amountPumpkin = 0; //가지고 있는 호박의 수
	public int amountOnion = 0; //가지고 있는 양파의 수 
	public int amountCabbage = 0; //가지고 있는 양배추의 수
	public int amountCarrot = 0; //가지고 있는 당근의 수
	
	public static int amountBone = 2;//가지고 있는 뼈의 수
	
	static Random randomPower = new Random();
	static int hp = 100;
	static int attackPower; 
	static int amountRandomMushroom = 2;
	static int amountPotionHp_30 = 3;
	static int amountPotionHp_50 = 3;
}
