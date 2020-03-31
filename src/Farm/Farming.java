package Farm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;

public class Farming {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Farming window = new Farming();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Farming() {
		initialize();
	}

	Player player = new Player();
	SparrowObstruction sparrowObstruction = new SparrowObstruction();
	
	static JPanel farmingScene = new JPanel() {
		//public void paintComponent(Graphics g) {
		//	Dimension d = getSize();
		//	ImageIcon image = new ImageIcon("./images/backGround.jpg");
		//	g.drawImage(image.getImage(), 0, 0, d.width, d.height, this);
		//}
	};

	static JLabel[] fieldImages = new JLabel[18];
	static JLabel[] emergencyMarkingImages = new JLabel[18];//���� �ʿ��ϴٴ� ���ǥ��
	JButton chooseSeedCanelButton = new JButton();
	JPanel seedPlantingWindow = new JPanel();
	JPanel plantStateWindow = new JPanel();
	
	static JLabel playerImage = new JLabel();
	JLabel playerEnergy = new JLabel();
	JLabel daysText = new JLabel();
	static JLabel houseImage = new JLabel();
	JLabel storeImage = new JLabel();
	JButton[] seedImage = new JButton[4];
	JLabel[] seedExplanationImage = new JLabel[4];

	//�Ĺ� ����â 
	JLabel[] plantsImage = new JLabel[18];
	JLabel[] plantsNametext = new JLabel[18];
	JLabel[] timeLeftText = new JLabel[18];
	static JLabel[] amountOfWater = new JLabel[18];
	JButton waterThePlantsButton = new JButton();
	JButton rapidGrowthButton = new JButton();
	JButton harvestingButton = new JButton();
	JButton statusCheckCanelButton = new JButton();
	
	//�κ��丮
	JLabel inventoryWindow = new JLabel();
	JLabel[] inventoryCompartment = new JLabel[16]; //�κ��丮 ĭ
	JLabel[] numberOfItemsText = new JLabel[16]; //��ǰ�� ����� text
	JLabel[] inventoryDescriptionText = new JLabel[4]; //�κ��丮 ���� (� ĭ����)
	int inventoryHorizontalLength = 5;
	int inventoryVerticalLength = 0;
	int inventoryInterval = 100;
	int inventoryWidth = 90;
	int inventoryHeight = 60;
	int inventoryDescriptionlength = 0;
	
	//����
	static JLabel sparrowImage = new JLabel();
	
	//��
	static ArrayList<String> statusOfField = new ArrayList<>();
	int numOfField = 0;
	int fieldWidth = 80;
	int fieldHeight = 80;
	int fieldHorizontalLength = 90;
	int fieldVerticalLength = 50;
	int fieldInterval = 100;
	
	//��
	int rightWall = 725;
	int leftWall = -10;
	int bottomWall = 488;
	int upperWall = -7;
	
	//���� ����
	int chooseSeedBoxLength = 10;//���� ���� �ڽ��� ���� ��ġ 
	int[] daysRemaining = new int[18]; //���۹��� �ڶ����� ���� �ϼ� 
	
	//��¥
	int day = 0;
	int finalday = 7; // ��������
	
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("farmTycoon");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		farmingScene.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(farmingScene);
		farmingScene.setLayout(null);

		playerImage.setHorizontalAlignment(SwingConstants.CENTER);
		playerImage.setIcon(new ImageIcon("./images/rabbit.png"));
		playerImage.setBounds(365, 338, 70, 80);
		farmingScene.add(playerImage);
		
		playerEnergy.setText("���� ������ : 100");
		playerEnergy.setFont(new Font("����", Font.BOLD, 15));
		playerEnergy.setBounds(650, -35, 150, 150);
		farmingScene.add(playerEnergy);
		
		daysText.setText("0����");
		daysText.setFont(new Font("����", Font.BOLD, 15));
		daysText.setBounds(650, -35, 100, 100);
		farmingScene.add(daysText);
		
		houseImage.setHorizontalAlignment(SwingConstants.CENTER);
		houseImage.setIcon(new ImageIcon("./images/houseImage.png"));
		houseImage.setBounds(620, 400, 150, 150);
		farmingScene.add(houseImage);

		storeImage.setHorizontalAlignment(SwingConstants.CENTER);
		storeImage.setIcon(new ImageIcon("./images/storeImage.png"));
		storeImage.setBounds(20, 400, 150, 150);
		farmingScene.add(storeImage);
		
		sparrowImage.setHorizontalAlignment(SwingConstants.CENTER);
		sparrowImage.setIcon(new ImageIcon("./images/sparrow.png"));
		sparrowImage.setBounds(-100, -100, 100, 50);
		farmingScene.add(sparrowImage);
		
		//�κ��丮 â
		inventoryWindow.setIcon(new ImageIcon("./images/inventoryBackGround.png"));
		inventoryWindow.setBounds(180, 80, 400, 350);
		inventoryWindow.setLayout(null);
		inventoryWindow.setVisible(false);
		farmingScene.add(inventoryWindow);
		
		for (int i = 0; i < inventoryCompartment.length; i++) {
			inventoryWindow.add(numberOfItemsText[i] = new JLabel());
			inventoryWindow.add(inventoryCompartment[i] = new JLabel());
		
			if (i < 4) {
				inventoryVerticalLength = 30;
				inventoryCompartment[i].setBounds(inventoryHorizontalLength, inventoryVerticalLength, inventoryWidth , inventoryHeight);
				numberOfItemsText[i].setBounds(inventoryHorizontalLength + 40, inventoryVerticalLength + 20, 50 , 50);

			} else if ((i >= 4) && (i < 8)) {
				inventoryVerticalLength = 120;
				inventoryCompartment[i].setBounds(inventoryHorizontalLength, inventoryVerticalLength, inventoryWidth, inventoryHeight);
				numberOfItemsText[i].setBounds(inventoryHorizontalLength + 40, inventoryVerticalLength + 20, 50 , 50);
				
			} else if ((i >= 8) && (i < 12)) {
				inventoryVerticalLength = 210;
				inventoryCompartment[i].setBounds(inventoryHorizontalLength, inventoryVerticalLength, inventoryWidth, inventoryHeight);
				numberOfItemsText[i].setBounds(inventoryHorizontalLength + 40, inventoryVerticalLength + 20, 50 , 50);
				
			} else if ((i >= 12) && (i < 16)) {
				inventoryVerticalLength = 295;
				inventoryCompartment[i].setBounds(inventoryHorizontalLength, inventoryVerticalLength, inventoryWidth, inventoryHeight);
				numberOfItemsText[i].setBounds(inventoryHorizontalLength + 40, inventoryVerticalLength + 20, 50 , 50);
			}
			inventoryHorizontalLength = inventoryInterval + inventoryHorizontalLength;
			
			if ((i + 1) % 4 == 0) {
				inventoryHorizontalLength = 5;
			}

			inventoryCompartment[i].setHorizontalAlignment(SwingConstants.CENTER);
			//inventoryCompartment[i].setIcon(new ImageIcon("./images/storeImage.png"));
			
			numberOfItemsText[i].setHorizontalAlignment(SwingConstants.CENTER);
			numberOfItemsText[i].setText("X 0");
		}
		inventoryCompartment[0].setIcon(new ImageIcon("./images/PumkinFieldImage.png"));
		inventoryCompartment[1].setIcon(new ImageIcon("./images/OnionFieldImage.png"));
		inventoryCompartment[2].setIcon(new ImageIcon("./images/CabbageFieldImage.png"));
		inventoryCompartment[3].setIcon(new ImageIcon("./images/CarrotFieldImage.png"));
		inventoryCompartment[4].setIcon(new ImageIcon("./images/PumkinFieldImage.png"));
		inventoryCompartment[5].setIcon(new ImageIcon("./images/OnionFieldImage.png"));
		inventoryCompartment[6].setIcon(new ImageIcon("./images/CabbageFieldImage.png"));
		inventoryCompartment[7].setIcon(new ImageIcon("./images/CarrotFieldImage.png"));
		inventoryCompartment[8].setIcon(new ImageIcon("./images/PumkinFieldImage.png"));
		inventoryCompartment[9].setIcon(new ImageIcon("./images/OnionFieldImage.png"));
		inventoryCompartment[10].setIcon(new ImageIcon("./images/CabbageFieldImage.png"));
		inventoryCompartment[11].setIcon(new ImageIcon("./images/CarrotFieldImage.png"));
		inventoryCompartment[12].setIcon(new ImageIcon("./images/PumkinFieldImage.png"));
		inventoryCompartment[13].setIcon(new ImageIcon("./images/OnionFieldImage.png"));
		inventoryCompartment[14].setIcon(new ImageIcon("./images/CabbageFieldImage.png"));
		inventoryCompartment[15].setIcon(new ImageIcon("./images/CarrotFieldImage.png"));
		
		for (int i = 0; i < inventoryDescriptionText.length; i++) {
			inventoryWindow.add(inventoryDescriptionText[i] = new JLabel());
			
			inventoryDescriptionText[i].setBounds(10, inventoryDescriptionlength, 50, 50);
			inventoryDescriptionlength = inventoryDescriptionlength + 85;
		}
		inventoryDescriptionText[0].setText("����");
		inventoryDescriptionText[1].setText("���۹�");
		inventoryDescriptionText[2].setText("����");
		inventoryDescriptionText[3].setText("�� ��");
		
		//���� �ɱ� â
		seedPlantingWindow.setBackground(Color.WHITE);
		seedPlantingWindow.setBounds(150, 100, 500, 300);
		seedPlantingWindow.setLayout(null);
		seedPlantingWindow.setVisible(false);
		farmingScene.add(seedPlantingWindow);
		
		//���� �ɱ� â �ȿ��ִ� ������ �̹����� ������ ������ �ʱ�ȭ
		for (int i = 0; i < seedImage.length; i++) {
			
			seedPlantingWindow.add(seedImage[i] = new JButton());
			seedPlantingWindow.add(seedExplanationImage[i] = new JLabel());
			
			seedImage[i].setBounds(chooseSeedBoxLength, 10, 105, 100);
			seedImage[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			seedExplanationImage[i].setBounds(chooseSeedBoxLength, 110, 105, 160);
			seedExplanationImage[i].setHorizontalAlignment(SwingConstants.CENTER);
			seedExplanationImage[i].setIcon(new ImageIcon("./images/storeImage.png"));
			
			chooseSeedBoxLength = chooseSeedBoxLength + 124;
			
			seedImage[i].setFocusPainted(false);
			seedImage[i].setContentAreaFilled(false);
		}
		
		seedImage[0].setIcon(new ImageIcon("./images/PumkinFieldImage.png"));
		seedImage[1].setIcon(new ImageIcon("./images/OnionFieldImage.png"));
		seedImage[2].setIcon(new ImageIcon("./images/CabbageFieldImage.png"));
		seedImage[3].setIcon(new ImageIcon("./images/CarrotFieldImage.png"));
		
		seedImage[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (player.energy <= 5) {
					JOptionPane.showMessageDialog(null, "�������� ���ڶ��ϴ�", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				} if(player.numOfPumpkinSeed == 0){
					JOptionPane.showMessageDialog(null, "ȣ�ھ��� ���ڶ��ϴ�", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				}else {
				
					plantsImage[numOfField].setIcon(new ImageIcon("./images/PumkinFieldImage.png"));
					plantsNametext[numOfField].setText("�̸� : ȣ��");
					timeLeftText[numOfField].setText("���� �� �� : 4��");

					player.numOfPumpkinSeed--;
					daysRemaining[numOfField] = 4;
					player.energy = player.energy - 5;
					playerEnergy.setText("���� ������ : " + player.energy);
				}
			}
		});
		
		seedImage[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (player.energy <= 5) {
					JOptionPane.showMessageDialog(null, "�������� ���ڶ��ϴ�", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				} if(player.numOfOnionSeed == 0){
					JOptionPane.showMessageDialog(null, "���ľ��� ���ڶ��ϴ�", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				} else {
					plantsImage[numOfField].setIcon(new ImageIcon("./images/OnionFieldImage.png"));
					plantsNametext[numOfField].setText("�̸� : ����");
					timeLeftText[numOfField].setText("���� �� �� : 2��");

					player.numOfOnionSeed--;
					daysRemaining[numOfField] = 2;
					player.energy = player.energy - 5;
					playerEnergy.setText("���� ������ : " + player.energy);
				}
			}
		});
		
		seedImage[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (player.energy <= 5) {
					JOptionPane.showMessageDialog(null, "�������� ���ڶ��ϴ�", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				}if(player.numOfCabbageSeed == 0){
					JOptionPane.showMessageDialog(null, "����߾��� ���ڶ��ϴ�", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				} else {
					plantsImage[numOfField].setIcon(new ImageIcon("./images/CabbageFieldImage.png"));
					plantsNametext[numOfField].setText("�̸� : �����");
					timeLeftText[numOfField].setText("���� �� �� : 3��");

					player.numOfCabbageSeed--;
					daysRemaining[numOfField] = 3;
					player.energy = player.energy - 5;
					playerEnergy.setText("���� ������ : " + player.energy);
				}
			}
		});
		
		seedImage[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (player.energy <= 5) {
					JOptionPane.showMessageDialog(null, "�������� ���ڶ��ϴ�", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				}if(player.numOfCarrotSeed == 0){
					JOptionPane.showMessageDialog(null, "��پ��� ���ڶ��ϴ�", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				}  else {
					plantsImage[numOfField].setIcon(new ImageIcon("./images/CarrotFieldImage.png"));
					plantsNametext[numOfField].setText("�̸� : ���");
					timeLeftText[numOfField].setText("���� �� �� : 2��");

					player.numOfCarrotSeed--;
					daysRemaining[numOfField] = 2;
					player.energy = player.energy - 5;
					playerEnergy.setText("���� ������ : " + player.energy);
				}
			}
		});
		
		
		for (int i = 0; i < seedImage.length; i++) {
			seedImage[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					for (int i = 0; i < seedImage.length; i++) {
						if (e.getSource() == seedImage[i]) {
							
							if (statusOfField.get(numOfField).equals("empty Field")) {
								fieldImages[numOfField].setIcon(new ImageIcon("./images/seedFieldImage.png"));
								statusOfField.set(numOfField, "seeded field");
							}
						}
					}
					
					for (int j = 0; j < fieldImages.length; j++) {
						fieldImages[j].setEnabled(true);
					}
					playerImage.setVisible(true);
					seedPlantingWindow.setVisible(false);
				}
			});
		}
		
		chooseSeedCanelButton.setText("����ϱ�");
		chooseSeedCanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				playerImage.setVisible(true);
				seedPlantingWindow.setVisible(false);	
				for (int i = 0; i < fieldImages.length; i++) {
					fieldImages[i].setEnabled(true);
				}
			}
		});
		chooseSeedCanelButton.setBounds(200, 270, 120, 20);
		seedPlantingWindow.add(chooseSeedCanelButton);
		
		
		//���� ���� â
		plantStateWindow.setBackground(Color.WHITE);
		plantStateWindow.setBounds(150, 100, 500, 300);
		plantStateWindow.setLayout(null);
		plantStateWindow.setVisible(false);
		farmingScene.add(plantStateWindow);

		for (int i = 0; i < fieldImages.length; i++) {
			
			plantStateWindow.add(plantsImage[i] = new JLabel());
			plantStateWindow.add(plantsNametext[i] = new JLabel());
			plantStateWindow.add(timeLeftText[i] = new JLabel());
			plantStateWindow.add(amountOfWater[i] = new JLabel());
			
			plantsImage[i].setBounds(30,60, 80, 80);
			
			plantsNametext[i].setText("�̸� : ");
			plantsNametext[i].setFont(new Font("����", Font.BOLD, 15));
			plantsNametext[i].setBounds(150, 0, 150, 150);
			
			timeLeftText[i].setText("���� �� �� : ");
			timeLeftText[i].setFont(new Font("����", Font.BOLD, 15));
			timeLeftText[i].setBounds(150, 30, 150, 150);

			amountOfWater[i].setText("���� �� : ����");
			amountOfWater[i].setFont(new Font("����", Font.BOLD, 15));
			amountOfWater[i].setBounds(150, 60, 150, 150);
			
			plantsImage[i].setVisible(false);
			plantsNametext[i].setVisible(false);
			timeLeftText[i].setVisible(false);
			amountOfWater[i].setVisible(false);
		}
		
		waterThePlantsButton.setText("���ֱ�");
		waterThePlantsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(player.energy <= 3) {
					JOptionPane.showMessageDialog(null, "�������� ���ڶ��ϴ�", "!!!!", JOptionPane.INFORMATION_MESSAGE);
				} else {

					// ���Ѹ� �ѷ��� ���¶��
					if (statusOfField.get(numOfField).equals("seeded field")) {
						statusOfField.set(numOfField, "Proper field");
						amountOfWater[numOfField].setText("���� �� : ����");
					}
					// ���������¶��
					if (statusOfField.get(numOfField).equals("need Water field")) {
						emergencyMarkingImages[numOfField].setVisible(false);
						statusOfField.set(numOfField, "seeded field");
						amountOfWater[numOfField].setText("���� �� : ����");
					}

					player.energy = player.energy - 3;
					playerEnergy.setText("���� ������ : " + player.energy);
				}
				
				plantStateWindow.setVisible(false);	
				playerImage.setVisible(true);
				
				for (int i = 0; i < fieldImages.length; i++) {
					fieldImages[i].setEnabled(true);
					
					plantsImage[numOfField].setVisible(false);
					plantsNametext[numOfField].setVisible(false);
					timeLeftText[numOfField].setVisible(false);
					amountOfWater[numOfField].setVisible(false);
				}
			}
		});
		waterThePlantsButton.setFont(new Font("����", Font.BOLD, 15));
		waterThePlantsButton.setBounds(15, 180, 120, 80);
		plantStateWindow.add(waterThePlantsButton);
		
		rapidGrowthButton.setText("�޼Ӽ���");
		rapidGrowthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(player.numOfBone >= 1) {
					player.numOfBone--;
					
					if(statusOfField.get(numOfField).equals("seeded field")
							|| statusOfField.get(numOfField).equals("need Water field")
							|| statusOfField.get(numOfField).equals("Proper field")) {
						//�����Ѵ�
						statusOfField.set(numOfField, "fullGrown field");
						
						if (plantsNametext[numOfField].getText().equals("�̸� : ȣ��")) {
							fieldImages[numOfField].setIcon(new ImageIcon("./images/PumKinFieldImage.png"));

						} else if (plantsNametext[numOfField].getText().equals("�̸� : ����")) {
							fieldImages[numOfField].setIcon(new ImageIcon("./images/OnionFieldImage.png"));

						} else if (plantsNametext[numOfField].getText().equals("�̸� : ���")) {
							fieldImages[numOfField].setIcon(new ImageIcon("./images/CarrotFieldImage.png"));

						} else if (plantsNametext[numOfField].getText().equals("�̸� : �����")) {
							fieldImages[numOfField].setIcon(new ImageIcon("./images/CabbageFieldImage.png"));

						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "���� ������ ���ڶ��ϴ�", "!!!!", JOptionPane.INFORMATION_MESSAGE);
				}
				plantStateWindow.setVisible(false);	
				playerImage.setVisible(true);
				
				for (int i = 0; i < fieldImages.length; i++) {
					fieldImages[i].setEnabled(true);
					
					plantsImage[numOfField].setVisible(false);
					plantsNametext[numOfField].setVisible(false);
					timeLeftText[numOfField].setVisible(false);
					amountOfWater[numOfField].setVisible(false);
				}
			}
		});
		rapidGrowthButton.setFont(new Font("����", Font.BOLD, 15));
		rapidGrowthButton.setBounds(190, 180, 120, 80);
		plantStateWindow.add(rapidGrowthButton);
		
		harvestingButton.setText("��Ȯ�ϱ�");
		harvestingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(player.energy <= 7) {
					JOptionPane.showMessageDialog(null, "�������� ���ڶ��ϴ�", "!!!!", JOptionPane.INFORMATION_MESSAGE);
				}else {
					
					//�� �ڶ� ���۹��� ��Ȯ�Ѵٸ�
					if(statusOfField.get(numOfField).equals("fullGrown field")) {
						
						if(plantsNametext[numOfField].getText().equals("�̸� : ȣ��")) {
							player.numOfPumpkin++;
							
						}else if(plantsNametext[numOfField].getText().equals("�̸� : ����")) {
							player.numOfOnion++;
							
						}else if(plantsNametext[numOfField].getText().equals("�̸� : �����")) {
							player.numOfCabbage++;
							
						}else if(plantsNametext[numOfField].getText().equals("�̸� : ���")) {
							player.numOfCarrot++;
						}
					}
					//�� �ڶ��� ���� ���۹��� ��Ȯ�Ѵٸ� ���۹��� ���� �� ������ ������ �ǵ��ư���
					statusOfField.set(numOfField, "empty Field");
					fieldImages[numOfField].setIcon(new ImageIcon("./images/basicsFieldImage.png"));

					player.energy  = player.energy - 7;
					playerEnergy.setText("���� ������ : " + player.energy);
				}
				
				plantStateWindow.setVisible(false);	
				playerImage.setVisible(true);
				for (int i = 0; i < fieldImages.length; i++) {
					fieldImages[i].setEnabled(true);
					
					plantsImage[numOfField].setVisible(false);
					plantsNametext[numOfField].setVisible(false);
					timeLeftText[numOfField].setVisible(false);
					amountOfWater[numOfField].setVisible(false);
				}
			}
		});
		harvestingButton.setFont(new Font("����", Font.BOLD, 15));
		harvestingButton.setBounds(370, 180, 120, 80);
		plantStateWindow.add(harvestingButton);
		
		statusCheckCanelButton.setText("����ϱ�");
		statusCheckCanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				plantStateWindow.setVisible(false);	
				playerImage.setVisible(true);
				for (int i = 0; i < fieldImages.length; i++) {
					fieldImages[i].setEnabled(true);
					
					plantsImage[numOfField].setVisible(false);
					plantsNametext[numOfField].setVisible(false);
					timeLeftText[numOfField].setVisible(false);
					amountOfWater[numOfField].setVisible(false);
				}
			}
		});
		statusCheckCanelButton.setBounds(190, 270, 120, 20);
		plantStateWindow.add(statusCheckCanelButton);
		
		//��
		for (int i = 0; i < fieldImages.length; i++) {
			
			farmingScene.add(emergencyMarkingImages[i] = new JLabel());
			farmingScene.add(fieldImages[i] = new JLabel());
			
			fieldImages[i].setIcon(new ImageIcon("./images/basicsFieldImage.png"));

			if (i < 6) {
				fieldVerticalLength = 50;
				fieldImages[i].setBounds(fieldHorizontalLength, fieldVerticalLength, fieldWidth, fieldHeight);

			} else if ((i >= 6) && (i < 12)) {
				fieldVerticalLength = 140;
				fieldImages[i].setBounds(fieldHorizontalLength, fieldVerticalLength, fieldWidth, fieldHeight);

			} else if ((i >= 12) && (i < 18)) {
				fieldVerticalLength = 230;
				fieldImages[i].setBounds(fieldHorizontalLength, fieldVerticalLength, fieldWidth, fieldHeight);

			}

			fieldHorizontalLength = fieldInterval + fieldHorizontalLength;

			if ((i + 1) % 6 == 0) {
				fieldHorizontalLength = 90;
			}
			
			emergencyMarkingImages[i].setBounds(fieldImages[i].getX() + 25, fieldImages[i].getY() + 25, 20, 20);
			emergencyMarkingImages[i].setIcon(new ImageIcon("./images/EmergencyMarking.png"));
			emergencyMarkingImages[i].setVisible(false);
			
			statusOfField.add("empty Field");
			RotCrops rotCrops = new RotCrops(i);
			rotCrops.start();
		
		}
		

		/*
		for (int i = 0; i < fieldImages.length; i++) {
			fieldImages[i].addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseClicked(MouseEvent e) {
		            	for (int i = 0; i < fieldImages.length; i++) {
							if (e.getSource() == fieldImages[i]) {
								numOfField = i;
								
								PlayerOutoMove playerOutoMove = new PlayerOutoMove(numOfField, "fields");
								playerOutoMove.start();
								frame.requestFocus();
							}
						}
		            }          
		        });
		}
		
		houseImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	PlayerOutoMove playerOutoMove = new PlayerOutoMove(0, "house");
				playerOutoMove.start();
            }          
        });
*/
		sparrowObstruction.start();
		frame.addKeyListener(new key());
		frame.setFocusable(true);
	}

	class key implements KeyListener {

		// ������ � Ű�� ���ڰ� �������� ����
		@Override
		public void keyTyped(KeyEvent e) {
		}

		// Ű������ Ű�� �������� ����
		@Override
		public void keyPressed(KeyEvent e) {

			int keyCode = e.getKeyCode();

			switch (keyCode) {

			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				if (playerImage.getY() >= upperWall) {
					playerImage.setLocation(playerImage.getX(), playerImage.getY() - player.speed);
				}
				break;

			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				if (playerImage.getY() <= bottomWall) {
					playerImage.setLocation(playerImage.getX(), playerImage.getY() + player.speed);
				}
				break;

			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				if (playerImage.getX() <= rightWall) {
					playerImage.setLocation(playerImage.getX() + player.speed, playerImage.getY());
				}
				break;

			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				if (playerImage.getX() >= leftWall) {
					playerImage.setLocation(playerImage.getX() - player.speed, playerImage.getY());
				}
				break;
			
				
			case KeyEvent.VK_SPACE:
				
					// ���� ù��° ��
					if (playerImage.getY() <= 54) {

						if (playerImage.getX() >= 70 && playerImage.getX() <= 120) {
							numOfField = 0;
							information();

						} else if (playerImage.getX() >= 170 && playerImage.getX() <= 220) {
							numOfField = 1;
							information();

						} else if (playerImage.getX() >= 270 && playerImage.getX() <= 320) {
							numOfField = 2;
							information();

						} else if (playerImage.getX() >= 370 && playerImage.getX() <= 420) {
							numOfField = 3;
							information();

						} else if (playerImage.getX() >= 470 && playerImage.getX() <= 520) {
							numOfField = 4;
							information();

						} else if (playerImage.getX() >= 570 && playerImage.getX() <= 620) {
							numOfField = 5;
							information();

						}
						// ���� �ι�° ��
					} else if (playerImage.getY() >= 68 && playerImage.getY() <= 143) {

						// ù��° ĭ
						if (playerImage.getX() >= 70 && playerImage.getX() <= 120) {
							numOfField = 6;
							information();

						} else if (playerImage.getX() >= 170 && playerImage.getX() <= 220) {
							numOfField = 7;
							information();

						} else if (playerImage.getX() >= 270 && playerImage.getX() <= 320) {
							numOfField = 8;
							information();

						} else if (playerImage.getX() >= 370 && playerImage.getX() <= 420) {
							numOfField = 9;
							information();

						} else if (playerImage.getX() >= 470 && playerImage.getX() <= 520) {
							numOfField = 10;
							information();

						} else if (playerImage.getX() >= 570 && playerImage.getX() <= 620) {
							numOfField = 11;
							information();

						}

					} else if (playerImage.getY() >= 160 && playerImage.getY() <= 235) {

						if (playerImage.getX() >= 70 && playerImage.getX() <= 120) {
							numOfField = 12;
							information();

						} else if (playerImage.getX() >= 170 && playerImage.getX() <= 220) {
							numOfField = 13;
							information();

						} else if (playerImage.getX() >= 270 && playerImage.getX() <= 320) {
							numOfField = 14;
							information();

						} else if (playerImage.getX() >= 370 && playerImage.getX() <= 420) {
							numOfField = 15;
							information();

						} else if (playerImage.getX() >= 470 && playerImage.getX() <= 520) {
							numOfField = 16;
							information();

						} else if (playerImage.getX() >= 570 && playerImage.getX() <= 620) {
							numOfField = 17;
							information();
							
						}

					}
					
					//���� �տ��� �����̽��ٸ� ������ ��
					for (int i = 0; i < fieldImages.length; i++) {
					if(playerImage.getX() == fieldImages[i].getX() && playerImage.getY() == fieldImages[i].getY() + 10) {
						information();
					}
				}
				
				
					//���ȿ��� �����̽��ٸ� ������ ��
				if (playerImage.getY() >= 330 && playerImage.getY() <= 475) {
					if (playerImage.getX() >= 600 && playerImage.getX() <= 720) {

						//�Ϸ簡 ��������
						JOptionPane.showMessageDialog(null, "�Ϸ簡 �������ϴ�", " ", JOptionPane.INFORMATION_MESSAGE);
						player.energy = 100;
						playerEnergy.setText("���� ������ : " + player.energy);
						day++;
						daysText.setText(day + "����");

						//������ ���̶�� ������ ����� �����ش�
						if (day == finalday) {

							if(player.money > 70000) {
								System.out.println("���� �¸�");
								
							}else {
								System.out.println("���� �й�");
							}
							
						} else {
							for (int i = 0; i < fieldImages.length; i++) {

								// ���� ���°� ������ ���¶��
								if (statusOfField.get(i).equals("Proper field")) {
									daysRemaining[i]--;
									timeLeftText[i].setText("���� �� �� : " + daysRemaining[i]);

									// ���� �ϼ��� ���� �̹��� ��ü
									if (daysRemaining[i] == 0) {
										statusOfField.set(i, "fullGrown field");

										if (plantsNametext[i].getText().equals("�̸� : ȣ��")) {
											fieldImages[i].setIcon(new ImageIcon("./images/PumKinFieldImage.png"));

										} else if (plantsNametext[i].getText().equals("�̸� : ����")) {
											fieldImages[i].setIcon(new ImageIcon("./images/OnionFieldImage.png"));

										} else if (plantsNametext[i].getText().equals("�̸� : ���")) {
											fieldImages[i].setIcon(new ImageIcon("./images/CarrotFieldImage.png"));

										} else if (plantsNametext[i].getText().equals("�̸� : �����")) {
											fieldImages[i].setIcon(new ImageIcon("./images/CabbageFieldImage.png"));

										}
									} else if (daysRemaining[i] == 1) {
										fieldImages[i].setIcon(new ImageIcon("./images/Grown1FieldImage.png"));

									} else if (daysRemaining[i] == 2) {
										fieldImages[i].setIcon(new ImageIcon("./images/Grown2FieldImage.png"));

									} else if (daysRemaining[i] == 3) {
										fieldImages[i].setIcon(new ImageIcon("./images/Grown3FieldImage.png"));

									}

									// �Ϸ簡 ���� �� �ϼ��� ���� �Ĺ��� ���� �߰��� ����ϵ��� ���۹��� ���¸� �ٲ��־���
									if (daysRemaining[i] >= 1) {

										statusOfField.set(i, "seeded field");
										amountOfWater[i].setText("���� �� : ����");
									}

									// ���� ���� ������ ���·� �Ϸ簡 ������ �Ǹ�
								} else if (statusOfField.get(i).equals("seeded field")
										|| statusOfField.get(i).equals("need Water field")) {

									// ���� ���� �ȴ�
									statusOfField.set(i, "rotten field");
									fieldImages[i].setIcon(new ImageIcon("./images/rottenFieldImage.png"));
									emergencyMarkingImages[i].setVisible(false);
								}
							}
						}
					}
				}
				
				//���� �ȿ��� �����̽� �ٸ� ������ ��
				if (playerImage.getY() >= 328 && playerImage.getY() <= 473) {
					if (playerImage.getX() >= 0 && playerImage.getX() <= 120) {
						System.out.println("�������� ���ϴ�");
						
					}
				}
				break;
				
			case KeyEvent.VK_E:
				
				if (inventoryWindow.isVisible() == true) {
					inventoryWindow.setVisible(false);
					playerImage.setVisible(true);

				} else {
					numberOfItemsText[0].setText("X " + player.numOfPumpkinSeed);
					numberOfItemsText[1].setText("X " + player.numOfOnionSeed);
					numberOfItemsText[2].setText("X " + player.numOfCabbageSeed);
					numberOfItemsText[3].setText("X " + player.numOfCarrotSeed);
					numberOfItemsText[4].setText("X " + player.numOfPumpkin);
					numberOfItemsText[5].setText("X " + player.numOfOnion);
					numberOfItemsText[6].setText("X " + player.numOfCabbage);
					numberOfItemsText[7].setText("X " + player.numOfCarrot);

					inventoryWindow.setVisible(true);
					playerImage.setVisible(false);
				}

				break;
			}
		}

		// Ű������ Ű�� ���ȴ� �������� ����
		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
	
	public void information(){

		//����ִ� ���̶�� ���ѽɱ�â�� �����ش�
		if (statusOfField.get(numOfField).equals("empty Field")) {
			seedPlantingWindow.setVisible(true);
			
			for (int j = 0; j < fieldImages.length; j++) {
				fieldImages[j].setEnabled(false);
			}
			playerImage.setVisible(false);
			
			//������ �ɰ��ִ� ���̶�� ���۹����� â�� �����ش�
		}else {
			plantStateWindow.setVisible(true);
			
			plantsImage[numOfField].setVisible(true);
			plantsNametext[numOfField].setVisible(true);
			timeLeftText[numOfField].setVisible(true);
			amountOfWater[numOfField].setVisible(true);
			
			//���� ���� ���̶�� 
			if(statusOfField.get(numOfField).equals("rotten field")){
				//���ֱ��ư��, �޼Ӽ����ư�� ������ ���ϰ� �Ѵ�
				waterThePlantsButton.setEnabled(false);
				rapidGrowthButton.setEnabled(false);
			}else {
				waterThePlantsButton.setEnabled(true);
				rapidGrowthButton.setEnabled(true);
			}
			
			for (int j = 0; j < fieldImages.length; j++) {
				fieldImages[j].setEnabled(false);
			}
			playerImage.setVisible(false);
		}
	}
}
