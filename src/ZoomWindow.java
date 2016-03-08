import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

/**
 * Created by brittanyregrut on 3/5/16.
 */
public class ZoomWindow extends JDialog {

    private JLabel image;
    private String fn;
    private JPanel searchPanel;
    private JButton submit;
    private JTextField plot = new JTextField();
    private JTextField grave = new JTextField();
    private JLabel plotLabel = new JLabel("Plot: ");
    private JLabel graveLabel = new JLabel("Grave: ");
    private JLabel plotInfo = new JLabel("View Information for: ");
    private static String currentSection; //The current section being viewed
    private static String plotNumber;
    private static String graveNumber;
    /**
     * Window displaying a zoomed in map of specified section
     * @param section
     */
    public ZoomWindow(String section){
        //Save section variable for use in action listener
        currentSection = section;

        //set basic functionality
        int width = 800, height = 723;
        Dimension min = new Dimension(width, height);
        setMinimumSize(min);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Stylize
        Color backgroundColor = new Color(153, 204, 255); //initialize main color
        Font mainFont = new Font("Serif", Font.PLAIN, 20); //create main font for buttons
        setBackground(backgroundColor);

        //Layout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setModal(true);

        try{
            //Add the map image
            fn = section + "map.png";
            BufferedImage myPicture = ImageIO.read(new File("/Users/brittanyregrut/Desktop/Cemetery_Management_Capstone/src/"+fn));
            image = new JLabel(new ImageIcon(myPicture));
            c.gridx = 0;
            c.gridy = 0;
            add(image);
        }catch (IOException e){
            //Map file not found
            System.out.println(fn + " not found");
            System.exit(1);
        }

        //Add panel to search for a plot in this section
        searchPanel = new JPanel(new GridBagLayout());
        Dimension search = new Dimension(800, 100);
        searchPanel.setMaximumSize(search);

        //Initialize button/stylize
        submit = new JButton("Go!");
        submit.addActionListener(new searchListener());
        submit.setFont(mainFont);
        plotLabel.setFont(mainFont);
        plotLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        graveLabel.setFont(mainFont);
        graveLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        plotInfo.setFont(mainFont);
        plot.setPreferredSize(new Dimension(80, 20));
        grave.setPreferredSize(new Dimension(80, 20));

        //Add components to panel. Add panel to window
        c.gridx = 0;
        c.gridy = 0;
        searchPanel.add(plotInfo, c);

        c.gridx = 2;
        searchPanel.add(plotLabel, c);

        c.gridx = 3;
        searchPanel.add(plot, c);

        c.gridx = 5;
        searchPanel.add(graveLabel, c);

        c.gridx = 6;
        searchPanel.add(grave, c);

        c.gridx = 8;
        searchPanel.add(submit);

        c.gridx = 0;
        c.gridy = 1;
        add(searchPanel, c);
    }

    /**
     * Class housing actionListener for submit button
     */
    class searchListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //Get requested plot and grave
            plotNumber = plot.getText();
            graveNumber = grave.getText();

            StringBuilder query = new StringBuilder("");
            boolean added = false;
            if (!currentSection.equals(""))
            {
                query.append("SELECT * FROM PLOTS WHERE UPPER(SECTION) like UPPER('" + currentSection + "')");
                added = true;
            }
            if (!plotNumber.equals(""))
            {
                if (added)
                    query.append(" AND UPPER(PLOT_NUMBER) like UPPER('" + plotNumber + "')");
                else
                {
                    query.append("SELECT * FROM PLOTS WHERE UPPER(PLOT_NUMBER) like UPPER('" + plotNumber + "')");
                    added = true;
                }

            }
            if (!graveNumber.equals(""))
            {
                if (added)
                    query.append(" AND UPPER(GRAVE) like UPPER('" + graveNumber + "')");
                else
                    query.append("SELECT * FROM PLOTS WHERE UPPER(GRAVE) like UPPER('" + plotNumber + "')");
            }
            try // Search for plot number
            {
                queryDb(query.toString());
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }

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

            //If rs is empty, this plot is available
            //For now, just display that it is available for purchase
            //In the future, this can be linked directly to the payment system, to enable purchasing of the plot
            if (isEmpty(rs)){
                JOptionPane.showMessageDialog(new Frame(), "Section " + currentSection + ", plot " + plotNumber + ", grave " + graveNumber + " is available for purchase.");
            }

            //This search should only have one result at most, any more than this is a data formatting error
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

            //Display an edit entry window
            EditEntry entryDisplay = new EditEntry(en);
            entryDisplay.setVisible(true);


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

    /**
     * A ResultSet is table of data representing a database.
     * Generated by executing a statement that queries the database.
     *
     * @param rs result set
     * @return true if rs is empty, false otherwise
     */
    public static boolean isEmpty(ResultSet rs) throws java.sql.SQLException
    {
        return !rs.first();
    }

}

