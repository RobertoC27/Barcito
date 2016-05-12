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

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
public class Connector {
    
    public void ConnectDB()
    {
        Connection c = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/Barcito",
            "postgres", "root");
         c.setAutoCommit(false);
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
    }
    public JTable GetInfoFromQuery(String Query)
    {
        JTable result = new JTable();
        Connection c = null;
        Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/Barcito",
            "postgres", "root");
         c.setAutoCommit(false);
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
        try {
            stmt = c.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //AQUI ESTA LA QUERY, LO QUE MANDE AQUI SE MUESTRA
            ResultSet rs = stmt.executeQuery(Query);
            ResultSetMetaData rsmd = rs.getMetaData();
            
            ArrayList<String> columnNames = new ArrayList<String>();
            ArrayList<String> columnLabels = new ArrayList<String>();
            ArrayList<String> columnTypes = new ArrayList<String>();
            for(int i=1; i<rsmd.getColumnCount()+1; i++)
            {
                String name = rsmd.getColumnName(i);
                //System.out.println("Column Name: " + name);
                String Label = rsmd.getColumnLabel(i);
                //System.out.println("Column Label: " + Label);
                String Type = rsmd.getColumnTypeName(i);
                System.out.println("Column Type: " + Type);
                columnNames.add(name);
                columnLabels.add(Label);
                columnTypes.add(Type);
            }
            
            Object columnN[] = columnNames.toArray();
            ArrayList<Object[]> Rows = new ArrayList<Object[]>();
            int counter =0;
            while(rs.next())
            {
                
                ArrayList<String> row = new ArrayList<String>();
                for(int i=0; i<columnNames.size(); i++)
                {
                    String toAdd = "";
                    if(columnTypes.get(i).equals("int4"))
                    {
                        int temp = rs.getInt(columnLabels.get(i));
                        toAdd = String.valueOf(temp);
                        row.add(toAdd);
                    }
                    else if(columnTypes.get(i).equals("varchar"))
                    {
                        toAdd = rs.getString(columnLabels.get(i));
                        row.add(toAdd);
                    }
                    else if(columnTypes.get(i).equals("bool"))
                    {
                        boolean temp = rs.getBoolean(columnLabels.get(i));
                        toAdd = String.valueOf(temp);
                        row.add(toAdd);
                    }
                    else if(columnTypes.get(i).equals("bytea"))
                    {
                        toAdd = "<binary data>";
                        row.add(toAdd);
                    }
                    else if(columnTypes.get(i).equals("date"))
                    {
                        Date temp = rs.getDate(columnLabels.get(i));
                        toAdd = String.valueOf(temp);
                        row.add(toAdd);
                    }
                    else if(columnTypes.get(i).equals("float4"))
                    {
                        float temp = rs.getFloat(columnLabels.get(i));
                        toAdd = String.valueOf(temp);
                        row.add(toAdd);
                    }
                }
                Object finna[] = row.toArray();
                Rows.add(finna);
                counter++;
            }
            Object rowData[][];
            rowData = new Object[counter][columnNames.size()];
            for(int i=0;i<counter; i++)
            {
               rowData[i] = Rows.get(i);
            }
            
            result = new JTable(rowData, columnN);
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        return result;
    }
    public void UpdateTable(String Query)
    {
        Connection c = null;
       Statement stmt = null;
       try {
       Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/Barcito",
            "postgres", "root");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         //String sql = "UPDATE COMPANY set SALARY = 25000.00 where ID=1;";
         stmt.executeUpdate(Query);
         c.commit();
         //rs.close();
         stmt.close();
         c.close();
       } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
       }
       System.out.println("Operation done successfully");
     }
    public void InsertIntoTable(String Query)
    {
        Connection c = null;
        Statement stmt = null;
        try {
           Class.forName("org.postgresql.Driver");
           c = DriverManager
              .getConnection("jdbc:postgresql://localhost:5432/Barcito",
              "postgres", "root");
           c.setAutoCommit(false);
           System.out.println("Opened database successfully");

           stmt = c.createStatement();
           stmt.executeUpdate(Query);

           stmt.close();
           c.commit();
           c.close();
        } catch (Exception e) {
           System.err.println( e.getClass().getName()+": "+ e.getMessage() );
           System.exit(0);
        }
        System.out.println("Record created successfully");
    }
    public void DeleteFromTable(String Query)
    {
        Connection c = null;
        Statement stmt = null;
        try {
           Class.forName("org.postgresql.Driver");
           c = DriverManager
              .getConnection("jdbc:postgresql://localhost:5432/Barcito",
              "postgres", "root");
           c.setAutoCommit(false);
           System.out.println("Opened database successfully");

           stmt = c.createStatement();
           int result = JOptionPane.showConfirmDialog(null, "desea eliminar los registros seleccionados?");
            // JOptionPane.showInternalConfirmDialog(desktop, "Continue printing?");

            if(JOptionPane.YES_OPTION == result)
            {
                stmt.executeUpdate(Query);
            }
           

           stmt.close();
           c.commit();
           c.close();
        } catch (Exception e) {
           System.err.println( e.getClass().getName()+": "+ e.getMessage() );
           System.exit(0);
        }
        System.out.println("Record created successfully");
    }
    public void AlterTable(String Query)
    {
        Connection c = null;
        Statement stmt = null;
        try {
           Class.forName("org.postgresql.Driver");
           c = DriverManager
              .getConnection("jdbc:postgresql://localhost:5432/Barcito",
              "postgres", "root");
           c.setAutoCommit(false);
           System.out.println("Opened database successfully");

           stmt = c.createStatement();
           stmt.execute(Query);

           stmt.close();
           c.commit();
           c.close();
        } catch (Exception e) {
           System.err.println( e.getClass().getName()+": "+ e.getMessage() );
           System.exit(0);
        }
        System.out.println("Table Altered Succesfully");
    }
    
    public ArrayList<String> getColumnNames(String TableName){
        ArrayList<String> NombreCampos = new ArrayList();
        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/Barcito",
                "postgres", "root");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT * FROM " +TableName);
            if (rs != null) {
                ResultSetMetaData columns = rs.getMetaData();
                int ColumnLength = columns.getColumnCount();
                for (int i = 1; i < ColumnLength; i++) {
                    NombreCampos.add(columns.getColumnName(i));
                }
                stmt.close();
                c.commit();
                c.close();
            }
        } catch (Exception e) {
           System.err.println( e.getClass().getName()+": "+ e.getMessage() );
           System.exit(0);
        }
        return NombreCampos;
    }
    public ArrayList<String> getGenreColumnNames()
    {
        ArrayList<String> aret = new ArrayList<String>();
        String Query = "SELECT descripcion FROM \"Genero\";";
        Connection c = null;
        Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/Barcito",
            "postgres", "root");
         c.setAutoCommit(false);
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
        try {
            stmt = c.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        try 
        {
            //AQUI ESTA LA QUERY, LO QUE MANDE AQUI SE MUESTRA
            ResultSet rs = stmt.executeQuery(Query);
            
            while(rs.next())
            {
                String desName = rs.getString("descripcion");
                aret.add(desName);
            }
                      
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aret;
    }
    
    public ArrayList<String> getStateColumnNames()
    {
        ArrayList<String> aret = new ArrayList<String>();
        String Query = "SELECT descripcion FROM \"Estado_Civil\";";
        Connection c = null;
        Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/Barcito",
            "postgres", "root");
         c.setAutoCommit(false);
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
        try {
            stmt = c.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        try 
        {
            //AQUI ESTA LA QUERY, LO QUE MANDE AQUI SE MUESTRA
            ResultSet rs = stmt.executeQuery(Query);
            
            while(rs.next())
            {
                String desName = rs.getString("descripcion");
                aret.add(desName);
            }
                      
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aret;
    }
    
    public ArrayList<Double> getGenreValues()
    {
        String Query1 = "SELECT COUNT(*) FROM \"Cliente\" WHERE \"genero\" = 1;";
        String Query2 = "SELECT COUNT(*) FROM \"Cliente\" WHERE \"genero\" = 2;";
        String Query3 = "SELECT COUNT(*) FROM \"Cliente\" WHERE \"genero\" = 3;";
        String Query4 = "SELECT COUNT(*) FROM \"Cliente\" WHERE \"genero\" = 4;";
        
        ArrayList<Double> values = new ArrayList<Double>();
        Connection c = null;
        Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/Barcito",
            "postgres", "root");
         c.setAutoCommit(false);
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
        try {
            stmt = c.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //AQUI ESTA LA QUERY, LO QUE MANDE AQUI SE MUESTRA
            ResultSet rs = stmt.executeQuery(Query1);
            while(rs.next())
            {
                int Count = rs.getInt("count");
                double Count1 = (double) Count;
                values.add(Count1);
            }
            rs = stmt.executeQuery(Query2);
            while(rs.next())
            {
                int Count = rs.getInt("count");
                double Count1 = (double) Count;
                values.add(Count1);
            }
            rs = stmt.executeQuery(Query3);
            while(rs.next())
            {
                int Count = rs.getInt("count");
                double Count1 = (double) Count;
                values.add(Count1);
            }
            rs = stmt.executeQuery(Query4);
            while(rs.next())
            {
                int Count = rs.getInt("count");
                double Count1 = (double) Count;
                values.add(Count1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return values;
    }
    
    public ArrayList<Double> getAgeValues()
    {
        String Query1 = "Select COUNT(*) FROM \"Cliente\" WHERE edad >= 18 AND edad < 30;";
        String Query2 = "Select COUNT(*) FROM \"Cliente\" WHERE edad >= 30 AND edad < 65;";
        String Query3 = "Select COUNT(*) FROM \"Cliente\" WHERE edad >= 65;";
        
        
        ArrayList<Double> values = new ArrayList<Double>();
        Connection c = null;
        Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/Barcito",
            "postgres", "root");
         c.setAutoCommit(false);
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
        try {
            stmt = c.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //AQUI ESTA LA QUERY, LO QUE MANDE AQUI SE MUESTRA
            ResultSet rs = stmt.executeQuery(Query1);
            while(rs.next())
            {
                int Count = rs.getInt("count");
                double Count1 = (double) Count;
                values.add(Count1);
            }
            rs = stmt.executeQuery(Query2);
            while(rs.next())
            {
                int Count = rs.getInt("count");
                double Count1 = (double) Count;
                values.add(Count1);
            }
            rs = stmt.executeQuery(Query3);
            while(rs.next())
            {
                int Count = rs.getInt("count");
                double Count1 = (double) Count;
                values.add(Count1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return values;
    }
    
     public ArrayList<Double> getfrecValues()
    {
        String Query1 = "SELECT COUNT(*) FROM \"Cliente\" WHERE frecuente = true;";
        String Query2 = "SELECT COUNT(*) FROM \"Cliente\" WHERE frecuente = false;";
        
        
        ArrayList<Double> values = new ArrayList<Double>();
        Connection c = null;
        Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/Barcito",
            "postgres", "root");
         c.setAutoCommit(false);
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
        try {
            stmt = c.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //AQUI ESTA LA QUERY, LO QUE MANDE AQUI SE MUESTRA
            ResultSet rs = stmt.executeQuery(Query1);
            while(rs.next())
            {
                int Count = rs.getInt("count");
                double Count1 = (double) Count;
                values.add(Count1);
            }
            rs = stmt.executeQuery(Query2);
            while(rs.next())
            {
                int Count = rs.getInt("count");
                double Count1 = (double) Count;
                values.add(Count1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return values;
    }
     
    public ArrayList<Double> getStateValues()
    {
        String Query1 = "SELECT COUNT(*) FROM \"Cliente\" WHERE estado_civil = 1;";
        String Query2 = "SELECT COUNT(*) FROM \"Cliente\" WHERE estado_civil = 2;";
        String Query3 = "SELECT COUNT(*) FROM \"Cliente\" WHERE estado_civil = 3;";
        String Query4 = "SELECT COUNT(*) FROM \"Cliente\" WHERE estado_civil = 4;";
        
        
        
        ArrayList<Double> values = new ArrayList<Double>();
        Connection c = null;
        Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/Barcito",
            "postgres", "root");
         c.setAutoCommit(false);
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
        try {
            stmt = c.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //AQUI ESTA LA QUERY, LO QUE MANDE AQUI SE MUESTRA
            ResultSet rs = stmt.executeQuery(Query1);
            while(rs.next())
            {
                int Count = rs.getInt("count");
                double Count1 = (double) Count;
                values.add(Count1);
            }
            rs = stmt.executeQuery(Query2);
            while(rs.next())
            {
                int Count = rs.getInt("count");
                double Count1 = (double) Count;
                values.add(Count1);
            }
            rs = stmt.executeQuery(Query3);
            while(rs.next())
            {
                int Count = rs.getInt("count");
                double Count1 = (double) Count;
                values.add(Count1);
            }
            rs = stmt.executeQuery(Query4);
            while(rs.next())
            {
                int Count = rs.getInt("count");
                double Count1 = (double) Count;
                values.add(Count1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return values;
    }
    
     public ArrayList<Double> getValetValues()
    {
        String Query1 = "SELECT COUNT(*) FROM \"Cliente\" WHERE valet = true;";
        String Query2 = "SELECT COUNT(*) FROM \"Cliente\" WHERE valet = false;";
        
        
        ArrayList<Double> values = new ArrayList<Double>();
        Connection c = null;
        Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/Barcito",
            "postgres", "root");
         c.setAutoCommit(false);
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
        try {
            stmt = c.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //AQUI ESTA LA QUERY, LO QUE MANDE AQUI SE MUESTRA
            ResultSet rs = stmt.executeQuery(Query1);
            while(rs.next())
            {
                int Count = rs.getInt("count");
                double Count1 = (double) Count;
                values.add(Count1);
            }
            rs = stmt.executeQuery(Query2);
            while(rs.next())
            {
                int Count = rs.getInt("count");
                double Count1 = (double) Count;
                values.add(Count1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return values;
    }
     
    public ArrayList<Double> getPointValues()
    {
        String Query1 = "SELECT avg(\"Puntos\") FROM \"Tarjeta\";";
     
        
        
        ArrayList<Double> values = new ArrayList<Double>();
        Connection c = null;
        Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/Barcito",
            "postgres", "root");
         c.setAutoCommit(false);
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
        try {
            stmt = c.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //AQUI ESTA LA QUERY, LO QUE MANDE AQUI SE MUESTRA
            ResultSet rs = stmt.executeQuery(Query1);
            while(rs.next())
            {
                int Count = rs.getInt("avg");
                double Count1 = (double) Count;
                values.add(Count1);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return values;
    } 
    
    public ArrayList<Double> getTwitterValues()
    {
        String Query1 = "SELECT COUNT(*) FROM \"Info_Contacto\" WHERE twitter is not null;";
        String Query2 = "SELECT COUNT(*) FROM \"Info_Contacto\" WHERE twitter is null;";
        
        
        ArrayList<Double> values = new ArrayList<Double>();
        Connection c = null;
        Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/Barcito",
            "postgres", "root");
         c.setAutoCommit(false);
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
        try {
            stmt = c.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //AQUI ESTA LA QUERY, LO QUE MANDE AQUI SE MUESTRA
            ResultSet rs = stmt.executeQuery(Query1);
            while(rs.next())
            {
                int Count = rs.getInt("count");
                double Count1 = (double) Count;
                values.add(Count1);
            }
            rs = stmt.executeQuery(Query2);
            while(rs.next())
            {
                int Count = rs.getInt("count");
                double Count1 = (double) Count;
                values.add(Count1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return values;
    }
    public int getAvailableId(){
        int count = 1;
        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/Barcito",
                "postgres", "root");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT count(*) FROM \"Cliente\"");
            if (rs != null) {
                while(rs.next())            {
                    count = rs.getInt("count");
                }
            }
                stmt.close();
                c.commit();
                c.close();
        } catch (Exception e) {
           System.err.println( e.getClass().getName()+": "+ e.getMessage() );
           System.exit(0);
        }
        return count;
    }
}

