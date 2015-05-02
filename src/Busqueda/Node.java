/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez - 1326691
 * Autor: Kellys Santa Gutierrez - 1325228
 * Autor: Mario Alejandro Payan - 1224601
 * Fecha: 24-abr-2015
 * Nombre del Archivo: Node.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
 */
package Busqueda;

import java.util.ArrayList;

public class Node {
    private int x;
    private int y;
    private int cost;
    private ArrayList<int[]> path;
    private int charge;
    private int heuristic;
    private int f_n;

    //Este constructor se usa para la busqueda por costo uniforme pues no tiene en cuenta la heuristica
    public Node(int x, int y, Node father, int cost, int charge){
        //Se inicializan las variables
        this.x=x;
        this.y=y;
        this.cost=cost;
        this.charge=charge;
        path = new ArrayList<int[]>();
        /*Esta condicion se usa para añadir el camino del padre al camino del nuevo nodo creado
        * si el padre es nulo entonces solo se agrega la posicion actual al camino*/
        if (father != null){
            path.addAll(father.getPath());
            path.add(new int[]{x,y});
        }else {
            path.add(new int[]{x,y});
        }
    }

    //Este contructor es usado para la busqueda por A* pues se tiene en cuenta la heuristica
    public Node(int x, int y, Node father, int cost, int charge, int heuristic){
        //Se inicializan las variables
        this.x=x;
        this.y=y;
        this.cost=cost;
        this.charge=charge;
        this.heuristic=heuristic;
        this.f_n=this.cost + this.heuristic;
        path = new ArrayList<int[]>();
        /*Esta condicion se usa para añadir el camino del padre al camino del nuevo nodo creado
        * si el padre es nulo entonces solo se agrega la posicion actual al camino*/
        if (father != null){
            path.addAll(father.getPath());
            path.add(new int[]{x,y});
        }else {
            path.add(new int[]{x,y});
        }
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getCost(){
        return cost;
    }

    public int getCharge(){
        return charge;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    public int getF_n() {
        return f_n;
    }

    public void setF_n(int f_n) {
        this.f_n = f_n;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public ArrayList<int[]> getPath() {
        return path;
    }

}
