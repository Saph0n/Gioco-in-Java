package main;

import entity.Entity;

public class EventHandler 
{
	GamePanel gp;
	EventRect eventRect[][][];
	
	int previousEventX,previousEventY;
	boolean canTouchEvent=true;
	int tempMap, tempCol, tempRow;
	
	public EventHandler(GamePanel gp)
	{
		this.gp=gp;
		
		eventRect=new EventRect[gp.maxMap ][gp.maxWorldCol][gp.maxWorldRow];
		
		int map = 0;
		int col=0;
		int row=0;
		
		while(map < gp.maxMap && col<gp.maxWorldCol&&row<gp.maxWorldRow) {
			
			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = 23;
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].width=2;
			eventRect[map][col][row].height=2;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y; 
			
			
			col++;
			
			if(col==gp.maxWorldCol) {
				
				col=0;
				row++;
				if(row == gp.maxWorldRow)
				{
					row = 0;
					map++;
				}
			}
		}
	}
	
	public void checkEvent()
	{	
		
		//evita lo spam di eventi se nello stesso tile 
		int xDistance=Math.abs(gp.player.worldX-previousEventX);
		int yDistance=Math.abs(gp.player.worldY-previousEventY);
		int distance=Math.max(xDistance,yDistance);

		if(distance>gp.tileSize) {
			
			canTouchEvent=true;
	
		}
		
		if(canTouchEvent=true) {
			
			//x=orizontale
			//y=verticale
			//RICORDA CHE SI INIZIA A CONTARE DA 0
			//     X  Y
			if(hit(0,29, 14, "any") == true){damagePit(1,2,gp.dialogueState);}
			else if(hit(0,19, 27, "any") == true){healingPool(gp.dialogueState);}	
			else if(hit(0,20, 27, "any") == true){healingPool(gp.dialogueState);}		
			else if(hit(0,21, 27, "any") == true){healingPool(gp.dialogueState);}		
			else if(hit(0,22, 27, "any") == true){healingPool(gp.dialogueState);}		
			else if(hit(0,23, 27, "any") == true){healingPool(gp.dialogueState);}		
			else if(hit(0,24, 27, "any") == true){healingPool(gp.dialogueState);}
			//if(hit(0,19, 40, "any") == true) {teleport(gp.dialogueState);}	
			else if(hit(0, 122, 147,"any")== true) {teleport(1,129,126);}
			else if(hit(0, 123, 147,"any")== true) {teleport(1,129,126);}
			else if(hit(0, 124, 147,"any")== true) {teleport(1,129,126);}
			else if(hit(0, 125, 147,"any")== true) {teleport(1,129,126);}
			else if(hit(0, 126, 147,"any")== true) {teleport(1,129,126);}
			else if(hit(0, 127, 147,"any")== true) {teleport(1,129,126);}
			else if(hit(0, 128, 147,"any")== true) {teleport(1,129,126);}
			else if(hit(1, 5, 5,"any")== true) {teleport(0, 7, 11);}
			//else if(hit(coordinate banco, "up")) == true){speak(gp.npc[0][2])} //0, 2 lo si vede da assetSetter in base all'npc da mettere
		}
	}
	public boolean hit(int map, int col, int row, String reqDirection)
	{
		boolean hit=false;
		
		if(map == gp.currentMap)
		{
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			
			eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
			eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;
			
			if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone==false)
			{
				if(gp.player.direction.contentEquals(reqDirection)||reqDirection.contentEquals("any"))
				{
					hit=true;
					
					previousEventX=gp.player.worldX;
					previousEventY=gp.player.worldY;
					
				}
			}
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}
		return hit;
	}
	
	
	public void teleport(int map, int col, int row) 
	{
		gp.gameState = gp.transitionState;
		tempMap = map;
		tempCol = col;
		tempRow = row;
		canTouchEvent = false;
	}
	
	public void damagePit (int col, int row, int gameState)
	{
		
		
		gp.gameState = gameState;
		gp.ui.currentDialogue = "Sei inciampato! Sciocchino!";
		gp.player.life -=1;
		
		eventRect[gp.currentMap][col][row].eventDone=true; //serve a far avvenire l' evento 1 sola volta a partita
		
		canTouchEvent=false; //serve a evitare lo spam di danno nello tile
	
	}
	
	public void healingPool (int gameState)
	{
		//PARTE PER CURARE IL PERSONAGGIO VIDEO 19 MINUTO 11:30
		if(gp.keyH.enterPressed==true) {
			gp.gameState=gameState;
			gp.player.attackCanceled = true;
			gp.ui.currentDialogue="L'acqua fa ruggine";
			gp.player.life=gp.player.maxLife;
			gp.player.mana = gp.player.maxMana;
		}
	}
	public void speak(Entity entity)
	{
		if(gp.keyH.enterPressed == true)
		{
			gp.gameState = gp.dialogueState;
			gp.player.attackCanceled = true;
			entity.speak();
		}
	}
	
}
