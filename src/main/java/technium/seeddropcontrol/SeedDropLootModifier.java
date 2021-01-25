package technium.seeddropcontrol;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class SeedDropLootModifier extends LootModifier {

    public SeedDropLootModifier(ILootCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        Boolean debugEnabled = ChanceConfig.debug_enabled.get();
        if (!generatedLoot.isEmpty()) {
            List<ItemStack> finalLootList = new ArrayList<>();
            for (ItemStack itemStack : generatedLoot) {
                if (itemStack.getItem() == Items.WHEAT_SEEDS) {
                    if (ChanceConfig.grass_wheat_chance.get() >= 100)
                        finalLootList.add(itemStack);
                    else {
                        double randomValue = Math.random();
                        double chance = ChanceConfig.grass_wheat_chance.get();
                        if (randomValue > chance / 100) {
                            if (debugEnabled) {
                                SeedDropControl.LOGGER.info("randomValue was > than " + chance + "/100, generating loot");
                                SeedDropControl.LOGGER.info("Added loot: " + itemStack.toString());
                            }
                            finalLootList.add(itemStack);
                        }
                        else {
                            if (debugEnabled) {
                                SeedDropControl.LOGGER.info("randomValue was < than " + chance + "/100, removing " + itemStack.toString() + " from loot.");
                            }
                        }
                    }
                }
                else {
                    if (itemStack.getItem() == ForgeRegistries.ITEMS.getValue(new ResourceLocation("planttech2", "guide"))) {
                        if (ChanceConfig.planttech2_guidebook_drop.get()) {
                            finalLootList.add(itemStack);
                        }
                        else {
                            if (debugEnabled) {
                                SeedDropControl.LOGGER.info("Planttech2 guidebook is disabled, removing " + itemStack.toString() + " from loot.");
                            }
                        }
                    }
                    else {
                        double randomValue = Math.random();
                        double chance = ChanceConfig.grass_mod_seed_chance.get();
                        if (randomValue > chance / 100) {
                            if (debugEnabled) {
                                SeedDropControl.LOGGER.info("randomValue was > than " + chance + "/100, generating loot");
                                SeedDropControl.LOGGER.info("Added loot: " + itemStack.toString());
                            }
                            finalLootList.add(itemStack);
                        }
                        else {
                            if (debugEnabled) {
                                SeedDropControl.LOGGER.info("randomValue was < than " + chance + "/100, removing " + itemStack.toString() + " from loot.");
                            }
                        }
                    }
                }
            }
            if (debugEnabled) {
                SeedDropControl.LOGGER.info("Final Loot List Is" + finalLootList.toString());
            }
            return finalLootList;
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<SeedDropLootModifier> {

        @Override
        public SeedDropLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
            return new SeedDropLootModifier(ailootcondition);
        }

        @Override
        public JsonObject write(SeedDropLootModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}
