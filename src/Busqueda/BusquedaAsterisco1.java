package Busqueda;


public class BusquedaAsterisco1{
    private int[][] matrix;
    private Queue queue = new Queue();
    private int iniX;
    private int iniY;
    private int endX;
    private int endY;
    private Aux aux;
    private int posPrioridad = 0;
    
    private int START = 0;
    private int WALL = 1;
    private int[] BLANK = {2,1};
    private int[] FLOOR_SLIPPERY = {3,3};
    private int[] CROWD = {4,4};
    private int[] RESTRICTED = {5,6};
    private int[] RECHARGE = {6,5};
    private int GOAL = 7;
    
    public BusquedaAsterisco1(int[][] matrix, int iniX, int iniY, int endX, int endY){
        this.matrix=matrix;
        this.iniX=iniX;
        this.iniY=iniY;
        this.endX=endX;
        this.endY=endY;
        aux = new Aux(matrix.length);
        int[][] iniPath = new int[0][2];
        
        queue.encolar(new Nodo(iniX, iniY, iniPath, 0, 6, calcManhattan(iniX, iniY)));
    }
    
    public void run(){
        posPrioridad = queue.posPrioridad();
        queue.prioridad(posPrioridad).print();
        while(!step()){
            posPrioridad = queue.posPrioridad();
            queue.prioridad(posPrioridad).print();
        }
    }
    
    public boolean step(){
        Nodo nodoAux = (Nodo) queue.prioridad(posPrioridad);
        queue.removerP(posPrioridad);
        return busqueda(nodoAux);
    }
    
    public boolean busqueda(Nodo nodo){
        int x = nodo.getX();
        int y = nodo.getY();
        int cost = nodo.getCost();
        int charge = nodo.getCharge();
        
        
        if(nodo.getHeuristic()==0){return true;}
        else if(charge==0){/*FIN*/}
        else{
            if(aux.inRange(x-1,y) && matrix[x-1][y]!=WALL && !nodo.travel(x-1, y)){
                queue.encolar(new Nodo(x-1, y, aux.toAdd(nodo.getPath(), x, y), calcCost(cost, matrix[x-1][y]), calcCharge(charge, matrix[x-1][y]), calcManhattan(x-1, y)));}//Arriba
            if(aux.inRange(x,y-1) && matrix[x][y-1]!=WALL && !nodo.travel(x, y-1)){
                queue.encolar(new Nodo(x, y-1, aux.toAdd(nodo.getPath(), x, y), calcCost(cost, matrix[x][y-1]), calcCharge(charge, matrix[x][y-1]), calcManhattan(x, y-1)));}//Izquierda
            if(aux.inRange(x+1,y) && matrix[x+1][y]!=WALL && !nodo.travel(x+1, y)){
                queue.encolar(new Nodo(x+1, y, aux.toAdd(nodo.getPath(), x, y), calcCost(cost, matrix[x+1][y]), calcCharge(charge, matrix[x+1][y]), calcManhattan(x+1, y)));}//Abajo
            if(aux.inRange(x,y+1) && matrix[x][y+1]!=WALL && !nodo.travel(x, y+1)){
                queue.encolar(new Nodo(x, y+1, aux.toAdd(nodo.getPath(), x, y), calcCost(cost, matrix[x][y+1]), calcCharge(charge, matrix[x][y+1]), calcManhattan(x, y+1)));}//Derecha
        }
        return false;
    }
    
    public int calcCost(int costAct, int number){
        if(number==BLANK[0]){return costAct+BLANK[1];}
        else if(number==FLOOR_SLIPPERY[0]){return costAct+FLOOR_SLIPPERY[1];}
        else if(number==CROWD[0]){return costAct+CROWD[1];}
        else if(number==RESTRICTED[0]){return costAct+RESTRICTED[1];}
        else if(number==RECHARGE[0]){return costAct+RECHARGE[1];}
        else{return costAct+BLANK[1];}
    }
    
    public int calcCharge(int chargeAct, int number){
        if(number==RECHARGE[0]){return 6;}
        else{return chargeAct-1;}
    }
    
    public int calcManhattan(int posx, int posy){
        int distanciaL = 0;
        int distanciaX = Math.abs(posx - endX);
        int distanciaY = Math.abs(posy - endY);
        distanciaL = distanciaX + distanciaY;
         
        return distanciaL;
    }
   
}
