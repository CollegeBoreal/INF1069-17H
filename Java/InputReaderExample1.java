package ca.collegeboreal.inf1069;

// Pour importer la librairie qui contient les classes nécessaires à la lecture.
import java.io.*;

/*
 * InputReaderExample1.java
 * Ce programme fait la lecture au clavier du nom et de l'âge de l'usager
 * pour ensuite afficher ces informations.
 * Auteur : Steve Tshibangu
 * Courriel: steve.tshibangu-mutshi.1@collegeboreal.ca
 * Cours: INF1069
 * Date : Hiver 2017
 */

public class InputReaderExample1 {

    public static void main(String[] args) {
        int age;
        String  name,
                ch;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try {
            // Ces deux lignes déclarent un tampon de lecture associé au clavier
            inputStreamReader = new InputStreamReader(System.in);
            bufferedReader = new BufferedReader(inputStreamReader);

            // Lire la ligne (de type String) et la placer dans la variable nom.
            System.out.print("\n\nVotre nom : ");
            name = bufferedReader.readLine();

            // Extraire l'entier de la ligne lue et le placer dans la variable age.
            System.out.print("\n\nVotre age : ");
            ch = bufferedReader.readLine();
            age = Integer.parseInt(ch);

            System.out.println(
                    "\nBonjour! " + name + ", vous êtes donc âgé(e) de "
                    + age + " ans");
            System.out.println("Puissiez-vous vivre encore longtemps!");

            // Lire la ligne mais ne pas la conserver
            // (ceci crée une attente pour la touche Enter
            // et donne le temps de voir l'affichage des résultats à l'écran)
            System.out.print("\nAppuyez sur Enter pour continuer");
            bufferedReader.readLine();
        } catch (Exception e) {
            // Affiche l'erreur
            System.err.println(e.toString());
        }
    }
}
