/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez - 1326691
 * Autor: Kellys Santa Gutierrez - 1325228
 * Autor: Mario Alejandro Payan - 1224601
 * Fecha: 02-abr-2015
 * Nombre del Archivo: Ubicame_UV.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
 */

import Interfaz.Eventos;
import Interfaz.MenuPrincipal;
import Interfaz.MovimientoRobot;

public class Ubicame_UV {
    public static void main(String[] args) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        MovimientoRobot movimientoRobot = new MovimientoRobot();
        Eventos eventos = new Eventos(menuPrincipal, movimientoRobot);
        menuPrincipal.setVisible(true);
    }
}
