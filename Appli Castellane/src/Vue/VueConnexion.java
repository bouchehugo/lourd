package Vue;

import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controleur.PPEAUTO;
import Controleur.Controleur;
import Controleur.PPEAUTO;
import Controleur.Responsable;

public class VueConnexion extends JFrame implements ActionListener 
{
	private JButton btSeConnecter = new JButton("Se Connecter") ; 
	private JButton btAnnuler = new JButton("Annuler") ;
	private JTextField txtEmail = new JTextField("b@gmail.com"); 
	private JPasswordField txtMdp = new JPasswordField("789"); 
	
	private JPanel panelForm = new JPanel (); 
	
	public VueConnexion() {
		this.setTitle("Application PPE_AUTO_ECOL2025");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100, 100, 600, 300);
		this.setLayout(null);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.darkGray);
		
		//ajout de l'image logo
		ImageIcon uneImage = new ImageIcon("src/images/logoad.jpg");
		JLabel unLogo = new JLabel(uneImage); 
		unLogo.setBounds(50, 50, 150, 150);
		this.add(unLogo);
		
		//Construction du panel Formulaire 
		this.panelForm.setBounds(280, 40, 260, 200);
		this.panelForm.setBackground(Color.darkGray);
		this.panelForm.setLayout(new GridLayout(3,2)); //matrice de 3 lignes et de 2 colonnes
		this.panelForm.add(new JLabel("Email")); 
		this.panelForm.add(this.txtEmail);
		
		this.panelForm.add(new JLabel("MDP")); 
		this.panelForm.add(this.txtMdp);
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btSeConnecter);
		
		this.add(this.panelForm); //ajout du panel dans la fenetre 
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btSeConnecter.addActionListener(this);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btAnnuler) {
			 this.txtEmail.setText("");
			 this.txtMdp.setText("");
		 }else if (e.getSource() == this.btSeConnecter) {
			 
			 String email = this.txtEmail.getText(); 
			 String mdp = new String (this.txtMdp.getPassword()); 
			 
			 //on vérifie la présence du  dans la BDD 
			 Responsable unResponsable = Controleur.selectWhereResponsable(email, mdp); 
			 if (unResponsable == null) {
				 JOptionPane.showMessageDialog(this, "Veuillez vérifier vos idenifiants !", 
						 "Erreur de Connexion", JOptionPane.ERROR_MESSAGE);
			 }else {
				 JOptionPane.showMessageDialog(this, "Bienvenue "+unResponsable.getNom()
				 + " "+unResponsable.getPrenom(), 
						 "Connexion à Auto-Ecole Castellane Application", JOptionPane.INFORMATION_MESSAGE);
				 
				 PPEAUTO.rendreVisible(false); //fermeture de la connexion
				 
				 PPEAUTO.creerVueGenerale(true); //ouverture du logiciel 
				 
			 }
		 }
	}
}

























