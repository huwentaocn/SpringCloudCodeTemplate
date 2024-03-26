package com.hwt.common.util;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

/**
 * @description: 矩形图片处理工具类
 * @author: gxj
 * @date: Created in 2024-02-19 11:35
 * @copyright: Copyright (c) 2024
 * @version: V1.0
 * @modified: gxj
 */
public class ImageProcessUtil {

    /**
     * 矩形图片裁剪为圆形图片
     */
    public static BufferedImage createCircleImage(Image image) {
        // 创建一个和原图片大小一样的透明背景图片
        BufferedImage circleImage = new BufferedImage(
                image.getWidth(null),
                image.getHeight(null),
                BufferedImage.TYPE_INT_RGB);
        int centerX = circleImage.getWidth() / 2;
        int centerY = circleImage.getHeight() / 2;
        int radius = Math.min(centerX,centerY);
        // 获取Graphics2D对象
        Graphics2D g2d = circleImage.createGraphics();
        // 使用Graphics2D对象设置抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 设置透明度为半透明，以便原始图片的背景可以透明显示
        g2d.setComposite(AlphaComposite.SrcIn);
        // 设置裁剪区域为圆形
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        Ellipse2D circle = new Ellipse2D.Double(0, 0, width, height);
        g2d.setClip(circle);
        // 绘制一个白色的背景，这将覆盖掉原始图片的背景
        g2d.setColor(Color.WHITE);
        g2d.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
        // 将原始正方形图片绘制到裁剪后的 Graphics2D 上下文中
        g2d.drawImage(image, 0, 0, null);
        // 释放Graphics2D对象
        g2d.dispose();
        return circleImage;
    }

    /**
     * 矩形图像裁剪为之前的1/2
     */
    public static BufferedImage cropHalfImage(BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();
        // 设定新的图片大小
        int newWidth = width / 2;
        int newHeight = height / 2;
        // 创建一个新的BufferedImage对象，类型为TYPE_INT_RGB
        BufferedImage newBufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        // 创建一个Graphics2D对象，用于在BufferedImage上绘图
        Graphics2D g2d = newBufferedImage.createGraphics();
        // 使用drawImage方法绘制缩放后的图片
        g2d.drawImage(image, 0, 0, newWidth, newHeight, null);
        // 释放Graphics2D对象占用的资源
        g2d.dispose();
        return newBufferedImage;
    }

    /**
     * 矩形图像裁剪为之前的1/4
     */
    public static BufferedImage cropQuarterImage(BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();
        // 设定新的图片大小
        int newWidth = width / 3;
        int newHeight = height / 3;
        // 创建一个新的BufferedImage对象，类型为TYPE_INT_RGB
        BufferedImage newBufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        // 创建一个Graphics2D对象，用于在BufferedImage上绘图
        Graphics2D g2d = newBufferedImage.createGraphics();
        // 使用drawImage方法绘制缩放后的图片
        g2d.drawImage(image, 0, 0, newWidth, newHeight, null);
        // 释放Graphics2D对象占用的资源
        g2d.dispose();
        return newBufferedImage;
    }
}
