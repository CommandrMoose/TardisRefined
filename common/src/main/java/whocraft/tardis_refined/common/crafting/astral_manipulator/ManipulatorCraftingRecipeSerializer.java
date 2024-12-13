package whocraft.tardis_refined.common.crafting.astral_manipulator;

import com.mojang.serialization.MapCodec;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.nbt.CompoundTag;
import whocraft.tardis_refined.TardisRefined;

/**
 * The recipe serializer implementation.
 * <br> This allows vanilla to automatically add our recipe types to its recipe packet entry and reload listener.
 */
public class ManipulatorCraftingRecipeSerializer implements RecipeSerializer<ManipulatorCraftingRecipe> {

    public static final ResourceLocation SERIALIZER_ID = new ResourceLocation(TardisRefined.MODID, "astral_manipulator");

    public ManipulatorCraftingRecipeSerializer() {
    }

    @Override
    public MapCodec<ManipulatorCraftingRecipe> codec() {
        return ManipulatorCraftingRecipe.CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, ManipulatorCraftingRecipe> streamCodec() {
        return StreamCodec.of(this::toNetwork, this::fromNetwork);
    }

    /**
     * Decodes the recipe from the network buffer (RegistryFriendlyByteBuf).
     * This method now correctly handles CompoundTag and expects MapLike structure.
     */
    private ManipulatorCraftingRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
        // Read the NBT data into a CompoundTag
        CompoundTag nbtData = buf.readNbt();
        // Use the MapCodec to decode the CompoundTag into a ManipulatorCraftingRecipe
        return ManipulatorCraftingRecipe.CODEC.decode(buf)
                .resultOrPartial(TardisRefined.LOGGER::error)
                .get();  // Get the result or log errors if any
    }

    /**
     * Encodes the recipe into the network buffer (RegistryFriendlyByteBuf).
     * Serializes the recipe into a CompoundTag and writes it to the buffer.
     */
    private void toNetwork(RegistryFriendlyByteBuf buf, ManipulatorCraftingRecipe recipe) {
        // Serialize the ManipulatorCraftingRecipe into a CompoundTag
        CompoundTag nbt = ManipulatorCraftingRecipe.CODEC.encodeStart(NbtOps.INSTANCE, recipe)
                .result()
                .orElseThrow(() -> new IllegalStateException("Failed to encode recipe"));
        // Write the CompoundTag to the buffer
        buf.writeNbt(nbt);
    }
}
