package technium.seeddropcontrol;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Config {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CONFIG;

    static {
        BUILDER.push("Vanilla Seed Options");
        ChanceConfig.vanilla(BUILDER);
        BUILDER.pop();

        BUILDER.push("Mod Compat Options");
        ChanceConfig.modcompat(BUILDER);
        BUILDER.pop();

        BUILDER.push("Debugging Options");
        ChanceConfig.debug(BUILDER);
        BUILDER.pop();

        CONFIG = BUILDER.build();
    }

    public static void loadConfig(ForgeConfigSpec config, String path) {

        CommentedFileConfig file = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig(file);

    }

}
