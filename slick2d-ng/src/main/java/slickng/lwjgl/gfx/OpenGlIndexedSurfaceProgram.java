package slickng.lwjgl.gfx;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import slickng.SlickException;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.GL_TEXTURE1;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.*;

/**
 * An OpenGL SL program that contains a shader that causes all colors to be
 * index-based.
 * <p>
 * This allows not only for palette-based textures to be rendered, but also for
 * swaps and color cycling.
 */
class OpenGlIndexedSurfaceProgram {

  private int programId;

  OpenGlIndexedSurfaceProgram() {
  }

  void init() throws SlickException {
    int fragmentShaderId = glCreateShader(GL_FRAGMENT_SHADER);
    try {
      glShaderSource(fragmentShaderId, readShaderSource("IndexedTexture.shader"));
    } catch (IOException e) {
      throw new SlickException("Could not load fragment shader.", e);
    }

    glCompileShader(fragmentShaderId);
    if (glGetShaderi(fragmentShaderId, GL_COMPILE_STATUS) == GL_FALSE) {
      int infoLogLen = glGetShaderi(fragmentShaderId, GL_INFO_LOG_LENGTH);
      String infoLog = glGetShaderInfoLog(fragmentShaderId, infoLogLen);
      glDeleteShader(fragmentShaderId);

      throw new SlickException(String.format("Could not initialize fragment shader: %n%s", infoLog));
    }

    int progId = glCreateProgram();
    glAttachShader(progId, fragmentShaderId);

    glLinkProgram(progId);
    if (glGetProgrami(progId, GL_LINK_STATUS) == GL_FALSE) {
      int infoLogLen = glGetShaderi(progId, GL_INFO_LOG_LENGTH);
      String infoLog = glGetShaderInfoLog(progId, infoLogLen);

      glDeleteShader(fragmentShaderId);
      glDeleteProgram(progId);

      throw new SlickException(String.format("Could not link shader program: %s", infoLog));
    }

    glDetachShader(progId, fragmentShaderId);

    this.programId = progId;
  }

  void deinit() {
    if (programId != 0) {
      glDeleteProgram(programId);
      programId = 0;
    }
  }

  void bind() {
    assertInitialized();

    glUseProgram(programId);
  }

  void setPalette(OpenGlSurface palette) {
    assertInitialized();

    int location = glGetUniformLocation(programId, "palette");
    glUniform1i(location, 1);
    glActiveTexture(GL_TEXTURE1);
    palette.bind();
  }

  void setPaletteIndex(int index) {
    assertInitialized();

  }

  void setImage(OpenGlSurface texture) {
    assertInitialized();

    int location = glGetUniformLocation(programId, "texture");
    glUniform1i(location, 0);
    glActiveTexture(GL_TEXTURE0);
    texture.bind();
  }

  void unbind() {
    assertInitialized();

    glUseProgram(0);
  }

  private static ByteBuffer readShaderSource(String filename) throws IOException {
    try (InputStream stream = OpenGlIndexedSurfaceProgram.class.getResourceAsStream("opengl/shaders/" + filename)) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      byte[] data = new byte[1024];
      int read;
      read = stream.read(data);
      while (read >= 0) {
        baos.write(data, 0, read);
        read = stream.read(data);
      }

      byte[] bla = baos.toByteArray();
      // Must be a direct buffer
      ByteBuffer out = ByteBuffer.allocateDirect(bla.length);
      out.put(bla);
      out.flip();
      return out;
    }
  }

  private void assertInitialized() throws IllegalStateException {
    if (programId == 0) {
      throw new IllegalStateException(String.format("%s has not been initialized.", OpenGlIndexedSurfaceProgram.class.getSimpleName()));
    }
  }

}
