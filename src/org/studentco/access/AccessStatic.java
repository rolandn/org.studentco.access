package org.studentco.access;

/**
 * Vérifie un accès sur base d'une liste hardcodée
 *
 */
public class AccessStatic implements Access {
	
	/**
	 * les comptes/usernames connus  {"1234", "2345", "3456", "4567", "5678", "6789", "7890"}
	 */
	private String[] accounts = {"1", "4", "2", "7", "6", "5", "3"};
		
	/**
	 * Les codes/mots de passe correspondants
	 */
	private String[] codes = {"1", "4", "2", "7", "6", "5", "3"};
		
	public String[] getAccounts() {
		return this.accounts;
	}

	public void setAccounts(String[] accounts) {
		this.accounts = accounts;
	}

	public String[] getCodes() {
		return this.codes;
	}

	public void setCodes(String[] codes) {
		this.codes = codes;
	}


	/* (non-Javadoc)
	 * @see org.banco.access.Access#verify(java.lang.String, java.lang.String)
	 * for (int i=0;i<account.length();i++) {
			if(account.contains(account)) return true;
		}
		return true;
	 */

	// il faut réécrire cette méthode pour que le test passe au vert. Mettre "tue" tout court ça
	// ne va pas fonctionner car il faut plusieurs test : il test s'il y a des comptes, des codes
	// et si les comptes et les codes correspondent.
	@Override
	public boolean verify(String account, String code) {

		for (int i=0;i<account.length();i++) {
			if(account.equals(code)) return true;
		}
		return false;
	}

	
}
