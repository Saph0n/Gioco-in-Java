package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Capibara extends Entity {
	
	public NPC_Capibara(GamePanel gp) {
		
		super(gp);
		
		direction="down";
		speed=1;
		
		getImage();
		setDialogue();
		
	}
	public void getImage()
	{
		
		up1 = setup("/npc/DietroFermoS",gp.tileSize,gp.tileSize);
		up2 = setup("/npc/DietroFermoS",gp.tileSize,gp.tileSize);
		up3 = setup("/npc/DietroFermoS",gp.tileSize,gp.tileSize);
		up4 = setup("/npc/DietroFermoS",gp.tileSize,gp.tileSize);
		
		down1 = setup("/npc/AvantiFermoS",gp.tileSize,gp.tileSize);
		down2 = setup("/npc/AvantiFermoS1",gp.tileSize,gp.tileSize);
		down3 = setup("/npc/AvantiFermoS",gp.tileSize,gp.tileSize);
		down4 = setup("/npc/AvantiFermoS1",gp.tileSize,gp.tileSize);
		
		left1 = setup("/npc/SinistraFermoS",gp.tileSize,gp.tileSize);
		left2 = setup("/npc/SinistraFermoS1",gp.tileSize,gp.tileSize);
		left3 = setup("/npc/SinistraFermoS",gp.tileSize,gp.tileSize);
		left4 = setup("/npc/SinistraFermoS1",gp.tileSize,gp.tileSize);
		
		right1 = setup("/npc/DestraFermoS",gp.tileSize,gp.tileSize);
		right2 = setup("/npc/DestraFermoS1",gp.tileSize,gp.tileSize);
		right3 = setup("/npc/DestraFermoS",gp.tileSize,gp.tileSize);
		right4 = setup("/npc/DestraFermoS1",gp.tileSize,gp.tileSize);
		
	}
	
	public void setDialogue() {
		
		dialogues[0]="ehi amico sono un Oldman";
		dialogues[1]="ti va un po di Oldman?";
		
	}
	
	public void setAction() {
		
		
		//if(onPath == true)
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
	
	
	public void speak() {
		
		super.speak();

	}	
}
