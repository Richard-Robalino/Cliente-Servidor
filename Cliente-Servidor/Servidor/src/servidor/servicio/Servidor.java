package servidor.servicio;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor {
    public void servicio() {
        int puerto = 5000;
        try {
            DatagramSocket socket = new DatagramSocket(puerto);
            System.out.println("Servidor UDP corriendo en el puerto " + puerto + "...");

            byte[] bufferEntrada = new byte[1024];
            while (true) {
                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                socket.receive(paqueteEntrada);

                String mensajeRecibido = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
                System.out.println("Mensaje recibido: " + mensajeRecibido);

                // Formato esperado: operacion,num1,num2
                String[] partes = mensajeRecibido.split(",");
                if (partes.length == 3) {
                    String operacion = partes[0].trim();
                    int num1 = Integer.parseInt(partes[1].trim());
                    int num2 = Integer.parseInt(partes[2].trim());
                    String resultado;

                    switch (operacion) {
                        case "suma":
                            resultado = "Resultado: " + (num1 + num2);
                            break;
                        case "resta":
                            resultado = "Resultado: " + (num1 - num2);
                            break;
                        case "multiplicacion":
                            resultado = "Resultado: " + (num1 * num2);
                            break;
                        case "division":
                            resultado = num2 != 0 ? "Resultado: " + ((double) num1 / num2) : "Error: División por cero";
                            break;
                        default:
                            resultado = "Operación no válida";
                    }

                    byte[] bufferSalida = resultado.getBytes();
                    DatagramPacket paqueteSalida = new DatagramPacket(
                            bufferSalida,
                            bufferSalida.length,
                            paqueteEntrada.getAddress(),
                            paqueteEntrada.getPort()
                    );
                    socket.send(paqueteSalida);
                    System.out.println("Respuesta enviada: " + resultado);
                } else {
                    System.out.println("Mensaje con formato incorrecto.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
