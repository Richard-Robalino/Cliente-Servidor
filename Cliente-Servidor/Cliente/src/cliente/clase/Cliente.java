package cliente.clase;

import java.io.IOException;
import java.net.*;

public class Cliente {
    public String enviarOperacion(String ip, int puerto, String operacion, int num1, int num2) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress direccionIp = InetAddress.getByName(ip);

            String mensaje = operacion + "," + num1 + "," + num2;
            byte[] bufferSalida = mensaje.getBytes();

            DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionIp, puerto);
            socket.send(paqueteSalida);

            byte[] bufferEntrada = new byte[1024];
            DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
            socket.receive(paqueteEntrada);

            return new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}
