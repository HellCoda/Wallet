package montableau;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Tableaux extends JTable {

	//Définir un constructeur qui prend un modèle de tableau en paramètre
		public Tableaux(DefaultTableModel model) {
	//Appeler le constructeur de la classe JTable avec le modèle
			super(model);
		}

	//Définir une méthode getDataFromTable() qui renvoie les données du tableau sous forme d'un tableau d'objets
		public Object[][] getDataFromTable() {
	//Récupérer le nombre de lignes et de colonnes du tableau
			int rows = getRowCount();
			int cols = getColumnCount();
	//Créer un tableau d'objets de la même taille que le tableau
			Object[][] data = new Object[rows][cols];
	//Parcourir les lignes et les colonnes du tableau
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
	//Récupérer la valeur de la cellule à l'index i, j
					Object value = getValueAt(i, j);
	//Stocker la valeur dans le tableau d'objets
					data[i][j] = value;
				}
			}
	//Renvoyer le tableau d'objets
			return data;
		}
		
		public void setDataToTable(Object[][] data) {
			// Récupérer le nombre de lignes et de colonnes du tableau
			int rows = getRowCount();
			int cols = getColumnCount();
			// Parcourir les lignes et les colonnes du tableau
			for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
			// Récupérer la valeur du tableau d'objets à l'index i, j
			Object value = data[i][j];
			// Modifier la valeur de la cellule du tableau à l'index i, j
			setValueAt(value, i, j);
			}
			}
			}
	}
