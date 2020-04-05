package Farm;

import java.util.Random;

public class GameInformation extends Thread{

	Random random = new Random();
	
	int randomInfomation = 0;
	boolean running = true;
	
	
	@Override
	public synchronized void run() {
		while (running) {
			try {
				
				// 만약 플레이어 위치가 상점이라면
				if (Farming.playerImage.getY() >= 328 && Farming.playerImage.getY() <= 473
						&& Farming.playerImage.getX() >= 0 && Farming.playerImage.getX() <= 120) {
					
					Farming.gameInformationText.setText("스페이스 바를 누르면 상점을 들어갈 수 있습니다");

					// 만약 플레이어의 위치가 집이라면
				} else if (Farming.playerImage.getY() >= 330 && Farming.playerImage.getY() <= 475
						&& Farming.playerImage.getX() >= 600 && Farming.playerImage.getX() <= 720) {
						
					Farming.gameInformationText.setText("스페이스 바를 누르면 잠을 잘 수 있습니다.");
						
					// 만약 플레이어의 위치가 밭이라면
				} else if (Farming.playerImage.getY() >= -20 && Farming.playerImage.getY() <= 235
						&& Farming.playerImage.getX() >= 65 && Farming.playerImage.getX() <= 620) {
						
					Farming.gameInformationText.setText("스페이스 바를 누르면 농작물 상태창을 보거나 식물을 심을 수 있습니다");

					// 상점, 집, 밭이 아닌 그 외일때
				} else {
					randomInfomation = random.nextInt(6);
					
					if(randomInfomation == 0) {
						Farming.gameInformationText.setText("G키를 누르면 미니게임을 할 수 있습니다.");
						
					}else if(randomInfomation == 1) {
						Farming.gameInformationText.setText("I키를 누르면 인벤토리를 볼 수 있습니다.");
						
					}else if(randomInfomation == 2) {
						Farming.gameInformationText.setText("랜덤으로 참새들이 농작물들을 가져가니 조심하세요!");
						
					}else if(randomInfomation == 3) {
						Farming.gameInformationText.setText("밭, 상점, 집 외의 장소에서는 야생동물을 만날 수 있습니다");
						
					}else if(randomInfomation == 4) {
						Farming.gameInformationText.setText("야생동물을 죽일 시 뼛가루, 랜덤버섯, 돈을 얻을 수 있습니다.");
						
					}else if(randomInfomation == 5) {
						Farming.gameInformationText.setText("기본 제공 아이템은 씨앗 각 2~3개씩, 뼈가루 2개, 포션 4개, 랜덤 버섯 2개");
						
					}
					Thread.sleep(1200);
				}
				
				Thread.sleep(500);

			} catch (InterruptedException e) {
				running = false;
			}
		}
	}
}
