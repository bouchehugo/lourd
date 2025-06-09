package Controleur;

import Modele.Modele;
import Vue.VueConnexion;
import Vue.VueGenerale;

public class PPEAUTO {

	
	
	
	private static VueConnexion uneVueConnexion ; 
	private static VueGenerale uneVueGenerale ; 
	
	public static void main(String[] args) {
		
		uneVueConnexion = new VueConnexion(); 

	}

	public static void creerVueGenerale (boolean action) {
		if (action == true) {
			uneVueGenerale = new VueGenerale(); 
			uneVueGenerale.setVisible(true);
		}else {
			uneVueGenerale.dispose();
		}
	}
	public static void rendreVisible (boolean action) {
		uneVueConnexion.setVisible(action);
	}
}
	