package cliente.test;

import cliente.gui.ClienteGUI;

public class Test {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new ClienteGUI().setVisible(true));
    }
}
