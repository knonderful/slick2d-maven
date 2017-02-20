package slickng.tiled.io;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import slickng.SlickException;

/**
 * A utility class to work with {@link Node} (and related) objects.
 */
public class DomUtil {

  /**
   * Retrieves a child node from the provided node.
   *
   * @param node The (parent) node.
   * @param name The name of the child node.
   * @return The child node or {@code null} if no such node exists.
   * @throws SlickException If an error occurred while retrieving the node.
   */
  public static Node getChildNode(Node node, String name) throws SlickException {
    return getChildNode(node, name, false);
  }

  /**
   * Retrieves a child node from the provided node. This method throws a
   * {@link SlickException} if the requested node was not found.
   *
   * @param node The (parent) node.
   * @param name The name of the child node.
   * @return The child node or {@code null} if no such node exists.
   * @throws SlickException If an error occurred while retrieving the node.
   */
  public static Node getChildNodeOrFail(Node node, String name) throws SlickException {
    return getChildNode(node, name, true);
  }

  /**
   * Retrieves an attribute from the provided map.
   *
   * @param map  The map.
   * @param name The name of the attribute.
   * @return The attribute value.
   * @throws SlickException If the attribute was not found.
   */
  public static String getValueOrFail(NamedNodeMap map, String name) throws SlickException {
    Node node = map.getNamedItem(name);
    if (node == null) {
      throw new SlickException(String.format("Missing mandatory attribute '%s'.", name));
    }

    String val = node.getNodeValue();
    if (val == null || val.isEmpty()) {
      throw new SlickException(String.format("Missing value for attribute '%s'.", name));
    }

    return val;
  }

  private static Node getChildNode(Node node, String name, boolean required) throws SlickException {
    Node curNode = node.getFirstChild();
    while (curNode != null) {
      if (curNode.getNodeName().equals(name)) {
        return curNode;
      }

      curNode = curNode.getNextSibling();
    }

    if (required) {
      throw new SlickException(String.format("Node '%s' not found.", name));
    }

    return null;
  }

  private DomUtil() {
  }
}
