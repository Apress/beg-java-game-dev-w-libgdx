import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class DemoScreen extends BaseScreen
{
    BaseActor3D player;
        
    public DemoScreen(BaseGame g)
    {  super(g);  }

    public void create()
    {    
        BaseActor3D screen = new BaseActor3D();
        Texture screenTex = new Texture(Gdx.files.internal("assets/starfish-collector.png"), true);
        screenTex.setFilter( TextureFilter.Linear, TextureFilter.Linear );
        ModelInstance screenInstance = ModelUtils.createBox(16, 12, 0.1f, screenTex, null);
        screen.setModelInstance(screenInstance);
        mainStage3D.addActor(screen);

        Texture texCrate = new Texture(Gdx.files.internal("assets/crate.jpg"), true);

        BaseActor3D markerO = new BaseActor3D();
        ModelInstance modCrateO = ModelUtils.createBox(1,1,1, texCrate, Color.PURPLE);
        markerO.setModelInstance(modCrateO);
        markerO.setPosition(0,0,0);
        mainStage3D.addActor(markerO);
        
        BaseActor3D markerX = markerO.clone();
        markerX.setColor(Color.RED);
        markerX.setPosition(5,0,0);
        mainStage3D.addActor(markerX);
        
        BaseActor3D markerY = markerO.clone();
        markerY.setColor(Color.GREEN);
        markerY.setPosition(0,5,0);
        mainStage3D.addActor(markerY);
        
        BaseActor3D markerZ = markerO.clone();
        markerZ.setColor(Color.BLUE);
        markerZ.setPosition(0,0,5);
        mainStage3D.addActor(markerZ);
       
        player = new BaseActor3D();
        Texture[] texSides = { 
                new Texture(Gdx.files.internal("assets/xneg.png")),
                new Texture(Gdx.files.internal("assets/xpos.png")),
                new Texture(Gdx.files.internal("assets/yneg.png")),
                new Texture(Gdx.files.internal("assets/ypos.png")),
                new Texture(Gdx.files.internal("assets/zneg.png")),
                new Texture(Gdx.files.internal("assets/zpos.png"))  };

        ModelInstance testModel = ModelUtils.createCubeTexture6(texSides);
        player.setModelInstance(testModel);
        player.setPosition(0,1,8);
        mainStage3D.addActor(player);
        
        mainStage3D.setCameraPosition(3,4,10);
        mainStage3D.setCameraDirection(0,0,0);
        
    }

    public void update(float dt)
    {    
        float speed = 3.0f;
        float rotateSpeed = 45.0f;
        
        if ( !(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)) )
        {
            if ( Gdx.input.isKeyPressed(Keys.W) )
                player.moveForward( speed * dt );
            if ( Gdx.input.isKeyPressed(Keys.S) )
                player.moveForward( -speed * dt ); 
            if ( Gdx.input.isKeyPressed(Keys.A) )
                player.moveRight( -speed * dt );
            if ( Gdx.input.isKeyPressed(Keys.D) )
                player.moveRight( speed * dt );
            if ( Gdx.input.isKeyPressed(Keys.Q) )
                player.turn( -rotateSpeed * dt );   
            if ( Gdx.input.isKeyPressed(Keys.E) )
                player.turn( rotateSpeed * dt );
            if ( Gdx.input.isKeyPressed(Keys.R) )
                player.moveUp( speed * dt );
            if ( Gdx.input.isKeyPressed(Keys.F) )
                player.moveUp( -speed * dt );
        }

        if ( Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT) )
        {
            if (Gdx.input.isKeyPressed(Keys.W)) 
                mainStage3D.moveCameraForward( speed * dt );
            if (Gdx.input.isKeyPressed(Keys.S)) 
                mainStage3D.moveCameraForward( -speed * dt );
            if (Gdx.input.isKeyPressed(Keys.A)) 
                mainStage3D.moveCameraRight( speed * dt );
            if (Gdx.input.isKeyPressed(Keys.D)) 
                mainStage3D.moveCameraRight( -speed * dt );

            if (Gdx.input.isKeyPressed(Keys.R)) 
                mainStage3D.moveCameraUp( speed * dt );
            if (Gdx.input.isKeyPressed(Keys.F)) 
                mainStage3D.moveCameraUp( -speed * dt );

            if (Gdx.input.isKeyPressed(Keys.Q)) 
                mainStage3D.turnCamera(-rotateSpeed * dt);
            if (Gdx.input.isKeyPressed(Keys.E)) 
                mainStage3D.turnCamera(rotateSpeed * dt);
            
            if (Gdx.input.isKeyPressed(Keys.T)) 
                mainStage3D.tiltCamera(-rotateSpeed * dt); 
            if (Gdx.input.isKeyPressed(Keys.G)) 
                mainStage3D.tiltCamera(rotateSpeed * dt);
        }
    }
    
    public boolean keyDown(int keycode)
    {
        if (keycode == Keys.Z)    
            game.setScreen( new DemoScreen(game) );

        return false;
    }
}