/**
 * Created by brittanyregrut on 2/21/16.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;



public class MapWindow extends JDialog {

    private JLabel image;
    private JPanel zoomPanel;
    private JButton submit;
    private JLabel zoomLabel = new JLabel("Zoom in on a section: ");
    private JComboBox sectionChooser;

    public MapWindow(){
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
            BufferedImage myPicture = ImageIO.read(new File("/Users/brittanyregrut/Desktop/Cemetery_Management_Capstone/src/map.png"));
            image = new JLabel(new ImageIcon(myPicture));
            c.gridx = 0;
            c.gridy = 0;
            add(image, c);
        }catch (IOException e){
            //Map file not found
            System.out.println("Cemetery Map not found");
            System.exit(1);
        }

        //Add panel to zoom in on a particular section
        zoomPanel = new JPanel(new GridLayout(1, 3));
        Dimension zoom = new Dimension(800, 100);
        zoomPanel.setMaximumSize(zoom);

        //Initialize drop down list
        String[] sections = {"A", "AA", "B", "BB", "C", "D", "DD", "DX", "E", "F", "G", "H", "J", "K", "N", "O"};
        sectionChooser = new JComboBox(sections);

        //Initialize button/stylize
        submit = new JButton("Go!");
        setBackground(backgroundColor);
        submit.setFont(mainFont);
        zoomLabel.setFont(mainFont);
        sectionChooser.setFont(mainFont);
        submit.addActionListener(new zoomListener());

        //Add components to panel, and add panel to window
        zoomPanel.add(zoomLabel);
        zoomPanel.add(sectionChooser);
        zoomPanel.add(submit);
        c.gridx = 0;
        c.gridy = 1;
        add(zoomPanel, c);
    }

    /**
     * Class housing actionListener for submit button
     */
    class zoomListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //Will open a new window displaying a zoomed in map for specified section
            String selected = (String)sectionChooser.getSelectedItem();
            ZoomWindow z = new ZoomWindow(selected);
            z.setVisible(true);
        }
    }

}
