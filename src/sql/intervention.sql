DROP DATABASE IF EXISTS assurancetourixJAVA;
CREATE DATABASE assurancetourixJAVA;

USE assurancetourixJAVA;

CREATE TABLE attraction (
  id_attraction int(3) NOT NULL AUTO_INCREMENT,
  nom varchar(25),
  description varchar(500),
  temps_attente int(11),
  statut varchar(25),
  PRIMARY KEY (`id_attraction`)
)

CREATE TABLE compte (
  id_compte int(4) NOT NULL AUTO_INCREMENT,
  login varchar(70) CHARACTER SET utf8 NOT NULL,
  mdp varchar(40) CHARACTER SET utf8 NOT NULL,
  droits ENUM("admin", "user", "autre"),
  PRIMARY KEY (id_compte)
)

CREATE TABLE IF NOT EXISTS personnel (
  id_personnel int(3) NOT NULL AUTO_INCREMENT,
  prenom varchar(25) NOT NULL,
  nom varchar(25) NOT NULL,
  fonction varchar(25) DEFAULT NULL,
  id_attraction int(11) DEFAULT NULL,
  id_restaurant int(11) DEFAULT NULL,
  PRIMARY KEY (`id_personne`),
  KEY FK_Personnel_id_attraction (id_attraction),
  KEY FK_Personnel_id_restaurant (id_restaurant)
)

CREATE TABLE intervention (
    id_inter INT(3) NOT NULL AUTO_INCREMENT,
    description VARCHAR(100),
    date_inter DATE,
    montant FLOAT,
    id_client INT(3) NOT NULL,
    id_tech INT(3) NOT NULL,
    PRIMARY KEY (id_inter),
    FOREIGN KEY (id_client) REFERENCES client(id_client),
    FOREIGN KEY (id_tech) REFERENCES technicien(id_tech)
    );

insert into client values
(null, "Ruben", "Thibault", "rue de Paris"),
(null, "Alicia", "Laurene", "rue de Lyon");

CREATE VIEW vueGlobale AS
(
SELECT C.nom AS nomClient, P.nom AS nomPers, P.prenom AS prenomPers, I.date_inter, I.montant 
FROM client C, technicien T, intervention I
WHERE C.id_client=I.id_client
AND T.id_tech = I.id_tech
);