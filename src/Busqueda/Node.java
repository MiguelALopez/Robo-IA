/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 24-abr-2015
 * Nombre del Archivo: Node.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
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

    public Node(int x, int y, Node father, int cost, int charge){
        this.x=x;
        this.y=y;
        this.cost=cost;
        this.charge=charge;
        path = new ArrayList<int[]>();
        if (father != null){
            path.addAll(father.getPath());
            path.add(new int[]{x,y});
        }else {
            path.add(new int[]{x,y});
        }
    }
    
    public Node(int x, int y, Node father, int cost, int charge, int heuristic){
        this.x=x;
        this.y=y;
        this.cost=cost;
        this.charge=charge;
        this.heuristic=heuristic;
        this.f_n=this.cost + this.heuristic;
        path = new ArrayList<int[]>();
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

    //Metodo encargado de retornar true si la posicion se encuentra en el camino recorrido por el nodo
    public boolean travel(int x, int y){
        for (int[] aPath : path) {
            if (aPath[0] == x && aPath[1] == y) {
                return true;
            }
        }
        return false;
    }
}
