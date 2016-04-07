import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by brittanyregrut on 3/31/16.
 */
public class AddPayment extends JDialog{

    private DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
    private JTextField amount = new JTextField();
    private JTextField payType = new JTextField();
    private JFormattedTextField date = new JFormattedTextField(df);
    private JLabel newPay = new JLabel("Submit a new payment: ");
    private JLabel amountLabel = new JLabel("Payment Amount: ");
    private JLabel payLabel = new JLabel("Payment Type: ");
    private JLabel dateLabel = new JLabel("Payment Date: ");
    private JPanel inputPanel = new JPanel(new GridLayout(3, 2));
    private JButton submit = new JButton("Add Payment");

    public AddPayment(Entry en){
        //set basic functionality
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) ((.25) * (screenSize.getWidth()));
        int height = (int) ((.25) * (screenSize.getHeight()));
        Dimension min = new Dimension(width, height); //set frame to 1/4 screen width and height
        setMinimumSize(min);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        //Add input information to panel
        inputPanel.add(amountLabel);
        inputPanel.add(amount);
        inputPanel.add(payLabel);
        inputPanel.add(payType);
        inputPanel.add(dateLabel);
        inputPanel.add(date);

        //Add everything to window
        add(newPay);
        add(inputPanel);
        add(submit);


        //Action listener for submit button to add payment to database
        submit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //get values to write to db
                int id = en.getPaymentID();
                double amt = Double.parseDouble(amount.getText());
                String payDate = date.getText();
                String type = payType.getText();

                try{
                    Class.forName("org.h2.Driver");
                    Connection con = DriverManager.getConnection("jdbc:h2:./h2/cemetery;IFEXISTS=TRUE", "laboon", "bethshalom");
                    Statement stmt = con.createStatement();

                    //determine payment number
                    ResultSet rs = stmt.executeQuery("SELECT * FROM PAYMENTS WHERE ID="+id);

                    //i is a counter for number of results
                    int i = 0;

                    while(rs.next()){
                        i++;
                    }

                    //Number of payments already existing + 1
                    int numPay = i++;
                    numPay++;

                    //Insert new payment into db
                    boolean insert = stmt.execute("INSERT INTO PAYMENTS VALUES(" + id + ", " + numPay + ", " + amt + ", '" + payDate + "', '" + type + "')");

                    //If successfully inserted, deduct amount from entry's balance
                    if (!insert){
                        double balance = en.getBalance();
                        balance = balance - amt;
                        en.setBalance(balance);
                        JOptionPane.showMessageDialog(new Frame(), "Payment Entry Successful");
                    }
                    else{
                        //else display error
                        JOptionPane.showMessageDialog(new Frame(), "Payment Entry Not Successful");
                    }
                    stmt.close();
                    con.close();

                }catch (Exception er)
                {
                    System.out.println(er.getMessage());
                }
                setVisible(false);
                dispose();
            }
        });



    }


}
