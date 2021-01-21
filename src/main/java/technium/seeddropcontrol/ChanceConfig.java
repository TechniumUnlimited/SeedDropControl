package technium.seeddropcontrol;

import net.minecraftforge.common.ForgeConfigSpec;

public class ChanceConfig {

    public static ForgeConfigSpec.IntValue grass_wheat_chance;
    public static ForgeConfigSpec.IntValue grass_mod_seed_chance;

    public static void init(ForgeConfigSpec.Builder config) {
        grass_wheat_chance = config.comment(
                "Increase or Decrease chance of drops of wheat seeds from grass, 100 is default, <100 is the x% of default (1 is 1% of default rate)").defineInRange("Drop Rate Change", 100, 1, 100);
        grass_mod_seed_chance = config.comment("Increase or Decrease chance of drops of wheat seeds from grass, 100 is default, <100 is the x% of default (1 is 1% of default rate)").defineInRange("Drop Rate Change", 100, 1, 100);
    }

}
