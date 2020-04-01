package Farm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class DungeonWildSnake extends JPanel {

	public static Object textArea;
	private JPanel contentPane;
	private JButton dungeonEx;
	private JButton useItemButton;
	private JButton runAwayButton;
	private JButton attackButton;

	static int randomMushroom;	
	static int randomItem;	//�������� ���� �� �ִ� �������� ��ȣ
	static int numOfRun;	//����ĥ �� �ִ� ���� ��ȣ

	static Random randomMushroomSelect = new Random();
	static Random randomItemSelect = new Random();
	static Random randomRun = new Random();

	// ���Һ����� �����ϴ� �����Լ�
	public static void randomMushroomSelect() {
		randomMushroom = randomMushroomSelect.nextInt(2);
	}

	// ���� ������ �����ϴ� �����Լ�
	public static void randomItemSelect() {
		randomItem = randomItemSelect.nextInt(5);
	}

	// ������ �� �ִ� ��ȣ�� �����ִ� �����Լ�
	public static void randomRun() {
		numOfRun = randomRun.nextInt(5);
	}

	public DungeonWildSnake() {
		//System.out.println("������ " + Main.day + "���Դϴ�. �÷��̾� ü���� " + Main.energy + "�Դϴ�.");
		//System.out.println("�� ü��"+wildSnakeHp);
		setLayout(null);
		setBounds(0, 0, 800, 600);
		setBackground(Color.black);
		
		WildAnimal wildSnake = new WildAnimal("�߻���", 100, 7, true);
		
		JLabel wildSnakeImage = new JLabel();
		wildSnakeImage.setHorizontalAlignment(SwingConstants.CENTER);
		wildSnakeImage.setIcon(new ImageIcon("./images/wildSnake.png"));
		wildSnakeImage.setBounds(292, -30, 224, 170);
		add(wildSnakeImage);

		JScrollPane scrollBar = new JScrollPane();
		scrollBar.setBounds(68, 201, 662, 186);
		scrollBar.getVerticalScrollBar().setValue(scrollBar.getVerticalScrollBar().getMaximum());
		add(scrollBar);
		
		JTextArea textArea = new JTextArea();
		scrollBar.setViewportView(textArea);
		textArea.setEnabled(true);
		textArea.setFont((new Font("����ü", Font.BOLD, 15)));
		
		JPanel itemPanel = new JPanel();
		itemPanel.setBounds(273, 397, 252, 58);
		itemPanel.setLayout(null);
		itemPanel.setVisible(false);
		add(itemPanel);
		
		// �߻��� �ڵ� ���� ������
		new Thread(new Runnable() {
			public void run() {
				try {
					//boolean isStop = true;
					do {
						Thread.sleep(1000);
						wildSnakeAttack();
						//System.out.println("������");
					} while (Player.hp > 0 && wildSnake.hp > 0);
				} catch (Exception ie) {
					ie.printStackTrace();
				}
			}
		private void wildSnakeAttack() {
			{
				if (wildSnake.hp > 0) {
					Player.hp = Player.hp - wildSnake.power;
					textArea.append("�߻����� �����Ͽ����ϴ�! (�÷��̾��� ���� ��: " + Player.hp + ")\n");
					scrollBar.getVerticalScrollBar().setValue(scrollBar.getVerticalScrollBar().getMaximum());

					// ���ӿ� ���� �������� �ٲ��, �÷��̾��� ü���� 50���� �����Ѵ�.
					if (Player.hp <= 0) {
						Farming.day ++;
						Player.energy = 50;
						//System.out.println("������ " + Main.day + "���Դϴ�. �÷��̾� ü���� " + Main.energy + "�Դϴ�.");
						Farming.daysText.setText(Farming.day + "����");
						Farming.EnergyText.setText("���� ������ : " + Player.energy);
							textArea.selectAll();
							textArea.replaceSelection("");
							setVisible(false);
							//interrupt();
							//Main.btnNewButton.setVisible(true);
							//AppearanceOfAnimals.running = true;
							JOptionPane.showMessageDialog(null, "�߻������� ���ϰ� ���ҽ��ϴ�.", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);

					}
				}
			}
		}
		}).start();

		attackButton = new JButton("�����ϱ�");
		attackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Player.hp > 0) {

					if (wildSnake.hp > 0) {
						Player.attackPower = Player.randomPower.nextInt(11) + 10;
						textArea.append("�÷��̾�� �߻��쿡�� " + Player.attackPower + " �� �������� �������ϴ�! ");
						wildSnake.hp = wildSnake.hp - Player.attackPower;

						if (wildSnake.hp > 0) {
							textArea.append("(���� �߻����� ü���� " + wildSnake.hp + " �Դϴ�!) \n");
						} else if (wildSnake.hp <= 0) {
							textArea.append("�߻� ���� �׾����ϴ�! \n");

							randomItemSelect();

							//���Һ� ���� ����
							if (randomItem == 0) {
								//textArea.append("SYSTEM: ���Һ� ������ �޾ҽ��ϴ�! \n");
								Player.amountRandomMushroom++;
								textArea.selectAll();
								textArea.replaceSelection("");
								setVisible(false);
								
								System.out.println("���Һ����� ���� �� ����: " +Player.amountRandomMushroom);
								
								Farming.farmingScene.setVisible(true);
								//AppearanceOfAnimals.running = true;
								JOptionPane.showMessageDialog(null, "���Һ������� �޾ҽ��ϴ�", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
								// AppearanceOfAnimals.running = true;
								//Main.btnNewButton.setVisible(true);
							} 
							//�� ����
							else if (randomItem == 1) {
								//textArea.append("SYSTEM: ���� �޾ҽ��ϴ�! \n");
								Player.money = Player.money + 2000;
								textArea.selectAll();
								textArea.replaceSelection("");
								setVisible(false);
								
								//Main.btnNewButton.setVisible(true);
								Farming.farmingScene.setVisible(true);
								Farming.moneyText.setText("�� : " + Player.money);
								//AppearanceOfAnimals.running = true;
								JOptionPane.showMessageDialog(null, "���� �޾ҽ��ϴ�", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
								// AppearanceOfAnimals.running = true;
							}
							//�İ��� ����
							else if (randomItem >= 2) {
								//textArea.append("SYSTEM: �İ��縦 �޾ҽ��ϴ�! \n");
								Player.amountBone = Player.amountBone+3;
								textArea.selectAll();
								textArea.replaceSelection("");
								setVisible(false);
								
								//Main.btnNewButton.setVisible(true);
								Farming.farmingScene.setVisible(true);
								//AppearanceOfAnimals.running = true;
								JOptionPane.showMessageDialog(null, "�İ��縦 �޾ҽ��ϴ�", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
								// AppearanceOfAnimals.running = true;
								//System.out.println("�İ��� ���� �� ����: " +numOfBone);
							} 
						}

					}

				}
			}
		});
		attackButton.setBounds(68, 465, 168, 47);
		add(attackButton);
		
		useItemButton = new JButton("������ ���");
		useItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemPanel.setVisible(true);
			}
		});
		
		//JButton useRandomMushroomButton = new JButton(new ImageIcon("img/btLogin.png"));
		JButton useRandomMushroomButton = new JButton("����");
		useRandomMushroomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("���Һ����� ��� �� ����: " + Player.amountRandomMushroom);

				if (Player.hp > 0) {
					if (wildSnake.hp > 0) {
						if (Player.amountRandomMushroom > 0) {
							Player.amountRandomMushroom--;
							// System.out.println("���Һ����� ��� �� ���� ����: " +numOfRandomMushroom);
							randomMushroomSelect();
							// System.out.println("���Һ����� ���ù�ȣ: "randomMushroom);

							if (randomMushroom == 0) {
								Player.hp = Player.hp + 100;
								if (Player.hp > 100) {
									Player.hp = 100;
								}
								textArea.append("���Һ������� ȿ���� �÷��̾��� �� ����! ü��:" + Player.hp + "\n");
								itemPanel.setVisible(false);
							}
						
							if (randomMushroom == 1) {
								Player.hp = Player.hp - 100;
								textArea.append("���Һ������� ȿ���� �÷��̾��� �� ����! ü��: " + Player.hp + "\n");
								
								if (Player.hp <= 0) {
									Player.hp = 0;
								
									Farming.day ++;
									Player.energy = 50;
									textArea.selectAll();
									textArea.replaceSelection("");
									setVisible(false);
									//interrupt();
									//AppearanceOfAnimals.running = true;
									JOptionPane.showMessageDialog(null, "���Һ������� ȿ���� �װ��Ҿ��.", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);

									//}catch(Exception ie) {
									//	ie.printStackTrace();
									//}
								}		
							}
						} else {
							textArea.append("���Һ������� ��� ����� �� �����ϴ�.\n");
						}
					}
				}
			}
		});
		//btnNewButton.setFont(new Font("����ü", Font.PLAIN, 7));
		useRandomMushroomButton.setBounds(12, 10, 68, 37);
		itemPanel.add(useRandomMushroomButton);
		
		JButton usePotionHpOf30_Button = new JButton("ü�� 30% ����");
		usePotionHpOf30_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Player.hp > 0) {
					if (wildSnake.hp > 0) {
						if(Player.amountPotionHp_30 >0) {
							Player.amountPotionHp_30--;
							Player.hp = Player.hp +30;
							if(Player.hp > 100) {
								Player.hp = 100;
							}
							textArea.append("ü�� 30% �߰� ������ ����߽��ϴ�!. ü�� UP+30! \n");
							itemPanel.setVisible(false);
						}else {
							textArea.append("ü�� 30% �߰� ������ ��� ����� �� �����ϴ�. \n");
							itemPanel.setVisible(false);
						}
					}
				}
			}
		});
		usePotionHpOf30_Button.setBounds(92, 10, 68, 37);
		itemPanel.add(usePotionHpOf30_Button);
		
		JButton usePotionHpOf50_Button = new JButton("ü�� 50% ����");
		usePotionHpOf50_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Player.hp > 0) {
					if (wildSnake.hp > 0) {
						if(Player.amountPotionHp_50 >0) {
							Player.amountPotionHp_50--;
							Player.hp = Player.hp +50;
							if(Player.hp > 100) {
								Player.hp = 100;
							}
							textArea.append("ü�� 50% �߰� ������ ����߽��ϴ�!. ü�� UP+50! \n");
							itemPanel.setVisible(false);
						}else {
							textArea.append("ü�� 50% �߰� ������ ��� ����� �� �����ϴ�. \n");
							itemPanel.setVisible(false);
						}
					}
				}
			}
		});
		usePotionHpOf50_Button.setBounds(172, 10, 68, 37);
		itemPanel.add(usePotionHpOf50_Button);
		
		useItemButton.setBounds(317, 465, 168, 47);
		add(useItemButton);

		runAwayButton = new JButton("����ġ��");
		runAwayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Player.hp > 0) {
					if (wildSnake.hp > 0) {
						randomRun();

						if (numOfRun == 0) {
							//wildSnake.hp = wildSnake.hp-wildSnake.hp ;
							wildSnake.hp = 0;						
							System.out.println("�������� �� ü��: "+wildSnake.hp);
							textArea.selectAll();
							textArea.replaceSelection("");
							setVisible(false);
							//Main.btnNewButton.setVisible(true);
							//AppearanceOfAnimals.running = true;
							//System.out.println(AppearanceOfAnimals.running);
							JOptionPane.showMessageDialog(null, "�����ƽ��ϴ�!", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
							AppearanceOfAnimals appearanceOfAnimals = new AppearanceOfAnimals();
							appearanceOfAnimals.start();
							AppearanceOfAnimals.running = true;
							System.out.println(AppearanceOfAnimals.running);
						} else {
							textArea.append("�������⿡ �����Ͽ����ϴ�! \n");
						}
					}
				}
			}
		});
		
		runAwayButton.setBounds(562, 465, 168, 47);
		add(runAwayButton);
		
	}
}
