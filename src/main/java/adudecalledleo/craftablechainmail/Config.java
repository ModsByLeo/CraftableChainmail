package adudecalledleo.craftablechainmail;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.Logging;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;

@Mod.EventBusSubscriber(modid = CraftableChainmail.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec spec;

    public static ForgeConfigSpec.BooleanValue replaceChainmailRepairMaterial;

    static {
        replaceChainmailRepairMaterial = BUILDER
                .comment("If true, replaces chainmail armor's repair item with chainmail plates.")
                .define("replaceChainmailRepairMaterial", true);

        spec = BUILDER.build();
    }

    public static void loadConfig(Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading e) {
        CraftableChainmail.LOGGER.debug("Loaded {}'s config file {}",
                CraftableChainmail.MODID, e.getConfig().getFileName());
    }

    public static void onFileChange(final ModConfig.ConfigReloading e) {
        CraftableChainmail.LOGGER.fatal(Logging.CORE, "{}'s config just got changed on the file system!",
                CraftableChainmail.MODID);
    }
}
