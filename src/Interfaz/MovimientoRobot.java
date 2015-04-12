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
    int path[][];
    int movimiento;

    JButton buttonNext;
    JButton buttonPrevious;
    JButton buttonReboot;
    JButton buttonReturn;

    public MovimientoRobot(){
        super("Ubicame UV");
        setLayout(new BorderLayout());

        //Inicializo las variables
        mapa = new Mapa();
        buttonNext = new JButton("Siguiente");
        buttonPrevious = new JButton("Anterior");
        buttonReboot = new JButton("Reiniciar");
        buttonReturn = new JButton("Regresar");
        movimiento = 0;

        JPanel panelPasoApaso = new JPanel();
        panelPasoApaso.setLayout(new GridLayout(1, 4, 5, 5));
        panelPasoApaso.add(buttonPrevious);
        panelPasoApaso.add(buttonNext);
        panelPasoApaso.add(buttonReboot);
        panelPasoApaso.add(buttonReturn);

        add(new JPanel(), BorderLayout.WEST);
        add(new JPanel(), BorderLayout.EAST);
        add(mapa, BorderLayout.CENTER);
        add(panelPasoApaso, BorderLayout.SOUTH);



        setVisible(false);
        setSize(500, 550);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void loadPath(int path[][]){
        this.path = path;
        System.out.println("camino cargado");
    }

    public void next(){
        movimiento++;
        if (movimiento< path.length){
            mapa.setRobot(path[movimiento]);
            mapa.repaint();
        }else {
            movimiento--;
        }
    }

    public void previous(){
        movimiento--;
        if (movimiento >= 0){
            mapa.setRobot(path[movimiento]);
            mapa.repaint();
        }else {
            movimiento++;
        }
    }

    public void reboot(){
        movimiento = 0;
        mapa.setRobot(path[movimiento]);
        mapa.repaint();
    }

    public Mapa getMapa() {
        return mapa;
    }
}
