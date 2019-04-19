package moddy10.craftablechainmail.init;

import moddy10.craftablechainmail.item.ModItem;
import moddy10.craftablechainmail.util.Reference;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ObjectHolder(value = Reference.MODID)
public final class ModItems {

	private ModItems() {
		throw new RuntimeException("No " + getClass().getName() + " instance for you!");
	}

	public static final ModItem chainmail_plate = null;
	
	@SideOnly(Side.CLIENT)
	public static void initModels() {
		chainmail_plate.initModel();
	}

}
