package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.Toolkit;


public class HelpFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public HelpFrame() {
		setResizable(false);
		setTitle("Aide");
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(HelpFrame.class.getResource("/icon/help.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Le manual d'utilisation d'application ");
		label.setForeground(Color.RED);
		label.setFont(new Font("Segoe Script", Font.BOLD, 15));
		label.setBounds(139, 11, 316, 25);
		contentPane.add(label);
		
		JTextArea textArea = new JTextArea();
		textArea.setAlignmentY(Component.CENTER_ALIGNMENT);
		textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
		textArea.setToolTipText("");
		textArea.setText("         L'application qui est sur votre main permettant le codage des symboles d'une source\n d'information avec l'algorithme de HUFFMAN .\r\nles \u00E9tapes  suiv\u00E9es pour marcher l'application : \r\n        1- Stockez Les  symboles de la source dans un fichier texte (.txt).\r\n        2- Chargez le contenu  de fichier dans l'application (le menu Fichier->Ouvrir).\r\n        3- Appliquez l'algorithme de codage (le menu Compilee->Codage).\r\n        4- Enregister les r\u00E9sultats.\n\nPar : Mohamed OIALILI");
		textArea.setFont(new Font("Cambria", Font.PLAIN, 13));
		textArea.setEditable(false);
		textArea.setBounds(10, 47, 526, 162);
		contentPane.add(textArea);
	}
}
