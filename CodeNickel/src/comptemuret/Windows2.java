package comptemuret;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Windows2 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JPanel panel_participation;
	JLabel label_revenuparticipation;
	JTextField text_revenuparticipation;
	JLabel label_symrevenu;
	JLabel label_tauxppc;
	JTextField text_tauxppc;
	JLabel label_symppc;
	JLabel label_tauxpl;
	JTextField text_tauxpl;
	JLabel label_sympl;
	JButton bouton_calculer;
	JLabel label_ppcparticipation;
	JTextField text_ppcparticipation;
	JLabel label_symppc1;
	JLabel label_plparticipation;
	JTextField text_plparticipation;
	JLabel label_sympl1;
	JLabel label_cantinable;
	JTextField text_cantinable;
	JLabel label_symcantinable;
	JButton bouton_comfirmer;
	JButton bouton_annuler;

	String text_revenu;
	double somme_revenu;

	String text_ppc;
	double taux_ppc;
	double montant_ppc;

	String text_pl;
	double taux_pl;
	double montant_pl;
	double montant_cantinable;
	double nouveauSolde;
	double nouveauppc;
	double nouveaupl;

	ImagePanel imagePanel = new ImagePanel("img_icon\\BGPB1.jpg");
	ImageIcon icon = new ImageIcon("img_icon\\budget.icon");
	Image image = icon.getImage();
	Image newImage = image.getScaledInstance(35, 35, Image.SCALE_DEFAULT);
	ImageIcon newIcon = new ImageIcon(newImage);

	Windows windows;
	private Windows tableau_bas;

	public Windows2(String text_add, Windows tab) {

		super();
		Content();
		PanelRevenu();
		this.add(panel_participation);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});

		text_revenuparticipation.setText(text_add);
		this.tableau_bas = tab;
	}

	public void Content() {

		setTitle("Calculer Revenu");
		setSize(332, 290);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		setContentPane(imagePanel);
	}

	public void StyleTextRevenu(JTextField text) {

		text.setFont(new Font("Calibri", Font.BOLD, 14));
		text.setHorizontalAlignment(JTextField.CENTER);
		text.setForeground(Color.BLACK);
		text.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	}

	public void styleBouton(JButton bouton) {
		bouton.setBackground(Color.DARK_GRAY);
		bouton.setForeground(Color.WHITE);
		bouton.setFocusable(false);
	}

	public void PanelRevenu() {

		panel_participation = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		panel_participation.setLayout(gridBagLayout);
		panel_participation.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 2, 2, 5);

		label_revenuparticipation = new JLabel("Revenu : ");
		text_revenuparticipation = new JTextField(10);
		label_symrevenu = new JLabel(" €");

		label_tauxppc = new JLabel("Taux PPC : ");
		text_tauxppc = new JTextField(5);
		label_symppc = new JLabel(" %");

		label_tauxpl = new JLabel("Taux PL : ");
		text_tauxpl = new JTextField(5);
		label_sympl = new JLabel(" %");

		bouton_calculer = new JButton("Calculer");

		label_ppcparticipation = new JLabel("PPC : ");
		text_ppcparticipation = new JTextField(10);
		label_symppc1 = new JLabel(" €");

		label_plparticipation = new JLabel("PL : ");
		text_plparticipation = new JTextField(10);
		label_sympl1 = new JLabel(" €");

		label_cantinable = new JLabel("Cantinable : ");
		text_cantinable = new JTextField(10);
		label_symcantinable = new JLabel(" €");

		bouton_comfirmer = new JButton("Comfirmer");
		bouton_annuler = new JButton("Annuler");

		gbc.gridx = 0;
		gbc.gridy = 0;
		panel_participation.add(label_revenuparticipation, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		panel_participation.add(text_revenuparticipation, gbc);
		text_revenuparticipation.setEditable(false);
		StyleTextRevenu(text_revenuparticipation);

		gbc.gridx = 2;
		gbc.gridy = 0;
		panel_participation.add(label_symrevenu, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panel_participation.add(label_tauxppc, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		panel_participation.add(text_tauxppc, gbc);
		StyleTextRevenu(text_tauxppc);
		text_tauxppc.requestFocusInWindow();

		gbc.gridx = 2;
		gbc.gridy = 1;
		panel_participation.add(label_symppc, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		panel_participation.add(label_tauxpl, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		panel_participation.add(text_tauxpl, gbc);
		StyleTextRevenu(text_tauxpl);

		gbc.gridx = 2;
		gbc.gridy = 3;
		panel_participation.add(label_sympl, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		panel_participation.add(bouton_calculer, gbc);
		styleBouton(bouton_calculer);
		bouton_calculer.addActionListener((ActionListener) this);

		gbc.gridx = 0;
		gbc.gridy = 5;
		panel_participation.add(label_ppcparticipation, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		panel_participation.add(text_ppcparticipation, gbc);
		StyleTextRevenu(text_ppcparticipation);

		gbc.gridx = 2;
		gbc.gridy = 5;
		panel_participation.add(label_symppc1, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		panel_participation.add(label_plparticipation, gbc);

		gbc.gridx = 1;
		gbc.gridy = 6;
		panel_participation.add(text_plparticipation, gbc);
		StyleTextRevenu(text_plparticipation);

		gbc.gridx = 2;
		gbc.gridy = 6;
		panel_participation.add(label_sympl1, gbc);

		gbc.gridx = 0;
		gbc.gridy = 7;
		panel_participation.add(label_cantinable, gbc);

		gbc.gridx = 1;
		gbc.gridy = 7;
		panel_participation.add(text_cantinable, gbc);
		StyleTextRevenu(text_cantinable);

		gbc.gridx = 2;
		gbc.gridy = 7;
		panel_participation.add(label_symcantinable, gbc);

		gbc.gridx = 0;
		gbc.gridy = 8;
		panel_participation.add(bouton_comfirmer, gbc);
		styleBouton(bouton_comfirmer);
		bouton_comfirmer.addActionListener((ActionListener) this);

		gbc.gridx = 1;
		gbc.gridy = 8;
		panel_participation.add(bouton_annuler, gbc);
		styleBouton(bouton_annuler);
	}

	public void GestionRevenu() {

		text_revenu = text_revenuparticipation.getText();
		somme_revenu = Double.parseDouble(text_revenu);

		text_ppc = text_tauxppc.getText();
		taux_ppc = Double.parseDouble(text_ppc);

		text_pl = text_tauxpl.getText();
		taux_pl = Double.parseDouble(text_pl);

		montant_ppc = (somme_revenu * taux_ppc) / 100;
		montant_pl = (somme_revenu * taux_pl) / 100;
		montant_cantinable = somme_revenu - montant_ppc - montant_pl;

		text_ppcparticipation.setText(String.format("%.2f \u20AC", montant_ppc));
		text_plparticipation.setText(String.format("%.2f \u20AC", montant_pl));
		text_cantinable.setText(String.format("%.2f \u20AC", montant_cantinable));
	}

	public void AddRevenuWin1() {

		double soldeActuel = tableau_bas.getSolde();
		double ppcActuel = tableau_bas.getPartieCivil();
		double plActuel = tableau_bas.getPartieLiberable();

		nouveauSolde = soldeActuel + montant_cantinable;
		nouveauppc = ppcActuel + montant_ppc;
		nouveaupl = plActuel + montant_pl;

		tableau_bas.setSolde(nouveauSolde);
		tableau_bas.jtextpane_solde.setText(String.format("%.2f \u20AC", nouveauSolde));

		tableau_bas.setPatieCivil(nouveauppc);
		tableau_bas.jtextpane_ppc.setText(String.format("%.2f \u20AC", nouveauppc));

		tableau_bas.setPartieLiberable(nouveaupl);
		tableau_bas.jtextpane_pl.setText(String.format("%.2f \u20AC", nouveaupl));
	}

	public void addtabrevenue() {

		DefaultTableModel model = (DefaultTableModel) tableau_bas.getTableau_bas().getModel();
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		Object value = model.getValueAt(0, 0);
		int num = 0;
		if (value instanceof Integer) {
			num = ((Integer) value).intValue();
		}

		Object[] data = new Object[5];
		data[0] = num + 1;
		data[1] = formater.format(now);
		data[2] = "Revenu";
		data[3] = somme_revenu + " \u20AC";
		data[4] = "P.P.C : " + montant_ppc + " \u20AC | P.L : " + montant_pl + " \u20AC | Cantine : "
				+ String.format("%.2f\u20AC", montant_cantinable);

		model.insertRow(0, data);
		num--;

	}

	public void emptyclose() {

		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		Object source = e.getSource();

		if (source == bouton_calculer) {
			GestionRevenu();

		} else if (source == bouton_comfirmer) {
			AddRevenuWin1();
			addtabrevenue();
			emptyclose();
			
		} else if (source == bouton_annuler) {
			emptyclose();
		}

	}

}
