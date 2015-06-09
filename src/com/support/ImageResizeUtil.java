package com.support;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageResizeUtil {
	public static String savingPath = "/home/baobao/products/";
	public static final int maxValue = 500;

	public static void imageResize(String filePath, String imageType) throws IOException {
		BufferedImage inputImage = ImageIO.read(new File(filePath));
		double orignWidth = inputImage.getWidth();
		double orignHeight = inputImage.getHeight();
		
		if(orignWidth < maxValue && orignHeight < maxValue)
			return;
		
		double ratio = (orignWidth > orignHeight)? orignHeight / orignWidth : orignWidth / orignHeight;
		int changedWidth = (orignWidth > orignHeight)? maxValue : (int)(maxValue * ratio);
		int changedHeight = (orignWidth > orignHeight)? (int)(maxValue * ratio) : maxValue;
		
		BufferedImage outputImage = new BufferedImage(changedWidth, changedHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = outputImage.createGraphics();
		g.drawImage(inputImage, 0, 0, changedWidth, changedHeight, null);
		File out = new File(filePath);
		FileOutputStream fos = new FileOutputStream(out);
		ImageIO.write(outputImage, imageType, fos);
	}
}
