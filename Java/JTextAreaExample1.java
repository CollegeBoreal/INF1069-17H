package ca.collegeboreal.inf1069;

import javax.swing.*;

/*
 * JTextAreaExample1.java
 * Exemple d'utilisation d'un JTextArea pour afficher
 * des résultats en colonnes
 * méthode setText pour assigner un String au contenu du JTextArea
 * Cours: INF1069
 * Date : Hiver 2017
 */
public class JTextAreaExample1 {

    public static void main(String[] args) {
        JTextArea jTextArea = null;
        String message = "";
        int nb = 1;

        // Initialier 10 lignes de 30 colonnes (ou plus au besoin)
        jTextArea = new JTextArea(10, 30);
        message += "nombre\tdouble\tquart\n";

        while (nb < 7)
        {
            // concaténer au texte (ajouter à la fin du texte)
            message += "\n" + nb + "\t" + (nb * 2) + "\t" + (nb / 4.0);
            nb++;
        }

        // mettre le texte dans le JTextArea
        jTextArea.setText(message);
        JOptionPane.showMessageDialog(
                null,
                jTextArea,
                "liste des nombres",
                JOptionPane.PLAIN_MESSAGE);

        System.exit(0);
    }
}
