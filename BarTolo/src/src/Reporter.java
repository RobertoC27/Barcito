/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;

/**
 *
 * @author juankboix1309
 */
public class Reporter 
{

    public void GenerarReporteCliente()
    {
        
        Connector elConectador = new Connector();
        
        
        //Crear repostero Genero
        Piechart elRepostero_genero = new Piechart("Genero Clientes", elConectador.getGenreColumnNames(), elConectador.getGenreValues());
        elRepostero_genero.setSize(400,400);
        elRepostero_genero.setVisible(true);
        
        //Crear Barrendero Edad
        ArrayList<String> Inters = new ArrayList<String>();
        Inters.add("de 18 a 30");
        Inters.add("de 30 a 65");
        Inters.add("de 65 en adelante");
        Barchart elBarrendero_edad = new Barchart("Edad Clientes","Intervalos de edad en BarTolo","Intervalos", "Cantidad", Inters, elConectador.getAgeValues(), "Edad");
        elBarrendero_edad.setSize(400,400);
        elBarrendero_edad.setVisible(true);
        
        //Crear Repostero Cliente Frecuente
        ArrayList<String> frecs = new ArrayList<String>();
        frecs.add("Cliente Frecuente");
        frecs.add("Cliente Normal");
        Piechart elReposteroFrecuente = new Piechart("Clientes Frecuentes", frecs, elConectador.getfrecValues());
        elReposteroFrecuente.setSize(400,400);
        elReposteroFrecuente.setVisible(true);

        //Crear Barrendero estado civil
        Barchart elBarrenderoEstado = new Barchart("Estado civil clientes", "Estado civil de clientes en BarTolo", "Estados", "Cantidad", elConectador.getStateColumnNames(), elConectador.getStateValues(), "Estado");
        elBarrenderoEstado.setSize(400,400);
        elBarrenderoEstado.setVisible(true);
        
        //Crear repostero Valet
        ArrayList<String> ValetNames = new ArrayList<String>();
        ValetNames.add("Clientes con Valet Parking");
        ValetNames.add("Clientes sin Valet Parking");
        Piechart elReposteroValet = new Piechart("Clientes y servicio de Valet Parking", ValetNames, elConectador.getValetValues());
        elReposteroValet.setSize(400,400);
        elReposteroValet.setVisible(true);
        
        //Crear Barrendero puntos
        ArrayList<String> PointNames = new ArrayList<String>();
        PointNames.add("Promedio de puntos tarjeta Cliente Frecuente");
        Barchart elBarrenderoPuntos = new Barchart("Promedio puntos", "Puntos de clientes frecuentes BarTolo", "puntos", "cantidad", PointNames, elConectador.getPointValues(), "Promedio");
        elBarrenderoPuntos.setSize(400,400);
        elBarrenderoPuntos.setVisible(true);
        
        //Crear Repostero Twitter
        ArrayList<String> twitterTypes =new ArrayList<String>();
        twitterTypes.add("Clientes que utilizan Twitter");
        twitterTypes.add("Clientes que no utilizan Twitter");
        Piechart elReposteroTwittero = new Piechart("Uso de Twitter Clientes BarTolo", twitterTypes, elConectador.getTwitterValues());
        elReposteroTwittero.setSize(400,400);
        elReposteroTwittero.setVisible(true);
        
    }
}
