public class TheDemo extends BaseGame
{
    public void create() 
    {  
        // initialize resources common to multiple screens

        // initialize and set start screen 
        DemoScreen gs = new DemoScreen(this);
        setScreen( gs );
    }
}
