package whocraft.tardis_refined.common.tardis.manager;

import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.TRConfig;
import whocraft.tardis_refined.common.util.DimensionUtil;
import whocraft.tardis_refined.common.util.Platform;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProgressionManager extends BaseHandler {

    private ArrayList<ResourceKey<Level>> ALLOWED_LEVELS = new ArrayList<>();

    public ProgressionManager() {
        this.ALLOWED_LEVELS = new ArrayList<>();
    }

    public ArrayList<ResourceKey<Level>> getDiscoveredLevels() {

        if (!TRConfig.SERVER.ADVENTURE_MODE.get()) {
            return new ArrayList<>(DimensionUtil.getAllowedDimensions(Platform.getServer()));
        }

        for (String defaults : TRConfig.SERVER.ADVENTURE_MODE_DEFAULTS.get()) {
            ResourceKey<Level> level = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(defaults));
            addDiscoveredLevel(level);
        }

        return ALLOWED_LEVELS.stream()
                .filter(DimensionUtil::isAllowedDimension)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    public void setDiscoveredLevels(ArrayList<ResourceKey<Level>> allowedLevels) {
        if (allowedLevels != null) {
            this.ALLOWED_LEVELS = new ArrayList<>(allowedLevels);
        } else {
            this.ALLOWED_LEVELS.clear();
        }
    }

    public boolean addDiscoveredLevel(ResourceKey<Level> level) {
        if (level != null && !ALLOWED_LEVELS.contains(level) && DimensionUtil.isAllowedDimension(level)) {
            return ALLOWED_LEVELS.add(level);
        }
        return false;
    }

    public boolean undiscoverLevel(ResourceKey<Level> level) {
        return ALLOWED_LEVELS.remove(level);
    }

    public boolean isLevelDiscovered(ResourceKey<Level> level) {

        if(!TRConfig.SERVER.ADVENTURE_MODE.get()){
            return true;
        }

        return ALLOWED_LEVELS.contains(level);
    }


    @Override
    public CompoundTag saveData(CompoundTag tag) {
        ListTag levelsList = new ListTag();
        for (ResourceKey<Level> level : ALLOWED_LEVELS) {
            levelsList.add(StringTag.valueOf(level.toString()));
        }
        tag.put("DiscoveredLevels", levelsList);
        return tag;
    }

    @Override
    public void loadData(CompoundTag tag) {
        if (tag.contains("DiscoveredLevels")) {
            ListTag levelsList = tag.getList("DiscoveredLevels", Tag.TAG_STRING);
            ALLOWED_LEVELS.clear();
            for (Tag levelTag : levelsList) {
                String levelKey = levelTag.getAsString();
                ResourceKey<Level> level = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(levelKey));
                ALLOWED_LEVELS.add(level);
            }
        }
    }
}
