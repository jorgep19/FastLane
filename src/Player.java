import java.awt.*;

public class Player
{
    private int x;
    private int y;
    private Color color;
    private String name;
    private int time;
    private Rectangle rect;

    public Player()
    {
        x = 0;
        y = 0;
        color = Color.blue;
        name = "Player 1";
        time = 0;
        initShape();
    }

    public Player(Color c, String n)
    {
        x = 0;
        y = 0;
        color = c;
        name = n;
        time = 0;
        initShape();
    }

    public Shape getShape()
    {
        return rect;
    }

    public void setColor(Color newColor)
    {
        color = newColor;
    }

    public void setName(String newName)
    {
        name = newName;
    }

    public Color getColor()
    {
        return color;
    }

    private void initShape()
    {
        rect = new Rectangle(150, 100, 50, 50);
    }
}
