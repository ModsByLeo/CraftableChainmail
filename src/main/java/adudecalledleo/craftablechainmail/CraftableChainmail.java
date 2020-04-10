package adudecalledleo.craftablechainmail;

import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(CraftableChainmail.MODID)
public class CraftableChainmail {
    public static final String MODID = "craftablechainmail";

    static final Logger LOGGER = LogManager.getLogger();

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> CHAINMAIL_PLATE = ITEMS.register("chainmail_plate", () -> new Item(new Item.Properties().group(ItemGroup.MISC)));

    public CraftableChainmail() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.spec);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        ITEMS.register(modEventBus);

        Config.loadConfig(FMLPaths.CONFIGDIR.get().resolve(MODID + "-common.toml"));
    }

    private void setup(FMLCommonSetupEvent e) {
        DeferredWorkQueue.runLater(CraftableChainmail::doReplaceChainmailRepairMaterial);
    }

    private static final String OBSFUCATED_FIELD_NAME_ARMORMATERIAL_REPAIRMATERIAL = "field_200914_m";

    static void doReplaceChainmailRepairMaterial() {
        if (!Config.replaceChainmailRepairMaterial.get())
            return;

        ObfuscationReflectionHelper.setPrivateValue(
                ArmorMaterial.class,
                ArmorMaterial.CHAIN,
                new LazyValue<>(() -> Ingredient.fromItems(CHAINMAIL_PLATE.get())),
                OBSFUCATED_FIELD_NAME_ARMORMATERIAL_REPAIRMATERIAL);
    }
}
