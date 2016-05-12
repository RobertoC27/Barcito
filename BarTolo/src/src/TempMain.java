/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;


import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
/**
 *
 * @author juankboix1309
 */
public class TempMain 
{
    public static void main(String args[])
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Connector c = new Connector();
        //"SELECT * FROM \"Cliente\";" toda la tabla cliente
        //c.UpdateTable("UPDATE \"Cliente\" SET edad = 99 WHERE edad >= 100;");
        //c.InsertIntoTable("INSERT INTO \"Cliente\" (\"ID\", nombre, apellido, genero, nacimiento, edad, frecuente, estado_civil) values (1001, 'Juan Carlos', 'Canteo', 1, '1981-10-23', 66, false, 2)");
        //c.DeleteFromTable("DELETE FROM \"Cliente\" WHERE \"ID\" = 1001;");
        //c.AlterTable("ALTER TABLE \"Cliente\" ADD nombrePareja varchar;");
        //JTable toShow = c.GetInfoFromQuery("SELECT * FROM \"Cliente\" WHERE \"ID\" > 997;");
        //JScrollPane scrollPane = new JScrollPane(toShow);
        new GUI().setVisible(true);
        JTable toShow = c.GetInfoFromQuery("SELECT * FROM \"Cliente\" WHERE id > 997;");
        JScrollPane scrollPane = new JScrollPane(toShow);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(300, 150);
        frame.setVisible(true);
        //new GUI().setVisible(true);
        Reporter elReportero = new Reporter();
        elReportero.GenerarReporteCliente();
    }
}
