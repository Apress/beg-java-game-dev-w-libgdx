import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
public class Launcher3
{
    public static void main ()
    {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        
        // change configuration settings
        config.width = 800;
        config.height = 600;
        
        TheGame myProgram = new TheGame();
        LwjglApplication launcher = new LwjglApplication( myProgram, config );
    }
}