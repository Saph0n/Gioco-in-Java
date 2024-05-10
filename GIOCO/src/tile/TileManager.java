package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

@SuppressWarnings("unused")
public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][][];
	//boolean drawPath = true;
	public TileManager(GamePanel gp) {
		
		this.gp=gp;	
		tile=new Tile[100];
		mapTileNum=new int [gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/Mappa1.txt", 0); //<---da cambiare con world01.txt quando si creerà il mondo
		loadMap("/maps/MappaSlime.txt", 1);
		
	}
	public void getTileImage() 
	{
		
		//setup(0, "Erba16", false); // true/false-->collisione
		setup(0,"1Albero",true);
		setup(1,"1AlberoM",true);
		setup(2,"1EAngolo1",false);
		setup(3,"1EAngolo2",false);
		setup(4,"1EAngolo3",false);
		setup(5,"1EAngolo4",false);
		setup(6,"1Erba1",false);
		setup(	7	,	"1Erba10"	,	false)	;
		setup(	8	,	"1Erba11"	,	false)	;
		setup(	9	,	"1Erba4"	,	false)	;
		setup(	10	,	"1Erba5"	,	false)	;
		setup(	11	,	"1Erba6"	,	false)	;
		setup(	12	,	"1Erba7"	,	false)	;
		setup(	13	,	"1Erba8"	,	false)	;
		setup(	14	,	"1Erba9"	,	false)	;
		setup(	15,		"1Erba2"	,	false)	;
		setup(	16	,	"1Erba3"	,	false)	;
		setup(	17	,	"1Lago1"	,	true)	;
		setup(	18	,	"1Lago2"	,	true)	;
		setup(	19	,	"1Lago3"	,	true)	;
		setup(	20	,	"1Lago4"	,	true)	;
		setup(	21	,	"1Lago5"	,	true)	;
		setup(	22	,	"1Lago6"	,	true)	;
		setup(	23	,	"1Lago7"	,	true)	;
		setup(	24	,	"1Lago8"	,	true)	;
		setup(	25	,	"1Lago9"	,	true)	;
		setup(	26	,	"1LAngolo1"	,	false)	;
		setup(	27	,	"1Langolo2"	,	false)	;
		setup(	28	,	"1LAngolo3"	,	false)	;
		setup(	29	,	"1LAngolo4"	,	false)	;
		setup(	30	,	"1Mare1"	,	true)	;
		setup(	31	,	"1Mare2"	,	true)	;
		setup(	32	,	"1Mare3"	,	true)	;
		setup(	33	,	"1Mare4"	,	true)	;
		setup(	34	,	"1Mare5"	,	true)	;
		setup(	35	,	"1Mare6"	,	true)	;
		setup(	36	,	"1Mare7"	,	true)	;
		setup(	37	,	"1Mare8"	,	true)	;
		setup(	38	,	"1Mare9"	,	true)	;
		setup(	39	,	"1Ninfea"	,	true)	;
		setup(	40	,	"1Spiaggia1"	,	false)	;
		setup(	41	,	"1Spiaggia2"	,	false)	;
		setup(	42	,	"1Spiaggia3"	,	false)	;
		setup(	43	,	"1Spiaggia4"	,	false)	;
		setup(	44	,	"1Spiaggia5"	,	false)	;
		setup(	45	,	"1Spiaggia6"	,   false)	;
		setup(	46	,	"1Spiaggia7"	,	false)	;
		setup(	47	,	"1Spiaggia8"	,	false)	;
		setup(	48	,	"1Spiaggia9"	,	false)	;
		setup(	49	,	"2erbaNeve1"	,	false)	;
		setup(	50	,	"2erbaNeve2"	,	false)	;
		setup(	51	,	"2erbaNeve3"	,	false)	;
		setup(	52	,	"2erbaNeve4"	,	false)	;
		setup(	53	,	"2erbaNeve5"	,	false)	;
		setup(	54	,	"2erbaNeve6"	,	false)	;
		setup(	55	,	"2erbaNeve7"	,	false)	;
		setup(	56	,	"2erbaNeve8"	,	false)	;
		setup(57, "2erbaNeve9", false);
		setup(58, "2erbaNeve10", false);
		setup(59, "2erbaNeve11", false);
		setup(60, "2erbaNeve12", false);
		setup(61, "2erbaNeve13", false);
		setup(62, "2erbaNeve14", false);
		setup(63, "2lago1", true);
		setup(64, "2lago2", true);
		setup(65, "2lago3", true);
		setup(66, "2lago4", true);
		setup(67, "2lago5", true);
		setup(68, "2lago6", true);
		setup(69, "2lago7", true);
		setup(70, "2lago8", true);
		setup(71, "2lago10", true);
		setup(72, "2lago11", true);
		setup(73, "2lago12", true);
		setup(74, "2lago13", true);
		setup(75, "2Sassi", false);
	}
	
	public void setup(int index, String imageName, boolean collision)
	{
		UtilityTool uTool = new UtilityTool();
		
		try
		{
			tile[index] = new Tile();
			tile[index].image=ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imageName +".png"));
			tile[index].image = uTool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision; 
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath, int map) {
		
		try {
			
			InputStream is =getClass().getResourceAsStream(filePath);
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			
			int col=0;
			int row=0;
			
			while(col<gp.maxWorldCol&&row<gp.maxWorldRow) {
				
				String line= br.readLine();
				
				while(col<gp.maxWorldCol) {
					
					String numbers[]=line.split(" ");

					int num=Integer.parseInt(numbers[col]);
					
					mapTileNum[map][col][row]=num;
					col++;
				}
				if(col==gp.maxWorldCol) {
					
					col=0;
					row++;
				}
			}
			br.close();
		}catch(Exception e){
			
			
		}
		
		
	}
	public void draw(Graphics2D g2) {
		
		int worldCol=0;
		int worldRow=0;
		
		while(worldCol<gp.maxWorldCol&&worldRow<gp.maxWorldRow) {
			
			int tileNum=mapTileNum[gp.currentMap][worldCol][worldRow];
			
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
			{
				g2.drawImage(tile[tileNum].image,screenX,screenY,null);
			}
			
			worldCol++;
			
			if(worldCol==gp.maxWorldCol) {
				worldCol=0;
				worldRow++;
			}
			
		}
//		if(drawPath == true)
//		{
//			g2.setColor(new Color(255, 0, 0, 70));
//			
//			for(int i=0; i< gp.pFinder.pathList.size(); i++)
//			{
//				int worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
//				int worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;
//				int screenX = worldX - gp.player.worldX + gp.player.screenX;
//				int screenY = worldY - gp.player.worldY + gp.player.screenY;
//				
//				g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
//			}
//		}
	}

}
