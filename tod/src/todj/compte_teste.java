package todj;

import static org.junit.Assert.*;

import org.junit.Test;

public class compte_teste {

	@Test
	public void test() {
		compte c= new compte(500);
		c.debite(200);
		assertTrue("debit� refus�",c.solde==300);
		
	}
	@Test
	public void teest1() {
		compte c= new compte(500);
		compte c1=new compte(1000);
		c.veraiment(500, c1);
		assertTrue("debit� refus�",c1.solde==0); //assertrue pr�dcate
		
	}
	
	@Test
	public void teest() {
		compte c= new compte(500);
		c.debite(-200);
		assertTrue("debit� refus�",c.solde==500);
		
	}
	
}
	


