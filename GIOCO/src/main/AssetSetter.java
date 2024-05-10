package main;

import entity.NPC_Capibara;
import entity.NPC_Goblin;
import entity.NPC_Merchant;
import monster.GreenSlime;
import monster.KingSlime;
import monster.Orc;
import object.OBJ_Axe;
import object.OBJ_Chest;
import object.OBJ_Coin;
import object.OBJ_Door;
import object.OBJ_Heart;
import object.OBJ_IronShield;
import object.OBJ_Key;
import object.OBJ_Mana;
import object.OBJ_PotionRed;
import object.OBJ_UML;
import tile_interactive.IT_DryTree;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp)
	{
		this.gp=gp;
	}
	
	public void setObject()
	{
		int mapNum = 0;
		int i=0;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Axe(gp));
		gp.obj[mapNum][i].worldX = 138 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 53 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Key(gp);
		gp.obj[mapNum][i].worldX = 121 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 49 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Key(gp);
		gp.obj[mapNum][i].worldX = 8 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 14 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Axe(gp);
		gp.obj[mapNum][i].worldX = 15 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 22 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_IronShield(gp);
		gp.obj[mapNum][i].worldX = 15 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 23 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_PotionRed(gp);
		gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 23 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp);
		gp.obj[mapNum][i].worldX = 9 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 9 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp);
		gp.obj[mapNum][i].worldX = 9 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp);
		gp.obj[mapNum][i].worldX = 9 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Heart(gp);
		gp.obj[mapNum][i].worldX = 15 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Mana(gp);
		gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
		i++;
	}
	
	public void setNPC() {
		int mapNum = 0;
		int i=0;
		gp.npc[mapNum][i]=new NPC_Capibara(gp);
		gp.npc[mapNum][i].worldX=6*gp.tileSize;
		gp.npc[mapNum][i].worldY=8*gp.tileSize;
		i++;
		gp.npc[mapNum][i]=new NPC_Goblin(gp);
		gp.npc[mapNum][i].worldX=10*gp.tileSize;
		gp.npc[mapNum][i].worldY=8*gp.tileSize;
		i++;
		gp.npc[mapNum][i]=new NPC_Merchant(gp);
		gp.npc[mapNum][i].worldX=15*gp.tileSize;
		gp.npc[mapNum][i].worldY=8*gp.tileSize;
		i++;
	}
	
	public void setMonster()
	{
		int mapNum = 0;
		int i=0;
		gp.monster[mapNum][i] = new GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*144;
		gp.monster[mapNum][i].worldY = gp.tileSize*80;
		i++;
		gp.monster[mapNum][i] = new GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*143;
		gp.monster[mapNum][i].worldY = gp.tileSize*80;
		i++;
		gp.monster[mapNum][i] = new GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*145;
		gp.monster[mapNum][i].worldY = gp.tileSize*80;
		i++;
		gp.monster[mapNum][i] = new GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*146;
		gp.monster[mapNum][i].worldY = gp.tileSize*80;
		i++;
		gp.monster[mapNum][i] = new GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*147;
		gp.monster[mapNum][i].worldY = gp.tileSize*81;
		i++;
		gp.monster[mapNum][i] = new GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*147;
		gp.monster[mapNum][i].worldY = gp.tileSize*81;
		i++;gp.monster[mapNum][i] = new GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*148;
		gp.monster[mapNum][i].worldY = gp.tileSize*81;
		i++;
		gp.monster[mapNum][i] = new GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*146;
		gp.monster[mapNum][i].worldY = gp.tileSize*81;
		i++;gp.monster[mapNum][i] = new GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*145;
		gp.monster[mapNum][i].worldY = gp.tileSize*81;
		i++;
		gp.monster[mapNum][i] = new GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*144;
		gp.monster[mapNum][i].worldY = gp.tileSize*81;
		i++;gp.monster[mapNum][i] = new GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*143;
		gp.monster[mapNum][i].worldY = gp.tileSize*81;
		i++;
		gp.monster[mapNum][i] = new Orc(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*105;
		gp.monster[mapNum][i].worldY = gp.tileSize*91;
		i++;
		
		gp.monster[mapNum][i] = new Orc(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*124;
		gp.monster[mapNum][i].worldY = gp.tileSize*90;
		i++;
		gp.monster[mapNum][i] = new Orc(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*130;
		gp.monster[mapNum][i].worldY = gp.tileSize*127;
		i++;
		
		mapNum = 1; //SE VOGLIO PIAZZARE MOSTRI NELL'ALTRA MAPPA
		i=0;
		gp.monster[mapNum][i] = new KingSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*117;
		gp.monster[mapNum][i].worldY = gp.tileSize*171;
		i++;
	}
	
	public void setInteractiveTile ()
	{
		int mapNum = 0;
		int i = 0;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 20, 12);i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,20, 13); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 20, 14);i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 20, 15);i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 20, 16);i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 20, 17);i++;
	}
}
