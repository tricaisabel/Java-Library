# Java-Library
O aplicatie client-server folosind sockets TCP/IP astfel incat serverul sa gestioneze o conexiune la baza de date derby DB, iar clientul sa interactioneze cu serverul dupa urmatorul API:

1. cerere client cu privire la cartile disponibile in biblioteca (clasa carte are un atribut suplimentar cu prvire la numarul de exemplare
   disponibil in biblioteca
    R1.1 serverul returneaza lista cartilor din biblioteca cu disponibilitatea fiecareia-select
2. imprumutul unei carti din tabela CARTE-select->update
    R2.1 serverul returneaza un raspuns cu privire la inexistenta titlului dorit de client
    R2.2 serverul returneaza un raspuns cu privire la idisponibilitatea temporara a cartii
    R2.3 serverul confirma imprumultul cartii dorite de client
3. returnarea unei carti din tabela CARTE->update exemplate+1
    R3.1 cartea a fost returnata cu succes
