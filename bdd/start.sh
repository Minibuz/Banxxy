#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "postgres" --dbname "postgres" <<-EOSQL
	DROP DATABASE IF EXISTS compte;
  --
  -- Name: compte; Type: DATABASE; Schema: -; Owner: postgres
  --

  CREATE DATABASE compte WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


  ALTER DATABASE compte OWNER TO postgres;

  \connect compte

  SET statement_timeout = 0;
  SET lock_timeout = 0;
  SET idle_in_transaction_session_timeout = 0;
  SET client_encoding = 'UTF8';
  SET standard_conforming_strings = on;
  SELECT pg_catalog.set_config('search_path', '', false);
  SET check_function_bodies = false;
  SET xmloption = content;
  SET client_min_messages = warning;
  SET row_security = off;

  SET default_tablespace = '';

  SET default_table_access_method = heap;

  --
  -- Name: account; Type: TABLE; Schema: public; Owner: postgres
  --

  CREATE TABLE public.account (
      id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 0 MINVALUE 0 MAXVALUE 999999 CACHE 1 ),
      customer_id bigint NOT NULL,
      balance bigint NOT NULL
  );


  ALTER TABLE public.account OWNER TO postgres;

  --
  -- Name: advisor; Type: TABLE; Schema: public; Owner: postgres
  --

  CREATE TABLE public.advisor (
      id bigint NOT NULL
  );


  ALTER TABLE public.advisor OWNER TO postgres;

  --
  -- Name: customer; Type: TABLE; Schema: public; Owner: postgres
  --

  CREATE TABLE public.customer (
      id bigint NOT NULL,
      advisor bigint NOT NULL,
      parent bigint
  );


  ALTER TABLE public.customer OWNER TO postgres;

  --
  -- Name: transaction; Type: TABLE; Schema: public; Owner: postgres
  --

  CREATE TABLE public.transaction (
      id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 0 MINVALUE 0 MAXVALUE 999999 CACHE 1 ),
      date date NOT NULL,
      amount bigint NOT NULL,
      author bigint NOT NULL,
      account_from bigint NOT NULL,
      account_to bigint NOT NULL
  );


  ALTER TABLE public.transaction OWNER TO postgres;

  --
  -- Name: user; Type: TABLE; Schema: public; Owner: postgres
  --

  CREATE TABLE public."user" (
      id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 0 MINVALUE 0 MAXVALUE 999999 CACHE 1 ),
      personal_id character(4) NOT NULL,
      username character varying(20) NOT NULL,
      name character varying(20) NOT NULL,
      firstname character varying(20) NOT NULL,
      mail character varying(40) NOT NULL,
      password character varying(255) NOT NULL,
      type character varying(10) NOT NULL
  );


  ALTER TABLE public."user" OWNER TO postgres;

  --
  -- Name: user User_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
  --

  ALTER TABLE ONLY public."user"
      ADD CONSTRAINT "User_pkey" PRIMARY KEY (id);


  --
  -- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
  --

  ALTER TABLE ONLY public.account
      ADD CONSTRAINT account_pkey PRIMARY KEY (id);


  --
  -- Name: advisor advisor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
  --

  ALTER TABLE ONLY public.advisor
      ADD CONSTRAINT advisor_pkey PRIMARY KEY (id);


  --
  -- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
  --

  ALTER TABLE ONLY public.customer
      ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


  --
  -- Name: transaction transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
  --

  ALTER TABLE ONLY public.transaction
      ADD CONSTRAINT transaction_pkey PRIMARY KEY (id);


  --
  -- Name: transaction fk_account_from; Type: FK CONSTRAINT; Schema: public; Owner: postgres
  --

  ALTER TABLE ONLY public.transaction
      ADD CONSTRAINT fk_account_from FOREIGN KEY (account_from) REFERENCES public.account(id);


  --
  -- Name: transaction fk_account_to; Type: FK CONSTRAINT; Schema: public; Owner: postgres
  --

  ALTER TABLE ONLY public.transaction
      ADD CONSTRAINT fk_account_to FOREIGN KEY (account_to) REFERENCES public.account(id);


  --
  -- Name: customer fk_advisor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
  --

  ALTER TABLE ONLY public.customer
      ADD CONSTRAINT fk_advisor FOREIGN KEY (advisor) REFERENCES public.advisor(id);


  --
  -- Name: transaction fk_author; Type: FK CONSTRAINT; Schema: public; Owner: postgres
  --

  ALTER TABLE ONLY public.transaction
      ADD CONSTRAINT fk_author FOREIGN KEY (author) REFERENCES public."user"(id);


  --
  -- Name: account fk_customer; Type: FK CONSTRAINT; Schema: public; Owner: postgres
  --

  ALTER TABLE ONLY public.account
      ADD CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES public.customer(id);


  --
  -- Name: customer fk_parent; Type: FK CONSTRAINT; Schema: public; Owner: postgres
  --

  ALTER TABLE ONLY public.customer
      ADD CONSTRAINT fk_parent FOREIGN KEY (parent) REFERENCES public.customer(id) NOT VALID;


  --
  -- Name: advisor fk_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
  --

  ALTER TABLE ONLY public.advisor
      ADD CONSTRAINT fk_user FOREIGN KEY (id) REFERENCES public."user"(id) NOT VALID;


  --
  -- Name: customer fk_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
  --

  ALTER TABLE ONLY public.customer
      ADD CONSTRAINT fk_user FOREIGN KEY (id) REFERENCES public."user"(id);

  --
  -- Adding data
  --

  INSERT INTO public."user"(personal_id, username, name, firstname, mail, password, type)
      VALUES ('0001', 'minibuz', 'buzelin', 'leo', 'mail', '0000', 'advisor');
  INSERT INTO public."user"(personal_id, username, name, firstname, mail, password, type)
      VALUES ('0002', 'maks', 'dumerat', 'maxime', 'mail', '0000', 'advisor');
  INSERT INTO public."user"(personal_id, username, name, firstname, mail, password, type)
      VALUES ('0003', 'lbar', 'barroux', 'leo', 'mail', '0000', 'customer');
  INSERT INTO public."user"(personal_id, username, name, firstname, mail, password, type)
      VALUES ('0004', 'casi', 'domart', 'guillaume', 'mail', '0000', 'customer');
  INSERT INTO public."user"(personal_id, username, name, firstname, mail, password, type)
      VALUES ('0005', 'dreccus', 'ricard', 'martin', 'mail', '0000', 'customer');
  INSERT INTO public.advisor(id)
      VALUES (0);
  INSERT INTO public.advisor(id)
      VALUES (1);
  INSERT INTO public.customer(id, advisor, parent)
      VALUES (2, 0, null);
  INSERT INTO public.customer(id, advisor, parent)
      VALUES (3, 0, 2);
  INSERT INTO public.customer(id, advisor, parent)
      VALUES (4, 1, null);
  INSERT INTO public.account(customer_id, balance)
      VALUES (2, 1000);
  INSERT INTO public.account(customer_id, balance)
      VALUES (3, 100);
  INSERT INTO public.account(customer_id, balance)
      VALUES (4, 500);
EOSQL