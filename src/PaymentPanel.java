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
public class PaymentPanel extends JPanel
{
    //Model for the table of search results
    private DefaultTableModel model;


    /**
     * DisplayPanel constructor
     */
    public PaymentPanel()
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
                    default:
                        return String.class;
                }
            }
        };

        //Initialize table and set model
        final JTable searchTable = new JTable();
        searchTable.setModel(model);

        //Add columns with appropriate headings to the table
        model.addColumn("Payment Number");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Date");
        model.addColumn("Amount");
        model.addColumn("Payment Type");

        //Add the table to the display panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JScrollPane(searchTable));


    }

    /**
     * Called when a search is performed. Called once for ever?y result returned. Adds the fields
     * for the result to be displayed to the data matrix and updates the display.
     *
     */
    public void add(int num, String payNum, String amt, String date, String payType, Entry en)
    {
        model.addRow(new Object[0]);
        model.setValueAt(payNum, num, 0);
        model.setValueAt(en.getFirstName(), num, 1);
        model.setValueAt(en.getLastName(), num, 2);
        model.setValueAt(date, num, 3);
        model.setValueAt(amt, num, 4);
        model.setValueAt(payType, num, 5);
        updateUI();
    }

    /**
     * Clears the display panel(this) of most recent output
     */
    public void clear()
    {
        model.setNumRows(0);
    }
}
