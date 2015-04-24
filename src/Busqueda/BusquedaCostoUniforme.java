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

import java.util.ArrayList;
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
           /* ArrayList<int[]> ints = node.getPath();
            for (int i = 0; i < ints.size(); i++) {
                System.out.print("(" + ints.get(i)[0] + " - " + ints.get(i)[1] + ")");
            }
            System.out.print(" el costo es: " + node.getCost());
            System.out.println();*/

            if (node != null && meta(node)){
                nodoMeta = node;
                fin = true;
            }else {
                expandir(node,1);
                expandir(node,2);
                expandir(node,3);
                expandir(node,4);
                nodosExpandidos++;
            }
        }
    }



    //Subclase encargada de ordenar la cola de prioridad de acuerdo al costo
    static class PQsort implements Comparator<Node> {
        public int compare(Node one, Node two) {
            return one.getCost() - two.getCost();
        }
    }
}
