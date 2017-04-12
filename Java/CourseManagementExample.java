package ca.collegeboreal.inf1069;

import java.io.*;
import java.util.*;
import javax.swing.*;

/*
 * CourseManagementExample.java
 * Cette classe est un exemple pour permettre de réaliser le TP 3.
 * Auteur : Steve Tshibangu
 * Courriel: steve.tshibangu-mutshi.1@collegeboreal.ca
 * Cours: INF1069
 * Date : Hiver 2017
 */
public class CourseManagementExample {
    static List<String> listCourses = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        // menu principal
        String mainMenu[] = {
                "Creer",
                "Lister",
                "Chercher",
                "Quitter"};

        // sous-menu
        String menuF[] = {
                "Modifier",
                "Supprimer",
                "Quitter"};
        int choiceM, choiceS;

        // afficher le menu principal
        do {
            choiceM = JOptionPane.showOptionDialog(
                        null,
                        "Que desirez-vous?",
                        "COURS",
                        0,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        mainMenu,
                        mainMenu[0]);
            switch(choiceM){
                case 0:	createCourse(); break;
                case 1: displayCourses(); break;
                case 2:
                    choiceS = JOptionPane.showOptionDialog(
                                null,
                                "Que desirez-vous?",
                                "COURS",
                                0,
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                menuF,
                                menuF[0]);
                    switch(choiceS){
                        case 0:
                            modifyCourse(findCourse());
                            break;
                        case 1:
                            deleteCourse(findCourse());
                            break;
                    }
                break;
            }
        } while(choiceM != 3);

        quit();
    }

    /**
     * Cette fonction permet de sauvergarder la liste des cours
     * dans un fichier et termine l'application.
     */
    private  static void quit() {
        BufferedWriter writer = null;
        String message = "";

        try {
            // initialise le fichier
            writer = new BufferedWriter(
                        new FileWriter(new File("courses.dat")));

            for(String course: listCourses) {
                message += course + "\n";
            }

            // ecrire dans le fichier seulement si on a des cours dans la liste
            if(message.length() > 0) {
                writer.write(message);
                writer.close();
            }
        } catch (Exception e) {
            displayError(e.getMessage());
        }
    }

    /**
     * Cette fonction permet d'afficher un message d'erreur.
     * @param message
     */
    private static  void displayError(String message) {
        JTextArea jTextArea = null;

        jTextArea = new JTextArea(10, 30);
        jTextArea.setText(message);
        JOptionPane.showMessageDialog(
                null,
                jTextArea,
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Cette fonction permet de créer un cours.
     */
    private static void createCourse() {
        String ch, code, name, city = "", course = "", day = "";
        int nbMaxInsc, choice, hr, min, posi;
        double price;
        String tabCities [] = {"Montréal", "Québec","Ottawa", "Toronto"};
        String tabDays [] = {"Lundi", "Mardi","Mercredi", "Jeudi", "Vendredi"};

        try {
            // code du cours
            do {
                code = JOptionPane.showInputDialog(
                        null,
                        "Entrez le numéro du cours [une lettre + 3 chiffres]:");
            } while (code.length() != 4);

            // valider le code du cours
            posi = findCode(code);
            if (posi == -1) {
                // nom du cours
                do {
                    name = JOptionPane.showInputDialog(
                            null,
                            "Entrez le nom du cours [minimum 3 caractères]:");
                } while (name.length() < 3);

                // nombre maximum d'inscriptions
                do {
                    ch = JOptionPane.showInputDialog(
                            null,
                            "Entrez le nombre maximum d'inscriptions:");
                    nbMaxInsc = Integer.parseInt(ch);
                } while (nbMaxInsc <= 0);

                // tarif régulier
                do {
                    ch = JOptionPane.showInputDialog(
                            null,
                            "Entrez le tarif régulier:");
                    price = Double.parseDouble(ch);
                } while (price <= 0);

                // jour du cours
                choice = JOptionPane.showOptionDialog(
                        null,
                        "Quand se donne le cours?",
                        "Jour du cours",
                        0,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        tabDays,
                        tabDays[0]);
                day = tabDays[choice];

                // heure du cours
                do {
                    ch = JOptionPane.showInputDialog(
                            null,
                            "Entrez l'heure du cours:");
                    hr = Integer.parseInt(ch);
                } while (hr <= 0 || hr > 24);

                // minute du cours
                do {
                    ch = JOptionPane.showInputDialog(
                            null,
                            "Entrez les minutes du cours:");
                    min = Integer.parseInt(ch);
                } while (min < 0 || min > 60);

                // ville du cours
                choice = JOptionPane.showOptionDialog(
                        null,
                        "Où se donne le cours?",
                        "Lieu du cours",
                        0,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        tabCities,
                        tabCities[0]);
                city = tabCities[choice];

                // confirmer l'enregistrement
                choice = JOptionPane.showConfirmDialog(
                        null,
                        "Voulez-vous enregistrer le nouveau cours?",
                        "",
                        JOptionPane.YES_NO_OPTION);

                // ajouter le cours dans la liste
                if (choice == JOptionPane.YES_OPTION) {
                    course = "" +
                            code + "\t" +
                            name + "\t" +
                            nbMaxInsc + "\t" +
                            price + "\t" +
                            city + "\t" +
                            day + "\t" +
                            hr + "\t" +
                            min;
                    listCourses.add(course);
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Création du cours annulé!");
                }
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Le numéro entré est déja utilisé!");
            }
        } catch (Exception e) {
            displayError(e.getMessage());
        }
    }

    private static  void displayCourses() {
        JTextArea jTextArea = null;
        String message = "";

        jTextArea = new JTextArea(10, 40);
        // texte d'en-tête
        message += "Code\tNom\tNB Max Insc\tPrice\tPlace\tDay\tHour\tMinutes\n";

        // texte du contenu
        for(String course: listCourses) {
            message += course + "\n";
        }

        // afficher le message
        jTextArea.setText(message);
        JOptionPane.showMessageDialog(
                null,
                jTextArea,
                "liste des cours",
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Cette fonction permet de chercher un code de cours.
     * @param ch
     * @return 1 si trouvé, sinon -1
     */
    private static  int findCode(String ch) {
        for (String course : listCourses) {
            if(course.contains(ch)) {
                return 1;
            }
        }
        return -1;
    }

    /**
     * Cette fonction permet de chercher un cours.
     * @return le cours si trouvé
     */
    private static  String findCourse() {
        String ch = "";
        do {
            ch = JOptionPane.showInputDialog(
                    null,
                    "Entrez le numéro du cours [une lettre + 3 chiffres]:");
        } while (ch.length() < 3);

        for (String course : listCourses) {
            if(course.contains(ch)) {
                return course;
            }
        }
        return "";
    }

    /**
     * Cette fonction permet de modifier un cours.
     * @param course
     */
    private  static void modifyCourse(String course) {
        if (course.length() > 0) {
            // TODO modify course
            JOptionPane.showMessageDialog(
                    null,
                    "Fonction non implémentée");
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Le cours recherché n'a pas été trouvé!");
        }
    }

    /**
     * Cette fonction permet de supprimer un cours.
     * @param course
     */
    private  static void deleteCourse(String course) {
        if (course.length() > 0) {
            listCourses.remove(course);
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Le cours recherché n'a pas été trouvé!");
        }
    }
}
