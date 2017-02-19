uniform sampler2D texture;
uniform sampler2D palette;
uniform vec2 paletteOffset;

void main()
{
  // The red color component in the texture contains the index in the palette
  float textureColor = texture2D(texture, gl_TexCoord[0].xy).r;
  // Look up and use the target color in the palette
  gl_FragColor = texture2D(palette, vec2(textureColor, 0) + paletteOffset).rgba;

//  // Example of applying transparency on top of the palette color:
//  vec4 paletteColor = texture2D(palette, vec2(textureColor, 0)).rgba;
//  // Set the color
//  gl_FragColor = vec4(paletteColor.r, paletteColor.g, paletteColor.b, paletteColor.a * 0.5);
//
//  // Similar approaches can be used to color a sprite with a red hue.
}