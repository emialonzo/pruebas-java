#!/bin/sh

java -cp lib/hsqldb.jar org.hsqldb.Server -database.0 file:./BBDD/baseAnimaleando -dbname.0 RegistrameAnimal
