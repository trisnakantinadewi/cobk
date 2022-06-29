/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobagui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
 
public class loadIMG {
 
    public static void main(String[] args) throws IOException {
 
        // lokasi file
        File file = new File("/home/echo/picture/head.jpg");
 
        // load gambar
        Image image = ImageIO.read(file);
    }
}