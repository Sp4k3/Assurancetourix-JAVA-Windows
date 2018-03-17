package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Main;
import modele.Modele;

public class VueConnexion extends JFrame implements ActionListener, KeyListener
{
	private JPanel unPanel = new JPanel();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btSeConnecter = new JButton("Se Connecter");
	private JTextField txtLogin = new JTextField();
	private JPasswordField pwdMdp = new JPasswordField();
	private JLabel JLlogin = new JLabel("Login : ");
	private JLabel JLmdp = new JLabel("MDP : ");
	
	public VueConnexion()
	{
		this.setTitle("Connexion à Gest-Assurance");
		this.setBounds(200, 200, 500, 450);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.white);
		
		// Construction du pannel
		this.unPanel.setBounds(50, 250, 400, 150);
		this.unPanel.setLayout(new GridLayout(3, 2));
		
		this.unPanel.add(JLlogin);
		JLlogin.setForeground(new Color(43,102,22));
		this.unPanel.add(this.txtLogin);
		txtLogin.setForeground(new Color(43,102,22));
		
		this.unPanel.add(JLmdp);
		JLmdp.setForeground(new Color(43,102,22));
		this.unPanel.add(this.pwdMdp);
		pwdMdp.setForeground(new Color(43,102,22));
		
		this.unPanel.add(this.btAnnuler);
		btAnnuler.setBackground(new Color(43,102,22));
		btAnnuler.setForeground(Color.white);
		btAnnuler.setOpaque(true);
		
		this.unPanel.add(this.btSeConnecter);
		btSeConnecter.setBackground(new Color(43,102,22));
		btSeConnecter.setForeground(Color.white);
		btSeConnecter.setOpaque(true);
		
		
		this.unPanel.setVisible(true);
		this.add(this.unPanel);
		
		// Ajout de l'image Ã  la fenÃªtre
		ImageIcon logo = new ImageIcon("src/images/logo.png");
		JLabel lbLogo = new JLabel(logo);
		lbLogo.setBounds(50, 10, 420, 230);
		this.add(lbLogo);
		
		// Ajout de l'icone de l'applicaiton
		ImageIcon logopetit = new ImageIcon("src/images/intervention_petit.png");
		this.setIconImage(logopetit.getImage());
		
		// Rendre les boutons cliquables
		this.btAnnuler.addActionListener(this);
		this.btSeConnecter.addActionListener(this);
		
		// Rendre la touche entree ecoutable
		this.txtLogin.addKeyListener(this);
		this.pwdMdp.addKeyListener(this);
		
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
			case "Annuler" :
				this.txtLogin.setText("");
				this.pwdMdp.setText("");
				break;
			case "Se Connecter" :
				traitement();
				break;
		}
	}
	
	private void traitement()
	{
		String login = this.txtLogin.getText();
		String mdp = new String(this.pwdMdp.getPassword());
		if (login.equals("") || mdp.equals(""))
		{
			JOptionPane.showMessageDialog(this, "Veuillez remplir les identifiants");
		}
		else
		{
			String droits = Modele.verifConnexion(login, mdp);
			if (droits.equals(""))
			{
				JOptionPane.showMessageDialog(this, "Erreur d'identifiants", "Erreur", JOptionPane.ERROR_MESSAGE);
				this.txtLogin.setText("");
				this.pwdMdp.setText("");
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Bienvenue !", "Connexion réussie", JOptionPane.INFORMATION_MESSAGE);
				
				// Appel de la JFrame generale
				Main.rendreVisible(false);
				new VueGenerale(droits);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyChar() == KeyEvent.VK_ENTER)
		{
			traitement();
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		
	}

}
