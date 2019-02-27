package org.studentco.access;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.studentco.access.AccessStatic;
import org.studentco.util.Utils;

/**
 * Test Case pour le composant AccessStatic
 * La méthode à tester est verify
 */
public class AccessStaticTest {

	/**
	 * Le composant testé
	 */
	AccessStatic accessStatic;
	
	/**
	 * Un couple (login) valide
	 */
	String account;
	String code;
		
	@Before
	public void setUp() throws Exception {
		accessStatic = new AccessStatic();
		//Choisir aléatoirement un login (account + code) connu :
		int low = 0;
		int high = accessStatic.getAccounts().length-1;
		int i = Utils.randomInt(low, high);
		//
		String accountRandom = accessStatic.getAccounts()[i];
		String codeRandom = accessStatic.getCodes()[i];
		account = new String(accountRandom); 
		code = new String(codeRandom);
	}
	
	
	/**
	 * Vérifie que accessStatic.verify renvoie true si account et code forment un login connu
	 * Vérifie que renvoie false sinon.
	 */
	@Test
	public void testVerify() {
		boolean actual;
		//account et code forment un login connu : --> il faut que actuel = true
		actual = accessStatic.verify(account, code);
		assertTrue(actual);

		//account est connu et/ou le code ne correspond pas --> s'arranger pour que
		// dans verify, si on lui donne un mauvais couple account/code -> return false
		// le test réussi donc si actual = false !
		String y = Utils.generateString();
		actual = accessStatic.verify(account, y);
		assertFalse(actual);

		//le login n'est pas connu --> idem, il faut que actual retourne false
		String x = Utils.generateString();
		actual = accessStatic.verify(x, y);
		assertFalse(actual);
		//teste les valeurs limites
		int n = accessStatic.getAccounts().length;
		assertTrue(n == accessStatic.getCodes().length);
		account = new String(accessStatic.getAccounts()[0]);
		code = new String(accessStatic.getCodes()[0]);
		actual = accessStatic.verify(account, code);
		assertTrue(actual);
		account = new String(accessStatic.getAccounts()[n-1]);
		code = new String(accessStatic.getCodes()[n-1]);
		actual = accessStatic.verify(account, code);
		assertTrue(actual);
	}



}
