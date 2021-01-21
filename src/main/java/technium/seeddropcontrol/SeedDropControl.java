package technium.seeddropcontrol;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

@Mod("seeddropcontrol")
public class SeedDropControl {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "seeddropcontrol";

    public SeedDropControl() {
        GLM.register(FMLJavaModLoadingContext.get().getModEventBus());
        DistExecutor.safeRunForDist(() -> SideProxy.Client::new, () -> SideProxy.Server::new);

    }

    private static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, MOD_ID);

    private static final RegistryObject<SeedDropLootModifier.Serializer> GRASSMODIFIER = GLM.register("global_grass_modifier", SeedDropLootModifier.Serializer::new);
    private static final RegistryObject<SeedDropLootModifier.Serializer> TALLGRASSMODIFIER = GLM.register("global_tall_grass_modifier", SeedDropLootModifier.Serializer::new);


    @Nonnull
    public static ResourceLocation getId(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        LOGGER.info("Is nether allowed?:");
    }

    public static double convertChanceRate() {
        return 0;
    }

}
