import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by brittanyregrut on 3/5/16.
 */
public class ZoomWindow extends JDialog {

    private JLabel image;
    /**
     * Window displaying a zoomed in map of specified section
     * @param section
     */
    public ZoomWindow(String section){
        //set basic functionality
        int width = 800, height = 623;
        Dimension min = new Dimension(width, height);
        setMinimumSize(min);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        try{
            //Add the map image
            String fn = section + "map.png";
            BufferedImage myPicture = ImageIO.read(new File(fn));
            image = new JLabel(new ImageIcon(myPicture));
            add(image);
        }catch (IOException e){
            //Map file not found
            System.out.println(section + " Cemetery Map not found");
            System.exit(1);
        }
    }

}
