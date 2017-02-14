uniform sampler2D texture;
uniform sampler2D palette;

void main()
{
  vec4 textureColor = texture2D(texture, gl_TexCoord[0].xy).rgba;
  vec4 paletteColor = texture2D(palette, vec2(textureColor.r, 0)).rgba;
  gl_FragColor = vec4(paletteColor.r, paletteColor.g, paletteColor.b, paletteColor.a);
}