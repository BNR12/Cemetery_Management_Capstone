import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Created by brittanyregrut on 2/23/16.
 */
public class PaymentWindow extends JFrame {

    private JLabel heading = new JLabel("Payment Management System");
    private JLabel subheading = new JLabel("Search to manage payment information");
    private JPanel namePanel;
    private JLabel nameLabel;
    private JTextField nameField;
    private JButton nameButton;
    private JPanel plotPanel;
    private JLabel plotLabel1;
    private JTextField plotField1;
    private JTextField plotField2;
    private JTextField plotField3;
    private JButton plotButton;
    private JPanel titlePanel;
    private PaymentDisplayPanel pdp;



    /**
     * Opens a new window to house the payment management system
     */
    public PaymentWindow(){
        //set basic functionality
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) ((.66) * (screenSize.getWidth()));
        int height = (int) ((.66) * (screenSize.getHeight()));
        Dimension min = new Dimension(width, height); //set frame to 2/3 screen width and height
        setMinimumSize(min);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        //stylize
        Color backgroundColor = new Color(153, 204, 255); //initialize main color
        Font mainFont = new Font("Serif", Font.PLAIN, 20); //create main font for buttons
        Font subHeadingFont = new Font("Serif", Font.PLAIN, 24); //font for the main heading
        Font mainHeadingFont = new Font("Serif", Font.BOLD, 32); //font for the sub heading
        heading.setFont(mainHeadingFont);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        subheading.setFont(subHeadingFont);
        subheading.setHorizontalAlignment(SwingConstants.CENTER);
        setBackground(backgroundColor);

        //Initialize titlePanel
        titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(backgroundColor);
        titlePanel.add(heading);
        titlePanel.add(subheading);
        add(titlePanel);
        titlePanel.setVisible(true);


        //Initialize namePanel
        namePanel = new JPanel(new GridLayout(1, 3));
        namePanel.setBackground(backgroundColor); // set name panel color
        nameLabel = new JLabel("Search By Name:");
        nameLabel.setFont(mainFont);//set name label font
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT); //center name label
        nameField = new JTextField();
        nameButton = new JButton("Search!");
        nameButton.setFont(mainFont); // set name button font
        nameButton.addActionListener(new nameListener());
        namePanel.setMaximumSize(new Dimension(400, 32));
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        namePanel.add(nameButton);
        add(namePanel);
        namePanel.setVisible(true);

        //Initialize plotPanel
        plotPanel = new JPanel(new GridLayout(1, 3));
        plotPanel.setBackground(backgroundColor);//set plot panel color
        plotLabel1 = new JLabel("Search By Section-Plot-Grave: ");
        plotLabel1.setFont(mainFont);//set plot label font
        plotLabel1.setHorizontalAlignment(SwingConstants.RIGHT); //center plot label
        plotField1 = new JTextField();
        plotField2 = new JTextField();
        plotField3 = new JTextField();
        plotButton = new JButton("Search!");
        plotButton.setFont(mainFont); // set plot button font
        plotButton.addActionListener(new plotListener());

        plotPanel.add(plotLabel1);
        plotPanel.setPreferredSize(new Dimension(400, 32));
        plotField1.setPreferredSize(new Dimension(89, 42));
        plotField2.setPreferredSize(new Dimension(88, 42));
        plotField3.setPreferredSize(new Dimension(89, 42));

        JPanel plotFieldPanel = new JPanel();
        plotFieldPanel.setBackground(backgroundColor);
        plotFieldPanel.setAlignmentY(SwingConstants.TOP);
        plotField1.setAlignmentY(SwingConstants.TOP);
        plotFieldPanel.add(plotField1);
        plotFieldPanel.add(plotField2);
        plotFieldPanel.add(plotField3);
        plotPanel.add(plotFieldPanel);
        plotPanel.add(plotButton);
        add(plotPanel);
        plotPanel.setVisible(true);

        //Create a new PaymentDisplayPanel
        pdp = new PaymentDisplayPanel();
        add(pdp);
        pdp.setVisible(true);

    }

    class nameListener implements ActionListener
    {
        /**
         * Queries database for name on button click
         *
         * @param actionEvent button click event
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            String fullName = nameField.getText();
            fullName = fullName.toLowerCase();

            if (fullName.length() > 0)
            {
                if (fullName.contains(" ") && fullName.split("\\s")[1].length()>0)
                {
                    // Split name if contains spaces - has a first and last name
                    String[] splitStr = fullName.split("\\s");

                    //set separate strings for first and last name
                    String firstName = splitStr[0];
                    String lastName = splitStr[1];

                    //clean up input to make first char of string uppercase
                    /*firstName = capitalize(firstName);
                    lastName = capitalize(lastName);

                    try // Both first and last name match entry in database
                    {
                        queryDb("SELECT * FROM PLOTS WHERE DECEASED_FNAME like \'%" + firstName + "%\' AND DECEASED_LNAME like \'%" + lastName + "%\'");
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }*/
                }
                else // Is only a first or only a last name
                {
                    //clean up input to make first char of string uppercase
                    /*fullName = capitalize(fullName);

                    try // Matches entry's first or last name field in database
                    {
                        queryDb("SELECT * FROM PLOTS WHERE DECEASED_FNAME like \'%" + fullName + "%\' OR DECEASED_LNAME like \'%" + fullName + "%\'");
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }*/
                }
            }
        }
    }

    /**
     * Class housing actionListener for plotButton
     */
    class plotListener implements ActionListener
    {
        /**
         * Queries database for plot on button click
         *
         * @param actionEvent button click event
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            String section = plotField1.getText();
            String plot = plotField2.getText();
            String grave = plotField3.getText();
            StringBuilder query = new StringBuilder("");
            boolean added = false;
            if (!section.equals(""))
            {
                query.append("SELECT * FROM PLOTS WHERE UPPER(SECTION) like UPPER('" + section + "')");
                added = true;
            }
            if (!plot.equals(""))
            {
                if (added)
                    query.append(" AND UPPER(PLOT_NUMBER) like UPPER('" + plot + "')");
                else
                {
                    query.append("SELECT * FROM PLOTS WHERE UPPER(PLOT_NUMBER) like UPPER('" + plot + "')");
                    added = true;
                }

            }
            if (!grave.equals(""))
            {
                if (added)
                    query.append(" AND UPPER(GRAVE) like UPPER('" + grave + "')");
                else
                    query.append("SELECT * FROM PLOTS WHERE UPPER(GRAVE) like UPPER('" + plot + "')");
            }
            /*try // Search for plot number
            {
                queryDb(query.toString());
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }*/
        }
    }

    /**
     * Helper method to query the DB
     *
     * @param query string used to query database
     * @returns number of results found by database
     */
    public static int queryDb(String query) throws java.sql.SQLException
    {
        int numEntries = 0; // Number of entries - used primarily for JUnit tests

        try
        {
            //establishes connection to our DB
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection("jdbc:h2:./h2/cemetery;IFEXISTS=TRUE", "laboon", "bethshalom");
            Statement stmt = con.createStatement();

            //sql statement to collect all the data in a certain row where the first name matches whatever entered into s
            ResultSet rs = stmt.executeQuery(query);

            //i is a counter for number of results in resultset
            int i = 0;

            while (rs.next())
            {
                numEntries++; // Increment number of entries

                //tokenizes the results of select statement into individual strings corresponding to their columns
                String fname = rs.getString("DECEASED_FNAME");
                String lname = rs.getString("DECEASED_LNAME");
                String plotNum = rs.getString("PLOT_NUMBER");
                String date = rs.getString("DATE_DECEASED");
                String sectionNum = rs.getString("SECTION");
                String graveNum = rs.getString("GRAVE");
                String intermentNumber = rs.getString("INTERMENT_NUMBER");
                String pInt = rs.getString("PN_INT");
                String liner = rs.getString("PN_LINER");
                String CGC = rs.getString("PN_CGC");
                String RMF = rs.getString("PN_RMF");
                String monument = rs.getString("MONUMENT");
                String planting = rs.getString("PP_PLANTING");
                String veteran = rs.getString("VETERAN");
                String cremated = rs.getString("CREMATED");
                String foundations = rs.getString("FOUNDATIONS");
                String monumentNotes = rs.getString("MONUMENT_NOTES");
                String cgcNotes = rs.getString("CGC_NOTES");
                String rmfNotes = rs.getString("RMF_NOTES");
                String linerNotes = rs.getString("LINER_NOTES");

                //Create a new entry object for this result
                Entry en = new Entry(fname, lname, plotNum, date, sectionNum, graveNum, intermentNumber, pInt, liner, CGC, RMF, monument, planting, veteran, cremated, foundations, monumentNotes, cgcNotes, rmfNotes, linerNotes);
                i++; //increment the row in the table so if multiple results returned, each is displayed in a new row
            }
            stmt.close();
            con.close();

            return numEntries;
        }
        catch (Exception er)
        {
            System.out.println(er.getMessage());
            return numEntries;
        }
    }

}
