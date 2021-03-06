import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by brittanyregrut on 3/18/16.
 */
public class PaymentView extends JDialog {

    private JLabel test = new JLabel("Testing Payment View");
    private JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
    private JButton add = new JButton("Add a new payment");
    private JButton charge = new JButton("Add new charges");
    private JButton refresh = new JButton("Refresh payment display");
    private PaymentPanel pp;

    /** Opens a payment information viewer for an existing entry */
    public PaymentView(Entry en){
        //Initialize payment panel
        pp = new PaymentPanel(en);

        //set basic functionality
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) ((.66) * (screenSize.getWidth()));
        int height = (int) ((.66) * (screenSize.getHeight()));
        Dimension min = new Dimension(width, height); //set frame to 1/4 screen width and height
        setMinimumSize(min);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        //Add Buttons to Panel
        buttonPanel.add(add);
        buttonPanel.add(charge);
        buttonPanel.add(refresh);

        //Add button panel
        add(buttonPanel);

        //Action listener for the button to add a new payment
        add.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                AddPayment newPayment = new AddPayment(en);
                newPayment.setVisible(true);
            }
        });

        //Action listener for the button to add new charges to an account
        charge.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                AddCharge newCharge = new AddCharge(en);
                newCharge.setVisible(true);
            }
        });

        //Add payment panel
        add(pp);

        //Action listener for the button to refresh the payment panel
        refresh.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                pp.refresh();
            }
        });

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
