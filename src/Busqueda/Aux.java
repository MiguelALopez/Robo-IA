/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez - 1326691
 * Autor: Kellys Santa Gutierrez - 1325228
 * Autor: Mario Alejandro Payan - 1224601
 * Fecha: 11-abr-2015
 * Nombre del Archivo: Aux.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
 */
package Busqueda;

public class Aux {

    public Aux(){}

    //Metodo encargado de encontrar la posicion inicial del robot en una matriz
    public int[] findPosStart(int matrix[][]){
        int posStart[] = new int[2];
        for(int j=0;j<matrix.length;j++){
            for(int k=0;k<matrix.length;k++){
                if(matrix[j][k]==0){
                    posStart[0]=j;
                    posStart[1]=k;
                    j=k=matrix.length;
                }
            }
        }
        return posStart;
    }

    //Metodo encargado de encontrar la meta del robot en una matriz
    public int[] findPosEnd(int matrix[][]){
        int posEnd[] = new int[2];
        for(int j=0;j<matrix.length;j++){
            for(int k=0;k<matrix.length;k++){
                if(matrix[j][k]==7){
                    posEnd[0]=j;
                    posEnd[1]=k;
                    j=k=matrix.length;
                }
            }
        }
        return posEnd;
    }
}
