package OOP;

import java.awt.Dimension;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Table extends JTable {
	private Object[][] rowData = {{1, "item1", "cat1", 10, 1}, {2, "item1", "cat1", 5, 3}};
	private Object originalValue;

	public Table(String[] generalFields, JPanel panel, JComboBox<Object> combobox) {
		// INITIAL TABLE SETTING CHANGES
		setModel(new DefaultTableModel(rowData, generalFields));
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setAutoCreateRowSorter(true);
		setPreferredScrollableViewportSize(new Dimension(550, 200));

		// SETS COLUMN WIDTHS
		for (int i = 0; i < getColumnCount(); i++) {
			getColumnModel().getColumn(i).setPreferredWidth(150);
		}
		TableColumn column = getColumnModel().getColumn(3);
		column.setPreferredWidth(200);

		for (int i = 0; i < getRowCount(); i++) {
			updateSum(i);
		}

		getDefaultEditor(Object.class).addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingStopped(ChangeEvent e) {
				int col = getSelectedColumn();

				String[] options = {"Jā", "Nē"};
				int answer = JOptionPane.showOptionDialog(panel, "Vai tiešām vēlies veikt šīs izmaiņas?", "", 0, JOptionPane.QUESTION_MESSAGE, null, options, null);
				
				if(answer == 0 && (col == 3 || col == 4)){
					System.out.println("update");

					updateSum(getSelectedRow());
				} else {
					setValueAt(originalValue, getSelectedRow(), col);
				}

				Set<Object> uniqueValues = new HashSet<>();
				DefaultComboBoxModel model = (DefaultComboBoxModel) combobox.getModel();
				model.removeAllElements();
				for (int i = 0; i < getRowCount(); i++) {
					Object value = getValueAt(i, 2);
					if (value != null)
						uniqueValues.add(value);
				}

				Object last = null;
				for (Object cat : uniqueValues){
					model.addElement(cat);
					last = cat;
				}
				model.setSelectedItem(last);
			}

			@Override
			public void editingCanceled(ChangeEvent e) {
			}
		});
	}

	private void updateSum(int row){
		int sum = convertToInt(getValueAt(row, 3)) * convertToInt(getValueAt(row, 4));
		setValueAt(sum, row, 5);
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
	
	@Override
	public boolean isCellEditable(int row, int column) {
		if (column == 5)
			return false;

		return true;
	}

	@Override
    public boolean editCellAt(int row, int column, EventObject e) {
        originalValue = getValueAt(row, column);
        return super.editCellAt(row, column, e);
    }
}
