package comptemuret;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Windows1 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	Windows windows;
	private Windows tableau_bas;
	// public Windows solde;
	public double somme_command;
	Windows insertDataFromTable;

	JPanel panelhaut2 = new JPanel();
	JLabel labeL2 = new JLabel("Bon de Commande");

	JPanel panelcenter2 = new JPanel();
	JTable table_command = new JTable();
	DefaultTableModel model = new DefaultTableModel();

	JPanel panelbas2 = new JPanel();
	JButton boutton_frais = new JButton("Frais Fixes");
	JButton boutton_register = new JButton("Enregister");
	JButton boutton_recredit = new JButton("Recréditer");
	String[] datatab = new String[5];

	Object[] data = new Object[5];

	ImageIcon icon = new ImageIcon("img_icon\\icon.png");
	Image image = icon.getImage();
	Image newImage = image.getScaledInstance(35, 35, Image.SCALE_DEFAULT);
	ImageIcon newIcon = new ImageIcon(newImage);

	ImageIcon icon1 = new ImageIcon("img_icon\\icon.png");
	Image image1 = icon1.getImage();
	Image newImage1 = image1.getScaledInstance(35, 35, Image.SCALE_DEFAULT);
	ImageIcon newIcon1 = new ImageIcon(newImage1);

	public Windows1(Windows tableau_bas) {

		super();
		this.tableau_bas = tableau_bas;
		Content2();
		panelcommand();
		paneltable();
		panelbutton();
		check_fraisfixes();
		check_command();
		check_command2();

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});

	}

	private void Content2() {

		setTitle("Commande");
		setSize(700, 430);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);

	}

	public void stylelabel2(JLabel lab) {
		lab.setFont(new Font("Calibri", Font.BOLD, 20));
	}

	public void panelcommand() {

		getContentPane().add(panelhaut2, BorderLayout.NORTH);
		panelhaut2.setLayout(new GridBagLayout());
		Color mycolor = new Color(20, 175, 210);
		panelhaut2.setBackground(mycolor);

		GridBagConstraints gbc1 = new GridBagConstraints();

		gbc1.insets = new Insets(20, 0, 10, 3);
		gbc1.gridx = 0;
		gbc1.gridy = 0;
		panelhaut2.add(labeL2, gbc1);
		stylelabel2(labeL2);
	}

	public void styletextpane(JTextPane input) {
		// input.setPreferredSize(new Dimension(125, 30));
		input.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		input.setFont(new Font("Calibri", Font.BOLD, 16));
		StyledDocument doc = input.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		input.setBackground(Color.WHITE);
		input.setForeground(Color.BLACK);
	}

	public void paneltable() {

		Object[] columnNames = { "N°", "Date", "Intitulé", "Montant", "Observations" };
		Object[][] rowData = new Object[14][5];

		model = new DefaultTableModel(rowData, columnNames);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		table_command = new JTable(model);

		for (int i = 0; i < table_command.getRowCount(); i++) {
			for (int j = 0; j < table_command.getColumnCount(); j++) {
				Object cell = table_command.getValueAt(i, j);

			}
		}

		for (int i = 0; i < 13; i++) {
			model.setValueAt(i + 1, i, 0);
		}

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		String sDate = now.format(formater);
		for (int i = 0; i < table_command.getRowCount(); i++) {

			for (int j = 0; j < table_command.getColumnCount(); j++) {

				if (j == 1) {

					model.setValueAt(sDate, i, j);
				}
			}
		}

		table_command.setRowHeight(21);
		table_command.getTableHeader().setBackground(Color.DARK_GRAY);
		table_command.getTableHeader().setForeground(Color.white);
		table_command.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 14));
		table_command.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table_command.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table_command.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

		JPanel tablePanel = new JPanel(new BorderLayout());

		tablePanel.add(table_command.getTableHeader(), BorderLayout.NORTH);
		tablePanel.add(table_command, BorderLayout.CENTER);
		tablePanel.setOpaque(false);
		tablePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		tablePanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		table_command.setCellSelectionEnabled(true);

		getContentPane().add(tablePanel, BorderLayout.CENTER);

		TableColumnModel colModel = table_command.getColumnModel();
		colModel.getColumn(0).setPreferredWidth(35);
		colModel.getColumn(1).setPreferredWidth(120);
		colModel.getColumn(2).setPreferredWidth(220);
		colModel.getColumn(3).setPreferredWidth(80);
		colModel.getColumn(4).setPreferredWidth(270);

		table_command.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {

					int[] rows = table_command.getSelectedRows();
					int[] cols = table_command.getSelectedColumns();

					for (int row : rows) {
						for (int col : cols) {

							model.setValueAt("", row, col);
						}
					}
				}
			}
		});

		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem effacer = new JMenuItem("Effacer");

		popupMenu.add(effacer);
		popupMenu.setBorder(new LineBorder(Color.DARK_GRAY));
		effacer.setBackground(Color.DARK_GRAY);
		effacer.setForeground(Color.WHITE);
		effacer.setBorder(null);

		table_command.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

				if (e.isPopupTrigger()) {
					popupMenu.show(table_command, e.getX(), e.getY());
				}
			}
		});

		effacer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int[] rows = table_command.getSelectedRows();
				int[] cols = table_command.getSelectedColumns();

				for (int row : rows) {
					for (int col : cols) {
						model.setValueAt("", row, col);
					}
				}
			}
		});
	}

	public void styleBouton2(JButton bouton) {
		bouton.setBackground(Color.DARK_GRAY);
		bouton.setForeground(Color.WHITE);
		bouton.setFocusable(false);
	}

	public void panelbutton() {

		getContentPane().add(panelbas2, BorderLayout.SOUTH);

		panelbas2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelbas2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		Color mycolor1 = new Color(20, 175, 210);
		panelbas2.setBackground(mycolor1);

		GridBagConstraints gbc1 = new GridBagConstraints();

		gbc1.insets = new Insets(25, 5, 10, 10);
		gbc1.gridx = 2;
		gbc1.gridy = 1;
		panelbas2.add(boutton_recredit, gbc1);
		styleBouton2(boutton_recredit);
		// boutton_recredit.addActionListener((ActionListener) this);

		gbc1.insets = new Insets(25, 5, 10, 10);
		gbc1.gridx = 2;
		gbc1.gridy = 1;
		panelbas2.add(boutton_frais, gbc1);
		styleBouton2(boutton_frais);
		// boutton_frais.addActionListener((ActionListener) this);

		gbc1.insets = new Insets(25, 5, 10, 10);
		gbc1.gridx = 2;
		gbc1.gridy = 1;
		panelbas2.add(boutton_register, gbc1);
		styleBouton2(boutton_register);
		// boutton_register.addActionListener((ActionListener) this);

	}

	public void gestionsolde1() {

		Object[][] data = tableau_bas.getDataFromTable(table_command);

		somme_command = 0;

		for (int i = 0; i < data.length; i++) {

			if (data[i][3] != null) {

				double valeur = Double.parseDouble((String) data[i][3]);

				somme_command = somme_command + valeur;
			}
		}

		double soldeActuel = tableau_bas.getSolde();

		if (soldeActuel >= somme_command) {

			double nouveauSolde = soldeActuel - somme_command;
			tableau_bas.setSolde(nouveauSolde);
			tableau_bas.jtextpane_solde.setText(String.format("%.2f \u20AC", nouveauSolde));

			JOptionPane.showMessageDialog(null, "Votre commande a été validée.", "Commande",
					JOptionPane.INFORMATION_MESSAGE);
		} else {

			JOptionPane.showMessageDialog(null, "Votre solde est insuffisant pour passer cette commande.", "Commande",
					JOptionPane.ERROR_MESSAGE);
		}
		return;
	}

	public void gestionsolde2() {

		Object[][] data = tableau_bas.getDataFromTable(table_command);

		somme_command = 0;

		for (int i = 0; i < data.length; i++) {

			if (data[i][3] != null) {

				double valeur = Double.parseDouble((String) data[i][3]);

				somme_command = somme_command + valeur;
			}
		}

		double soldeActuel = tableau_bas.getSolde();

		if (soldeActuel >= 0) {

			double nouveauSolde = soldeActuel + somme_command;
			tableau_bas.setSolde(nouveauSolde);
			tableau_bas.jtextpane_solde.setText(String.format("%.2f \u20AC", nouveauSolde));

			JOptionPane.showMessageDialog(null, "Votre compte a bien été recréditer.", "Recréditer",
					JOptionPane.INFORMATION_MESSAGE);
		} else {

			JOptionPane.showMessageDialog(null, " Votre recrédit n'as pas aboutie ", "Recréditer",
					JOptionPane.ERROR_MESSAGE);
		}
		return;
	}

	public void addtabcommand() {

		Object[][] data = tableau_bas.getDataFromTable(table_command);
		DefaultTableModel model = (DefaultTableModel) tableau_bas.getTableau_bas().getModel();

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		// int num = (Integer) model.getValueAt(0, 0);

		Object value = model.getValueAt(0, 0);
		int num = 0;
		if (value instanceof Integer) {
			num = ((Integer) value).intValue();
		}

		somme_command = 0;

		for (int i = 0; i < data.length; i++) {

			if (data[i][3] != null) {

				double valeur = Double.parseDouble((String) data[i][3]);

				somme_command = somme_command + valeur;
			}
		}

		double soldeActuel = tableau_bas.getSolde();

		if (soldeActuel >= somme_command) {


			for (int i = 0; i < data.length; i++) {

				if (data[i][3] != null) {

					Object[] newRow = new Object[5];

					num++; // incrémenter le numéro de la ligne
					newRow[0] = num; // ajouter le nombre de lignes existantes
					newRow[1] = formater.format(now);
					newRow[2] = data[i][2];
					newRow[3] = "- " + data[i][3] + " \u20AC";
					newRow[4] = data[i][4];

					model.insertRow(0, newRow);
					//num--;
				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "La ligne n'as pas étaait insérer.", "Commande",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void addtabcommand2() {

		Object[][] data = tableau_bas.getDataFromTable(table_command);
		DefaultTableModel model = (DefaultTableModel) tableau_bas.getTableau_bas().getModel();

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		Object value = model.getValueAt(0, 0);
		int num = 0;
		if (value instanceof Integer) {
			num = ((Integer) value).intValue();
		}

		somme_command = 0;

		for (int i = 0; i < data.length; i++) {

			if (data[i][3] != null) {

				double valeur = Double.parseDouble((String) data[i][3]);

				somme_command = somme_command + valeur;
			}
		}

		double soldeActuel = tableau_bas.getSolde();

		if (soldeActuel >= 0) {


			for (int i = 0; i < data.length; i++) {

				if (data[i][3] != null) {

					Object[] newRow = new Object[5];

					num++; // incrémenter le numéro de la ligne
					newRow[0] = num; // ajouter le nombre de lignes existantes
					newRow[1] = formater.format(now);
					newRow[2] = data[i][2];
					newRow[3] = "+ " + data[i][3] + " \u20AC";
					newRow[4] = data[i][4];

					model.insertRow(0, newRow);
					//num--;
				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "La ligne n'as pas étaait insérer.", "Recrédit",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void check_fraisfixes() {

		JCheckBox[] options = new JCheckBox[4];
		options[0] = new JCheckBox("Parties civils - 40 - Versement volontaire");
		options[1] = new JCheckBox("Université Cnam - 35  - Réglement Mensuel");
		options[2] = new JCheckBox("Télévision - 14.15 - Réglement Mensuel");
		options[3] = new JCheckBox("Réfrigérateur - 4.30 - Réglement Mensuel");

		boutton_frais.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					Color mycolor = new Color(20, 175, 210);
					UIManager.put("OptionPane.background", mycolor);
					UIManager.put("Panel.background", mycolor);
					UIManager.put("OptionPane.messageForeground", Color.BLACK);
					UIManager.put("OptionPane.messageFont", new Font("Calibri", Font.BOLD, 16));
					UIManager.put("Button.background", Color.DARK_GRAY);
					UIManager.put("Button.foreground", Color.WHITE);
					UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
					options[0].setOpaque(false);
					options[1].setOpaque(false);
					options[2].setOpaque(false);
					options[3].setOpaque(false);

					int result = JOptionPane.showOptionDialog(null, options, "Choisissez une ou plusieurs options",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, newIcon,
							new String[] { "Valider" }, "OK");

					int row = 0;
					for (int i = 0; i < options.length; i++) {

						Object[] selected = options[i].getSelectedObjects();

						if (selected != null) {

							String[] parts = selected[0].toString().split("-");

							model.setValueAt(parts[0].trim(), row, 2);
							model.setValueAt(parts[1].trim(), row, 3);
							model.setValueAt(parts[2].trim(), row, 4);

							row++;
						}
						options[i].setSelected(false);
					}

				} catch (NumberFormatException ex) {

					JOptionPane.showMessageDialog(null, " Erreur, la saisie du tableau n'as pas aboutie ",
							" Fonction frais fixes ", JOptionPane.ERROR_MESSAGE);
					return;
				}

			}
		});
	}

	public void emptyclose() {

		DefaultTableModel model = (DefaultTableModel) table_command.getModel();

		for (int i = 0; i < model.getRowCount(); i++) {

			table_command.setValueAt(null, i, 2);
			table_command.setValueAt(null, i, 3);
			table_command.setValueAt(null, i, 4);
		}

		model.fireTableDataChanged();

		this.dispose();
	}

	public void check_command() {

		boutton_register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String lignes = "";

				try {
					for (int i = 0; i < 13; i++) {

						data[0] = table_command.getValueAt(i, 0);
						data[1] = table_command.getValueAt(i, 1);
						data[2] = table_command.getValueAt(i, 2);
						data[3] = table_command.getValueAt(i, 3);
						data[4] = table_command.getValueAt(i, 4);

						if (data[0] != null && data[2] != null && data[3] != null && data[4] != null) {
							lignes += "- " + data[0] + " | " + data[2] + " | " + data[3] + " \u20AC | " + data[4]
									+ "\n";
						}
					}
					Color mycolor = new Color(20, 175, 210);
					UIManager.put("OptionPane.background", mycolor);
					UIManager.put("Panel.background", mycolor);
					UIManager.put("OptionPane.messageForeground", Color.BLACK);
					UIManager.put("OptionPane.messageFont", new Font("Calibri", Font.BOLD, 16));
					UIManager.put("Button.background", Color.DARK_GRAY);
					UIManager.put("Button.foreground", Color.WHITE);
					UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

					int reponse = JOptionPane.showConfirmDialog(null, lignes, "Confirmation", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, newIcon1);

					if (reponse == JOptionPane.YES_OPTION) {

						try {
							
							addtabcommand();
							gestionsolde1();
							emptyclose();
							
						} catch (Exception ex) {

							return;
						}

					}
				} catch (NumberFormatException ex) {

					JOptionPane.showMessageDialog(null,
							" Erreur, la commande n'as pas aboutie,\n ou vérifier bien le solde ",
							" Fonction commande ", JOptionPane.ERROR_MESSAGE);
					return;
				}

			}
		});
	}

	public void check_command2() {

		boutton_recredit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String lignes = "";

				try {
					for (int i = 0; i < 13; i++) {

						data[0] = table_command.getValueAt(i, 0);
						data[1] = table_command.getValueAt(i, 1);
						data[2] = table_command.getValueAt(i, 2);
						data[3] = table_command.getValueAt(i, 3);
						data[4] = table_command.getValueAt(i, 4);

						if (data[0] != null && data[2] != null && data[3] != null && data[4] != null) {
							lignes += "- " + data[0] + " | " + data[2] + " | " + data[3] + " \u20AC | " + data[4]
									+ "\n";
						}
					}
					Color mycolor = new Color(20, 175, 210);
					UIManager.put("OptionPane.background", mycolor);
					UIManager.put("Panel.background", mycolor);
					UIManager.put("OptionPane.messageForeground", Color.BLACK);
					UIManager.put("OptionPane.messageFont", new Font("Calibri", Font.BOLD, 16));
					UIManager.put("Button.background", Color.DARK_GRAY);
					UIManager.put("Button.foreground", Color.WHITE);
					UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

					int reponse = JOptionPane.showConfirmDialog(null, lignes, "Confirmation recrédit",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, newIcon1);

					if (reponse == JOptionPane.YES_OPTION) {

						try {
							
							addtabcommand2();
							gestionsolde2();
							emptyclose();
							
						} catch (Exception ex) {

							return;
						}

					}
				} catch (NumberFormatException ex) {

					JOptionPane.showMessageDialog(null,
							" Erreur, le recrédit n'as pas aboutie,\n ou vérifier bien le solde ",
							" Fonction récrédit ", JOptionPane.ERROR_MESSAGE);
					return;
				}

			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		/*
		 * Object source = e.getSource();
		 * 
		 * if (source == boutton_recredit) { gestionsolde2(); addtabcommand2();
		 * emptyclose(); }
		 */

	}

}