package technium.seeddropcontrol;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Config {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CONFIG;

    public static ForgeConfigSpec.IntValue grass_wheat_chance;
    public static ForgeConfigSpec.IntValue dead_bush_chance;
    public static ForgeConfigSpec.IntValue grass_mod_seed_chance;
    public static ForgeConfigSpec.BooleanValue planttech2_guidebook_drop;
    public static ForgeConfigSpec.BooleanValue debug_enabled;

    static {
        BUILDER.push("Vanilla Seed Options");
        vanilla(BUILDER);
        BUILDER.pop();

        BUILDER.push("Dead Bush Drops");
        vanillaDeadBush(BUILDER);
        BUILDER.pop();

        BUILDER.push("Mod Compat Options");
        modcompat(BUILDER);
        BUILDER.pop();

        BUILDER.push("Debugging Options");
        debug(BUILDER);
        BUILDER.pop();

        CONFIG = BUILDER.build();
    }

    public static void vanilla(ForgeConfigSpec.Builder config) {
        grass_wheat_chance = config.comment("Decrease chance of drops of wheat seeds from grass, 100 is vanilla default drop rate, <100 is the x% of default (1 is 1% of default rate)").defineInRange("Drop Rate Change", 100, 1, 100);
    }
    public static void vanillaDeadBush(ForgeConfigSpec.Builder config) {
        dead_bush_chance = config.comment("Decrease chance of drops from all dead bush blocks, 100 is vanilla default drop rate, <100 is the x% of default (1 is 1% of default rate)").defineInRange("Drop Rate Change", 100, 1, 100);
    }
    public static void modcompat(ForgeConfigSpec.Builder config) {
        grass_mod_seed_chance = config.comment("Decrease chance of drops of modded seeds from grass, 100 is vanilla default drop rate, <100 is the x% of default (1 is 1% of default rate)").defineInRange("Drop Rate Change", 100, 1, 100);
        planttech2_guidebook_drop = config.comment("Mod Compat - Planttech2: Allow Planttech2's guidebook to drop?").define("True/False", true);
    }
    public static void debug(ForgeConfigSpec.Builder config) {
        debug_enabled = config.comment("Should debug messages be printed to the logs/server console?").define("True/False", false);
    }

}
