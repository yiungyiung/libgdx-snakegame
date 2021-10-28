package com.testgame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.*;

public class TestGame extends ApplicationAdapter {
	SpriteBatch batch;
	float x[];
	float y[];
	public float runtime;
	public float changetime=0;
	ShapeRenderer shapeRenderer;
	private OrthographicCamera camera;
	float speed;
	Random rand;
	float cirx=100;
	float ciry=100;
	int dir;
	float snsize;
	float apsize;
	Boolean upturn;
	Boolean lefturn;
	Vector2 snbod[];//declaring array
	int ctr=0;
	int fcounter=0;
	Vector2 snakloc[];
	BitmapFont font ;
	Preferences prefs;
	int hiscore;
	Boolean gameover;
	
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera(50,50);
		font = new BitmapFont();
		prefs = Gdx.app.getPreferences("game preferences");
		speed=1f;
		dir=0;
		apsize=0.5f;
		snsize=1;
		upturn=false;
		lefturn=true;
		snbod = new Vector2[251];
		snakloc=new Vector2[251];
		x=new float[251];
		y=new float[251];
		gameover=false;
		
		snbod[0]=new Vector2(0f, 0f);
		snbod[1]=new Vector2(0f, -(snsize));
		for(int i=1;i<251;i++) {
			snbod[i]=new Vector2(100, 0);
		}
		Gdx.app.log("msg","works");
		hiscore=prefs.getInteger("highscore");
		
	}
	
	public void update(float deltaTime) {
	runtime+=deltaTime;
	if (fcounter>2)
	{
	switch(dir) {
	case 0:
	{
		snbod[0].y+=speed;

		
		break;}
	case 1:
	{snbod[0].y-=speed;
	
	
	break;}
	case 2:
	{snbod[0].x-=speed;
	
	break;}
	case 3:
	{snbod[0].x+=speed;
	
	break;}
	}
	
if (Gdx.input.isKeyPressed(Keys.UP)  && upturn ) {
	
	//y+=speed;
	dir=0;
	upturn=false;
	lefturn=true;
			
		}
 if (Gdx.input.isKeyPressed(Keys.DOWN )&& upturn) {
			//y-=speed;
	 dir=1;
	 upturn=false;
	 lefturn=true;
		}
if (Gdx.input.isKeyPressed(Keys.LEFT) && lefturn) {
	dir=2;
	lefturn=false;
	 upturn=true;
	//x-=speed;
}
 if (Gdx.input.isKeyPressed(Keys.RIGHT) && lefturn) {
	//x+=speed;
	dir=3;
	lefturn=false;
	upturn=true;
}
	
  
 if(ctr!=0)
	 {
	 //Gdx.app.log("msg","workstoo well");
	 for (int i=1;i<=ctr;i++)
		{
	 snbod[i]=new Vector2(x[i-1],y[i-1]);}

	
	
 fcounter=0;}
for (int i=0;i<=ctr;i++)
{
x[i]=snbod[i].x;
y[i]=snbod[i].y;}
	
	fcounter=0;}

	
if(snbod[0].x>25f)
{	

	snbod[0]=new Vector2(-25,snbod[0].y);

}
if (snbod[0].y>25)
{	

	snbod[0]=new Vector2(snbod[0].x,-25);

	
}
if(snbod[0].x<-25f)
{
	snbod[0]=new Vector2(25,snbod[0].y);

}
if (snbod[0].y<-25)
{

	snbod[0]=new Vector2(snbod[0].x,25);
}

if((snbod[0].x < (cirx + apsize) && ((snbod[0].x + snsize) > cirx) &&
        (snbod[0].y < (ciry + apsize) && (snbod[0].y + snsize) > ciry)))
{
	
	ctr++;
	nexaploc();
}
for (int i = 1; i <=ctr; i++)
{
    if ((snbod[0].x == snbod[i].x) && (snbod[0].y == snbod[i].y)) {
    	Gdx.app.log("msg","works like a charm");
    	snbod[0]=new Vector2(0,0);
    	ctr=0;
    	for(int j=1;j<251;j++) {
			snbod[j]=new Vector2(100, 0);
		}
    	
    	gameover=true;}
    if(ctr> hiscore) {
        prefs.putInteger("highscore", ctr);
        prefs.flush();
    }
    
}
	

	
 

/*if (Gdx.input.isKeyJustPressed(Keys.R))
{
	ctr++;
	//Gdx.app.log("msg","works3");
}*/




}

	@Override
	public void render () {
		ScreenUtils.clear(Color.GRAY);
		update(Gdx.graphics.getDeltaTime());
		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);
		/*batch.begin();
		batch.draw(img, x, y);
		batch.end();*/
		if (!gameover)
		{
	//update
	//update
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0,256,0,1);
        shapeRenderer.rect(snbod[0].x,snbod[0].y,snsize,snsize);
        shapeRenderer.end();
        for (int i=1;i<=ctr;i++)
    	{
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.OLIVE);
        shapeRenderer.rect(snbod[i].x,snbod[i].y,snsize,snsize);    
        shapeRenderer.end();
    	}
        
        batch.begin();
        font.setColor(Color.GOLDENROD);
        font.getData().setScale(1f);
        font.draw(batch, "Score " + ctr , 0, 590);
        font.draw(batch, "HIScore " + hiscore , 0, 570);
        batch.end();
        
        
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(256, 0, 0, 1);
        shapeRenderer.rect(cirx,ciry,apsize,apsize);
        shapeRenderer.end();
        
       
        if(runtime>(changetime+4))
        {
        	
        
        
       nexaploc();

	}
        fcounter+=1;
	}
		else {
			endgame();
			if (Gdx.input.isKeyJustPressed(Keys.ENTER))
			{
				gameover=false;
				dir=0;
				upturn=false;
				lefturn=true;
			}
			
		}
        
		//Gdx.app.log("msg"," "+snbod[1].x+ " "+snbod[1].y+ "|"+snbod[0].x+ " "+snbod[0].y);
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		
		
	}
	
	public void nexaploc()
	{	
		changetime=runtime;
		 cirx=(-25) + (int)(Math.random() * ((25 - (-25)) + 1));
	        ciry=(-25) + (int)(Math.random() * ((25 - (-25)) + 1));
	}
	
	public void endgame() {
			batch.begin();
	        font.setColor(Color.RED);
	        font.getData().setScale(2f);
	        font.draw(batch, "Yuo Ded Noob",150, 400);
	        font.draw(batch, "Press Enter To Restart",150, 350);
	        batch.end();
		
		
	}
}
	

