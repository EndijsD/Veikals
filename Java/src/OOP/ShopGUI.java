package OOP;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
                        "Daudzums"};

		DefaultTableModel model = new DefaultTableModel(rowData, columnNames);

		JTable table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JPanel panel = new JPanel();

		table.setAutoCreateRowSorter(true);
		
		panel.add(new Button("Pievienot", "add", table, rowData, panel));
		panel.add(new Button("Noņemt", "remove", table, rowData, panel));
		// panel.add(new Button("Labot", "update", table, rowData, panel));

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(5,2, 10, 10));


		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 2; j++) {
				inputPanel.add(j == 0 ? new JLabel(columnNames[i]) : new Input());
			}
		}

		panel.add(inputPanel);
		panel.add(new JScrollPane(table));

		TableColumn column = null;
		column = table.getColumnModel().getColumn(3);
		column.setPreferredWidth(100);

		add(panel);
	}

	public static void main(String[] args) {
		ShopGUI GUI = new ShopGUI();
		GUI.setVisible(true);
	}
}
