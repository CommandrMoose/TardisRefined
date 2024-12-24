package dev.jeryn.frame.tardis;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.*;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joml.Vector3f;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static net.minecraft.client.animation.AnimationChannel.Interpolations.*;
import static net.minecraft.client.animation.AnimationChannel.Targets.*;

/**
 * <h1>Frame</h1>
 *
 * <p>Allows loading JSON-based animations into an AnimationDefinition.</p>
 * <p>Developed by Jeryn</p>
 *
 * @version 2.1
 * @since 2024-12-01
 */
public class Frame {

    private static final Logger LOGGER = LogManager.getLogger("TardisRefined/FrameByJeryn");

    public static final AnimationChannel.Interpolation SNAP_TO = (destination, progress, keyFrames, startIndex, endIndex, scaleFactor) -> {
        Vector3f startVector = keyFrames[startIndex].target();
        Vector3f endVector = keyFrames[endIndex].target();
        return startVector.lerp(endVector, 0, destination).mul(scaleFactor);
    };

    public static AnimationDefinition loadAnimation(ResourceLocation resourceLocation) {
        return loadAnimation(Minecraft.getInstance().getResourceManager(), resourceLocation);
    }

    public static AnimationDefinition loadAnimation(ResourceManager resourceManager, ResourceLocation resourceLocation) {
        if(resourceManager == null) return null;
        JsonObject animationJson = loadJsonFromResource(resourceManager, resourceLocation);

        validateAnimationJson(animationJson, resourceLocation);

        float animationLength = animationJson.get("length").getAsFloat();
        boolean looping = animationJson.has("looping") && animationJson.get("looping").getAsBoolean();

        AnimationDefinition.Builder animationDefinition = AnimationDefinition.Builder.withLength(animationLength);
        if (looping) animationDefinition.looping();

        JsonArray animations = animationJson.getAsJsonArray("animations");
        for (JsonElement boneEntry : animations) {
            if (!boneEntry.isJsonObject()) {
                LOGGER.warn("Skipping invalid bone entry in animation: {}", resourceLocation);
                continue;
            }

            JsonObject boneData = boneEntry.getAsJsonObject();
            String boneName = boneData.get("bone").getAsString();
            String target = boneData.get("target").getAsString();

            List<Keyframe> keyframes = parseKeyframes(boneData, targetToAnimationTarget(target));
            if (keyframes.isEmpty()) continue;

            animationDefinition.addAnimation(
                    boneName,
                    new AnimationChannel(targetToAnimationTarget(target), keyframes.toArray(new Keyframe[0]))
            );
        }

        return animationDefinition.build();
    }

    private static void validateAnimationJson(JsonObject animationJson, ResourceLocation resourceLocation) {
        if (animationJson == null || !animationJson.has("animations") || animationJson.getAsJsonArray("animations").isEmpty()) {
            LOGGER.error("Invalid or missing animation JSON: {}", resourceLocation);
            throw new IllegalArgumentException("Invalid or missing animation JSON: " + resourceLocation);
        }
    }

    private static AnimationChannel.Target targetToAnimationTarget(String target) {
        return switch (target.toLowerCase()) {
            case "rotation" -> ROTATION;
            case "position" -> POSITION;
            case "scale" -> SCALE;
            default -> throw new IllegalArgumentException("Unknown animation target: " + target);
        };
    }

    private static List<Keyframe> parseKeyframes(JsonObject boneData, AnimationChannel.Target targetType) {
        if (!boneData.has("keyframes") || !boneData.get("keyframes").isJsonArray()) {
            return Collections.emptyList();
        }

        JsonArray keyframesArray = boneData.getAsJsonArray("keyframes");
        List<Keyframe> keyframes = new ArrayList<>(keyframesArray.size());

        for (JsonElement keyframeElement : keyframesArray) {
            if (!keyframeElement.isJsonObject()) continue;

            JsonObject keyframeObject = keyframeElement.getAsJsonObject();
            float timestamp = keyframeObject.has("timestamp") ? keyframeObject.get("timestamp").getAsFloat() : 0.0f;

            JsonArray targetArray = keyframeObject.getAsJsonArray("target");
            if (targetArray == null || targetArray.size() != 3) {
                LOGGER.warn("Invalid target array for keyframe at timestamp: {}", timestamp);
                continue;
            }

            Vector3f vector3f = new Vector3f(
                    targetArray.get(0).getAsFloat(),
                    targetArray.get(1).getAsFloat(),
                    targetArray.get(2).getAsFloat()
            );

            AnimationChannel.Interpolation interpolation = keyframeObject.has("interpolation")
                    ? getInterpolation(keyframeObject.get("interpolation").getAsString())
                    : LINEAR;

            keyframes.add(new Keyframe(
                    timestamp,
                    targetToVector(targetType, vector3f),
                    interpolation
            ));
        }

        LOGGER.debug("Parsed {} keyframes for bone: {}, target: {}", keyframes.size(), boneData.get("bone").getAsString(), targetToString(targetType));
        return keyframes;
    }

    /*Helper method for logging purposes*/
    private static String targetToString(AnimationChannel.Target target) {
        if (target.equals(POSITION)) {
            return "Position";
        } else if (target.equals(ROTATION)) {
            return "Rotation";
        } else if (target.equals(SCALE)) {
            return "Scale";
        }
        throw new IllegalArgumentException("Unexpected target: " + target); // This should never happen
    }

    private static Vector3f targetToVector(AnimationChannel.Target  target, Vector3f vector3f){
        if(target == POSITION){
            return KeyframeAnimations.posVec(vector3f.x, vector3f.y, vector3f.z);
        }

        if(target == ROTATION){
            return KeyframeAnimations.degreeVec(vector3f.x, vector3f.y, vector3f.z);
        }

        if(target == SCALE){
            return KeyframeAnimations.scaleVec(vector3f.x, vector3f.y, vector3f.z);
        }

        return null; // We should never get here
    }

    private static AnimationChannel.Interpolation getInterpolation(String easingType) {
        return switch (easingType.toLowerCase()) {
            case "linear" -> LINEAR;
            case "catmullrom" -> CATMULLROM;
            default -> SNAP_TO;
        };
    }

    public static JsonObject loadJsonFromResource(ResourceManager resourceManager, ResourceLocation resourceLocation) {
        LOGGER.info("Loading animation: {}", resourceLocation);

        return resourceManager.getResource(resourceLocation).map(resource -> {
            try (JsonReader reader = new JsonReader(new InputStreamReader(resource.open()))) {
                return JsonParser.parseReader(reader).getAsJsonObject();
            } catch (IOException e) {
                LOGGER.error("Error reading animation: {}", resourceLocation, e);
                throw new RuntimeException(e);
            }
        }).orElseThrow(() -> {
            LOGGER.error("Animation not found: {}", resourceLocation);
            return new IllegalArgumentException("Animation not found: " + resourceLocation);
        });
    }

    public static ModelPart findPart(HierarchicalModel<?> model, String partName) {
        return model.root()
                .getAllParts()
                .filter(part -> part.hasChild(partName))
                .findFirst()
                .map(part -> part.getChild(partName))
                .orElseThrow(() -> new IllegalArgumentException("Part not found: " + partName));
    }
}
