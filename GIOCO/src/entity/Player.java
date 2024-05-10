package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.OBJ_Key;
import object.OBJ_Fireball;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

@SuppressWarnings("unused")
public class Player extends Entity{

	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	public boolean attackCanceled = false;
	
	public Player(GamePanel gp,KeyHandler keyH) {
		
		super(gp);
		this.keyH=keyH;
		
		screenX=gp.screenWidth/2 - (gp.tileSize/2);
		screenY=gp.screenHeight/2 - (gp.tileSize/2);
		
		//grandezza del rettangolo di collisione del pg
		solidArea= new Rectangle();
		solidArea.x=17;       //collisioni destra
		solidArea.y=15;			
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width=15;  
		solidArea.height=30; //collisioni piedi
		
		//ATTACK AREA
		//attackArea.width=36;
		//attackArea.height=36;

		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		setItems();
	}
	
	public void setDefaultValues() {
		
		worldX=gp.tileSize * 122;
		worldY=gp.tileSize * 48;
		
		defaultSpeed = 15;
	    speed=defaultSpeed; //VELOCITA -----------------------------------------------------
	    direction="down";
	   
	    //PLAYER STATUS
	    level = 1;
	    maxLife=6;
	    life = maxLife;
	    maxMana = 4;
	    mana = maxMana;
	    //ammo = 10;
	    strength = 1;
	    dexterity = 1; 
	    exp = 0;
	    nextLevelExp = 5; //exp livello successivo
	    coin = 500;
	    currentWeapon = new OBJ_Sword_Normal(gp);
	    currentShield = new OBJ_Shield_Wood(gp);
	    projectile=new OBJ_Fireball(gp	);
	    attack = getAttack(); 
	    defense = getDefense(); 
	}
	public void setDefaultPositions ()
	{
		worldX=gp.tileSize * 122;
		worldY=gp.tileSize * 48;
		direction="down";
	}
	public void restoreLifeAndMana()
	{
		life = maxLife;
		mana = maxMana;
		invincible = false;
	}
	public void setItems ()
	{
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);
	}
	public int getAttack()
	{
		attackArea = currentWeapon.attackArea;
		return attack = strength * currentWeapon.attackValue;
	}
	
	public int getDefense ()
	{
		return defense = dexterity * currentShield.defenseValue;
	}
	
	public void getPlayerImage()
	{
	
		up1 = setup("/player/AvantiFermo",gp.tileSize,gp.tileSize);
		up2 = setup("/player/Avanti1",gp.tileSize,gp.tileSize);
		up3 = setup("/player/AvantiFermo",gp.tileSize,gp.tileSize);
		up4 = setup("/player/Avanti2",gp.tileSize,gp.tileSize);
		
		down1 = setup("/player/DietroFermo",gp.tileSize,gp.tileSize);
		down2 = setup("/player/Dietro1",gp.tileSize,gp.tileSize);
		down3 = setup("/player/DietroFermo",gp.tileSize,gp.tileSize);
		down4 = setup("/player/Dietro2",gp.tileSize,gp.tileSize);
		
		left1 = setup("/player/SinistraFermo",gp.tileSize,gp.tileSize);
		left2 = setup("/player/Sinistra1",gp.tileSize,gp.tileSize);
		left3 = setup("/player/SinistraFermo",gp.tileSize,gp.tileSize);
		left4 = setup("/player/Sinistra2",gp.tileSize,gp.tileSize);
		
		right1 = setup("/player/DestraFermo",gp.tileSize,gp.tileSize);
		right2 = setup("/player/Destra1",gp.tileSize,gp.tileSize);
		right3 = setup("/player/DestraFermo",gp.tileSize,gp.tileSize);
		right4 = setup("/player/Destra2",gp.tileSize,gp.tileSize);
		
		
	}
	
	public void getPlayerAttackImage() {
		
		if(currentWeapon.type == type_sword)
		{
			attackUp1=setup("/player/AttaccoW1",gp.tileSize,gp.tileSize);
			attackUp2=setup("/player/AttaccoW1",gp.tileSize,gp.tileSize);

			attackDown1=setup("/player/AttaccoS",gp.tileSize,gp.tileSize);
			attackDown2=setup("/player/AttaccoS",gp.tileSize,gp.tileSize);

			attackLeft1=setup("/player/AttaccoA1",gp.tileSize,gp.tileSize);
			attackLeft2=setup("/player/AttaccoA1",gp.tileSize,gp.tileSize);
			
			attackRight1=setup("/player/AttaccoD1",gp.tileSize,gp.tileSize);
			attackRight2=setup("/player/AttaccoD1",gp.tileSize,gp.tileSize);

		}
		if(currentWeapon.type == type_axe)
		{
			attackUp1=setup("/player/Capybara",gp.tileSize,gp.tileSize*2);
			attackUp2=setup("/player/Capybara",gp.tileSize,gp.tileSize*2);

			attackDown1=setup("/player/Capybara",gp.tileSize,gp.tileSize*2);
			attackDown2=setup("/player/Capybara",gp.tileSize,gp.tileSize*2);

			attackLeft1=setup("/player/Capybara",gp.tileSize*2,gp.tileSize);
			attackLeft2=setup("/player/Capybara",gp.tileSize*2,gp.tileSize);
			
			attackRight1=setup("/player/Capybara",gp.tileSize*2,gp.tileSize);
			attackRight2=setup("/player/Capybara",gp.tileSize*2,gp.tileSize);

		}
		


	}
	

	
	public void update() {
		
		if(attacking==true) {
			attacking();
		}
		else if(keyH.upPressed==true||keyH.downPressed==true||
				keyH.leftPressed==true||keyH.rightPressed==true||keyH.enterPressed==true) {
			   
			if(keyH.upPressed==true) {
				direction="up";
			}else if(keyH.downPressed==true) {
				direction="down";
			}else if(keyH.leftPressed==true) {
				direction="left";
			}else if(keyH.rightPressed==true) {
				direction="right";
			}
			//controllo collisioni tile
			collisionOn=false;
			gp.cChecker.checkTile(this);
			
			//controllo collisione oggetto
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			//controllo npc collisioni
			int npcIndex=gp.cChecker.checkEntity(this,gp.npc);	
			interactNPC(npcIndex);
			
			//controllo mostri collisioni
			int monsterIndex=gp.cChecker.checkEntity(this,gp.monster);	
			contactMonster(monsterIndex);
			
			//controllo collisioni tile interattivi
			int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
			
			//controllo evento
			gp.eHandler.checkEvent();
			
			
			
			//collisione=false player si muove
			if(collisionOn==false&&keyH.enterPressed==false) {
				
				switch(direction) {
				
				case"up":
					worldY=worldY-speed;
					break;
				case"down":
					worldY=worldY+speed;
					break;
				case"left":
					worldX=worldX-speed;
					break;
				case"right":
					worldX=worldX+speed;
					break;
				}
			}	
			
			
			if(keyH.enterPressed == true  && attackCanceled == false )
			{
				//gp.playSE();
				attacking = true;
				spriteCounter = 0;
			}
			attackCanceled = false;
			gp.keyH.enterPressed =  false;
			
			spriteCounter++;
			if(spriteCounter>10) {
				
				if(spriteNum==1) {
					spriteNum=2;
				}else if(spriteNum==2) {
					spriteNum=3;
				}else if(spriteNum==3) {
					spriteNum=4;
				}else if(spriteNum==4) {
					spriteNum=1;
				}
				
				spriteCounter=0;
			}
		}
		
		if(gp.keyH.shotKeyPressed==true&&projectile.alive==false&&
				shotAvailableCounter==30 && projectile.haveResource(this) == true) {
			
			projectile.set(worldX,worldY,direction,true,this);
			
			//SUBTRACT THE COST(MANA, AMMO, ETC.)
			projectile.subtractResource(this);
			
			//CHECK VACANCY
			for(int i = 0;i<gp.projectile[1].length; i++)
			{
				if(gp.projectile[gp.currentMap][i] == null)
				{
					gp.projectile[gp.currentMap][i] = projectile;
					break;
				}
			}
			
			shotAvailableCounter=0;
			
			gp.playSE(3);
			
		}
		
		if(invincible==true) {
			
			invincibleCounter++;
			
			if(invincibleCounter>60) {
				
				invincible=false;
				
				invincibleCounter=0;
				
			}
			
		}
		
		if(shotAvailableCounter<30) {
			
			shotAvailableCounter++;
		}
		if(life>maxLife) {
			life=maxLife;
		}	
		if(mana>maxMana) {
			mana=maxMana;
		}
		
		if(life <= 0)
		{
			gp.gameState = gp.gameOverState;
			gp.stopMusic();
			gp.playSE(4);
		}
	}
	
	public void attacking() {
		
		spriteCounter++;
		
		if(spriteCounter<=5) {
			spriteNum=1;
		}
		if(spriteCounter>5&&spriteCounter<=25)//SE RESTRINGI L'INTERVALLO DIVENTA PIU DIFFICILIE PARARE I COLPI
		{
			spriteNum=2;
			
			int currentWorldX=worldX;
			int currentWorldY=worldY;
			int solidAreaWidth=solidArea.width;
			int solidAreaHeight=solidArea.height;
			
			switch(direction) {
			case"up":worldY-=attackArea.height;break;
			case"down":worldY+=attackArea.height;break;
			case"left":worldX-=attackArea.width;break;
			case"right":worldX+=attackArea.width;break;
		}
			solidArea.width=attackArea.width;
			solidArea.height=attackArea.height;
			
			int monsterIndex=gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex,attack, currentWeapon.knockBackPower);
			
			int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
			damageInteractiveTile(iTileIndex);
			
			int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
			damageProjectile(projectileIndex);
			
			worldX=currentWorldX;
			worldY=currentWorldY;
			solidArea.width=solidAreaWidth;
			solidArea.height=solidAreaHeight;
		}
		if(spriteCounter>25) {
			
			spriteNum=1;
			spriteCounter=0;
			attacking=false;
			
		}
		
	}
	
	public void pickUpObject(int i)
	{
		if(i != 999)
		{	
			
			
			//Oggeti solo raccoglibili
			if(gp.obj[gp.currentMap][i].type==type_pickupOnly) {
				
				gp.obj[gp.currentMap][i].use(this);
				gp.obj[gp.currentMap][i]=null;
				
				
			}
			//OSTACOLO
			else if(gp.obj[gp.currentMap][i].type == type_obstacle)
			{
				if(keyH.enterPressed == true)
				{
					attackCanceled = true;
					gp.obj[gp.currentMap][i].interact();
				}
			}
			
			//oggetti inventario
			else {
				
				String text;
				if(inventory.size() != maxInventorySize)
				{
					inventory.add(gp.obj[gp.currentMap][i]);
					gp.playSE(1);
					text = "Hai trovato ["+gp.obj[gp.currentMap][i].name+"]!";
				}
				else
					text = "Inventario pieno!";
				gp.ui.addMessage(text);
				gp.obj[gp.currentMap][i] = null;
				
			}
			
			
		}
		
	}
	
	public void interactNPC	(int i) {
		
		if( gp.keyH.enterPressed==true) {
			
			if(i != 999)
			{
				attackCanceled = true;
				System.out.println("Stai venendo colpito da un npc!");
				gp.gameState=gp.dialogueState;
				gp.npc[gp.currentMap][i].speak();
			}
		}
	}
	
	public void contactMonster(int i) {
		
		if(i!=999) {
			if(invincible==false&&gp.monster[gp.currentMap][i].dying==false) {
				
				int damage = gp.monster[gp.currentMap][i].attack - defense;
				if(damage<0)
				{
					damage = 0;
				}
				life -= damage;
				invincible=true;
				
			}
			
		}
		
		
	}
	
	public void damageMonster(int i,int attack, int knockBackPower) {
		if(i!=999) 
		{
			if(gp.monster[gp.currentMap][i].invincible==false) {
				
				if(knockBackPower > 0)
				{
					knockBack(gp.monster[gp.currentMap][i], knockBackPower);
				}
				
				int damage = attack - gp.monster[gp.currentMap][i].defense;
				if(damage<0)
				{
					damage = 0;
				}
				gp.monster[gp.currentMap][i].life-= damage;
				gp.ui.addMessage(damage + " danni!");
				gp.monster[gp.currentMap][i].invincible=true;
				gp.monster[gp.currentMap][i].damageReaction();
				
				if(gp.monster[gp.currentMap][i].life<=0) {
					gp.monster[gp.currentMap][i].dying = true;
					gp.ui.addMessage(gp.monster[gp.currentMap][i].name+" ucciso!");
					gp.ui.addMessage(gp.monster[gp.currentMap][i].exp+" exp!");
					exp += gp.monster[gp.currentMap][i].exp;
					checkLevelUp();
					
				}
			}
		}
	}
	public void damageInteractiveTile(int i)
	{
		if(i != 999 && gp.iTile[gp.currentMap][i].destructible == true && 
				gp.iTile[gp.currentMap][i].isCorrectItem(this)==true && gp.iTile[gp.currentMap][i].invincible == false)
		{
			//gp.iTile[i].playSE();
			gp.iTile[gp.currentMap][i].life--;
			gp.iTile[gp.currentMap][i].invincible = true;
			
			//Generazione particelle
			generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);
			
			if(gp.iTile[gp.currentMap][i].life == 0)
			{
				gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
			}
			
		}
	}
	public void checkLevelUp()
	{
		if(exp>=nextLevelExp)
		{
			level++;
			nextLevelExp = nextLevelExp * 3;
			maxLife += 2;
			strength++;
			dexterity++;
			attack = getAttack();
			defense = getDefense();
			
			//gp.playSE(numero);
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "Livello "+ level + "!";
		}
	}
	public void selectItem()
	{
		int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
		
		if(itemIndex < inventory.size())
		{
			Entity selectedItem = inventory.get(itemIndex);
			
			if(selectedItem.type == type_sword || selectedItem.type == type_axe)
			{
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();
			}
			if(selectedItem.type == type_shield)
			{
				currentShield = selectedItem;
				defense = getDefense();
			}
			if(selectedItem.type == type_consumable)
			{
				if(selectedItem.use(this) == true);
				{
					inventory.remove(itemIndex);	
				}
			}
		}
	}
	public void draw(Graphics2D g2) {
		
		
		//g2.setColor(Color.white);
		
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image=null;
		int tempScreenX=screenX;
		int tempScreenY=screenY;
		
		switch(direction) {
		
		case"up":
			if(attacking==false) {
				if(spriteNum==1) {image=up1;}
				if(spriteNum==2) {image=up2;}
				if(spriteNum==3) {image=up3;}
				if(spriteNum==4) {image=up4;}
			}
			if(attacking==true) {
				//tempScreenY=screenY-gp.tileSize;
				if(spriteNum==1) {image=attackUp1;}
				if(spriteNum==2) {image=attackUp2;}
			}
			break;	
	    case"down":
	    	if(attacking==false) {
	    		if(spriteNum==1) {image=down1;}
				if(spriteNum==2) {image=down2;}
				if(spriteNum==3) {image=down3;}
				if(spriteNum==4) {image=down4;}
	    	}
	    	if(attacking==true) {
	    		if(spriteNum==1) {image=attackDown1;}
				if(spriteNum==2) {image=attackDown2;}
	    	}
		
			break;			
		case"left":
			if(attacking==false) {		
				if(spriteNum==1) {image=left1;}
				if(spriteNum==2) {image=left2;}
				if(spriteNum==3) {image=left3;}
				if(spriteNum==4) {image=left4;}
			}
			
			if(attacking==true) {
				//tempScreenX=screenX-gp.tileSize;
				if(spriteNum==1) {image=attackLeft1;}
				if(spriteNum==2) {image=attackLeft2;}

			}
			break;			
		case"right":
			
			if(attacking==false) {
				if(spriteNum==1) {image=right1;}
				if(spriteNum==2) {image=right2;}
				if(spriteNum==3) {image=right3;}
				if(spriteNum==4) {image=right4;}
			}
		
			if(attacking==true) {
				if(spriteNum==1) {image=attackRight1;}
				if(spriteNum==2) {image=attackRight2;}

			}
			break;
		}
		//Fa lampaeggiare il player quando subisce damage
		if(invincible==true) {
			
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f));
			
		}
		g2.drawImage(image, tempScreenX, tempScreenY, null);
		//reset Alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));

		
		//Debug del contatore del invicibilità del player dopo aver subito danno
//		g2.setFont(new Font("Arial",Font.PLAIN,26));
//		g2.setColor(Color.white);
//		g2.drawString("Invincibile:"+invincibleCounter,10,400);
		
	}
	public void damageProjectile(int i)
	{
		if(i != 999)
		{
			Entity projectile = gp.projectile[gp.currentMap][i];
			projectile.alive = false;
			generateParticle(projectile, projectile);
		}
	}
	public void knockBack(Entity entity, int knockBackPower)
	{
		entity.direction = direction;
		entity.speed += knockBackPower;
		entity.knockBack = true;
		
	}
}
	








