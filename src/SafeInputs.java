import java.util.Scanner;

public class SafeInputs {
    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = ""; // Set this to zero length. Loop runs until it isn't
        do
        {
            System.out.print("\n" + prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        }while(retString.length() == 0);

        return retString;

    }

    public static int getInt(Scanner pipe, String prompt) {

        int retInt = 0;
        boolean done = false;

        do {
            System.out.println(prompt + ": ");
            if (pipe.hasNextInt()) {
                retInt = pipe.nextInt();
                pipe.nextLine();
                done = true;
            } else {
                System.out.println("Invalid input. Try again.");
                pipe.nextLine();
            }
        } while (!done);

        return retInt;
    }

    public static double getDouble(Scanner pipe, String prompt) {

        double retDouble = 0;
        boolean done = false;

        do {
            System.out.println(prompt + ": ");
            if (pipe.hasNextDouble()) {
                retDouble = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            } else {
                System.out.println("Invalid input. Try again.");
                pipe.nextLine();
            }
        } while (!done);

        return retDouble;
    }

    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {

        double retDouble = 0;
        boolean done = false;

        do {
            System.out.println(prompt + "(" + low + "-" + high + "): ");
            if (pipe.hasNextDouble()) {
                retDouble = pipe.nextDouble();
                pipe.nextLine();
                if (retDouble >= low && retDouble <= high) {
                    done = true;
                } else {
                    System.out.println("Invalid input. Try again.");
                }

            } else {
                System.out.println("Invalid input. Try again.");
                pipe.nextLine();
            }
        } while (!done);

        return retDouble;
    }

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {

        int retInt = 0;
        boolean done = false;

        do {
            System.out.println(prompt + "(" + low + "-" + high + "): ");
            if (pipe.hasNextInt()) {
                retInt = pipe.nextInt();
                pipe.nextLine();
                if (retInt >= low && retInt <= high) {
                    done = true;
                } else {
                    System.out.println("Invalid input. Try again.");
                }

            } else {
                System.out.println("Invalid input. Try again.");
                pipe.nextLine();
            }
        } while (!done);

        return retInt;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt) {

        String input = "";
        boolean TF = false;
        boolean done = false;

        do {
            System.out.println(prompt + ": ");

            input = pipe.nextLine();
            if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N")) {
                done = true;
                pipe.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                        TF = true;
                } else {
                        TF = false;
                }
            } else {
                System.out.println("Invalid input. Try again.");
            }
        } while (!done);

        return TF;

    }

    public static String getRegExString(Scanner pipe, String prompt, String regEx) {

        String retString = "";
        boolean done = false;

        do {
            System.out.println(prompt + ": ");
            retString = pipe.nextLine();
            if (retString.contains("%")) {
                done = true;
            } else {
                System.out.println("Invalid input. Try again.");
            }
        } while (!done);

        return retString;

    }
}