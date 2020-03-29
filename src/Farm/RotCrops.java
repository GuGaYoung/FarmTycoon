package Farm;

import java.util.Random;

import javax.swing.ImageIcon;

public class RotCrops extends Thread{

	//���� ���幰�� ����� ������
	
	Random random = new Random();
	
	int fieldNum = 0;
	int minTime = 10000;
	int maxTime = 15000;
	int randomShortageTime = 0;//���� �����ϱ� ���� ���� �ð�
	int randomRotTime = 0; //���۹��� ������ ���� �ð�
	boolean running = true;
	
	RotCrops(int fieldNum){
		this.fieldNum = fieldNum;
	}
	
	@Override
	public synchronized void run() {
		while (running) {
			try {
				// ������ �ɰ��� ���̶�� -> �� ���� ������ �ڶ���ϱ� ->���� �����ϸ� �Ǵ��ϱ� �����忡�� ���� 
				//���⿡�� ���۹���� �� ǥ���ϱ� �����ð� ��� 
				
				if (Farming.statusOfField.get(fieldNum).equals("seeded field")) {
					randomShortageTime = random.nextInt(maxTime - minTime + 1) + minTime;
					Thread.sleep(randomShortageTime);
					
					//����ǥ ! ǥ�� ���� ���� ���µ� �ٲٱ�
					Farming.emergencyMarkingImages[fieldNum].setVisible(true);
					System.out.println(Farming.emergencyMarkingImages[fieldNum].getX());
					System.out.println(Farming.emergencyMarkingImages[fieldNum].getY());
					Farming.statusOfField.set(fieldNum, "need Water field");
					
					//�������� ���� ���� ������ ��� 
				}else if(Farming.statusOfField.get(fieldNum).equals("need Water field")) {
					randomRotTime = random.nextInt(maxTime - minTime + 1) + minTime;
					Thread.sleep(randomRotTime);
					
					Farming.emergencyMarkingImages[fieldNum].setVisible(false);
					Farming.fieldImages[fieldNum].setIcon(new ImageIcon("C:\\Users\\dayou\\OneDrive\\���� ȭ��\\�����\\java_teamProject\\rottenFieldImage.png"));
					Farming.statusOfField.set(fieldNum, "rotten field");
				}
				
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				running = false;
			}
		}
	}

}
