package moddy10.craftablechainmail.item;

import moddy10.craftablechainmail.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ModItem extends Item {

	public ModItem(String name) {
		setRegistryName(name);
		setUnlocalizedName(Reference.MODID + "." + name);
	}
	
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

}