package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_UML extends Entity{
	GamePanel gp;
	
	public OBJ_UML(GamePanel gp) {
		super(gp);
		
		this.gp=gp;
		name="Palla di fuoco BUM";
		down1 = setup("/objects/umlbello",gp.tileSize,gp.tileSize);
		alive=false;
		knockBackPower = 0;
		getImage();
		collision=true;
	}
	
	public void getImage(){
		
		int i = 40;
		up1=setup("/objects/umlbello",gp.tileSize*i,gp.tileSize*i);
		up2=setup("/objects/umlbello",gp.tileSize*i,gp.tileSize*i);

		down1=setup("/objects/umlbello",gp.tileSize*i,gp.tileSize*i);
		down2=setup("/objects/umlbello",gp.tileSize*i,gp.tileSize*i);

		left1=setup("/objects/umlbello",gp.tileSize*i,gp.tileSize*i);
		left2=setup("/objects/umlbello",gp.tileSize*i,gp.tileSize*i);

		right1=setup("/objects/umlbello",gp.tileSize*i,gp.tileSize*i);
		right2=setup("/objects/umlbello",gp.tileSize*i,gp.tileSize*i);

	}
}
