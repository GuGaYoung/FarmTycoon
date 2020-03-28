package Farm;

import java.util.Random;

import javax.swing.ImageIcon;

public class RotCrops extends Thread{

	//���� ���幰�� ����� ������
	
	Random random = new Random();
	
	int fieldNum = 0;
	int minTime = 7000;
	int maxTime = 10000;
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
					Farming.statusOfField.set(fieldNum, "need Water field");
					System.out.println(fieldNum + "�� ���� ���� �����մϴ�");
					System.out.println(Farming.statusOfField.get(fieldNum));
					
					//�������� ���� ���� ������ ��� 
				}else if(Farming.statusOfField.get(fieldNum).equals("need Water field")) {
					randomRotTime = random.nextInt(maxTime - minTime + 1) + minTime;
					Thread.sleep(randomRotTime);
					
					Farming.fieldImages[fieldNum].setIcon(new ImageIcon("C:\\Users\\dayou\\OneDrive\\���� ȭ��\\�����\\java_teamProject\\rottenFieldImage.png"));
					Farming.statusOfField.set(fieldNum, "rotten field");
					System.out.println(fieldNum + "�� ���� ������ϴ�");
					System.out.println(Farming.statusOfField.get(fieldNum));
				}
				
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				running = false;
			}
		}
	}

}
