package nl.rug.oop.rpg;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Initialiser {

    public static void createProperties(String fileName){
        File configDirectory = new File("config");
        configDirectory.mkdir();

        Properties props = new Properties();
        props.setProperty("playerName", "jake");

        try (FileWriter fileWriter = new FileWriter(configDirectory + File.separator + fileName);{
            props.store(fileWriter, "Game properties");
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Could not write to file");
        }
    }

    public void initGameFromProps(String fileName){
        File configDirectory = new File("config");
        try (FileReader fileReader = new FileReader(configDirectory + File.separator + fileName);{
                
        }

}
