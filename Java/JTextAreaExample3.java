package ca.collegeboreal.inf1069;

import javax.swing.*;
import java.awt.Font;

/*
 * JTextAreaExample3.java (version utilisant un Font)
 * Auteur : Steve Tshibangu
 * Courriel: steve.tshibangu-mutshi.1@collegeboreal.ca
 * Cours: INF1069
 * Date : Hiver 2017
 */
public class JTextAreaExample3 {

    public static void main(String[] args)
    {
        JTextArea jTextArea = null;
        String message = "";
        int nb = 1;

        jTextArea = new JTextArea(10, 30);
        message += "nombre\tdouble\tquart\n";

        while (nb < 7)
        {
            message += "\n" + nb + "\t" + (nb * 2) + "\t" + (nb / 4.0);
            nb++;
        }

        jTextArea.setFont(new Font("Courier", Font.PLAIN, 12));
        jTextArea.setText(message);
        JOptionPane.showMessageDialog(
                null,
                jTextArea,
                "liste des nombres en Courier 12",
                JOptionPane.PLAIN_MESSAGE);

        jTextArea.setFont(new Font("CG Times", Font.BOLD+Font.ITALIC, 16));
        JOptionPane.showMessageDialog(
                null,
                jTextArea,
                "liste des nombres en Times 16",
                JOptionPane.PLAIN_MESSAGE);

        System.exit(0);
    }
}
