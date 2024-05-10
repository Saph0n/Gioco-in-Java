package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_IronShield extends Entity{
	
	public OBJ_IronShield(GamePanel gp) {
		super(gp);
		type = type_shield;
		name = "Scudo di Ferro";
		down1 = setup("/objects/ScudoF", gp.tileSize, gp.tileSize);
		defenseValue = 2;
		description = "[" + name + "]\nUno scudo migliorato.\nAumenta il valore\ndi difesa di 2 punti.";
		price = 40;
	}

}
