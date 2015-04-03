/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 02-abr-2015
 * Nombre del Archivo: MovimientoRobot.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * *********************************************
 */
package Interfaz;

import javax.swing.*;
import java.awt.*;

public class MovimientoRobot extends JFrame{

    private Mapa mapa;

    public MovimientoRobot(){
        super("Ubicame UV");
        setLayout(new GridLayout(1, 2, 20, 20));

        mapa = new Mapa();
        add(mapa);

        JPanel panelArbol = new JPanel();
        panelArbol.add(new JLabel("hola mundo"));
        panelArbol.setOpaque(false);

        add(panelArbol);

        setVisible(false);
        setSize(1000, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public Mapa getMapa() {
        return mapa;
    }
}
