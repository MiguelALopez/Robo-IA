/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 11-abr-2015
 * Nombre del Archivo: Busqueda.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * *********************************************
 */
package Busqueda;

import Interfaz.Mapa;

import java.util.PriorityQueue;

public class Busqueda {
    protected int matrix[][];
    protected int nodosExpandidos;
    protected int nodosCreados;
    protected int profundidad;
    protected double factRamificacion;
    protected Node nodoMeta;

    protected int iniX;
    protected int iniY;
    protected int endX;
    protected int endY;
    
    protected PriorityQueue<Node> priorityQueue;

    public Busqueda() {
        nodoMeta = null;
        nodosCreados = 0;
        nodosExpandidos = 0;
        profundidad = 0;
        factRamificacion = 0;
        nodoMeta = new Node(iniX, iniY, null, 0, 6);
    }

    //Metodo encargado de retornar true si la posicion es valida
    public boolean validAccess(int posX, int posY){
        boolean valido = true;
        try {
            if (matrix[posX][posY] == 1){
                valido = false;
            }
        }catch (ArrayIndexOutOfBoundsException e){
            valido = false;
        }
        return valido;
    }

    //Metodo encargado de comprovar si es un nodo meta
    public boolean meta(Node node){
        boolean isMeta = false;
        if (validAccess(node.getX(), node.getY())){
            if (matrix[node.getX()][ node.getY()] == 7){
                isMeta = true;
            }
        }
        return isMeta;
    }

    /*Metodo encargado de comprobar si en esa posicion se encuentra
    * una estacion de recarga de ser asi retorna true*/
    public boolean checkStationCharge(int x, int y){
        boolean isStationCharge = false;
        if (matrix[x][y] == 6){ //
            isStationCharge = true;
        }
        return isStationCharge;
    }

    public boolean checkCharge(Node node){
        boolean isCharge = false;
        if (node.getCharge() > 0){
            isCharge = true;
        }
        return isCharge;
    }

    /*Metodo encargado de calcular el factor de ramificacion segun la profundidad y los nodos expandidos
    * Esta se calcula segun la formula que el profesor uso en el parcial:
    * profundidad = p
    * sumatoria desde i = 0 hasta p de b^i es igual a (b^(profundidad+1) - 1)/( 1)*/
    public double calcFactRam(int profudidad, int nodos){
        double factRam;
        factRam = Math.pow((nodos + 1.0), 1.0 / (profudidad + 1.0));
        factRam = Math.round(factRam*10000.0)/10000.0; //Redondea el numero a 4 cifras significativas
        return factRam;
    }

    //Metodo de encargado de Actualizar la profundidad para calcular la mayor profundidad
    public void updateProfundidad(int profundidad){
        if (profundidad > this.profundidad){
            this.profundidad = profundidad;
        }
    }

    //Metodo ecargado de calcular los costos en una posicion dada
    public int calcCost(int posX, int posY, int costo){
        switch (matrix[posX][posY]){
            //Espacio libre
            case 2:{
                costo+=1;
            }break;
            //Piso resbaloso
            case 3:{
                costo+=3;
            }break;
            //Lugar con alto flujo de personas
            case 4:{
                costo+=4;
            }break;
            //Lugar restringido
            case 5:{
                costo+=6;
            }break;
            //Lugar para recarga
            case 6:{
                costo+=5;
            }break;
        }
        return costo;
    }

    public Node getNodoMeta() {
        return nodoMeta;
    }

    public int getNodosExpandidos() {
        return nodosExpandidos;
    }

    public int getNodosCreados() {
        return nodosCreados;
    }

    public double getFactRamificacion() {
        return factRamificacion;
    }

    public int getProfundidad() {
        return profundidad;
    }
}
