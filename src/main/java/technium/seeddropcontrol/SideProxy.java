package technium.seeddropcontrol;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

public class SideProxy {

    SideProxy() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.CONFIG, "seeddropcontrol.toml");
        FMLJavaModLoadingContext.get().getModEventBus().addListener(SideProxy::commonSetup);
        Config.loadConfig(Config.CONFIG, FMLPaths.CONFIGDIR.get().resolve("seeddropcontrol.toml").toString());
    }

    private static void commonSetup(FMLCommonSetupEvent event) {

    }

    static class Client extends SideProxy {
        Client() {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(Client::clientSetup);
        }

        private static void clientSetup(FMLClientSetupEvent event) {

        }
    }

    static class Server extends SideProxy {
        Server() {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(Server::serverSetup);

        }

        private static void serverSetup(FMLDedicatedServerSetupEvent event) {

        }
    }

}
