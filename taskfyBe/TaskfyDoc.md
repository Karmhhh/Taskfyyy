#### Taskfy

Web App:

- Lato FrontEnd Sviluppata con React, Mui, Axios 

- Lato BeckEnd Sviluppata con Java SpringBoot, Postgress

##### Obbiettivo esercitazione:

Prendere dimestichezza con Java e Springboot, Pattern DTO, Docker, Persistenza dei Dati e concetto Rilascio Conteiner Doker ad un ipotetico Cliente.

Esercitazione Assegnata: 

Aggiungere colonna all' Entita todo di tipo local date time e  restituire dto che include anche tempo residuo alla scadenza in ore e calcola il  valore di una enum basato sul tempo residuo (scaduta ore/minuti rimanenti = 0, vicino a scadenza 0 < x < 50%, in tempo 50% < x < 100%).
Piccola nota => ricorda che determinati calcoli non è il caso di farli nel caso in cui il task sia completo.

Implementare:

##### DTO: Data Transfer Object (Pattern*)

- Todo è un'entità JPA che rappresenta un utente nel database.

- TodoDTO è un DTO che contiene solo i dati necessari per la visualizzazione.

- TodoService contiene la logica per eseguire operazioni col nostro oggeto principale, in breve i servizi che il beckEnd offre al Frontend servizi.

- TodoController è un controller REST che espone un endpoint per ottenere i vari servizi. Utilizza TodoDTO per trasferire dati tra il client e il server e non il modello principale (Todo)per non esporre la nostra entità all'esterno.
  
  - *Pattern: Metologia di Sviluppo Software Standardizzata 

Gestione Eccezioni e Response Entity.

##### Doker

Docker file: file di configurazione volto alla build di un container apposito per storicizzare il BE in questo caso della mia app, tramite variabili di ambiente settate nel docker compose, un file .yaml si riesce a far comunicare diversi container/images tra loro in modo automatico una volta startato il Be sara rannabile in modo autonomo startando direttamente il container. 

app.properties 

```
spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME} 
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASS}
spring.jpa.hibernate.ddl-auto=update
```

docker (file)

```
FROM maven:3.9.6-eclipse-temurin-17 as BUILDER

WORKDIR /app

COPY pom.xml .
COPY Dockerfile .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests

FROM eclipse-temurin:17.0.10_7-jre-jammy

WORKDIR /app

COPY --from=BUILDER /app/target/Taskfy.jar /app/Taskfy.jar

EXPOSE 8080

CMD ["java", "-jar", "Taskfy.jar"]
```

definito il file docker usare 

```
docker build -t taskfy:test .
```

per buildare il container

docker-compose.yml

```
version: '3'
services:
    servizioPostgres:
        image: postgres:latest
        environment:
            POSTGRES_USERNAME: postgres
            POSTGRES_PASSWORD: passdb

    taskfy:
        image: taskfy:test
        environment:
            DB_USERNAME: postgres
            DB_NAME: postgres
            DB_PASS: passdb
            SERVER_HOST: servizioPostgres
```

il docker-compose e app.properties interagiscono grazie alle variabili di ambiente definite

- DB_USERNAME

- DB_NAME

- DB_PASS

- SERVER_HOST

Nel terminale aperto nella cartella dove si trova il docker-compose.yml



```
docker-compose up -d
```

si crea cosi il container che mette in comunizazione il servizio di un database postgres con il mio servizio BE taskfy come specificato nel file docker-compose.yml

```
CONTAINER ID   IMAGE             COMMAND                  CREATED              STATUS              PORTS                                       NAMES
26564f0ba4e8   postgres:latest   "docker-entrypoint.s…"   About a minute ago   Up About a minute   0.0.0.0:3001->5432/tcp, :::3001->5432/tcp   desktop_servizioPostgres_1
e0d67d3d3b39   taskfy:test       "/__cacert_entrypoin…"   About a minute ago   Up About a minute   0.0.0.0:8080->8080/tcp, :::8080->8080/tcp   desktop_taskfy_1
```

con docker ps -a vedo TUTTI i miei container, attivi e non

con docker logs NOME_CONTAINER (e0d)  nel mio caso avvio il container  NOME_CONTAINER

il BE è cosi disponibile per essere usato sulla porta 8080 del lh come specificato nella configuarzione.
