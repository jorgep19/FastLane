import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class VehicleSelection extends Screen
{
    private Player[] players;
    int currentPlayerIndex;
    private int playerCount;
    private JButton[] buttons;
    private int currentOptionIndex;
    JLabel title;
    JTextField userName;
    // ImageIcon userVisual;

    public VehicleSelection(ScreenManager sm)
    {
        screenManager = sm;
        buttons = new JButton[7];
        setBackground(Color.black);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add title label
        title = new JLabel("Customize your car : Player " + (currentPlayerIndex + 1) + "  ");
        title.setForeground(Color.white);
        title.setFont(new Font("Impact", Font.ITALIC, 40));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);

        // create color button
        add(createColorButtons());

        // create text box
        add(createUserNameSection());

        // TODO fixcreate user image
        /*start(1);
        BufferedImage playerIcon = new BufferedImage( 30, 30, BufferedImage.TYPE_INT_ARGB );
        Graphics2D g2d = (Graphics2D) playerIcon.getGraphics();
        g2d.setColor(players[currentPlayerIndex].getColor());
        g2d.fillRect(0, 0, 30, 30);
        userVisual =  new ImageIcon(playerIcon);
        add(new JLabel(userVisual ));

        updateUserVisual(); */

        // create navigation button
        add(createNavButtons());

        updateButtons();
    }

    public void start(int count)
    {
        super.start();
        players = new Player[count];
        for (int i = 0; i< players.length; i++)
        {
            players[i] = new Player(Color.blue, "Player " + (currentPlayerIndex + 1) + "  ");
        }
        playerCount = count;
        currentPlayerIndex = 0;
        currentOptionIndex = 0;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_ENTER)
        {
            buttons[currentOptionIndex].doClick();
        }
        else if ( keyCode == KeyEvent.VK_ESCAPE)
        {
            cleanUpForChangeOfScreen();
            screenManager.showMainMenu();
        }
        else if (keyCode == KeyEvent.VK_KP_UP || keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_KP_DOWN || keyCode == KeyEvent.VK_DOWN)
        {
            if (currentOptionIndex > 4)
            {
                currentOptionIndex = 0;
            }
            else
            {
                currentOptionIndex = 5;
            }
            updateButtons();
        }
        else if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_KP_LEFT)
        {
            currentOptionIndex = currentOptionIndex == 0 ? 6 : currentOptionIndex - 1;
            updateButtons();
        }
        else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_KP_RIGHT)
        {
            currentOptionIndex = currentOptionIndex == 6 ? 0 : currentOptionIndex + 1;
            updateButtons();
        }
    }

    // Creates the navigation buttons and adds it to the pane
    private JPanel createColorButtons()
    {
        JPanel buttons = new JPanel();
        buttons.setBackground(Color.black);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

        addButton(
                buttons,
                Color.blue,
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        colorButtonsHandler(0, Color.blue);
                    }
                },
                0);

        addButton(
                buttons,
                Color.red,
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        colorButtonsHandler(1, Color.red);
                    }
                },
                1);

        addButton(
                buttons,
                Color.orange,
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        colorButtonsHandler(2, Color.orange);
                    }
                },
                2);

        addButton(
                buttons,
                Color.yellow,
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        colorButtonsHandler(3, Color.yellow);
                    }
                },
                3);

        addButton(
                buttons,
                Color.pink,
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        colorButtonsHandler(4, Color.pink);
                    }
                },
                4);

        return buttons;
    }

    private void colorButtonsHandler(int index, Color color)
    {
        players[currentPlayerIndex].setColor(color);
        currentOptionIndex = index;
        updateButtons();
        // TODO fix updateUserVisual();
    }

    private JPanel createUserNameSection()
    {
        JPanel userNameControlSet = new JPanel();
        userNameControlSet.setBackground(Color.black);
        userNameControlSet.setLayout(new BoxLayout(userNameControlSet, BoxLayout.X_AXIS));
        userNameControlSet.setAlignmentX(Component.CENTER_ALIGNMENT);
        userNameControlSet.setSize(50, 50);

        JLabel label = new JLabel("Name: ");
        label.setForeground(Color.white);
        label.setFont(new Font("Impact", Font.ITALIC, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        userNameControlSet.add(label);

        userName = new JTextField(20);
        userName.setSize(10, 10);
        userName.setAlignmentX(Component.CENTER_ALIGNMENT);
        userNameControlSet.add(userName);

        return userNameControlSet;
    }

    private JPanel createNavButtons()
    {
        JPanel navButtons = new JPanel();
        navButtons.setBackground(Color.black);
        navButtons.setLayout(new BoxLayout(navButtons, BoxLayout.X_AXIS));
        navButtons.setAlignmentX(Component.CENTER_ALIGNMENT);

        addButton(
                navButtons,
                "Back",
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        cleanUpForChangeOfScreen();
                        screenManager.showMainMenu();
                    }
                },
                5);

        addButton(
                navButtons,
                "Continue",
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        players[currentPlayerIndex].setName(userName.getText());

                        if ( currentPlayerIndex + 1 == playerCount)
                        {
                            cleanUpForChangeOfScreen();
                            screenManager.showRace(players);
                        }
                        else
                        {
                            currentPlayerIndex++;
                            title.setText("Customize your car : Player " + (currentPlayerIndex + 1) + "  ");
                        }
                    }
                },
                6);

        return navButtons;
    }

    // method for adding buttons
    private void addButton(JPanel panel, Color color, ActionListener listener, int index)
    {
        BufferedImage colorIcon = new BufferedImage( 31, 31, BufferedImage.TYPE_INT_ARGB );
        Graphics2D g2d = (Graphics2D) colorIcon.getGraphics();
        g2d.setColor(color);
        g2d.fillRect(0, 0, 29, 29);
        g2d.setColor(Color.black);
        g2d.drawRect(0,0, 31, 31);
        JButton button = new JButton( new ImageIcon(colorIcon));
        button.addActionListener(listener);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);
        buttons[index] = button;
    }

    // method for adding buttons
    private void addButton(JPanel panel, String label, ActionListener listener, int index)
    {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);
        buttons[index] = button;
    }

    // updates the buttons for providing feedback when the user is moving between the options
    private void updateButtons()
    {
        for (int i = 0; i < buttons.length; i++)
        {
            if (i == currentOptionIndex)
            {
                buttons[i].setBackground(Color.red);
                buttons[i].setForeground(Color.white);
            }
            else
            {
                buttons[i].setBackground(Color.gray);
                buttons[i].setForeground(Color.black);
            }
        }
    }

    /* TODO fix private void updateUserVisual()
    {
        BufferedImage playerIcon = new BufferedImage( 30, 30, BufferedImage.TYPE_INT_ARGB );
        Graphics2D g2d = (Graphics2D) playerIcon.getGraphics();
        g2d.setColor(players[currentPlayerIndex].getColor());
        g2d.fillRect(0, 0, 30, 30);
        userVisual.setImage(playerIcon);
        add(new JLabel(userVisual));
        validate();
        repaint();
    } */
}
