package adudecalledleo.craftablechainmail;

import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(CraftableChainmail.MODID)
public class CraftableChainmail {
    public static final String MODID = "craftablechainmail";

    static final Logger LOGGER = LogManager.getLogger();

    @ObjectHolder(MODID)
    public static final Item CHAINMAIL_PLATE = null;

    public CraftableChainmail() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.spec);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

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
                new LazyLoadBase<>(() -> Ingredient.fromItems(CHAINMAIL_PLATE)),
                OBSFUCATED_FIELD_NAME_ARMORMATERIAL_REPAIRMATERIAL);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    private static class RegistryEvents {
        public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
            return setup(entry, new ResourceLocation(MODID, name));
        }

        public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
            entry.setRegistryName(registryName);
            return entry;
        }

        @SubscribeEvent
        public static void onRegisterItems(RegistryEvent.Register<Item> e) {
            e.getRegistry().registerAll(
                setup(new Item(new Item.Properties().group(ItemGroup.MATERIALS)), "chainmail_plate")
            );
        }
    }
}
