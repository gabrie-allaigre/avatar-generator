package com.talanlabs.avatargenerator.layers.others;

import com.talanlabs.avatargenerator.IAvatarInfo;
import com.talanlabs.avatargenerator.layers.ILayer;
import com.talanlabs.jlegofy.JLegofyEngine;
import com.talanlabs.jlegofy.JLegofyException;

import java.awt.image.BufferedImage;

public class LegofyLayer implements ILayer {

    private final JLegofyEngine engine;

    private double precision = 0.5;

    public LegofyLayer() throws JLegofyException {
        this(0.5);
    }

    public LegofyLayer(double precision) throws JLegofyException {
        super();

        this.engine = new JLegofyEngine();
        this.precision = precision;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    @Override
    public BufferedImage apply(IAvatarInfo avatarInfo, BufferedImage src) {
        return engine.toLegofy(src, precision);
    }
}
