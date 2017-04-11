package ca.collegeboreal.inf1069;

// Pour importer la librairie qui contient les classes nécessaires
// à l'affichage des divers écrans graphiques
import javax.swing.*;

/*
 * InputReaderExample2.java (version graphique)
 * Ce programme fait la lecture au clavier du nom et de l'âge de l'usager
 * pour ensuite afficher ces informations
 * Auteur : Steve Tshibangu
 * Courriel: steve.tshibangu-mutshi.1@collegeboreal.ca
 * Cours: INF1069
 * Date : Hiver 2017
 */


public class InputReaderExample2 {

    public static void main (String args[])
    {
        int age;
        String  name,
                ch,
                message;

        try {
            name = JOptionPane.showInputDialog(
                    null,
                    "Votre nom : ",
                    "Écran de saisie du nom",
                    JOptionPane.PLAIN_MESSAGE);

            ch = JOptionPane.showInputDialog(
                    null,
                    "Votre âge : ",
                    "Écran de saisie de l'âge",
                    JOptionPane.PLAIN_MESSAGE);
            age = Integer.parseInt(ch);

            message = "Bonjour! " + name + ", vous êtes donc âgé(e) de "
                        + age + " ans\nPuissiez-vous vivre encore longtemps!";
            JOptionPane.showMessageDialog(
                    null,
                    message,
                    "Écran de sortie",
                    JOptionPane.PLAIN_MESSAGE);

            // Pour arrêter l'exécution du programme
            //(nécessaire quand on travaille en mode graphique)
            System.exit(0);
        } catch(Exception e) {
            System.err.println(e.toString());
        }
    }
}
