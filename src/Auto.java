public class Auto implements Runnable {
    private String nome;
    private double metri = 0;
    private double velocita;
    private double distanzaTotale;
    private Giudice giudice;

    public Auto(String nome, double velocita, double distanzaTotale, Giudice giudice) {
        this.nome = nome;
        this.velocita = velocita;
        this.distanzaTotale = distanzaTotale;
        this.giudice = giudice;
    }

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
