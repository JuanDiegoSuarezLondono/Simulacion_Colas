/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

/**
 *
 * @author suare
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import objetos.*;

public class Progress extends JFrame implements ActionListener{
    
  static JProgressBar current;
  JTextArea out;
  JButton find;
  Thread runner;
  int num = 0;
  
  static ColaLogica cola1 = new ColaLogica();
  static Calendar time = new GregorianCalendar();
  static long horaInicio = 0;
  static long anterior = 0;
  static boolean finJornada = false;
  static int cont = 1;
  static int salier = 0; 
  static boolean salirDo;
  static boolean bn;
  static JButton boton;
  static String modo;  
  static JComboBox combo; 
      
   
  
public Progress() {
  super("Progress");  
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  JPanel pane = new JPanel();
  combo = new JComboBox();
  salirDo = true;
  bn =false;
  boton = new JButton();
  boton.addActionListener(this);
  pane.setLayout(new FlowLayout());
  current = new JProgressBar(1,0, 30);
  current.setValue(0);
  pane.add(current);
  setContentPane(pane);
  }

public void iterate() {
  while (num < 30) {
  current.setValue(num);
  try {
  Thread.sleep(3000);
  } catch (InterruptedException e) { }
  num += 1;
  }
  }
public static void main(String[] arguments) {
  Progress frame = new Progress(); 
  boton.setLabel("¡Go!");
  combo.addItem("FIFO");
  combo.addItem("LIFO");
  combo.addItem("MID");  
  frame.pack();
  frame.add(boton);
  frame.add(combo);
  frame.setSize(200, 200);
  frame.setLocationRelativeTo(null);
  frame.setVisible(true);  
  modo = "MID";
  do{    
    System.out.print("");
    if (bn == true) {        
        time.setTime(new Date());
        horaInicio=time.getTimeInMillis();
        cola1.insertar(((int) (Math.random()*1000)+1)+time.getTimeInMillis());
        if (modo.equalsIgnoreCase("FIFO")) {
          FIFO();
        }
        else if(modo.equalsIgnoreCase("LIFO")){
          LIFO();
        }
        else if(modo.equalsIgnoreCase("MID")){   
          mitad();
        }
        salirDo = false;
    }
  }while(salirDo);
  /*time.setTime(new Date());
  horaInicio=time.getTimeInMillis();
  cola1.insertar(((int) (Math.random()*1000)+1)+time.getTimeInMillis());
  if (modo.equalsIgnoreCase("FIFO")) {
    FIFO();
  }
  else if(modo.equalsIgnoreCase("LIFO")){
    LIFO();
  }
  else if(modo.equalsIgnoreCase("MID")){   
    mitad();
  }*/    
        cola1.estaVacia();
        System.out.println("Numero de datos en la cola: " + cola1.contar());
        System.out.println("Clientes entraron "+cont);
        System.out.println("Clientes salieron "+salier);
        System.out.println(cola1.toString(horaInicio));  
  }

    public static void FIFO(){          
        do{                
            if(time.getTimeInMillis() == cola1.getNodoFin().getDato()) {
                System.out.println("Inicio"+(cola1.getNodoInicio().getDato()-horaInicio));
                System.out.println("Fin: "+(cola1.getNodoFin().getDato()-horaInicio));
                cola1.insertar(((int) (Math.random()*1000)+1)+time.getTimeInMillis());
                current.setValue(cola1.contar());
                cont++;                
            }
            if((time.getTimeInMillis() >= (cola1.getNodoInicio().getDato()+3000)) && (time.getTimeInMillis()>=(anterior+3000))) {
                anterior = time.getTimeInMillis();
                System.out.println("El que sale:"+(cola1.getNodoInicio().getDato()-horaInicio));
                cola1.extraerFIFO();
                current.setValue(cola1.contar());
                salier++;
            }
            if((time.getTimeInMillis()-horaInicio)>= 10000) {
                System.out.println("Hora de Cerrar");
                finJornada = true;
            }                         
            time.setTime(new Date());
        }while(!finJornada);
    }
    
    public static void LIFO(){
        long ultimo = cola1.getNodoFin().getDato();
        do{               
            if(time.getTimeInMillis() == ultimo) {
                System.out.println("Inicio"+(cola1.getNodoInicio().getDato()-horaInicio));
                System.out.println("Fin: "+(cola1.getNodoFin().getDato()-horaInicio));
                cola1.insertar(((int) (Math.random()*1000)+1)+time.getTimeInMillis());
                ultimo = cola1.getNodoFin().getDato();
                current.setValue(cola1.contar());
                cont++;
            }
            if((time.getTimeInMillis() >= (cola1.getNodoInicio().getDato()+3000)) && (time.getTimeInMillis()>=(anterior+3000))) {
                anterior = time.getTimeInMillis();
                System.out.println("El que sale:"+(cola1.getNodoFin().getDato()-horaInicio));
                cola1.extraerLIFO();
                current.setValue(cola1.contar());
                salier++;
            }
            if((time.getTimeInMillis()-horaInicio)>= 10000) {
                System.out.println("Hora de Cerrar");
                finJornada = true;
            }            
            time.setTime(new Date()); 
        }while(!finJornada);
    }
    
    public static void mitad(){        
        do{               
            if(time.getTimeInMillis() == cola1.getNodoFin().getDato()) {
                System.out.println("Inicio"+(cola1.getNodoInicio().getDato()-horaInicio));
                System.out.println("Fin: "+(cola1.getNodoFin().getDato()-horaInicio));
                cola1.insertar(((int) (Math.random()*1000)+1)+time.getTimeInMillis());
                current.setValue(cola1.contar());
                cont++;
            }
            if((time.getTimeInMillis() >= (cola1.getNodoInicio().getDato()+3000)) && (time.getTimeInMillis()>=(anterior+3000))) {
                anterior = time.getTimeInMillis();
                cola1.extraerM(horaInicio);
                current.setValue(cola1.contar());
                salier++;
            }
            if((time.getTimeInMillis()-horaInicio)>= 10000) {
                System.out.println("Hora de Cerrar");
                finJornada = true;
            }            
            time.setTime(new Date()); 
        }while(!finJornada);
    }
    
    
    public void actionPerformed(ActionEvent e) {
        modo = (String) combo.getSelectedItem();
        bn = true;
    }
    
}

