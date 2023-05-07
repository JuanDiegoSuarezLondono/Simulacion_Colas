package objetos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author USER
 */
public class Nodo {

    private long dato;
    private Nodo nodoAdelante;
    private Nodo nodoAtras;

    public Nodo(long dato) {
        this.dato = dato;
    }

    public long getDato() {
        return dato;
    }

    public void setDato(long dato) {
        this.dato = dato;
    }

    public Nodo getNodoAd() {
        return nodoAdelante;
    }

    public void setNodoAd(Nodo nodo) {
        this.nodoAdelante = nodo;
    }
    
    public Nodo getNodoAt() {
        return nodoAtras;
    }

    public void setNodoAt(Nodo nodo) {
        this.nodoAtras = nodo;
    }

    //Metodo toString
    public String toString(long horaInicio) {
        String s = " " + (dato-horaInicio) + " ";
        return s;
    }

}
