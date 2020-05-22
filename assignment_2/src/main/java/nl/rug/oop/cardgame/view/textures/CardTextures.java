package nl.rug.oop.cardgame.view.textures;

import nl.rug.oop.cardgame.model.elements.Card;

import java.util.EnumMap;

import java.io.File;
import java.io.IOException;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * For each card in a 54 card deck, this class loads a texture. It does so
 * statically to prevent duplicate loading of large images. It also does it
 * eagerly (at startup) to prevent loading times from slowing the program down
 * at runtime.
 */
public class CardTextures {

    private static final EnumMap<Card, BufferedImage> textures;

    static {
        textures = new EnumMap<>(Card.class);
        for (Card card : Card.values()) {
            BufferedImage texture = null;
            String fileName = "src/resources/textures/" + card + ".png";
            try {
                File imgFile = new File(fileName);
                texture = ImageIO.read(imgFile);
            } catch (IOException ioe) {
                System.err.println("Could not load " + fileName);
            }
            textures.put(card, texture);
        }
    }

    /**
     * Find a texture for a card.
     *
     * @param card The cart in question.
     */
    public static BufferedImage getTexture(Card card) {
        return textures.get(card);
    }

}
