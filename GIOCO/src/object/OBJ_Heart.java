package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity
{
	GamePanel gp;
	
	public OBJ_Heart(GamePanel gp)
	{	
		super(gp);
		this.gp=gp;
		type=type_pickupOnly;
		name = "Heart";
		value=2;
		down1=setup("/objects/Heart_full",gp.tileSize,gp.tileSize);
		image = setup("/objects/Heart_full",gp.tileSize,gp.tileSize);
		image2 = setup("/objects/Heart_half",gp.tileSize,gp.tileSize);
		image3 = setup("/objects/Heart_blank",gp.tileSize,gp.tileSize);
	}
	
	public boolean use(Entity entity) {
		
		gp.playSE(2);
		gp.ui.addMessage("Life +" +value);
		entity.life+=value;
		return true;
	}
	
}