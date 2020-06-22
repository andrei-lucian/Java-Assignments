package nl.rug.oop.grapheditor.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/** Displays the file chooser when the user decides to save or load a file. */
public class SaveAndLoad {

    private static final JFrame parentFrame = new JFrame();
    private static String filePath;

    /**
     * Shows a file chooser and asks user to select save location and filename
     * @return file to save
     */
    public static File chooseSaveLocation(){
        File fileToSave = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify directory and save name");
        int userSelection = fileChooser.showSaveDialog(parentFrame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
        }
        return fileToSave;
    }

    /**
     *
     * @return true if user selected a file to load or
     * false if the user decided to cancel the operation
     */
    public static boolean chooseFile() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("graph files", "graph");
        final JFileChooser fc = new JFileChooser();
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(parentFrame);
        File file;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            filePath = file.getAbsolutePath();
            return true;
        }
        else if(returnVal == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null, "you closed without selecting file");
        }
        return false;
    }

    public static String getFilePath() {
        return filePath;
    }
}
