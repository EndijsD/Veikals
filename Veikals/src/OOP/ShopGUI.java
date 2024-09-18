package OOP;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ShopGUI extends JFrame {

	public ShopGUI() {
		setTitle("Veikals");
		setSize(800, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();

		panel.add(new Button("Pievienot"));
		panel.add(new Button("Noòemt"));
		panel.add(new Button("Labot"));
//		panel.add(new Input());
		panel.add(new JScrollPane(new Table()));

		add(panel);
	}

	public static void main(String[] args) {
		ShopGUI GUI = new ShopGUI();
		GUI.setVisible(true);
	}
}
