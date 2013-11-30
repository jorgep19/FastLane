import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainMenu extends Screen
{
    private JButton[] buttons;
    private int currentOptionIndex;

    public MainMenu(ScreenManager sm)
    {
        screenManager = sm;
        buttons = new JButton[4];
        currentOptionIndex = 0;
        setBackground(Color.black);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add title label
        JLabel title = new JLabel("Fast Lane ");
        title.setForeground(Color.white);
        title.setFont(new Font("Impact", Font.ITALIC, 40));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);

        // add Image
        JLabel image = new JLabel(new ImageIcon("mainMenu.jpg"));
        image.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(image);

        // Add buttons
        add(createButtonsSection());
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        e.consume();

        // navigate based on user input
        if(keyCode == KeyEvent.VK_ENTER)
        {
            buttons[currentOptionIndex].doClick();
        }
        else if (keyCode == KeyEvent.VK_ESCAPE)
        {
            this.cleanUpForChangeOfScreen();
            screenManager.showStartScreen();
        }
        else if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_KP_UP)
        {
            currentOptionIndex = currentOptionIndex == 0 ? 3 : currentOptionIndex - 1;
            updateButtons();
        }
        else if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_KP_DOWN)
        {
            currentOptionIndex = currentOptionIndex == 3 ? 0 : currentOptionIndex + 1;
            updateButtons();
        }
    }

    // Creates the navigation buttons and adds it to the pane
    private JPanel createButtonsSection()
    {
        JPanel buttons = new JPanel();
        buttons.setBackground(Color.black);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

        addButton(
                buttons,
                "Single Player",
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        cleanUpForChangeOfScreen();
                        screenManager.showSingleVehicleSelect();
                    }
                },
                0);

        addButton(
                buttons,
                "Two Players",
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        cleanUpForChangeOfScreen();
                        screenManager.showMultiVehicleSelect();
                    }
                },
                1);

        addButton(
                buttons,
                "Instructions",
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        cleanUpForChangeOfScreen();
                        screenManager.showInstructions();
                    }
                },
                2);

        addButton(
                buttons,
                "Exit",
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        screenManager.close();
                    }
                },
                3);

        updateButtons();

        return buttons;
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
}
