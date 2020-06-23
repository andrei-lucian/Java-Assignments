package nl.rug.oop.grapheditor.view;

import javax.swing.*;

/**
 * Displays new name window when user decides to rename a node
 */
public class EditNodeName {

    private static String newName;

    /**
     *
     * @param nodeName current name of the node
     * @return true if the user inputs a new name, else false
     */
    public static boolean setNewNodeName(String nodeName){
        newName = JOptionPane.showInputDialog(
                null,
                "Input a new name:",
                nodeName);
        return newName != null;
    }

    public static String getNewName() {
        return newName;
    }

}
