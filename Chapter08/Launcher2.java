import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
public class Launcher2
{
    public static void main ()
    {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        
        // change configuration settings
        config.width = 800;
        config.height = 600;
        
        TheDemo myProgram = new TheDemo();
        LwjglApplication launcher = new LwjglApplication( myProgram, config );
    }
}