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
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
 
public class Piechart extends ApplicationFrame 
{
   public Piechart( String title, ArrayList<String> ColumnNames, ArrayList<Double> Values ) 
   {
      super( title ); 
      setContentPane(GetPieChart( title, ColumnNames, Values));
   }
   private static PieDataset createDataset(ArrayList<String> ColumnNames, ArrayList<Double> Values) 
   {
      DefaultPieDataset dataset = new DefaultPieDataset();
      
      for(int i=0; i<ColumnNames.size(); i++ )
      {
          dataset.setValue( ColumnNames.get(i), Values.get(i));
      }  
      return dataset;         
   }
   
   private static JFreeChart createChart( PieDataset dataset, String Title )
   {
      JFreeChart chart = ChartFactory.createPieChart(      
         Title,  // chart title 
         dataset,        // data    
         true,           // include legend   
         true, 
         false);

      return chart;
   }
   public static JPanel GetPieChart(String Title, ArrayList<String> ColumnNames, ArrayList<Double> Values )
   {
      JFreeChart chart = createChart(createDataset(ColumnNames, Values), Title);  
      return new ChartPanel( chart ); 
   }
}