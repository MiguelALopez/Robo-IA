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

import com.sun.xml.internal.ws.client.sei.ResponseBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MovimientoRobot extends JFrame{

    private Mapa mapa;
    ArrayList<int[]> path;
    int movimiento;

    //Botones de interaccion del mapa
    JButton buttonNext;
    JButton buttonPrevious;
    JButton buttonReboot;
    JButton buttonReturn;

    //Mensajes de Detalles de la expancion
    JLabel labelNodosExpandidos;
    JLabel labelNodosCreados;
    JLabel labelCostoTotal;
    JLabel labelFactorRamificacion;

    public MovimientoRobot(){
        super("Ubicame UV");
        setLayout(new GridBagLayout());


        //Inicializo las variables
        GridBagConstraints constraintsMapa = new GridBagConstraints();
        constraintsMapa.gridx = 0;
        constraintsMapa.gridy = 0;
        constraintsMapa.gridwidth = 1;
        constraintsMapa.gridheight = 2;
        constraintsMapa.weightx = 1;
        constraintsMapa.weighty = 1;
        constraintsMapa.fill = GridBagConstraints.BOTH;
        mapa = new Mapa();
        buttonNext = new JButton("Siguiente");
        buttonPrevious = new JButton("Anterior");
        buttonReboot = new JButton("Reiniciar");
        buttonReturn = new JButton("Regresar");
        labelNodosExpandidos = new JLabel();
        labelNodosCreados = new JLabel();
        labelCostoTotal = new JLabel();
        labelFactorRamificacion = new JLabel();
        movimiento = 0;

        //Panel con las correspondientes opciones y detalles de la expancion de los nodos
//        JPanel panelOpciones = new JPanel();
//        panelOpciones.setSize(300, 550);
//        panelOpciones.setMaximumSize(new Dimension(300, 550));

        JPanel panelDetalles = new JPanel(new GridLayout(4,1,5,5));
        GridBagConstraints constraintsDetalles = new GridBagConstraints();
        constraintsDetalles.gridx = 1;
        constraintsDetalles.gridy = 0;
        constraintsDetalles.gridwidth = 1;
        constraintsDetalles.gridheight = 1;
        constraintsDetalles.weightx = 0;
        constraintsDetalles.weighty = 1;
        constraintsDetalles.anchor = GridBagConstraints.WEST;
        panelDetalles.add(labelNodosExpandidos);
        panelDetalles.add(labelNodosCreados);
        panelDetalles.add(labelCostoTotal);
        panelDetalles.add(labelFactorRamificacion);

        //Vista del mapa en el cual se hace el paso a paso
        JPanel panelPasoApaso = new JPanel(new GridLayout(4, 1, 5, 5));
        GridBagConstraints constraintsPasoApaso = new GridBagConstraints();
        constraintsPasoApaso.gridx = 1;
        constraintsPasoApaso.gridy = 1;
        constraintsPasoApaso.gridwidth = 1;
        constraintsPasoApaso.gridheight = 1;
        constraintsPasoApaso.weightx = 0;
        constraintsPasoApaso.weighty = 1;
        panelPasoApaso.add(buttonPrevious);
        panelPasoApaso.add(buttonNext);
        panelPasoApaso.add(buttonReboot);
        panelPasoApaso.add(buttonReturn);

//        panelOpciones.add(panelDetalles);
//        panelOpciones.add(panelPasoApaso);

        add(mapa,constraintsMapa);
//        add(new JTextField("Hola"), constraintsMapa);
        add(panelDetalles, constraintsDetalles);
        add(panelPasoApaso, constraintsPasoApaso);
//        add(panelOpciones);

//        add(new JPanel(), BorderLayout.WEST);
//        add(new JPanel(), BorderLayout.EAST);
//        add(mapa, BorderLayout.CENTER);
//        add(panelPasoApaso, BorderLayout.SOUTH);



        setVisible(false);
        setSize(700, 550);
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

    public JLabel getLabelNodosExpandidos() {
        return labelNodosExpandidos;
    }

    public void setLabelNodosExpandidos(JLabel labelNodosExpandidos) {
        this.labelNodosExpandidos = labelNodosExpandidos;
    }

    public JLabel getLabelNodosCreados() {
        return labelNodosCreados;
    }

    public void setLabelNodosCreados(JLabel labelNodosCreados) {
        this.labelNodosCreados = labelNodosCreados;
    }

    public JLabel getLabelCostoTotal() {
        return labelCostoTotal;
    }

    public void setLabelCostoTotal(JLabel labelCostoTotal) {
        this.labelCostoTotal = labelCostoTotal;
    }

    public JLabel getLabelFactorRamificacion() {
        return labelFactorRamificacion;
    }

    public void setLabelFactorRamificacion(JLabel labelFactorRamificacion) {
        this.labelFactorRamificacion = labelFactorRamificacion;
    }
}
