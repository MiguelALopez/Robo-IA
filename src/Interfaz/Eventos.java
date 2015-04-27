/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 02-abr-2015
 * Nombre del Archivo: Eventos.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * *********************************************
 */
package Interfaz;

import Busqueda.Aux;
import Busqueda.BusquedaCostoUniforme;
import Busqueda.BusquedaAsterisco;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
                        if (!url.equals("null")){
                            movimientoRobot.getMapa().uploadMap(jFileChooser.getSelectedFile());
                            menuPrincipal.textFieldLoadFiles.setText(url);
                        }
                    }
                }
        );
        menuPrincipal.buttonStart.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Condicional para verificar si ya se cargo el mapa
                        if (movimientoRobot.getMapa().isLoadMap()){
                            movimientoRobot.setVisible(true);
                            menuPrincipal.setVisible(false);
                            if (menuPrincipal.radioButtonBusqInformada1.isSelected()){
                                int potitionInitial[] = new Aux().findPosStart(movimientoRobot.getMapa().getPositionsMap());
                                int potitionEnd[] = new Aux().findPosEnd(movimientoRobot.getMapa().getPositionsMap());
                                BusquedaAsterisco busquedaCostoUniforme = new BusquedaAsterisco(
                                        movimientoRobot.getMapa().getPositionsMap(),
                                        potitionInitial[0],
                                        potitionInitial[1],
                                        potitionEnd[0],
                                        potitionEnd[1]       ,
                                        1
                                );
                                busquedaCostoUniforme.busqueda();
                                movimientoRobot.loadPath(busquedaCostoUniforme.getNodoMeta().getPath());
                                /*ArrayList<int[]> ints = busquedaCostoUniforme.getNodoMeta().getPath();
                                for (int i = 0; i < ints.size(); i++) {
                                    System.out.print("(" +ints.get(i)[0] + ", " +ints.get(i)[1] + ")");
                                }*/
                                movimientoRobot.getLabelNodosExpandidos().setText("Numero de Nodos Expandidos: " + busquedaCostoUniforme.getNodosExpandidos());
                                movimientoRobot.getLabelNodosCreados().setText("Numero de Nodos Creados: " + busquedaCostoUniforme.getNodosCreados());
                                movimientoRobot.getLabelCostoTotal().setText("Costo Total de la Solucion: " + busquedaCostoUniforme.getNodoMeta().getCost());
                                movimientoRobot.getLabelFactorRamificacion().setText("Factor de Ramificacion: " + 0);
                                /*System.out.println("\ncosto: " + busquedaCostoUniforme.getNodoMeta().getCost());
                                System.out.println("nodos expandidos: " + busquedaCostoUniforme.getNodosExpandidos());
                                System.out.println("nodos creados: " + busquedaCostoUniforme.getNodosCreados());*/
                            }
                            if (menuPrincipal.radioButtonBusqInformada2.isSelected()){
                                int potitionInitial[] = new Aux().findPosStart(movimientoRobot.getMapa().getPositionsMap());
                                int potitionEnd[] = new Aux().findPosEnd(movimientoRobot.getMapa().getPositionsMap());
                                BusquedaAsterisco busquedaCostoUniforme = new BusquedaAsterisco(
                                        movimientoRobot.getMapa().getPositionsMap(),
                                        potitionInitial[0],
                                        potitionInitial[1],
                                        potitionEnd[0],
                                        potitionEnd[1]       ,
                                        2
                                );
                                busquedaCostoUniforme.busqueda();
                                movimientoRobot.loadPath(busquedaCostoUniforme.getNodoMeta().getPath());
                                ArrayList<int[]> ints = busquedaCostoUniforme.getNodoMeta().getPath();
                                for (int[] anInt : ints) {
                                    System.out.print("(" + anInt[0] + ", " + anInt[1] + ")");
                                }
                                movimientoRobot.getLabelNodosExpandidos().setText("Numero de Nodos Expandidos: " + busquedaCostoUniforme.getNodosExpandidos());
                                movimientoRobot.getLabelNodosCreados().setText("Numero de Nodos Creados: " + busquedaCostoUniforme.getNodosCreados());
                                movimientoRobot.getLabelCostoTotal().setText("Costo Total de la Solucion: " + busquedaCostoUniforme.getNodoMeta().getCost());
                                movimientoRobot.getLabelFactorRamificacion().setText("Factor de Ramificacion: " + 0);
                                /*System.out.println("\ncosto: " + busquedaCostoUniforme.getNodoMeta().getCost());
                                System.out.println("nodos expandidos: " + busquedaCostoUniforme.getNodosExpandidos());
                                System.out.println("nodos creados: " + busquedaCostoUniforme.getNodosCreados());*/
                            }
                            if (menuPrincipal.radioButtonBusqNoInformada.isSelected()){
                                int potitionInitial[] = new Aux().findPosStart(movimientoRobot.getMapa().getPositionsMap());
                                BusquedaCostoUniforme busquedaCostoUniforme = new BusquedaCostoUniforme(
                                        movimientoRobot.getMapa().getPositionsMap(),
                                        potitionInitial[0],
                                        potitionInitial[1]
                                );
                                busquedaCostoUniforme.busqueda();
                                movimientoRobot.loadPath(busquedaCostoUniforme.getNodoMeta().getPath());
//                                System.out.println("El robot finalizo con: " + busquedaCostoUniforme.getNodoMeta().getCharge());
                                ArrayList<int[]> ints = busquedaCostoUniforme.getNodoMeta().getPath();
                                for (int[] anInt : ints) {
                                    System.out.print("(" + anInt[0] + ", " + anInt[1] + ")");
                                }
                                movimientoRobot.getLabelNodosExpandidos().setText("Numero de Nodos Expandidos: " + busquedaCostoUniforme.getNodosExpandidos());
                                movimientoRobot.getLabelNodosCreados().setText("Numero de Nodos Creados: " + busquedaCostoUniforme.getNodosCreados());
                                movimientoRobot.getLabelCostoTotal().setText("Costo Total de la Solucion: " + busquedaCostoUniforme.getNodoMeta().getCost());
                                movimientoRobot.getLabelFactorRamificacion().setText("Factor de Ramificacion: " + 0);
                                /*System.out.println("\ncosto: " + busquedaCostoUniforme.getNodoMeta().getCost());
                                System.out.println("nodos expandidos: " + busquedaCostoUniforme.getNodosExpandidos());
                                System.out.println("nodos creados: " + busquedaCostoUniforme.getNodosCreados());*/
                            }
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
}
