package OOP;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopGUI extends JFrame {

	public ShopGUI() {
		// MAIN APP WINDOW SETUP
		setTitle("Veikals");
		setSize(800, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String[] generalFields = {"Preces ID",
					"Nosaukums",
					"Kategorija",
					"Cena par vienību",
					"Daudzums",
					"Kopsumma"};

					
		JComboBox<Object> combobox = new JComboBox<>();
		JPanel panel = new JPanel();
		Table table = new Table(generalFields, panel, combobox);
		List<JTextField> list = new ArrayList<JTextField>();
		
		Set<Object> uniqueValues = new HashSet<>();
		DefaultComboBoxModel model = (DefaultComboBoxModel) combobox.getModel();
		for (int i = 0; i < table.getRowCount(); i++) {
			Object value = table.getValueAt(i, 2);
			if (value != null)
				uniqueValues.add(value);
		}
		Object last = null;
		for (Object cat : uniqueValues){
			model.addElement(cat);
			last = cat;
		}
		model.setSelectedItem(last);
					
		// FOR CATEGORY --->>>
		

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		int catCountVal = 0;
		int catSumVal = 0;
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			if(tableModel.getValueAt(i, 2).toString().equals(combobox.getSelectedItem())){
				catCountVal += (int)tableModel.getValueAt(i, 4);
				catSumVal += (int)tableModel.getValueAt(i, 3) * (int)tableModel.getValueAt(i, 4);
			}
		}

		// FOR CATEGORY --->>>
		JPanel catPanel = new JPanel();
		catPanel.setLayout(new GridLayout(2, 1, 10, 10));
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(1, 2, 10, 10));
		
		catPanel.add(combobox);
		JLabel catCount = new JLabel("Kopskaits: " + catCountVal);
		JLabel catSum = new JLabel("Kopsumma: " + catSumVal);
		textPanel.add(catCount);
		textPanel.add(catSum);

		catPanel.add(textPanel);
		panel.add(catPanel, SwingConstants.CENTER);
		// <<<--- FOR CATEGORY
						
		panel.add(new Button("Pievienot", "add", table, panel, list, combobox, catCount, catSum));
		panel.add(new Button("Noņemt", "remove", table, panel, list, combobox, catCount, catSum));

		panel.add(new InputPanel(generalFields, list));
		panel.add(new JScrollPane(table));


		add(panel);
	}

	public static void main(String[] args) {
		ShopGUI GUI = new ShopGUI();
		GUI.setVisible(true);
	}
}
