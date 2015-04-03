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
                        JFileChooser jFileChooser = new JFileChooser();
                        jFileChooser.showOpenDialog(menuPrincipal);
                        String url = String.valueOf(jFileChooser.getSelectedFile());
                        if (url!= "null"){
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

                        }else{
                            JOptionPane.showMessageDialog(null, "No se a cargado el mapa correctamente");
                        }
                    }
                }
        );
    }
}
