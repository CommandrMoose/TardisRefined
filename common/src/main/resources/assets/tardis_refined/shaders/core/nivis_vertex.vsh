#version 150

in vec3 Position;
in vec4 Color;
in vec2 UV0;
in vec3 Normal;

uniform mat4 ProjMat;
uniform sampler2D Sampler2;
uniform mat3 IViewRotMat;

out vec4 vertexColor;
out vec2 texCoord0;
out vec4 normal;
out vec4 snowDir;
out vec4 lightMapColor;

void main() {
    gl_Position = ProjMat * vec4(Position, 1.0);
    vertexColor = Color;
    texCoord0 = UV0;
    lightMapColor = texture(Sampler2, ivec2(UV0 * 16.0), 0);
    normal = vec4(Normal * inverse(IViewRotMat), 0.0);
    snowDir = vec4(0.0, 1.0, 0.0, 0.0);
}
