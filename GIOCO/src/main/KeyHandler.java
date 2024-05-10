package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	GamePanel gp;																						
	public boolean upPressed,downPressed,leftPressed,rightPressed,enterPressed,shotKeyPressed ;
	//DEBUG
	boolean check = false;
	
	public KeyHandler (GamePanel gp) {this.gp=gp;}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		int code=e.getKeyCode();
		
		//TITLE STATE
		if(gp.gameState == gp.titleState)
		{
			titleState(code);
		}
		//PLAY
		else if(gp.gameState==gp.playState) {
			playState(code);
		}
		//PAUSE STATE
		else if(gp.gameState==gp.pauseState) {
			
			pauseState(code);
		}
		//DIALOGO
		else if(gp.gameState==gp.dialogueState) {
			dialogueState(code);
		}
		//CHARACTER STATE
		else if (gp.gameState == gp.characterState)
		{
			characterState(code);
		}
		//GAME OVER STATE
		else if (gp.gameState == gp.gameOverState)
		{
			gameOverState(code);
		}
		//TRADE STATE
		else if(gp.gameState == gp.tradeState)
		{
			tradeState(code);
		}

}
	public void playerInventory(int code)
	{
		if(code == KeyEvent.VK_UP) 
		{
			if(gp.ui.playerSlotRow != 0)
			{
				gp.ui.playerSlotRow--;
				gp.playSE(2); //suono di quando si scorrono gli slot dell'inventario
			}
		}
		if(code == KeyEvent.VK_LEFT)
		{
			if(gp.ui.playerSlotCol != 0)
			{
				gp.ui.playerSlotCol--;
				gp.playSE(2);
			}
		}
		if(code == KeyEvent.VK_DOWN)
		{
			if(gp.ui.playerSlotRow != 3)
			{
				gp.ui.playerSlotRow++;
				gp.playSE(2);
			}
		}
		if(code == KeyEvent.VK_RIGHT)
		{
			if(gp.ui.playerSlotCol != 4)
			{
				gp.ui.playerSlotCol++;
				gp.playSE(2);
			}
		}

	}
	public void npcInventory(int code)
	{
		if(code == KeyEvent.VK_UP) 
		{
			if(gp.ui.npcSlotRow!=0)
			{
				gp.ui.npcSlotRow--;
				gp.playSE(2); //suono di quando si scorrono gli slot dell'inventario
			}
		}
		if(code == KeyEvent.VK_DOWN)
		{
			if(gp.ui.npcSlotRow!=3)
			{
				gp.ui.npcSlotRow++;
				gp.playSE(2);
			}
		}
		if(code == KeyEvent.VK_RIGHT)
		{
			if(gp.ui.npcSlotCol != 4)
			{
				gp.ui.npcSlotCol++;
				gp.playSE(2);
			}
		}
		if(code == KeyEvent.VK_LEFT)
		{
			if(gp.ui.npcSlotCol!=0)
			{
				gp.ui.npcSlotCol--;
				gp.playSE(2);
			}
		}
	}
	public void tradeState(int code)
	{
		if(code == KeyEvent.VK_ENTER)
		{
			enterPressed = true;
		}
		
		if(gp.ui.subState == 0)
		{
			if(code == KeyEvent.VK_UP) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum <0)
				{
					gp.ui.commandNum = 2;
				}
				gp.playSE(2);
			}
			if(code == KeyEvent.VK_DOWN) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum >2)
				{
					gp.ui.commandNum = 0;
				}
				gp.playSE(2);
			}
		}
		if(gp.ui.subState == 1)
		{
			npcInventory(code);
			if(code == KeyEvent.VK_ESCAPE)
			{
				gp.ui.subState = 0;
			}
		}
		if(gp.ui.subState == 2)
		{
			playerInventory(code);
			if(code == KeyEvent.VK_ESCAPE)
			{
				gp.ui.subState = 0;
			}
		}
	}
	public void gameOverState(int code)
	{
		if(code == KeyEvent.VK_DOWN)
		{
			gp.ui.commandNum--;
			if(gp.ui.commandNum<0)
				gp.ui.commandNum = 1;
			gp.playSE(2);
		}
		if(code == KeyEvent.VK_UP)
		{
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 0)
				gp.ui.commandNum = 0;
			gp.playSE(2);
		}
		if(code == KeyEvent.VK_ENTER)
		{
			if(gp.ui.commandNum == 0)
			{
				gp.gameState = gp.playState;
				gp.retry();
				gp.stopMusic();
				gp.playMusic(5);
			}
			else if (gp.ui.commandNum == 1)
			{
				gp.gameState = gp.titleState;
				gp.restart();
				gp.stopMusic();
			}
		}
	}
	public void titleState(int code)
	{
		
		if(code == KeyEvent.VK_UP)
		{
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0)
			{
				gp.ui.commandNum=2;
			}
		}
		if(code == KeyEvent.VK_DOWN)
		{
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 2)
			{
				gp.ui.commandNum=0;
			}
		}
		if(code == KeyEvent.VK_ENTER)
		{
			switch(gp.ui.commandNum)
			{
				case 0:
					gp.gameState = gp.playState;
					gp.playMusic(5);
					break;
				case 1:
					System.exit(0);
					break;
				case 2:
					
					
			}
		}
	}
	
	public void playState(int code)
	{
		if(code == KeyEvent.VK_W) {
			upPressed=true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed=true;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed=true;
		}
		if(code == KeyEvent.VK_D) {
		   rightPressed=true;
		}
		if(code == KeyEvent.VK_ESCAPE) {
		  if(gp.gameState == gp.playState)
		  {
			  //ABBASSARE IL SUONO----------------------------------------------------------------------------------------------
			  gp.gameState = gp.pauseState;
		  }
	    }
	    if(code == KeyEvent.VK_E)
	    {
	    	gp.gameState = gp.characterState;
	    }
	    if(code == KeyEvent.VK_ENTER) {
	    	enterPressed=true;			   
	    }
	    if(code == KeyEvent.VK_C) {
	    	shotKeyPressed=true;			   
	    }
	    //DEBUG
	    if(code == KeyEvent.VK_T) {
	    	if(check==false)
	    	{
	    		check=true;
	    	}
	    	else if(check==true)
	    	{
	    		check=false;
	    	}
	    }
	    if(code == KeyEvent.VK_R)
	    {
	    	switch(gp.currentMap)
	    	{
	    		case 0:
	    			gp.tileM.loadMap("/maps/Mappa1.txt", 0);
	    			break;
	    		case 1:
	    			gp.tileM.loadMap("/maps/mappa2.txt", 1);
	    			break;
	    	}
	    		
	    }
	}
	
	public void pauseState(int code)
	{
		if(code == KeyEvent.VK_UP)
		{
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0)
			{
				gp.ui.commandNum=1;
			}
		}
		if(code == KeyEvent.VK_DOWN)
		{
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 1)
			{
				gp.ui.commandNum=0;
			}
		}
		if(code == KeyEvent.VK_ENTER)
		{
			switch(gp.ui.commandNum)
			{
				case 0: //RIPRENDI
					gp.gameState = gp.playState;
					//rialza suono--------------------------------------------------------------------
					break;
				case 1:
					gp.gameState = gp.titleState;
					break;	
			}
		}
	}
	public void dialogueState(int code)
	{	
		if(code==KeyEvent.VK_ENTER) {
			gp.gameState=gp.playState;
		}
	}
	
	public void characterState(int code)
	{
		if(code == KeyEvent.VK_E)
		{
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_ENTER)
		{
			gp.player.selectItem();
		}
		playerInventory(code);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code=e.getKeyCode();
		
		
		if(code == KeyEvent.VK_W) {
			upPressed=false;

		}
		if(code == KeyEvent.VK_S) {
			downPressed=false;

	   }
	  
	   if(code == KeyEvent.VK_A) {
			leftPressed=false;

	   }
	   if(code == KeyEvent.VK_D) {
		   rightPressed=false;
		   
	   }
	   if(code == KeyEvent.VK_C) {
	    	shotKeyPressed=false;	
	    		
	   }
		
	}


}
