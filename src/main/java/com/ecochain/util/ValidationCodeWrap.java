package com.ecochain.util;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * 验证码图片封装
 */
public class ValidationCodeWrap implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 6386330499102721297L;
    public BufferedImage image;
    private ValidationCode vc;

    public ValidationCode getVc() {
        return vc;
    }

    public void setVc(ValidationCode vc) {
        this.vc = vc;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }


}