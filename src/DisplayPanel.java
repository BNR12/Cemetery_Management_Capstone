import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * DisplayPanel class designed to output database queries in MainWindow
 */
public class DisplayPanel extends JPanel
{
  //Model for the table of search results
  private DefaultTableModel model;


  //Arraylist to hold the entries in the table
  ArrayList<Entry> results = new ArrayList<Entry>();

  //AL of items that are checked true - will be printed
  ArrayList<Entry> toPrint = new ArrayList<Entry>();

  /**
   * DisplayPanel constructor
   */
  public DisplayPanel()
  {
    model = new DefaultTableModel()
    {
      //Switch statement specifying first 6 rows as strings, and the last row to be booleans (checkboxes)
      public Class<?> getColumnClass(int column)
      {
        switch (column)
        {
          case 0:
            return String.class;
          case 1:
            return String.class;
          case 2:
            return String.class;
          case 3:
            return String.class;
          case 4:
            return String.class;
          case 5:
            return String.class;
          case 6:
            return String.class;
          case 7:
            return Boolean.class;
          default:
            return String.class;
        }
      }
    };

    //Initialize table and set model
    final JTable searchTable = new JTable();
    searchTable.setModel(model);

    //Add columns with appropriate headings to the table
    model.addColumn("First Name");
    model.addColumn("Last Name");
    model.addColumn("Interment");
    model.addColumn("Section");
    model.addColumn("Plot Number");
    model.addColumn("Grave Number");
    model.addColumn("Date Deceased");
    model.addColumn("Select Entry");

    //Add the table to the display panel
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    add(new JScrollPane(searchTable));

    //Create panel to hold buttons
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(1, 5));

    //Create new buttons and add to the button panel
    JButton select = new JButton("View Entries");
    buttonPanel.add(select);

    final JButton print = new JButton("Print Selected Entries");
    buttonPanel.add(print);

    JButton all = new JButton("Select All");
    buttonPanel.add(all);

    JButton deselectAll = new JButton("Deselect All");
    buttonPanel.add(deselectAll);

    JButton createButton = new JButton("Create New Entry");
    buttonPanel.add(createButton);

    //Add buttons to the display panel
    add(buttonPanel);
    setVisible(true);

    //Action listener for the all button to select all entries
    all.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        //Set the value of column 6 to true for every row
        for (int i = 0; i < searchTable.getRowCount(); i++)
        {
          searchTable.setValueAt(true, i, 7);
        }

      }
    });

    //Action listener for the all button to deselect all entries
    deselectAll.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        //Set the value of column 6 to false for every row
        for (int i = 0; i < searchTable.getRowCount(); i++)
        {
          searchTable.setValueAt(false, i, 7);
        }

      }
    });

    //Action listener for the print button to print selected entries to a text file
    print.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        //make sure that the print array is empty
        toPrint.clear();

        for (int i = 0; i < searchTable.getRowCount(); i++)
        {
          Boolean checked = Boolean.valueOf(searchTable.getValueAt(i, 7).toString());

          //print each selected entry to a text file
          if (checked)
          {
            toPrint.add(results.get(i));
          }
        }
        print(toPrint);
      }
    });

    //Action listener for the select button to view checked search results
    select.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {

        //determine if a row is selected
        for (int i = 0; i < searchTable.getRowCount(); i++)
        {
          Boolean checked = Boolean.valueOf(searchTable.getValueAt(i, 7).toString());

          //display an entry view/edit box for each selected entry
          if (checked)
          {
            //New ContentPane
            EditEntry edit = new EditEntry(results.get(i));
            edit.setVisible(true);
          }
        }
      }
    });

    createButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        NewEntry entry = new NewEntry();
        entry.setVisible(true);
      }
    });
  }

  /**
   * Called when a search is performed. Called once for ever?y result returned. Adds the fields
   * for the result to be displayed to the data matrix and updates the display.
   *
   * @param fn  first name
   * @param ln  last name
   * @param sn  section number
   * @param pn  plot number
   * @param gn  grave number
   * @param d   date
   * @param num number of rows
   * @param en  Entry object of the result being added
   */
  public void add(String fn, String ln, String in, String sn, String pn, String gn, String d, int num, Entry en)
  {
    results.add(num, en); // Add the full entry data to the arraylist
    model.addRow(new Object[0]);
    model.setValueAt(fn, num, 0);
    model.setValueAt(ln, num, 1);
    model.setValueAt(in, num, 2);
    model.setValueAt(sn, num, 3);
    model.setValueAt(pn, num, 4);
    model.setValueAt(gn, num, 5);
    model.setValueAt(d, num, 6);
    model.setValueAt(false, num, 7);
    updateUI();
  }

  /**
   * Clears the display panel(this) of most recent output
   */
  public void clear()
  {
    model.setNumRows(0);
    results.clear();
  }

  /**
   * Writes every object that was checked in the table to a newly created text file
   *
   * @param checked : an Array:ist of DB Entry objects
   */
  public void print(ArrayList<Entry> checked)
  {
    //Make sure they've selected items so we don't create an empty file
    if (!checked.isEmpty())
    {
      //get the current time/date
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH_mm");
      try
      {
        //new file. name it the date + time
        PrintWriter writer = new PrintWriter(dateFormat.format(new Date()) + ".txt", "UTF-8");
        //write a header
        writer.println("----------------------------------------------------------");
        writer.format("%-16s%-16s%-16s%-16s\n", "LAST", "FIRST", "PLOT", "INTERNMENT");
        writer.println("----------------------------------------------------------");
        //for every checked item, write the info to the file
        for (Entry e : checked)
          writer.format("%-16s%-16s%-16s%-16s\n", e.getLastName(), e.getFirstName(), e.getSection() + "-" + e.getPlotNumber() + "-" + e.getGraveNumber(), e.getIntermentNumber());
        //close file
        writer.close();
      }
      catch (FileNotFoundException e)
      {
        e.printStackTrace();
      }
      catch (UnsupportedEncodingException e)
      {
        e.printStackTrace();
      }
    }

  }
}
