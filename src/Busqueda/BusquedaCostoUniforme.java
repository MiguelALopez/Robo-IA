/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez - 1326691
 * Autor: Kellys Santa Gutierrez - 1325228
 * Autor: Mario Alejandro Payan - 1224601
 * Fecha: 23-abr-2015
 * Nombre del Archivo: BusquedaCostoUniforme.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
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
        //Se inicializa la cola de prioridad con el formato de ordenamiento
        PQsort pqs = new PQsort();
        priorityQueue = new PriorityQueue<Node>(pqs);
        //Se añade el primer nodo a la cola de prioridad que en este casi seria el inicio
        priorityQueue.offer(new Node(iniX, iniY, null, 0, 6));
    }

    /*Metodo encargado de realizar la busqueda, esta comienza y no para hasta que
    * encuentre la meta o la cola de prioridad quede vacia, que en este caso,
    * no se encontraria la meta*/
    public void busqueda(){
        boolean fin = false; //Variable que comprueba si a terminado
        while (!fin && priorityQueue.size() > 0){
            Node node = priorityQueue.poll(); //Saca y remueve el nodo que se va a expandir
            //Se usa para hallar cual es la maxima profundidad
            updateProfundidad(node.getPath().size() - 1); //Se le resta un 1 de el nodo raiz

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
        //Se caulcula cual es el factor de ramificacion una vez a encontrado la meta
        factRamificacion = calcFactRam(profundidad, nodosCreados);
    }

    //Metodo encargado de expandir un nodo en una direccion determinada
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
            //Se añade el nuevo nodo a la cola de prioridad
            priorityQueue.offer(new Node(x, y, node,costo,charge));
            nodosCreados++;
        }
    }

    //Subclase encargada de ordenar la cola de prioridad de acuerdo al costo
    static class PQsort implements Comparator<Node> {
        /*Este metodo es el encargado de darle la instruccion a la cola de prioridad que se ordene
        * de cierta manera, ya sea que se ordene con respecto a el costo o a una typeHeuristic en especifico
        *
        * la manera en la que esta funciona es simple,
        * Si el primer valor a comparar es menor que el segundo entonces retorna un numero negativo.
        * Si ambos numeros tienen el mismo valor entonces retorna cero.
        * Si el primer valor es mayor que el segundo entonces retornara un numero positivo
        *
        * Si quisiesemos ordenar de forma acendente lo normal seria que el primer valor restado con el segundo
        * diese un numero negativo*/
        public int compare(Node one, Node two) {
            return one.getCost() - two.getCost();
        }
    }
}
