package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Mana extends Entity{
	
	GamePanel gp;
	public OBJ_Mana(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type=type_pickupOnly;
		name = "Mana";
		value=1;
		down1= setup("/objects/ManaP", gp.tileSize, gp.tileSize );
		image = setup("/objects/ManaP", gp.tileSize, gp.tileSize );
		image2 = setup ("/objects/ManaV", gp.tileSize, gp.tileSize);
		
	}
	
	public boolean use(Entity entity) {
		
		gp.playSE(2);
		gp.ui.addMessage("Mana +"+value);
		entity.mana+=value;
		return true;
		
		
	}

}
