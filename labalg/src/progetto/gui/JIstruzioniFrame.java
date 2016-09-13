package progetto.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.omg.CORBA.TypeCodePackage.BadKind;

/**
 * JIstruzioniFrame e' un semplice pannello che visualizza le istruzioni per il
 * gioco del Kenken.
 * 
 * @author Salvatore
 * @version 1.0.6
 */
public class JIstruzioniFrame extends JFrame {
	private static final long serialVersionUID = -2878373186759475971L;
	private JTextArea testo = null;
	private JScrollPane jScrollPanel = null;
	private JButton esci = null;
	private JPanel pannelloTesto = null;
	private JPanel pannelloBottone = null;
	private int dimL = 300, dimW = 400;
	private Dimension dim = null;

	public JIstruzioniFrame() {

		testo = new JTextArea();
		esci = new JButton();
		pannelloTesto = new JPanel();
		pannelloTesto.setLayout(new BorderLayout());
		pannelloBottone = new JPanel();
		pannelloBottone.add(Box.createHorizontalGlue());
		testo.setColumns(20);
		testo.setLineWrap(true);
		testo.setRows(5);
		testo.setWrapStyleWord(true);
		testo.setEditable(false);
		testo.setName("testo");
		testo.setBackground(Color.WHITE);
		testo.setFont(new Font("Ariel", Font.ROMAN_BASELINE, 15));
		try {
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("info/Istruzioni");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String linea = br.readLine();
			while (linea != null && linea.length() != 0) {
				testo.append(linea + '\n');
				linea = br.readLine();
			}
		} catch (IOException ioe) {
		}
		jScrollPanel = new JScrollPane(testo);
		jScrollPanel.setName("areatesto");
		testo.select(0, 0);
		pannelloTesto.add(jScrollPanel, BorderLayout.CENTER);
		esci.setText("Esci");
		esci.setName("fineIstruzioni");
		esci.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		pannelloBottone.add(esci);
		Container container = getContentPane();
		container.add(pannelloTesto, BorderLayout.CENTER);
		container.add(pannelloBottone, BorderLayout.PAGE_END);
		setTitle("Istruzioni Kenken");
		dim = new Dimension(dimW, dimL);
		setSize(dim);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(500, 200);
		setResizable(true);
		setVisible(true);
	}
}
