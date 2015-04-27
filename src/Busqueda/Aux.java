/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 11-abr-2015
 * Nombre del Archivo: Aux.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 * *********************************************
 */
package Busqueda;

public class Aux {

    public Aux(){}

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
