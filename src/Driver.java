import javax.swing.*;
import java.awt.*;

/**
 * class housing main(), used to initialize and add displayPanel and MainWindow to one Frame
 */
public class Driver
{
  private static JFrame mainWindow; //Frame housing MainWindow and DisplayPanel
  private static MainWindow mw;
  private static DisplayPanel dp;

  /**
   * main, used to handle arguments and call Driver() to start GUI
   *
   * @param args string array containing run arguments
   */
  public static void main(String[] args)
  {
    //Handle arguments and other init issues
    run();
  }

  /**
   * Initialize the main JPanels and set them into the main Frame
   */
  public static void run()
  {
    //Initialize main JWindow to house search and display panels
    mainWindow = new JFrame();
    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainWindow.setLayout(new GridLayout(2, 1));
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) ((.66) * (screenSize.getWidth()));
    int height = (int) ((.66) * (screenSize.getHeight()));
    Dimension min = new Dimension(width, height); //set frame to 2/3 screen width and height
    mainWindow.setMinimumSize(min);
    dp = new DisplayPanel();  //Initialize display panel
    mw = new MainWindow(dp); //Initialize search panel
    //add
    mainWindow.add(mw);
    mainWindow.add(dp);
    //Make them visible
    mw.setVisible(true);
    dp.setVisible(true);
    mainWindow.setVisible(true);
  }
}
