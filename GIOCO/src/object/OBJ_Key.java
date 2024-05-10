package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity
{
	GamePanel gp;
	public OBJ_Key(GamePanel gp)
	{
		super(gp);
		this.gp = gp;
		name ="Chiave";
		down1 = setup("/objects/Key",gp.tileSize,gp.tileSize);
		type = type_consumable;
		description = "[" + name + "]\nUna chiave.Usata per \nforzieri e porte.";
		collision = true;
		price = 5;
	}
	
	public boolean use (Entity entity)
	{
		gp.gameState = gp.dialogueState;
		int objIndex = getDetected(entity, gp.obj, "Porta");
		
		if(objIndex != 999)
		{
			gp.ui.currentDialogue = "Hai usato la "+name+"!";
			gp.obj[gp.currentMap][objIndex] = null;
			return true;
		}
		else
		{
			gp.ui.currentDialogue = "Nessun effetto";
			return false;
		}
		
	}
	
}
