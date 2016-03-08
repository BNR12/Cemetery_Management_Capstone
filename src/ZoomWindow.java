import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
    /**
     * Window displaying a zoomed in map of specified section
     * @param section
     */
    public ZoomWindow(String section){
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

        }
    }

}

