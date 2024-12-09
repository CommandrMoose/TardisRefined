package whocraft.tardis_refined.client.renderer.vortex;

import net.minecraft.util.Mth;

import java.util.HashMap;
import java.util.Map;

public class VortexGradientTint {
    public static final VortexGradientTint BlueOrngGradient = new VortexGradientTint()
            .add(1f, 0, 46, 128)        // Deep Blue (Front)
            .add(0f, 8, 109, 196)       // Sky Blue (Center)
            .add(-0.5f, 193, 111, 20)   // Orange Yellow (Behind)
            .add(-1f, 234, 204, 77);    // Light Yellow (Behind)
    public static final VortexGradientTint MODERN_VORTEX = new VortexGradientTint()
            .add(-1.0f, 238, 164, 107)  // Light Orange (Behind)
            .add(-0.5f, 199, 92, 159)   // Purple (Behind)
            .add(0.0f, 238, 164, 107)   // Light Orange (Center)
            .add(0.5f, 199, 92, 159)    // Purple (Front)
            .add(1.0f, 238, 164, 107);  // Light Orange (Front)
    public static final VortexGradientTint PASTEL_GRADIENT = new VortexGradientTint()
            .add(1f, 223, 190, 223)     // Light Lavender (Front)
            .add(0.75f, 190, 210, 255)  // Light Blue (Front)
            .add(0.5f, 243, 209, 215)   // Pale Pink (Center)
            .add(0f, 247, 223, 209)     // Light Peach (Center)
            .add(-0.75f, 190, 210, 255) // Light Blue (Behind)
            .add(-0.5f, 228, 190, 207)  // Soft Pink (Behind)
            .add(-1f, 223, 190, 223);   // Light Lavender (Behind)
    public static final VortexGradientTint TWILIGHT_GLOW = new VortexGradientTint()
            .add(-1.0f, 62, 12, 94)     // Deep Purple (Behind)
            .add(-0.5f, 106, 27, 154)   // Rich Violet (Behind)
            .add(0.0f, 255, 87, 51)     // Sunset Orange (Center)
            .add(0.5f, 255, 195, 113)   // Soft Peach (Front)
            .add(1.0f, 255, 251, 186);  // Warm Light Yellow (Front)
    public static final VortexGradientTint AURORA_DREAMS = new VortexGradientTint()
            .add(-1.0f, 0, 51, 102)     // Midnight Blue (Behind)
            .add(-0.75f, 0, 153, 204)   // Ocean Blue (Behind)
            .add(0.0f, 51, 255, 153)    // Vibrant Green (Center)
            .add(0.5f, 204, 102, 255)   // Lilac (Front)
            .add(1.0f, 255, 255, 204);  // Pale Yellow (Front)
    public static final VortexGradientTint DESERT_MIRAGE = new VortexGradientTint()
            .add(-1.0f, 232, 90, 41)    // Burnt Orange (Behind)
            .add(-0.5f, 255, 160, 41)   // Golden Yellow (Behind)
            .add(0.0f, 248, 196, 113)   // Sand (Center)
            .add(0.5f, 156, 102, 31)    // Earthy Brown (Front)
            .add(1.0f, 89, 58, 28);     // Deep Mocha (Front)
    public static final VortexGradientTint NEON_PULSE = new VortexGradientTint()
            .add(-1.0f, 0, 255, 127)    // Electric Green (Behind)
            .add(-0.5f, 0, 127, 255)    // Neon Blue (Behind)
            .add(0.0f, 255, 0, 255)     // Hot Pink (Center)
            .add(0.5f, 255, 255, 0)     // Neon Yellow (Front)
            .add(1.0f, 255, 0, 127);    // Magenta (Front)
    public static final VortexGradientTint OCEAN_BREEZE = new VortexGradientTint()
            .add(-1.0f, 0, 105, 148)    // Deep Blue (Behind)
            .add(-0.75f, 3, 169, 244)   // Sky Blue (Behind)
            .add(0.0f, 144, 224, 239)   // Aqua (Center)
            .add(0.5f, 205, 237, 247)   // Pale Blue (Front)
            .add(1.0f, 255, 255, 255);  // White (Front)
    public static final VortexGradientTint SOLAR_FLARE = new VortexGradientTint()
            .add(-1.0f, 255, 69, 0)     // Red-Orange (Behind)
            .add(-0.5f, 255, 140, 0)    // Dark Orange (Behind)
            .add(0.0f, 255, 215, 0)     // Gold (Center)
            .add(0.5f, 255, 69, 0)      // Red-Orange (Front)
            .add(1.0f, 255, 140, 0);    // Dark Orange (Front)
    public static final VortexGradientTint MELROSIAN_WHITE = new VortexGradientTint()
            .add(-1.0f, 139, 0, 0)          // Dark Red (Behind)
            .add(-0.75f, 179, 18, 18)       // Crimson (Behind)
            .add(-0.5f, 215, 53, 53)        // Red (Behind)
            .add(0.0f, 247, 139, 139)       // Light Red (Center)
            .add(0.5f, 255, 182, 182)       // Pale Red (Front)
            .add(1.0f, 255, 229, 229);      // Very Light Red (Front)
    public static final VortexGradientTint GAY_FLAG = new VortexGradientTint()
            .add(-1.0f, 0, 128, 0)        // Dark Green (Behind)
            .add(-0.75f, 0, 204, 102)     // Lighter Green (Behind)
            .add(-0.5f, 144, 238, 144)    // Lighter Lighter Green (Behind)
            .add(-0.25f, 128, 0, 255)     // Purple (Behind)
            .add(0.0f, 173, 216, 230)    // Lighter Blue (Center)
            .add(0.25f, 128, 0, 255)     // Purple (Front)
            .add(0.5f, 144, 238, 144)    // Lighter Lighter Green (Front)
            .add(0.75f, 0, 204, 102)     // Lighter Green (Front)
            .add(1.0f, 0, 128, 0);       // Dark Green (Front)
    public static final VortexGradientTint CRYSTAL_LAGOON = new VortexGradientTint()
            .add(-1.0f, 0, 102, 204)    // Deep Blue (Behind)
            .add(-0.5f, 51, 153, 255)   // Sky Blue (Behind)
            .add(0.0f, 102, 204, 255)   // Aqua (Center)
            .add(0.5f, 153, 255, 204)   // Pale Mint (Front)
            .add(1.0f, 204, 255, 229);  // Soft Green (Front)
    public static final VortexGradientTint VELVET_NIGHT = new VortexGradientTint()
            .add(-1.0f, 25, 25, 112)    // Midnight Blue (Behind)
            .add(-0.5f, 72, 61, 139)    // Dark Slate Blue (Behind)
            .add(0.0f, 123, 104, 238)   // Medium Slate Blue (Center)
            .add(0.5f, 106, 90, 205)    // Slate Blue (Front)
            .add(1.0f, 25, 25, 112);    // Midnight Blue (Front)
    public static final VortexGradientTint CANDY_POP = new VortexGradientTint()
            .add(-1.0f, 255, 182, 193)  // Light Pink (Behind)
            .add(-0.5f, 255, 105, 180)  // Hot Pink (Behind)
            .add(0.0f, 238, 130, 238)   // Violet (Center)
            .add(0.5f, 147, 112, 219)   // Medium Purple (Front)
            .add(1.0f, 255, 182, 193);  // Light Pink (Front)
    public static final VortexGradientTint EMERALD_FOREST = new VortexGradientTint()
            .add(-1.0f, 34, 139, 34)    // Forest Green (Behind)
            .add(-0.5f, 46, 139, 87)    // Sea Green (Behind)
            .add(0.0f, 60, 179, 113)    // Medium Sea Green (Center)
            .add(0.5f, 143, 188, 143)   // Dark Sea Green (Front)
            .add(1.0f, 34, 139, 34);    // Forest Green (Front)
    public static final VortexGradientTint LGBT_RAINBOW = new VortexGradientTint()
            .add(-1.0f, 255, 0, 0)          // Red (Behind)
            .add(-0.75f, 255, 127, 0)       // Orange (Behind)
            .add(-0.5f, 255, 255, 0)        // Yellow (Behind)
            .add(-0.25f, 0, 255, 0)         // Green (Behind)
            .add(0.0f, 0, 0, 255)           // Blue (Center)
            .add(0.25f, 75, 0, 130)         // Indigo (Front)
            .add(0.5f, 255, 0, 255)         // Violet (Front)
            .add(0.75f, 255, 127, 0)        // Orange (Front)
            .add(1.0f, 255, 0, 0);          // Red (Front)
    public static final VortexGradientTint TRANSGENDER_FLAG = new VortexGradientTint()
            .add(-1.0f, 0, 190, 255)        // Light Blue (Behind)
            .add(-0.75f, 255, 105, 180)     // Light Pink (Behind)
            .add(-0.5f, 255, 255, 255)      // White (Behind)
            .add(0.0f, 255, 105, 180)       // Light Pink (Center)
            .add(0.5f, 0, 190, 255)         // Light Blue (Front)
            .add(1.0f, 255, 105, 180);      // Light Pink (Front)
    public static final VortexGradientTint BISEXUAL_FLAG = new VortexGradientTint()
            .add(-1.0f, 204, 0, 204)        // Purple (Behind)
            .add(-0.5f, 255, 0, 0)          // Red (Behind)
            .add(0.0f, 255, 105, 180)       // Pink (Center)
            .add(0.5f, 0, 0, 255)           // Blue (Front)
            .add(1.0f, 204, 0, 204);        // Purple (Front)
    public static final VortexGradientTint LESBIAN_FLAG = new VortexGradientTint()
            .add(-1.0f, 255, 56, 85)        // Dark Pink (Behind)
            .add(-0.75f, 255, 121, 156)     // Pink (Behind)
            .add(-0.5f, 255, 177, 210)      // Light Pink (Behind)
            .add(-0.25f, 255, 233, 255)     // Light Yellow (Behind)
            .add(0.0f, 255, 233, 255)       // Light Yellow (Center)
            .add(0.25f, 255, 177, 210)      // Light Pink (Front)
            .add(0.5f, 255, 121, 156)       // Pink (Front)
            .add(0.75f, 255, 56, 85)        // Dark Pink (Front)
            .add(1.0f, 255, 56, 85);        // Dark Pink (Front)
    public static final VortexGradientTint NON_BINARY_FLAG = new VortexGradientTint()
            .add(-1.0f, 255, 225, 0)        // Yellow (Behind)
            .add(-0.75f, 204, 204, 204)     // Gray (Behind)
            .add(-0.5f, 0, 0, 0)            // Black (Behind)
            .add(0.0f, 255, 225, 0)         // Yellow (Center)
            .add(0.5f, 204, 204, 204)       // Gray (Front)
            .add(1.0f, 0, 0, 0);            // Black (Front)
    public static final VortexGradientTint AGENDER_FLAG = new VortexGradientTint()
            .add(-1.0f, 0, 0, 0)            // Black (Behind)
            .add(-0.5f, 255, 255, 255)      // White (Behind)
            .add(0.0f, 0, 255, 255)         // Cyan (Center)
            .add(0.5f, 255, 255, 255)       // White (Front)
            .add(1.0f, 0, 0, 0);            // Black (Front)
    private final Map<Float, float[]> gradient_map = new HashMap<>();
    public float offset = 0;


    public VortexGradientTint() {
    }

    /**
     * Adds a color to the gradient map
     *
     * @param pos position in the gradient the color should go in. can be from -1 to 1
     * @param r   RED 0 to 1
     * @param g   GREEN 0 to 1
     * @param b   BLUE 0 to 1
     * @return The VortexGradient with the color added
     */
    public VortexGradientTint add(float pos, float r, float g, float b) {
        this.gradient_map.put(pos, new float[]{r, g, b});
        return this;
    }

    public VortexGradientTint add(float pos, int r, int g, int b) {
        return add(pos, r / 255.0f, g / 255.0f, b / 255.0f);
    }

    public float[] getRGBf(float pos_original) {
        float r = 1, g = 1, b = 1;
        float[] out = new float[]{r, g, b};
        if (gradient_map.isEmpty()) return out;
        if (gradient_map.size() == 1) {
            for (float p : gradient_map.keySet()) {
                out = gradient_map.get(p);
            }
            return out;
        }

        float pos = pos_original + offset;
        while (pos > 1) pos -= 2;
        while (pos < -1) pos += 2;

        float first = 0, second = 0, smallest_dist = 9999, second_smallest_dist = 1000;

        for (float p : gradient_map.keySet()) {
            float dist = Mth.abs(pos - p);
            if (dist < smallest_dist) {
                second_smallest_dist = smallest_dist;
                smallest_dist = dist;
                second = first;
                first = p;
            }
        }

        if (gradient_map.get(first) == null || gradient_map.get(second) == null) return out;

        r = Mth.lerp(smallest_dist / (smallest_dist + second_smallest_dist), gradient_map.get(first)[0], gradient_map.get(second)[0]);
        g = Mth.lerp(smallest_dist / (smallest_dist + second_smallest_dist), gradient_map.get(first)[1], gradient_map.get(second)[1]);
        b = Mth.lerp(smallest_dist / (smallest_dist + second_smallest_dist), gradient_map.get(first)[2], gradient_map.get(second)[2]);

        return new float[]{r, g, b};
    }
}