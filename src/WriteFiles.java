import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class WriteFiles {
    public static void main(String[] args) {

        Path writeFile = new File(System.getProperty("user.dir")).toPath();
        writeFile = writeFile.resolve("src\\output.txt");
        System.out.println("Path is " + writeFile);

        try {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(writeFile, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            String line1 = "How are you today?";
            String line2 = "Today is Monday";
            writer.write(line1);
            writer.newLine();
            writer.write(line2);
            writer.newLine();
            writer.write("This is another line");
            writer.close();
            System.out.println("File successfully created");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
