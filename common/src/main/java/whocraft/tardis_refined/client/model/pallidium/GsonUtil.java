package whocraft.tardis_refined.client.model.pallidium;

import com.google.gson.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * <h2>Credits</h2>
 * <ul>
 *   <li>Developed by: Lucraft</li>
 *   <li>Used with permission from Lucraft</li>
 * </ul>
 */
public class GsonUtil {

    public static int[] getIntArray(JsonObject jsonObject, int fields, String key) {
        if (!GsonHelper.isValidNode(jsonObject, key))
            throw new JsonSyntaxException("Missing " + key + ", expected to find a JsonArray");

        JsonArray jsonArray = GsonHelper.getAsJsonArray(jsonObject, key);

        if (jsonArray.size() != fields)
            throw new JsonParseException("Array " + key + " must have " + fields + " entries!");

        int[] array = new int[fields];

        for (int i = 0; i < jsonArray.size(); i++) {
            array[i] = jsonArray.get(i).getAsInt();
        }

        return array;
    }

    public static int[] getIntArray(JsonObject jsonObject, int fields, String key, int... fallback) {
        if (!GsonHelper.isValidNode(jsonObject, key))
            return fallback;
        return getIntArray(jsonObject, fields, key);
    }

    public static float[] getFloatArray(JsonObject jsonObject, int fields, String key) {
        if (!GsonHelper.isValidNode(jsonObject, key))
            throw new JsonSyntaxException("Missing " + key + ", expected to find a JsonArray");

        JsonArray jsonArray = GsonHelper.getAsJsonArray(jsonObject, key);

        if (jsonArray.size() != fields)
            throw new JsonParseException("Array " + key + " must have " + fields + " entries!");

        float[] array = new float[fields];

        for (int i = 0; i < jsonArray.size(); i++) {
            array[i] = jsonArray.get(i).getAsFloat();
        }

        return array;
    }

    public static float[] getFloatArray(JsonObject jsonObject, int fields, String key, float... fallback) {
        if (!GsonHelper.isValidNode(jsonObject, key))
            return fallback;
        return getFloatArray(jsonObject, fields, key);
    }

    public static ItemStack getAsItemStack(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            return readItemStack(json.get(memberName));
        } else {
            throw new JsonSyntaxException("Missing " + memberName + ", expected to find an itemstack");
        }
    }

    public static ItemStack getAsItemStack(JsonObject json, String memberName, @Nullable ItemStack fallback) {
        return json.has(memberName) ? getAsItemStack(json, memberName) : fallback;
    }

    public static ResourceLocation convertToResourceLocation(JsonElement json, String memberName) {
        if (json.isJsonPrimitive()) {
            return new ResourceLocation(json.getAsString());
        } else {
            throw new JsonSyntaxException("Expected " + memberName + " to be a resource location, was " + GsonHelper.getType(json));
        }
    }

    public static ResourceLocation getAsResourceLocation(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            return new ResourceLocation(GsonHelper.getAsString(json, memberName));
        } else {
            throw new JsonSyntaxException("Missing " + memberName + ", expected to find a resource location");
        }
    }


    @Environment(EnvType.CLIENT)
    public static ModelLayerLocation convertToModelLayerLocation(JsonElement json, String memberName) {
        if (json.isJsonPrimitive()) {
            String[] s = json.getAsString().split("#", 2);

            if (s.length == 1) {
                return new ModelLayerLocation(new ResourceLocation(s[0]), "main");
            } else {
                return new ModelLayerLocation(new ResourceLocation(s[0]), s[1]);
            }
        } else {
            throw new JsonSyntaxException("Expected " + memberName + " to be a model layer location, was " + GsonHelper.getType(json));
        }
    }

    @Environment(EnvType.CLIENT)
    public static ModelLayerLocation getAsModelLayerLocation(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            String[] s = GsonHelper.getAsString(json, memberName).split("#", 2);

            if (s.length == 1) {
                return new ModelLayerLocation(new ResourceLocation(s[0]), "main");
            } else {
                return new ModelLayerLocation(new ResourceLocation(s[0]), s[1]);
            }
        } else {
            throw new JsonSyntaxException("Missing " + memberName + ", expected to find a model layer location");
        }
    }

    @Environment(EnvType.CLIENT)
    public static ModelLayerLocation getAsModelLayerLocation(JsonObject json, String memberName, @Nullable ModelLayerLocation fallback) {
        return json.has(memberName) ? getAsModelLayerLocation(json, memberName) : fallback;
    }


    public static UUID getAsUUID(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            return UUID.fromString(GsonHelper.getAsString(json, memberName));
        } else {
            throw new JsonSyntaxException("Missing " + memberName + ", expected to find a UUID string");
        }
    }

    public static UUID getAsUUID(JsonObject json, String memberName, @Nullable UUID fallback) {
        return json.has(memberName) ? getAsUUID(json, memberName) : fallback;
    }

    public static int getAsIntRanged(JsonObject json, String memberName, int min, int max, int fallback) {
        if (!GsonHelper.isValidNode(json, memberName)) {
            return fallback;
        }
        return getAsIntRanged(json, memberName, min, max);
    }

    public static int getAsIntRanged(JsonObject json, String memberName, int min, int max) {
        int i = GsonHelper.getAsInt(json, memberName);

        if (i < min || i > max) {
            throw new JsonParseException("Expected " + memberName + " to be within bounds " + min + " ~ " + max);
        }

        return i;
    }

    public static int getAsIntMin(JsonObject json, String memberName, int min, int fallback) {
        if (!GsonHelper.isValidNode(json, memberName)) {
            return fallback;
        }
        return getAsIntMin(json, memberName, min);
    }

    public static int getAsIntMin(JsonObject json, String memberName, int min) {
        int i = GsonHelper.getAsInt(json, memberName);

        if (i < min) {
            throw new JsonParseException("Expected " + memberName + " to be greater than or equals " + min);
        }

        return i;
    }

    public static int getAsIntMax(JsonObject json, String memberName, int max, int fallback) {
        if (!GsonHelper.isValidNode(json, memberName)) {
            return fallback;
        }
        return getAsIntMax(json, memberName, max);
    }

    public static int getAsIntMax(JsonObject json, String memberName, int max) {
        int i = GsonHelper.getAsInt(json, memberName);

        if (i > max) {
            throw new JsonParseException("Expected " + memberName + " to be less then or equals " + max);
        }

        return i;
    }

    public static float getAsFloatRanged(JsonObject json, String memberName, float min, float max, float fallback) {
        if (!GsonHelper.isValidNode(json, memberName)) {
            return fallback;
        }
        return getAsFloatRanged(json, memberName, min, max);
    }

    public static float getAsFloatRanged(JsonObject json, String memberName, float min, float max) {
        float f = GsonHelper.getAsFloat(json, memberName);

        if (f < min || f > max) {
            throw new JsonParseException("Expected " + memberName + " to be within bounds " + min + " ~ " + max);
        }

        return f;
    }

    public static float getAsFloatMin(JsonObject json, String memberName, float min, float fallback) {
        if (!GsonHelper.isValidNode(json, memberName)) {
            return fallback;
        }
        return getAsFloatMin(json, memberName, min);
    }

    public static float getAsFloatMin(JsonObject json, String memberName, float min) {
        float f = GsonHelper.getAsFloat(json, memberName);

        if (f < min) {
            throw new JsonParseException("Expected " + memberName + " to be greater than or equals " + min);
        }

        return f;
    }

    public static float getAsFloatMax(JsonObject json, String memberName, float max, float fallback) {
        if (!GsonHelper.isValidNode(json, memberName)) {
            return fallback;
        }
        return getAsFloatMax(json, memberName, max);
    }

    public static float getAsFloatMax(JsonObject json, String memberName, float max) {
        float f = GsonHelper.getAsFloat(json, memberName);

        if (f > max) {
            throw new JsonParseException("Expected " + memberName + " to be less then or equals " + max);
        }

        return f;
    }

    public static Component getAsComponent(JsonObject json, String memberName) {
        if (GsonHelper.isValidNode(json, memberName)) {
            return Component.Serializer.fromJson(json.get(memberName));
        } else {
            throw new JsonSyntaxException("Missing " + memberName + ", expected to find a Text Component definition");
        }
    }

    public static Component getAsComponent(JsonObject json, String memberName, Component fallback) {
        if (!GsonHelper.isValidNode(json, memberName)) {
            return fallback;
        }
        return getAsComponent(json, memberName);
    }

    public static List<Component> getAsComponentList(JsonObject json, String memberName) {
        if (GsonHelper.isValidNode(json, memberName)) {
            if (json.get(memberName).isJsonPrimitive() || json.get(memberName).isJsonObject()) {
                return List.of(Objects.requireNonNull(Component.Serializer.fromJson(json.get(memberName))));
            }
            JsonArray array = GsonHelper.convertToJsonArray(json.get(memberName), memberName);
            List<Component> list = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                list.add(Component.Serializer.fromJson(array.get(i)));
            }
            return list;
        } else {
            throw new JsonSyntaxException("Missing " + memberName + ", expected to find a String, a JsonObject, or an array of Text Component definitions");
        }
    }

    public static List<Component> getAsComponentList(JsonObject json, String memberName, List<Component> fallback) {
        if (!GsonHelper.isValidNode(json, memberName)) {
            return fallback;
        } else {
            return getAsComponentList(json, memberName);
        }
    }

    public static Color getAsColor(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            var jsonElement = json.get(memberName);

            if (jsonElement.isJsonPrimitive()) {
                return Color.decode(jsonElement.getAsString());
            } else if (jsonElement.isJsonArray()) {
                JsonArray array = jsonElement.getAsJsonArray();
                if (array.size() == 3) {
                    return new Color(array.get(0).getAsInt(), array.get(1).getAsInt(), array.get(2).getAsInt());
                } else if (array.size() == 4) {
                    return new Color(array.get(0).getAsInt(), array.get(1).getAsInt(), array.get(2).getAsInt(), array.get(3).getAsInt());
                } else {
                    throw new JsonParseException("Color array must either have 3 (RGB) or 4 (RGBA) integers");
                }
            } else {
                throw new JsonParseException("Color must either be defined as RGB-string or array of integers");
            }
        } else {
            throw new JsonSyntaxException("Missing " + memberName + ", expected to find a color");
        }
    }

    public static Color getAsColor(JsonObject json, String memberName, @org.jetbrains.annotations.Nullable Color fallback) {
        return json.has(memberName) ? getAsColor(json, memberName) : fallback;
    }

    public static Object getAsRawColor(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            var jsonElement = json.get(memberName);

            if (jsonElement.isJsonPrimitive()) {
                return jsonElement.getAsString();
            } else if (jsonElement.isJsonArray()) {
                JsonArray array = jsonElement.getAsJsonArray();
                Function<JsonPrimitive, Object> parse = js -> {
                    if (js.isString()) {
                        return js.getAsString();
                    } else {
                        return js.getAsInt();
                    }
                };

                if (array.size() == 3) {
                    return new Object[]{parse.apply(array.get(0).getAsJsonPrimitive()), parse.apply(array.get(1).getAsJsonPrimitive()), parse.apply(array.get(2).getAsJsonPrimitive())};
                } else if (array.size() == 4) {
                    return new Object[]{parse.apply(array.get(0).getAsJsonPrimitive()), parse.apply(array.get(1).getAsJsonPrimitive()), parse.apply(array.get(2).getAsJsonPrimitive()), parse.apply(array.get(3).getAsJsonPrimitive())};
                } else {
                    throw new JsonParseException("Color array must either have 3 (RGB) or 4 (RGBA) integers");
                }
            } else {
                throw new JsonParseException("Color must either be defined as RGB-string or array of integers");
            }
        } else {
            throw new JsonSyntaxException("Missing " + memberName + ", expected to find a color");
        }
    }

    public static Object getAsRawColor(JsonObject json, String memberName, @org.jetbrains.annotations.Nullable Object fallback) {
        return json.has(memberName) ? getAsRawColor(json, memberName) : fallback;
    }


    public static Vector3f getAsVector3f(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            if (json.get(memberName).isJsonArray()) {
                JsonArray array = json.get(memberName).getAsJsonArray();

                if (array.size() != 3) {
                    throw new JsonSyntaxException(memberName + " must have 3 floats");
                }

                return new Vector3f(array.get(0).getAsFloat(), array.get(1).getAsFloat(), array.get(2).getAsFloat());
            } else {
                throw new JsonSyntaxException(memberName + " must be an array to represent a 3-dimensional vector");
            }
        } else {
            throw new JsonSyntaxException("Missing " + memberName + ", expected to find a vector");
        }
    }

    public static Vector3f getAsVector3f(JsonObject json, String memberName, Vector3f fallback) {
        return json.has(memberName) ? getAsVector3f(json, memberName) : fallback;
    }

    public static Vector3f convertToVector3f(JsonElement jsonElement, String memberName) {
        if(jsonElement.isJsonArray()) {
            JsonArray array = jsonElement.getAsJsonArray();

            if (array.size() != 3) {
                throw new JsonSyntaxException(memberName + " must have 3 floats");
            }

            return new Vector3f(array.get(0).getAsFloat(), array.get(1).getAsFloat(), array.get(2).getAsFloat());
        } else {
            throw new JsonSyntaxException(memberName + " must be an array to represent a 3-dimensional vector");
        }
    }

    public static Vec3 getAsVec3(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            if (json.get(memberName).isJsonArray()) {
                JsonArray array = json.get(memberName).getAsJsonArray();

                if (array.size() != 3) {
                    throw new JsonSyntaxException(memberName + " must have 3 doubles");
                }

                return new Vec3(array.get(0).getAsDouble(), array.get(1).getAsDouble(), array.get(2).getAsDouble());
            } else {
                throw new JsonSyntaxException(memberName + " must be an array to represent a 3-dimensional vector");
            }
        } else {
            throw new JsonSyntaxException("Missing " + memberName + ", expected to find a vector");
        }
    }

    public static Vec3 getAsVec3(JsonObject json, String memberName, Vec3 fallback) {
        return json.has(memberName) ? getAsVec3(json, memberName) : fallback;
    }

    public static float getAsBooleanFloat(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            if (json.get(memberName).isJsonPrimitive()) {
                var primitive = json.get(memberName).getAsJsonPrimitive();

                if (primitive.isBoolean()) {
                    return primitive.getAsBoolean() ? 1F : 0F;
                } else {
                    return primitive.getAsFloat();
                }
            } else {
                throw new JsonSyntaxException(memberName + " must be a boolean or float");
            }
        } else {
            throw new JsonSyntaxException("Missing " + memberName + ", expected to find a boolean or float");
        }
    }

    public static float getAsBooleanFloat(JsonObject json, String memberName, float fallback) {
        return json.has(memberName) ? getAsBooleanFloat(json, memberName) : fallback;
    }

    public static void ifHasKey(JsonObject json, String memberName, Consumer<JsonElement> consumer) {
        if (GsonHelper.isValidNode(json, memberName)) {
            consumer.accept(json.get(memberName));
        }
    }

    public static void ifHasObject(JsonObject json, String memberName, Consumer<JsonObject> consumer) {
        if (GsonHelper.isValidNode(json, memberName)) {
            consumer.accept(GsonHelper.getAsJsonObject(json, memberName));
        }
    }

    public static void ifHasArray(JsonObject json, String memberName, Consumer<JsonElement> consumer) {
        if (GsonHelper.isValidNode(json, memberName)) {
            JsonArray array = GsonHelper.getAsJsonArray(json, memberName);

            for (JsonElement jsonElement : array) {
                consumer.accept(jsonElement);
            }
        }
    }

    public static JsonObject merge(JsonObject json1, JsonObject json2) {
        JsonObject json = GsonHelper.parse(json1.toString()); // copy

        json2.entrySet().forEach((entry -> {
            if (!json.has(entry.getKey())) {
                json.add(entry.getKey(), entry.getValue());
            } else {
                if (json.get(entry.getKey()).isJsonPrimitive() && entry.getValue().isJsonPrimitive()) {
                    json.add(entry.getKey(), entry.getValue());
                } else if (json.get(entry.getKey()).isJsonArray() && entry.getValue().isJsonArray()) {
                    JsonArray jsonArray = json.get(entry.getKey()).getAsJsonArray();
                    JsonArray json2Array = entry.getValue().getAsJsonArray();
                    for (int i = 0; i < json2Array.size(); i++) {
                        jsonArray.add(json2Array.get(i));
                    }
                    json.add(entry.getKey(), jsonArray);
                } else if (json.get(entry.getKey()).isJsonObject() && entry.getValue().isJsonObject()) {
                    json.add(entry.getKey(), merge(json.get(entry.getKey()).getAsJsonObject(), entry.getValue().getAsJsonObject()));
                }
            }
        }));

        return json;
    }

    public static JsonElement nbtToJson(Tag nbt) {
        if (nbt instanceof NumericTag) {
            return new JsonPrimitive(((NumericTag) nbt).getAsNumber());
        } else if (nbt instanceof CollectionTag) {
            JsonArray jsonArray = new JsonArray();
            for (int i = 0; i < ((CollectionTag<?>) nbt).size(); i++) {
                jsonArray.add(nbtToJson(((CollectionTag<?>) nbt).get(i)));
            }
            return jsonArray;
        } else if (nbt instanceof StringTag) {
            return new JsonPrimitive(nbt.getAsString());
        } else if (nbt instanceof CompoundTag) {
            JsonObject jsonObject = new JsonObject();
            for (String key : ((CompoundTag) nbt).getAllKeys()) {
                jsonObject.add(key, nbtToJson(((CompoundTag) nbt).get(key)));
            }
            return jsonObject;
        } else {
            return new JsonObject();
        }
    }

    public static JsonObject serializeItemStack(ItemStack stack) {
        return serializeItemStack(stack, true);
    }

    public static JsonObject serializeItemStack(ItemStack stack, boolean writeNbt) {
        JsonObject json = new JsonObject();

        json.addProperty("item", BuiltInRegistries.ITEM.getKey(stack.getItem()).toString());
        json.addProperty("count", stack.getCount());

        if (writeNbt && stack.hasTag()) {
            json.add("nbt", nbtToJson(stack.getTag()));
        }

        return json;
    }

    public static ItemStack readItemStack(JsonElement jsonElement) {
        if (jsonElement.isJsonPrimitive()) {
            ResourceLocation id = new ResourceLocation(jsonElement.getAsString());

            if (!BuiltInRegistries.ITEM.containsKey(id)) {
                throw new JsonParseException("Unknown item '" + id + "'");
            }

            return new ItemStack(BuiltInRegistries.ITEM.get(id));
        } else if (jsonElement.isJsonObject()) {
            var json = jsonElement.getAsJsonObject();
            ResourceLocation id = new ResourceLocation(GsonHelper.getAsString(json, "item"));

            if (!BuiltInRegistries.ITEM.containsKey(id)) {
                throw new JsonParseException("Unknown item '" + id + "'");
            }

            Item item = BuiltInRegistries.ITEM.get(id);

            return new ItemStack(item, GsonHelper.getAsInt(json, "count", 1));
        } else {
            throw new JsonParseException("Item stack definition must either be a primitive or an object");
        }
    }

    public static void forEachInListOrPrimitive(JsonElement element, Consumer<JsonElement> consumer) {
        if (element.isJsonPrimitive() || element.isJsonObject()) {
            consumer.accept(element);
        } else if (element.isJsonArray()) {
            JsonArray array = element.getAsJsonArray();
            for (JsonElement child : array) {
                consumer.accept(child);
            }
        }
    }

    public static <T> List<T> fromListOrPrimitive(JsonElement element, Function<JsonElement, T> function) {
        if (element.isJsonPrimitive() || element.isJsonObject()) {
            return Collections.singletonList(function.apply(element));
        } else if (element.isJsonArray()) {
            List<T> list = new ArrayList<>();
            JsonArray array = element.getAsJsonArray();
            for (JsonElement child : array) {
                list.add(function.apply(child));
            }
            return list;
        }
        return Collections.emptyList();
    }

    public static <T> List<T> fromListOrPrimitive(JsonElement element, Function<JsonElement, T> function, @Nullable List<T> fallback) {
        if (element == null) {
            return fallback;
        } else {
            return fromListOrPrimitive(element, function);
        }
    }

}