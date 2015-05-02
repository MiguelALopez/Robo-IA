/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez - 1326691
 * Autor: Kellys Santa Gutierrez - 1325228
 * Autor: Mario Alejandro Payan - 1224601
 * Fecha: 02-abr-2015
 * Nombre del Archivo: FondoInicio.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
 */
package Interfaz;

import javax.swing.*;
import java.awt.*;

public class FondoInicio extends JPanel{

    private Image imagen;

    public FondoInicio() {
    }

    //Constructor que recibe la direccion de la imagen que se muestra de fondo
    public FondoInicio(String nombreImagen) {
        if (nombreImagen != null) {
            imagen = new ImageIcon(getClass().getResource(nombreImagen)).getImage();
        }
    }

    //Constructor que se le pasa la imagen que se muestra de fondo
    public FondoInicio(Image imagenInicial) {
        if (imagenInicial != null) {
            imagen = imagenInicial;
        }
    }

    //Metodo para cambiar la imagen de fondo con una direccion dada
    public void setImagen(String nombreImagen) {
        if (nombreImagen != null) {
            imagen = new ImageIcon(getClass().getResource(nombreImagen)).getImage();
        } else {
            imagen = null;
        }

        repaint();
    }

    //Metodo usado para cambiar la imagen de fondo por medio de una imagen que se le pase
    public void setImagen(Image nuevaImagen) {
        imagen = nuevaImagen;

        repaint();
    }

    //Metodo encargado de pintar la imagen en el fondo de el JPanel
    @Override
    public void paint(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);
        } else {

            setOpaque(true);
        }

        super.paint(g);
    }
}
