import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class StartScreen extends Screen
{
    private boolean showingStartLabel;
    private Timer startAnimTimer;

    public StartScreen(ScreenManager sm)
    {
        screenManager = sm;

        // Add title label
        setBackground(Color.black);
        JLabel title = new JLabel("Fast Lane ");
        title.setForeground(Color.white);
        title.setFont(new Font("Impact", Font.ITALIC, 40));
        add(title, BorderLayout.NORTH);

        // Add image
        add( new JLabel(new ImageIcon("splash.jpg")), BorderLayout.CENTER);

        // Add press Enter label
        final JLabel pressEnter = new JLabel("Press Enter ");
        pressEnter.setForeground(Color.white);
        pressEnter.setFont(new Font("Impact", Font.ITALIC, 20));
        add(pressEnter, BorderLayout.SOUTH);

        // set up timer for press enter animation
        startAnimTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                showingStartLabel = !showingStartLabel;

                if(showingStartLabel)
                {
                    pressEnter.setText("Press Enter ");
                }
                else
                {
                    pressEnter.setText("");
                }

                pressEnter.repaint();
            }
        });
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        e.consume();

        if(keyCode == KeyEvent.VK_ENTER)
        {
            cleanUpForChangeOfScreen();
            screenManager.showMainMenu();
        }
        if( keyCode == KeyEvent.VK_ESCAPE)
        {
            screenManager.close();
        }
    }

    // Setup for when entering the page
    public void start()
    {
        super.start();
        startAnimTimer.start();
    }

    // Cleans up when leaving this screen to release resources
    protected void cleanUpForChangeOfScreen()
    {
        super.cleanUpForChangeOfScreen();
        startAnimTimer.stop();
    }
}
