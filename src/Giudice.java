import java.util.ArrayList;
import java.util.List;

public class Giudice {
    private boolean garaIniziata = false;
    private boolean garaFinita = false;
    private final List<String> classifica = new ArrayList<>();

    // Partenza
    public synchronized void daiIlVia() {
        System.out.println("Il giudice dà il via per la gara");
        garaIniziata = true;
        notifyAll();
    }

    // Attesa per le auto
    public synchronized void attendiPartenza() {
        while (!garaIniziata) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Segnalazione di arrivo
    public synchronized void registraArrivo(String nomeAuto) {
        classifica.add(nomeAuto);
        if (classifica.size() == 1) {
            System.out.println("Vincitore: " + nomeAuto);
        }
        if (classifica.size() >= 3) { // se tutte le auto arrivate
            garaFinita = true;
            stampaClassifica();
        }
    }

    public synchronized boolean garaFinita() {
        return garaFinita;
    }

    // Stampa classifica finale
    public synchronized void stampaClassifica() {
        System.out.println("\n CLASSIFICA FINALE");
        for (int i = 0; i < classifica.size(); i++) {
            System.out.println((i + 1) + "° posto → " + classifica.get(i));
        }
    }
}
