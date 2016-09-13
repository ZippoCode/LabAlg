package progetto.main;

import java.util.Scanner;

import progetto.builder.BuilderMappa;
import progetto.builder.Director;
import progetto.builder.Mappa;
import progetto.template_method.Kenken;
import progetto.utility.GestoreTesto;
import progetto.utility.GetStringa;

public class MainTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Scrivi la dimensione >>");
		String dim = sc.nextLine();
		String nome = GetStringa.getName(Integer.parseInt(dim));
		GestoreTesto gt = new GestoreTesto(nome);
		BuilderMappa bm = new BuilderMappa();
		Director dir = new Director(bm);
		dir.buildInsiemeBlocchi(gt);
		dir.creaGriglia(Integer.parseInt(dim));
		Mappa mappa = bm.getMappa();
		System.out.println(mappa);
		System.out.println("RISOLUZIONE...");
		Kenken kk = new Kenken(mappa);
		kk.risolvi();
		System.out.println(mappa.getSoluzione());
	}
}
