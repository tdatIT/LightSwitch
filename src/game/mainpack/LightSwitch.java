package game.mainpack;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LightSwitch extends JFrame implements ActionListener {

	private static final long serialVersionUID = -6400598411033928989L;
	private final int SIZE = 10;
	private Color onSwitch = Color.BLUE;
	private Color offSwitch = Color.red;
	// Set col-row

	private boolean power[][] = new boolean[SIZE + 2][SIZE + 2];
	Container ctn;
	JPanel pn1, pn2;
	JLabel lb1;
	JButton btnFill, btnExit, btnReset;
	JButton lights[][] = new JButton[SIZE + 2][SIZE + 2];

	// set properties
	public LightSwitch() {
		super("Light Switch");
		ctn = getContentPane();
		pn1 = new JPanel();
		pn1.setLayout(new GridLayout(SIZE, SIZE));
		for (int i = 0; i < SIZE + 2; i++) {
			for (int j = 0; j < SIZE + 2; j++) {
				lights[i][j] = new JButton("");
				lights[i][j].setBackground(offSwitch);
				lights[i][j].setActionCommand(i + ";" + j);
				lights[i][j].addActionListener(this);
				power[i][j] = false;
			}
		}
		for (int i = 1; i < SIZE + 1; i++) {
			for (int j = 1; j < SIZE + 1; j++) {
				pn1.add(lights[i][j]);

			}
		}
		lb1 = new JLabel("Anh bạn à tối quá mở đèn hộ");
		btnExit = new JButton("Thoát");
		btnFill = new JButton("Mở đèn hộ");
		btnReset = new JButton("Tắt đèn dùm");
		// set actionListener for button
		btnExit.addActionListener(this);
		btnFill.addActionListener(this);
		btnReset.addActionListener(this);
		btnExit.setForeground(Color.orange);
		// add to container
		ctn.add(pn1);
		// set function button
		pn2 = new JPanel();
		pn2.setLayout(new FlowLayout());
		pn2.add(btnReset);
		pn2.add(btnFill);
		pn2.add(btnExit);
		ctn.add(pn2, "South");
		// set frame
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCmd = e.getActionCommand();
		if (actionCmd == "Thoát") {
			System.exit(0);
		} else {
			if (actionCmd == "Tắt đèn dùm") {
				resetGame();
			} else {
				if (actionCmd == "Mở đèn hộ") {
					fillLight();
				} else {
					onSwitch(actionCmd);
				}
			}

		}
	}

	private void onSwitch(String actionCmd) {
		// TODO Auto-generated method stub
		StringTokenizer token = new StringTokenizer(actionCmd, ";");
		int i = Integer.parseInt(token.nextToken());
		int j = Integer.parseInt(token.nextToken());
		int X[] = { 0, -1, 0, 1, 0 };
		int Y[] = { 0, 0, 1, 0, -1 };
		if(power[i][j]==true) {
			lights[i][j].setBackground(offSwitch);
			power[i][j] = false;
		}else
		for (int k = 0; k < 5; k++) {
			if (power[i + X[k]][j + Y[k]] == false) {
				lights[i + X[k]][j + Y[k]].setBackground(onSwitch);
				power[i + X[k]][j + Y[k]] = true;
			} else {
				lights[i + X[k]][j + Y[k]].setBackground(offSwitch);
				power[i + X[k]][j + Y[k]] = false;
			}

		}

		if (checkWin()) {
			JOptionPane.showMessageDialog(this, "Bạn giỏi quá đi UwU");
			resetGame();
		}

	}

	private void resetGame() {
		for (int i = 1; i < SIZE + 1; i++) {
			for (int j = 1; j < SIZE + 1; j++) {
				lights[i][j].setBackground(offSwitch);
				power[i][j] = false;
			}
		}
	}

	private void fillLight() {
		for (int i = 1; i < SIZE + 1; i++) {
			for (int j = 1; j < SIZE + 1; j++) {
				lights[i][j].setBackground(onSwitch);
				power[i][j] = true;
			}
		}
		lights[SIZE][SIZE].setBackground(offSwitch);
		power[SIZE][SIZE] = false;
		//
		lights[SIZE-1][SIZE].setBackground(offSwitch);
		power[SIZE-1][SIZE] = false;
		//
		lights[SIZE][SIZE-1].setBackground(offSwitch);
		power[SIZE][SIZE-1] = false;
	}

	private boolean checkWin() {
		for (int i = 1; i < SIZE + 1; i++) {
			for (int j = 1; j < SIZE + 1; j++) {
				if (power[i][j] == false)
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		LightSwitch game = new LightSwitch();

	}

}
