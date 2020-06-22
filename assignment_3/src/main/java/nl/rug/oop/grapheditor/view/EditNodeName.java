package nl.rug.oop.grapheditor.view;

import javax.swing.*;

public class EditNodeName {

    private static String newName;

    public static boolean setNewNodeName(String nodeName){
        newName = JOptionPane.showInputDialog(
                null,
                "Input a new name:",
                nodeName);
        if(newName != null){
            return true;
        }
        else{
            return false;
        }
    }

    public static String getNewName() {
        return newName;
    }

}
