package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Client;
import controleur.Tableau;
import modele.Modele;

public class VueClients extends JPanel implements ActionListener
{
	private JTable tableClients;
	
	private JPanel panelEdition = new JPanel();
	
	private JButton btAjouter = new JButton("Ajouter");
	private JButton btSupprimer = new JButton("Supprimer");
	private JButton btMiseAJour = new JButton("Mise à jour");
	
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField();
	private JTextField txtTaille = new JTextField();
	private JTextField txtId_client = new JTextField();
	private JTextField txtBudget = new JTextField();
	private JTextField txtAdresse = new JTextField();
	private JTextField txtPreference = new JTextField();
	
	private Tableau unTableau;
	
	public VueClients()
	{
		this.setBounds(20, 70, 660, 390);
		this.setLayout(null);
		this.setBackground(Color.yellow);
		
		// Construction de la JTable
		String entetes[] = {"ID (Client)", "Nom (Client)", "Prénom (Client)", "Taille (Client)", "Budget (Client)", "Preference (Client)"};
		
		unTableau = new Tableau(this.recupererLesClients(), entetes);
		this.tableClients = new JTable(unTableau)
				{
					public boolean isCellEditable(int row, int column)
					{
						return false;
					}
				};
		this.tableClients.setEnabled(true);
		
		this.tableClients.addMouseListener(new MouseListener()
			{
				public void mouseClicked (MouseEvent m)
				{
					int ligne = tableClients.getSelectedRow ();
		            txtId_client.setText ( tableClients.getValueAt(ligne , 0).toString());
		            txtNom.setText ( tableClients.getValueAt(ligne , 1).toString());
		            txtPrenom.setText ( tableClients.getValueAt(ligne , 2).toString());
		            txtTaille.setText ( tableClients.getValueAt(ligne , 3).toString());
		            txtBudget.setText (	tableClients.getValueAt(ligne, 4).toString());
		            txtPreference.setText (	tableClients.getValueAt(ligne, 5).toString());
		        }


					public void mousePressed(MouseEvent e)
					{
						
					}

					public void mouseReleased(MouseEvent e)
					{
						
					}

					public void mouseEntered(MouseEvent e)
					{
						
					}

					public void mouseExited(MouseEvent e)
					{
						
					}
					
				});
		
		// Affichage de la JTable dans une ScrollTable
		JScrollPane uneScroll = new JScrollPane(this.tableClients);
		uneScroll.setBounds(20, 20, 600, 250);
		uneScroll.setBackground(Color.black);
		this.add(uneScroll);
		
		// Construction du panel edition d'un client
		this.panelEdition.setBounds(20, 290, 600, 60);
		this.panelEdition.setLayout(new GridLayout(2, 4));
		this.panelEdition.add(new JLabel("	ID Client : "));
		this.panelEdition.add(txtId_client);
		this.panelEdition.add(new JLabel("	Nom : "));
		this.panelEdition.add(txtNom);
		this.panelEdition.add(new JLabel("	Prénom : "));
		this.panelEdition.add(txtPrenom);
		this.panelEdition.add(new JLabel("	Budget : "));
		this.panelEdition.add(txtTaille);
		this.add(this.panelEdition);
		
		this.btAjouter.setBounds(130	, 360, 100, 20);
		this.add(btAjouter);
		this.btSupprimer.setBounds(280, 360, 100, 20);
		this.add(btSupprimer);
		this.btMiseAJour.setBounds(430, 360, 100, 20);
		this.add(btMiseAJour);
		this.txtId_client.setEditable(false);
		
		// Rendre les boutons cliquables
		this.btAjouter.addActionListener(this);
		this.btSupprimer.addActionListener(this);
		this.btMiseAJour.addActionListener(this);
		
		
		this.setVisible(false);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btAjouter)
		{
			Client unClient = new Client (txtNom.getText(), txtPrenom.getText(), txtAdresse.getText());
			Modele.insertClient(unClient);
			JOptionPane.showMessageDialog(this, "Insertion réussie");
			txtNom.setText("");
			txtPrenom.setText("");
			txtAdresse.setText("");

			Object data[] = {unClient.getIdclient() + "" ,unClient.getNom(), unClient.getPrenom(), unClient.getAdresse()};
			this.unTableau.add(data);
		}
		else if (e.getSource() == this.btSupprimer)
		{
			int id_client = Integer.parseInt(txtId_client.getText());
			Client unClient = new Client (id_client, txtNom.getText(), txtPrenom.getText(), txtAdresse.getText());
			Modele.deleteClient(unClient);
			JOptionPane.showMessageDialog(this, "Suppression r�ussie");
			txtId_client.setText("");
			txtNom.setText("");
			txtPrenom.setText("");
			txtAdresse.setText("");
			int rowIndex = tableClients.getSelectedRow();
			unTableau.remove(rowIndex);;
		}
		else if (e.getSource() == this.btMiseAJour)
		{
			int id_client = Integer.parseInt(txtId_client.getText());
			Client unClient = new Client (id_client, txtNom.getText(), txtPrenom.getText(), txtAdresse.getText());
			Modele.updateClient(unClient);
			JOptionPane.showMessageDialog(this, "Mise à jour réussie");
			txtId_client.setText("");
			txtNom.setText("");
			txtPrenom.setText("");
			txtAdresse.setText("");
			
			int rowIndex = tableClients.getSelectedRow();
			Object data[] = {unClient.getIdclient() + "" ,unClient.getNom(), unClient.getPrenom(), unClient.getAdresse()};
			this.unTableau.update(rowIndex, data);;
		}
	}
	
	// Récupération des données sous forme d'une matrice
	private Object[][] recupererLesClients()
	{
		ArrayList<Client> lesClients = Modele.selectAllClients();
		Object[][] donnees = new Object[lesClients.size()][Client.getNbChampsClients()]; 
		int i = 0;
		for (Client unClient : lesClients)
		{
			donnees[i][0] = unClient.getIdclient() + "";
			donnees[i][1] = unClient.getNom();
			donnees[i][2] = unClient.getPrenom();
			donnees[i][3] = unClient.getAdresse();
			i++;
		}
		return donnees;
	}
}
