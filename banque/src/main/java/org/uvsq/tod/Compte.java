package org.uvsq.tod;

public class Compte {
	
	
	double solde;


	public Compte(double solde) {
		this.solde=solde;
		
	}
	
	public void crediteSolde(double somme ) {
		if(somme>0)
		solde=solde+somme;
		
	}
	
	public void virement (double somme,Compte c) {
		if(somme>0 && solde-somme>0){
			solde=solde-somme;
			c.solde=0.00;
		}
	}
	
	public  void debiteSolde(double somme) {
		if(somme>0 && (solde-somme)>0)
		solde=solde-somme;
	}
	
	
	

}
