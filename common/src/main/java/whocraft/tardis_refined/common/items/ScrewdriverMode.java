package whocraft.tardis_refined.common.items;

import com.mojang.serialization.Codec;

public enum ScrewdriverMode {
    ENABLED,
    DISABLED,
    DRAWING;

    public static final Codec<ScrewdriverMode> CODEC = Codec.STRING.xmap(
            ScrewdriverMode::valueOf,
            ScrewdriverMode::name
    );
}
