package Farm;

import java.util.Random;

import javax.swing.ImageIcon;

public class RotCrops extends Thread{

	//���� ���幰�� ����� ������
	
	Random random = new Random();
	
	int numOfField = 0;
	int minTime = 10000;
	int maxTime = 15000;
	int randomShortageTime = 0;//���� �����ϱ� ���� ���� �ð�
	int randomRotTime = 0; //���۹��� ������ ���� �ð�
	boolean running = true;
	
	RotCrops(int numOfField){
		this.numOfField = numOfField;
	}
	
	@Override
	public synchronized void run() {
		while (running) {
			try {
				// ������ �ɰ��� ���̶�� -> �� ���� ������ �ڶ���ϱ� ->���� �����ϸ� �Ǵ��ϱ� �����忡�� ���� 
				//���⿡�� ���۹���� �� ǥ���ϱ� �����ð� ��� 
				
				if (Farming.statusOfField.get(numOfField).equals("seeded field")) {
					randomShortageTime = random.nextInt(maxTime - minTime + 1) + minTime;
					
					Thread.sleep(randomShortageTime);
					
					if(Farming.statusOfField.get(numOfField).equals("seeded field")) {
						
						Farming.emergencyMarkingImages[numOfField].setVisible(true);
						Farming.statusOfField.set(numOfField, "need Water field");
						Farming.amountOfWater[numOfField].setText("���� �� : �ſ����");
					}

					
					//�������� ���� ���� ������ ��� 
				}else if(Farming.statusOfField.get(numOfField).equals("need Water field")) {
					randomRotTime = random.nextInt(maxTime - minTime + 1) + minTime;
					Thread.sleep(randomRotTime);
					
					if(Farming.statusOfField.get(numOfField).equals("need Water field")) {
						Farming.emergencyMarkingImages[numOfField].setVisible(false);
						Farming.fieldImages[numOfField].setIcon(new ImageIcon("C:\\Users\\dayou\\OneDrive\\���� ȭ��\\�����\\java_teamProject\\rottenFieldImage.png"));
						Farming.statusOfField.set(numOfField, "rotten field");
					}
					
				}
				
				Thread.sleep(5000);

			} catch (InterruptedException e) {
				running = false;
			}
		}
	}

}
