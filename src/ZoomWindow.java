import javax.swing.*;
import java.awt.*;

/**
 * Created by brittanyregrut on 3/5/16.
 */
public class ZoomWindow extends JDialog {

    /**
     * Window displaying a zoomed in map of specified section
     * @param section
     */
    public ZoomWindow(String section){
        //set basic functionality
        int width = 800, height = 723;
        Dimension min = new Dimension(width, height);
        setMinimumSize(min);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

}
