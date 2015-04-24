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
    private int tam = 0;

    public Aux(int tam){
        this.tam = tam;
    }

    public int[][] toAdd(int[][] array, int x, int y){
        int[][] newArray = new int[array.length+1][2];
        for(int i=0;i<array.length;i++){
            newArray[i][0]=array[i][0];
            newArray[i][1]=array[i][1];
        }
        newArray[array.length][0]=x;
        newArray[array.length][1]=y;
        return newArray;
    }

    public boolean inRange(int x, int y){
        if(x<0){return false;}
        else if(y<0){return false;}
        else if(x>=tam){return false;}
        else return y < tam;
    }

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
