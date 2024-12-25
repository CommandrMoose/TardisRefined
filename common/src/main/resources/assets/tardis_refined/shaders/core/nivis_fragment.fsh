#version 150

in vec4 vertexColor;
in vec2 texCoord0;
in vec4 normal;
in vec4 snowDir;
in vec4 lightMapColor;

out vec4 fragColor;

uniform sampler2D Sampler0;      // Original texture
uniform sampler2D SamplerSnow;  // Snow texture
uniform vec4 SnowColor;
uniform mat3 IViewRotMat;

void main() {
    // Sample original texture
    vec4 baseColor = texture(Sampler0, texCoord0) * vertexColor;

    // Discard transparent areas
    if (baseColor.a < 0.1) {
        discard;
    }

    // Sample snow texture with scaling for tiling
    vec2 scaledTexCoord = texCoord0 * 16.0; // Adjust tiling factor as needed
    vec4 snowTextureColor = texture(SamplerSnow, scaledTexCoord);

    // Calculate snow blending factor
    float snowFactor = max(dot(normalize(snowDir.xyz), normalize(normal.xyz)), 0.0);

    // Blend base color with snow texture
    vec4 blendedSnow = mix(baseColor, snowTextureColor, smoothstep(0.1, 0.9, snowFactor));

    // Add a snow tint for brightness
    vec4 finalColor = mix(blendedSnow, SnowColor, snowFactor);

    // Apply light map color for shading
    finalColor *= lightMapColor;
    fragColor = finalColor;
}
