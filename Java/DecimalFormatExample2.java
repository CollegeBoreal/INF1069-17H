package ca.collegeboreal.inf1069;

import javax.swing.JOptionPane;
import java.text.*;

/*
 * DecimalFormatExample2.java
 * Version avec contrôle du format d'affichage pour le résultat
 * Ce programme lit des informations sur l'achat d'un article,
 * il calcule et affiche le coût total de l'achat avec les taxes
 * Auteur : Steve Tshibangu
 * Courriel: steve.tshibangu-mutshi.1@collegeboreal.ca
 * Cours: INF1069
 * Date : Hiver 2017
 */
public class DecimalFormatExample2 {

    public static void main (String args[])
    {
        final double TAUX_TPS = 0.07;
        final double TAUX_TVQ = 0.075;

        int noProd;
        int qte;
        double unitPrice;
        double total;
        String ch;
        String totalStr;

        try {
            ch = JOptionPane.showInputDialog("Numéro du produit :");
            noProd = Integer.parseInt(ch);

            qte = Integer.parseInt(JOptionPane.showInputDialog("Quantité :"));

            ch = JOptionPane.showInputDialog("Prix unitaire :");
            unitPrice = Double.parseDouble(ch);

            total = qte * unitPrice * (1 + TAUX_TPS) * (1 + TAUX_TVQ);

            totalStr = new DecimalFormat("0.00 $").format(total);

            JOptionPane.showMessageDialog(
                    null,
                    "Le coût total est de " + totalStr,
                    "Écran de résultats",
                    JOptionPane.PLAIN_MESSAGE);

            System.exit(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "Écran d'erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
