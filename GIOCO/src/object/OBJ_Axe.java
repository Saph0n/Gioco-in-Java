package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity{

	public OBJ_Axe(GamePanel gp) {
		super(gp);
		type = type_axe;
		name = "Ascia";
		down1 = setup("/objects/Ascia1", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\nUtile per tagliare alberi \nsecchi ma utilizzabile\n anche contro i nemici.";
		attackValue = 2;
		attackArea.width = 30;
		attackArea.height = 30;
		price = 30;
		knockBackPower = 10;
	}

}
