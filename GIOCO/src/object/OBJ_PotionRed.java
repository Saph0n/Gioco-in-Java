package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_PotionRed extends Entity{
	
	GamePanel gp;
	
	public OBJ_PotionRed(GamePanel gp) {
		super(gp);
		
		this.gp=gp;
		type = type_consumable;
		value=5;
		name = "Pozione cura";
		down1 = setup("/objects/PozioneCura", gp.tileSize, gp.tileSize);
		description = "["+name+"]\nCura la tua vita di "+value+" punti.";
		price = 20;
	}
	public boolean use(Entity entity)
	{
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "Hai bevuto la pozione, ti sei curato!";
		entity.life += value;
		return true;
		//gp.playSE(); //mette il suono glu glu glu pozione
	}
}
