import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Quaternion;

public class ModelUtils
{
    public static ModelBuilder modelBuilder = new ModelBuilder();

    public static ModelInstance createBox( float xSize, float ySize, float zSize, Texture t, Color c )
    {
        Material boxMaterial = new Material();
        if (t != null)
            boxMaterial.set( TextureAttribute.createDiffuse(t) ); 
        if (c != null)
            boxMaterial.set( ColorAttribute.createDiffuse(c) ); 
            
        int usageCode = Usage.Position + Usage.ColorPacked + Usage.Normal + Usage.TextureCoordinates;

        Model boxModel = modelBuilder.createBox(xSize, ySize, zSize, boxMaterial, usageCode);
        Vector3 position = new Vector3(0,0,0);

        ModelInstance box = new ModelInstance(boxModel, position);
        return box;
    }

    public static ModelInstance createCubeTexture6( Texture[] texSides )
    {
        Material[] matSides = new Material[6];
        for (int i = 0; i < 6; i++)
        {
            matSides[i] = new Material(
                TextureAttribute.createDiffuse(texSides[i]) );
        }

        MeshPartBuilder mpb;
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();

        int usageCode = Usage.Position + Usage.Normal + Usage.TextureCoordinates + Usage.ColorPacked;

        modelBuilder.node().id = "negx";
        mpb = modelBuilder.part("negx", GL20.GL_TRIANGLES, usageCode, matSides[0] );
        mpb.setColor(Color.WHITE);
        mpb.rect( -0.5f,-0.5f,-0.5f, -0.5f,-0.5f,+0.5f, -0.5f,+0.5f,+0.5f, -0.5f,+0.5f,-0.5f, 0,0,-1 );
        
        modelBuilder.node().id = "posx";
        mpb = modelBuilder.part("posx", GL20.GL_TRIANGLES, usageCode, matSides[1] );
        mpb.setColor(Color.WHITE);
        mpb.rect( +0.5f,-0.5f,+0.5f, +0.5f,-0.5f,-0.5f, +0.5f,+0.5f,-0.5f, +0.5f,+0.5f,+0.5f,  0,0,1 );
        
        modelBuilder.node().id = "negy";
        mpb = modelBuilder.part("negy", GL20.GL_TRIANGLES, usageCode, matSides[2] );
        mpb.setColor(Color.WHITE);
        mpb.rect( -0.5f,-0.5f,+0.5f, -0.5f,-0.5f,-0.5f, +0.5f,-0.5f,-0.5f, +0.5f,-0.5f,+0.5f, 0,-1,0 );
        
        modelBuilder.node().id = "posy";
        mpb = modelBuilder.part("posy", GL20.GL_TRIANGLES, usageCode, matSides[3] );
        mpb.setColor(Color.WHITE);
        mpb.rect( +0.5f,+0.5f,+0.5f, +0.5f,+0.5f,-0.5f, -0.5f,+0.5f,-0.5f, -0.5f,+0.5f,+0.5f, 0,1,0 );
       
        modelBuilder.node().id = "negz";
        mpb = modelBuilder.part("negz", GL20.GL_TRIANGLES, usageCode, matSides[4] );
        mpb.setColor(Color.WHITE);
        mpb.rect(  +0.5f,-0.5f,-0.5f, -0.5f,-0.5f,-0.5f, -0.5f,+0.5f,-0.5f, +0.5f,+0.5f,-0.5f, 0,0,-1 );
        
        modelBuilder.node().id = "posz";
        mpb = modelBuilder.part("posz", GL20.GL_TRIANGLES, usageCode, matSides[5] );
        mpb.setColor(Color.WHITE);
        mpb.rect( -0.5f,-0.5f,+0.5f, +0.5f,-0.5f,+0.5f, +0.5f,+0.5f,+0.5f, -0.5f,+0.5f,+0.5f, 0,0,1 );
        
        Model model = modelBuilder.end();

        return new ModelInstance(model);
    }
    
    public static ModelInstance createSphere( float r, Texture t, Color c )
    {
        Material sphereMaterial = new Material();
        if (t != null)
            sphereMaterial.set( TextureAttribute.createDiffuse(t) ); 
        if (c != null)
            sphereMaterial.set( ColorAttribute.createDiffuse(c) );         
        int usageCode = Usage.Position + Usage.ColorPacked + Usage.Normal + Usage.TextureCoordinates;

        Model sphereModel = modelBuilder.createSphere(r,r,r, 32,32, sphereMaterial, usageCode);
        Vector3 position = new Vector3(0,0,0);

        ModelInstance sphere = new ModelInstance(sphereModel, position);
        return sphere;
    }
    
    public static ModelInstance createSphereInv( float r, Texture t, Color c )
    {
        Material sphereMaterial = new Material();
        if (t != null)
            sphereMaterial.set( TextureAttribute.createDiffuse(t) ); 
        if (c != null)
            sphereMaterial.set( ColorAttribute.createDiffuse(c) );         
        int usageCode = Usage.Position + Usage.ColorPacked + Usage.Normal + Usage.TextureCoordinates;

        Model sphereModel = modelBuilder.createSphere(r,r,r, 32,32, sphereMaterial, usageCode);
        for (Mesh m : sphereModel.meshes)
        {
            m.scale(1,1,-1) ;
        }
        Vector3 position = new Vector3(0,0,0);

        ModelInstance sphere = new ModelInstance(sphereModel, position);
        return sphere;
    }
}