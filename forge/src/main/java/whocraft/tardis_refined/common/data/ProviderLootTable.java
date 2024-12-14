package whocraft.tardis_refined.common.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.registry.RegistryHolder;
import whocraft.tardis_refined.registry.TRBlockRegistry;
import whocraft.tardis_refined.registry.TREntityRegistry;
import whocraft.tardis_refined.registry.TRItemRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProviderLootTable extends LootTableProvider {


    public ProviderLootTable(PackOutput arg, Set<ResourceKey<LootTable>> set, List<SubProviderEntry> list, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(arg, set, list, completableFuture);
    }

    @Override
    protected void validate(WritableRegistry<LootTable> writableregistry, ValidationContext validationcontext, ProblemReporter.Collector problemreporter$collector) {

    }

    public static class ModBlockLoot extends BlockLootSubProvider {

        public ModBlockLoot(HolderLookup.Provider arg2) {
            super(Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.PIGLIN_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(Collectors.toSet()), FeatureFlags.REGISTRY.allFlags(), arg2);
        }

        @Override
        protected void generate() {
            for (Block block : getKnownBlocks()) {
                dropSelf(block);
            }

            this.add(TRBlockRegistry.ZEITON_ORE.get(), (block) -> createOreDrop(block, TRItemRegistry.RAW_ZEITON.get()));
            this.add(TRBlockRegistry.ZEITON_ORE_DEEPSLATE.get(), (block) -> createOreDrop(block, TRItemRegistry.RAW_ZEITON.get()));
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            ArrayList<@NotNull Block> blocks = new ArrayList<>();
            for (RegistryHolder<Block, ? extends Block> entry : TRBlockRegistry.BLOCKS.getEntries()) {
                ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(entry.get());
                if (!blockId.toString().contains("minecraft")) {
                    blocks.add(entry.get());
                }
            }
            return blocks;
        }
    }

}