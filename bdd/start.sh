#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "root" --dbname "root" <<-EOSQL
	CREATE DATABASE test;
EOSQL