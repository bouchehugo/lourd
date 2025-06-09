package Vue;

import java.awt.Color;



import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPrincipal extends JPanel 
{
	public PanelPrincipal(String titre) {
		this.setBounds(20,80,960, 470 );
		this.setLayout(null);
		this.setBackground(Color.cyan);
		
		JLabel lbTitre = new JLabel(titre); 
		lbTitre.setBounds(350, 20, 200,20);
		this.add(lbTitre);
		
		this.setVisible(false);
	}
}
