package whocraft.tardis_refined.common.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.registry.TRBlockRegistry;
import whocraft.tardis_refined.registry.TREntityRegistry;
import whocraft.tardis_refined.registry.TRItemRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class ProviderLootTable extends LootTableProvider {

    public ProviderLootTable(PackOutput arg) {
        // super(arg, Set.of(), List.of(new LootTableProvider.SubProviderEntry(ModBlockLoot::new, LootContextParamSets.BLOCK), new LootTableProvider.SubProviderEntry(ModEntityLoot::new, LootContextParamSets.ENTITY)));
        super(arg, Set.of(), List.of(new LootTableProvider.SubProviderEntry(ModBlockLoot::new, LootContextParamSets.BLOCK)));
    }

    public static class ModBlockLoot extends BlockLootSubProvider {
        protected ModBlockLoot() {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags());
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
            for (Block entry : TRBlockRegistry.BLOCKS.getRegistry().get().stream().toList()) {
                ResourceLocation blockId = TRBlockRegistry.BLOCKS.getRegistry().get().getKey(entry);
                if (!blockId.toString().contains("minecraft")) {
                    blocks.add(entry);
                }
            }
            return blocks;
        }
    }

    public static class ModEntityLoot extends EntityLootSubProvider {

        protected ModEntityLoot() {
            super(FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected Stream<EntityType<?>> getKnownEntityTypes() {
            ArrayList<@NotNull EntityType<?>> entities = new ArrayList<>();
            for (EntityType<?> entry : TREntityRegistry.ENTITY_TYPES.getRegistry().get().stream().toList()) {
                if (entry == TREntityRegistry.CONTROL_ENTITY.get())
                    break;
                entities.add(entry);
            }
            return entities.stream();
        }

        @Override
        public void generate() {
            getKnownEntityTypes().forEach(entityType -> {
                add(entityType, new LootTable.Builder());
            });
        }
    }
}