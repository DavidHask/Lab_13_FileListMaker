import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileListMaker {

    static ArrayList<String> array = new ArrayList<>();
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        String input = "";
        boolean done = false;
        boolean needsToBeSaved = false;

        do {
            System.out.println("Please choose an option: ");
            System.out.println("A – Add an item to the list");
            System.out.println("D – Delete an item from the list");
            System.out.println("I – Insert an item into the list");
            System.out.println("M - Move one item to another index");
            System.out.println("O - Open a list file from the disk");
            System.out.println("S – Save the current list file to disk");
            System.out.println("C – Clear removes all the elements from the current list");
            System.out.println("V – View (i.e. display) the list");
            System.out.println("Q – Quit the program ");

            input = in.nextLine();
            if (input.equalsIgnoreCase("a")) {
                addItem(in);
                needsToBeSaved = true;
            }
            if (input.equalsIgnoreCase("d")) {
                deleteItem(in);
                needsToBeSaved = true;
            }
            if (input.equalsIgnoreCase("i")) {
                insertItem(in);
                needsToBeSaved = true;
            }
            if (input.equalsIgnoreCase("m")) {
                moveItem(in);
                needsToBeSaved = true;
            }
            if (input.equalsIgnoreCase("o")) {
                if (needsToBeSaved == true) {
                    boolean save = SafeInputs.getYNConfirm(in,
                            "The current file has unsaved changes. Save them?");
                    if (save == true) {
                        saveFile();
                    }
                }
                openItem();
            }
            if (input.equalsIgnoreCase("s")) {
                saveFile();
                needsToBeSaved = false;
            }
            if (input.equalsIgnoreCase("c")) {
                clear();
            }
            if (input.equalsIgnoreCase("v")) {
                view();
            }
            if (input.equalsIgnoreCase("q")) {
                if (needsToBeSaved == true) {
                    boolean save = SafeInputs.getYNConfirm(in,
                            "The current file has unsaved changes. Save them?");
                    if (save == true) {
                        saveFile();
                    }
                }
                done = quit();
            }

        } while (!done);
    }

    private static void addItem(Scanner pipe) {

        String item = SafeInputs.getNonZeroLenString(in, "What item do you want to add?");
        array.add(item);

    }

    private static void deleteItem(Scanner pipe) {

        int index = SafeInputs.getRangedInt(in, "What index do you want" +
                " to delete?", 0, array.size() - 1);
        array.remove(index);

    }

    private static void insertItem(Scanner pipe) {

        int index = SafeInputs.getRangedInt(in, "What index do you want" +
                " to replace?", 0, array.size() - 1);
        String item = SafeInputs.getNonZeroLenString(in, "What is the item?");
        array.set(index, item);

    }

    private static void moveItem(Scanner pipe) {

        int startIndex = SafeInputs.getRangedInt(in, "What index do you want to move?",
                0, array.size() - 1);
        int endIndex = SafeInputs.getRangedInt(in, "Where is the item moving to?",
                0, array.size() - 1);
        array.add(endIndex + 1, array.get(startIndex));
        array.remove(startIndex);

    }

    private static void openItem() {

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        File currentDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(currentDirectory);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

            selectedFile = chooser.getSelectedFile();
            Path file = selectedFile.toPath();

            try {

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                while (reader.ready()) {
                    System.out.println(reader.readLine());
                }
                reader.close();

            } catch (IOException e){
                System.out.println("Error occurred");
                e.printStackTrace();
            }

        } else {

            System.out.println("You must select a file");

        }

    }

    private static void saveFile() {

        String name = SafeInputs.getNonZeroLenString(in, "What would you like to name the file?");

        Path writeFile = new File(System.getProperty("user.dir")).toPath();
        writeFile = writeFile.resolve("src\\" + name + ".txt");
        System.out.println("Path is " + writeFile);

        try {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(writeFile, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for (int i = 0; i < array.size(); i++) {
                writer.write(array.get(i));
                writer.newLine();
            }
            writer.close();
            System.out.println("File successfully saved");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void clear() {

        for (int i = 0; i < array.size(); i++) {
            array.remove(i);
        }
    }

    private static void view() {

        System.out.println("Index:   Item: ");

        for (int i = 0; i < array.size(); i++ ) {
            System.out.println(i + "    " + array.get(i));
        }

    }

    private static boolean quit() {

        boolean done = false;

        done = SafeInputs.getYNConfirm(in, "Are you sure you want to quit?");

        return done;

    }
}