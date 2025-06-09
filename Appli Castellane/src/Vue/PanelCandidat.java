package Vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import Controleur.Candidat;
import Controleur.Controleur;
import Controleur.Tableau;

public class PanelCandidat extends PanelPrincipal implements ActionListener {
    
    private JPanel panelForm = new JPanel();
    private JTextField textNom = new JTextField();
    private JTextField textPrenom = new JTextField();
    private JTextField textAge = new JTextField();
    private JTextField textEmail = new JTextField();
    private JTextField textType_User = new JTextField();
    private JTextField textNumero_Telephone = new JTextField();
    private JComboBox<String> comboProfession = new JComboBox<>();
    
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");

    private JTable uneTable; 
    private Tableau unTableau; 
    private JPanel panelFiltre = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");
    
    private JLabel lbNbCandidats = new JLabel();
    
    public PanelCandidat() {
        super("Gestion des Candidats"); 
        
        // Configuration du formulaire
        this.panelForm.setBackground(Color.cyan);
        this.panelForm.setBounds(40,80,300,250);
        this.panelForm.setLayout(new GridLayout(8,2));
    
        // Configuration du ComboBox
        comboProfession.addItem("Étudiant");
        comboProfession.addItem("Salarié");
        comboProfession.addItem("Sans emploi");
        
        // Ajout des champs au formulaire
        this.panelForm.add(new JLabel("Nom Candidat:"));
        this.panelForm.add(this.textNom);
        this.panelForm.add(new JLabel("Prenom Candidat:"));
        this.panelForm.add(this.textPrenom);
        this.panelForm.add(new JLabel("Age:"));
        this.panelForm.add(this.textAge);
        this.panelForm.add(new JLabel("Email:"));
        this.panelForm.add(this.textEmail);
        this.panelForm.add(new JLabel("Type User:"));
        this.panelForm.add(this.textType_User);
        this.textType_User.setText("candidat");
        this.textType_User.setEditable(false);
        this.panelForm.add(new JLabel("Téléphone:"));
        this.panelForm.add(this.textNumero_Telephone);
        this.panelForm.add(new JLabel("Profession:"));
        this.panelForm.add(this.comboProfession);
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        
        this.add(this.panelForm);
        
        // Configuration des listeners
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);
        this.btSupprimer.addActionListener(this);
        this.btSupprimer.setVisible(false);
        this.add(btSupprimer);

        // Configuration du tableau
        String entetes[] = {"ID","Nom", "Prenom", "Age","Email", "Type","Téléphone", "Profession"};
        this.unTableau = new Tableau(this.obtenirDonnees(""), entetes);
        this.uneTable = new JTable(this.unTableau); 
        JScrollPane uneScroll = new JScrollPane(this.uneTable); 
        uneScroll.setBounds(400, 80, 550, 340);
        this.add(uneScroll);
        
        // Gestion du clic sur le tableau
        this.uneTable.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() >= 1) {
                    int numLigne = uneTable.getSelectedRow();
                    textNom.setText(unTableau.getValueAt(numLigne, 1).toString());
                    textPrenom.setText(unTableau.getValueAt(numLigne, 2).toString());
                    textAge.setText(unTableau.getValueAt(numLigne, 3).toString());
                    textEmail.setText(unTableau.getValueAt(numLigne, 4).toString());
                    textType_User.setText(unTableau.getValueAt(numLigne, 5).toString());
                    textNumero_Telephone.setText(unTableau.getValueAt(numLigne, 6).toString());
                    comboProfession.setSelectedItem(unTableau.getValueAt(numLigne, 7).toString());
                    btSupprimer.setVisible(true);
                    btValider.setText("Modifier");
                }
            }
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });
        
        // Configuration du filtre
        this.panelFiltre.setBackground(Color.cyan);
        this.panelFiltre.setLayout(new GridLayout(1, 3));
        this.panelFiltre.setBounds(400, 50, 400, 20);
        this.panelFiltre.add(new JLabel("Filtrer: "));
        this.panelFiltre.add(this.txtFiltre);
        this.panelFiltre.add(this.btFiltrer);
        this.btFiltrer.addActionListener(this);
        this.add(this.panelFiltre);
        
        // Configuration du label de comptage
        this.lbNbCandidats.setBounds(450,440,400,20);
        this.lbNbCandidats.setText("Nombre de candidats: " + this.unTableau.getRowCount());
        this.add(this.lbNbCandidats);
    }

    private String genererMotDePasse(String nom, String prenom) {
        String premiereLettrePrenom = prenom.substring(0, 1).toLowerCase();
        String troisLettresNom = nom.length() >= 3 ? nom.substring(0, 3).toLowerCase() : nom.toLowerCase();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        String date = sdf.format(new Date());
        return premiereLettrePrenom + "." + troisLettresNom + "@" + date;
    }

    private Object[][] obtenirDonnees(String filtre) {
        ArrayList<Candidat> lesCandidats = filtre.equals("") 
            ? Controleur.selectAllCandidats() 
            : Controleur.selectLikeCandidats(filtre);
        
        Object[][] matrice = new Object[lesCandidats.size()][8];
        int i = 0;
        for (Candidat unCandidat : lesCandidats) {
            matrice[i][0] = unCandidat.getIdCandidat();
            matrice[i][1] = unCandidat.getNom();
            matrice[i][2] = unCandidat.getPrenom();
            matrice[i][3] = unCandidat.getAge();
            matrice[i][4] = unCandidat.getEmail();
            matrice[i][5] = unCandidat.getType_User();
            matrice[i][6] = unCandidat.getNumero_Telephone();
            matrice[i][7] = unCandidat.getProfession();
            i++;
        }
        return matrice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.textNom.setText("");
            this.textPrenom.setText("");
            this.textAge.setText("");
            this.textEmail.setText("");
            this.textType_User.setText("candidat");
            this.textNumero_Telephone.setText("");
            this.comboProfession.setSelectedIndex(0);
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
        else if (e.getSource() == this.btValider) {
            String nom = this.textNom.getText();
            String prenom = this.textPrenom.getText();
            String age = this.textAge.getText();
            String email = this.textEmail.getText();
            String type_User = this.textType_User.getText();
            String numero_Telephone = this.textNumero_Telephone.getText();
            String profession = (String)comboProfession.getSelectedItem();
            String mdp = genererMotDePasse(nom, prenom);
            
            Candidat unCandidat = new Candidat(nom, prenom, age, email, mdp, 
                                             type_User, numero_Telephone, profession);
            
            if (btValider.getText().equals("Valider")) {
                Controleur.insertCandidat(unCandidat);
                JOptionPane.showMessageDialog(this, "Candidat ajouté\nMot de passe: " + mdp);
            } else {
                unCandidat.setIdCandidat((int)unTableau.getValueAt(uneTable.getSelectedRow(), 0));
                Controleur.updateCandidat(unCandidat);
                JOptionPane.showMessageDialog(this, "Modification réussie");
            }
            
            this.unTableau.setDonnees(this.obtenirDonnees(""));
            this.lbNbCandidats.setText("Nombre de candidats: " + this.unTableau.getRowCount());
            
            // Réinitialisation
            this.textNom.setText("");
            this.textPrenom.setText("");
            this.textAge.setText("");
            this.textEmail.setText("");
            this.textNumero_Telephone.setText("");
            this.comboProfession.setSelectedIndex(0);
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
        else if (e.getSource() == this.btFiltrer) {
            this.unTableau.setDonnees(this.obtenirDonnees(txtFiltre.getText()));
            this.lbNbCandidats.setText("Nombre de candidats: " + this.unTableau.getRowCount());
        }
        else if (e.getSource() == this.btSupprimer) {
            int id = (int)unTableau.getValueAt(uneTable.getSelectedRow(), 0);
            Controleur.deleteCandidat(id);
            this.unTableau.setDonnees(this.obtenirDonnees(""));
            this.lbNbCandidats.setText("Nombre de candidats: " + this.unTableau.getRowCount());
            
            // Réinitialisation
            this.textNom.setText("");
            this.textPrenom.setText("");
            this.textAge.setText("");
            this.textEmail.setText("");
            this.textNumero_Telephone.setText("");
            this.comboProfession.setSelectedIndex(0);
            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }
    }
}