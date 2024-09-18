package OOP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Button extends JButton {

	public Button(String name, String action, JTable table, Object[][] rowData, JPanel panel) {
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
						JOptionPane.showMessageDialog(panel, "Ieraksts tika izdzēsts!");
					}
				}
			}
		});
	}
}
