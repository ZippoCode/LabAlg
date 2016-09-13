package progetto.builder;

import java.util.StringTokenizer;

import progetto.utility.GestoreTesto;
import progetto.utility.IPLinkedList;
import progetto.utility.InsiemePosizioni;
import progetto.utility.Operatore;
import progetto.utility.Posizione;

/**
 * 
 * @author Salvatore
 * @version 1.0.1
 */

/*
 * DIRECTOR
 * 
 * E' colui che gestisce la creazione di un oggetto di tipo Mappa
 */
public class Director {

	private BuilderMappa builder = null;

	public Director(BuilderMappa mappa) {
		this.builder = mappa;
	}

	public void buildInsiemeBlocchi(GestoreTesto gt) {
		String linea = gt.getLinea();
		while (linea != GestoreTesto.EOF) {
			StringTokenizer st = new StringTokenizer(linea);
			Operatore op = Operatore.getOperatore(st.nextToken());
			Integer ris = Integer.parseInt(st.nextToken());
			InsiemePosizioni ip = new IPLinkedList();
			while (st.hasMoreTokens()) {
				String s = st.nextToken();
				char x = s.charAt(0);
				char y = s.charAt(1);
				Posizione pos = new Posizione(Integer.parseInt(String.valueOf(x)), Integer.parseInt(String.valueOf(y)));
				ip.addLast(pos);
			}
			linea = gt.getLinea();
			builder.addBlocco(op, ris, ip);
		}
	}

	public void creaGriglia(int dim) {
		builder.buildGriglia(dim);
	}
}
