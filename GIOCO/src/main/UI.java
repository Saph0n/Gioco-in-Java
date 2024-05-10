 package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import entity.Entity;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.OBJ_Mana;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font maruMonica, purisaB; 
	//Font arial_80B;
	//BufferedImage keyImage;
	BufferedImage heart_full, heart_half, heart_blank, manaFull, manaBlank, coin;
	public boolean messageOn=false;
	public String message1 = "";
	int messageCounter1 = 0;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList <>();
	public boolean gameFinished = false;
	public String currentDialogue=""; 
	public int commandNum = -1;
	public int playerSlotCol=0;
	public int playerSlotRow=0;
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;
	int counter = 0;
	public Entity npc;
	int subState=0;
	
	double playTime;
	
	public UI(GamePanel gp)
	{
		this.gp=gp;
		//arial_80B = new Font ("Arial", Font.BOLD, 80);
		try {
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
			purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		//OBJ_Key key = new OBJ_Key(gp);
		//keyImage = key.image;
		
		//CREATE HUD OBJECT
		Entity heart = new OBJ_Heart(gp);
		heart_full=heart.image;
		heart_half=heart.image2;
		heart_blank=heart.image3;
		Entity crystal = new OBJ_Mana(gp);
		manaFull = crystal.image;
		manaBlank = crystal.image2;
		Entity moneta = new OBJ_Coin(gp);
		coin = moneta.down1;
	}
	
	public void addMessage(String text)
	{
		//message = text;
		//messageOn=true;
		message.add(text);
		messageCounter.add(0);
	}
	
	public void draw (Graphics2D g2)
	{
		//g2.setFont(new Font("Arial", Font.PLAIN, 40)); Così il font viene istanziato 60 volte al secondo non va bene
		//g2.drawImage(keyImage, 10, 10, gp.tileSize*2, gp.tileSize*2, null);
		//g2.drawString("x "+gp.player.hasKey, 80, 70); //50 50 posizione nello schermo
		
		this.g2=g2;
	
		g2.setFont(maruMonica);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.red);
		
		//TITLE STATE
		if(gp.gameState == gp.titleState )
		{
			drawTitleScreen();
		}
		
		//PLAY STATE
		if(gp.gameState == gp.playState )
		{
			drawPlayerLife();
			drawMessage();
		}
		//PAUSE STATE
		if(gp.gameState == gp.pauseState )
		{
			drawPlayerLife();
			drawPauseScreen ();
		}
		//DIALOGUE STATE
		if(gp.gameState==gp.dialogueState) 
		{
			drawPlayerLife();
			drawDialogueScreen();
		}
		//CHARACTER STATE
		if(gp.gameState == gp.characterState)
		{
			drawCharacterScreen();
			drawInventory(gp.player, true);
		}
		//GAME OVER STATE
		if(gp.gameState == gp.gameOverState)
		{
			drawGameOverScreen();
		}
		//TRANSITION STATE
		if(gp.gameState == gp.transitionState)
		{
			drawTransition();
		}
		//TRADE STATE
		if(gp.gameState == gp.tradeState)
		{
			drawTradeScreen();
		}
	
	}
	public void drawTradeScreen()
	{
		switch(subState)
		{
			case 0:
				trade_select();break;
			case 1:
				trade_buy();break;
			case 2:
				trade_sell();break;
		}
			
		gp.keyH.enterPressed = false;
	}
	public void trade_select()
	{
		drawDialogueScreen();
		//disegna finestra
		int x = gp.tileSize * 15;
		int y = gp.tileSize * 4;
		int width = gp.tileSize * 3;
		int height = (int)(gp.tileSize * 3.5);
		drawSubWindow(x, y, width, height);
		
		//disegna testo
		x += gp.tileSize;
		y += gp.tileSize;
		g2.drawString("Compra", x, y);
		if(commandNum == 0)
		{
			g2.drawString(">", x-24, y);
			if(gp.keyH.enterPressed == true)
			{
				subState = 1;
			}
		}
		y+=gp.tileSize ;
		g2.drawString("Vendi", x, y);
		if(commandNum == 1)
		{
			g2.drawString(">", x-24, y);
			if(gp.keyH.enterPressed == true)
			{
				subState = 2;
			}
		}
		y+=gp.tileSize ;
		g2.drawString("Esci", x, y);
		if(commandNum == 2)
		{
			g2.drawString(">", x-24, y);
			if(gp.keyH.enterPressed == true)
			{
				commandNum = 0;
				gp.gameState = gp.dialogueState;
				currentDialogue  = "Torna presto!";
			}
		}
		y+=gp.tileSize ;
		
		
	}
	public void trade_buy()
	{
		//disegna l'inventario del player
		drawInventory(gp.player, false);
		//disegna l'inventario dell'npc
		drawInventory(npc, true);
		
		//finestra suggerimento
		int x = gp.tileSize*2;
		int y = gp.tileSize*9;
		int width = gp.tileSize*6;
		int height = gp.tileSize * 2;
		//drawSubWindow(x, y, width, height);
		//g2.drawString("[ESC] Indietro", x+24, y+60);
		
		//finestra monete
		x = gp.tileSize*12;
		y = gp.tileSize*9;
		width = gp.tileSize*6;
		height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("Monete: "+ gp.player.coin, x+24, y+60);
		
		//finestra prezzo
		int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
		if(itemIndex < npc.inventory.size())
		{
			x = (int)(gp.tileSize*5.5);
			y = (int)(gp.tileSize*5.5);
			width = (int)(gp.tileSize*2.5);
			height = gp.tileSize;
			drawSubWindow(x, y, width, height);
			g2.drawImage(coin, x+10, y+10, 32,32, null);
			
			int price = npc.inventory.get(itemIndex).price;
			String text = ""+price;
			x = getXforAlignToRightText(text, gp.tileSize*8-30);
			g2.drawString(text, x, y+32);
			
			//COMPRA UN OGGETTO
			if(gp.keyH.enterPressed)
			{
				if(npc.inventory.get(itemIndex).price > gp.player.coin)
				{
					subState = 0;
					gp.gameState = gp.dialogueState;
					currentDialogue = "Non hai abbastanza monete!";
					drawDialogueScreen();
				}
				else if(gp.player.inventory.size()== gp.player.maxInventorySize)
				{
					subState = 0;
					gp.gameState = gp.dialogueState;
					currentDialogue = "Non hai abbastanza spazio nel tuo inventario!";
					drawDialogueScreen();
				}
				else
				{
					gp.player.coin -= npc.inventory.get(itemIndex).price;
					gp.player.inventory.add(npc.inventory.get(itemIndex));
				}
			}
		}
	}
	public void trade_sell()
	{
		//disegna l'inventario del player
		drawInventory(gp.player, true);
		int x;
		int y;
		int width;
		int height;
		
		x = gp.tileSize*2;
		y = gp.tileSize*9;
		width = gp.tileSize*6;
		height = gp.tileSize * 2;
		//drawSubWindow(x, y, width, height);
		//g2.drawString("[ESC] Indietro", x+24, y+60);
		
		//finestra monete
		x = gp.tileSize*12;
		y = gp.tileSize*9;
		width = gp.tileSize*6;
		height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("Monete: "+ gp.player.coin, x+24, y+60);
		
		//finestra prezzo
		int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
		if(itemIndex < gp.player.inventory.size())
		{
			x = (int)(gp.tileSize*15.5);
			y = (int)(gp.tileSize*5.5);
			width = (int)(gp.tileSize*2.5);
			height = gp.tileSize;
			drawSubWindow(x, y, width, height);
			g2.drawImage(coin, x+10, y+10, 32,32, null);
			
			int price = gp.player.inventory.get(itemIndex).price/2;
			String text = ""+price;
			x = getXforAlignToRightText(text, gp.tileSize*18-30);
			g2.drawString(text, x, y+32);
			
			//VENDI UN OGGETTO
			if(gp.keyH.enterPressed)
			{
				if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon || 
						gp.player.inventory.get(itemIndex) == gp.player.currentShield)
				{
					commandNum = 0;
					subState = 0;
					gp.gameState = gp.dialogueState;
					currentDialogue = "Non puoi vendere un oggetto equipaggiato!";
					
				}
				else
				{
					gp.player.inventory.remove(itemIndex);
					gp.player.coin += price;
				}
			}
		}
	}
	public void drawTransition()
	{
		counter++;
		g2.setColor(new Color(0, 0, 0, counter*5));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		if(counter == 50)
		{
			counter = 0;
			gp.gameState = gp.playState;
			gp.currentMap = gp.eHandler.tempMap;
			gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
			gp.eHandler.previousEventX = gp.player.worldX;
			gp.eHandler.previousEventY = gp.player.worldY;
		}
	}
	public void drawGameOverScreen()
	{
		g2.setColor(new Color(0, 0, 0, 150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110));
		
		text = "Game Over";
		//OMBREGGIATURA
		g2.setColor(Color.BLACK);
		x = getXforCenteredText(text);
		y = gp.tileSize*4;
		g2.drawString(text, x, y);
		
		//PRINCIPAL
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);
		
		//RETRY
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Riprova";
		x = getXforCenteredText(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum == 0)
		{
			g2.drawString(">", x-40, y);
		}
		
		//BACK TO THE TITLE SCREEN
		text = "Menu principale";
		x = getXforCenteredText(text);
		y += 55;
		g2.drawString(text, x, y);
		if(commandNum == 1)
		{
			g2.drawString(">", x-40, y);
		}
	}
	
	public void drawPlayerLife ()
	{
		int x=gp.tileSize/2;
		int y=gp.tileSize/2;
		int i = 0;
		
		//DRAW MAX LIFE
		while(i<gp.player.maxLife/2)
		{
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x+=gp.tileSize;
		}
		
		//RESET
		x=gp.tileSize/2;
		y=gp.tileSize/2;
		i = 0;
		
		//DRAW CURRENT LIFE
		while(i<gp.player.life)
		{
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i<gp.player.life)
			{
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
		//DRAW MAX MANA
		x = (gp.tileSize/2)-5;
		y = (int)(gp.tileSize*1.5);
		i = 0;
		while(i<gp.player.maxMana)
		{
			g2.drawImage(manaBlank, x, y, null);
			i++;
			x += 35;
		}
		
		//DRAW MANA
		x = (gp.tileSize/2)-5;
		y = (int)(gp.tileSize*1.5);
		i = 0;
		while(i < gp.player.mana)
		{
			g2.drawImage(manaFull, x, y, null);
			i++;
			x += 35;
		}
		
	}
	public void drawMessage ()
	{
		int messageX = gp.tileSize;
		int messageY = gp.tileSize*4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
		
		for(int i=0;i<message.size();i++)
		{
			if(message.get(i) != null)
			{
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX+2, messageY+2);
				g2.setColor(Color.WHITE);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i)+1;
				messageCounter.set(i, counter);
				messageY +=50;
				
				if(messageCounter.get(i) > 180)
				{
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
	}
	
	public void drawTitleScreen()
	{
		g2.setColor(new Color(70, 120, 80)); //COLORE BACKGROUND, TOGLI PER USARE IL COLORE DI DEFAULT IN GAMEPANEL
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		//TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
		String text = "Don Keedick Adventure";
		int x = getXforCenteredText(text);
		int y = gp.tileSize*3-20;
		
		//SHADOW --- VIENE SCRITTO SOTTO AL TESTO UN ALTRO TESTO SPOSTATO
		g2.setColor(Color.black); //CAMBIA IL COLORE DEL TESTO SOTTO PER CAMBIARE LA SFUMATURA
		g2.drawString(text, x+5, y+5);
		//MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		//DON KEEDICK IMAGE
		x = gp.screenWidth /2 - (gp.tileSize*2)/2;
		y += gp.tileSize*2;
		g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
		
		//MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
		text = "NUOVA PARTITA";
		x = getXforCenteredText(text);
		y += gp.tileSize*4-20;
		g2.drawString(text, x, y);
		if(commandNum == 0)
		{
			g2.drawString(">", x-20, y);
		}
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
		text = "ESCI DAL GIOCO";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 1)
		{
			g2.drawString(">", x-20, y);
		}
		
//		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
//		text = "UML PROGETTO";
//		x = getXforCenteredText(text);
//		y += gp.tileSize;
//		g2.drawString(text, x, y);
//		if(commandNum == 2)
//		{
//			g2.drawString(">", x-20, y);
//		}
	}
	public void drawPauseScreen ()
	{
		g2.setColor(new Color(70, 120, 80)); //COLORE BACKGROUND, TOGLI PER USARE IL COLORE DI DEFAULT IN GAMEPANEL
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		//TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
		String text = "Don Keedick Adventure";
		int x = getXforCenteredText(text);
		int y = gp.tileSize*3-20;
		
		//SHADOW --- VIENE SCRITTO SOTTO AL TESTO UN ALTRO TESTO SPOSTATO
		g2.setColor(Color.black); //CAMBIA IL COLORE DEL TESTO SOTTO PER CAMBIARE LA SFUMATURA
		g2.drawString(text, x+5, y+5);
		//MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		//MENU
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
		text = "CONTINUA PARTITA";
		x =	getXforCenteredText(text);
		y += gp.tileSize*4-20;
		g2.drawString(text, x, y);
		if(commandNum == 0)
		{
			g2.drawString(">", x-20, y);
		}
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
		text = "TORNA AL MENU";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 1)
		{
			g2.drawString(">", x-20, y);
		}
	}
	
	public void drawDialogueScreen() {
		
		//finestra
		int x = gp.tileSize * 3;// modifica la larghezza della finestra dialogo----------------
		int y = gp.tileSize/2; // modifica la lunghezza della finestra dialogo-----------------
		int width=gp.screenWidth-(gp.tileSize*6);
		int height=gp.tileSize*4;
		
		drawSubWindow(x,y,width,height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
		x=x+gp.tileSize;
		y=y+gp.tileSize;
		for(String line:currentDialogue.split("\n")) {
			g2.drawString(line,x,y);
			y=y+40;
		}
	}
	public void drawCharacterScreen()
	{
		//CREATE A FRAME
		final int frameX = gp.tileSize;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize*5;
		final int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		//TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 35;
		
		//NAMES
		g2.drawString("Livello", textX, textY);
		textY += lineHeight;
		g2.drawString("Vita", textX, textY);
		textY += lineHeight;
		g2.drawString("Mana", textX, textY);
		textY += lineHeight;
		g2.drawString("Forza", textX, textY);
		textY += lineHeight;
		g2.drawString("Destrezza", textX, textY);
		textY += lineHeight;
		g2.drawString("Attacco", textX, textY);
		textY += lineHeight;
		g2.drawString("Difesa", textX, textY);
		textY += lineHeight;
		g2.drawString("Exp", textX, textY);
		textY += lineHeight;
		g2.drawString("Prossimo livello", textX, textY);
		textY += lineHeight;
		g2.drawString("Monete", textX, textY);
		textY += lineHeight + 15;
		g2.drawString("Arma", textX, textY);
		textY += lineHeight+ 10;
		g2.drawString("Scudo", textX, textY);
		textY += lineHeight;
		
		//VALUES
		int tailX = (frameX + frameWidth) - 30;
		//Reset text Y 
		textY = frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.life+ "/" + gp.player.maxLife);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.mana+ "/" + gp.player.maxMana);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.strength);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.dexterity);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.attack);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.defense);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.exp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX+5, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.coin);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY-20, null);
		textY += gp.tileSize;
		g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY-20, null);
	}
	
	public void drawInventory (Entity entity, boolean cursor)
	{
		int frameX = 0;
		int frameY = 0;
		int frameWidth = 0;
		int frameHeight =0;
		int dFrameWidth = 0; 
		int dFrameHeight = 0;
		int slotCol = 0;
		int slotRow = 0;
		
		if(entity == gp.player)
		{
			frameX = gp.tileSize*12;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize * 6;
			frameHeight = gp.tileSize * 5;
			dFrameWidth = frameWidth;	
			dFrameHeight = gp.tileSize * 4;
			slotCol = playerSlotCol;
			slotRow = playerSlotRow;
		}
		else
		{
			frameX = gp.tileSize*2;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize * 6;
			frameHeight = gp.tileSize * 5;
			dFrameWidth = frameWidth;	
			dFrameHeight = gp.tileSize * 4;
			slotCol = npcSlotCol;
			slotRow = npcSlotRow;
		}
		
		//FRAME
		drawSubWindow(frameX, frameY, frameWidth,frameHeight);
		
		//SLOTS
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.tileSize+3;
		
		//DRAW PLAYER'S ITEMS
		for(int i = 0;i<entity.inventory.size(); i++)
		{
			//EQUIP CURSOR
			if(entity.inventory.get(i) == entity.currentWeapon ||
					entity.inventory.get(i) == entity.currentShield )
			{
				g2.setColor(new Color(240, 190, 90));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}
		
			g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
			slotX += slotSize;
			
			if(i == 4 || i == 9 || i==14)
			{
				slotX=slotXstart;
				slotY += slotSize;
			}
		}
		
		//CURSOR
		if(cursor == true)
		{
			int corsorX = slotXstart + (slotSize * slotCol);
			int corsorY = slotYstart + (slotSize * slotRow);
			int cursorWidth = gp.tileSize;
			int cursorHeight = gp.tileSize;
			
			//DRAW CURSOR
			g2.setColor(Color.WHITE); 
			g2.setStroke(new BasicStroke(3));
			g2.drawRoundRect(corsorX, corsorY, cursorWidth, cursorHeight, 10, 10);
			
			//DESCRIPTION FRAME
			int dFrameX = frameX;
			int dFrameY = frameY + frameHeight+10;
			
			
			//DRAW DESCRIPTION TEXT
			int textX = dFrameX + 20;
			int textY = dFrameY + gp.tileSize;
			g2.setFont(g2.getFont().deriveFont(28F));
			int itemIndex = getItemIndexOnSlot(slotCol, slotRow);
			
			if(itemIndex < entity.inventory.size())
			{
				drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
				
				for(String line: entity.inventory.get(itemIndex).description.split("\n"))
				{
					g2.drawString(line, textX, textY);
					textY += 32;
				}
			}
		}
		
	}
	public int getItemIndexOnSlot(int slotCol, int slotRow)
	{
		int itemIndex = slotCol + (slotRow*5);
		return itemIndex;
	}
	
	public void drawSubWindow(int x,int y,int width,int height) {
		
		int trasparenza=200;
		Color c=new Color(0,0,0,trasparenza);
		g2.setColor(c);
		g2.fillRoundRect(x,y,width,height,35,35);
		
		c=new Color(255,55,55);//ROSSO,GIALLO,BLU----MODIFICA IL BORDO DELLA FINESTRA TESTO
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
		
	}
	
	public int getXforCenteredText(String text)
	{
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	public int getXforAlignToRightText(String text, int tailX)
	{
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
}
