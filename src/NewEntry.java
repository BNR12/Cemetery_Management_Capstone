import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Popup JDialog extension to add new entry to database
 */
public class NewEntry extends JDialog
{
  private JTextField firstNameText;  //text field for first name
  private JLabel first = new JLabel("First Name:");
  private JTextField lastNameText;   //text field for last name
  private JLabel last = new JLabel("Last Name:");
  private JTextField plotNumberText; //text field for plot number
  private JLabel plot = new JLabel("Plot Number:");
  private JTextField dateText;       //text field for date
  private JLabel date = new JLabel("Date (yyyy-mm-dd):");
  private JTextField sectionText;       //text field for section
  private JLabel section = new JLabel("Section:");
  private JTextField graveNumberText;       //text field for grave
  private JLabel grave = new JLabel("Grave Number:");
  private JTextField intermentNumberText;       //text field for interment number
  private JLabel interment = new JLabel("Interment Number:");
  private JCheckBox linerBox;    //checkbox for liner
  private JCheckBox cgcBox;      //checkbox for cemetery ground care
  private JCheckBox rmfBox;      //checkbox for road maintenance fee
  private JCheckBox monumentBox;   //checkbox for monument
  private JCheckBox plantingBox;  //checkbox for perpetual planting
  private JCheckBox veteranBox;    //checkbox for veteran
  private JCheckBox crematedBox;   //checkbox for cremated
  private JTextField linerNotes;  //text field for liner notes
  private JTextField RMFNotes;  //text field for RMF Notes
  private JTextField CGCNotes;  //text field for CGC Notes
  private JTextField monumentNotes; //text field for monument notes
  private JButton add;               //button to add entry with text field arguments
  private JButton cancel;            //cancel new entry

  /**
   * NewEntry class that constructor
   */
  public NewEntry()
  {
    //set basic functionality
    int width = 400, height = 200;
    Dimension min = new Dimension(width, height);
    setMinimumSize(min);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(new GridLayout(20, 2));
    setModal(true);
    //init components
    add = new JButton("Add Entry");
    cancel = new JButton("Cancel");
    firstNameText = new JTextField();
    lastNameText = new JTextField();
    plotNumberText = new JTextField();
    dateText = new JTextField();
    sectionText = new JTextField();
    graveNumberText = new JTextField();
    intermentNumberText = new JTextField();
    linerNotes = new JTextField();
    RMFNotes = new JTextField();
    CGCNotes = new JTextField();
    monumentNotes = new JTextField();
    crematedBox = new JCheckBox("Cremated?", false);
    plantingBox = new JCheckBox("Perpetual Planting?", false);
    monumentBox = new JCheckBox("Monument?", false);
    veteranBox = new JCheckBox("Veteran?", false);
    rmfBox = new JCheckBox("Road Maintenance Fee?", false);
    cgcBox = new JCheckBox("Cemetery Ground Care?", false);
    linerBox = new JCheckBox("Liner?", false);


    //stylize
    Color backgroundColor = new Color(153, 204, 255); //initialize main color
    Font mainFont = new Font("Serif", Font.PLAIN, 20); //create main font for buttons
    add.setFont(mainFont);
    cancel.setFont(mainFont);
    setBackground(backgroundColor);
    //add components
    add(first);
    add(firstNameText);
    add(last);
    add(lastNameText);
    add(plot);
    add(plotNumberText);
    add(date);
    add(dateText);
    add(section);
    add(sectionText);
    add(grave);
    add(graveNumberText);
    add(interment);
    add(intermentNumberText);
    add(linerBox);
    add(linerNotes);
    add(rmfBox);
    add(RMFNotes);
    add(cgcBox);
    add(CGCNotes);
    add(monumentBox);
    add(monumentNotes);
    add(plantingBox);
    add(veteranBox);
    add(crematedBox);
    add(new JLabel(" ")); //spacer
    add(add);
    add(cancel);
    pack();
    //set default enter function
    JRootPane rootPane = SwingUtilities.getRootPane(add);
    rootPane.setDefaultButton(add);
    //LISTENERS
    firstNameText.addMouseListener(new textFieldListener());
    lastNameText.addMouseListener(new textFieldListener());
    plotNumberText.addMouseListener(new textFieldListener());
    dateText.addMouseListener(new textFieldListener());
    sectionText.addMouseListener(new textFieldListener());
    graveNumberText.addMouseListener(new textFieldListener());
    intermentNumberText.addMouseListener(new textFieldListener());
    linerNotes.addMouseListener(new textFieldListener());
    RMFNotes.addMouseListener(new textFieldListener());
    CGCNotes.addMouseListener(new textFieldListener());
    monumentNotes.addMouseListener(new textFieldListener());
    add.addActionListener(new addEntryListener());
    cancel.addActionListener(new cancelButtonListener());
  }

  /**
   * Class housing actionListener for add button
   */
  class addEntryListener implements ActionListener
  {
    /**
     * Adds current text in text fields as new database entry
     *
     * @param e button click action event
     */
    public void actionPerformed(ActionEvent e)
    {
      //int day = 0, month = 0, year = 0;
      String firstName = firstNameText.getText();
      String lastName = lastNameText.getText();
      String plotNumber = plotNumberText.getText();
      String date = dateText.getText();
      String section = sectionText.getText();
      String grave = graveNumberText.getText();
      String interment = intermentNumberText.getText();
      String pnliner;

      if (linerBox.isSelected())
      {
        pnliner = "Liner";
      }
      else
      {
        pnliner = " ";
      }
      String pncgc;
      if (cgcBox.isSelected())
      {
        pncgc = "CGC";
      }
      else
      {
        pncgc = " ";
      }
      String pnrmf;
      if (rmfBox.isSelected())
      {
        pnrmf = "RMF";
      }
      else
      {
        pnrmf = " ";
      }
      String monument;
      if (monumentBox.isSelected())
      {
        monument = monumentNotes.getText();
      }
      else
      {
        monument = " ";
      }
      String ppplanting;
      if (plantingBox.isSelected())
      {
        ppplanting = "PP";
      }
      else
      {
        ppplanting = " ";
      }
      String veteran;
      if (veteranBox.isSelected())
      {
        veteran = "Veteran";
      }
      else
      {
        veteran = " ";
      }
      String cremated;
      if (crematedBox.isSelected())
      {
        cremated = "Cremated";
      }
      else
      {
        cremated = " ";
      }
      String lnotes = linerNotes.getText();
      String cnotes = CGCNotes.getText();
      String rnotes = RMFNotes.getText();
      String mnotes = monumentNotes.getText();


      if (firstName.equals(""))
      {
        firstNameText.setForeground(Color.RED);
        firstNameText.setText("PLEASE ENTER A FIRST NAME");
      }
      else
      {
        //Error handling for first name not implemented
      }
      if (lastName.equals(""))
      {
        lastNameText.setForeground(Color.RED);
        lastNameText.setText("PLEASE ENTER A LAST NAME");
      }
      else
      {
        //Error handling for last name not implemented
      }
      if (plotNumber.equals(""))
      {
        plotNumberText.setForeground(Color.RED);
        plotNumberText.setText("PLEASE ENTER A PLOT NUMBER");
      }
      else
      {
        //Error handling for plot not implemented
      }
      if (date.equals(""))
      {

      }
      else if (dateText.getForeground() == Color.BLACK)
      {
        //Error handling for date not implemented
      }
      try
      {
        Class.forName("org.h2.Driver");
        Connection con = DriverManager.getConnection("jdbc:h2:./h2/cemetery;IFEXISTS=TRUE", "laboon", "bethshalom");
        Statement stmt = con.createStatement();

        //ensure that we enter new data in titlecase
        firstName = capitalize(firstName);
        lastName = capitalize(lastName);

        //execute an insert into our DB
        if (!date.equals(""))
        {
          boolean rs = stmt.execute("" +
              "INSERT INTO PLOTS (DECEASED_FNAME, DECEASED_LNAME, " +
              "PLOT_NUMBER, DATE_DECEASED, SECTION, GRAVE, INTERMENT_NUMBER, PN_LINER, " +
              "PN_CGC, PN_RMF, MONUMENT, PP_PLANTING, VETERAN, CREMATED, LINER_NOTES, " +
              "RMF_NOTES, CGC_NOTES, MONUMENT_NOTES) " +
              "VALUES ('" + firstName + "'," + "'" + lastName + "'," + "'" + plotNumber + "'," +
              "'" + date + "'," + "'" + section + "'," + "'" + grave + "'," + "'" + interment + "'," +
              "'" + pnliner + "'," + "'" + pncgc + "'," + "'" + pnrmf + "'," + "'" + monument + "'," +
              "'" + ppplanting + "'," + "'" + veteran + "'," + "'" + cremated + "'," + "'" + lnotes + "'," +
              "'" + cnotes + "'," + "'" + rnotes + "'," + "'" + mnotes + "');"
          );
        }
        else
        {
          boolean rs = stmt.execute("" +
              "INSERT INTO PLOTS (DECEASED_FNAME, DECEASED_LNAME, " +
              "PLOT_NUMBER, DATE_DECEASED, SECTION, GRAVE, INTERMENT_NUMBER, PN_LINER, " +
              "PN_CGC, PN_RMF, MONUMENT, PP_PLANTING, VETERAN, CREMATED, LINER_NOTES, " +
              "RMF_NOTES, CGC_NOTES, MONUMENT_NOTES) " +
              "VALUES ('" + firstName + "'," + "'" + lastName + "'," + "'" + plotNumber + "'," +
              "NULL," + "'" + section + "'," + "'" + grave + "'," + "'" + interment + "'," +
              "'" + pnliner + "'," + "'" + pncgc + "'," + "'" + pnrmf + "'," + "'" + monument + "'," +
              "'" + ppplanting + "'," + "'" + veteran + "'," + "'" + cremated + "'," + "'" + lnotes + "'," +
              "'" + cnotes + "'," + "'" + rnotes + "'," + "'" + mnotes + "');"
          );
        }
        stmt.close();
        con.close();
      }
      catch (Exception er)
      {
        System.out.println(er.getMessage());
      }
      setVisible(false);
      dispose();
    }
  }

  /**
   * Class housing actionListener for cancel button
   */
  class cancelButtonListener implements ActionListener
  {
    /**
     * Disposes of NewEntry(this)
     *
     * @param e button click action event
     */
    public void actionPerformed(ActionEvent e)
    {
      setVisible(false);
      dispose();
    }
  }

  /**
   * Class housing mouseListener for textFields
   */
  class textFieldListener implements MouseListener
  {

    /**
     * Resets text fields of error messages on click
     *
     * @param e mouse click event
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
      JTextField curr = (JTextField) e.getSource();
      if (curr.getForeground() == Color.RED)
      {
        curr.setForeground(Color.BLACK);
        curr.setText("");
      }
    }

    /**
     * Unused
     *
     * @param e MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e)
    {
      return;
    }

    /**
     * Unused
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
      return;
    }

    /**
     * Unused
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseEntered(MouseEvent e)
    {
      return;
    }

    /**
     * Unused
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseExited(MouseEvent e)
    {
      return;
    }
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
}
