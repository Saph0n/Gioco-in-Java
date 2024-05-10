package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Wood extends Entity{

	public OBJ_Shield_Wood(GamePanel gp) {
		super(gp);
		type = type_shield;
		name = "Scudo di legno";
		down1 = setup("/objects/ScudoL", gp.tileSize, gp.tileSize);
		defenseValue = 1;
		description = "[" + name + "]\nUn vecchio scudo.\nAumenta il valore\ndi difesa di 1 punto.";
		price = 15;
	}

}
