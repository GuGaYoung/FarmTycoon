package Farm;

import java.util.Random;

public class Player {

	public int speed = 5;
	public static int energy = 100;
	public static int money = 0;
	
	public int amountPumpkinSeed = 10; //������ �ִ� ȣ�ھ��� ��
	public int amountOnionSeed = 10; //������ �ִ� ���ľ��� �� 
	public int amountCabbageSeed = 10; //������ �ִ� ����߾��� ��
	public int amountCarrotSeed = 10; //������ �ִ� ��پ��� ��
	public int amountPumpkin = 0; //������ �ִ� ȣ���� ��
	public int amountOnion = 0; //������ �ִ� ������ �� 
	public int amountCabbage = 0; //������ �ִ� ������� ��
	public int amountCarrot = 0; //������ �ִ� ����� ��
	
	public static int amountBone = 2;//������ �ִ� ���� ��
	
	static Random randomPower = new Random();
	static int hp = 100;
	static int attackPower; 
	static int amountRandomMushroom = 2;
	static int amountPotionHp_30 = 3;
	static int amountPotionHp_50 = 3;
}
