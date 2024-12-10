package dev.jeryn.anim.tardis;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.joml.Vector3f;
import whocraft.tardis_refined.TardisRefined;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static net.minecraft.client.animation.AnimationChannel.Interpolations.CATMULLROM;
import static net.minecraft.client.animation.AnimationChannel.Interpolations.LINEAR;
import static net.minecraft.client.animation.AnimationChannel.Targets.*;

/**
 * <h1>JsonToAnimationDefinition</h1>
 *
 * <p>This file magically allows me to load JSON based animations to a AnimationDefinition</p>
 * <p>It was a nightmare to get correct</p>
 * <p>All Rights Reserved</p>
 *
 * <h2>Credits</h2>
 * <ul>
 *   <li>Developed by: Jeryn</li>
 * </ul>
 *
 * @version 1.0
 * @since 2024-12-01
 */
public class JsonToAnimationDefinition {

    public static ModelPart findPart(HierarchicalModel hierarchicalModel, String string) {
        System.out.println(string + " " + hierarchicalModel.getClass());
        return hierarchicalModel.root().getAllParts().filter((modelPart) -> modelPart.hasChild(string)).findFirst().map((modelPart) -> modelPart.getChild(string)).get();
    }

    public static final AnimationChannel.Interpolation SNAP_TO = (destination, progress, keyFrames, startIndex, endIndex, scaleFactor) -> {
        Vector3f startVector = keyFrames[startIndex].target();
        Vector3f endVector = keyFrames[endIndex].target();
        return startVector.lerp(endVector, 0, destination).mul(scaleFactor);
    };


    public static AnimationDefinition loadAnimation(ResourceManager resourceManager, ResourceLocation resourceLocation) {

        JsonObject animationJson = loadJsonFromResource(resourceManager, resourceLocation);
        JsonArray animations = animationJson.getAsJsonArray("animations");

        if(animationJson == null || animations.isEmpty() || animations.isJsonNull()){
            TardisRefined.LOGGER.info("The specified animation '{}' does not exist?", resourceLocation);
        }

        float animationLength = animationJson.get("length").getAsFloat();

        AnimationDefinition.Builder animationDefinition = AnimationDefinition.Builder.withLength(animationLength);

        boolean looping = animationJson.get("looping").getAsBoolean();

        if(looping){
            animationDefinition.looping();
        }

        for (JsonElement boneEntry : animations.getAsJsonArray()) {
            JsonObject boneData = boneEntry.getAsJsonObject();

            List<Keyframe> rotationKeyframes = new ArrayList<>();
            List<Keyframe> positionKeyframes = new ArrayList<>();
            List<Keyframe> scaleKeyframes = new ArrayList<>();

            String boneName = boneData.get("bone").getAsString();

            if(boneData.get("target").getAsString().equals("rotation")){
                rotationKeyframes = parseKeyframes(boneData, ROTATION);
            }

            if(boneData.get("target").getAsString().equals("position")){
                positionKeyframes = parseKeyframes(boneData, POSITION);
            }

            if(boneData.get("target").getAsString().equals("scale")){
                scaleKeyframes = parseKeyframes(boneData, SCALE);
            }



            AnimationChannel positionChannel = positionKeyframes.isEmpty() ? null : new AnimationChannel(POSITION, positionKeyframes.toArray(new Keyframe[0]));
            AnimationChannel rotationChannel = rotationKeyframes.isEmpty() ? null : new AnimationChannel(ROTATION, rotationKeyframes.toArray(new Keyframe[0]));
            AnimationChannel scaleChannel = scaleKeyframes.isEmpty() ? null : new AnimationChannel(SCALE, scaleKeyframes.toArray(new Keyframe[0]));

            if (positionChannel != null) {
                animationDefinition.addAnimation(boneName, positionChannel);
            }
            if (rotationChannel != null) {
                animationDefinition.addAnimation(boneName, rotationChannel);
            }

            if (scaleChannel != null) {
                animationDefinition.addAnimation(boneName, scaleChannel);
            }


        }

        return animationDefinition.build();
    }

    private static List<Keyframe> parseKeyframes(JsonElement transformationData, AnimationChannel.Target targetType) {
        List<Keyframe> keyframes = new ArrayList<>();

        if(transformationData == null) return keyframes;

        JsonObject jsonObject = transformationData.getAsJsonObject();

        if (!jsonObject.has("keyframes") || !jsonObject.get("keyframes").isJsonArray()) {
            return keyframes;
        }

        JsonArray keyframesArray = jsonObject.getAsJsonArray("keyframes");
        for (JsonElement keyframeElement : keyframesArray) {
            if (!keyframeElement.isJsonObject()) continue;

            JsonObject keyframeObject = keyframeElement.getAsJsonObject();

            // Parse the individual keyframe details
            float timestamp = keyframeObject.has("timestamp") ? keyframeObject.get("timestamp").getAsFloat() : 0.0f;
            JsonArray targetArray = keyframeObject.has("target") ? keyframeObject.getAsJsonArray("target") : null;
            AnimationChannel.Interpolation interpolation = keyframeObject.has("interpolation") ? getInterpolation(keyframeObject.get("interpolation").getAsString()) : getInterpolation("linear");

            Vector3f vector3f;

            if (targetArray != null && targetArray.size() == 3) {
                vector3f = new Vector3f(
                        targetArray.get(0).getAsFloat(),
                        targetArray.get(1).getAsFloat(),
                        targetArray.get(2).getAsFloat()
                );
            } else {
                continue; // Skip this keyframe if target is invalid
            }


            // Create a new Keyframe object and add it to the list
            Keyframe keyframe = new Keyframe(timestamp, Objects.requireNonNull(targetToVector(targetType, vector3f)), interpolation);
            keyframes.add(keyframe);
        }
        // Log the total number of keyframes parsed
        TardisRefined.LOGGER.debug("({} + {}) Total keyframes parsed: {}", targetToString(targetType), jsonObject.get("bone").getAsString(),  keyframes.size());

        return keyframes;
    }


    private static String targetToString(AnimationChannel.Target  target){
        if(target == POSITION){
            return "Position";
        }

        if(target == ROTATION){
            return "Rotation";
        }

        if(target == SCALE){
            return "Scale";
        }

        return null; // We should never get here
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
        return switch (easingType) {
            case "linear" -> LINEAR;
            case "catmullrom" -> CATMULLROM;
            default -> SNAP_TO;
        };
    }



    public static JsonObject loadJsonFromResource(ResourceManager resourceManager, ResourceLocation resourceLocation) {
        try {
            TardisRefined.LOGGER.info("Loading Animation: {}", resourceLocation);
            InputStream inputStream = resourceManager.getResource(resourceLocation).get().open();
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}