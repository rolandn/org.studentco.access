package org.studentco.access;

import org.studentco.exception.StudentcoException;

/**
 * Vérifie l'accès avec plusieurs essais possibles. Exemple : carte avalée après n essais infructueux
 * Chaque tentative est réalisée avec un objet Access fourni
 */
public class Counter {

	private static final String GLOUP="Carte avalée";
	
	/**
	 * L'objet Access utilisé pour vérifier une tentative
	 */
	private Access access;
	
	/**
	 * Nombre max de tentatives permises
	 */
	private int maxAttempt;
	
	/**
	 * Nombre de tentatives restant
	 */
	private int nbAttempt;
	
	/**
	 * Constructeur
	 * @param access objet qui vérifie chaque tentative
	 * @param maxAttempt nombre maximum de tentatives permises
	 */
	public Counter(Access access, int maxAttempt) {
		this.access = access;
		this.nbAttempt = maxAttempt;
		this.maxAttempt = maxAttempt;
	}
	
	/**
	 * @param account le compte (ou username pour 1 login)
	 * @param code le code associé au compte (ou password pour 1 login)
	 * @return true, si accès autorisé, false sinon
	 * @throws StudentcoException si la carte est avalée, c-à-d nombre de tentatives infructueuses atteint maxAttempt
	 * Remarque : dans l'esprit d'un login, on demande en plus que :
	 * 1. l'état soit toujours carte avalée pour toutes tentatives après maxAttempt même fructueuses
	 * 2. Une tentative réussie réinitialise le compteur
	 */
	public boolean enter(String account, String code) throws StudentcoException {



		throw new StudentcoException(GLOUP);
	}

	// il faut réécrire la méthode pour que le test passe au vert
	
}
