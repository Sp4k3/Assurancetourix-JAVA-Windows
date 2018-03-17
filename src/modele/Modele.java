package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Client;
import controleur.Technicien;

public class Modele
{	
	public static String verifConnexion(String login, String mdp)
	{
		String requete = "SELECT COUNT(*) AS nb FROM compte WHERE login = '" + login + "' AND mdp = '" + mdp + "';";
		String droits = "";
		
		Bdd uneBdd = new Bdd ("localhost", "assurancetourix", "root", "");
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next())
			{
				droits = unRes.getString("droits");
				int nb = unRes.getInt("nb");
				if (nb == 0) droits = "";
			}
			unRes.close();
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur : " + requete);
		}
		return droits;
	}
	
	/***** Modele des Clients *****/
	public static ArrayList<Client> selectAllClients()
	{
		// Création de la liste
		ArrayList<Client> lesClients = new ArrayList<Client>();
		
		// Création de la requete
		String requete = "SELECT id_personne, budget, preference, taille, nom, prenom "
				+ "FROM client, personne WHERE client.id_personne = personne.id_personne;";
		
		// Connexion à la BDD
		Bdd uneBdd = new Bdd("localhost", "assurancetourix", "root", "");
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			while (unRes.next())
			{
				// Ajout du client à la liste des clients
				lesClients.add(new Client(	unRes.getInt("id_client"), 
											unRes.getString("nom"), 
											unRes.getString("prenom"), 
											unRes.getString("adresse")));
			}
			unStat.close();
			unRes.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur : " + requete);
		}
		return lesClients;
	}
	
	public static void insertClient (Client unClient)
	{
        String requete = "INSERT INTO client VALUES (null,'" + unClient.getNom() + "','" + unClient.getPrenom() +
                "','" + unClient.getAdresse() + "');";
        Bdd uneBdd = new Bdd ("localhost" , "assurancetourix" , "root" , "");
        try
        {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        }
        catch (SQLException exp)
        {
            System.out.println("Erreur : " + requete);
        }
    }
	
	public static void deleteClient (Client unClient)
	{
        String requete = "DELETE FROM client WHERE id_client = " + unClient.getIdclient () + ";";
        Bdd uneBdd = new Bdd ("localhost" , "assurancetourix" , "root" , "");
        try
        {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        }
        catch (SQLException exp)
        {
            System.out.println("Erreur : " + requete);
        }
    }
	
	public static void updateClient (Client unClient)
	{
        String requete = "UPDATE client SET nom = '" + unClient.getNom() +
                "', prenom = '" + unClient.getPrenom() +
                "', adresse = '" + unClient.getAdresse() +
                "' WHERE id_client = " + unClient.getIdclient() + ";";
        Bdd uneBdd = new Bdd ("localhost" , "intervention" , "root" , "");
        try
        {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        }
        catch (SQLException exp)
        {
            System.out.println("Erreur : " + requete);
        }
    }
	


	/***** Modele des Techniciens *****/
	public static ArrayList<Technicien> selectallTechniciens()
	{
		ArrayList<Technicien> lesTechniciens = new ArrayList<Technicien>();
		String requete = "SELECT * FROM technicien;";
		Bdd uneBdd = new Bdd("localhost", "assurancetourix", "root", "");
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			while (unRes.next())
			{
				int id_tech = unRes.getInt("id_tech");
				String nom = unRes.getString("nom");
				String prenom = unRes.getString("prenom");
				String competence = unRes.getString("competence");
				Technicien unTechnicien = new Technicien(id_tech, nom, prenom, competence);
				lesTechniciens.add(unTechnicien);
			}
			unStat.close();
			unRes.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur : " + requete);
		}
		return lesTechniciens;
	}
	
	public static Technicien selectWhereTechnicien(Technicien unTechnicien)
	{
		String requete = "SELECT * FROM technicien WHERE id_tech = " + unTechnicien.getId_tech();
		Technicien unTechResultat = null;
		
		Bdd uneBdd = new Bdd("localhost", "assurancetourix", "root", "");
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next())
			{
				int id_tech = unRes.getInt("id_tech");
				String nom = unRes.getString("nom");
				String prenom = unRes.getString("prenom");
				String competence = unRes.getString("competence");
				unTechResultat = new Technicien(id_tech, nom, prenom, competence);
			}
			unStat.close();
			unRes.close();
			uneBdd.seDeConnecter();
		}	
		catch (SQLException exp)
		{
			System.out.println("Erreur : " + requete);
		}
		return unTechResultat;
	}
}
