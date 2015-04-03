/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 02-abr-2015
 * Nombre del Archivo: Mapa.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * *********************************************
 */
package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Mapa extends JPanel {

    //Variables de las imagenes
    private Image imgGoal;
    private Image imgPeople;
    private Image imgPower;
    private Image imgProhibit;
    private Image imgRobo1;
    private Image imgRobo2;
    private Image imgStart;
    private Image imgWall;
    private Image imgWater;

    //Matriz que contiene los valores del mapa
    private int positionsMap[][];

    //Verifica si se a cargado el mapa
    private boolean loadMap;

    //Variable encargada de escalar el juego para que se tenga el tamaño correcto
    int factorEscala;

    public Mapa(){
        loadImages();
        loadMap = false;
        factorEscala = 0;
    }

    //Metodo encargado de cargar las imagenes de la carpeta de imagenes
    public void loadImages(){
        try{
            imgGoal = new ImageIcon(getClass().getResource("../Img/iconos/goal.png")).getImage();
            imgPeople = new ImageIcon(getClass().getResource("../Img/iconos/people.png")).getImage();
            imgPower = new ImageIcon(getClass().getResource("../Img/iconos/power.png")).getImage();
            imgProhibit = new ImageIcon(getClass().getResource("../Img/iconos/prohibit.png")).getImage();
            imgRobo1 = new ImageIcon(getClass().getResource("../Img/iconos/robo1.png")).getImage();
            imgRobo2 = new ImageIcon(getClass().getResource("../Img/iconos/robo2.png")).getImage();
            imgStart = new ImageIcon(getClass().getResource("../Img/iconos/start.png")).getImage();
            imgWall = new ImageIcon(getClass().getResource("../Img/iconos/wall.png")).getImage();
            imgWater = new ImageIcon(getClass().getResource("../Img/iconos/water.png")).getImage();
        }catch (NullPointerException e){
            System.err.println("Error al cargar las imagenes de los iconos");
            imgGoal = Toolkit.getDefaultToolkit().getImage("");
            imgPeople = Toolkit.getDefaultToolkit().getImage("");
            imgPower = Toolkit.getDefaultToolkit().getImage("");
            imgProhibit = Toolkit.getDefaultToolkit().getImage("");
            imgRobo1 = Toolkit.getDefaultToolkit().getImage("");
            imgRobo2 = Toolkit.getDefaultToolkit().getImage("");
            imgStart = Toolkit.getDefaultToolkit().getImage("");
            imgWall = Toolkit.getDefaultToolkit().getImage("");
            imgWater = Toolkit.getDefaultToolkit().getImage("");
        }
    }

   //Metodo encargado de cargar los valores del arreglo del mapa pasandole un archivo externo
    public void uploadMap(File file){
        String buffer;
        boolean correct = true; // variable usada para verificar si esta en el rango correcto de numeros
        if (file != null){
            try {
                FileReader archivos = new FileReader(file);
                BufferedReader lee = new BufferedReader(archivos);
                if ((buffer = lee.readLine())!=null){
                    int i = Integer.parseInt(buffer);
                    positionsMap = new int[i][i];
                }
                //Ciclo para almacenar los datos del texto en el arreglo del mapa positionsMap
                for (int i = 0; (buffer=lee.readLine())!=null && correct; i++) {
                    StringTokenizer st = new StringTokenizer(buffer, " ");
                    for (int j = 0; st.hasMoreElements() && correct; j++) {
                        int number = Integer.parseInt(st.nextToken());
                        //Condicional para verificar si esta en el rango correcto de numeros( de 0 a 7 )
                        if ( 0 <= number && number <= 7){
                            positionsMap[i][j] = number;
                        }else {
                            correct = false;
                        }
                    }
                }
                lee.close();
                if (correct){
                    loadMap = true;
                    System.out.println("Mapa cargado exitosamente");
                }else {
                    System.err.println("No se an ingresado correctamente los valores");
                }

            }catch (IOException e){
                loadMap = false;
                System.err.println("Error al cargar el mapa");
            }catch (ArrayIndexOutOfBoundsException e){
                loadMap = false;
                System.err.println("Error al cargar el mapa hay elementos de mas");
            }catch (NumberFormatException e){
                System.err.println("Error al cargar el mapa formato de numeros no correcto");
            }
        }
    }

    public void paint(Graphics g){
        System.out.println("x= " + getWidth() + " y= " + getHeight());
        if (loadMap){
            Image imageBuffer = createImage(getWidth(), getHeight());
            Graphics graphicsBuffer = imageBuffer.getGraphics();
            drawGrids(graphicsBuffer);
            drawIcons(graphicsBuffer);


            g.drawImage(imageBuffer,0,0,Color.cyan,null);
        }
    }

    public void drawGrids(Graphics g){
        int n = positionsMap.length;
        double sizeGrid = (getHeight() * 0.037)/n;
        double sizeSpace = ((getHeight() * 0.9259) + (2* sizeGrid))/n;
        for (int x = 0; x <= n*sizeSpace; x+=sizeSpace) {
            g.fillRect(x, 0, (int) (2 * sizeGrid), (int) (n * sizeSpace));
        }
        for (int y = 0; y <= n*sizeSpace; y+=sizeSpace) {
            g.fillRect(0, y, (int) (n * sizeSpace) + 1, (int) (2 * sizeGrid));
        }
    }
    public void drawIcons(Graphics g){
        int n = positionsMap.length;
        double sizeGrid = (getHeight() * 0.037)/n;
        double sizeSpace = ((getHeight() * 0.9259) + (2* sizeGrid))/n;
        double sizeSquare = (getHeight() * 0.7407) / n;
        double y = sizeGrid + ((getHeight()* 0.0923)/n);
        for (int i = 0; i < n; i++, y+=sizeSpace) {
            double x = sizeGrid + ((getHeight()* 0.0923)/n);
            for (int j = 0; j < n; j++, x+=sizeSpace) {
                g.drawImage(getIcons(positionsMap[i][j]), (int)x,(int)y,(int)sizeSquare,(int)sizeSquare, null);
            }
        }
    }

    public Image getIcons(int numberIcon){
        Image icon;
        switch (numberIcon){
            case 0:{
                icon = imgStart;
            }
            break;
            case 1:{
                icon = imgWall;
            }break;
            case 2:{
                icon = null;
            }break;
            case 3:{
                icon = imgWater;
            }break;
            case 4:{
                icon = imgPeople;
            }break;
            case 5:{
                icon = imgProhibit;
            }break;
            case 6:{
                icon = imgPower;
            }break;
            case 7:{
                icon = imgGoal;
            }break;
            default:{
                System.err.println("Representacion de imagen no valida");
                icon = null;
            }break;
        }
        return icon;
    }


    public boolean isLoadMap() {
        return loadMap;
    }
}
