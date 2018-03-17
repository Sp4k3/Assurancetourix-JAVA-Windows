package controleur;

public class Technicien
{
	private int id_tech;
	private String nom, prenom, competence;
	
	public Technicien()
	{
		this.id_tech = 0;
		this.nom = this.prenom = this.competence = "";
	}
	public Technicien(int id_tech, String nom, String prenom, String competence)
	{
		this.id_tech = id_tech;
		this.nom = nom;
		this.prenom = prenom;
		this.competence = competence;
	}
	public Technicien(String nom, String prenom, String competence)
	{
		this.id_tech = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.competence = competence;
	}
	public int getId_tech() {
		return id_tech;
	}
	public void setId_tech(int id_tech) {
		this.id_tech = id_tech;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getCompetence() {
		return competence;
	}
	public void setCompetence(String competence) {
		this.competence = competence;
	}
	
	
}
