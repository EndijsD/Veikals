package OOP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Button extends JButton {

	public Button(String name, String action, JTable table, JPanel panel, List<JTextField> list) {
		this.setText(name);
		this.setActionCommand(action);

		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				if (e.getActionCommand() == "remove") {
					int rowID = table.getSelectedRow();
					if(rowID > -1){
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.removeRow(rowID);
						JOptionPane.showMessageDialog(panel, "Ieraksts tika izdzēsts!", "", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(panel, "Nav izvēlēts ieraksts!", "", JOptionPane.WARNING_MESSAGE);
					}
				}else if(e.getActionCommand() == "add"){
					String[] inputContent = new String[5];
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

								if (!idAlreadyExists)
									model.addRow(inputContent);
							}
						}
					 }
				}
			}
		});
	}
}
