public class TheGame extends BaseGame
{
    public void create() 
    {  
        // initialize resources common to multiple screens

        // initialize and set start screen 
        GameScreen gs = new GameScreen(this);
        setScreen( gs );
    }
}
