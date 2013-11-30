import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Screen extends JPanel implements KeyListener
{
    protected ScreenManager screenManager;

    @Override
    public void keyTyped(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    // Setup for when entering the page
    public void start()
    {
        screenManager.getFrame().setContentPane(this);
        screenManager.getFrame().addKeyListener(this);
    }

    // Cleans up when leaving this screen to release resources
    protected void cleanUpForChangeOfScreen()
    {
        screenManager.getFrame().removeKeyListener(this);
    }
}
