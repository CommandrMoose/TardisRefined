package whocraft.tardis_refined.common.crafting.astral_manipulator;

import com.mojang.serialization.Codec;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import whocraft.tardis_refined.TardisRefined;

/**
 * The recipe serialiser implementation.
 * <br> Making this allows vanilla to automatically add our recipe types onto its recipe packet entry and reload listener
 */
public class ManipulatorCraftingRecipeSerializer implements RecipeSerializer<ManipulatorCraftingRecipe> {

    public static ResourceLocation SERIALIZER_ID = new ResourceLocation(TardisRefined.MODID, "astral_manipulator");

    public static Logger LOGGER = LogManager.getLogger("TardisRefined/ManipulatorCraftingRecipeSerializer");


    public ManipulatorCraftingRecipeSerializer() {

    }

    @Override
    public Codec<ManipulatorCraftingRecipe> codec() {
        return ManipulatorCraftingRecipe.CODEC;
    }

    @Override
    public ManipulatorCraftingRecipe fromNetwork(FriendlyByteBuf friendlyByteBuf) {
        ManipulatorCraftingRecipe recipe = ManipulatorCraftingRecipe.CODEC.parse(NbtOps.INSTANCE, friendlyByteBuf.readNbt()).resultOrPartial(LOGGER::error).get();
        return recipe;
    }

    @Override
    public void toNetwork(FriendlyByteBuf friendlyByteBuf, ManipulatorCraftingRecipe recipe) {
        friendlyByteBuf.writeNbt(ManipulatorCraftingRecipe.CODEC.encodeStart(NbtOps.INSTANCE, recipe).result().orElse(new CompoundTag()));
    }
}
