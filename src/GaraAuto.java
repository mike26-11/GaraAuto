/**
 * Classe principale che avvia la simulazione della gara di auto.
 * Crea il giudice e le auto, gestisce iil conto alla rovescia e dirige l'inizio della gara.
 */
public class GaraAuto {
    public static void main(String[] args) {
        Giudice giudice = new Giudice();
        double distanzaTotale = 150;

        Thread a1 = new Thread(new Auto("Ferrari", 25, distanzaTotale, giudice));
        Thread a2 = new Thread(new Auto("Lamborghini", 22, distanzaTotale, giudice));
        Thread a3 = new Thread(new Auto("Porsche", 20, distanzaTotale, giudice));

        a1.start();
        a2.start();
        a3.start();

        try {
            for (int i = 3; i > 0; i--) {
                System.out.println("Partenza tra " + i + "...");
                Thread.sleep(1000);
            }

            giudice.daiIlVia();

            // Attende che tutti i thread finiscano
            a1.join();
            a2.join();
            a3.join();

            System.out.println("\n Gara terminata!");
            // Il giudice ha già stampato la classifica
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
