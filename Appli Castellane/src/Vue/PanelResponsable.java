package Vue;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Controleur.Candidat;
import Controleur.Controleur;
import Controleur.Responsable;
import Controleur.Tableau;

import javax.swing.JButton;

public class PanelResponsable extends PanelPrincipal implements ActionListener
{
	
	private JPanel panelForm=new JPanel ();
	private JTextField textNom = new JTextField ();
	private JTextField textPrenom = new JTextField ();
	private JTextField textEmail = new JTextField ();
	private JTextField textMdp = new JTextField ();
	private JTextField textType_User = new JTextField ();
	private JTextField textnumero_Telephone = new JTextField ();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider = new JButton("Valider");
	private JButton btSupprimer = new JButton("Supprimer");

	
	private JTable uneTable ; 
	private Tableau unTableau ; 
	private JPanel panelFiltre = new JPanel ();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");
	

	private JLabel lbNbResponsables = new JLabel();
	
	public PanelResponsable() {
		super("Gestion des Responsables"); 
		//installation du formulaire
		this.panelForm.setBackground(Color.cyan);
		this.panelForm.setBounds(40,80,300,220);
		this.panelForm.setLayout(new GridLayout(7,2));
		
		this.panelForm.add(new  JLabel("Nom Responsable:"));
		this.panelForm.add(this.textNom);
		
		this.panelForm.add(new  JLabel("Prenom Responsable :"));
		this.panelForm.add(this.textPrenom);
		
		this.panelForm.add(new  JLabel("Email  :"));
		this.panelForm.add(this.textEmail);
		
		this.panelForm.add(new  JLabel("Mdp :"));
		this.panelForm.add(this.textMdp);
		
		this.panelForm.add(new  JLabel("Type d'utilisateur :"));
		this.panelForm.add(this.textType_User);
		
		this.panelForm.add(new  JLabel("Numero de Telephone:"));
		this.panelForm.add(this.textnumero_Telephone);
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btValider);
		
		//on ajoute le formulaire dans la vue
		this.add(this.panelForm);
		
		//rendre les bouttons clicables
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		
		//installation de la JTable 
		String entetes [] = {"idResponsable","Nom", "Prenom", "Email","Mdp","Type_User ","Numero_Telephone"};
		this.unTableau = new Tableau (this.obtenirDonnees(""), entetes); 
		this.uneTable = new JTable(this.unTableau); 
		JScrollPane uneScroll = new JScrollPane(this.uneTable); 
		
		uneScroll.setBounds(400, 80, 500, 340);
		this.add(uneScroll); 
		
		//implementation du click sur une ligne de la table 
		this.uneTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {	
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne = 0 ; 
				
				if (e.getClickCount() >= 1) {
					numLigne = uneTable.getSelectedRow(); 
					textNom.setText(unTableau.getValueAt(numLigne, 1).toString());
					textPrenom.setText(unTableau.getValueAt(numLigne,2).toString());
					textEmail.setText(unTableau.getValueAt(numLigne, 3).toString());
					textMdp.setText(unTableau.getValueAt(numLigne, 3).toString());
					textType_User.setText(unTableau.getValueAt(numLigne,4).toString());
					textnumero_Telephone.setText(unTableau.getValueAt(numLigne, 5).toString());
					
					btSupprimer.setVisible(true);
					btValider.setText("Modifier");
				}
				
			}
		});
		
		//installation du panel filtre
		this.panelFiltre.setBackground(Color.cyan);
		this.panelFiltre.setLayout(new GridLayout(1, 3));
		this.panelFiltre.setBounds(400, 50, 400, 20);
		this.panelFiltre.add(new JLabel("Filtrer les responsables par : "));
		this.panelFiltre.add(this.txtFiltre);
		this.panelFiltre.add(this.btFiltrer);
		this.btFiltrer.addActionListener(this);
		this.add(this.panelFiltre);
		
		//installation du label nb candidats
		
		this.lbNbResponsables .setBounds(450,440,400,20);
		this.lbNbResponsables .setText("Nombre de responsables : " + this.unTableau.getColumnCount());
		this.add(this.lbNbResponsables );
				

}

private Object[][] obtenirDonnees(String filtre) {

//récuperer les responsables  de la base de données
ArrayList<Responsable> lesResponsables ;
if (filtre.equals("")) {
	lesResponsables = Controleur.selectAllResponsables();
	}else {
		lesResponsables = Controleur.selectLikeResponsable(filtre);
	}
//création d'une matrice de données
Object[][] matrice = new Object[lesResponsables.size()][7];
int i = 0;
for (Responsable unResponsable : lesResponsables) {
	matrice[i][0] = unResponsable.getIdResponsable();
	matrice[i][1] = unResponsable.getNom();
	matrice[i][2] = unResponsable.getPrenom();
	matrice[i][3] = unResponsable.getEmail();
	matrice[i][4] = unResponsable.getMdp();
	//matrice[i][5] = unCandidat.getMdp();
	matrice[i][5] = unResponsable.getType_User();
	matrice[i][6] = unResponsable.getNumero_Telephone();
	i++;
}
return matrice ;
		
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() ==this.btAnnuler) {
			this.textNom.setText("");
			this.textPrenom.setText("");
		    this.textEmail.setText("");
			this.textMdp.setText("");
			this.textType_User.setText("");
			this.textnumero_Telephone.setText("");
			
		}
		else if (e.getSource() ==this.btValider) {
		// recuperer les champs saisis
		String nom = this.textNom.getText();
		String prenom = this.textPrenom.getText();
        String email = this.textEmail.getText();
        String Mdp = this.textMdp .getText();
		String Type_User = this.textType_User.getText();
		String numero_telephone = this.textnumero_Telephone.getText();
		//instanciation de la classe Responsable
		Responsable unResponsable =new Responsable(nom,prenom,email,Mdp,Type_User,numero_telephone);
		// inserer dans la base de donnees
		Controleur.insertResponsable(unResponsable);
		
		//on affiche un message d insertion 
		JOptionPane.showMessageDialog(this,"insertion reussi du Responsable");
		
		//on vide les champs
		this.textNom.setText("");
		this.textPrenom.setText("");
		this.textEmail.setText("");
		this.textMdp.setText("");
		this.textType_User.setText("");
		this.textnumero_Telephone.setText("");
		
		
		btSupprimer.setVisible(false);
		btValider.setText("Valider");
	}
	else if (e.getSource() == this.btFiltrer) {
		//recuperer le filtre
		String filtre = this.txtFiltre.getText();
		
		//on actualise l'affichage du tableau avec les responsable trouves
		this.unTableau.setDonnees(this.obtenirDonnees(filtre));
		
	    }
	}
	
}
