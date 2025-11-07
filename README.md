# Gara di Auto in Java (Multithreading)

Questo progetto simula una gara tra auto utilizzando i thread in Java.  
Ogni auto parte solo quando il giudice dà il via e avanza ogni secondo in base alla sua velocità.  
Alla fine viene stampata la classifica con l’ordine di arrivo.

---

## Descrizione generale

La simulazione rappresenta una gara in cui più auto corrono contemporaneamente.  
Ogni auto è eseguita in un thread separato e avanza in base alla propria velocità.  
Il giudice controlla la partenza e registra gli arrivi, aggiornando la classifica.

---

## Struttura del progetto

### 1. `Auto.java`
- Rappresenta un’auto partecipante alla gara.
- Implementa l’interfaccia `Runnable`, quindi ogni auto gira in un thread separato.
- Attende il segnale del giudice prima di partire (`attendiPartenza()`).
- Ogni secondo aumenta i metri percorsi in base alla velocità.
- Quando raggiunge la distanza totale, avvisa il giudice con `registraArrivo()`.

### 2. `Giudice.java`
- Controlla l’inizio e la fine della gara.
- Tiene una lista con l’ordine di arrivo delle auto.
- Usa i metodi `wait()` e `notifyAll()` per sincronizzare la partenza di tutti i thread.
- Quando tutte le auto arrivano, stampa la classifica finale.

### 3. `GaraAuto.java`
- Contiene il metodo `main`.
- Crea il giudice e le auto.
- Esegue un conto alla rovescia prima della partenza.
- Avvia i thread e poi dà il via alla gara.
- Alla fine attende che tutti i thread terminino e mostra la scritta “Gara terminata”.

---

## Funzionamento della sincronizzazione

- Il giudice usa `synchronized` sui metodi per evitare che più thread accedano agli stessi dati nello stesso momento.
- Le auto aspettano (`wait()`) fino a quando il giudice non chiama `notifyAll()` per dare il via.
- Quando un’auto arriva al traguardo, il giudice registra l’arrivo in modo sicuro.

---