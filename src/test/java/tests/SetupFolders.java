package tests;

import org.testng.annotations.BeforeSuite;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SetupFolders {

    public final String TEMPORARY_FOLDER = "target" + File.separator + "logs";
    Path path = Paths.get(TEMPORARY_FOLDER);

    @BeforeSuite
    public void prepareFolderStructure(){
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
