package com.talanlabs.avatargenerator;

import com.talanlabs.avatargenerator.cache.ICache;
import com.talanlabs.avatargenerator.element.ElementInfo;
import com.talanlabs.avatargenerator.element.ElementRegistry;
import com.talanlabs.avatargenerator.layers.ILayer;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Avatar {

    private int width = 128;
    private int height = 128;
    private int padding = 0;
    private int margin = 0;

    private ElementRegistry elementRegistry = null;
    private IColorizeFunction colorizeFunction = null;
    private ILayer[] layers;
    private ICache cache;

    private Avatar() {
        super();
    }

    public static AvatarBuilder newBuilder() {
        return new AvatarBuilder();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPadding() {
        return padding;
    }

    public int getMargin() {
        return margin;
    }

    /**
     * Create avatar image
     *
     * @param code the code
     * @return image
     */
    public BufferedImage create(long code) {
        Random random = new Random(code);

        IAvatarInfo avatarInfo = new MyAvatarInfo(code, random);

        if (cache != null) {
            return cache.get(avatarInfo, this::buildAll);
        } else {
            return buildAll(avatarInfo);
        }
    }

    /**
     * Create avatar image as png bytes
     *
     * @param code the code
     * @return byte array
     */
    public byte[] createAsPngBytes(long code) {
        BufferedImage src = create(code);
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(src, "png", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new AvatarException("Failed to write png for code=" + code, e);
        }
    }

    /**
     * Create avatar image as png to file
     *
     * @param code the code
     * @param file file to write png
     */
    public void createAsPngToFile(long code, File file) {
        BufferedImage src = create(code);
        try {
            ImageIO.write(src, "png", file);
        } catch (IOException e) {
            throw new AvatarException("Failed to write png for code=" + code, e);
        }
    }

    private BufferedImage buildAll(IAvatarInfo avatarInfo) {
        try {
            BufferedImage bufferedImage = buildAvatarImage(avatarInfo);

            int wm = width - margin * 2;
            int hm = height - margin * 2;
            int wmp = wm - padding * 2;
            int hmp = hm - padding * 2;

            bufferedImage = AvatarUtils.resizeImage(bufferedImage, wmp, hmp);
            bufferedImage = AvatarUtils.planImage(bufferedImage, wm, hm);

            if (layers != null && layers.length > 0) {
                for (ILayer layer : layers) {
                    bufferedImage = layer.apply(avatarInfo, bufferedImage);
                }
            }

            bufferedImage = AvatarUtils.resizeImage(bufferedImage, wm, hm);
            bufferedImage = AvatarUtils.planImage(bufferedImage, width, height);
            return bufferedImage;
        } catch (Exception e) {
            throw new AvatarException("Failed to build avatar", e);
        }
    }

    private BufferedImage buildAvatarImage(IAvatarInfo avatarInfo) throws IOException {
        if (elementRegistry == null) {
            return new BufferedImage(width - (margin + padding) * 2, height - (margin + padding) * 2, BufferedImage.TYPE_INT_ARGB);
        }

        Random random = avatarInfo.getRandom();

        int xmin = Integer.MAX_VALUE, ymin = Integer.MAX_VALUE, xmax = Integer.MIN_VALUE, ymax = Integer.MIN_VALUE;
        List<ImageInfo> imageInfos = new ArrayList<>();
        int groupCount = elementRegistry.getGroupCount(avatarInfo);
        if (groupCount > 0) {
            int d = random.nextInt(groupCount);
            ElementInfo[] elements = elementRegistry.getGroup(avatarInfo, d);
            if (elements != null && elements.length > 0) {
                for (ElementInfo element : elements) {
                    int elementCount = elementRegistry.getElementCount(avatarInfo, element.name);
                    if (elementCount > 0) {
                        int index = random.nextInt(elementCount);
                        BufferedImage bufferedImage = AvatarUtils.toARGBImage(elementRegistry.getElement(avatarInfo, element.name, index));

                        xmin = Math.min(xmin, -bufferedImage.getWidth() / 2 + element.offsetX);
                        xmax = Math.max(xmax, bufferedImage.getWidth() / 2 + element.offsetX);
                        ymin = Math.min(ymin, -bufferedImage.getHeight() / 2 + element.offsetY);
                        ymax = Math.max(ymax, bufferedImage.getHeight() / 2 + element.offsetY);

                        imageInfos.add(new ImageInfo(element.name, bufferedImage, element.offsetX, element.offsetY));
                    }
                }
            }
        }
        int w = xmax - xmin;
        int h = ymax - ymin;

        BufferedImage dest = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = dest.createGraphics();
        AvatarUtils.activeAntialiasing(g2);

        for (ImageInfo imageInfo : imageInfos) {
            copyImage(g2, avatarInfo, imageInfo, w, h);
        }

        g2.dispose();

        return dest;
    }

    private void copyImage(Graphics2D g2, IAvatarInfo avatarInfo, ImageInfo imageInfo, int width, int height) throws IOException {
        BufferedImage img = imageInfo.image;
        if (colorizeFunction != null) {
            Color color = colorizeFunction.colorize(avatarInfo, imageInfo.element);
            if (color != null) {
                img = AvatarUtils.tintImage(img, color);
            }
        }
        int w = img.getWidth(null);
        int h = img.getHeight(null);
        int x = (width - w) / 2 + imageInfo.offsetX;
        int y = (height - h) / 2 + imageInfo.offsetY;
        g2.drawImage(img, x, y, w, h, null);
    }

    public interface IColorizeFunction {

        /**
         * Get color for element
         *
         * @param avatarInfo current avatarInfo
         * @param element    name of element
         * @return color
         */
        Color colorize(IAvatarInfo avatarInfo, String element);

    }

    public static class AvatarBuilder {

        private int width = 128;
        private int height = 128;
        private int padding = 0;
        private int margin = 0;

        private ElementRegistry elementRegistry = null;
        private IColorizeFunction colorizeFunction = null;
        private ILayer[] layers;
        private ICache cache;

        private AvatarBuilder() {
            super();
        }

        /**
         * Element loader
         */
        public AvatarBuilder elementRegistry(ElementRegistry elementRegistry) {
            this.elementRegistry = elementRegistry;
            return this;
        }

        /**
         * Set size of avatar
         * Default is 128x128
         */
        public AvatarBuilder size(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        /**
         * Set space with border
         * Default is 8
         */
        public AvatarBuilder padding(int padding) {
            this.padding = padding;
            return this;
        }

        /**
         * Set space out border
         * Default is 8
         */
        public AvatarBuilder margin(int margin) {
            this.margin = margin;
            return this;
        }

        /**
         * Apply layers after
         */
        public AvatarBuilder layers(ILayer... layers) {
            this.layers = layers;
            return this;
        }

        /**
         * Color of element
         */
        public AvatarBuilder color(Color color) {
            return colorizeFunction((c, e) -> color);
        }

        /**
         * Color of element
         */
        public AvatarBuilder colorizeFunction(IColorizeFunction colorizeFunction) {
            this.colorizeFunction = colorizeFunction;
            return this;
        }

        public AvatarBuilder cache(ICache cache) {
            this.cache = cache;
            return this;
        }

        /**
         * Build image
         */
        public Avatar build() {
            Avatar avatar = new Avatar();
            avatar.width = width;
            avatar.height = height;
            avatar.padding = padding;
            avatar.margin = margin;
            avatar.elementRegistry = elementRegistry;
            avatar.colorizeFunction = colorizeFunction;
            avatar.layers = layers != null ? Arrays.copyOf(layers, layers.length) : null;
            avatar.cache = cache;
            return avatar;
        }
    }

    private static class ImageInfo {

        public final String element;
        public final BufferedImage image;
        public final int offsetX;
        public final int offsetY;

        ImageInfo(String element, BufferedImage image, int offsetX, int offsetY) {
            super();

            this.element = element;
            this.image = image;
            this.offsetX = offsetX;
            this.offsetY = offsetY;
        }
    }

    private class MyAvatarInfo implements IAvatarInfo {

        private long code;
        private Random random;

        public MyAvatarInfo(long code, Random random) {
            this.code = code;
            this.random = random;
        }

        @Override
        public long getCode() {
            return code;
        }

        @Override
        public Random getRandom() {
            return random;
        }

        @Override
        public int getWidth() {
            return width;
        }

        @Override
        public int getHeight() {
            return height;
        }

        @Override
        public int getPadding() {
            return padding;
        }

        @Override
        public int getMargin() {
            return margin;
        }
    }
}