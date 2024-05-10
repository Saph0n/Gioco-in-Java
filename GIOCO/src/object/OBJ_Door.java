package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity{

	GamePanel gp;
	public OBJ_Door(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name ="Porta";
		down1 = setup ("/objects/PortaChiusa", gp.tileSize, gp.tileSize);
		type = type_obstacle;
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	public void interact ()
	{
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "Hai bisogno di una chiave!";
		
	}
}
	
