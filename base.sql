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

