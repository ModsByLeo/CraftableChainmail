package adudecalledleo.craftablechainmail;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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
