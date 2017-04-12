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
public class CourseManagementExample2 {
    static List<String> listCourses = new ArrayList<String>();

    public static void main(String[] args) {
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        String message = "", ch = "";
        int choiceM = 0, choiceS = 0;

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

        // afficher le menu principal
        try {
            do {
                inputStreamReader = new InputStreamReader(System.in);
                bufferedReader = new BufferedReader(inputStreamReader);

                message = "\n";
                for (int i = 0; i < mainMenu.length; i++) {
                    message += i + ". " + mainMenu[i] + "\n";
                }
                message += "\n[COURS] Que desirez-vous:";
                System.out.print(message);
                ch = bufferedReader.readLine();
                choiceM = Integer.parseInt(ch);

                switch (choiceM) {
                    case 0:
                        createCourse();
                        break;
                    case 1:
                        displayCourses();
                        break;
                    case 2:
                        do {
                            message = "\n";
                            for (int i = 0; i < menuF.length; i++) {
                                message += i + ". " + menuF[i] + "\n";
                            }
                            message += "\n[COURS] Que desirez-vous:";
                            System.out.print(message);
                            ch = bufferedReader.readLine();
                            choiceS = Integer.parseInt(ch);

                            switch (choiceS) {
                                case 0:
                                    modifyCourse(findCourse());
                                    break;
                                case 1:
                                    deleteCourse(findCourse());
                                    break;
                            }
                        } while (choiceS != 2);
                    break;
                }
            } while (choiceM != 3);
            quit();
        } catch (Exception e) {
            displayError(e.getMessage());
        }
    }

    /**
     * Cette fonction permet de sauvergarder la liste des cours
     * dans un fichier et termine l'application.
     */
    private  static void quit() {
        BufferedWriter bufferedWriter = null;
        String message = "";

        try {
            // initialise le fichier
            bufferedWriter = new BufferedWriter(
                                new FileWriter(new File("courses.dat")));

            for(String course: listCourses) {
                message += course + "\n";
            }

            // ecrire dans le fichier seulement si on a des cours dans la liste
            if(message.length() > 0) {
                bufferedWriter.write(message);
                bufferedWriter.close();
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
        System.err.println("\n[Erreur]\t" + message);
    }

    /**
     * Cette fonction permet de créer un cours.
     */
    private static void createCourse() {
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        String ch, code, name, city, course, day, message, choiceYN;
        int nbMaxInsc, choice, hr, min, posi;
        double price;
        String tabCities [] = {"Montréal", "Québec","Ottawa", "Toronto"};
        String tabDays [] = {"Lundi", "Mardi","Mercredi", "Jeudi", "Vendredi"};

        try {
            // Ces deux lignes déclarent un tampon de lecture associé au clavier
            inputStreamReader = new InputStreamReader(System.in);
            bufferedReader = new BufferedReader(inputStreamReader);

            // code du cours
            do {
                System.out.print(
                        "Entrez le numéro du cours [une lettre + 3 chiffres]:");
                code = bufferedReader.readLine();
            } while (code.length() != 4);

            // valider le code du cours
            posi = findCode(code);
            if (posi == -1) {
                // nom du cours
                do {
                    System.out.print(
                            "Entrez le nom du cours [minimum 3 caractères]:");
                    name = bufferedReader.readLine();
                } while (name.length() < 3);

                // nombre maximum d'inscriptions
                do {
                    System.out.print(
                            "Entrez le nombre maximum d'inscriptions:");
                    ch = bufferedReader.readLine();
                    nbMaxInsc = Integer.parseInt(ch);
                } while (nbMaxInsc <= 0);

                // tarif régulier
                do {
                    System.out.print("Entrez le tarif régulier:");
                    ch = bufferedReader.readLine();
                    price = Double.parseDouble(ch);
                } while (price <= 0);

                // jour du cours
                do {
                    message = "";
                    for(int i = 0; i < tabDays.length; i++) {
                        message += i + ". " + tabDays[i] + "\n";
                    }
                    message += "[Jour du cours] Quand se donne le cours:";
                    System.out.print(message);
                    ch = bufferedReader.readLine();
                    choice = Integer.parseInt(ch);
                } while (choice < 0 || choice >= tabDays.length);
                day = tabDays[choice];

                // heure du cours
                do {
                    System.out.print("Entrez l'heure du cours:");
                    ch = bufferedReader.readLine();
                    hr = Integer.parseInt(ch);
                } while (hr <= 0 || hr > 24);

                // minute du cours
                do {
                    System.out.print("Entrez les minutes du cours:");
                    ch = bufferedReader.readLine();
                    min = Integer.parseInt(ch);
                } while (min < 0 || min > 60);

                // ville du cours
                do {
                    message = "";
                    for(int i = 0; i < tabCities.length; i++) {
                        message += i + ". " + tabCities[i] + "\n";
                    }
                    message += "[Lieu du cours] Où se donne le cours:";
                    System.out.print(message);
                    ch = bufferedReader.readLine();
                    choice = Integer.parseInt(ch);
                } while (choice < 0 || choice >= tabCities.length);
                city = tabCities[choice];

                do {
                    // confirmer l'enregistrement
                    System.out.print(
                        "Voulez-vous enregistrer le nouveau cours [O ou N]:");
                    choiceYN = bufferedReader.readLine().toUpperCase();
                } while (!choiceYN.equals("O") && !choiceYN.equals("N"));
                // ajouter le cours dans la liste
                if (choiceYN.equals("O")) {
                    course = "" +
                            code + "\t" +
                            name + "\t" +
                            nbMaxInsc + "\t\t" +
                            price + "\t\t" +
                            city + "\t" +
                            day + "\t" +
                            hr + "\t" +
                            min;
                    listCourses.add(course);
                } else {
                    System.out.println("Création du cours annulé!");
                }
            } else {
                System.out.print("Le numéro entré est déja utilisé!");
            }
        } catch (Exception e) {
            displayError(e.toString());
        }
    }

    private static  void displayCourses() {
        String message = "";

        message = "Liste des cours\n";
        // texte d'en-tête
        message += "Code\tNom\t\tInsc\tPrice\tPlace\tDay\t\tHour\tMinutes\n";

        // texte du contenu
        for(String course: listCourses) {
            message += course + "\n";
        }

        // afficher le message
        System.out.println(message);
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
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        String ch = "";

        try {
            do {
                inputStreamReader = new InputStreamReader(System.in);
                bufferedReader = new BufferedReader(inputStreamReader);
                System.out.print(
                        "Entrez le numéro du cours [une lettre + 3 chiffres]:");
                ch = bufferedReader.readLine();
            } while (ch.length() != 4);

            for (String course : listCourses) {
                if (course.contains(ch)) {
                    return course;
                }
            }
        } catch (Exception e) {
            displayError(e.toString());
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
            System.out.println("Fonction non implémentée");
        } else {
            System.out.println("Le cours recherché n'a pas été trouvé!");
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
            System.out.println("Le cours recherché n'a pas été trouvé!");
        }
    }
}
