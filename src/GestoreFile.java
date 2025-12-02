import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Classe che gestisce la scrittura e lettura di un file di testo.
 */
public class GestoreFile {

    private String nomeFile;

    /**
     * Costruttore del gestore file.
     * @param nomeFile nome del file da gestire
     */
    public GestoreFile(String nomeFile) {
        this.nomeFile = nomeFile;
    }

    /**
     * Scrive del testo nel file, sovrascrivendo il contenuto precedente.
     * @param testo contenuto da scrivere nel file
     */
    public void scriviFile(String testo) {
        try {
            FileWriter fw = new FileWriter(nomeFile);
            fw.write(testo);
            fw.close();
            System.out.println("Classifica salvata nel file.");
        } catch (IOException e) {
            System.out.println("Errore durante la scrittura del file.");
        }
    }

    /**
     * Legge il contenuto del file e lo stampa a schermo.
     */
    public void leggiFile() {
        try {
            System.out.println("\n--- CONTENUTO DEL FILE ---");
            for (String riga : Files.readAllLines(Paths.get(nomeFile))) {
                System.out.println(riga);
            }
        } catch (IOException e) {
            System.out.println("Errore durante la lettura del file.");
        }
    }
}
