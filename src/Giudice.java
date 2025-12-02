import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta il giudice che gestisce l'inizio, la fine e la classifica della gara.
 * Controlla la sincronizzazione tra i thread delle auto.
 */
public class Giudice {

    private boolean garaIniziata = false;
    private boolean garaFinita = false;
    private final List<String> classifica = new ArrayList<>();
    private GestoreFile gestoreFile;

    /**
     * Costruttore del giudice.
     * @param gestoreFile oggetto per la gestione del file classifica
     */
    public Giudice(GestoreFile gestoreFile) {
        this.gestoreFile = gestoreFile;
    }

    /**
     * Da il via alla gara e notifica tutte le auto che possono partire.
     */
    public synchronized void daiIlVia() {
        System.out.println("Il giudice dà il via per la gara");
        garaIniziata = true;
        notifyAll();
    }

    /**
     * Fa attendere le auto finchè la gara non è iniziata.
     * Usa il metodo wait() per sospendere i thread finchè il giudice non da il via.
     */
    public synchronized void attendiPartenza() {
        while (!garaIniziata) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Registra l'arrivo di un'auto nella classifica
     * Se è la prima, questa viene dichiarata vincitrice.
     * Quando tutte le auto sono arrivate, segna la fine della gara e stampa la classifica.
     * @param nomeAuto nome dell'auto arrivata
     */
    public synchronized void registraArrivo(String nomeAuto) {
        classifica.add(nomeAuto);

        if (classifica.size() == 1) {
            System.out.println("Vincitore: " + nomeAuto);
        }

        if (classifica.size() >= 3) { // se tutte le auto arrivate
            garaFinita = true;
            stampaClassifica();
            salvaSuFile();
        }
    }

    /**
     * Indica se la gara è finita
     * @return true se la gara è terminata, altrimenti false
     */
    public synchronized boolean garaFinita() {
        return garaFinita;
    }

    /**
     * Stampa la classifica finale delle auto.
     * Viene chiamato automaticamente dal giudice quando la gara termina.
     */
    public synchronized void stampaClassifica() {
        System.out.println("\n CLASSIFICA FINALE");
        for (int i = 0; i < classifica.size(); i++) {
            System.out.println((i + 1) + " posto → " + classifica.get(i));
        }
    }

    /**
     * Salva la classifica nel file tramite GestoreFile.
     */
    private void salvaSuFile() {
        String testo = "CLASSIFICA FINALE\n";
        for (int i = 0; i < classifica.size(); i++) {
            testo += (i + 1) + " posto -> " + classifica.get(i) + "\n";
        }
        gestoreFile.scriviFile(testo);
    }
}
