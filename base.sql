CREATE TABLE agence (
    code NUMBER  GENERATED ALWAYS AS IDENTITY,
    label VARCHAR(50),
    CONSTRAINT agence_pk PRIMARY KEY (code)
);

CREATE TABLE utilisateur (
    cod NUMBER  GENERATED ALWAYS AS IDENTITY,
    nom VARCHAR(20) NOT NULL,
    nomComplet VARCHAR(50),
    code_agence NOT NULL NUMBER,
    CONSTRAINT nom_unique UNIQUE (nom),
    CONSTRAINT utilisateur_pk PRIMARY KEY (cod),
    CONSTRAINT agence_fk FOREIGN KEY (code_agence) REFERENCES agence(code)
);

CREATE TABLE prestation (
    id NUMBER  GENERATED ALWAYS AS IDENTITY,
    type VARCHAR(50) NOT NULL,
    nbrActes NUMBER NOT NULL,
    montantPaye NUMBER NOT NULL,
    montantEngage NUMBER NOT NULL,
    CONSTRAINT prestation_pk PRIMARY KEY (id)
)
 CREATE TABLE PAYS (
    ID NUMBER GENERATED ALWAYS AS IDENTITY,
	NOM VARCHAR2(40) NOT NULL, 
	CONSTRAINT PAYS_Pk PRIMARY KEY (ID)
 )

CREATE TABLE [dbo].[DirectionRegional](
    [Cod] [int] NOT NULL,
    [Design] [varchar](50) NULL,
    [Adresse] [char](10) NULL,
    [Ville] [int] NULL )

CREATE TABLE DirectionRegional (
    cod NUMBER  GENERATED ALWAYS AS IDENTITY,
    Design VARCHAR(50) NULL,
    Adresse VARCHAR(10) NULL,
    Ville NUMBER NULL

);
   CREATE TABLE VILLE (
     CODE  NUMBER GENERATED ALWAYS AS IDENTITY,
	 DESIGN VARCHAR2(40) NOT NULL, 
	 CODPAYS  NUMBER NOT NULL,
	 CONSTRAINT VILLE_PK PRIMARY KEY (CODE)
 )

 CREATE TABLE CAISSEETRANGERE (
    CODE NUMBER GENERATED ALWAYS AS IDENTITY,
	NOM VARCHAR2(50) NOT NULL, 
	ADRESSE VARCHAR2(50) NOT NULL, 
	VILLE NUMBER NOT NULL, 
	PAYS NUMBER NOT NULL, 
	TEL VARCHAR2(50) NOT NULL, 
	FAX VARCHAR2(50) NOT NULL, 
	EMAIL VARCHAR2(50) NOT NULL, 
	CONSTRAINT CAISSEETRANGERE2_PK PRIMARY KEY (CODE)
 )

 
  CREATE TABLE CAISSEMERE (
     CODE NUMBER GENERATED ALWAYS AS IDENTITY,
	 NOM VARCHAR2(50) NOT NULL, 
	 ADRESSE VARCHAR2(100) NOT NULL, 
	 PAYS NUMBER NOT NULL, 
	 CONSTRAINT CAISSEMERES_PK PRIMARY KEY (CODE),
    CONSTRAINT PAYSS_FK FOREIGN KEY (PAYS) REFERENCES PAYS(ID)
  )
  /*****************************Procedure******************************/
  create or replace PROCEDURE Search3(
       p__code IN  CaisseMere.code%TYPE,
       p__nom IN   CaisseMere.nom%TYPE,
       p__pays IN  CaisseMere.pays%TYPE
       )
   AS
    c1  sys_refcursor ;
   Begin
      open c1 for
      SELECT * FROM CAISSEMERE  WHERE (p__code IS NULL OR CODE LIKE '%' || p__code || '%')
            and (p__nom IS NULL OR NOM LIKE '%' || p__nom || '%')
            AND (p__pays IS NULL OR PAYS LIKE '%' || p__pays || '%');
      dbms_sql.return_result(c1);

      commit;
End;


/*****************************************************/
CREATE TABLE Historique_Dossier (
    id_historique_dossier NUMBER  GENERATED ALWAYS AS IDENTITY,
    n_dossier Number,
    date_debut_soin DATE NULL,
    date_fin_soin DATE NULL,
    date_dossier DATE NULL,
    n_assure NUMBER  ,
    Montant_engage FLOAT(20) NULL,
    Montant_a_remboureser FLOAT(20) NULL,
    Type_dossier VARCHAR(50) NULL,
    code_etat_dossier NUMBER  ,
    n_utilisateur NUMBER ,
    date_creation DATE ,
    action VARCHAR(50) not NULL,
    CONSTRAINT historique_dossier_pk PRIMARY KEY (id_historique_dossier),
    CONSTRAINT assure_fk2 FOREIGN KEY (n_assure) REFERENCES assure(ID_ASSURÉ),
    CONSTRAINT etat_dossier_fk2 FOREIGN KEY (code_etat_dossier) REFERENCES etat_dossier(ID_ETAT),
    CONSTRAINT dossier_fk2 FOREIGN KEY (n_dossier) REFERENCES dossier(n_dossier),
    CONSTRAINT utilisateur_fk2 FOREIGN KEY (n_utilisateur) REFERENCES utilisateur(cod)

)