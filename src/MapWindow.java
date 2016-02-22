/**
 * Created by brittanyregrut on 2/21/16.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;



public class MapWindow extends JDialog {

    public MapWindow(){
        //set basic functionality
        int width = 800, height = 623;
        Dimension min = new Dimension(width, height);
        setMinimumSize(min);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);

        try{
            BufferedImage myPicture = ImageIO.read(new File("/Users/brittanyregrut/Desktop/Cemetery_Management_Capstone/src/map.png"));
            JLabel image = new JLabel(new ImageIcon(myPicture));
            add(image);
        }catch (IOException e){
            //Map file not found
            System.out.println("Cemetery Map not found");
            System.exit(1);
        }
    }

}
