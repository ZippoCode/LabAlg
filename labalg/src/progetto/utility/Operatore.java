package progetto.utility;

/**
 * 
 * @author Salvatore
 * @version 1.0.3
 */

/*
 * SINGLETON Questi sono gli unici operatori di un blocco
 */
public enum Operatore {

	ADDIZIONE, SOTTRAZIONE, DIVISIONE, MOLTIPLICAZIONE, NULLO;

	// Data una stringa ritorna l'operatore corrispondente
	public static Operatore getOperatore(String string) {
		string.trim();
		if (string.equalsIgnoreCase("addizione") || string.equals("+")) {
			return Operatore.ADDIZIONE;
		} else if (string.equalsIgnoreCase("sottrazione") || string.equals("-")) {
			return Operatore.SOTTRAZIONE;
		} else if (string.equalsIgnoreCase("moltiplicazione")
				|| string.equals("*")) {
			return Operatore.MOLTIPLICAZIONE;
		} else if (string.equalsIgnoreCase("divisione") || string.equals("/")) {
			return Operatore.DIVISIONE;
		} else if (string.equalsIgnoreCase("n"))
			return Operatore.NULLO;
		throw new IllegalArgumentException(string + " non e' un operatore");
	}

	// Dato un carattere ritorna un operatore
	public static Operatore getOperatore(char character) {
		if (character == '+') {
			return Operatore.ADDIZIONE;
		} else if (character == '-') {
			return Operatore.SOTTRAZIONE;
		} else if (character == '*' || character == 'x') {
			return Operatore.MOLTIPLICAZIONE;
		} else if (character == '/' || character == '÷') {
			return Operatore.DIVISIONE;
		} else if (character == 'n')
			return Operatore.NULLO;
		throw new IllegalArgumentException(character + " non e' un operatore");
	}

	public static String getStringaOperatore(Operatore operatore) {
		switch (operatore) {
		case ADDIZIONE:
			return "+";
		case SOTTRAZIONE:
			return "-";
		case MOLTIPLICAZIONE:
			return "x";
		case DIVISIONE:
			return "÷";
		case NULLO:
			return "";
		default:
			throw new RuntimeException("Operatore non presente");
		}
	}
}
