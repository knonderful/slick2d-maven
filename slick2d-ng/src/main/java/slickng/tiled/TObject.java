package slickng.tiled;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * An object in an {@link TObjectGroup}.
 */
public class TObject {

  private final int id;
  private final String name;
  private final String type;
  private final float x;
  private final float y;
  private final float width;
  private final float height;
  private final float rotation;
  private final boolean flippedHorizontally;
  private final boolean flippedVertically;
  private final TTile tile;
  private final Map<String, String> properties;
  private final boolean visible;

  /**
   * Creates a new instance.
   *
   * @param id                  The ID inside the object group.
   * @param name                The name.
   * @param type                The type.
   * @param x                   The X coordinate.
   * @param y                   The Y coordinate.
   * @param width               The width.
   * @param height              The height.
   * @param rotation            The rotation.
   * @param flippedHorizontally A flag that specifies whether the object is
   *                            horizontally flipped.
   * @param flippedVertically   A flag that specifies whether the object is
   *                            vertically flipped.
   * @param tile                The tile for this object.
   * @param properties          The properties.
   * @param visible             A flag that specifies whether the object is
   *                            visible.
   */
  public TObject(int id, String name, String type, float x, float y,
          float width, float height, float rotation,
          boolean flippedHorizontally, boolean flippedVertically,
          TTile tile, Map<String, String> properties, boolean visible) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.rotation = rotation;
    this.flippedHorizontally = flippedHorizontally;
    this.flippedVertically = flippedVertically;
    this.tile = tile;
    this.properties = new HashMap<>(properties);
    this.visible = visible;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final TObject other = (TObject) obj;
    if (this.id != other.id) {
      return false;
    }
    if (Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x)) {
      return false;
    }
    if (Float.floatToIntBits(this.y) != Float.floatToIntBits(other.y)) {
      return false;
    }
    if (Float.floatToIntBits(this.width) != Float.floatToIntBits(other.width)) {
      return false;
    }
    if (Float.floatToIntBits(this.height) != Float.floatToIntBits(other.height)) {
      return false;
    }
    if (Float.floatToIntBits(this.rotation) != Float.floatToIntBits(other.rotation)) {
      return false;
    }
    if (this.flippedHorizontally != other.flippedHorizontally) {
      return false;
    }
    if (this.flippedVertically != other.flippedVertically) {
      return false;
    }
    if (this.visible != other.visible) {
      return false;
    }
    if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    if (!Objects.equals(this.type, other.type)) {
      return false;
    }
    if (!Objects.equals(this.tile, other.tile)) {
      return false;
    }
    return Objects.equals(this.properties, other.properties);
  }

  /**
   * Retrieves the height.
   *
   * @return The height.
   */
  public float getHeight() {
    return height;
  }

  /**
   * Retrieves the ID.
   *
   * @return The ID.
   */
  public int getId() {
    return id;
  }

  /**
   * Retrieves the name.
   *
   * @return The name.
   */
  public String getName() {
    return name;
  }

  /**
   * Retrieves the properties.
   *
   * @return The properties.
   */
  public Map<String, String> getProperties() {
    return Collections.unmodifiableMap(properties);
  }

  /**
   * Retrieves the rotation.
   *
   * @return The rotation.
   */
  public float getRotation() {
    return rotation;
  }

  /**
   * Retrieves the tile.
   *
   * @return The tile.
   */
  public TTile getTile() {
    return tile;
  }

  /**
   * Retrieves the type.
   *
   * @return The type.
   */
  public String getType() {
    return type;
  }

  /**
   * Retrieves the width.
   *
   * @return The width.
   */
  public float getWidth() {
    return width;
  }

  /**
   * Retrieves the X coordinate.
   *
   * @return The X coordinate.
   */
  public float getX() {
    return x;
  }

  /**
   * Retrieves the Y coordinate.
   *
   * @return The Y coordinate.
   */
  public float getY() {
    return y;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 17 * hash + this.id;
    hash = 17 * hash + Objects.hashCode(this.name);
    hash = 17 * hash + Objects.hashCode(this.type);
    hash = 17 * hash + Float.floatToIntBits(this.x);
    hash = 17 * hash + Float.floatToIntBits(this.y);
    hash = 17 * hash + Float.floatToIntBits(this.width);
    hash = 17 * hash + Float.floatToIntBits(this.height);
    hash = 17 * hash + Float.floatToIntBits(this.rotation);
    hash = 17 * hash + (this.flippedHorizontally ? 1 : 0);
    hash = 17 * hash + (this.flippedVertically ? 1 : 0);
    hash = 17 * hash + Objects.hashCode(this.tile);
    hash = 17 * hash + Objects.hashCode(this.properties);
    hash = 17 * hash + (this.visible ? 1 : 0);
    return hash;
  }

  /**
   * Determines whether the object is horizontally flipped.
   *
   * @return {@code true} if the object is horizontally flipped, otherwise
   *         {@code false}.
   */
  public boolean isFlippedHorizontally() {
    return flippedHorizontally;
  }

  /**
   * Determines whether the object is vertically flipped.
   *
   * @return {@code true} if the object is vertically flipped, otherwise
   *         {@code false}.
   */
  public boolean isFlippedVertically() {
    return flippedVertically;
  }

  /**
   * Determines whether the object is visible.
   *
   * @return {@code true} if the object is visible, otherwise {@code false}.
   */
  public boolean isVisible() {
    return visible;
  }

}
