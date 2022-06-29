/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobagui;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Aldino
 */
public class dbkoneksi {
    String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    String url  = "jdbc:mysql://localhost/mahasiswa";
    String user = "root";
    String pass = "";
    private static Connection koneksi;
    
    public Connection koneksi (){
        if (koneksi == null){
            try{
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi = DriverManager.getConnection(url, user, pass);
                System.out.println("Koneksi Berhasil");
            }catch(SQLException e){
                System.out.println("Error Membuat Koneksi");
            }
        }
        return koneksi;
    }
}
