package org.studentco.access;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.studentco.access.Access;
import org.studentco.access.AccessStatic;
import org.studentco.access.Counter;
import org.studentco.exception.StudentcoException;
import org.studentco.util.Utils;

/**
 * Cette classe teste Counter dans le cas où le nbr de tentatives permises =3 (nbr. d'accès autorisés avant carte avalée)
 *
 */
public class CounterTest {

	Access access;
	Counter counter;
	int nbAccess = 3;
	//
	String account;
	String code;
	
	@Before
	public void setUp() throws Exception {
		access = new AccessStatic();
		counter = new Counter(access, nbAccess);
	}

	/**
	 * Cas ou la première tentative a réussi
	 */
	@Test
	public void testValidate1() {
		boolean actual;
		try {
			newAccessTrue();
			actual = counter.enter(account, code);
			assertTrue(actual);
		}
		catch (StudentcoException e) {
			fail("Problème lors de la Validation 1");
		}
	}
	
	/**
	 * Cas ou la 2ème tentative a réussi
	 */
	@Test
	public void testValidate2() {
		boolean actual;
		try {
			newAccessFalse();
			actual = counter.enter(account, code);
			assertFalse(actual);
			newAccessTrue();
			actual = counter.enter(account, code);
			assertTrue(actual);
		}
		catch (StudentcoException e) {
			fail("Problème lors de la Validation 2");
		}
	}
	
	/**
	 * Cas ou la 3ème tentative a réussi
	 */
	@Test
	public void testValidate3() {
		boolean actual;
		try {
			newAccessFalse();
			actual = counter.enter(account, code);
			assertFalse(actual);
			newAccessFalse();
			actual = counter.enter(account, code);
			assertFalse(actual);
			newAccessTrue();
			actual = counter.enter(account, code);
			assertTrue(actual);
		}
		catch (StudentcoException e) {
			fail("Problème lors de la Validation 3");
		}
	}
	
	/**
	 * Cas ou la 3ème tentative n'a pas réussi
	 */
	@Test
	public void testValidate3b() {
		boolean actual;
		try {
			newAccessFalse();
			actual = counter.enter(account, code);
			assertFalse(actual);
			newAccessFalse();
			actual = counter.enter(account, code);
			assertFalse(actual);
			newAccessFalse();
			actual = counter.enter(account, code);
			fail("Succès de la méthode : les 3 essais sont consommés et raté.");
		}
		catch (StudentcoException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * 4ème tentative et suivantes : carte avalée
	 */
	@Test
	public void testValidate4() {
		boolean actual;
		try {
			newAccessFalse();
			actual = counter.enter(account, code);
			assertFalse(actual);
			newAccessFalse();
			actual = counter.enter(account, code);
			assertFalse(actual);
			newAccessFalse();
			actual = counter.enter(account, code);
			fail("should be Carte avalée");
		}
		catch (StudentcoException e) {
			assertTrue(true);
		}
		try {
			newAccessTrue();
			actual = counter.enter(account, code);
			fail("should be Carte avalée");
		} catch (StudentcoException e) {
			assertTrue(true);
		}
		try {
			newAccessFalse();
			actual = counter.enter(account, code);
			fail("should be Carte avalée");
		} catch (StudentcoException e) {
			assertTrue(true);
		}
		
	}
	
	/**
	 * Teste si une tentative réussie réinitialise le compteur
	 */
	@Test
	public void testValidate5() {
		boolean actual;
		try {
			//2 essais ratés
			newAccessFalse();
			actual = counter.enter(account, code);
			assertFalse(actual);
			newAccessFalse();
			actual = counter.enter(account, code);
			assertFalse(actual);
			//3ème essai réussi
			newAccessTrue();
			actual = counter.enter(account, code);
			assertTrue(actual);
			//Le compteur devrait être réinitialisé : on réessaye 1x
			newAccessTrue();
			actual = counter.enter(account, code);
			assertTrue(actual);
			//Le compteur devrait être réinitialisé : on réessaye 3x avec la 3ème ok
			newAccessFalse();
			actual = counter.enter(account, code);
			assertFalse(actual);
			newAccessFalse();
			actual = counter.enter(account, code);
			assertFalse(actual);
			newAccessTrue();
			actual = counter.enter(account, code);
			assertTrue(actual);
		}
		catch (StudentcoException e) {
			fail();
		}
	}
	
	
	private void newAccessTrue() {
		//
		int low = 0;
		int high = ((AccessStatic)access).getAccounts().length-1;
		int i = Utils.randomInt(low, high);
		//
		String accountRandom = ((AccessStatic)access).getAccounts()[i];
		String codeRandom = ((AccessStatic)access).getCodes()[i];
		account = new String(accountRandom); 
		code = new String(codeRandom);
	}
	
	private void newAccessFalse() {
		//
		account = Utils.generateString();
		code = Utils.generateString();
	}

}
