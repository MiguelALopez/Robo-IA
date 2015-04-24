/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Busqueda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BusquedaAsterisco extends Busqueda{


    public BusquedaAsterisco(int[][] matrix, int iniX, int iniY, int endX, int endY){
        this.matrix = matrix;
        this.iniX = iniX;
        this.iniY = iniY;
        this.endX = endX;
        this.endY = endY;
        PQsort pqs = new PQsort();
        priorityQueue = new PriorityQueue<Node>(pqs);
        priorityQueue.offer(new Node(iniX, iniY, null, 0, 6, calcManhattan(this.iniX, this.iniY))); //Se añade el primer nodo a la cola de prioridad
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
            priorityQueue.offer(new Node(x, y, node,costo,charge,calcManhattan(x, y)));
            nodosCreados++;
        }
    }

    public void busqueda(){
        boolean fin = false; //Variable que comprueba si a terminado
        while (!fin && priorityQueue.size() > 0){
            Node node = priorityQueue.poll(); //Saca y remueve el nodo que se va a expandir
           ArrayList<int[]> ints = node.getPath();
            for (int i = 0; i < ints.size(); i++) {
                System.out.print("(" + ints.get(i)[0] + " - " + ints.get(i)[1] + ")");
            }
            System.out.print(" el costo es: " + node.getF_n());
            System.out.println();

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

    public int calcManhattan(int posx, int posy){
        int distanciaL = 0;
        int distanciaX = Math.abs(posx - endX);
        int distanciaY = Math.abs(posy - endY);
        distanciaL = distanciaX + distanciaY;

        return distanciaL;
     }


    //Subclase encargada de ordenar la cola de prioridad de acuerdo al costo
    static class PQsort implements Comparator<Node> {
        public int compare(Node one, Node two) {
            return one.getF_n() - two.getF_n();
        }
    }
}
