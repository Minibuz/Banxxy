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
      title character varying(255),
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
      username character varying(20) NOT NULL,
      name character varying(20) NOT NULL,
      firstname character varying(20) NOT NULL,
      mail character varying(40) NOT NULL,
      password character varying(255) NOT NULL,
      role character varying(20) NOT NULL,
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

  INSERT INTO public."user"(username, name, firstname, mail, password, role, type)
      VALUES ('minibuz', 'buzelin', 'leo', 'mail', '{bcrypt}\$2a\$10\$VCIeTiINf5oL9grYi/cnN.W7xssZjHgzDBK7F8oD14ndZUVifhjTK', 'ROLE_ADVISOR', 'advisor');
  INSERT INTO public."user"(username, name, firstname, mail, password, role,type)
      VALUES ('maks', 'dumerat', 'maxime', 'mail', '{bcrypt}\$2a\$10\$VCIeTiINf5oL9grYi/cnN.W7xssZjHgzDBK7F8oD14ndZUVifhjTK', 'ROLE_ADVISOR', 'advisor');
  INSERT INTO public."user"(username, name, firstname, mail, password, role,type)
      VALUES ('lbar', 'barroux', 'leo', 'mail', '{bcrypt}\$2a\$10\$VCIeTiINf5oL9grYi/cnN.W7xssZjHgzDBK7F8oD14ndZUVifhjTK', 'ROLE_CUSTOMER', 'customer');
  INSERT INTO public."user"(username, name, firstname, mail, password, role,type)
      VALUES ('casi', 'domart', 'guillaume', 'mail', '{bcrypt}\$2a\$10\$VCIeTiINf5oL9grYi/cnN.W7xssZjHgzDBK7F8oD14ndZUVifhjTK', 'ROLE_CUSTOMER', 'customer');
  INSERT INTO public."user"(username, name, firstname, mail, password, role,type)
      VALUES ('dreccus', 'ricard', 'martin', 'mail', '{bcrypt}\$2a\$10\$VCIeTiINf5oL9grYi/cnN.W7xssZjHgzDBK7F8oD14ndZUVifhjTK', 'ROLE_CUSTOMER', 'customer');
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
  INSERT INTO public.account(title, customer_id, balance)
      VALUES ('My personnal account', 2, 1000);
  INSERT INTO public.account(title, customer_id, balance)
      VALUES ('El accounto de Patrick', 3, 100);
  INSERT INTO public.account(title, customer_id, balance)
      VALUES ('Le Account' ,4, 500);
  INSERT INTO public."user"(username, name, firstname, mail, password, role,type)
        VALUES ('CB1', 'CB1', 'CB1', 'CB1', '{bcrypt}\$2a\$10\$VCIeTiINf5oL9grYi/cnN.W7xssZjHgzDBK7F8oD14ndZUVifhjTK', 'ROLE_ADVISOR', 'advisor');
  INSERT INTO public.advisor(id)
        VALUES (5);
  INSERT INTO public."user"(username, name, firstname, mail, password, role,type)
          VALUES ('Parent', 'Parent', 'Parent', 'Parent', '{bcrypt}\$2a\$10\$VCIeTiINf5oL9grYi/cnN.W7xssZjHgzDBK7F8oD14ndZUVifhjTK', 'ROLE_CUSTOMER', 'customer');
  INSERT INTO public.customer(id, advisor, parent)
        VALUES (6, 0, null);
  INSERT INTO public."user"(username, name, firstname, mail, password, role,type)
        VALUES ('Enfant', 'Enfant', 'Enfant', 'Enfant', '{bcrypt}\$2a\$10\$VCIeTiINf5oL9grYi/cnN.W7xssZjHgzDBK7F8oD14ndZUVifhjTK', 'ROLE_CUSTOMER', 'customer');
  INSERT INTO public.customer(id, advisor, parent)
        VALUES (7, 0, 6);
  INSERT INTO public.transaction(date, amount, author, account_from, account_to)
  	    VALUES (current_timestamp, 100, 0, 0, 1);
  INSERT INTO public.transaction(date, amount, author, account_from, account_to)
    	  VALUES (current_timestamp, 100, 0, 0, 2);
  INSERT INTO public.transaction(date, amount, author, account_from, account_to)
    	  VALUES (current_timestamp, 23, 0, 0, 1);
EOSQL