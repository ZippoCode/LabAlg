package progetto.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 
 * @author Salvatore
 * @version 1.0.3
 */
/**
 * Gestisce il testo contenente gli elementi di una file di testo. Ritorna molto
 * semplicemente un Token di una linea.
 */

public class GestoreTesto {

	public static final String EOF = "-1";
	private InputStream file = null;
	private BufferedReader br = null;
	private String linea = null;

	public GestoreTesto(String nomeFile) {
		file = this.getClass().getClassLoader().getResourceAsStream(nomeFile);
		br = new BufferedReader(new InputStreamReader(file));
	}

	/**
	 * 
	 * @return La stringa contenente i dati per la costruzione di un blocco
	 */
	public String getLinea() {
		try {
			linea = br.readLine();
		} catch (IOException ioe) {

		}
		if (linea != null)
			return linea;
		try {
			file.close();
			br.close();
		} catch (IOException e) {
		}
		return EOF;
	}

}
