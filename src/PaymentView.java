import javax.swing.*;
import java.awt.*;

/**
 * Created by brittanyregrut on 3/18/16.
 */
public class PaymentView extends JDialog {

    private JLabel test = new JLabel("Testing Payment View");

    /** Opens a payment information viewer for an existing entry */
    public PaymentView(Entry en){

        //set basic functionality
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) ((.25) * (screenSize.getWidth()));
        int height = (int) ((.25) * (screenSize.getHeight()));
        Dimension min = new Dimension(width, height); //set frame to 1/4 screen width and height
        setMinimumSize(min);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 1));



        add(test);
    }

    /** Opens a blank payment information viewer for an available plot,
        so that this plot can be sold and added to the database.
     */
    public PaymentView(String section, String plot, String grave){
        //set basic functionality
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) ((.25) * (screenSize.getWidth()));
        int height = (int) ((.25) * (screenSize.getHeight()));
        Dimension min = new Dimension(width, height); //set frame to 1/4 screen width and height
        setMinimumSize(min);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        add(test);
    }


}
