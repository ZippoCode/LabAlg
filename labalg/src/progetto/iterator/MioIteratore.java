package progetto.iterator;

import progetto.utility.Posizione;

public interface MioIteratore {
	
	public Posizione getPrimaPosizione();

	public Posizione getPosizioneSuccessiva(Posizione posizione);
	
	public Posizione getUltimaPosizione();
}
