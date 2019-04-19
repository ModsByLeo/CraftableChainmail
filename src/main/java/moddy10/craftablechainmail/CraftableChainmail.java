package moddy10.craftablechainmail;

import org.apache.logging.log4j.Logger;

import moddy10.craftablechainmail.proxy.CommonProxy;
import moddy10.craftablechainmail.util.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, acceptedMinecraftVersions = "[1.12.2]")
public class CraftableChainmail {
	
	@Instance
	public static CraftableChainmail instance;
	
	@SidedProxy(serverSide = Reference.PROXY_COMMON, clientSide = Reference.PROXY_CLIENT)
	public static CommonProxy proxy;
	
	public static Logger log;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		log = event.getModLog();
		proxy.preInit(event);
		log.info("Pre-init OK!");
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		proxy.init(event);
		log.info("Init OK!");
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
		log.info("Post-init OK!");
	}
	
}
