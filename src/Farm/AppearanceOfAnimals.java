package Farm;

import java.util.Random;

public class AppearanceOfAnimals extends Thread{

	Random random = new Random();
	
	int minTime = 10000;
	int maxTime = 15000;
	int randomTime = 0; //������ �����ϱ���� ���� �ð� 
	boolean running = true;
	
	@Override
	public synchronized void run() {
		while (running) {
			try {

				randomTime = random.nextInt(maxTime - minTime + 1) + minTime; //�����ð� ����
				Thread.sleep(randomTime); //�����ð��� �帥 �� 

				// ���� �÷��̾� ��ġ�� �����̶��
				if (Farming.playerImage.getY() >= 328 && Farming.playerImage.getY() <= 473) {
					if (Farming.playerImage.getX() >= 0 && Farming.playerImage.getX() <= 120) {
						//System.out.println("���� ��ġ�� �����Դϴ�");

					}
					// ���� �÷��̾��� ��ġ�� ���̶��
				} else if (Farming.playerImage.getY() >= 330 && Farming.playerImage.getY() <= 475) {
					if (Farming.playerImage.getX() >= 600 && Farming.playerImage.getX() <= 720) {
						//System.out.println("���� ��ġ�� ���Դϴ�");

					}
					// ���� �÷��̾��� ��ġ�� ���̶��
				} else if (Farming.playerImage.getY() >= -20 && Farming.playerImage.getY() <= 235) {
					if (Farming.playerImage.getX() >= 65 && Farming.playerImage.getX() <= 620) {
						//System.out.println("���� ��ġ�� ���Դϴ�");

					}
					// ����, ��, ���� �ƴ� �� ���� ��ġ�� �� ���Ͱ� ��Ÿ����
				} else {
					// ���Ͱ� ��Ÿ����.
					System.out.println("!!! ���Ϳ� ������ !!!");
				}

			} catch (InterruptedException e) {
				running = false;
			}
		}
	}
}
