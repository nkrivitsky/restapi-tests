package framework.utils;

import org.testng.reporters.jq.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;

public class ResourceFile {
    private ResourceFile() {
    }

    private static String getPathToResourceFile(String path) {
        File file = null;
        URL res = Main.class.getClassLoader().getResource(path);
        try {
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    public static String getStringFromResourceFile(String fileName) {
        return getTextFromFile(getPathToResourceFile(fileName));
    }

    private static String getTextFromFile(String filePath) {
        StringBuilder allInfoFromFile = new StringBuilder();

        File file = new File(filePath);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNext()) {
            allInfoFromFile.append(sc.nextLine());
        }
        sc.close();
        return allInfoFromFile.toString();
    }
}
