/**
 * Rappresenta un'auto che partecipa alla gara.
 * Ogni auto è eseguita in un thread separato e avanza in base alla sua velocità.
 */
public class Auto implements Runnable {
    private String nome;
    private double metri = 0;
    private double velocita;
    private double distanzaTotale;
    private Giudice giudice;

    /**
     * Costruttore per creare un'auto partecipante alla gara.
     *
     * @param nome nome dell'auto
     * @param velocita velocità in metri al secondo
     * @param distanzaTotale distanza totale da percorrere
     * @param giudice giudice che gestisce la gara
     */
    public Auto(String nome, double velocita, double distanzaTotale, Giudice giudice) {
        this.nome = nome;
        this.velocita = velocita;
        this.distanzaTotale = distanzaTotale;
        this.giudice = giudice;
    }

    /**
     * Metodo eseguito dal thread dell'auto.
     * Attende il via del giudice, poi ogni secondo aumenta i metri percorsi
     * in base alla velocità. Quando raggiunge la distanza totale,
     * comunica l'arrivo al giudice.
     */
    @Override
    public void run() {
        try {
            giudice.attendiPartenza();
            System.out.println(nome + " è partito!");

            while (!giudice.garaFinita() && metri < distanzaTotale) {
                Thread.sleep(1000);
                metri += velocita;

                synchronized (System.out) {
                    System.out.printf("%s ha percorso %.1f metri%n", nome, metri);
                }

                if (metri >= distanzaTotale) {
                    giudice.registraArrivo(nome);
                }
            }

        } catch (InterruptedException e) {
            System.out.println(nome + " si è ritirato dalla gara");
        }
    }
}
