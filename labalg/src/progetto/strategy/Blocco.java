package progetto.strategy;

import java.util.Iterator;

import progetto.utility.Cella;
import progetto.utility.Insieme;
import progetto.utility.InsiemeLL;
import progetto.utility.Operatore;
import progetto.utility.Posizione;

/**
 * 
 * @author Salvatore
 * @versione 1.0.7
 * 
 */

/*
 *
 * NEL PATTERN RAPPRESENTA IL CONTEXT
 * 
 * Info: Gestisce un blocco. Ha il compito di scrivere e rimuovere il numero in
 * cella fornire informazioni sugli elementi scritti e sullo stato del blocco
 * 
 */
public class Blocco {

	private Insieme<Cella> celle = new InsiemeLL<Cella>();
	private Tipo strategia = null;
	private int risultato;
	private int risultatoCorrente = 0;
	private int numeroCelleScritte = 0;
	private Operatore operatore = null;

	public Blocco(Operatore operatore, int risultato, InsiemeLL<Posizione> posizioni) {
		if (risultato <= 0) {
			throw new IllegalArgumentException(risultato + " deve essere maggione di 0");
		}
		this.risultato = risultato;
		this.operatore = operatore;
		strategia = elaboraStrategia(operatore);
		for (Posizione posizione : posizioni.getListaPosizioni()) {
			celle.addLast(new Cella(posizione));
		}
	}

	public Blocco(Blocco blocco) {
		this.risultato = blocco.risultato;
		this.operatore = blocco.operatore;
		this.strategia = blocco.strategia;
		celle = new InsiemeLL<Cella>();
		for (Cella cella : blocco.celle) {
			celle.addLast(new Cella(cella));
		}
	}// costruttore di copia

	// metodo privato per elaborare la strategia
	private Tipo elaboraStrategia(Operatore operatore) {
		switch (operatore) {
		case ADDIZIONE:
			return new Addizione();
		case SOTTRAZIONE:
			return new Sottrazione();
		case MOLTIPLICAZIONE:
			return new Moltiplicazione();
		case DIVISIONE:
			return new Divisione();
		default:
			return new Nullo();
		}
	}// elaboraStrategia

	// ritorna la Cella corrispondente ad una determinata posizione
	public Cella getCella(Posizione posizione) {
		for (Cella cella : celle) {
			if (cella.getPosizione().equals(posizione)) {
				return cella;
			}
		}
		return null;
	}

	public int getRisultato() {
		return risultato;
	}

	public int getSize() {
		return celle.dimensione();
	}

	public Cella getCella(int riga, int colonna) {
		return getCella(new Posizione(riga, colonna));
	}

	public Tipo getTipo() {
		return strategia;
	}

	public String getOperatore() {
		return Operatore.getStringaOperatore(operatore);
	}

	public void scriviNumero(Posizione posizione, int numero) {
		if (numero <= 0) {
			throw new IllegalArgumentException(numero + " non superiore allo zero.");
		}
		Cella cella = getCella(posizione);
		if (cella != null) {
			if (cella.getValore() == 0) {
				numeroCelleScritte++;
			}
			cella.setValore(numero);
			risultatoCorrente = strategia.aggiornaRisultato(celle);
		} else {
			throw new IllegalArgumentException("Posizione non presente nel blocco.");
		}
	}

	public void rimuoviNumero(Posizione posizione) {
		Cella cella = getCella(posizione);
		if (cella != null) {
			if (cella.getValore() != 0) {
				numeroCelleScritte--;
			}
			cella.setValore(0);
			risultatoCorrente = strategia.aggiornaRisultato(celle);
		} else {
			throw new IllegalArgumentException("Posizione non presente nel blocco.");
		}

	}

	public boolean contienePosizione(Posizione posizione) {
		Cella cella = getCella(posizione);
		return cella != null;
	}

	public int getValoreCella(Posizione posizione) {
		Cella cella = getCella(posizione);
		if (cella != null) {
			return cella.getValore();
		} else {
			throw new RuntimeException("Posizione non trovata.");
		}
	}

	public boolean bloccoCompleto() {
		return risultato == risultatoCorrente && bloccoSaturo();
	}

	public boolean bloccoSaturo() {
		return numeroCelleScritte == celle.dimensione();
	}

	public boolean quasiSaturo() {
		return numeroCelleScritte == celle.dimensione() - 1;
	}

	public boolean completaBlocco(Posizione posizione, int numero) {
		scriviNumero(posizione, numero);
		boolean flag = bloccoCompleto();
		rimuoviNumero(posizione);
		return flag;
	}

	public Insieme<Cella> getListaCelle() {
		return celle;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Operatore: ").append(strategia.getClass().getSimpleName());
		sb.append('\n');
		sb.append("Risultato: ").append(risultato).append('\n');
		sb.append("Celle: ");
		Iterator<Cella> it = celle.iterator();
		while (it.hasNext()) {
			Cella c = it.next();
			sb.append(c);
			if (it.hasNext()) {
				sb.append(" - ");
			}
		}
		return sb.toString();
	}
}
