/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 23-abr-2015
 * Nombre del Archivo: BusquedaCostoUniforme.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * *********************************************
 */
package Busqueda;

import java.util.Comparator;
import java.util.PriorityQueue;

public class BusquedaCostoUniforme extends Busqueda{


    public BusquedaCostoUniforme(int matrix[][], int iniX, int iniY){
        this.matrix = matrix;
        this.iniX = iniX;
        this.iniY = iniY;
        PQsort pqs = new PQsort();
        priorityQueue = new PriorityQueue<Node>(pqs);
        priorityQueue.offer(new Node(iniX, iniY, null, 0, 6)); //Se añade el primer nodo a la cola de prioridad
    }

    public void busqueda(){
        boolean fin = false; //Variable que comprueba si a terminado
        while (!fin && priorityQueue.size() > 0){
            Node node = priorityQueue.poll(); //Saca y remueve el nodo que se va a expandir
            updateProfundidad(node.getPath().size() - 1); //Se le resta un 1 de el nodo raiz
           /* ArrayList<int[]> ints = node.getPath();
            for (int i = 0; i < ints.size(); i++) {
                System.out.print("(" + ints.get(i)[0] + " - " + ints.get(i)[1] + ")");
            }
            System.out.print(" el costo es: " + node.getCost());
            System.out.println();*/

            if (node != null && meta(node)){
                nodoMeta = node;
                fin = true;
            }else if (checkCharge(node)){ // //Se comprueba que el robot esta cargado para poder seguir expandiendo
                expandir(node,1);
                expandir(node,2);
                expandir(node,3);
                expandir(node,4);
                nodosExpandidos++;
            }
        }
        factRamificacion = calcFactRam(profundidad, nodosCreados);
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
        /*Esta condicion comprueba que el nodo este cargado, que al lugar que se
        dirige es un acceso valido y que no lo halla recorrido antes*/
        if (checkCharge(node) && validAccess(x,y)){
            int costo = calcCost(x, y, node.getCost());
            int charge = node.getCharge();
            //En esta condicion se combrueba si se encuentra en una estacion de recarga
            if (checkStationCharge(x, y)){
                charge = 6;
            }else {
                charge--;
            }

            priorityQueue.offer(new Node(x, y, node,costo,charge));
            nodosCreados++;
        }
    }

    //Subclase encargada de ordenar la cola de prioridad de acuerdo al costo
    static class PQsort implements Comparator<Node> {
        public int compare(Node one, Node two) {
            return one.getCost() - two.getCost();
        }
    }
}
