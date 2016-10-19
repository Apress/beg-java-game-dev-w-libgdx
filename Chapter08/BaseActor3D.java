import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Intersector.MinimumTranslationVector;

public class BaseActor3D
{
    private ModelInstance modelData;
    private final Vector3 position;
    private final Quaternion rotation;
    private final Vector3 scale;
    private Polygon boundingPolygon;

    public BaseActor3D()
    {
        modelData = null;
        position  = new Vector3(0,0,0);
        rotation  = new Quaternion();
        scale     = new Vector3(1,1,1);
        boundingPolygon = null;
    }

    public void setModelInstance(ModelInstance m)
    {  modelData = m;  }

    public Matrix4 calculateTransform()
    {  return new Matrix4(position, rotation, scale);  }

    public void act(float dt)
    {  modelData.transform.set( calculateTransform() );  }

    public void draw(ModelBatch batch, Environment env)
    {  batch.render(modelData, env);  } 
    
    public void setColor(Color c)
    {  
        for (Material m : modelData.materials)
            m.set( ColorAttribute.createDiffuse(c) ); 
    }
    
    public Vector3 getPosition()
    {  return position;  }

    public void setPosition(Vector3 v)
    {  position.set(v);  }
    public void setPosition(float x, float y, float z)
    {  position.set(x,y,z);  }

    public void addPosition(Vector3 v)
    {  position.add(v);  }
    public void addPosition(float x, float y, float z)
    {  addPosition( new Vector3(x,y,z) );  }

    public void moveForward(float dist)
    {  addPosition( rotation.transform( new Vector3(0,0,-1) ).scl( dist ) );  }
    public void moveUp(float dist)
    {  addPosition( rotation.transform( new Vector3(0,1,0) ).scl( dist ) );  }
    public void moveRight(float dist)
    {  addPosition( rotation.transform( new Vector3(1,0,0) ).scl( dist ) );  }

    public float getTurnAngle()
    {   return rotation.getAngleAround(0,-1,0);  }
    public void setTurnAngle(float degrees)
    {  rotation.set( new Quaternion(Vector3.Y,degrees) );  }
    public void turn(float degrees)
    {  rotation.mul( new Quaternion(Vector3.Y,-degrees) );  }
    
    // 2D collision detection
    public void setRectangleBase()
    {
        BoundingBox modelBounds = modelData.calculateBoundingBox( new BoundingBox() );
        Vector3 max = modelBounds.max;
        Vector3 min = modelBounds.min;

        float[] vertices = 
            {max.x, max.z, min.x, max.z, min.x, min.z, max.x, min.z};
        boundingPolygon = new Polygon(vertices);
        boundingPolygon.setOrigin(0,0);
    }

    public void setEllipseBase()
    {
        BoundingBox modelBounds = modelData.calculateBoundingBox( new BoundingBox() );
        Vector3 max = modelBounds.max;
        Vector3 min = modelBounds.min;

        float a = 0.75f; // offset amount.
        float[] vertices = 
            {max.x,0, a*max.x,a*max.z, 0,max.z, a*min.x,a*max.z,
             min.x,0, a*min.x,a*min.z, 0,min.z, a*max.x,a*min.z };
        boundingPolygon = new Polygon(vertices);
        boundingPolygon.setOrigin(0,0);
    }

    public Polygon getBoundingPolygon()
    {          
        boundingPolygon.setPosition( position.x, position.z );
        boundingPolygon.setRotation( getTurnAngle() );
        return boundingPolygon;
    }

    public boolean overlaps(BaseActor3D other, boolean resolve)
    {
        Polygon poly1 = this.getBoundingPolygon();
        Polygon poly2 = other.getBoundingPolygon();

        if ( !poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()) )
            return false;

        MinimumTranslationVector mtv = new MinimumTranslationVector();
        boolean polyOverlap = Intersector.overlapConvexPolygons(poly1, poly2, mtv);
        if (polyOverlap && resolve)
        {
            this.addPosition( mtv.normal.x * mtv.depth, 0,  mtv.normal.y * mtv.depth );
        }
        float significant = 0.5f;
        return (polyOverlap && (mtv.depth > significant));
    }

    public BaseActor3D clone()
    {
        BaseActor3D newbie = new BaseActor3D();
        newbie.copy(this);
        return newbie;
    }
    
    public void copy(BaseActor3D orig)
    {
        this.modelData = new ModelInstance(orig.modelData);
        this.position.set( orig.position );
        this.rotation.set( orig.rotation );
        this.scale.set( orig.scale );
        if (orig.boundingPolygon != null)
            this.boundingPolygon = new Polygon(orig.boundingPolygon.getVertices());
    }
}
