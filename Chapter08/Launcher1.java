import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
public class Launcher1
{
    public static void main () 
    {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 600;
        TheTest myProgram = new TheTest();
        LwjglApplication launcher = new LwjglApplication( myProgram, config );
    }
}