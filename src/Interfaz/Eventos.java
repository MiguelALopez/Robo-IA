/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez - 1326691
 * Autor: Kellys Santa Gutierrez - 1325228
 * Autor: Mario Alejandro Payan - 1224601
 * Fecha: 02-abr-2015
 * Nombre del Archivo: Eventos.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
 */
package Interfaz;

import Busqueda.Aux;
import Busqueda.BusquedaCostoUniforme;
import Busqueda.BusquedaAsterisco;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Eventos {
    private MenuPrincipal menuPrincipal;
    private MovimientoRobot movimientoRobot;

    public Eventos(final MenuPrincipal menuPrincipal, final MovimientoRobot movimientoRobot){
        this.menuPrincipal = menuPrincipal;
        this.movimientoRobot = movimientoRobot;

        menuPrincipal.buttonLoadFiles.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Creo un JfileChooser para obtener el fichero
                        JFileChooser jFileChooser = new JFileChooser();
                        jFileChooser.showOpenDialog(menuPrincipal);
                        String url = String.valueOf(jFileChooser.getSelectedFile());
                        if (!url.equals("null")){// Se comprueba que no sea una url vacia
                            movimientoRobot.getMapa().uploadMap(jFileChooser.getSelectedFile()); //Carga el mapa
                            menuPrincipal.textFieldLoadFiles.setText(url); //Muestra la url en el TextField
                        }
                    }
                }
        );
        menuPrincipal.buttonStart.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Condicional para verificar si ya se cargo el mapa
                        if (movimientoRobot.getMapa().isLoadMap()){
                            movimientoRobot.setVisible(true); //Se hace visible la ventana que contiene el mapa
                            movimientoRobot.setMovimiento(0);  //Se inicia reinicia los movimientos del robot en 0
                            menuPrincipal.setVisible(false); //Se hace invisible la ventana principal
                            selectBusqueda(); //Carga el mapa dependiendo de la busqueda seleccionada
                        }else{
                            JOptionPane.showMessageDialog(null, "No se a cargado el mapa correctamente");
                        }
                    }
                }
        );
        movimientoRobot.buttonNext.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        movimientoRobot.next();
                    }
                }
        );

        movimientoRobot.buttonPrevious.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        movimientoRobot.previous();
                    }
                }
        );
        movimientoRobot.buttonReboot.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        movimientoRobot.reboot();
                    }
                }
        );
        movimientoRobot.buttonReturn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        movimientoRobot.setVisible(false);
                        menuPrincipal.setVisible(true);
                    }
                }
        );
    }

    //Metodo encargado de cargar el mapa dependiendo de que busqueda se a seleccionado
    public void selectBusqueda(){
        if (menuPrincipal.radioButtonBusqInformada1.isSelected()){
            //Se busca el inicio y el fin de la buqueda
            int potitionInitial[] = new Aux().findPosStart(movimientoRobot.getMapa().getPositionsMap());
            int potitionEnd[] = new Aux().findPosEnd(movimientoRobot.getMapa().getPositionsMap());
            BusquedaAsterisco busquedaAsterisco = new BusquedaAsterisco(
                    movimientoRobot.getMapa().getPositionsMap(),
                    potitionInitial[0],
                    potitionInitial[1],
                    potitionEnd[0],
                    potitionEnd[1],
                    1 //Indica que es la heuristica 1
            );
            busquedaAsterisco.busqueda(); //Se realiza la busqueda
            //Se carga el camino en la vista para que el robot lo recorra
            movimientoRobot.loadPath(busquedaAsterisco.getNodoMeta().getPath());
            //Se inicializa los detalles de la expancion de la busqueda
            movimientoRobot.getLabelNodosExpandidos().setText("Numero de Nodos Expandidos: " + busquedaAsterisco.getNodosExpandidos());
            movimientoRobot.getLabelNodosCreados().setText("Numero de Nodos Creados: " + busquedaAsterisco.getNodosCreados());
            movimientoRobot.getLabelCostoTotal().setText("Costo Total de la Solucion: " + busquedaAsterisco.getNodoMeta().getCost());
            movimientoRobot.getLabelFactorRamificacion().setText("Factor de Ramificacion: " + busquedaAsterisco.getFactRamificacion());
            movimientoRobot.getLabelProfundidad().setText("Profundidad del Arbol: " + busquedaAsterisco.getProfundidad());
        }
        if (menuPrincipal.radioButtonBusqInformada2.isSelected()){
            //Se busca el inicio y el fin de la buqueda
            int potitionInitial[] = new Aux().findPosStart(movimientoRobot.getMapa().getPositionsMap());
            int potitionEnd[] = new Aux().findPosEnd(movimientoRobot.getMapa().getPositionsMap());
            BusquedaAsterisco busquedaAsterisco = new BusquedaAsterisco(
                    movimientoRobot.getMapa().getPositionsMap(),
                    potitionInitial[0],
                    potitionInitial[1],
                    potitionEnd[0],
                    potitionEnd[1],
                    2 //Indica que es la heuristica 2
            );
            busquedaAsterisco.busqueda(); //Se realiza la busqueda
            //Se carga el camino en la vista para que el robot lo recorra
            movimientoRobot.loadPath(busquedaAsterisco.getNodoMeta().getPath());
            //Se inicializa los detalles de la expancion de la busqueda
            movimientoRobot.getLabelNodosExpandidos().setText("Numero de Nodos Expandidos: " + busquedaAsterisco.getNodosExpandidos());
            movimientoRobot.getLabelNodosCreados().setText("Numero de Nodos Creados: " + busquedaAsterisco.getNodosCreados());
            movimientoRobot.getLabelCostoTotal().setText("Costo Total de la Solucion: " + busquedaAsterisco.getNodoMeta().getCost());
            movimientoRobot.getLabelFactorRamificacion().setText("Factor de Ramificacion: " + busquedaAsterisco.getFactRamificacion());
            movimientoRobot.getLabelProfundidad().setText("Profundidad del Arbol: " + busquedaAsterisco.getProfundidad());
        }
        if (menuPrincipal.radioButtonBusqNoInformada.isSelected()){
            //Se busca el inicio de la buqueda
            int potitionInitial[] = new Aux().findPosStart(movimientoRobot.getMapa().getPositionsMap());
            BusquedaCostoUniforme busquedaCostoUniforme = new BusquedaCostoUniforme(
                    movimientoRobot.getMapa().getPositionsMap(),
                    potitionInitial[0],
                    potitionInitial[1]
            );
            busquedaCostoUniforme.busqueda(); //Se realiza la busqueda
            //Se carga el camino en la vista para que el robot lo recorra
            movimientoRobot.loadPath(busquedaCostoUniforme.getNodoMeta().getPath());
            //Se inicializa los detalles de la expancion de la busqueda
            movimientoRobot.getLabelNodosExpandidos().setText("Numero de Nodos Expandidos: " + busquedaCostoUniforme.getNodosExpandidos());
            movimientoRobot.getLabelNodosCreados().setText("Numero de Nodos Creados: " + busquedaCostoUniforme.getNodosCreados());
            movimientoRobot.getLabelCostoTotal().setText("Costo Total de la Solucion: " + busquedaCostoUniforme.getNodoMeta().getCost());
            movimientoRobot.getLabelFactorRamificacion().setText("Factor de Ramificacion: " + busquedaCostoUniforme.getFactRamificacion());
            movimientoRobot.getLabelProfundidad().setText("Profundidad del Arbol: " + busquedaCostoUniforme.getProfundidad());
        }
    }
}
