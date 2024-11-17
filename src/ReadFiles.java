import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class ReadFiles {
    public static void main(String[] args) {

        Path readFile = new File(System.getProperty("user.dir")).toPath();
        readFile = readFile.resolve("src\\data.txt");
        System.out.println("Path is " + readFile);

        try {
            InputStream in =
                    new BufferedInputStream(Files.newInputStream(readFile, CREATE));
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(in));

            while (reader.ready()) {
                System.out.println(reader.readLine());
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
