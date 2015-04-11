/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 11-abr-2015
 * Nombre del Archivo: Queue.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * *********************************************
 */
package Busqueda;

import java.util.ArrayList;

public class Queue extends ArrayList{
    //se añade un elemento a la cola. Se añade al final de esta.
    public void encolar(Nodo nodo){
        if(nodo != null){
            this.add(nodo);
        }else{
            System.out.println("Ingrese un nodo");
        }
    }

    //se elimina el elemento frontal de la cola, es decir, el primer elemento que entró.
    public void desencolar(){
        if(this.size() > 0){
            this.remove(0);
        }
    }

    //se devuelve el elemento frontal de la cola, es decir, el primer elemento que entró.
    public Nodo frente(){
        Nodo nodoAux = null;
        if(this.size() > 0){
            nodoAux = (Nodo) this.get(0);
        }
        return nodoAux;
    }

    //devuelve cierto si la pila está vacía o falso en caso contrario (empty).
    public boolean vacia(){
        return this.isEmpty();
    }
}
