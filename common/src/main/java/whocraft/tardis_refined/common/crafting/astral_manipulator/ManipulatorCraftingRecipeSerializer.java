package whocraft.tardis_refined.common.crafting.astral_manipulator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import whocraft.tardis_refined.TardisRefined;

/**
 * The recipe serialiser implementation.
 * <br> Making this allows vanilla to automatically add our recipe types onto its recipe packet entry and reload listener
 */
public class ManipulatorCraftingRecipeSerializer implements RecipeSerializer<ManipulatorCraftingRecipe> {

    public static ResourceLocation SERIALIZER_ID = ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "astral_manipulator");

    public ManipulatorCraftingRecipeSerializer() {

    }


    @Override
    public MapCodec<ManipulatorCraftingRecipe> codec() {
        return ManipulatorCraftingRecipe.CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, ManipulatorCraftingRecipe> streamCodec() {
        return null;
    }

    @Override
    public ManipulatorCraftingRecipe fromNetwork(FriendlyByteBuf friendlyByteBuf) {
        ManipulatorCraftingRecipe recipe = ManipulatorCraftingRecipe.CODEC.parse(NbtOps.INSTANCE, friendlyByteBuf.readNbt()).resultOrPartial(TardisRefined.LOGGER::error).get();
        return recipe;
    }

    @Override
    public void toNetwork(FriendlyByteBuf friendlyByteBuf, ManipulatorCraftingRecipe recipe) {
        friendlyByteBuf.writeNbt(ManipulatorCraftingRecipe.CODEC.encodeStart(NbtOps.INSTANCE, recipe).result().orElse(new CompoundTag()));
    }
}
