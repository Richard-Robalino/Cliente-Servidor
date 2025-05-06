package cliente.gui;

import cliente.clase.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteGUI extends JFrame {
    private JTextField campoNum1;
    private JTextField campoNum2;
    private JComboBox<String> comboOperacion;
    private JButton botonEnviar;
    private JLabel etiquetaResultado;

    public ClienteGUI() {
        setTitle("Cliente UDP - Calculadora");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel label1 = new JLabel("Número 1:");
        label1.setBounds(20, 20, 100, 25);
        add(label1);

        campoNum1 = new JTextField();
        campoNum1.setBounds(120, 20, 200, 25);
        add(campoNum1);

        JLabel label2 = new JLabel("Número 2:");
        label2.setBounds(20, 60, 100, 25);
        add(label2);

        campoNum2 = new JTextField();
        campoNum2.setBounds(120, 60, 200, 25);
        add(campoNum2);

        JLabel labelOperacion = new JLabel("Operación:");
        labelOperacion.setBounds(20, 100, 100, 25);
        add(labelOperacion);

        comboOperacion = new JComboBox<>(new String[]{"suma", "resta", "multiplicacion", "division"});
        comboOperacion.setBounds(120, 100, 200, 25);
        add(comboOperacion);

        botonEnviar = new JButton("Enviar");
        botonEnviar.setBounds(120, 140, 100, 30);
        add(botonEnviar);

        etiquetaResultado = new JLabel("Resultado:");
        etiquetaResultado.setBounds(20, 180, 300, 25);
        add(etiquetaResultado);

        botonEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int num1 = Integer.parseInt(campoNum1.getText());
                    int num2 = Integer.parseInt(campoNum2.getText());
                    String operacion = (String) comboOperacion.getSelectedItem();

                    Cliente cliente = new Cliente();
                    String respuesta = cliente.enviarOperacion("127.0.0.1", 5000, operacion, num1, num2);
                    etiquetaResultado.setText(respuesta);
                } catch (NumberFormatException ex) {
                    etiquetaResultado.setText("Error: Ingresa números válidos.");
                }
            }
        });
    }
}
