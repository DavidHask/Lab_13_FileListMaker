import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileChooser {
    public static void main(String[] args) {

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
}
