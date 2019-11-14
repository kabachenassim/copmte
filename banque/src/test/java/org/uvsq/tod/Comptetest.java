package org.uvsq.tod;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Comptetest {

	@Test
	public void test() {
		Compte c= new Compte(500);
		c.debiteSolde(200);
		assertTrue("debite refusé",c.solde==300);
	}
	@Test
	public void teest1() {
		Compte c= new Compte(500);
		Compte c1=new Compte(1000);
		c.virement(500, c1);
		assertTrue("debité refusé",c1.solde==1000); 
		
	}
	
	@Test
	public void test2() {
		Compte c= new Compte(500);
		c.crediteSolde(200);
		assertTrue("debite refusé",c.solde==700);
	}
	
	
}
