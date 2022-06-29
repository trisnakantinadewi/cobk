/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobagui;

import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author Aldino
 */
public class CURDmhs extends dbkoneksi{
    static Statement stmt;
    static ResultSet rs;
    dbkoneksi cnn = new dbkoneksi();
    
    private void query(String sql){
        try{
            Statement stmt = cnn.koneksi().createStatement();
            this.rs = stmt.executeQuery(sql);
        }catch(Exception e){
            System.out.println("terjadi kendala pada query");
        }
    }
    public void Listmhs(){
        String sql = "SELECT * FROM mhs";
        this.query(sql);
        
        try{
            while (rs.next()) {
                System.out.println("Record ke-"+rs.getRow());
                System.out.println("NIM : "+rs.getString("NIM"));
                System.out.println("NAMA : "+rs.getString("NAMA"));
                System.out.println("JURUSAN : "+rs.getString("JURUSAN"));                
            }
            cnn.koneksi().close();
        }catch(Exception e){
            System.out.println("Terjadi Kendala Pada Pembacaan Record Data");
        }
    }
    public void Storemhs(String sql){
    }
    public void Updatemhs(String sql){
    }
    public void Deletemhs(String sql){
    }
}
