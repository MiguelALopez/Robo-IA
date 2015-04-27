package Busqueda;

import java.util.Comparator;
import java.util.PriorityQueue;

public class BusquedaAsterisco extends Busqueda{
    /*Para los valores de la typeHeuristic asumiremos que 1 es la primera typeHeuristic y para
    cualquier otro valor la segunda typeHeuristic*/
    private int typeHeuristic;


    public BusquedaAsterisco(int[][] matrix, int iniX, int iniY, int endX, int endY, int typeHeuristic){
        this.matrix = matrix;
        this.iniX = iniX;
        this.iniY = iniY;
        this.endX = endX;
        this.endY = endY;
        this.typeHeuristic = typeHeuristic;
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
        if (checkCharge(node) && validAccess(x,y)){
            int costo = calcCost(x, y, node.getCost());
            int charge = node.getCharge();
            //En esta condicion se combrueba si se encuentra en una estacion de recarga
            if (checkStationCharge(x,y)){
                charge = 6;
            }else {
                charge--;
            }
            int heuristica;
            if (typeHeuristic == 1){
                heuristica = calcManhattan(x,y);
            }else {
                heuristica = calcHeuristic(x,y,charge);
            }
            priorityQueue.offer(new Node(x, y, node,costo,charge,heuristica));
            nodosCreados++;
        }
    }

    public void busqueda(){
        boolean fin = false; //Variable que comprueba si a terminado
        while (!fin && priorityQueue.size() > 0){
            Node node = priorityQueue.poll(); //Saca y remueve el nodo que se va a expandir
           /*ArrayList<int[]> ints = node.getPath();
            for (int i = 0; i < ints.size(); i++) {
                System.out.print("(" + ints.get(i)[0] + " - " + ints.get(i)[1] + ")");
            }
            System.out.print(" el costo es: " + node.getF_n());
            System.out.println();*/

            if (node != null && meta(node)){
                nodoMeta = node;
                fin = true;
            }else if (checkCharge(node)){ //Se comprueba que el robot esta cargado para poder seguir expandiendo
                expandir(node,1);
                expandir(node,2);
                expandir(node,3);
                expandir(node,4);
                nodosExpandidos++;
            }
        }   
    }

    //Metodo encargado de retornar la distancia en L
    public int calcManhattan(int posx, int posy){
        int distanciaL = 0;
        int distanciaX = Math.abs(posx - endX);
        int distanciaY = Math.abs(posy - endY);
        distanciaL = distanciaX + distanciaY;

        return distanciaL;
    }
    //Heuristica distancia en L * (7 - charge)
    public int calcHeuristic(int posx, int posy, int charge){
        int heuristica;
        heuristica = calcManhattan(posx,posy) * (7 - charge);
        return heuristica;
    }


    //Subclase encargada de ordenar la cola de prioridad de acuerdo al costo
    class PQsort implements Comparator<Node> {
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
            return one.getF_n() - two.getF_n();
        }
    }
}
