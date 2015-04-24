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
import java.util.ArrayList;

public class MovimientoRobot extends JFrame{

    private Mapa mapa;
    ArrayList<int[]> path;
//    int path[][];
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

    //Metodo encargado de inicializar el camino por el cual el robot se ira
    public void loadPath(ArrayList<int[]> path){
        this.path = path;
        mapa.setRobot(path.get(0));
        mapa.repaint();
        System.out.println("camino cargado");
    }

    public void next(){
        movimiento++;
        if (movimiento< path.size()){
            mapa.setRobot(path.get(movimiento));
            mapa.repaint();
        }else {
            movimiento--;
        }
    }

    public void previous(){
        movimiento--;
        if (movimiento >= 0){
            mapa.setRobot(path.get(movimiento));
            mapa.repaint();
        }else {
            movimiento++;
        }
    }

    public void reboot(){
        movimiento = 0;
        mapa.setRobot(path.get(movimiento));
        mapa.repaint();
    }

    public Mapa getMapa() {
        return mapa;
    }
}
