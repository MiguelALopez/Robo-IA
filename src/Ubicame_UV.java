/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 02-abr-2015
 * Nombre del Archivo: Ubicame_UV.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * *********************************************
 */

import Interfaz.Eventos;
import Interfaz.MenuPrincipal;

public class Ubicame_UV {
    public static void main(String[] args) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        Eventos eventos = new Eventos(menuPrincipal);
        menuPrincipal.setVisible(true);
    }
}