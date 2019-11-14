package todj;

public class compte {
double solde;
	
	public compte(double solde) {
		this.solde=solde;
		
	}
	public  void debite(double somme) {
		if(somme>0 && (solde-somme)>0)
		solde=solde-somme;
		
		
	}
	public void crid(double somme ) {
		if(somme>0)
		solde=solde+somme;
		
	}
	public void veraiment (double somme,compte c) {
		if(somme>0 && solde-somme>0){
			solde=solde-somme;
			c.solde=0.00;
		}
		
		
	}
	

}
