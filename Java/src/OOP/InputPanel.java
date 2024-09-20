package OOP;

import java.awt.GridLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputPanel extends JPanel {
    
    public InputPanel (String[] generalFields, List<JTextField> list) {
        setLayout(new GridLayout(5, 2, 10, 10));

        for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 2; j++) {
				if(j==1){
					Input input = new Input();
					add(input);
					list.add(input);
				} else
					add(new JLabel(generalFields[i]));
			}
		}
    }
}
