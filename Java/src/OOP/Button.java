package OOP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Button extends JButton {

	public Button(String name, String action, JTable table, JPanel panel, List<JTextField> list, JComboBox<Object> combobox, JLabel catCount, JLabel catSum) {
		// INITIAL BUTTON SETUP
		setText(name);
		setActionCommand(action);

		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				if (e.getActionCommand().equals("remove")) {
					int rowID = table.getSelectedRow();
					if(rowID > -1){
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.removeRow(rowID);
						JOptionPane.showMessageDialog(panel, "Ieraksts tika izdzēsts!", "", JOptionPane.INFORMATION_MESSAGE);

						resetCombobox(combobox, table);
					} else {
						JOptionPane.showMessageDialog(panel, "Nav izvēlēts ieraksts!", "", JOptionPane.WARNING_MESSAGE);
					}
				}else if(e.getActionCommand().equals("add")){
					String[] inputContent = new String[6];
					for(int i = 0; i < 5; i++) { 
						if(list.get(i).getText().isEmpty()){
							JOptionPane.showMessageDialog(panel, "Nav aizpildīti visi ievades lauki!", "", JOptionPane.WARNING_MESSAGE);
							break;
						} else {
							inputContent[i] = list.get(i).getText();

							if(i == 4){
								DefaultTableModel model = (DefaultTableModel) table.getModel();
								boolean idAlreadyExists = false;

								for (int j = 0; j < model.getRowCount(); j++) {
									if(inputContent[0].equals(model.getValueAt(j, 0).toString())){
										idAlreadyExists = true;
										JOptionPane.showMessageDialog(panel, "Tāds ID jau eksistē!", "", JOptionPane.WARNING_MESSAGE);
										break;
									}
								}

								if (!idAlreadyExists){
									inputContent[5] = String.valueOf(convertToInt(inputContent[3]) * convertToInt(inputContent[4]));
									model.addRow(inputContent);
									resetCombobox(combobox, table);
								}
							}
						}
					 }
				}
			}
		});

		combobox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) combobox.getSelectedItem();
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				int catCountVal = 0;
				int catSumVal = 0;
				
				for (int i = 0; i < tableModel.getRowCount(); i++) {
					if(tableModel.getValueAt(i, 2).toString().equals(selectedItem)){
						catCountVal += convertToInt(tableModel.getValueAt(i, 4));
						catSumVal += convertToInt(tableModel.getValueAt(i, 3)) * convertToInt(tableModel.getValueAt(i, 4));
						System.out.println(catSumVal);
					}
				}
				
				catCount.setText("Kopskaits: " + catCountVal);
				catSum.setText("Kopsumma: " + catSumVal);
			}
		});
	}

	private void resetCombobox(JComboBox<Object> combobox, JTable table){
		Set<Object> uniqueValues = new HashSet<>();
		DefaultComboBoxModel model = (DefaultComboBoxModel) combobox.getModel();
		model.removeAllElements();
		for (int i = 0; i < table.getRowCount(); i++) {
			Object value = table.getValueAt(i, 2);
			if (value != null)
				uniqueValues.add(value);
		}

		for (Object cat : uniqueValues){
			model.addElement(cat);
		}
		model.setSelectedItem(null);
	}

	private int convertToInt(Object value) {
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof String) {
            try {
                return Integer.parseInt((String) value);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }
}
