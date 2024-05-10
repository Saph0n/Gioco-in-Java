package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.OBJ_Mana;
import object.OBJ_SlimeBall;

public class KingSlime extends Entity
{

	GamePanel gp;
	
	public KingSlime(GamePanel gp) {
		super(gp);
		
		this.gp=gp;
		
		type= type_monster;
		name = "Green Slime";
		speed = 2;
		maxLife = 50;
		life = maxLife;
		attack = 5;
		defense = 2;
		exp = 50;
		projectile = new OBJ_SlimeBall(gp);
		
		int size = gp.tileSize*5;
		solidArea.x = 48;
		solidArea.y = 48;
		solidArea.width = size - 48*2;
		solidArea.height = size - 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	public void getImage()
	{
		int i = 5;
		up1 = setup("/monster/ReSlimeD1.2",gp.tileSize*i,gp.tileSize*i); 
		up2 = setup("/monster/ReSlimeD2.2",gp.tileSize*i,gp.tileSize*i); 
		up3 = setup("/monster/ReSlimeD3.2",gp.tileSize*i,gp.tileSize*i);
		up4 = setup("/monster/ReSlimeS1.2",gp.tileSize*i,gp.tileSize*i); 
		up5 = setup("/monster/ReSlimeS2.2",gp.tileSize*i,gp.tileSize*i); 
		up6 = setup("/monster/ReSlimeS3.2",gp.tileSize*i,gp.tileSize*i);
		
		down1 = setup ("/monster/ReSlimeD1.2",gp.tileSize*i,gp.tileSize*i); 
		down2 = setup("/monster/ReSlimeD2.2",gp.tileSize*i,gp.tileSize*i); 
		down3 = setup("/monster/ReSlimeD3.2",gp.tileSize*i,gp.tileSize*i);
		down4 = setup ("/monster/ReSlimeS1.2",gp.tileSize*i,gp.tileSize*i); 
		down5 = setup("/monster/ReSlimeS2.2",gp.tileSize*i,gp.tileSize*i); 
		down6 = setup("/monster/ReSlimeS3.2",gp.tileSize*i,gp.tileSize*i);
		
		right1 = setup("/monster/ReSlimeD1.2",gp.tileSize*i,gp.tileSize*i); 
		right2 = setup("/monster/ReSlimeD2.2",gp.tileSize*i,gp.tileSize*i); 
		right3 = setup("/monster/ReSlimeD3.2",gp.tileSize*i,gp.tileSize*i);
		right4 = setup("/monster/ReSlimeS1.2",gp.tileSize*i,gp.tileSize*i); 
		right5 = setup("/monster/ReSlimeS2.2",gp.tileSize*i,gp.tileSize*i); 
		right6 = setup("/monster/RESlimeS3.2",gp.tileSize*i,gp.tileSize*i);
		
		left1 = setup("/monster/ReSlimeD1.2",gp.tileSize*i,gp.tileSize*i); 
		left2 = setup("/monster/ReSlimeD2.2",gp.tileSize*i,gp.tileSize*i); 
		left3 = setup("/monster/ReSlimeD3.2",gp.tileSize*i,gp.tileSize*i);
		left4 = setup("/monster/ReSlimeS1.2",gp.tileSize*i,gp.tileSize*i); 
		left5 = setup("/monster/ReSlimeS2.2",gp.tileSize*i,gp.tileSize*i); 
		left6 = setup("/monster/ReSlimeS3.2",gp.tileSize*i,gp.tileSize*i);
		
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
			
			checkShoot(200, 30);
		}
		else
		{
			checkStartChasing(gp.player, 5, 100);
			
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
