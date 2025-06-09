drop database if exists auto_ecole_251;
create database auto_ecole_251;
use auto_ecole_251;
CREATE TABLE candidat (
  idcandidat int NOT NULL auto_increment,
  nom varchar(32) NULL,
  prenom varchar(32) NULL,
  age int(3) NOT NULL,
  email varchar(32) NULL,
  mdp varchar(32) NULL,
  type_user enum('CANDIDAT'),
  numero_telephone varchar(32) NULL,
  PRIMARY KEY (idcandidat)
);

CREATE TABLE examen (
  idexamen int NOT NULL auto_increment,
  date_et_heure_examen varchar(32) NULL,
  vehicule varchar(32) NULL,
  type_de_permis varchar(32) NULL,
  PRIMARY KEY (idexamen)
);

CREATE TABLE vehicule (
  idvehicule int(3) NOT NULL auto_increment,
  marque varchar(32) NULL,
  modele varchar(32) NULL,
  immatriculation varchar(32) NULL,
  PRIMARY KEY (idvehicule)
);

CREATE TABLE lecon (
  idlecon int(3) NOT NULL auto_increment,
  type_de_lecon varchar(32) NULL,
  description varchar(32) NULL,
  titre varchar(32) NULL,
  PRIMARY KEY (idlecon)
);

CREATE TABLE responsable (
  idresponsable int NOT NULL auto_increment,
  nom varchar(30),
  prenom varchar(30),
  email varchar(30),
  mdp varchar(50),
  type_user enum('responsable'),
  numero_telephone varchar(32) NULL,
  PRIMARY KEY (idresponsable)
);

CREATE TABLE formule (
  num_formule int NOT NULL auto_increment,
  description varchar(30),
  PRIMARY KEY (num_formule)
);

CREATE TABLE mois (
  idannee_mois varchar(7) NOT NULL PRIMARY KEY,
  mois varchar(20)
);

CREATE TABLE km_effectuer (
  idkm_effectuer int NOT NULL auto_increment,
  idvehicule int(3) NOT NULL,
  idannee_mois varchar(7) NOT NULL,
  kilometrage int(3) NOT NULL,
  PRIMARY KEY (idkm_effectuer),
  CONSTRAINT fk_vehicule FOREIGN KEY (idvehicule) REFERENCES vehicule(idvehicule),
  CONSTRAINT fk_anneemois FOREIGN KEY (idannee_mois) REFERENCES mois(idannee_mois)
);

CREATE TABLE passage_examen (
  idpassage_examen int NOT NULL auto_increment,
  idexamen int(11) NOT NULL,
  idcandidat int(1) NOT NULL,
  date_et_heure_examen datetime NOT NULL,
  PRIMARY KEY (idpassage_examen),
  CONSTRAINT fkr_examen FOREIGN KEY (idexamen) REFERENCES examen(idexamen),
  CONSTRAINT fkt_candidat FOREIGN KEY (idcandidat) REFERENCES candidat(idcandidat)
);

DROP TABLE IF EXISTS moniteur;
CREATE TABLE moniteur (
  idmoniteur int(3) NOT NULL auto_increment,
  nom varchar(32) NULL,
  prenom varchar(32) NULL,
  email varchar(32) NULL,
  mdp varchar(32) NULL,
  type_user enum('MONITEUR'),
  numero_telephone varchar(32) NULL,
  idresponsable int(2) NOT NULL,
  PRIMARY KEY (idmoniteur),
  CONSTRAINT fk_responsable FOREIGN KEY (idresponsable) REFERENCES responsable(idresponsable)
);

CREATE TABLE plannings (
  idplanning int NOT NULL auto_increment,
  idlecon int(3) NOT NULL,
  idcandidat int(3) NOT NULL,
  dateheuredebut datetime NOT NULL,
  idmoniteur int(3) NOT NULL,
  dateheuref datetime NULL,
  etat enum('annule', 'valide', 'en attente'),
  PRIMARY KEY (idplanning),
  CONSTRAINT fk_lecon FOREIGN KEY (idlecon) REFERENCES lecon(idlecon),
  CONSTRAINT fk_candidat FOREIGN KEY (idcandidat) REFERENCES candidat(idcandidat),
  CONSTRAINT fk_moniteur FOREIGN KEY (idmoniteur) REFERENCES moniteur(idmoniteur)
);

CREATE TABLE user (
  iduser int(5) NOT NULL auto_increment,
  nom varchar(50),
  prenom varchar(50),
  email varchar(50),
  mdp varchar(255),
  role enum('admin', 'user','candidat','moniteur','responsable'),
  PRIMARY KEY (iduser)
);
insert into user values (null, "Youcef", 
   "Gedeon", "a@gmail.com","123", "admin"); 

insert into user values (null, "Allan", 
   "Nicolas", "b@gmail.com","456", "user"); 

ALTER TABLE Candidat ADD COLUMN Profession VARCHAR(20);
