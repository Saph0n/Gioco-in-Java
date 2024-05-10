package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin extends Entity{
	
	GamePanel gp;
	
	public OBJ_Coin(GamePanel gp) {
		
		super(gp);
		this.gp=gp;
		
		type=type_pickupOnly;
		name="Fiorino";
		value=1;
		down1=setup("/objects/Moneta",gp.tileSize,gp.tileSize);
		
		
	}
	
	public boolean use(Entity entity)
	{
		gp.playSE(1);
		gp.ui.addMessage("Fiorino +"+ value);
		gp.player.coin+=value;
		return true;
	}
	
	
}
