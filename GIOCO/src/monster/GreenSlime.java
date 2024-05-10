package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.OBJ_Mana;
import object.OBJ_SlimeBall;

public class GreenSlime extends Entity
{

	GamePanel gp;
	
	public GreenSlime(GamePanel gp) {
		super(gp);
		
		this.gp=gp;
		
		type= type_monster;
		name = "Green Slime";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 4;
		life = maxLife;
		attack = 2;
		defense = 1;
		exp = 2;
		projectile = new OBJ_SlimeBall(gp);
		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	public void getImage()
	{
		up1 = setup("/monster/SlimeD1",gp.tileSize,gp.tileSize); up2 = setup("/monster/SlimeD2",gp.tileSize,gp.tileSize); up3 = setup("/monster/SlimeD3",gp.tileSize,gp.tileSize);
		up4 = setup("/monster/SlimeS1",gp.tileSize,gp.tileSize); up5 = setup("/monster/SlimeS2",gp.tileSize,gp.tileSize); up6 = setup("/monster/SlimeS3",gp.tileSize,gp.tileSize);
		
		down1 = setup ("/monster/SlimeD1",gp.tileSize,gp.tileSize); down2 = setup("/monster/SlimeD2",gp.tileSize,gp.tileSize); down3 = setup("/monster/SlimeD3",gp.tileSize,gp.tileSize);
		down4 = setup ("/monster/SlimeS1",gp.tileSize,gp.tileSize); down5 = setup("/monster/SlimeS2",gp.tileSize,gp.tileSize); down6 = setup("/monster/SlimeS3",gp.tileSize,gp.tileSize);
		
		right1 = setup("/monster/SlimeD1",gp.tileSize,gp.tileSize); right2 = setup("/monster/SlimeD2",gp.tileSize,gp.tileSize); right3 = setup("/monster/SlimeD3",gp.tileSize,gp.tileSize);
		right4 = setup("/monster/SlimeS1",gp.tileSize,gp.tileSize); right5 = setup("/monster/SlimeS2",gp.tileSize,gp.tileSize); right6 = setup("/monster/SlimeS3",gp.tileSize,gp.tileSize);
		
		left1 = setup("/monster/SlimeD1",gp.tileSize,gp.tileSize); left2 = setup("/monster/SlimeD2",gp.tileSize,gp.tileSize); left3 = setup("/monster/SlimeD3",gp.tileSize,gp.tileSize);
		left4 = setup("/monster/SlimeS1",gp.tileSize,gp.tileSize); left5 = setup("/monster/SlimeS2",gp.tileSize,gp.tileSize); left6 = setup("/monster/SlimeS3",gp.tileSize,gp.tileSize);
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
