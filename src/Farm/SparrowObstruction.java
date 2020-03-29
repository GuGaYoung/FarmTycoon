package Farm;

import java.util.Random;

import javax.swing.ImageIcon;

public class SparrowObstruction extends Thread{
	//������ �����ϴ� Ŭ���� - ���� �������� (���� ������ �ɾ���� ���� ������ ���� Ȯ���� ��������)
	
	Random random = new Random();
	
	int randomField = 0;
	boolean running = true;
	
	@Override
	public synchronized void run() {
		while (running) {
			try {
				
				Thread.sleep(10000);
				
				randomField = random.nextInt(18);
					
				// �������� ������ ���� �� ���� �ƴҶ� ������ ��Ÿ����
				if (!Farming.statusOfField.get(randomField).equals("empty Field")) {
					Farming.sparrowImage.setLocation(Farming.fieldImages[randomField].getX(),Farming.fieldImages[randomField].getY());
					Farming.statusOfField.set(randomField, "empty Field");
					Farming.fieldImages[randomField].setIcon(new ImageIcon("C:\\Users\\dayou\\OneDrive\\���� ȭ��\\�����\\java_teamProject\\basicsFieldImage.png"));
					
					//������ ��Ÿ�� 1�� �� ������ �ٽ� ���󰣴�
					Thread.sleep(1000);
					Farming.sparrowImage.setLocation(-100,-100);
				}
				
			} catch (InterruptedException e) {
				running = false;
			}
		}
	}
}
