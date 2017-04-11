package ca.collegeboreal.inf1069;

import javax.swing.*;

/*
 * MiniCalcExample.java
 * Ce programme simule une mini-calculatrice. Les données à lire sont un
 * caractère pour le code d'opération et deux nombres réels qui servent
 * d'opérandes.
 * Il permet de manipuler les fonctions, showInputDialog, showMessageDialog et
 * showConfirmDialog.
 * Auteur : Steve Tshibangu
 * Courriel: steve.tshibangu-mutshi.1@collegeboreal.ca
 * Cours: INF1069
 * Date : Hiver 2017
 */
public class MiniCalcExample {

    public static void main(String[] args) {
        String 		ch, message = "";
        int    		answer;
        char   		oper;
        double 		nb1 = 0,
                    nb2 = 0,
                    result = 0;
        boolean 	error;

        do
        {
            error = false;

            ch = JOptionPane.showInputDialog("Opération désirée (+, -, *, /) :");
            oper = ch.charAt(0);    // récupérer le «char» à la première position

            try {
                ch = JOptionPane.showInputDialog("Premier nombre :");
                nb1 = Double.parseDouble(ch);

                ch = JOptionPane.showInputDialog("Deuxième nombre :");
                nb2 = Double.parseDouble(ch);

                switch (oper)
                {
                    case '+' :
                        result = nb1 + nb2;
                        break;
                    case '-' :
                        result = nb1 - nb2;
                        break;
                    case '*' :
                        result = nb1 * nb2;
                        break;
                    case '/' :
                        result = nb1 / nb2;
                        break;
                    default :
                        message = "Erreur dans le caractère d'opération";
                        error = true;
                } // fin du switch (oper)
            } catch (Exception e) {
                error = true;
                message = e.getMessage();
            }

            if (error) {
                JOptionPane.showMessageDialog(
                        null,
                        message,
                        "Résultat de l'opération",
                        JOptionPane.ERROR_MESSAGE);
            } else
            {
                message = nb1 + " " + oper + " " + nb2 + " = " + result;
                JOptionPane.showMessageDialog(
                        null,
                        message,
                        "Résultat de l'opération",
                        JOptionPane.PLAIN_MESSAGE);
            }

            answer = JOptionPane.showConfirmDialog(
                    null,
                    "Voulez-vous effectuer une autre opération ?",
                    "Question",
                    JOptionPane.YES_NO_OPTION);

        } while (answer == JOptionPane.YES_OPTION);

        System.exit(0);

    }  	// fin de la méthode main
}   	// fin de la classe
