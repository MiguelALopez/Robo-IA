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
import Busqueda.BusquedaAmplitud;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Eventos {
    private MenuPrincipal menuPrincipal;
    private MovimientoRobot movimientoRobot;
//    private BusquedaAmplitud busquedaAmplitud;

    public Eventos(final MenuPrincipal menuPrincipal, final MovimientoRobot movimientoRobot){
        this.menuPrincipal = menuPrincipal;
        this.movimientoRobot = movimientoRobot;

        menuPrincipal.buttonLoadFiles.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Creo un
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
        menuPrincipal.buttonStart.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Condicional para verificar si ya se cargo el mapa
                        if (movimientoRobot.getMapa().isLoadMap()){
                            movimientoRobot.setVisible(true);
                            menuPrincipal.setVisible(false);
                            if (menuPrincipal.radioButtonBusqInformada.isSelected()){
                                int potitionInitial[] = new Aux(0).findPosStart(movimientoRobot.getMapa().getPositionsMap());
                                BusquedaAmplitud busquedaAmplitud = new BusquedaAmplitud(
                                        movimientoRobot.getMapa().getPositionsMap(),
                                        potitionInitial[0],
                                        potitionInitial[1]
                                );
                                busquedaAmplitud.run();
                                movimientoRobot.loadPath(busquedaAmplitud.path);
                            }
                            if (menuPrincipal.radioButtonBusqInformada.isSelected()){

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
