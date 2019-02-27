package org.studentco.access;

/**
 * Vérifie l'accès avec une carte (n° compte, code) / login (username, password)
 *
 */
public interface Access {
	
	/**
	 * @param account le n° du compte/username
	 * @param code le code/password associé
	 * @return true si le code correspond au compte, false sinon
	 */
	public boolean verify(String account, String code);
	
}