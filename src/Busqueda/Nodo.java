/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 11-abr-2015
 * Nombre del Archivo: Nodo.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * *********************************************
 */
package Busqueda;

public class Nodo {
    private int x;
    private int y;
    private int[][] path;
    private int cost;
    private int charge;
    private int heuristic;
    private int f_n;

    Nodo(int x, int y, int[][] path, int cost, int charge){
        this.x=x;
        this.y=y;
        this.path=path;
        this.cost=cost;
        this.charge=charge;
    }

    Nodo(int x, int y, int[][] path, int cost, int charge, int heuristic){
        this.x=x;
        this.y=y;
        this.path=path;
        this.cost=cost;
        this.charge=charge;
        this.heuristic=heuristic;
        this.f_n=this.cost + this.heuristic;
    }

    int getX(){
        return x;
    }

    int getY(){
        return y;
    }

    int[][] getPath(){
        return path;
    }

    int getCost(){
        return cost;
    }

    int getCharge(){
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



    boolean travel(int x, int y){
        for(int i=0;i<path.length;i++){
            if(path[i][0]==x && path[i][1]==y){return true;}
        }
        return false;
    }

    void print(){
        String pathString="";
        for(int i=0;i<path.length;i++){
            pathString+="("+path[i][0]+","+path[i][1]+") ";
        }
        System.out.println("--------------------------");
        System.out.println("Posicion: ("+ x + "," + y + ")");
        System.out.println("Camino: "+ pathString);
        System.out.println("Costo: " + cost);
        System.out.println("Carga: " + charge);
        System.out.println("heuristica: " + heuristic);
        System.out.println("f_n: " + f_n);
        System.out.println("*****Nodos a expandir*****");
    }
}