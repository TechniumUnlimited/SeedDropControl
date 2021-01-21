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
import java.util.List;

public class SeedDropLootModifier extends LootModifier {

    public SeedDropLootModifier(ILootCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        if (generatedLoot.size() > 0) {
            if (generatedLoot.get(0).getItem() == Items.WHEAT_SEEDS) {
                if (ChanceConfig.grass_wheat_chance.get() == 100) {
                    return generatedLoot;
                }
                if (ChanceConfig.grass_wheat_chance.get() < 100) {
                    double randomValue = Math.random();
                    double chance = ChanceConfig.grass_wheat_chance.get();
                    if (randomValue < chance / 100) {
                        SeedDropControl.LOGGER.info(randomValue);
                        generatedLoot.remove(0);
                    }
                }
            }
            else {
                double randomValue = Math.random();
                double chance = ChanceConfig.grass_mod_seed_chance.get();
                if (randomValue < chance / 100) {
                    SeedDropControl.LOGGER.info(randomValue);
                    generatedLoot.remove(0);
                }
            }
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
            JsonObject json = makeConditions(instance.conditions);
            return json;
        }
    }
}
