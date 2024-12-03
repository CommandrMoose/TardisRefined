package whocraft.tardis_refined.client.model.pallidium;

import com.google.common.base.Preconditions;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDefinition;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.UVPair;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <h2>Credits</h2>
 * <ul>
 *   <li>Developed by: Lucraft</li>
 *   <li>Used with permission from Lucraft</li>
 * </ul>
 */
public class ExtendedCubeListBuilder extends CubeListBuilder {

    public static @NotNull ExtendedCubeListBuilder create() {
        return new ExtendedCubeListBuilder();
    }

    public PerFaceCubeBuilder addPerFaceUVCube() {
        return new PerFaceCubeBuilder(this);
    }

    public static class PerFaceCubeBuilder {

        private final ExtendedCubeListBuilder parentBuilder;
        @Nullable
        public String comment;
        public Vector3f origin;
        public Vector3f dimensions;
        public CubeDeformation grow = CubeDeformation.NONE;
        public boolean mirror = false;
        public UVPair texScale = new UVPair(1, 1);
        private final Map<Direction, PerFaceUV> uvMap = new HashMap<>();

        public PerFaceCubeBuilder(ExtendedCubeListBuilder parentBuilder) {
            this.parentBuilder = parentBuilder;
        }

        public PerFaceCubeBuilder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public PerFaceCubeBuilder origin(Vector3f origin) {
            this.origin = origin;
            return this;
        }

        public PerFaceCubeBuilder origin(float x, float y, float z) {
            this.origin = new Vector3f(x, y, z);
            return this;
        }

        public PerFaceCubeBuilder dimensions(Vector3f dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public PerFaceCubeBuilder dimensions(float x, float y, float z) {
            this.dimensions = new Vector3f(x, y, z);
            return this;
        }

        public PerFaceCubeBuilder grow(CubeDeformation deformation) {
            this.grow = deformation;
            return this;
        }

        public PerFaceCubeBuilder grow(float grow) {
            this.grow = new CubeDeformation(grow);
            return this;
        }

        public PerFaceCubeBuilder mirror() {
            this.mirror = true;
            return this;
        }

        public PerFaceCubeBuilder mirror(boolean mirror) {
            this.mirror = mirror;
            return this;
        }

        public PerFaceCubeBuilder texScale(float uScale, float vScale) {
            this.texScale = new UVPair(uScale, vScale);
            return this;
        }

        public PerFaceCubeBuilder addFace(Direction direction, UVPair uv, UVPair uvSize) {
            this.uvMap.put(direction, new PerFaceUV(uv, uvSize));
            return this;
        }

        public ExtendedCubeListBuilder build() {
            Preconditions.checkNotNull(this.origin);
            Preconditions.checkNotNull(this.dimensions);
            this.parentBuilder.cubes.add(new PerFaceCubeDefinition(
                    this.comment,
                    this.origin.x, this.origin.y, this.origin.z,
                    this.dimensions.x, this.dimensions.y, this.dimensions.z,
                    this.grow, this.mirror, this.uvMap, this.texScale.u(), this.texScale.v()
            ));
            return this.parentBuilder;
        }
    }

    public static class PerFaceCubeDefinition extends CubeDefinition {

        public final Map<Direction, PerFaceUV> uvMap;

        protected PerFaceCubeDefinition(@Nullable String comment, float originX, float originY, float originZ, float dimensionX, float dimensionY, float dimensionZ, CubeDeformation grow, boolean mirror, Map<Direction, PerFaceUV> uvMap, float texScaleU, float texScaleV) {
            super(comment, 0, 0, originX, originY, originZ, dimensionX, dimensionY, dimensionZ, grow, mirror, texScaleU, texScaleV, Collections.emptySet());
            this.uvMap = uvMap;
        }

        @Override
        public ModelPart.Cube bake(int texWidth, int texHeight) {
            return new PerFaceUVCube(this.uvMap, this.origin.x, this.origin.y, this.origin.z, this.dimensions.x, this.dimensions.y, this.dimensions.z, this.grow.growX, this.grow.growY, this.grow.growZ, this.mirror, (float)texWidth * this.texScale.u(), (float)texHeight * this.texScale.v());
        }
    }

    public record PerFaceUV(UVPair uv, UVPair size) {

    }

    public static class PerFaceUVCube extends ModelPart.Cube {

        public PerFaceUVCube(Map<Direction, PerFaceUV> uvMap, float originX, float originY, float originZ, float dimensionX, float dimensionY, float dimensionZ, float growX, float growY, float growZ, boolean mirror, float texScaleU, float texScaleV) {
            super(0, 0, originX, originY, originZ, dimensionX, dimensionY, dimensionZ, growX, growY, growZ, mirror, texScaleU, texScaleV, uvMap.keySet());

            float f = originX + dimensionX;
            float g = originY + dimensionY;
            float h = originZ + dimensionZ;
            originX -= growX;
            originY -= growY;
            originZ -= growZ;
            f += growX;
            g += growY;
            h += growZ;
            if (mirror) {
                float i = f;
                f = originX;
                originX = i;
            }
            ModelPart.Vertex vertex = new ModelPart.Vertex(originX, originY, originZ, 0.0F, 0.0F);
            ModelPart.Vertex vertex2 = new ModelPart.Vertex(f, originY, originZ, 0.0F, 8.0F);
            ModelPart.Vertex vertex3 = new ModelPart.Vertex(f, g, originZ, 8.0F, 8.0F);
            ModelPart.Vertex vertex4 = new ModelPart.Vertex(originX, g, originZ, 8.0F, 0.0F);
            ModelPart.Vertex vertex5 = new ModelPart.Vertex(originX, originY, h, 0.0F, 0.0F);
            ModelPart.Vertex vertex6 = new ModelPart.Vertex(f, originY, h, 0.0F, 8.0F);
            ModelPart.Vertex vertex7 = new ModelPart.Vertex(f, g, h, 8.0F, 8.0F);
            ModelPart.Vertex vertex8 = new ModelPart.Vertex(originX, g, h, 8.0F, 0.0F);

            int s = 0;
            if (uvMap.containsKey(Direction.DOWN)) {
                var uv = uvMap.get(Direction.DOWN);
                this.polygons[s++] = new ModelPart.Polygon(new ModelPart.Vertex[]{vertex6, vertex5, vertex, vertex2}, uv.uv.u(), uv.uv.v(), uv.uv.u() + uv.size.u(), uv.uv.v() + uv.size.v(), texScaleU, texScaleV, mirror, Direction.DOWN);
            }

            if (uvMap.containsKey(Direction.UP)) {
                var uv = uvMap.get(Direction.UP);
                this.polygons[s++] = new ModelPart.Polygon(new ModelPart.Vertex[]{vertex3, vertex4, vertex8, vertex7}, uv.uv.u(), uv.uv.v(), uv.uv.u() + uv.size.u(), uv.uv.v() + uv.size.v(), texScaleU, texScaleV, mirror, Direction.UP);
            }

            if (uvMap.containsKey(Direction.WEST)) {
                var uv = uvMap.get(Direction.WEST);
                this.polygons[s++] = new ModelPart.Polygon(new ModelPart.Vertex[]{vertex, vertex5, vertex8, vertex4}, uv.uv.u(), uv.uv.v(), uv.uv.u() + uv.size.u(), uv.uv.v() + uv.size.v(), texScaleU, texScaleV, mirror, Direction.WEST);
            }

            if (uvMap.containsKey(Direction.NORTH)) {
                var uv = uvMap.get(Direction.NORTH);
                this.polygons[s++] = new ModelPart.Polygon(new ModelPart.Vertex[]{vertex2, vertex, vertex4, vertex3}, uv.uv.u(), uv.uv.v(), uv.uv.u() + uv.size.u(), uv.uv.v() + uv.size.v(), texScaleU, texScaleV, mirror, Direction.NORTH);
            }

            if (uvMap.containsKey(Direction.EAST)) {
                var uv = uvMap.get(Direction.EAST);
                this.polygons[s++] = new ModelPart.Polygon(new ModelPart.Vertex[]{vertex6, vertex2, vertex3, vertex7}, uv.uv.u(), uv.uv.v(), uv.uv.u() + uv.size.u(), uv.uv.v() + uv.size.v(), texScaleU, texScaleV, mirror, Direction.EAST);
            }

            if (uvMap.containsKey(Direction.SOUTH)) {
                var uv = uvMap.get(Direction.SOUTH);
                this.polygons[s] = new ModelPart.Polygon(new ModelPart.Vertex[]{vertex5, vertex6, vertex7, vertex8}, uv.uv.u(), uv.uv.v(), uv.uv.u() + uv.size.u(), uv.uv.v() + uv.size.v(), texScaleU, texScaleV, mirror, Direction.SOUTH);
            }
        }
    }

}