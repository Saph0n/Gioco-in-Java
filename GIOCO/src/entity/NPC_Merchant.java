package entity;

import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Key;
import object.OBJ_PotionRed;
import object.OBJ_Shield_Wood;

public class NPC_Merchant extends Entity{
	
	public NPC_Merchant(GamePanel gp) {
		
		super(gp);
		getImage();
		setDialogue();
		setItems();
	}
	public void getImage()
	{
		
		up1 = setup("/npc/Merchant1",gp.tileSize,gp.tileSize);
		up2 = setup("/npc/Merchant2",gp.tileSize,gp.tileSize);
		up3 = setup("/npc/Merchant1",gp.tileSize,gp.tileSize);
		up4 = setup("/npc/Merchant2",gp.tileSize,gp.tileSize);
		
		down1 = setup("/npc/Merchant1",gp.tileSize,gp.tileSize);
		down2 = setup("/npc/Merchant2",gp.tileSize,gp.tileSize);
		down3 = setup("/npc/Merchant1",gp.tileSize,gp.tileSize);
		down4 = setup("/npc/Merchant2",gp.tileSize,gp.tileSize);
		
		left1 = setup("/npc/Merchant1",gp.tileSize,gp.tileSize);
		left2 = setup("/npc/Merchant2",gp.tileSize,gp.tileSize);
		left3 = setup("/npc/Merchant1",gp.tileSize,gp.tileSize);
		left4 = setup("/npc/Merchant2",gp.tileSize,gp.tileSize);
		
		right1 = setup("/npc/Merchant1",gp.tileSize,gp.tileSize);
		right2 = setup("/npc/Merchant2",gp.tileSize,gp.tileSize);
		right3 = setup("/npc/Merchant1",gp.tileSize,gp.tileSize);
		right4 = setup("/npc/Merchant2",gp.tileSize,gp.tileSize);
	}
	
	public void setDialogue() {
		
		dialogues[0]="Benvenuto nel mio negozio, cosa vuoi comprare?";
		dialogues[1]="ti va un po di corteccia?";
		
	}
	public void setItems()
	{
		inventory.add(new OBJ_Axe(gp));
		inventory.add(new OBJ_PotionRed(gp));
		inventory.add(new OBJ_Key(gp));
		inventory.add(new OBJ_Shield_Wood(gp));
	}
	public void speak()
	{
		super.speak();
		gp.gameState = gp.tradeState;
		gp.ui.npc = this;
	}
}
