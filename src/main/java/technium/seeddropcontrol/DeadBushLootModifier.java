package technium.seeddropcontrol;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class DeadBushLootModifier extends LootModifier {
    public DeadBushLootModifier(ILootCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        Boolean debugEnabled = Config.debug_enabled.get();
        if (!generatedLoot.isEmpty()) {
            List<ItemStack> finalLootList = new ArrayList<>();
            for (ItemStack itemStack : generatedLoot) {
                if (Config.dead_bush_chance.get() >= 100)
                    finalLootList.add(itemStack);
                else {
                    double randomValue = Math.random();
                    double chance = Config.dead_bush_chance.get();
                    if (randomValue < chance / 100) {
                        if (debugEnabled) {
                            SeedDropControl.LOGGER.info("randomValue " + randomValue + "  was < than " + chance + "/100, generating loot");
                            SeedDropControl.LOGGER.info("Added loot: " + itemStack.toString());
                        }
                        finalLootList.add(itemStack);
                    } else {
                        if (debugEnabled) {
                            SeedDropControl.LOGGER.info("randomValue " + randomValue + " was > than " + chance + "/100, removing loot: " + itemStack.toString() + " from loot.");
                        }
                    }
                }
            }
            if (debugEnabled) {
                SeedDropControl.LOGGER.info("Final Loot List Is: " + finalLootList.toString());
            }
            return finalLootList;
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<DeadBushLootModifier> {
        @Override
        public DeadBushLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
            return new DeadBushLootModifier(ailootcondition);
        }

        @Override
        public JsonObject write(DeadBushLootModifier instance) {
            return makeConditions(instance.conditions);
        }
    }

}
