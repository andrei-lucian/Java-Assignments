package nl.rug.oop.grapheditor.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class SaveAndLoad {

    private static JFrame parentFrame = new JFrame();
    private static String filePath;

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

    public static int saveCheck(){
        return JOptionPane.showConfirmDialog(
                null,
                "Would you like to save the graph?",
                "Save graph?",
                JOptionPane.YES_NO_OPTION);
    }

    public static String getFilePath() {
        return filePath;
    }
}
