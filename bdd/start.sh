#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "postgres" --dbname "postgres" <<-EOSQL
	-- Database: compte
  -- DROP DATABASE IF EXISTS compte;

  CREATE DATABASE compte
      WITH
      OWNER = postgres
      ENCODING = 'UTF8'
      LC_COLLATE = 'en_US.utf8'
      LC_CTYPE = 'en_US.utf8'
      TABLESPACE = pg_default
      CONNECTION LIMIT = -1
      IS_TEMPLATE = False;

  \c compte;

  -- Table: public.compte
  -- DROP TABLE IF EXISTS public.compte;

  CREATE TABLE IF NOT EXISTS compte.public.compte
  (
      nocompte character(4) COLLATE pg_catalog."C.UTF-8" NOT NULL,
      nom character(20) COLLATE pg_catalog."C.UTF-8",
      prenom character(20) COLLATE pg_catalog."C.UTF-8",
      solde numeric(10,2),
      CONSTRAINT compte_pkey PRIMARY KEY (nocompte)
  )

      TABLESPACE pg_default;

  ALTER TABLE IF EXISTS compte.public.compte
      OWNER to postgres;


  -- Table: public.operation
  -- DROP TABLE IF EXISTS public.operation;

  CREATE TABLE IF NOT EXISTS compte.public.operation
  (
      id serial,
      nocompte character(4) COLLATE pg_catalog."default" NOT NULL,
      date date NOT NULL,
      heure time without time zone NOT NULL,
      op character(1) COLLATE pg_catalog."default" NOT NULL,
      valeur numeric(10,2) NOT NULL,
      CONSTRAINT "OPERATION_pkey" PRIMARY KEY (id)
  )

      TABLESPACE pg_default;

  ALTER TABLE IF EXISTS compte.public.operation
      OWNER to postgres;
EOSQL