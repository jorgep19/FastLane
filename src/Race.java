import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Race extends Screen implements ActionListener
{
    Timer timer = new Timer(5, this);
    private Player[] players;
    private Image bgImage;
    private int x = 800;
    private int y = 0;
    private Rectangle computerDriver;
    private Rectangle marker1;
    private Rectangle marker2;
    private Rectangle marker3;
    private Rectangle marker4;
    private int compX = 0;
    private int compY = 0;
    private int xSpeed = 3;
    private int ySpeed = 3;
    private boolean isMovingOnY = false;

    public Race(ScreenManager sm)
    {
        screenManager = sm;
        bgImage = new ImageIcon("bg.png").getImage();
    }

    public void start(Player[] plys)
    {
        super.start();
        players = plys;
        timer.start();

        computerDriver = new Rectangle(170, 160, 25, 25);
        marker1 = new Rectangle(2100, 100, 50, 100);
        marker2 = new Rectangle(2070, 1500, 150, 50);
        marker3 = new Rectangle(230, 1450, 50, 150);
        marker4 = new Rectangle(200, 150, 100, 25);
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.green);
        g2d.fillRect(0, 0, 1000, 1000);
        g.drawImage(bgImage, 800 - x, y, null);

        g2d.setColor(players[0].getColor());
        g2d.fill(players[0].getShape());

        g2d.setColor(Color.gray);
        g2d.fill(computerDriver);
    }

    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_KP_RIGHT)
        {
            x = x + 50;
            marker1.setLocation(marker1.x - 50, marker1.y);
            marker2.setLocation(marker2.x - 50, marker2.y);
            marker3.setLocation(marker3.x - 50, marker3.y);
            marker4.setLocation(marker4.x - 50, marker4.y);
            computerDriver.setLocation(computerDriver.x - 50, computerDriver.y);
        }
        else if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_KP_LEFT)
        {
            x = x - 50;
            marker1.setLocation(marker1.x + 50, marker1.y);
            marker2.setLocation(marker2.x + 50, marker2.y);
            marker3.setLocation(marker3.x + 50, marker3.y);
            marker4.setLocation(marker4.x + 50, marker4.y);
            computerDriver.setLocation(computerDriver.x + 50, computerDriver.y);
        }
        else if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_KP_UP)
        {
            y = y + 50;
            marker1.setLocation(marker1.x, marker1.y + 50);
            marker2.setLocation(marker2.x, marker2.y + 50);
            marker3.setLocation(marker3.x, marker3.y + 50);
            marker4.setLocation(marker4.x, marker4.y + 50);
            computerDriver.setLocation(computerDriver.x, computerDriver.y + 50);
        }
        else if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_KP_DOWN)
        {
            y = y - 50;
            marker1.setLocation(marker1.x, marker1.y - 50);
            marker2.setLocation(marker2.x, marker2.y - 50);
            marker3.setLocation(marker3.x, marker3.y - 50);
            marker4.setLocation(marker4.x, marker4.y - 50);
            computerDriver.setLocation(computerDriver.x, computerDriver.y - 50);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        compX = computerDriver.x + xSpeed;
        compY = computerDriver.y + ySpeed;

        if(computerDriver.intersects(marker1))
        {
            isMovingOnY = true;
            ySpeed = 3;
        }
        else if (computerDriver.intersects(marker2))
        {
            isMovingOnY = false;
            xSpeed = -3;

        }
        else if ( computerDriver.intersects(marker3))
        {
            isMovingOnY = true;
            ySpeed = -3;
        }
        else if (computerDriver.intersects(marker4))
        {
            isMovingOnY = false;
            xSpeed = 3;
        }


        if(isMovingOnY)
        {
            xSpeed = 0;
            computerDriver.setLocation(computerDriver.x, compY);
        }
        else
        {
            ySpeed = 0;
            computerDriver.setLocation(compX, computerDriver.y);
        }

        repaint();
    }
}
