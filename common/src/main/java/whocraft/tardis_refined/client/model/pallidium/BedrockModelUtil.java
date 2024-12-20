package whocraft.tardis_refined.client.model.pallidium;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.core.Direction;
import net.minecraft.util.GsonHelper;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.*;

import static whocraft.tardis_refined.client.model.blockentity.shell.ShellModel.addMaterializationPart;

/**
 * <h2>Credits</h2>
 * <ul>
 *   <li>Developed by: Lucraft</li>
 *   <li>Used with permission from Lucraft</li>
 * </ul>
 */
public class BedrockModelUtil {

    public static LayerDefinition parseAsLayerDefinition(JsonObject json) {
        var geometry = GsonHelper.getAsJsonArray(json, "minecraft:geometry");

        if (geometry.isEmpty()) {
            throw new JsonParseException("Empty geometry!");
        }

        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition root = meshDefinition.getRoot();

        addMaterializationPart(root);


        var geo = GsonHelper.convertToJsonObject(geometry.get(0), "minecraft:geometry[].$");
        var description = GsonHelper.getAsJsonObject(geo, "description", new JsonObject());
        int textureWidth = GsonHelper.getAsInt(description, "texture_width");
        int textureHeight = GsonHelper.getAsInt(description, "texture_height");

        var bones = GsonHelper.getAsJsonArray(geo, "bones", new JsonArray());
        Map<String, BedrockModelPartCache> cache = new HashMap<>();
        Map<String, PartDefinition> modelParts = new HashMap<>();

        for (JsonElement b : bones) {
            var bone = GsonHelper.convertToJsonObject(b, "minecraft:geomeotry[].bones.$");
            var name = GsonHelper.getAsString(bone, "name");
            var parent = GsonHelper.getAsString(bone, "parent", null);
            var pivot = GsonUtil.getFloatArray(bone, 3, "pivot", 0, 0, 0);
            var rotation = GsonUtil.getFloatArray(bone, 3, "rotation", 0, 0, 0);
            var cubesJson = GsonHelper.getAsJsonArray(bone, "cubes", new JsonArray());
            List<BedrockModelCube> cubes = new ArrayList<>();

            for (JsonElement cj : cubesJson) {
                var cubeJson = GsonHelper.convertToJsonObject(cj, "minecraft:geomeotry[].bones[].cubes[].$");
                var origin = GsonUtil.getFloatArray(cubeJson, 3, "origin");
                var size = GsonUtil.getFloatArray(cubeJson, 3, "size");
                var inflate = GsonHelper.getAsFloat(cubeJson, "inflate", 0);
                var uvJson = cubeJson.get("uv");
                var mirror = GsonHelper.getAsBoolean(cubeJson, "mirror", false);

                if (uvJson.isJsonArray()) {
                    var uv = GsonUtil.getIntArray(cubeJson, 2, "uv", 0, 0);
                    cubes.add(new BedrockModelCube(new Vector3f(origin[0], origin[1], origin[2]),
                            new Vector3f(size[0], size[1], size[2]),
                            inflate, mirror, new UVPair(uv[0], uv[1])));
                } else {
                    var uvs = GsonHelper.convertToJsonObject(uvJson, "minecraft:geomeotry[].bones[].cubes[].uv");
                    Map<Direction, ExtendedCubeListBuilder.PerFaceUV> uvMap = new HashMap<>();
                    for (Direction direction : Direction.values()) {
                        if (GsonHelper.isValidNode(uvs, direction.getName())) {
                            var directionJson = GsonHelper.getAsJsonObject(uvs, direction.getName());
                            var uv = GsonUtil.getIntArray(directionJson, 2, "uv", 0, 0);
                            var uvSize = GsonUtil.getIntArray(directionJson, 2, "uv_size", 0, 0);
                            uvMap.put(direction.getAxis() == Direction.Axis.Z ? direction : direction.getOpposite(), new ExtendedCubeListBuilder.PerFaceUV(new UVPair(uv[0], uv[1]), new UVPair(uvSize[0], uvSize[1])));
                        }
                    }
                    cubes.add(new BedrockModelCube(new Vector3f(origin[0], origin[1], origin[2]),
                            new Vector3f(size[0], size[1], size[2]),
                            inflate, mirror, uvMap));
                }
            }

            cache.put(name, new BedrockModelPartCache(name, parent, new Vector3f(pivot[0], pivot[1], pivot[2]),
                    new Vector3f((float) Math.toRadians(rotation[0]), (float) Math.toRadians(rotation[1]), (float) Math.toRadians(rotation[2])), cubes));
        }

        // Validate parents
        for (Map.Entry<String, BedrockModelPartCache> e : cache.entrySet()) {
            if (e.getValue().parentName != null) {
                if (!cache.containsKey(e.getValue().parentName)) {
                    throw new JsonParseException("Unknown parent '" + e.getValue().parentName + "'");
                } else {
                    e.getValue().parent = cache.get(e.getValue().parentName);
                    cache.get(e.getValue().parentName).children.add(e.getValue());
                }
            }
        }

        // Organize hierarchy
        while (!cache.isEmpty()) {
            var copy = new HashMap<>(cache);

            for (Map.Entry<String, BedrockModelPartCache> e : copy.entrySet()) {
                if (e.getValue().parentName == null) {
                    convertHierarchy(e.getValue(), "");
                    modelParts.put(e.getKey(), e.getValue().add(root));
                    cache.remove(e.getKey());
                } else {
                    var parent = modelParts.get(e.getValue().parentName);

                    if (parent != null) {
                        modelParts.put(e.getKey(), e.getValue().add(parent));
                        cache.remove(e.getKey());
                    }
                }
            }
        }

        return LayerDefinition.create(meshDefinition, textureWidth, textureHeight);
    }

    private static void convertHierarchy(BedrockModelPartCache part, String prefix) {
        part.convert();
        for (BedrockModelPartCache child : part.children) {
            convertHierarchy(child, prefix + "  ");
        }
    }

    public static JsonObject toJsonModel(LayerDefinition layerDefinition, @Nullable String identifier) {
        JsonObject json = new JsonObject();
        json.addProperty("format_version", "1.12.0");
        var geometryArray = new JsonArray();
        var geometry = new JsonObject();

        // Description
        var description = new JsonObject();
        description.addProperty("identifier", identifier != null ? identifier : "geometry.unknown");
        description.addProperty("texture_width", layerDefinition.material.xTexSize);
        description.addProperty("texture_height", layerDefinition.material.yTexSize);
        geometry.add("description", description);

        // Bones
        var bones = new JsonArray();
        layerDefinition.mesh.getRoot().children.forEach((s, part) -> addBoneToArray(bones, s, part, new ArrayList<>(), null));
        geometry.add("bones", bones);

        geometryArray.add(geometry);
        json.add("minecraft:geometry", geometryArray);
        return json;
    }

    private static void addBoneToArray(JsonArray array, String name, PartDefinition part, List<PartDefinition> parents, @Nullable String parent) {
        var json = new JsonObject();

        json.addProperty("name", name);
        if (parent != null) {
            json.addProperty("parent", parent);
        }
        var fixedPivot = new Vector3f(part.partPose.x, part.partPose.y, part.partPose.z);
        for (PartDefinition parentPart : parents) {
            fixedPivot.add(parentPart.partPose.x, parentPart.partPose.y, parentPart.partPose.z);
        }
        fixedPivot.sub(0, 24, 0).mul(1, -1, 1);
        json.add("pivot", vec3ToJsonArray(fixedPivot));
        json.add("rotation", vec3ToJsonArray(Math.toDegrees(part.partPose.xRot), Math.toDegrees(part.partPose.yRot), Math.toDegrees(part.partPose.zRot)));

        var cubes = new JsonArray();

        for (CubeDefinition c : part.cubes) {
            var cube = new JsonObject();
            var fixedOrigin = new Vector3f().set(c.origin).add(fixedPivot.x, 0, fixedPivot.z);
            fixedOrigin.y = (fixedOrigin.y + c.dimensions.y - fixedPivot.y) * -1;
            cube.add("origin", vec3ToJsonArray(fixedOrigin));
            cube.add("size", vec3ToJsonArray(c.dimensions));
            cube.addProperty("inflate", (c.grow.growX + c.grow.growY + c.grow.growZ) / 3D);

            if (c instanceof ExtendedCubeListBuilder.PerFaceCubeDefinition perFace) {
                var uv = new JsonObject();
                for (Map.Entry<Direction, ExtendedCubeListBuilder.PerFaceUV> e : perFace.uvMap.entrySet()) {
                    var face = new JsonObject();
                    var direction = e.getKey();
                    face.add("uv", uvPairToJsonArray(e.getValue().uv()));
                    face.add("uv_size", uvPairToJsonArray(e.getValue().size()));
                    uv.add(direction.getAxis() == Direction.Axis.Z ? direction.getName() : direction.getOpposite().getName(), face);
                }
                cube.add("uv", uv);
            } else {
                cube.add("uv", uvPairToJsonArray(c.texCoord));
            }

            cube.addProperty("mirror", c.mirror);
            cubes.add(cube);
        }

        json.add("cubes", cubes);
        array.add(json);
        List<PartDefinition> newParents = new ArrayList<>(parents);
        newParents.add(part);
        part.children.forEach((n, p) -> addBoneToArray(array, n, p, newParents, name));
    }

    private static JsonArray vec3ToJsonArray(Vector3f vec) {
        var array = new JsonArray();
        array.add(vec.x);
        array.add(vec.y);
        array.add(vec.z);
        return array;
    }

    private static JsonArray vec3ToJsonArray(double x, double y, double z) {
        var array = new JsonArray();
        array.add(x);
        array.add(y);
        array.add(z);
        return array;
    }

    private static JsonArray uvPairToJsonArray(UVPair uv) {
        var array = new JsonArray();
        array.add(uv.u());
        array.add(uv.v());
        return array;
    }

    public static class BedrockModelPartCache {

        private final String name;
        @Nullable
        public final String parentName;
        public BedrockModelPartCache parent;
        public List<BedrockModelPartCache> children = new ArrayList<>();
        private final Vector3f unconvertedPivot;
        private final Vector3f pivot;
        private final Vector3f rotation;
        private final List<BedrockModelCube> cubes;

        public BedrockModelPartCache(String name, @Nullable String parentName, Vector3f pivot, Vector3f rotation, List<BedrockModelCube> cubes) {
            this.name = name;
            this.parentName = parentName;
            this.unconvertedPivot = pivot;
            this.pivot = pivot;
            this.rotation = rotation;
            this.cubes = cubes;
        }

        public PartDefinition add(PartDefinition parent) {
            var builder = ExtendedCubeListBuilder.create();

            for (BedrockModelCube cube : this.cubes) {
                cube.add(builder);
            }

            return parent.addOrReplaceChild(this.name, builder,
                    PartPose.offsetAndRotation(
                            this.pivot.x, this.pivot.y, this.pivot.z,
                            this.rotation.x, this.rotation.y, this.rotation.z));
        }

        public BedrockModelPartCache convert() {
            this.cubes.forEach(c -> c.convert(this.pivot, this.name));
            this.pivot.mul(1, -1, 1).add(0, 24, 0);
            var parent = this.parent;

            while (parent != null) {
                this.pivot.sub(parent.unconvertedPivot);
                parent = parent.parent;
            }

            return this;
        }
    }

    public static class BedrockModelCube {

        private final Vector3f origin;
        private final Vector3f size;
        private final float inflate;
        private final UVPair uv;
        private final Map<Direction, ExtendedCubeListBuilder.PerFaceUV> uvMap;
        private final boolean mirror;

        public BedrockModelCube(Vector3f origin, Vector3f size, float inflate, boolean mirror, UVPair uv) {
            this.origin = origin;
            this.size = size;
            this.inflate = inflate;
            this.mirror = mirror;
            this.uv = uv;
            this.uvMap = null;
        }

        public BedrockModelCube(Vector3f origin, Vector3f size, float inflate, boolean mirror, Map<Direction, ExtendedCubeListBuilder.PerFaceUV> uvMap) {
            this.origin = origin;
            this.size = size;
            this.inflate = inflate;
            this.mirror = mirror;
            this.uv = null;
            this.uvMap = uvMap;
        }

        public void add(ExtendedCubeListBuilder builder) {
            if (this.uv != null) {
                builder.mirror(this.mirror).texOffs((int) this.uv.u(), (int) this.uv.v()).addBox(
                        this.origin.x, this.origin.y, this.origin.z,
                        this.size.x, this.size.y, this.size.z,
                        new CubeDeformation(this.inflate));
            } else {
                var cubeBuilder = builder.addPerFaceUVCube().origin(this.origin).dimensions(this.size).grow(this.inflate);
                for (Map.Entry<Direction, ExtendedCubeListBuilder.PerFaceUV> e : Objects.requireNonNull(this.uvMap).entrySet()) {
                    cubeBuilder.addFace(e.getKey(), e.getValue().uv(), e.getValue().size());
                }
                cubeBuilder.build();
            }
        }

        public void convert(Vector3f pivot, String name) {
            this.origin.sub(pivot.x, 0, pivot.z);
            this.origin.y = pivot.y - this.origin.y - this.size.y;
        }
    }

}