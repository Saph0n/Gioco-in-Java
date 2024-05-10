package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity{
	
	boolean opened = false;
	GamePanel gp;
	Entity loot;
	
	
	public OBJ_Chest(GamePanel gp, Entity loot) {
		super(gp);
		name="Baule";
		this.gp=gp;
		this.loot=loot;
		
		image = setup("/objects/ChestChiusa1", gp.tileSize, gp.tileSize);
		image2 = setup("/objects/ChestApertaP1", gp.tileSize, gp.tileSize);
		image3 = setup("/objects/ChestApertaV1", gp.tileSize, gp.tileSize);
		down1 = image;
		collision = true;
		type= type_obstacle;
		solidArea.x=4;
		solidArea.y=16;
		solidArea.width=40;
		solidArea.height=32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	
	public void interact () 
 	{
		gp.gameState = gp.dialogueState;
		if(opened == false)
		{
			StringBuilder sb = new StringBuilder();
			sb.append("Hai aperto il baule e hai trovato ["+loot.name+"]!");
			
			if(gp.player.inventory.size() == gp.player.maxInventorySize)
			{
				sb.append("\n...ma non hai piu' spazio nel tuo inventario");
			}
			else
			{
				sb.append("\nHai messo ["+loot.name+"] nel tuo inventario!");
				gp.player.inventory.add(loot);
				down1 = image3;
				opened = true;
			}
			gp.ui.currentDialogue = sb.toString();
		}
		else
		{
			gp.ui.currentDialogue = "Il baule e' vuoto!";
		}
 	}
}
