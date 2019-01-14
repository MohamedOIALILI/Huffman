package app;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Dimension;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.Toolkit;

public class ApplicationFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String output;  
	private JTable dictionary;
	 private JTextArea textResult;
  	 private Stream inOutput;
	 private Huffman huffmanEncoding;
	 private JToolBar toolBar;
	 private JButton openButton;
	 private JButton saveButton;
	 private JButton printButton;
	 private JButton codingButton;
	 private JPanel panel;
	 private JSplitPane splitPane;
	 private JTabbedPane tabbedPane;
	 String [] title={"Symbol","code"};
	 private JMenuItem encoding;
	 private JScrollPane scrollPane_1;
	 private JTextPane textToCoding;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationFrame frame = new ApplicationFrame();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ApplicationFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ApplicationFrame.class.getResource("/icon/title.PNG")));
		inOutput=new Stream();
		huffmanEncoding=new Huffman();
		setTitle("Le codage de HUFFMAN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 646);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu file = new JMenu("Fichier");
		file.setMnemonic('F');
		menuBar.add(file);
		
		JMenuItem open = new JMenuItem("Ouvrir");
		open.addActionListener(new Open());
		open.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/icon/open.png")));
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		file.add(open);
		
		JMenuItem save = new JMenuItem("Enregistrer");
		save.addActionListener(new Save());
		save.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/icon/save.png")));
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		file.add(save);
		
		JMenuItem print = new JMenuItem("Imprimer");
		print.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/icon/print.png")));
		print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		file.add(print);
		
		JSeparator separator = new JSeparator();
		file.add(separator);
		
		JMenuItem exit = new JMenuItem("Quitter");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exit.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/icon/exit.png")));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		file.add(exit);
		
		JMenu run = new JMenu("Compiler");
		run.setMnemonic('c');
		menuBar.add(run);
		
		encoding = new JMenuItem("Codage");
		encoding.addActionListener(new Encoding());
		encoding.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/icon/run.png")));
		run.add(encoding);
		
		JMenu help = new JMenu("Aide");
		help.setMnemonic('a');
		menuBar.add(help);
		
		JMenuItem manualUser = new JMenuItem("Manual d'utilisateur");
		manualUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HelpFrame f=new HelpFrame();
				f.setVisible(true);
				f.setLocationRelativeTo(null);
			}
		});
		manualUser.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/icon/help.png")));
		help.add(manualUser);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		openButton = new JButton("");
		openButton.setToolTipText("Ouvrir");
		openButton.addActionListener(new Open());
		openButton.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/icon/open.png")));
		toolBar.add(openButton);
		
		saveButton = new JButton("");
		saveButton.setToolTipText("Enregistrer");
		saveButton.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/icon/save.png")));
		saveButton.addActionListener(new Save());
		toolBar.add(saveButton);
		
		printButton = new JButton("");
		printButton.addActionListener(new Printing());
		printButton.setToolTipText("Imprimer");
		printButton.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/icon/print.png")));
		toolBar.add(printButton);
		
		codingButton = new JButton("");
		codingButton.setToolTipText("Coder");
		codingButton.addActionListener(new Encoding());
		codingButton.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/icon/run.png")));
		toolBar.add(codingButton);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(500, 300));
		panel.setSize(new Dimension(250,250));
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		textResult=new JTextArea();
		textResult.setToolTipText("Statistique sur le codage");
		textResult.setFont(new Font("Cambria", Font.BOLD, 14));
		
		tabbedPane = new JTabbedPane();
		tabbedPane.add("Statistique sur le codage", new JScrollPane( textResult));
		panel.add(tabbedPane, BorderLayout.CENTER);
		//*
		splitPane = new JSplitPane();
		splitPane.setDividerSize(15);
		splitPane.setMinimumSize(new Dimension(194, 32));
		
		splitPane.setOneTouchExpandable(true);
		getContentPane().add(splitPane, BorderLayout.CENTER);		
		//*
		  Object[][] data = {	{"", ""},
				
				};
		print.addActionListener(new Printing());
		
		dictionary = new JTable(new Model( data, title));			
		JScrollPane scrollPane = new JScrollPane( dictionary);		
		scrollPane.setToolTipText("Symboles cod\u00E9s");
		splitPane.setRightComponent(scrollPane);
		
		scrollPane_1 = new JScrollPane();
		splitPane.setLeftComponent(scrollPane_1);
		
		textToCoding = new JTextPane();
		textToCoding.setToolTipText("sortie de la source");
		textToCoding.setFont(new Font("Cambria", Font.PLAIN, 14));
		textToCoding.setEditable(false);
		scrollPane_1.setViewportView(textToCoding);
		splitPane.setDividerLocation(300);
		//*/
	}
	private void ViewHuffmanEncoding(){
		java.util.List<Symbol> list=inOutput.getSourceCoding();
		((Model)dictionary.getModel()).removeRow(0);       
		for(int i=0;i<list.size();i++){
			if(list.get(i).getAlphabet().equals("\n")){
				Object [] raw=new Object[]{"saut de ligne",list.get(i).getCode()};
				((Model)dictionary.getModel()).addRow(raw);	    
			}
			else{
				Object [] raw=new Object[]{list.get(i).getAlphabet(),list.get(i).getCode()};
				((Model)dictionary.getModel()).addRow(raw);	    	
			}
			
		}		
	}
	private class Open implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			textToCoding.setText("");				
			codingButton.setEnabled(true);
			encoding.setEnabled(true);
			
			JFileChooser openFileDialog=new JFileChooser();
			 openFileDialog.checkImage(null, null);
			 openFileDialog.setFileFilter(new FiltringText());			  
			 if (openFileDialog.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){			 
			  if(openFileDialog.getSelectedFile()!=null){
					String file = openFileDialog.getSelectedFile().getAbsolutePath();
			        inOutput.inputFile(file);	
			        textToCoding.setText(inOutput.getSourceSymbol());
			   }
			   }
			
			SwingWorker<?, ?> sw=new SwingWorker<Object, Object>(){

				@Override
				protected Object doInBackground() throws Exception {
					// TODO Auto-generated method stub
					inOutput.symbolExtraction();
					return null;
				}
				 
			 };
			 sw.execute();			 
			 	 
			 
		}
		
	}
	private class Save implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			 JFileChooser saveFileDialog=new JFileChooser();
			 saveFileDialog.checkImage(null, null);
			 saveFileDialog.setFileFilter(new FiltringText());			  
			 if (saveFileDialog.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){			 
			  if(saveFileDialog.getSelectedFile()!=null){
					String file = saveFileDialog.getSelectedFile().getAbsolutePath();
			        inOutput.outputFile(file,output);
			   }
			   }
		}
		
	}
	private class Encoding implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			huffmanEncoding.creatHuffamTree(inOutput.clone());
			huffmanEncoding.Encoding(inOutput);
			ViewHuffmanEncoding();
			huffmanEncoding.statisticCode(inOutput.getSourceCoding());
			String str="\tStatistique sur le codage de HUFFMAN:\n\t------------------------------------------------\n\n\n L'entropie :"+huffmanEncoding.getCodeEntropy()+" bit/symbol\n\n Longueur moyenne :"+huffmanEncoding.getCodeLength()+" bit/symbol\n\n L'efficacitée :"+huffmanEncoding.getCodeEfficiency()+"%";
			textResult.setText(str);
			output="\t\t les codes des symboles \t\t\n\t\t ----------------------\n\n"; 
			for(int i=0;i<inOutput.getSourceCoding().size();i++){
				
			//*
				if(inOutput.getSourceCoding().get(i).getAlphabet().equals("\n")){
					output+="saut de ligne"+" --> "+inOutput.getSourceCoding().get(i).getCode()+"\n";
				}
				else{					
				output+=inOutput.getSourceCoding().get(i).getAlphabet()+" --> "+inOutput.getSourceCoding().get(i).getCode()+"\n";
				}
				//*/
			}
			output+="\tStatistique sur le codage de HUFFMAN:\n\t-------------------------------------\n\n\n L'entropie :"+huffmanEncoding.getCodeEntropy()+" bit/symbol\n\n Longueur moyenne :"+huffmanEncoding.getCodeLength()+" bit/symbol\n\n L'efficacitée :"+huffmanEncoding.getCodeEfficiency()+"%";
			codingButton.setEnabled(false);
			encoding.setEnabled(false);
		}                 
		
	}
	private class Printing implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String str=textResult.getText();
			textResult.setText(output);
			try {
				textResult.print();
			} catch (PrinterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			textResult.setText(str);
		}                 
		
	}
	private class FiltringText extends FileFilter{

		 private String extension = ".txt", description = "Text Files";
			
			public boolean accept(File file){
				return (file.isDirectory() || file.getName().endsWith(this.extension));
			}
			
			public String getDescription(){
				return   this.description+ " (*" +this.extension+")" ;
			}	
		
	}
	public class Model extends AbstractTableModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Object[][] data;
		private String[] title;
		/**
		 * Constructeur
		 * @param data
		 * @param title
		 */
		public Model(Object[][] data, String[] title){
			this.data = data;
			this.title = title;
		}
		/**
		* Retourne le titre de la colonne à l'indice spécifé
		*/
		public String getColumnName(int col) {
		  return this.title[col];
		}

		/**
		 * Retourne le nombre de colonnes
		 */
		public int getColumnCount() {
			return this.title.length;
		}
		
		/**
		 * Retourne le nombre de lignes
		 */
		public int getRowCount() {
			return this.data.length;
		}
		
		/**
		 * Retourne la valeur à l'emplacement spécifié
		 */
		public Object getValueAt(int row, int col) {
			return this.data[row][col];
		}
		
		/**
		 * Défini la valeur à l'emplacement spécifié
		 */
		public void setValueAt(Object value, int row, int col) {
			//On interdit la modification sur certaine colonne !
			if(!this.getColumnName(col).equals("Age") && !this.getColumnName(col).equals("Suppression"))
				this.data[row][col] = value;
		}
		
		public Class<? extends Object> getColumnClass(int col){
			return this.data[0][col].getClass();
		}

		public void removeRow(int position){
			
			int indice = 0, indice2 = 0, nbRow = this.getRowCount()-1, nbCol = this.getColumnCount();
			Object temp[][] = new Object[nbRow][nbCol];
			
			for(Object[] value : this.data){
				if(indice != position){
					temp[indice2++] = value;
				}
				indice++;
			}
			this.data = temp;
			temp = null;

			this.fireTableDataChanged();
		}
		public void removeAll(){
			for(int i=0;i<this.getRowCount();i++){
				remove(i);
			}
		}
		
		public void addRow(Object[] data){
			int indice = 0, nbRow = this.getRowCount(), nbCol = this.getColumnCount();
			
			Object temp[][] = this.data;
			this.data = new Object[nbRow+1][nbCol];
			
			for(Object[] value : temp)
				this.data[indice++] = value;
			
			this.data[indice] = data;
			temp = null;
			this.fireTableDataChanged();
		}
		
		public boolean isCellEditable(int row, int col){
			return true;
		}
	}
}
