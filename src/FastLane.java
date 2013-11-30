import javax.swing.*;

public class FastLane
{
    private static ScreenManager screenManager = new ScreenManager();

    public static void main( String[] args )
    {
        SwingUtilities.invokeLater( new Runnable() {
            @Override
            public void run()
            {
                screenManager.init();
                screenManager.showStartScreen();
            }
        });
    }

}

class ScreenManager
{
    private StartScreen startScreen;
    private MainMenu mainMenu;
    private VehicleSelection vehicleSelection;
    private InstructionsScreen instructionsScreen;
    private Race raceScreen;
    private JFrame frame;

    public void init()
    {
        frame = new JFrame();
        startScreen = new StartScreen(this);
        mainMenu = new MainMenu(this);
        vehicleSelection = new VehicleSelection(this);
        instructionsScreen = new InstructionsScreen(this);
        raceScreen = new Race(this);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(800, 650);
    }

    public JFrame getFrame()
    {
        return frame;
    }

    public void updateScreen()
    {
        frame.validate();
        frame.repaint();
    }

    public void showStartScreen()
    {
        startScreen.start();
        updateScreen();
    }

    public void showMainMenu()
    {
        mainMenu.start();
        updateScreen();
    }

    public void showSingleVehicleSelect()
    {
        vehicleSelection.start(1);
        updateScreen();
    }

    public void showMultiVehicleSelect()
    {
        vehicleSelection.start(2);
        updateScreen();
    }

    public void showInstructions()
    {
        instructionsScreen.start();
        updateScreen();
    }

    public void showRace(Player[] players)
    {
        raceScreen.start(players);
        updateScreen();
    }

    public void close()
    {
        System.exit(0);
    }
}