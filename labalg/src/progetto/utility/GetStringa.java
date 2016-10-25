package progetto.utility;

import java.util.Random;

/**
 * 
 * @author Salvatore
 * @version 1.0.0
 */
public class GetStringa {

	public static final int numTre = 5, numQuattro = 2, numCinque = 5, numSei = 4, numSette = 1, numOtto = 1,
			numNove = 2;
	private static int val = 0;
	private static Random random = new Random();

	public static String getName(int dimensione) {
		switch (dimensione) {
		case 3:
			val = random.nextInt(numTre) + 1;
			break;
		case 4:
			val = random.nextInt(numQuattro) + 1;
			break;
		case 5:
			val = random.nextInt(numCinque) + 1;
			break;
		case 6:
			val = random.nextInt(numSei) + 1;
			break;
		case 7:
			val = random.nextInt(numSette) + 1;
			break;
		case 8:
			val = random.nextInt(numOtto) + 1;
			break;
		default:
			val = random.nextInt(numNove) + 1;
			break;
		}
		return getStringa(dimensione, val);
	}

	public static String getStringa(int numero, int posizione) {
		return "file/" + numero + "x" + numero + "_" + String.valueOf(posizione);
	}

	public static String getStringaIcona(int numero, int posizione) {
		return "/icon/" + numero + "x" + numero + "_" + String.valueOf(posizione) + ".jpg";
	}

}
