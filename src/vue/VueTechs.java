package vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controleur.Technicien;
import modele.Modele;

public class VueTechs extends JPanel implements ActionListener
{
	private JComboBox cbxTechnicien = new JComboBox();
	private JButton btOk = new JButton("Ok");
	private JTextArea areaTech = new JTextArea();
	
	public VueTechs()
	{
		this.setBounds(20, 70, 660, 390);
		this.setLayout(null);
		this.setBackground(Color.red);
		
		this.cbxTechnicien.setBounds(50, 50, 120, 20);
		this.add(cbxTechnicien);
		this.btOk.setBounds(190, 50, 80, 20);
		this.add(btOk);
		this.areaTech.setBounds(50, 80, 270, 150);
		this.add(this.areaTech);
		
		this.remplirCBX();
		
		this.btOk.addActionListener(this);
		
		this.areaTech.setEditable(false);
		
		this.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btOk)
		{
			String chaine = this.cbxTechnicien.getSelectedItem().toString();
			String tab[] = chaine.split(" - ");
			int id_tech = Integer.parseInt(tab[0]);
			Technicien unTechnicien = new Technicien(id_tech, tab[1], tab[2], "");
			unTechnicien = Modele.selectWhereTechnicien(unTechnicien);
			this.areaTech.setText("Technicien sélectionné :\n"
					+ "ID Technicien : " + unTechnicien.getId_tech() + "\n"
					+ "Nom Technicien : " + unTechnicien.getNom() + "\n"
					+ "Prénom Technicien : " + unTechnicien.getPrenom() + "\n"
					+ "Compétence : " + unTechnicien.getCompetence());
		}
	}
	
	public void remplirCBX()
	{
		ArrayList<Technicien> lesTechniciens = Modele.selectallTechniciens();
		for (Technicien unTechnicien : lesTechniciens)
		{
			this.cbxTechnicien.addItem(unTechnicien.getId_tech() + " - " + unTechnicien.getNom() + " - " + unTechnicien.getPrenom());
		}
	}

}
