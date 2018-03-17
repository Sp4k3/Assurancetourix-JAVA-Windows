package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controleur.Main;

public class VueGenerale extends JFrame implements ActionListener
{
	private JPanel panelMenu = new JPanel();
	private JButton tabButton[] = new JButton[7];
	private final String tabNoms[] = {"Clients", "Techniciens", "Interventions", "Attractions", "Promotions", "Restaurant","Quitter"};
	
	// Création des trois panels
	private static VueClients uneVueClients = new VueClients();
	private static VueInters uneVueInters = new VueInters();
	private static VueTechs uneVueTechs = new VueTechs();
	
	private static VueAttractions uneVueAttractions = new VueAttractions();
	private static VuePromotions uneVuePromotions = new VuePromotions();
	private static VueRestaurants uneVueRestaurants = new VueRestaurants();
	
	public VueGenerale(String droits)
	{
		this.setTitle("Gestion des interventions");
		this.setLayout(null);
		this.setResizable(false);
		this.setBounds(200, 200, 700, 500);
		this.getContentPane().setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.panelMenu.setBounds(0, 20, 700, 30);
		this.panelMenu.setLayout(new GridLayout(1, 7));
		
		// Construction des boutons dans le panel
		for (int i = 0; i<7; i++)
		{
			this.tabButton[i] = new JButton(tabNoms[i]);
			
			// Ajouter les boutons dans le panel
			this.panelMenu.add(this.tabButton[i]);
			tabButton[i].setForeground(new Color(43,102,22));
			
			// Rendre les boutons cliquables
			this.tabButton[i].addActionListener(this);
		}
		
		this.panelMenu.setVisible(true);
		this.add(this.panelMenu);
		
		// Ajout des trois panels
		this.add(uneVueClients);
		this.add(uneVueInters);
		this.add(uneVueTechs);
		
		
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.tabButton[3])
		{
			this.dispose();
			Main.rendreVisible(true);
		}
		else if (e.getSource() == this.tabButton[0])
		{
			uneVueClients.setVisible(true);
			uneVueInters.setVisible(false);
			uneVueTechs.setVisible(false);
		}
		else if (e.getSource() == this.tabButton[1])
		{
			uneVueClients.setVisible(false);
			uneVueInters.setVisible(false);
			uneVueTechs.setVisible(true);
		}
		else if (e.getSource() == this.tabButton[2])
		{
			uneVueClients.setVisible(false);
			uneVueInters.setVisible(true);
			uneVueTechs.setVisible(false);
		}
	}
}
