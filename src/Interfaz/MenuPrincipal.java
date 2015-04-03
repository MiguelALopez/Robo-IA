/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 02-abr-2015
 * Nombre del Archivo: MenuPrincipal.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * *********************************************
 */
package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class MenuPrincipal extends JFrame {

    JButton buttonLoadFiles;
    JButton buttonStart;
    JTextField textFieldLoadFiles;
    JRadioButton radioButtonBusqInformada;
    JRadioButton radioButtonBusqNoInformada;
    ButtonGroup buttonGroupBusquedas;

    public MenuPrincipal(){
        super("Ubicame UV");

        //Inicializo los componentes
        buttonLoadFiles = new JButton("Examinar");
        buttonStart = new JButton("Comenzar");
        textFieldLoadFiles = new JFormattedTextField("/");
        textFieldLoadFiles.setEditable(false);
        buttonGroupBusquedas = new ButtonGroup();
        radioButtonBusqInformada = new JRadioButton("Busqueda Informada", true);
        radioButtonBusqInformada.setOpaque(false);
        radioButtonBusqNoInformada = new JRadioButton("Busqueda No Informada");
        radioButtonBusqNoInformada.setOpaque(false);
        buttonGroupBusquedas.add(radioButtonBusqInformada);
        buttonGroupBusquedas.add(radioButtonBusqNoInformada);

        //Se crea el JPanel con el fondo de inicio
        FondoInicio fondoInicio = new FondoInicio("../Img/robo-fondo.jpg");
        fondoInicio.setLayout(new GridLayout(3, 1));

        //Panel contenedor del titulo
        JPanel panelTitle = new JPanel();
        panelTitle.setOpaque(false);
        panelTitle.setLayout(new GridLayout());
        JLabel labelTitle = new JLabel("Ubicame UV", JLabel.CENTER);
        Font fontTitle = loadFont("../Fonts/MagmaWave_Caps.ttf", Font.BOLD, 80);//Se carga la fuente prederterminada
        labelTitle.setFont(fontTitle);
        labelTitle.setForeground(Color.decode("#aed581"));
        panelTitle.add(labelTitle);

        //Panel contenedor de elementos de la mitad
        JPanel panelContenidoMedio = new JPanel();
        panelContenidoMedio.setOpaque(false);
        panelContenidoMedio.setLayout(new BorderLayout());

        //Panel contenedor de carga de archivos
        JPanel panelFilesContent = new JPanel(new BorderLayout(20, 20));
        panelFilesContent.setOpaque(false);
        panelFilesContent.add(new JLabel("      "), BorderLayout.WEST);
        panelFilesContent.add(textFieldLoadFiles, BorderLayout.CENTER);
        JPanel panelButton = new JPanel(new BorderLayout());
        panelButton.setOpaque(false);
        panelButton.add(buttonLoadFiles, BorderLayout.CENTER);
        panelButton.add(new JLabel("    "), BorderLayout.EAST);
        panelFilesContent.add(panelButton, BorderLayout.EAST);


        //Panel contenedor de radioButton y buttonStart
        JPanel panelOpciones = new JPanel(new BorderLayout());
        panelOpciones.setOpaque(false);

        //Panel contenedor de radioButton
        JPanel panelRadioButton = new JPanel(new GridLayout(2, 1, 0, 0));
        panelRadioButton.setOpaque(false);
        panelRadioButton.add(radioButtonBusqInformada);
        panelRadioButton.add(radioButtonBusqNoInformada);

        //Panel contenedor de buttonStart
        JPanel panelStart = new JPanel(new FlowLayout());
        panelStart.setOpaque(false);
        panelStart.add(buttonStart);

        //Añade los radioButton y el buttonStart a el contenedor
        panelOpciones.add(new JLabel("           "),BorderLayout.WEST);
        panelOpciones.add(panelRadioButton, BorderLayout.CENTER);
        panelOpciones.add(panelStart, BorderLayout.SOUTH);

        //Añade el buscador de archivos y lao opciones de busqueda al contenedor del centro
        panelContenidoMedio.add(panelFilesContent, BorderLayout.NORTH);
        panelContenidoMedio.add(panelOpciones, BorderLayout.WEST);

        //Añade el titulo y las opciones del centro a la ventana principal
        fondoInicio.add(panelTitle, BorderLayout.NORTH);
        fondoInicio.add(panelContenidoMedio, BorderLayout.CENTER);
        add(fondoInicio);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
    }

    // Metodo encargado de cargar un tipo de fuente
    public Font loadFont(String url, int style, int size){
        Font font;
        try {
            //Se carga la fuente
            InputStream is =  getClass().getResourceAsStream(url);
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(style,size);
        } catch (Exception ex) {
            //Si existe un error se carga fuente por defecto ARIAL
            System.err.println(url + " No se cargo la fuente");
            font = new Font("Arial", style, size);
        }
        return font;
    }


}
