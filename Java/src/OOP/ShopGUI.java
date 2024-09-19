package OOP;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ShopGUI extends JFrame {

	public ShopGUI() {
		setTitle("Veikals");
		setSize(800, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Object[][] rowData = {{1, "item1", "cat1", 10, 1}, {2, "item1", "cat1", 5, 3}};
		String[] columnNames = {"Preces ID",
                        "Nosaukums",
                        "Kategorija",
                        "Cena par vienību",
                        "Daudzums",
						"Kopsumma"};

		DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
		List<JTextField> list = new ArrayList<JTextField>();

		JTable table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JPanel panel = new JPanel();

		table.setAutoCreateRowSorter(true);

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(5,2, 10, 10));
		
		panel.add(new Button("Pievienot", "add", table, panel, list));
		panel.add(new Button("Noņemt", "remove", table, panel, list));
		// panel.add(new Button("Labot", "update", table, rowData, panel));



		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 2; j++) {
				if(j==1){
					Input input = new Input();
					inputPanel.add(input);
					list.add(input);
				} else
					inputPanel.add(new JLabel(columnNames[i]));
			}
		}

		table.setPreferredScrollableViewportSize(new Dimension(550, 200));

		panel.add(inputPanel);
		panel.add(new JScrollPane(table));

		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(150);
		}
		TableColumn column = table.getColumnModel().getColumn(3);
		column.setPreferredWidth(200);

		add(panel);
	}

	public static void main(String[] args) {
		ShopGUI GUI = new ShopGUI();
		GUI.setVisible(true);
	}
}
