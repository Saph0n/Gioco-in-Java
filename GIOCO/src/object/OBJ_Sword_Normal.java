package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity{

	public OBJ_Sword_Normal(GamePanel gp) {
		super(gp);
		type = type_sword;
		name = "Spada normale";
		down1 = setup("/objects/Spada", gp.tileSize, gp.tileSize);
		attackValue = 2; //IL DANNO DIPENDERA' ANCHE DALLA FORZA DEL PLAYER
		description = "[" + name + "]\nUna spada normale. \nAumenta il valore\ndell'attacco di 2 punti.";
		attackArea.width = 18;
		attackArea.height = 36;
		price = 10;
		knockBackPower = 10;
	}
	
	
	

}
