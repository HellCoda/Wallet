package comptemuret;

import montableau.Tableaux;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Windows extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JMenuBar menubar = new JMenuBar();
	JButton miopen = new JButton("Ouvrir");
	JButton misave = new JButton("Sauvegarder");
	JButton mirelease = new JButton("Rafraichir");
	JButton miexport = new JButton("Exporter");

	JPanel panelhaut = new JPanel();
	JPanel panelbas = new JPanel();
	JLabel label_add = new JLabel("Revenu : ");
	public JTextPane jtextpane_add = new JTextPane();
	JButton boutton_add = new JButton("Valider");
	JLabel label_ppc = new JLabel("P.P.C : ");
	JTextPane jtextpane_ppc = new JTextPane();
	JLabel label_pl = new JLabel("P.L : ");
	JTextPane jtextpane_pl = new JTextPane();
	JLabel label_solde = new JLabel("Solde : ");
	JTextPane jtextpane_solde = new JTextPane();

	public String texte;
	private double somme;
	private double ppc;
	private double pl;
	private double solde;
	double vppc;
	double vpl;
	private double ppc_box;
	private double pl_box;

	JButton boutton_command = new JButton("Commander");

	Container container = new Container();
	// JTable tableau_bas = new JTable();
	public Tableaux tableau_bas;
	JPanel mainPain = new JPanel();
	JPanel tablePane = new JPanel();
	JPanel infoPane = new JPanel();
	JScrollPane pane = new JScrollPane();

	File file = new File("");

	ImagePanel imagePanel = new ImagePanel("img_icon\\BGPB1.jpg");
	ImageIcon icon = new ImageIcon("img_icon\\icon.png");
	Image image = icon.getImage();
	Image newImage = image.getScaledInstance(35, 35, Image.SCALE_DEFAULT);
	ImageIcon newIcon = new ImageIcon(newImage);

	ImagePanel imagePanel1 = new ImagePanel("img_icon\\BGPB.jpg");
	ImageIcon icon1 = new ImageIcon("img_icon\\icon.png");
	Image image1 = icon1.getImage();
	Image newImage1 = image1.getScaledInstance(35, 35, Image.SCALE_DEFAULT);
	ImageIcon newIcon1 = new ImageIcon(newImage1);

	Windows1 fenetre1;
	Windows1 table_command;
	Windows1 boutton_register;

	Windows2 fenetre2;
	Windows2 windows2;
	Windows2 GestionRevenu;
	Windows2 bouton_comfirmer;

	public Windows() {

		super();
		content();
		menubar();
		panelnord();
		panelbas();

		fenetre1 = new Windows1(this);
		fenetre1.setVisible(false);

		// fenetre2 = new Windows2(texte);
		// fenetre2.setVisible(false);

		somme = 0;
		ppc = 0;
		pl = 517.24;
		solde = 56.01;

		this.setVisible(true);
	}

	private void content() {

		setTitle("Compte Muret");
		setSize(780, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		setContentPane(imagePanel);
	}

	public void styleBouton(JButton bouton) {
		bouton.setBackground(Color.DARK_GRAY);
		bouton.setForeground(Color.WHITE);
		bouton.setFocusable(false);
	}

	public void stylelabel(JLabel lab) {
		lab.setFont(new Font("Calibri", Font.BOLD, 18));
	}

	public void styletextpane(JTextPane input) {
		input.setPreferredSize(new Dimension(125, 30));
		input.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		input.setFont(new Font("Calibri", Font.BOLD, 20));
		StyledDocument doc = input.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		input.setBackground(Color.GRAY);
		input.setForeground(Color.WHITE);
	}

	public static void custompopup(JFileChooser fileChooser) {

		Color mycolor = new Color(20, 175, 210);
		UIManager.put("OptionPane.background", mycolor);
		UIManager.put("Panel.background", mycolor);
		UIManager.put("OptionPane.messageForeground", Color.BLACK);
		UIManager.put("OptionPane.messageFont", new Font("Calibri", Font.BOLD, 16));
		UIManager.put("Button.background", Color.DARK_GRAY);
		UIManager.put("Button.foreground", Color.WHITE);
		UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

		SwingUtilities.updateComponentTreeUI(fileChooser);
	}

	private void menubar() {

		menubar.setBackground(Color.LIGHT_GRAY);

		menubar.add(miopen);
		styleBouton(miopen);

		menubar.add(misave);
		styleBouton(misave);

		menubar.add(miexport);
		styleBouton(miexport);

		menubar.add(mirelease);
		styleBouton(mirelease);

		this.setJMenuBar(menubar);

		miopen.addActionListener((ActionListener) this);
		misave.addActionListener((ActionListener) this);
		miexport.addActionListener((ActionListener) this);
		mirelease.addActionListener((ActionListener) this);

	}

	private void panelnord() {

		getContentPane().add(panelhaut, BorderLayout.NORTH);
		panelhaut.setLayout(new GridBagLayout());
		panelhaut.setOpaque(false);

		GridBagConstraints gbc1 = new GridBagConstraints();

		gbc1.insets = new Insets(25, 0, 10, 3);
		gbc1.gridx = 0;
		gbc1.gridy = 0;
		panelhaut.add(label_add, gbc1);
		stylelabel(label_add);

		gbc1.insets = new Insets(25, 3, 10, 6);
		gbc1.gridx = 1;
		gbc1.gridy = 0;
		panelhaut.add(jtextpane_add, gbc1);
		styletextpane(jtextpane_add);

		gbc1.insets = new Insets(25, 5, 10, 10);
		gbc1.gridx = 2;
		gbc1.gridy = 0;
		panelhaut.add(boutton_add, gbc1);
		styleBouton(boutton_add);
		boutton_add.addActionListener((ActionListener) this);

		gbc1.insets = new Insets(28, 20, 10, 1);
		gbc1.gridx = 3;
		gbc1.gridy = 0;
		panelhaut.add(label_ppc, gbc1);
		stylelabel(label_ppc);

		gbc1.insets = new Insets(25, 1, 10, 10);
		gbc1.gridx = 4;
		gbc1.gridy = 0;
		panelhaut.add(jtextpane_ppc, gbc1);
		styletextpane(jtextpane_ppc);

		gbc1.insets = new Insets(30, 20, 10, 1);
		gbc1.gridx = 5;
		gbc1.gridy = 0;
		panelhaut.add(label_pl, gbc1);
		stylelabel(label_pl);

		gbc1.insets = new Insets(25, 1, 10, 5);
		gbc1.gridx = 6;
		gbc1.gridy = 0;
		panelhaut.add(jtextpane_pl, gbc1);
		styletextpane(jtextpane_pl);

		gbc1.insets = new Insets(10, 0, 10, 0);
		gbc1.gridx = 0;
		gbc1.gridy = 1;
		panelhaut.add(label_solde, gbc1);
		stylelabel(label_solde);

		gbc1.insets = new Insets(10, 5, 10, 5);
		gbc1.gridx = 1;
		gbc1.gridy = 1;
		panelhaut.add(jtextpane_solde, gbc1);
		styletextpane(jtextpane_solde);

		gbc1.insets = new Insets(10, 5, 10, 5);
		gbc1.gridx = 6;
		gbc1.gridy = 1;
		panelhaut.add(boutton_command, gbc1);
		styleBouton(boutton_command);
		boutton_command.setPreferredSize(new Dimension(125, 30));
		boutton_command.addActionListener((ActionListener) this);

	}

	public void panelbas() {

		container = this.getContentPane();

		String[] columnNames = { " N° ", " Date ", " Intitulé ", " Monatant ", " Observations " };
		Object[][] rowData = new Object[21][5];

		DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		tableau_bas = new Tableaux(model);
		tableau_bas.setRowHeight(20);
		tableau_bas.getTableHeader().setBackground(Color.DARK_GRAY);
		tableau_bas.getTableHeader().setForeground(Color.white);
		tableau_bas.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 14));
		tableau_bas.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tableau_bas.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tableau_bas.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

		pane = new JScrollPane(tableau_bas);
		pane.setOpaque(false);
		pane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		pane.setBorder(BorderFactory.createLineBorder(Color.black, 3));

		tablePane = new JPanel(new GridLayout(2, 1));

		TableColumnModel colmdl = tableau_bas.getColumnModel();
		colmdl.getColumn(0).setPreferredWidth(35);
		colmdl.getColumn(1).setPreferredWidth(120);
		colmdl.getColumn(2).setPreferredWidth(190);
		colmdl.getColumn(3).setPreferredWidth(80);
		colmdl.getColumn(4).setPreferredWidth(300);

		infoPane = new JPanel();
		infoPane.setOpaque(false);
		tablePane.add(pane);
		tablePane.add(infoPane);
		tablePane.setPreferredSize(new Dimension(690, 850));
		tablePane.setOpaque(false);
		container.add(tablePane);
	}

	public void exporttab() {

		try {
			custompopup(new JFileChooser());

			JFileChooser fileChooser = new JFileChooser("C:\\Users\\Neptune512\\Desktop\\NickelApp");

			int result = fileChooser.showSaveDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();

				PrintWriter pw = new PrintWriter(file);
				DefaultTableModel model = (DefaultTableModel) tableau_bas.getModel();

				for (int i = 0; i < model.getRowCount(); i++) {

					for (int j = 0; j < model.getColumnCount(); j++) {

						Object value = model.getValueAt(i, j);

						pw.printf(value + ", ");
					}

					pw.println();
				}

				pw.close();
				JOptionPane.showMessageDialog(null, "Le tableaux à bien été exporter dans le fichier " + file.getName(),
						"Exportation Tableau", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(null,
					"Le fichier ReleverdeCompte.txt n'a pas été trouvé ou n'est pas accessible.", "Erreur",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void refreshapp() {

		Color mycolor = new Color(20, 175, 210);
		UIManager.put("OptionPane.background", mycolor);
		UIManager.put("Panel.background", mycolor);
		UIManager.put("OptionPane.messageForeground", Color.BLACK);
		UIManager.put("OptionPane.messageFont", new Font("Calibri", Font.BOLD, 16));
		UIManager.put("Button.background", Color.DARK_GRAY);
		UIManager.put("Button.foreground", Color.WHITE);
		UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

		int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous vider les données ?", "Confirmation",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, newIcon1);

		DefaultTableModel model = (DefaultTableModel) tableau_bas.getModel();

		if (reponse == JOptionPane.YES_OPTION) {

			jtextpane_add.setText("");
			jtextpane_ppc.setText("");
			jtextpane_pl.setText("");
			jtextpane_solde.setText("");

			for (int i = 0; i < model.getRowCount(); i++) {

				tableau_bas.setValueAt(null, i, 0);
				tableau_bas.setValueAt(null, i, 1);
				tableau_bas.setValueAt(null, i, 2);
				tableau_bas.setValueAt(null, i, 3);
				tableau_bas.setValueAt(null, i, 4);
			}

			model.fireTableDataChanged();
		}

		else {

			return;
		}
	}

	
	public JTable getTableau_bas() {
		return tableau_bas;
	}

	
	public Object[][] getDataFromTable(JTable table) {

		int nbLignes = table.getRowCount();
		int nbColonnes = table.getColumnCount();

		Object[][] data = new Object[nbLignes][nbColonnes];

		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbColonnes; j++) {

				Object element = table.getValueAt(i, j);
				data[i][j] = element;
			}
		}
		return data;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double nouveauSolde) {
		this.solde = nouveauSolde;
	}

	public double getPartieCivil() {
		return ppc;
	}

	public void setPatieCivil(double nouveauppc) {
		this.ppc = nouveauppc;
	}

	public double getPartieLiberable() {
		return pl;
	}

	public void setPartieLiberable(double nouveaupl) {
		this.pl = nouveaupl;
	}

	
	public void addtabrevenue() {

		DefaultTableModel model = (DefaultTableModel) tableau_bas.getModel();
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		Object value = model.getValueAt(0, 0);
		int num = 0;
		if (value instanceof Integer) {
			num = ((Integer) value).intValue();
		}

		if (somme <= 200) {
			Object[] data = new Object[5];
			data[0] = num + 1;
			data[1] = formater.format(now);
			data[2] = "Revenu";
			data[3] = somme + " \u20AC";
			data[4] = "P.P.C : " + 0 + " \u20AC | P.L : " + 0 + " \u20AC | Solde : "
					+ String.format("%.2f\u20AC", solde);

			model.insertRow(0, data);
			num--;
		} else {
			Object[] data = new Object[5];
			data[0] = num + 1;
			data[1] = formater.format(now);
			data[2] = "Revenu";
			data[3] = "+ " + somme + " \u20AC";
			data[4] = "P.P.C : " + String.format("%.2f\u20AC", ppc_box) + " \u20AC | P.L : "
					+ String.format("%.2f\u20AC", pl_box) + " \u20AC | Solde : " + String.format("%.2f\u20AC", solde);

			model.insertRow(0, data);
			num--;
		}
	}

	private void openwindows1() {

		fenetre1.setVisible(true);
		targetwin1(fenetre1);

	}

	private void openwindows2() {

		String text_add = jtextpane_add.getText();
		Windows2 fenetre2 = new Windows2(text_add,this);

		fenetre2.setVisible(true);
		targetwin2(fenetre2);

	}

	private void targetwin1(Windows1 fenetre1) {

		int x = this.getX();
		int y = this.getY();
		int x2 = x + 40;
		int y2 = y + 185;
		fenetre1.setLocation(x2, y2);
	}

	private void targetwin2(Windows2 fenetre2) {

		int x = this.getX();
		int y = this.getY();
		int x2 = x + 40;
		int y2 = y + 185;
		fenetre2.setLocation(x2, y2);
	}

	public void saveuser() {

		custompopup(new JFileChooser());

		JFileChooser fileChooser = new JFileChooser("C:\\Users\\Neptune512\\Desktop\\NickelApp");

		int result = fileChooser.showSaveDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {

			File file = fileChooser.getSelectedFile();
			try {

				FileOutputStream fos = new FileOutputStream(file);

				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(tableau_bas.getDataFromTable());

				oos.writeDouble(ppc);
				oos.writeDouble(pl);
				oos.writeDouble(solde);

				oos.close();
				fos.close();

				JOptionPane.showMessageDialog(null,
						"Les données ont été sauvegardées dans le fichier " + file.getName(), "Sauvegarde",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException ex) {

				JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la sauvegarde des données.",
						"Sauvegarde", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}

	}

	public void openuser() {

		custompopup(new JFileChooser());

		JFileChooser fileChooser = new JFileChooser("C:\\Users\\Neptune512\\Desktop\\NickelApp");

		int result = fileChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {

			File file = fileChooser.getSelectedFile();
			try {

				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object[][] data = (Object[][]) ois.readObject();

				ppc = ois.readDouble();
				pl = ois.readDouble();
				solde = ois.readDouble();

				ois.close();
				fis.close();

				tableau_bas.setDataToTable(data);

				jtextpane_add.setText(String.valueOf(""));
				jtextpane_ppc.setText(String.format("%.2f \u20AC", ppc));
				jtextpane_solde.setEditable(false);
				jtextpane_pl.setText(String.format("%.2f \u20AC", pl));
				jtextpane_pl.setEditable(false);
				jtextpane_solde.setText(String.format("%.2f \u20AC", solde));
				jtextpane_solde.setEditable(false);

				JOptionPane.showMessageDialog(null, "Les données ont été chargées depuis le fichier " + file.getName(),
						"Ouverture", JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException | ClassNotFoundException ex) {

				JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de l'ouverture des données.",
						"Ouverture", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// Gérer les actions des menus

		Object source = e.getSource();

		if (source == miexport) {
			exporttab();

		} else if (source == boutton_add) {

			openwindows2();

		} else if (source == boutton_command) {
			openwindows1();

		} else if (source == mirelease) {
			refreshapp();

		} else if (source == misave) {
			saveuser();

		} else if (source == miopen) {
			openuser();

		}

	}

}