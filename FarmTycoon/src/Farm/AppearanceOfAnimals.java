package Farm;

import java.util.Random;

import javax.swing.JPanel;

public class AppearanceOfAnimals extends Thread{

	Random random = new Random();
	static Random randomWildAnimalSelect = new Random();
	
	int minTime = 5000;
	int maxTime = 10000;
	//int randomTime = 0; //������ �����ϱ���� ���� �ð� 
	static int randomWildAnimal ;
	static boolean running = true;
	
	public static void randomMushroomSelect() {
		randomWildAnimal = randomWildAnimalSelect.nextInt(2);
	}
	@Override
	public synchronized void run() {
		while (running) {
			try {
				int randomTime = random.nextInt(maxTime - minTime + 1) + minTime; //�����ð� ����
				Thread.sleep(randomTime); //�����ð��� �帥 �� 
				System.out.println(running);
				System.out.println(randomTime);
				
				// ���� �÷��̾� ��ġ�� �����̶��
				if (Farming.playerImage.getY() >= 328 && Farming.playerImage.getY() <= 473) {
					if (Farming.playerImage.getX() >= 0 && Farming.playerImage.getX() <= 120) {
						System.out.println("���� ��ġ�� �����Դϴ�");

					}
					// ���� �÷��̾��� ��ġ�� ���̶��
				} else if (Farming.playerImage.getY() >= 330 && Farming.playerImage.getY() <= 475) {
					if (Farming.playerImage.getX() >= 600 && Farming.playerImage.getX() <= 720) {
						System.out.println("���� ��ġ�� ���Դϴ�");
					// ���� �÷��̾��� ��ġ�� ���̶��
					}
				} else if (Farming.playerImage.getY() >= -20 && Farming.playerImage.getY() <= 235) {
					if (Farming.playerImage.getX() >= 65 && Farming.playerImage.getX() <= 620) {
						System.out.println("���� ��ġ�� ���Դϴ�");

					}
					// ����, ��, ���� �ƴ� �� ���� ��ġ�� �� ���Ͱ� ��Ÿ����
				} else {
					System.out.println("���� ��ġ�� ������ �� �ִ� �� �Դϴ�");
					//randomMushroomSelect();
					//System.out.println(randomWildAnimal);
					//System.out.println(running);
					randomMushroomSelect();
					
					if(randomWildAnimal == 0) {
						
						JPanel dungeonWildWolf = new DungeonWildWolf();
						dungeonWildWolf.setVisible(true);
						Farming.dungeonScene.add(dungeonWildWolf);
						Farming.dungeonScene.setVisible(true);
						Farming.farmingScene.setVisible(false);
						//getContentPane().add(dungeonWildWolf);
						Player.hp = 100;
						//dungeonWildWolf.setVisible(true);
						//Farming.farmingScene.setVisible(false);
						//btnNewButton.setVisible(false);
						//running = true;
						//System.out.println(running);
						System.out.println("!!! ���Ϳ� ������ !!!");
						
					}else if(randomWildAnimal == 1) {
						running = false;
						JPanel dungeonWildBear = new DungeonWildBear();
						dungeonWildBear.setVisible(true);
						Farming.dungeonScene.add(dungeonWildBear);
						Farming.dungeonScene.setVisible(true);
						Farming.farmingScene.setVisible(false);
						//getContentPane().add(dungeonWildWolf);
						Player.hp = 100;
						//dungeonWildWolf.setVisible(true);
						//Farming.farmingScene.setVisible(false);
						//btnNewButton.setVisible(false);
						//running = true;
						System.out.println("!!! ���Ϳ� ������ !!!");
						//running = true;
						//System.out.println(running);
					}else if(randomWildAnimal == 2) {
						running = false;
						JPanel dungeonWildSnake = new DungeonWildSnake();
						dungeonWildSnake.setVisible(true);
						Farming.dungeonScene.add(dungeonWildSnake);
						Farming.dungeonScene.setVisible(true);
						Farming.farmingScene.setVisible(false);
						//getContentPane().add(dungeonWildWolf);
						Player.hp = 100;
						//dungeonWildWolf.setVisible(true);
						//Farming.farmingScene.setVisible(false);
						//btnNewButton.setVisible(false);
						//running = true;
						System.out.println("!!! ���Ϳ� ������ !!!");
						//running = true;
						//System.out.println(running);
					}
					running = false;
					// ���Ͱ� ��Ÿ����.
				}

			} catch (InterruptedException e) {
				running = false;
			}
		}
	}
}
