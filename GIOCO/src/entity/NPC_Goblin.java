package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Goblin extends Entity {
	
	public NPC_Goblin(GamePanel gp) {
		
		super(gp);
		
		direction="down";
		speed=1;
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 30;
		
		getImage();
		setDialogue();
		
	}
	public void getImage()
	{
		
		up1 = setup("/npc/AvantiFermoG",gp.tileSize,gp.tileSize);
		up2 = setup("/npc/AvantiFermoG",gp.tileSize,gp.tileSize);
		up3 = setup("/npc/AvantiFermoG",gp.tileSize,gp.tileSize);
		up4 = setup("/npc/AvantiFermoG",gp.tileSize,gp.tileSize);
		
		down1 = setup("/npc/DietroFermoG",gp.tileSize,gp.tileSize);
		down2 = setup("/npc/DietroFermoG",gp.tileSize,gp.tileSize);
		down3 = setup("/npc/DietroFermoG",gp.tileSize,gp.tileSize);
		down4 = setup("/npc/DietroFermoG",gp.tileSize,gp.tileSize);
		
		left1 = setup("/npc/SinistraFermoG",gp.tileSize,gp.tileSize);
		left2 = setup("/npc/SinistraFermoG",gp.tileSize,gp.tileSize);
		left3 = setup("/npc/SinistraFermoG",gp.tileSize,gp.tileSize);
		left4 = setup("/npc/SinistraFermoG",gp.tileSize,gp.tileSize);
		
		right1 = setup("/npc/DestraFermoG",gp.tileSize,gp.tileSize);
		right2 = setup("/npc/DestraFermoG",gp.tileSize,gp.tileSize);
		right3 = setup("/npc/DestraFermoG",gp.tileSize,gp.tileSize);
		right4 = setup("/npc/DestraFermoG",gp.tileSize,gp.tileSize);
		
	}
	
	public void setDialogue() {
		
		dialogues[0]="ehi amico sono un Goblin";
		dialogues[1]="ti va un po di Goblin?";
		
	}
	
	public void setAction() {
		
		if(onPath == true)
		{
			int goalCol=19;
			int goalRow=9;
			
//			int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
//			int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
			
			searchPath(goalCol, goalRow);
		}
		else
		{
			actionLockCounter++;
			if(actionLockCounter==120) {
				
				Random random= new Random();	 
				int i=random.nextInt(100)+1; //prende un numero a caso da 1 a 100;
				
				if(i<=25) {
					
					direction="up";
					
				}
				if(i>25&&i<=50) {
					
					direction="down"; 
					
				}
				if(i>50&&i<=75) {
					
					
					direction="left";
				}
				if(i>75&&i<=100) {
					
					direction="right";
					
				}			
				actionLockCounter=0;
			}
		}
	}
	
	
	public void speak() {
		

		
		super.speak();
		
		onPath = true;
	}	
}
