-- Generated by Oracle SQL Developer Data Modeler 19.2.0.182.1216
--   at:        2019-12-28 18:05:12 CET
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g

DROP SEQUENCE sala_nr_sali_seq;

DROP SEQUENCE karta_pobytu_id_karty_seq;

DROP SEQUENCE operacja_id_operacji_seq;

DROP SEQUENCE recepta_id_recepty_seq;

DROP SEQUENCE pielegniarka_id_pielegniarki;

DROP SEQUENCE diagnoza_id_diagnozy_seq;

DROP SEQUENCE lekarz_id_lekarza_seq;


DROP TABLE diagnoza CASCADE CONSTRAINTS;

DROP TABLE karta_pobytu CASCADE CONSTRAINTS;

DROP TABLE kartypobytu_lekarze CASCADE CONSTRAINTS;

DROP TABLE lek CASCADE CONSTRAINTS;

DROP TABLE lekarz CASCADE CONSTRAINTS;

DROP TABLE lekarze_specjalizacjelekarskie CASCADE CONSTRAINTS;

DROP TABLE operacja CASCADE CONSTRAINTS;

DROP TABLE pacjent CASCADE CONSTRAINTS;

DROP TABLE pielegniarka CASCADE CONSTRAINTS;

DROP TABLE pielegniarki_sale CASCADE CONSTRAINTS;

DROP TABLE recepta CASCADE CONSTRAINTS;

DROP TABLE recepty_leki CASCADE CONSTRAINTS;

DROP TABLE sala CASCADE CONSTRAINTS;

DROP TABLE specjalizacje CASCADE CONSTRAINTS;

CREATE TABLE diagnoza (
    id_diagnozy        INTEGER NOT NULL,
    opis               VARCHAR2(200) NOT NULL,
    data_wystawienia   DATE NOT NULL,
    id_lekarza         INTEGER NOT NULL,
    id_karty           INTEGER NOT NULL
);

COMMENT ON TABLE diagnoza IS
    'Tabela zawierajaca diagnozy';

ALTER TABLE diagnoza ADD CONSTRAINT diagnoza_pk PRIMARY KEY ( id_diagnozy );

ALTER TABLE diagnoza ADD CONSTRAINT diagnoza__un UNIQUE ( id_lekarza,
                                                          id_karty );

CREATE TABLE karta_pobytu (
    id_karty            INTEGER NOT NULL,
    data_przyjecia      DATE NOT NULL,
    godzina_przyjecia   VARCHAR2(5) NOT NULL,
    data_wypisu         DATE,
    nr_sali             INTEGER NOT NULL,
    pesel               VARCHAR2(11) NOT NULL
);

COMMENT ON TABLE karta_pobytu IS
    'Tabela zawierajaca Karty pobytu';

ALTER TABLE karta_pobytu ADD CONSTRAINT karta_pobytu_pk PRIMARY KEY ( id_karty );

ALTER TABLE karta_pobytu
    ADD CONSTRAINT karta_pobytu__un UNIQUE ( data_przyjecia,
                                             godzina_przyjecia,
                                             pesel );

CREATE TABLE kartypobytu_lekarze (
    id_karty     INTEGER NOT NULL,
    id_lekarza   INTEGER NOT NULL
);

COMMENT ON TABLE kartypobytu_lekarze IS
    'Tabela zawierajaca o kartach i pacjentach';

ALTER TABLE kartypobytu_lekarze ADD CONSTRAINT kartypobytu_lekarze_pk PRIMARY KEY ( id_karty,
                                                                                    id_lekarza );

CREATE TABLE lek (
    nazwa_leku    VARCHAR2(20) NOT NULL,
    rodzaj_leku   VARCHAR2(50) NOT NULL
);

COMMENT ON TABLE lek IS
    'Tabela zawierajaca leki';

ALTER TABLE lek ADD CONSTRAINT lek_pk PRIMARY KEY ( nazwa_leku );

CREATE TABLE lekarz (
    id_lekarza   INTEGER NOT NULL,
    imie         VARCHAR2(20) NOT NULL,
    nazwisko     VARCHAR2(20) NOT NULL,
    placa_pod    NUMBER(7, 2) NOT NULL
);

COMMENT ON TABLE lekarz IS
    'Tabela zawierajaca lekarzy';

ALTER TABLE lekarz ADD CONSTRAINT lekarz_pk PRIMARY KEY ( id_lekarza );

CREATE TABLE lekarze_specjalizacjelekarskie (
    nazwa_specjalizacji   VARCHAR2(50) NOT NULL,
    id_lekarza            INTEGER NOT NULL
);

COMMENT ON TABLE lekarze_specjalizacjelekarskie IS
    'Tabela lekarze i specjalizacje';

ALTER TABLE lekarze_specjalizacjelekarskie ADD CONSTRAINT lek_specj_pk PRIMARY KEY ( nazwa_specjalizacji,
                                                                                     id_lekarza );

CREATE TABLE operacja (
    id_operacji      INTEGER NOT NULL,
    nazwa_operacji   VARCHAR2(50) NOT NULL,
    termin           DATE NOT NULL,
    id_lekarza       INTEGER NOT NULL,
    id_karty         INTEGER NOT NULL
);

COMMENT ON TABLE operacja IS
    'Tabela zawierajaca operacje';

ALTER TABLE operacja ADD CONSTRAINT operacja_pk PRIMARY KEY ( id_operacji );

ALTER TABLE operacja ADD CONSTRAINT operacja__un UNIQUE ( id_lekarza,
                                                          id_karty );

CREATE TABLE pacjent (
    pesel      VARCHAR2(11) NOT NULL,
    imie       VARCHAR2(20) NOT NULL,
    nazwisko   VARCHAR2(20) NOT NULL
);

COMMENT ON TABLE pacjent IS
    'Tabela zawierajaca pacjentow';

ALTER TABLE pacjent ADD CONSTRAINT pacjent_pk PRIMARY KEY ( pesel );

CREATE TABLE pielegniarka (
    id_pielegniarki   INTEGER NOT NULL,
    imie              VARCHAR2(20) NOT NULL,
    nazwisko          VARCHAR2(20) NOT NULL,
    placa             NUMBER(6, 2) NOT NULL
);

COMMENT ON TABLE pielegniarka IS
    'Tabela zawierajaca pielegniarki';

ALTER TABLE pielegniarka ADD CONSTRAINT pielegniarka_pk PRIMARY KEY ( id_pielegniarki );

CREATE TABLE pielegniarki_sale (
    id_pielegniarki   INTEGER NOT NULL,
    nr_sali           INTEGER NOT NULL
);

COMMENT ON TABLE pielegniarki_sale IS
    'Tabela zawierajaca informacje o pielegniarkach i salach';

ALTER TABLE pielegniarki_sale ADD CONSTRAINT pielegniarki_sale_pk PRIMARY KEY ( id_pielegniarki,
                                                                                nr_sali );

CREATE TABLE recepta (
    id_recepty         INTEGER NOT NULL,
    data_wystawienia   DATE NOT NULL,
    id_lekarza         INTEGER NOT NULL,
    id_karty           INTEGER NOT NULL
);

COMMENT ON TABLE recepta IS
    'Tabela zawierajaca recepty';

ALTER TABLE recepta ADD CONSTRAINT recepta_pk PRIMARY KEY ( id_recepty );

ALTER TABLE recepta ADD CONSTRAINT recepta__un UNIQUE ( id_lekarza,
                                                        id_karty );

CREATE TABLE recepty_leki (
    id_recepty   INTEGER NOT NULL,
    nazwa_leku   VARCHAR2(20) NOT NULL
);

COMMENT ON TABLE recepty_leki IS
    'Tabela zawierajaca informacje o receptach i lekach';

ALTER TABLE recepty_leki ADD CONSTRAINT recepty_leki_pk PRIMARY KEY ( id_recepty,
                                                                      nazwa_leku );

CREATE TABLE sala (
    nr_sali     INTEGER NOT NULL,
    pojemnosc   NUMBER(1) NOT NULL,
    oddzial     VARCHAR2(50) NOT NULL
);

COMMENT ON TABLE sala IS
    'Tabela zawierajaca sale';

ALTER TABLE sala ADD CONSTRAINT sala_pk PRIMARY KEY ( nr_sali );

CREATE TABLE specjalizacje (
    nazwa_specjalizacji   VARCHAR2(50) NOT NULL,
    placa_max             NUMBER(7, 2) NOT NULL,
    placa_min             NUMBER(7, 2) NOT NULL
);

COMMENT ON TABLE specjalizacje IS
    'Tabela zawierajaca specjalizacje';

ALTER TABLE specjalizacje ADD CONSTRAINT specjalizacje_pk PRIMARY KEY ( nazwa_specjalizacji );

ALTER TABLE diagnoza
    ADD CONSTRAINT diagnoza_karta_pobytu_fk FOREIGN KEY ( id_karty )
        REFERENCES karta_pobytu ( id_karty );

ALTER TABLE diagnoza
    ADD CONSTRAINT diagnoza_lekarz_fk FOREIGN KEY ( id_lekarza )
        REFERENCES lekarz ( id_lekarza );

ALTER TABLE karta_pobytu
    ADD CONSTRAINT karta_pob_pacj_fk FOREIGN KEY ( pesel )
        REFERENCES pacjent ( pesel );

ALTER TABLE karta_pobytu
    ADD CONSTRAINT karta_pobytu_sala_fk FOREIGN KEY ( nr_sali )
        REFERENCES sala ( nr_sali );

ALTER TABLE kartypobytu_lekarze
    ADD CONSTRAINT karty_lek_karta_pob_fk FOREIGN KEY ( id_karty )
        REFERENCES karta_pobytu ( id_karty );

ALTER TABLE kartypobytu_lekarze
    ADD CONSTRAINT kartypobytu_lekarze_lekarz_fk FOREIGN KEY ( id_lekarza )
        REFERENCES lekarz ( id_lekarza );

ALTER TABLE lekarze_specjalizacjelekarskie
    ADD CONSTRAINT lek_specj_lekarz_fk FOREIGN KEY ( id_lekarza )
        REFERENCES lekarz ( id_lekarza );

ALTER TABLE lekarze_specjalizacjelekarskie
    ADD CONSTRAINT lek_specj_specj_fk FOREIGN KEY ( nazwa_specjalizacji )
        REFERENCES specjalizacje ( nazwa_specjalizacji );

ALTER TABLE operacja
    ADD CONSTRAINT operacja_karta_pobytu_fk FOREIGN KEY ( id_karty )
        REFERENCES karta_pobytu ( id_karty );

ALTER TABLE operacja
    ADD CONSTRAINT operacja_lekarz_fk FOREIGN KEY ( id_lekarza )
        REFERENCES lekarz ( id_lekarza );

ALTER TABLE pielegniarki_sale
    ADD CONSTRAINT piel_sale_piel_fk FOREIGN KEY ( id_pielegniarki )
        REFERENCES pielegniarka ( id_pielegniarki );

ALTER TABLE pielegniarki_sale
    ADD CONSTRAINT pielegniarki_sale_sala_fk FOREIGN KEY ( nr_sali )
        REFERENCES sala ( nr_sali );

ALTER TABLE recepta
    ADD CONSTRAINT recepta_karta_pobytu_fk FOREIGN KEY ( id_karty )
        REFERENCES karta_pobytu ( id_karty );

ALTER TABLE recepta
    ADD CONSTRAINT recepta_lekarz_fk FOREIGN KEY ( id_lekarza )
        REFERENCES lekarz ( id_lekarza );

ALTER TABLE recepty_leki
    ADD CONSTRAINT recepty_leki_lek_fk FOREIGN KEY ( nazwa_leku )
        REFERENCES lek ( nazwa_leku );

ALTER TABLE recepty_leki
    ADD CONSTRAINT recepty_leki_recepta_fk FOREIGN KEY ( id_recepty )
        REFERENCES recepta ( id_recepty );

CREATE SEQUENCE diagnoza_id_diagnozy_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER diagnoza_id_diagnozy_trg BEFORE
    INSERT ON diagnoza
    FOR EACH ROW
    WHEN ( new.id_diagnozy IS NULL )
BEGIN
    :new.id_diagnozy := diagnoza_id_diagnozy_seq.nextval;
END;
/

CREATE SEQUENCE karta_pobytu_id_karty_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER karta_pobytu_id_karty_trg BEFORE
    INSERT ON karta_pobytu
    FOR EACH ROW
    WHEN ( new.id_karty IS NULL )
BEGIN
    :new.id_karty := karta_pobytu_id_karty_seq.nextval;
END;
/

CREATE SEQUENCE lekarz_id_lekarza_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER lekarz_id_lekarza_trg BEFORE
    INSERT ON lekarz
    FOR EACH ROW
    WHEN ( new.id_lekarza IS NULL )
BEGIN
    :new.id_lekarza := lekarz_id_lekarza_seq.nextval;
END;
/

CREATE SEQUENCE operacja_id_operacji_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER operacja_id_operacji_trg BEFORE
    INSERT ON operacja
    FOR EACH ROW
    WHEN ( new.id_operacji IS NULL )
BEGIN
    :new.id_operacji := operacja_id_operacji_seq.nextval;
END;
/

CREATE SEQUENCE pielegniarka_id_pielegniarki START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER pielegniarka_id_pielegniarki BEFORE
    INSERT ON pielegniarka
    FOR EACH ROW
    WHEN ( new.id_pielegniarki IS NULL )
BEGIN
    :new.id_pielegniarki := pielegniarka_id_pielegniarki.nextval;
END;
/

CREATE SEQUENCE recepta_id_recepty_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER recepta_id_recepty_trg BEFORE
    INSERT ON recepta
    FOR EACH ROW
    WHEN ( new.id_recepty IS NULL )
BEGIN
    :new.id_recepty := recepta_id_recepty_seq.nextval;
END;
/

CREATE SEQUENCE sala_nr_sali_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER sala_nr_sali_trg BEFORE
    INSERT ON sala
    FOR EACH ROW
    WHEN ( new.nr_sali IS NULL )
BEGIN
    :new.nr_sali := sala_nr_sali_seq.nextval;
END;
/



-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                            14
-- CREATE INDEX                             0
-- ALTER TABLE                             34
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           7
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          7
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
