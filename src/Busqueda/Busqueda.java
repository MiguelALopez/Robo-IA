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

import java.util.PriorityQueue;

public class Busqueda {
    protected int matrix[][];
    protected int nodosExpandidos;
    protected int nodosCreados;
    protected Node nodoMeta;

    protected int iniX;
    protected int iniY;
    protected int endX;
    protected int endY;
    
    protected PriorityQueue<Node> priorityQueue;

    public Busqueda() {
        nodoMeta = null;
        nodosCreados = 1;
        nodosExpandidos = 0;
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

    //Metodo encargado de comprovar si el nodo esta en una posicion de carga de ser asi retorna true
    public boolean checkCharge(Node node){
        boolean isStationCharge = false;
        if (matrix[node.getX()][node.getY()] == 6){
            isStationCharge = true;
        }
        return isStationCharge;
    }

    public void expandir(Node node, int direccion){
        int x = 0;
        int y = 0;
        switch (direccion){
            //Arriba
            case 1:{
                x = node.getX() - 1;
                y = node.getY();
            }break;
            //Derecha
            case 2:{
                x = node.getX();
                y = node.getY() + 1;
            }break;
            //Abajo
            case 3:{
                x = node.getX() + 1;
                y = node.getY();
            }break;
            //Izquierda
            case 4:{
                x = node.getX();
                y = node.getY() - 1;
            }break;
            default:{
                x = node.getX();
                y = node.getY();
            }break;
        }
        if (node.getCharge() > 0 && validAccess(x,y) && !node.travel(x,y)){
            int costo = calcCost(x, y, node.getCost());
            int charge = node.getCharge();
            if (checkCharge(node)){
                charge = 6;
            }else {
                charge--;
            }
            priorityQueue.offer(new Node(x, y, node,costo,charge));
            nodosCreados++;
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

}
