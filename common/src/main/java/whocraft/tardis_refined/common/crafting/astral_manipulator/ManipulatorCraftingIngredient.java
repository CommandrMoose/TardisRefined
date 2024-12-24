package whocraft.tardis_refined.common.crafting.astral_manipulator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Main ingredient object for a ManipulatorCraftingRecipe.
 **/
public class ManipulatorCraftingIngredient {

    public static final Codec<ManipulatorCraftingIngredient> CODEC = RecordCodecBuilder.create(
            builder -> builder.group(
                            BlockPos.CODEC.fieldOf("relative_pos").forGetter(recipe -> recipe.relativeBlockPos),
                            BlockState.CODEC.fieldOf("block_state").forGetter(recipe -> recipe.blockState),
                            TagKey.codec(Registries.BLOCK).optionalFieldOf("block_tag").forGetter(recipe -> recipe.blockTagKey)
                    )
                    .apply(builder, ManipulatorCraftingIngredient::new)
    );
    // Block position relative to the closest corner to 0,0,0 in world space.
    private BlockPos relativeBlockPos;
    // The block state that must exist at that position.
    private BlockState blockState;

    private Optional<TagKey<Block>> blockTagKey;

    public ManipulatorCraftingIngredient(BlockPos pos, Block block) {
        this(pos, block.defaultBlockState(), Optional.empty());
    }

    public ManipulatorCraftingIngredient(BlockPos pos, BlockState blockState) {
        this(pos, blockState, Optional.empty());
    }

    public ManipulatorCraftingIngredient(BlockPos pos, BlockState blockState, Optional<TagKey<Block>> blockTagKey) {
        this.relativeBlockPos = pos;
        this.blockState = blockState;
        this.blockTagKey = blockTagKey;
    }

    /**
     * Compares a ManipulatorCraftingRecipeItem to another.
     *
     * @param compared The recipe item to compare to.
     * @return If the items are equivalent.
     **/
    public boolean IsSameAs(ManipulatorCraftingIngredient compared) {
        if (!compared.blockState.is(this.blockState.getBlock()) ||
                blockTagKey.isPresent() && compared.blockState.is(blockTagKey.get())) {
            return false;
        }
        return this.relativeBlockPos.getX() == compared.relativeBlockPos.getX() &&
                this.relativeBlockPos.getY() == compared.relativeBlockPos.getY() &&
                this.relativeBlockPos.getZ() == compared.relativeBlockPos.getZ();
    }

    /**
     * Defines the offset position for a block, in terms of a distance away from the position closest to the smallest coordinates out of the two positions chosen by the player
     */
    public BlockPos relativeBlockPos() {
        return this.relativeBlockPos;
    }

    public BlockState inputBlockState() {
        return this.blockState;
    }
}
