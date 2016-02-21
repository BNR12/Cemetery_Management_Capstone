import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Objects;


/**
 * Created by brittanyregrut on 11/17/15.
 */
public class EditEntry extends JDialog
{

  private JLabel firstNameLabel = new JLabel("First Name: ");
  private JLabel lastNameLabel = new JLabel("Last Name: ");
  private JLabel plotNumberLabel = new JLabel("Plot Number: ");
  private JLabel dateDeceasedLabel = new JLabel("Date Deceased: ");
  private JLabel sectionLabel = new JLabel("Section: ");
  private JLabel graveNumberLabel = new JLabel("Grave Number: ");
  private JLabel intermentNumberLabel = new JLabel("Interment Number: ");
  private JTextField firstNameField;
  private JTextField lastNameField;
  private JTextField plotNumberField;
  private JTextField dateDeceasedField;
  private JTextField sectionField;
  private JTextField graveNumberField;
  private JTextField intermentNumberField;
  private JCheckBox linerBox;
  private JCheckBox CGCBox;
  private JCheckBox RMFBox;
  private JCheckBox monumentBox;
  private JCheckBox plantingBox;
  private JCheckBox veteranBox;
  private JCheckBox crematedBox;
  private JTextField linerNotes;
  private JTextField CGCNotes;
  private JTextField RMFNotes;
  private JTextField monumentNotes;
  private JButton save;


  /**
   * Opens a new window to display and edit a selected entry.
   *
   * @param en entry to be displayed for viewing/editing
   */
  public EditEntry(final Entry en)
  {
    //set basic functionality
    int width = 400, height = 200;
    Dimension min = new Dimension(width, height);
    setMinimumSize(min);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(new GridLayout(13, 2));
    setModal(true);

    //Initialize Text Fields
    firstNameField = new JTextField(en.getFirstName());
    lastNameField = new JTextField(en.getLastName());
    plotNumberField = new JTextField(en.getPlotNumber());
    dateDeceasedField = new JTextField(en.getDateDeceased());
    sectionField = new JTextField(en.getSection());
    graveNumberField = new JTextField(en.getGraveNumber());
    intermentNumberField = new JTextField(en.getIntermentNumber());

    //Initialize Checkboxes
    //Initialize Liner
    boolean liner = false;
    if (en.getLiner().equals("Liner"))
      liner = true;

    String linerText = en.getLinerNotes();
    linerBox = new JCheckBox("Liner?", liner);
    linerNotes = new JTextField(linerText);

    //Initialize CGC
    boolean cgc = false;
    if (en.getCGC().equals("CGC"))
      cgc = true;

    String cgcText = en.getCgcNotes();
    CGCBox = new JCheckBox("Cemetery Ground Care?", cgc);
    CGCNotes = new JTextField(cgcText);

    //Initialize RMF
    boolean rmf = false;
    if (en.getRMF().equals("RMF"))
      rmf = true;

    String rmfText = en.getRmfNotes();
    RMFBox = new JCheckBox("Road Maintenance Fee?", rmf);
    RMFNotes = new JTextField(rmfText);

    //Initialize Monument
    boolean monument = false;
    String monumentText = " ";
    if (!(en.getMonument().equals(" ")))
      monument = true;
    monumentText = en.getMonument();
    monumentBox = new JCheckBox("Monument?", monument);
    monumentNotes = new JTextField(monumentText);

    //Initialize Perpetual Planting
    boolean planting = false;
    if (en.getPlanting().equals("PP"))
      planting = true;
    plantingBox = new JCheckBox("Perpetual Planting?", planting);

    //Initialize Veteran
    boolean veteran = false;
    if (en.getVeteran().equals("Veteran"))
      veteran = true;
    veteranBox = new JCheckBox("Veteran?", veteran);

    //Initialize Cremated
    boolean cremated = false;
    if (en.getCremated().equals("Cremated"))
      cremated = true;
    crematedBox = new JCheckBox("Cremated?", cremated);

    //Initialize Button
    save = new JButton("Save Changes");

    //stylize
    Color backgroundColor = new Color(153, 204, 255); //initialize main color
    Font mainFont = new Font("Serif", Font.PLAIN, 20); //create main font for buttons
    setBackground(backgroundColor);

    //Add components
    add(firstNameLabel);
    add(firstNameField);
    add(lastNameLabel);
    add(lastNameField);
    add(dateDeceasedLabel);
    add(dateDeceasedField);
    add(sectionLabel);
    add(sectionField);
    add(plotNumberLabel);
    add(plotNumberField);
    add(graveNumberLabel);
    add(graveNumberField);
    add(intermentNumberLabel);
    add(intermentNumberField);
    add(linerBox);
    add(linerNotes);
    add(CGCBox);
    add(CGCNotes);
    add(RMFBox);
    add(RMFNotes);
    add(monumentBox);
    add(monumentNotes);
    add(plantingBox);
    add(veteranBox);
    add(crematedBox);
    add(save);
    pack();

    //Action Listener
    save.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        String lastN = lastNameField.getText();
        String firstN = firstNameField.getText();
        String date = dateDeceasedField.getText();
        String section = sectionField.getText();
        String plot = plotNumberField.getText();
        String grave = graveNumberField.getText();
        String intern = intermentNumberField.getText();
        String notes1 = linerNotes.getText();
        String notes2 = CGCNotes.getText();
        String notes3 = RMFNotes.getText();

        String cgc = "";
        String rmf = "";
        String monument = "";
        String planting = "";
        String liner = "";
        String vet = "";
        String cremated = "";

        if (CGCBox.isSelected())
          cgc = "CGC";
        if (RMFBox.isSelected())
          rmf = "RMF";
        if (monumentBox.isSelected())
          monument = monumentNotes.getText();
        if (plantingBox.isSelected())
          planting = "PP";
        if (linerBox.isSelected())
          liner = "Liner";
        if (veteranBox.isSelected())
          vet = "Veteran";
        if (crematedBox.isSelected())
          cremated = "Cremated";

        try {
          Class.forName("org.h2.Driver");
          Connection con = DriverManager.getConnection("jdbc:h2:./h2/cemetery;IFEXISTS=TRUE", "laboon", "bethshalom");
          Statement stmt = con.createStatement();

          //execute an insert into our DB

          if (Objects.equals(date, " ") || Objects.equals(date, "")) // Special case when the date is empty
		  { 
            boolean rs = stmt.execute("UPDATE PLOTS SET DECEASED_LNAME='" + lastN + "', DECEASED_FNAME='" + firstN +
                    "', PLOT_NUMBER='" + plot + "', DATE_DECEASED=NULL , SECTION='" + section + "', GRAVE='" + grave +
                    "', INTERMENT_NUMBER='" + intern + "', PN_LINER='" + liner + "', PN_CGC='" + cgc + "', PN_RMF='" + rmf +
                    "', MONUMENT='" + monument + "', PP_PLANTING='" + planting + "', VETERAN='" + vet + "', CREMATED='" + cremated +
                    "', FOUNDATIONS=NULL, LINER_NOTES='" + liner + "', RMF_NOTES='" + rmf + "', CGC_NOTES='" + cgc +
                    "' WHERE ( DECEASED_FNAME LIKE '" + en.getFirstName() + "' AND DECEASED_LNAME LIKE '" + en.getLastName() +
                    "' AND PLOT_NUMBER LIKE '" + en.getPlotNumber() + "' AND SECTION LIKE '" + en.getSection() + "' AND GRAVE LIKE '" + en.getGraveNumber() + "');");

          } 
		  else 
		  {
            boolean rs = stmt.execute("UPDATE PLOTS SET DECEASED_LNAME='" + lastN + "', DECEASED_FNAME='" + firstN +
                    "', PLOT_NUMBER='" + plot + "', DATE_DECEASED='" + date + "', SECTION='" + section + "', GRAVE='" + grave +
                    "', INTERMENT_NUMBER='" + intern + "', PN_LINER='" + liner + "', PN_CGC='" + cgc + "', PN_RMF='" + rmf +
                    "', MONUMENT='" + monument + "', PP_PLANTING='" + planting + "', VETERAN='" + vet + "', CREMATED='" + cremated +
                    "', FOUNDATIONS=NULL, LINER_NOTES='" + liner + "', RMF_NOTES='" + rmf + "', CGC_NOTES='" + cgc +
                    "' WHERE ( DECEASED_FNAME LIKE '" + en.getFirstName() + "' AND DECEASED_LNAME LIKE '" + en.getLastName() +
                    "' AND PLOT_NUMBER LIKE '" + en.getPlotNumber() + "' AND SECTION LIKE '" + en.getSection() + "' AND GRAVE LIKE '" + en.getGraveNumber() + "');");
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
    });
  }
}
