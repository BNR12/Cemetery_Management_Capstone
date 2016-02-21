import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

/**
 * Window in top half of main frame, houses all text fields and buttons
 */
public class MainWindow extends JPanel
{
  private static DisplayPanel dp;
  private JPanel titlePanel;
  private JLabel mainHeading = new JLabel("Beth Shalom Synagogue");
  private JLabel subHeading = new JLabel("Cemetery Management System");
  private JPanel createPanel;
  private JPanel namePanel;
  private JLabel nameLabel;
  private JTextField nameField;
  private JButton nameButton;
  private JPanel plotPanel;
  private JLabel plotLabel1;
  private JLabel plotLabel2;
  private JLabel plotLabel3;
  private JTextField plotField1;
  private JTextField plotField2;
  private JTextField plotField3;
  private JButton plotButton;
  private JLabel intermentLabel = new JLabel("Search By Interment Number: ");
  private JTextField intermentField;
  private JButton intermentButton;
  private JLabel dateLabel = new JLabel("Search By Date: ");
  private JTextField dateField;
  private JButton dateButton;
  private JPanel intermentPanel;
  private JPanel datePanel;

  /**
   * Constructor for MainWindow
   *
   * @param display DisplayPanel to be inserted in bottom half of grid layout
   */
  public MainWindow(DisplayPanel display)
  {
    //initialize dp to display
    dp = display;

    //Setup SearchPanel
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    //Deal with titlePanel
    titlePanel = new JPanel(new GridLayout(2, 1));
    Color panelColor = new Color(153, 204, 255); //initialize main color
    titlePanel.setBackground(panelColor); //set color of title panel
    Font mainHeadingFont = new Font("Serif", Font.PLAIN, 24); //font for the main heading
    mainHeading.setFont(mainHeadingFont); // set the font of the main heading
    mainHeading.setHorizontalAlignment(JLabel.CENTER);
    titlePanel.add(mainHeading);
    Font subHeadingFont = new Font("Serif", Font.BOLD, 32); //font for the sub heading
    subHeading.setFont(subHeadingFont); // set the font of the sub heading
    subHeading.setHorizontalAlignment(JLabel.CENTER);
    titlePanel.add(subHeading);
    add(titlePanel);

    //Initialize namePanel
    Font mainFont = new Font("Serif", Font.PLAIN, 20); //create main font for buttons, etc.
    namePanel = new JPanel(new GridLayout(1, 3));
    namePanel.setBackground(panelColor); // set name panel color
    nameLabel = new JLabel("Search By Name:");
    nameLabel.setFont(mainFont);//set name label font
    nameLabel.setHorizontalAlignment(SwingConstants.RIGHT); //center name label
    nameField = new JTextField();
    nameField.addMouseListener(new fieldListener());
    nameButton = new JButton("Search!");
    nameButton.setFont(mainFont); // set name button font
    nameButton.addActionListener(new nameListener());
    namePanel.add(nameLabel);
    namePanel.add(nameField);
    namePanel.add(nameButton);
    add(namePanel);
    namePanel.setVisible(true);

    //Initialize plotPanel
    plotPanel = new JPanel(new GridLayout(1, 3));
    plotPanel.setBackground(panelColor);//set plot panel color
    plotLabel1 = new JLabel("Search By Section-Plot-Grave: ");
    plotLabel1.setFont(mainFont);//set plot label font
    plotLabel1.setHorizontalAlignment(SwingConstants.RIGHT); //center plot label
    plotField1 = new JTextField();
    plotField1.addMouseListener(new fieldListener());
    plotField2 = new JTextField();
    plotField2.addMouseListener(new fieldListener());
    plotField3 = new JTextField();
    plotField3.addMouseListener(new fieldListener());
    plotButton = new JButton("Search!");
    plotButton.setFont(mainFont); // set plot button font
    plotButton.addActionListener(new plotListener());

    plotPanel.add(plotLabel1);
    plotPanel.setPreferredSize(new Dimension(400, 32));
    plotField1.setPreferredSize(new Dimension(89, 42));
    plotField2.setPreferredSize(new Dimension(88, 42));
    plotField3.setPreferredSize(new Dimension(89, 42));

    JPanel plotFieldPanel = new JPanel();
    plotFieldPanel.setBackground(panelColor);
    plotFieldPanel.setAlignmentY(SwingConstants.TOP);
    plotField1.setAlignmentY(SwingConstants.TOP);
    plotFieldPanel.add(plotField1);
    plotFieldPanel.add(plotField2);
    plotFieldPanel.add(plotField3);
    plotPanel.add(plotFieldPanel);

    plotPanel.add(plotButton);


    //Initialize date panel
    datePanel = new JPanel(new GridLayout(1, 3));
    datePanel.setBackground(panelColor); // set name panel color
    dateLabel.setFont(mainFont);//set name label font
    dateLabel.setHorizontalAlignment(SwingConstants.RIGHT); //center name label
    dateField = new JTextField();
    dateField.addMouseListener(new fieldListener());
    dateButton = new JButton("Search!");
    dateButton.setFont(mainFont); // set name button font
    dateButton.addActionListener(new dateListener());
    datePanel.add(dateLabel);
    datePanel.add(dateField);
    datePanel.add(dateButton);
    add(datePanel);
    datePanel.setVisible(true);

    //Initialize interment panel
    intermentPanel = new JPanel(new GridLayout(1, 3));
    intermentPanel.setBackground(panelColor); // set name panel color
    intermentLabel.setFont(mainFont);//set name label font
    intermentLabel.setHorizontalAlignment(SwingConstants.RIGHT); //center name label
    intermentField = new JTextField();
    intermentField.addMouseListener(new fieldListener());
    intermentButton = new JButton("Search!");
    intermentButton.setFont(mainFont); // set name button font
    intermentButton.addActionListener(new intermentListener());
    intermentPanel.add(intermentLabel);
    intermentPanel.add(intermentField);
    intermentPanel.add(intermentButton);
    add(intermentPanel);
    intermentPanel.setVisible(true);

    add(plotPanel);
    plotPanel.setVisible(true);
  }

  /**
   * Class to house actionListener for createButton
   */
  class createListener implements ActionListener
  {
    /**
     * Initialize and set visible a NewEntry dialog
     *
     * @param actionEvent button click event
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
      NewEntry entry = new NewEntry();
      entry.setVisible(true);
    }
  }

  /**
   * Class to house actionListener for nameField
   */
  class fieldListener implements MouseListener
  {
    /**
     * Set nameButton to "Enter key"
     *
     * @param mouseEvent button click event
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent)
    {
      JTextField temp = (JTextField) mouseEvent.getSource();
      if (temp == nameField)
      {
        JRootPane rootPane = SwingUtilities.getRootPane(nameButton);
        rootPane.setDefaultButton(nameButton);
      }
      if (temp == plotField1 || temp == plotField2 || temp == plotField3)
      {
        JRootPane rootPane = SwingUtilities.getRootPane(plotButton);
        rootPane.setDefaultButton(plotButton);
      }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent)
    {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent)
    {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent)
    {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent)
    {

    }
  }

  /**
   * Class housing actionListener for nameButton
   */
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
          firstName = capitalize(firstName);
          lastName = capitalize(lastName);

          try // Both first and last name match entry in database
          {
            dp.clear();
            queryDb("SELECT * FROM PLOTS WHERE DECEASED_FNAME like \'%" + firstName + "%\' AND DECEASED_LNAME like \'%" + lastName + "%\'");
          }
          catch (SQLException e)
          {
            e.printStackTrace();
          }
        }
        else // Is only a first or only a last name
        {
          //clean up input to make first char of string uppercase
          fullName = capitalize(fullName);

          try // Matches entry's first or last name field in database
          {
            dp.clear();
            queryDb("SELECT * FROM PLOTS WHERE DECEASED_FNAME like \'%" + fullName + "%\' OR DECEASED_LNAME like \'%" + fullName + "%\'");
          }
          catch (SQLException e)
          {
            e.printStackTrace();
          }
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
      try // Search for plot number
      {
        dp.clear();
        queryDb(query.toString());
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
  }

  /**
   * Class housing actionListener for dateButton
   */
  class dateListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      String date = dateField.getText();
      String query = "SELECT * FROM PLOTS WHERE DATE_DECEASED like '" + date + "'";

      if (date.charAt(0) == '>')
      {
        date = date.substring(1);
        query = "SELECT * FROM PLOTS WHERE DATE_DECEASED > '" + date + "'";
      }
      else if (date.charAt(0) == '<')
      {
        date = date.substring(1);
        query = "SELECT * FROM PLOTS WHERE DATE_DECEASED < '" + date + "'";
      }
      else ;

      try // Search for plot number
      {
        dp.clear();
        queryDb(query);
      }
      catch (SQLException er)
      {
        er.printStackTrace();
      }

    }
  }

  /**
   * Class housing actionListener for intermentButton
   */
  class intermentListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      String inter = intermentField.getText();
      String query = "SELECT * FROM PLOTS WHERE UPPER(INTERMENT_NUMBER) like UPPER('" + inter + "')";
      try // Search for plot number
      {
        dp.clear();
        queryDb(query);
      }
      catch (SQLException er)
      {
        er.printStackTrace();
      }
    }
  }


  /**
   * A ResultSet is table of data representing a database.
   * Generated by executing a statement that queries the database.
   *
   * @param rs result set
   * @return true if rs is empty, false otherwise
   */
  public boolean isEmpty(ResultSet rs) throws java.sql.SQLException
  {
    return !rs.first();
  }

  /**
   * Returns whether or not given result set has more than 1 row
   *
   * @param rs result set
   * @return true if more than 1 row, false otherwise
   * @throws java.sql.SQLException
   */
  public boolean hasMoreThanOneRow(ResultSet rs) throws java.sql.SQLException
  {
    return rs.first() && rs.next();
  }

  /**
   * Capitalizes first letter of string s
   * Name changed from cleanUp to capitalize on 10/26 by Louie
   *
   * @param s string to capitalize
   * @return capitalized string
   */
  public String capitalize(String s)
  {
    char[] sc = s.toCharArray();
    sc[0] = Character.toUpperCase(sc[0]);
    s = new String(sc);
    return s;
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
        dp.add(fname, lname, intermentNumber, sectionNum, plotNum, graveNum, date, i, en); //add the current result to the table data
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
