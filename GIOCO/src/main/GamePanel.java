package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import ai.PathFinder;
import entity.Entity;
import entity.Player;
import tile.TileManager;
import tile_interactive.InteractiveTile;

@SuppressWarnings({ "serial", "unused" })
public class GamePanel extends JPanel implements Runnable {

	
	//Impostazioni schermo
	
	final int originalTileSize=16; //16*16 dim titolo
	final int scale=3;
	
	public final int tileSize= originalTileSize * scale; //48*48 tile
	public final int maxScreenCol=20;
	public final int maxScreenRow=12; 
	public final int screenWidth=tileSize*maxScreenCol; //768pixel
	public final int screenHeight=tileSize*maxScreenRow; //576pixel
	
	//WORLD SETTINGS
	public final int maxWorldCol = 250; //si possono cambiare
	public final int maxWorldRow = 250;
	public final int maxMap = 5;
	public int currentMap = 0;

	// FPS
	int FPS=60;
	
	
	public TileManager tileM = new TileManager(this);
	public KeyHandler keyH=new KeyHandler(this);
	Sound sound=new Sound();
	Thread gameThread;
	public CollisionChecker cChecker=new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler=new EventHandler(this);
	public PathFinder pFinder = new PathFinder(this);
	
	//ENTITY AND OBJECT
	public Player player=new Player(this,keyH);
	public Entity obj[][] = new Entity[maxMap][20]; //10 perché si possono rappresentare al massimo 10 oggetti allo stesso momento ma si può aumentare
	public Entity npc[][]=new Entity[maxMap][10];
	public Entity monster[] []= new Entity[maxMap][20];
	public InteractiveTile iTile[][]=new InteractiveTile[maxMap][50];
	public Entity projectile [][] = new Entity[maxMap][20];
	//public ArrayList<Entity> projectileList = new ArrayList<>();
	public ArrayList<Entity> particleList=new ArrayList<>();
	ArrayList<Entity> entityList = new ArrayList<>();
	
	
	//GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState=3;
	public final int characterState = 4;
	//public final int optionState = 5;
	public final int gameOverState = 6;
	public final int transitionState = 7;
	public final int tradeState = 8;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.green);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame ()
	{
		//playMusic(5);
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		aSetter.setInteractiveTile();
		gameState = titleState;
		
	}
	public void retry ()
	{
		player.setDefaultPositions();
		player.restoreLifeAndMana();
		aSetter.setNPC();
		aSetter.setMonster();
	}
	public void restart ()
	{
		player.setDefaultValues();
		player.setItems();
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		aSetter.setInteractiveTile();
	}
	
	public void startGameThread() {
		
		
		gameThread=new Thread(this);
		gameThread.start();
	}
	
	
	@Override
	public void run() {
		//accumulator "Delta" LOOP
		double drawInterval=1000000000/FPS;
		double delta=0;
		long lastTime=System.nanoTime();
		long currentTime;
		long timer=0;
		int drawCount=0;
		
		while(gameThread != null) {
			
			currentTime=System.nanoTime();
			
			delta=delta+(currentTime-lastTime)/drawInterval;
			timer=timer+(currentTime-lastTime);
			lastTime=currentTime;
			
			
			if(delta>=1) {
				
				update();
				repaint();
				delta--;
				drawCount++;  
				
			}
			if(timer>=1000000000) {
				//System.out.println("FPS:"+drawCount);
				drawCount=0;
				timer=0;
			}
		}
		
	}
	
	public void update() {
		
		if(gameState == playState)
		{	
		
			//player
			player.update();
			//NPC
			for(int i=0;i<npc[1].length;i++) {
				
				if(npc[currentMap][i]!=null) {
					npc[currentMap][i].update();	
				}
			}
			for(int i=0;i<monster[1].length;i++)
			{
				if(monster[currentMap][i]!=null)
				{
					if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false)
					{
						monster[currentMap][i].update();
					}
					if(monster[currentMap][i].alive == false)
					{
						monster[currentMap][i].checkDrop();
						monster[currentMap][i]=null;
					}
				}
			}
			
			for(int i=0;i<projectile[1].length;i++)
			{
				if(projectile[currentMap][i]!=null)
				{
					if(projectile[currentMap][i].alive == true)
					{
						projectile[currentMap][i].update();
					}
					if(projectile[currentMap][i].alive == false)
					{
						projectile[currentMap][i]=null;
					}
				}
			}
			
			for(int i=0;i<particleList.size();i++)
			{
				if(particleList.get(i)!=null)
				{
					if(particleList.get(i).alive == true)
					{
						particleList.get(i).update();
					}
					if(particleList.get(i).alive == false)
					{
						particleList.remove(i);
					}
				}
			}
			
			for(int i=0;i<iTile[1].length;i++) {
				
				if(iTile[currentMap][i]!=null) {
					iTile[currentMap][i].update();
				}
					
			}
		
			
		}
		
		
		if(gameState == pauseState)
		{
			//niente per ora
		}
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		 
		Graphics2D g2 = (Graphics2D)g;
		
		//DEBUG 
		long drawStart=0;
		if(keyH.check==true)
		{
			drawStart=System.nanoTime();
		}
		
		//TITLE SCREEN
		if(gameState == titleState)
		{
			ui.draw(g2);
		}
		//OTHERS
		else
		{
			//TILE
			tileM.draw(g2);
			
			//INTERACTIVE TILE
			for(int i=0; i < iTile[1].length; i++)
			{
				if(iTile[currentMap][i]!= null)
				{
					iTile[currentMap][i].draw(g2);
				}
			}
			
			entityList.add(player);
			
			//AGGIUNTA ENTITA' ALLA LISTA
			for(int i=0; i < npc[1].length; i++)
			{
				if(npc[currentMap][i]!=null)
				{
					entityList.add(npc[currentMap][i]);
				}
			}
			
			for(int i=0; i < obj[1].length; i++)
			{
				if(obj[currentMap][i]!=null)
				{
					entityList.add(obj[currentMap][i]);
				}
			}
			for(int i=0; i < monster[1].length; i++)
			{
				if(monster[currentMap][i]!=null)
				{
					entityList.add(monster[currentMap][i]);
				}
			}
			for(int i=0; i < projectile[1].length; i++)
			{
				if(projectile[currentMap][i]!=null)
				{
					entityList.add(projectile[currentMap][i]);
				}
			}
			
			for(int i=0; i < particleList.size(); i++)
			{
				if(particleList.get(i)!=null)
				{
					entityList.add(particleList.get(i));
				}
			}
			
			//SORT
			Collections.sort(entityList, new Comparator<Entity>() 
			{	@Override
				public int compare(Entity e1, Entity e2) 
				{
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}
			});
			
			//DISEGNA ENTITA'
			for(int i=0;i<entityList.size();i++)
			{
				entityList.get(i).draw(g2);
			}
			
			//SVUOTA LISTA
			entityList.clear();
			
	
			//UI
			ui.draw(g2);
		}
		
		//DEBUG
		
		drawStart = 0;

		int countCol = player.worldX;
		int countRow = player.worldY;

		if (countCol < 0) {
		    countCol = 0;
		    countRow++;
		}

		if (keyH.check == true) {
		    long drawEnd = System.nanoTime();
		    long passed = drawEnd - drawStart;
		    g2.setColor(Color.white);
		    g2.drawString("Draw Time: " + passed, 10, 400);
		    g2.drawString("Col: " + (int) Math.round((double) countCol / tileSize), 10, 320);
		    g2.drawString("Row: " + (int) Math.round((double) countRow / tileSize), 10, 360);
		}
		g2.dispose();
	}

	
	public void playMusic(int i) {
		
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	
	public void stopMusic() {
		sound.stop();
	}
	
	public void playSE(int i) {
		
		sound.setFile(i);
		sound.play();
		
	}
	
}
