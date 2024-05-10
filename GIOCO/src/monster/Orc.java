package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.OBJ_Mana;
import object.OBJ_SlimeBall;

public class Orc extends Entity{
	GamePanel gp;
	
	public Orc(GamePanel gp) {
		super(gp);
		
		this.gp=gp;
		
		type= type_monster;
		name = "Orc";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 10;
		life = maxLife;
		attack = 5;
		defense = 2;
		exp = 8;
		
		solidArea.x = 4;
		solidArea.y = 4;
		solidArea.width = 40;
		solidArea.height = 44;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = 48;
		attackArea.height = 48;
		
		getImage();
		getAttackImage();
	}
	public void getImage()
	{
		int i=2;

		up1 = setup("/monster/orc_up_1",gp.tileSize*i,gp.tileSize*i); 
		up2 = setup("/monster/orc_up_2",gp.tileSize*i,gp.tileSize*i);
		up3 = setup("/monster/orc_up_1",gp.tileSize*i,gp.tileSize*i);
		up4 = setup("/monster/orc_up_2",gp.tileSize*i,gp.tileSize*i);
		
		down1 = setup ("/monster/orc_down_1",gp.tileSize*i,gp.tileSize*i); 
		down2 = setup("/monster/orc_down_2",gp.tileSize*i,gp.tileSize*i);
		down3 =	setup ("/monster/orc_down_1",gp.tileSize*i,gp.tileSize*i); 
		down4 = setup("/monster/orc_down_2",gp.tileSize*i,gp.tileSize*i);
		
		right1 = setup("/monster/orc_right_1",gp.tileSize*i,gp.tileSize*i); 
		right2 = setup("/monster/orc_right_2",gp.tileSize*i,gp.tileSize*i);
		right3 = setup("/monster/orc_right_1",gp.tileSize*i,gp.tileSize*i); 
		right4 = setup("/monster/orc_right_2",gp.tileSize*i,gp.tileSize*i);
		
		left1 = setup("/monster/orc_left_1",gp.tileSize*i,gp.tileSize*i); 
		left2 = setup("/monster/orc_left_2",gp.tileSize*i,gp.tileSize*i);
		left3 =	setup("/monster/orc_left_1",gp.tileSize*i,gp.tileSize*i); 
		left4 = setup("/monster/orc_left_2",gp.tileSize*i,gp.tileSize*i);
	}
	
	public void getAttackImage()
	{	
		int i=2;
		attackUp1=setup("/monster/orc_attack_up_1",gp.tileSize*i,gp.tileSize*i);
		attackUp2=setup("/monster/orc_attack_up_2",gp.tileSize*i,gp.tileSize*i);

		attackDown1=setup("/monster/orc_attack_down_1",gp.tileSize*i,gp.tileSize*i);
		attackDown2=setup("/monster/orc_attack_down_1",gp.tileSize*i,gp.tileSize*i);

		attackLeft1=setup("/monster/orc_attack_left_1",gp.tileSize*i,gp.tileSize*i);
		attackLeft2=setup("/monster/orc_attack_left_2",gp.tileSize*i,gp.tileSize*i);
		
		attackRight1=setup("/monster/orc_attack_right_1",gp.tileSize*i,gp.tileSize*i);
		attackRight2=setup("/monster/orc_attack_right_1",gp.tileSize*i,gp.tileSize*i);
	}
	public void setAction()
	{
		int xDistance = Math.abs(worldX - gp.player.worldX);
		int yDistance = Math.abs(worldY - gp.player.worldY);
		int tileDistance = (xDistance + yDistance)/gp.tileSize;
		
		if(onPath == true)
		{
			if(tileDistance > 20)
			{
				onPath = false;
			}
		
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
		}
		else
		{
			checkStartChasing(gp.player, 9, 100);
			
			getRandomDirection();
		}
	}
	
	public void damageReaction()
	{
		actionLockCounter = 0;
		direction = gp.player.direction;
	}
	public void checkDrop() {
		
		int i=new Random().nextInt(100)+1;
		if(i<50) {
			
			dropItem(new OBJ_Coin(gp));
			
		}
		if(i>=50&&i<75) {
			
			dropItem(new OBJ_Heart(gp));
		}
		
		if(i>=75&&i<100) {
			
			dropItem(new OBJ_Mana(gp));
		}
		
	}
	
}
