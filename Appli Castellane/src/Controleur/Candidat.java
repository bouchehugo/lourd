package Controleur;

public class Candidat {
    private int idCandidat;
    private String Nom;
    private String Prenom;
    private String Age;
    private String Email;
    private String Mdp;
    private String Type_User;
    private String Numero_Telephone;
    private String Profession;

    public Candidat(int idCandidat, String nom, String prenom, String age, String email, 
                   String mdp, String type_User, String numero_Telephone, String profession) {
        this.idCandidat = idCandidat;
        Nom = nom;
        Prenom = prenom;
        Age = age;
        Email = email;
        Mdp = mdp;
        Type_User = type_User;
        Numero_Telephone = numero_Telephone;
        Profession = profession;
    }

    public Candidat(String nom, String prenom, String age, String email, 
                   String mdp, String type_User, String numero_Telephone, String profession) {
        this(0, nom, prenom, age, email, mdp, type_User, numero_Telephone, profession);
    }

    // Getters et Setters
    public int getIdCandidat() { return idCandidat; }
    public void setIdCandidat(int idCandidat) { this.idCandidat = idCandidat; }
    public String getNom() { return Nom; }
    public void setNom(String nom) { Nom = nom; }
    public String getPrenom() { return Prenom; }
    public void setPrenom(String prenom) { Prenom = prenom; }
    public String getAge() { return Age; }
    public void setAge(String age) { Age = age; }
    public String getEmail() { return Email; }
    public void setEmail(String email) { Email = email; }
    public String getMdp() { return Mdp; }
    public void setMdp(String mdp) { Mdp = mdp; }
    public String getType_User() { return Type_User; }
    public void setType_User(String type_User) { Type_User = type_User; }
    public String getNumero_Telephone() { return Numero_Telephone; }
    public void setNumero_Telephone(String numero_Telephone) { Numero_Telephone = numero_Telephone; }
    public String getProfession() { return Profession; }
    public void setProfession(String profession) { Profession = profession; }
}