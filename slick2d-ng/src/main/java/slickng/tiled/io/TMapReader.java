package slickng.tiled.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import slickng.SlickException;
import slickng.gfx.Surface;
import slickng.gfx.TileSheet;
import slickng.tiled.TLayer;
import slickng.tiled.TMap;
import slickng.tiled.TTileSet;

import static java.util.Objects.requireNonNull;

public class TMapReader {

  private static final Logger LOG = Logger.getLogger(TMapReader.class.getName());

  private final SurfaceResolver surfaceResolver;

  public TMapReader(SurfaceResolver surfaceResolver) {
    this.surfaceResolver = requireNonNull(surfaceResolver, "Argument surfaceResolver must be non-null.");
  }

  public TMap read(InputStream inputStream) throws SAXException, IOException, ParserConfigurationException, SlickException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = dbf.newDocumentBuilder();
    Document doc = builder.parse(inputStream);

    Node mapNode = getChildNodeOrFail(doc, "map");

    Collection<TTileSet> tileSets = new LinkedList<>();
    List<TLayer> layers = new LinkedList<>();

    NodeList mapChildren = mapNode.getChildNodes();
    for (int i = 0; i < mapChildren.getLength(); i++) {
      Node node = mapChildren.item(i);
      String nodeName = node.getNodeName();
      if (nodeName.equals("tileset")) {
        handleTilesetNode(node, tileSets);
      } else if (nodeName.equals("layer")) {
        handleTileLayerNode(node, layers);
      } else if (nodeName.equals("objectgroup")) {
        // Not yet supported
        LOG.info(String.format("Skipping unsupported XML node: '%s'.", nodeName));
      } else {
        LOG.finest(String.format("Skipping unexpected XML node: '%s'.", nodeName));
      }
    }

    return new TMap(tileSets, layers);
  }

  private static String getValueOrFail(NamedNodeMap map, String name) throws IOException {
    Node node = map.getNamedItem(name);
    if (node == null) {
      throw new IOException(String.format("Missing mandatory attribute '%s'.", name));
    }

    String val = node.getNodeValue();
    if (val == null || val.isEmpty()) {
      throw new IOException(String.format("Missing value for attribute '%s'.", name));
    }

    return val;
  }

  private void handleTilesetNode(Node node, Collection<TTileSet> tileSets) throws IOException, SlickException {
    NamedNodeMap atts = node.getAttributes();
    String name = getValueOrFail(atts, "name");
    int firstGid = Integer.parseInt(getValueOrFail(atts, "firstgid"));
    int tileWidth = Integer.parseInt(getValueOrFail(atts, "tilewidth"));
    int tileHeight = Integer.parseInt(getValueOrFail(atts, "tileheight"));
    int tileCount = Integer.parseInt(getValueOrFail(atts, "tilecount"));

    Surface surface = surfaceFromImageNode(getChildNodeOrFail(node, "image"));
    TileSheet tileSheet = surface.createTileSheet(tileWidth, tileHeight);

    if (tileSheet.getWidth() * tileSheet.getHeight() != tileCount) {
      throw new IOException(String.format("Tile sheet from surface (%dx%d) does not have the expected tile count: %d.", tileSheet.getWidth(), tileSheet.getHeight(), tileCount));
    }

    TTileSet tileSet = new TTileSet(name, firstGid, surface, tileSheet);
    tileSets.add(tileSet);
  }

  private Surface surfaceFromImageNode(Node node) throws IOException, SlickException {
    NamedNodeMap atts = node.getAttributes();
    String source = getValueOrFail(atts, "source");
    int width = Integer.parseInt(getValueOrFail(atts, "width"));
    int height = Integer.parseInt(getValueOrFail(atts, "height"));

    Surface surface = surfaceResolver.resolve(source);
    if (surface.getWidth() != width) {
      throw new IOException(String.format("Map data expects image width for source '%s' to be %d, but actual surface width is %d.", source, width, surface.getWidth()));
    }
    if (surface.getHeight() != height) {
      throw new IOException(String.format("Map data expects image height for source '%s' to be %d, but actual surface height is %d.", source, height, surface.getHeight()));
    }

    return surface;
  }

  private void handleTileLayerNode(Node node, List<TLayer> layers) {

  }

  private static Node getChildNodeOrFail(Node node, String name) throws IOException {
    Node curNode = node.getFirstChild();
    while (curNode != null) {
      if (curNode.getNodeName().equals(name)) {
        return curNode;
      }

      curNode = curNode.getNextSibling();
    }

    throw new IOException(String.format("Node '%s' not found.", name));
  }
}
