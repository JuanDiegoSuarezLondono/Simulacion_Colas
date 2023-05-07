/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author USER
 */
public class ColaLogica {

    private Nodo nodoInicio;
    private Nodo nodoFin;

    public ColaLogica() {
        nodoInicio = null;
        nodoFin = null;
    }

    //Metodo insertar
    public void insertar(long dato) {
        Nodo i = new Nodo(dato);        
        i.setNodoAd(null);
        i.setNodoAt(nodoFin);        
        if (nodoInicio == null & nodoFin == null) {
            nodoInicio = i;
            nodoFin = i;
        }        
        nodoFin.setNodoAd(i);
        nodoFin = nodoFin.getNodoAd();
    }

    //Metodo extraer dato
    public void extraerLIFO() {
        nodoFin = nodoFin.getNodoAt();
        nodoFin.setNodoAd(null);
    }
    
    public void extraerM(long horaInicio) {
        Nodo M = nodoInicio; 
        int mitad = 0;
        mitad = (int) Math.ceil((float)this.contar()/2); 
        System.out.println("-----Midad: "+mitad);
        for (int j=1;j<mitad;j++) {
            M=M.getNodoAd();
        }
       System.out.println("El que sale:"+(M.getDato()-horaInicio));
       M.getNodoAt().setNodoAd(M.getNodoAd());
    }
    
    public long extraerFIFO() {
        long dato = nodoInicio.getDato();
        nodoInicio = nodoInicio.getNodoAd();
        return dato;
    }
    
    public Nodo getNodoInicio() {
        return this.nodoInicio;
    }
    
    public Nodo getNodoFin() {
        return this.nodoFin;
    }

    //Metodo para comprobar que la cola no esta vacia
    public boolean estaVacia() {
        boolean cola = false;
        if (nodoInicio == null & nodoFin == null) {
            cola = true;
        } else {
            cola = false;
        }
        return cola;
    }

    //Metodo para contar los elementos de la cola
    public int contar() {
        int contador = 0;
        Nodo c = this.nodoInicio;
        while (c != null) {
            contador++;
            c = c.getNodoAd();
        }        
        return contador;
    }

    //Metodo toString
    public String toString(long horaInicio) {
        Nodo c = this.nodoInicio;
        String s = "";
        while (c != null) {
            s = s + c.toString(horaInicio);
            c = c.getNodoAd();
        }
        return s;
    }
}
