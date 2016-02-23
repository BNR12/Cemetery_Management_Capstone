import javax.swing.*;
import java.awt.*;

/**
 * Created by brittanyregrut on 2/23/16.
 */
public class PaymentWindow extends JDialog {

    JLabel tester = new JLabel("Testing payment window");

    /**
     * Opens a new window to house the payment management system
     */
    public PaymentWindow(){
        //set basic functionality
        int width = 600, height = 400;
        Dimension min = new Dimension(width, height);
        setMinimumSize(min);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(1, 1));
        setModal(true);

        //stylize
        Color backgroundColor = new Color(153, 204, 255); //initialize main color
        Font mainFont = new Font("Serif", Font.PLAIN, 20); //create main font for buttons
        setBackground(backgroundColor);

        add(tester);
    }
}
