/**
 * Created by brittanyregrut on 4/7/16.
 */
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

public class AddCharge extends JDialog{

    private DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
    private JTextField amount = new JTextField();
    private JFormattedTextField date = new JFormattedTextField(df);
    private JTextField admin = new JTextField();
    private JLabel newPay = new JLabel("Add new charges: ");
    private JLabel amountLabel = new JLabel("Amount: ");
    private JLabel dateLabel = new JLabel("Date added: ");
    private JLabel adminLabel = new JLabel("Administrator name: ");
    private JPanel inputPanel = new JPanel(new GridLayout(3, 2));
    private JButton submit = new JButton("Add Charges");

    public AddCharge(Entry en){
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
        inputPanel.add(dateLabel);
        inputPanel.add(date);
        inputPanel.add(adminLabel);
        inputPanel.add(admin);

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
                String adminName = admin.getText();
                String type = "CHARGE";

                try{
                    Class.forName("org.h2.Driver");
                    Connection con = DriverManager.getConnection("jdbc:h2:./h2/cemetery;IFEXISTS=TRUE", "laboon", "bethshalom");
                    Statement stmt = con.createStatement();

                    //determine payment number
                    ResultSet rs = stmt.executeQuery("SELECT * FROM PAYMENTS WHERE ID="+id);

                    //i is a counter for number of results
                    int i = 0;

                    String bal = null;

                    while(rs.next()){
                        i++;
                        bal = rs.getString("BALANCE");
                    }

                    //Number of payments already existing + 1
                    int numPay = i++;
                    numPay++;

                    //get last balance
                    double balance;
                    if (numPay > 1){
                        balance = Double.parseDouble(bal);
                    }
                    else{
                        balance = 0.00;
                    }

                    //update the balance
                    balance = balance + amt;

                    //make amt negative for display purposes
                    amt = amt - (2*amt);

                    //Insert new payment into db
                    boolean insert = stmt.execute("INSERT INTO PAYMENTS(ID, PAYMENT_NUMBER, AMOUNT, DATE, PAYMENT_TYPE, BALANCE, ADMIN) VALUES(" + id + ", " + numPay + ", " + amt + ", '" + payDate + "', '" + type + "', " + balance + ", '" + adminName + "')");

                    //If successfully inserted, deduct amount from entry's balance
                    if (!insert){
                        JOptionPane.showMessageDialog(new Frame(), "Charge Entry Successful - ID: " + id + "\nNew Balance: " + balance);
                    }
                    else{
                        //else display error
                        JOptionPane.showMessageDialog(new Frame(), "Charge Entry Not Successful");
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

