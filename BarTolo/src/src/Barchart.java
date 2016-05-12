/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author juankboix1309
 */
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class Barchart extends ApplicationFrame
{
   public Barchart( String windowTitle , String chartTitle, String nombreChart, String nombreMetrica, ArrayList<String> barras, ArrayList<Double> valorBarras, String tiposValor )
   {
      super( windowTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         nombreChart,            
         nombreMetrica,            
         createDataset(barras, valorBarras, tiposValor),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
      setContentPane( chartPanel ); 
   }
   private CategoryDataset createDataset(ArrayList<String> barras, ArrayList<Double> valorBarras, String tiposValor  )
   {
              
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  
      for(int i=0; i< barras.size(); i++)
      {
          dataset.addValue(valorBarras.get(i), tiposValor,barras.get(i));
      }

      return dataset; 
   }   
}