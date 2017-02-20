package slickng.tiled.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import slickng.SlickException;
import slickng.tiled.TAnimation;
import slickng.tiled.TAnimationFrame;
import slickng.tiled.TImage;
import slickng.tiled.TLayer;
import slickng.tiled.TMap;
import slickng.tiled.TTile;
import slickng.tiled.TTileSet;

import static slickng.tiled.io.DomUtil.*;

public class TMapReader {

  private static final Logger LOG = Logger.getLogger(TMapReader.class.getName());

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

  private TTile getTile(Node tileNode, int firstGid) throws NumberFormatException, SlickException {
    // This ID is actually the index of the tile in the current tile set, so we need to convert to GID
    int tileId = Integer.parseInt(getValueOrFail(tileNode.getAttributes(), "id")) + firstGid;

    Map<String, String> properties;
    Node propertiesNode = getChildNode(tileNode, "properties");
    if (propertiesNode == null) {
      properties = Collections.emptyMap();
    } else {
      properties = getTileProperties(propertiesNode);
    }

    TAnimation animation;
    Node animationNode = getChildNode(tileNode, "animation");
    if (animationNode == null) {
      animation = null;
    } else {
      animation = getTileAnimation(animationNode, firstGid);
    }

    return new TTile(tileId, properties, animation);
  }

  private TAnimation getTileAnimation(Node animationNode, int firstGid) throws NumberFormatException, SlickException {
    TAnimation animation;
    NodeList animChildren = animationNode.getChildNodes();
    List<TAnimationFrame> frames = new ArrayList<>(animChildren.getLength());
    for (int j = 0; j < animChildren.getLength(); j++) {
      Node animChild = animChildren.item(j);
      if (!"frame".equals(animChild.getNodeName())) {
        continue;
      }

      NamedNodeMap frameAttribs = animChild.getAttributes();
      TAnimationFrame frame = new TAnimationFrame(
              // This is the index of the tile in the current tile set, so we need to convert to GID here
              Integer.parseInt(getValueOrFail(frameAttribs, "tileid")) + firstGid,
              Integer.parseInt(getValueOrFail(frameAttribs, "duration")));

      frames.add(frame);
    }
    animation = new TAnimation(frames);
    return animation;
  }

  private Map<String, String> getTileProperties(Node propertiesNode) throws SlickException {
    NodeList propNodes = propertiesNode.getChildNodes();
    Map<String, String> properties = new HashMap<>(propNodes.getLength());
    for (int j = 0; j < propNodes.getLength(); j++) {
      Node propNode = propNodes.item(j);
      if (!"property".equals(propNode.getNodeName())) {
        continue;
      }

      NamedNodeMap propAtts = propNode.getAttributes();
      properties.put(
              getValueOrFail(propAtts, "name"),
              getValueOrFail(propAtts, "value"));
    }
    return properties;
  }

  private void handleTilesetNode(Node node, Collection<TTileSet> tileSets) throws IOException, SlickException {
    NamedNodeMap atts = node.getAttributes();
    String name = getValueOrFail(atts, "name");
    int firstGid = Integer.parseInt(getValueOrFail(atts, "firstgid"));
    int tileWidth = Integer.parseInt(getValueOrFail(atts, "tilewidth"));
    int tileHeight = Integer.parseInt(getValueOrFail(atts, "tileheight"));

    Node imageNode = getChildNodeOrFail(node, "image");
    NamedNodeMap imageNodeAtts = imageNode.getAttributes();
    TImage image = new TImage(
            getValueOrFail(imageNodeAtts, "source"),
            Integer.parseInt(getValueOrFail(imageNodeAtts, "width")),
            Integer.parseInt(getValueOrFail(imageNodeAtts, "height")));

    Collection<TTile> explicitTiles = new LinkedList<>();
    NodeList subNodes = node.getChildNodes();
    for (int i = 0; i < subNodes.getLength(); i++) {
      Node curNode = subNodes.item(i);
      if (!"tile".equals(curNode.getNodeName())) {
        continue;
      }

      explicitTiles.add(getTile(curNode, firstGid));
    }

    int tileCount = Integer.parseInt(getValueOrFail(atts, "tilecount"));
    List<TTile> tiles = IntStream.range(firstGid, firstGid + tileCount)
            .boxed()
            .map(tileId -> {
              TTile tile = explicitTiles.stream()
                      .filter(t -> t.getGid() == tileId)
                      .findAny()
                      .orElse(null);

              if (tile != null) {
                return tile;
              }

              return new TTile(tileId);
            })
            .collect(Collectors.toList());

    TTileSet tileSet = new TTileSet(name, tileWidth, tileHeight, image, tiles);
    tileSets.add(tileSet);
  }

  private void handleTileLayerNode(Node node, List<TLayer> layers) {

  }

}
