package ca.collegeboreal.inf1069;

import javax.swing.*;

/*
 * JTextAreaExample2.java (version utilisant append)
 * Auteur : Steve Tshibangu
 * Courriel: steve.tshibangu-mutshi.1@collegeboreal.ca
 * Cours: INF1069
 * Date : Hiver 2017
 */
public class JTextAreaExample2 {
    public static void main(String[] args)
    {
        JTextArea jTextArea = null;
        int nb = 1;

        jTextArea = new JTextArea(10, 30);
        // ajouter à la fin du contenu du JTextArea
        jTextArea.append("nb\tdouble\tquart\n");

        while (nb < 7)
        {
            jTextArea.append(
                    "\n" + nb +
                    "\t" + (nb * 2) +
                    "\t" + (nb / 4.0));
            nb++;
        }

        JOptionPane.showMessageDialog(
                null,
                jTextArea,
                "liste des nombres",
                JOptionPane.PLAIN_MESSAGE);

        System.exit(0);
    }
}