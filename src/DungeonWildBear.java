import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class DungeonWildBear extends JPanel {

	public static Object textArea;
	private JPanel contentPane;
	private JButton dungeonEx;
	private JButton useItemButton;
	private JButton runAwayButton;
	private JButton attackButton;

	int day = 1;
	int energy = 100;

	static int randomMushroom;
	static int randomItem;
	static int numOfRun;
	static int numOfRandomMushroom = 2;
	static int numOfBone = 1;
	static int money;

	static Random randomMushroomSelect = new Random();
	static Random randomItemSelect = new Random();
	static Random randomRun = new Random();

	// ���� ���� �����ϴ� �����Լ�
	public static void randomMushroomSelect() {
		randomMushroom = randomMushroomSelect.nextInt(2);
	}

	// ���� ������ �����ϴ� �����Լ�
	public static void randomItemSelect() {
		randomItem = randomItemSelect.nextInt(3);
	}

	// ������ ���⸦ �����Լ�
	public static void randomRun() {
		numOfRun = randomRun.nextInt(5);
	}

	public DungeonWildBear() {
		setLayout(null);
		setBounds(0, 0, 800, 600);
		setBackground(Color.black);
		
		WildAnimal wildBear = new WildAnimal("�߻���", 100, 10, true);

		JLabel wildBearImage = new JLabel();
		wildBearImage.setHorizontalAlignment(SwingConstants.CENTER);
		wildBearImage.setIcon(new ImageIcon("./images/wildbear.png"));
		wildBearImage.setBounds(263, -11, 266, 216);
		add(wildBearImage);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(68, 218, 662, 216);
		textArea.setEnabled(true);
		//textArea.setFont((new Font("����ü", Font.BOLD, 15)));

		JScrollPane scrollBar = new JScrollPane(textArea);
		scrollBar.setBounds(68, 218, 662, 216);
		scrollBar.getVerticalScrollBar().setValue(scrollBar.getVerticalScrollBar().getMaximum());
		add(scrollBar);

		// �߻��� �ڵ� ���� ������
		new Thread(new Runnable() {
			public void run() {
				try {
					do {
						Thread.sleep(3000);
						wildBearAttack();
					} while (wildBear.hp > 0 && Player2.hp > 0);
				} catch (Exception ie) {
					ie.printStackTrace();
				}
			}

			private void wildBearAttack() {
				{
					if (wildBear.hp > 0) {
						Player2.hp = Player2.hp - wildBear.power;
						textArea.append("�߻����� �����Ͽ����ϴ�! (���� �÷��̾� ü��: " + Player2.hp + ")\n");
						scrollBar.getVerticalScrollBar().setValue(scrollBar.getVerticalScrollBar().getMaximum());

						// ���ӿ� ���� �������� �ٲ��, �÷��̾��� ü���� 50���� �����Ѵ�.
						if (Player2.hp == 0) {
							day = day + 1;
							energy = 50;
							System.out.println("������ " + day + "���Դϴ�. �÷��̾� ü���� " + energy + "�Դϴ�.");

							try {
								Thread.sleep(1000);
								Player2.hp = 100;
								wildBear.hp = 100;
								textArea.selectAll();
								textArea.replaceSelection("");
								setVisible(false);
								//Main.dungeonWildBear.setVisible(true);
								// dungeonEx.setVisible(true);
							} catch (Exception ie) {
								ie.printStackTrace();
							}
						}
					}
				}
			}
		}).start();

		attackButton = new JButton("�����ϱ�");
		attackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Player2.hp > 0) {

					if (wildBear.hp > 0) {
						Player2.attackPower = Player2.randomPower.nextInt(11) + 10;
						textArea.append("�÷��̾�� �߻������� " + Player2.attackPower + " �� �������� �������ϴ�! ");
						wildBear.hp = wildBear.hp - Player2.attackPower;

						if (wildBear.hp > 0) {
							textArea.append("(���� �߻����� ü���� " + wildBear.hp + " �Դϴ�!) \n");
						} else if (wildBear.hp <= 0) {
							textArea.append("�߻� ���� �׾����ϴ�! \n");

							randomItemSelect();
							// System.out.println("�İ��� �ޱ� �� ����: " +numOfBone);
							// System.out.println("���� ���� �ޱ� �� ����: " +numOfRandomMushroom);
							// System.out.println("�� �ޱ� �� �����Ӵ�: " +money);
							if (randomItem == 0) {
								textArea.append("SYSTEM: �İ��縦 �޾ҽ��ϴ�! \n");
								numOfBone++;

								try {
									// textArea.append("SYSTEM: �İ��縦 �޾ҽ��ϴ�! \n");
									Thread.sleep(1000);
									Player2.hp = 100;
									wildBear.hp = 100;
									textArea.selectAll();
									textArea.replaceSelection("");
									setVisible(false);
									// dungeonEx.setVisible(true);
								} catch (Exception ie) {
									ie.printStackTrace();
								}
								Main.btnNewButton.setVisible(true);
								// System.out.println("�İ��� ���� �� ����: " +numOfBone);
							} else if (randomItem == 1) {
								textArea.append("SYSTEM: ���������� �޾ҽ��ϴ�! \n");
								numOfRandomMushroom++;
								// System.out.println("���� ���� ���� �� ����: " +numOfRandomMushroom);
								Main.btnNewButton.setVisible(true);
							} else if (randomItem == 2) {
								textArea.append("SYSTEM: ���� �޾ҽ��ϴ�! \n");
								// System.out.println("�� ���� �� ���� �Ӵ�: " +money);
								money = money + 1000;
								// System.out.println("��: " +money);
								/*
								 * try { Thread.sleep(1000); Player.hp = 100; wildBear.hp = 100;
								 * textArea.selectAll(); textArea.replaceSelection("");
								 * dungeonWildBear.setVisible(false); dungeonEx.setVisible(true); }catch
								 * (Exception ie) { ie.printStackTrace(); }
								 */
								Main.btnNewButton.setVisible(true);
							}
						}

					}

				}
			}
		});
		attackButton.setBounds(68, 458, 168, 47);
		add(attackButton);

		useItemButton = new JButton("������ ���");
		useItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("���� ���� ��� �� ����: " + numOfRandomMushroom);

				if (Player2.hp > 0) {
					if (wildBear.hp > 0) {
						if (numOfRandomMushroom > 0) {
							numOfRandomMushroom--;
							// System.out.println("���� ���� ��� �� ���� ����: " +numOfRandomMushroom);
							randomMushroomSelect();
							// System.out.println("���� ���� ���ù�ȣ: "randomMushroom);

							if (randomMushroom == 0) {
								Player2.hp = Player2.hp + 20;
								if (Player2.hp > 100) {
									Player2.hp = 100;
								}
								textArea.append("���������� ȿ���� �÷��̾��� �� ����! ü��:" + Player2.hp + "\n");
							}

							if (randomMushroom == 1) {
								Player2.hp = Player2.hp - 20;
								if (Player2.hp <= 0) {
									Player2.hp = 0;
								}
								textArea.append("���������� ȿ���� �÷��̾��� �� ����! ü��: " + Player2.hp + "\n");
							}
						} else {
							textArea.append("���������� ��� ����� �� �����ϴ�.\n");
						}
					}
				}
			}

		});
		useItemButton.setBounds(317, 458, 168, 47);
		add(useItemButton);

		runAwayButton = new JButton("����ġ��");
		runAwayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Player2.hp > 0) {
					if (wildBear.hp > 0) {
						randomRun();

						if (numOfRun == 0) {
							Player2.hp = 100;
							wildBear.hp = 100;
							textArea.selectAll();
							textArea.replaceSelection("");
							setVisible(false);
							Main.btnNewButton.setVisible(true);
							
						} else {
							textArea.append("�������⿡ �����Ͽ����ϴ�! \n");
						}
					}
				}
			}
		});
		runAwayButton.setBounds(562, 458, 168, 47);
		add(runAwayButton);
	}

}
