package com.java.manager.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
/**
 *   验证码工具类
 * @author lujun
 *
 */
public class AuthCodeUtil {

	// 字符串的长度
	private static final int AUTHCODE_LENGTH = 4;
	// 单个字符的宽度
	private static final int SINGLECODE_WIDTH = 25;
	// 单个字符的高度
	private static final int SINGLECODE_HEIGHT = 32;
	// 字符之间的间隙
	private static final int SINGLECODE_GAP = 5;
	// 图片的宽度
	private static final int IMG_WIDHT=AUTHCODE_LENGTH*(SINGLECODE_WIDTH+SINGLECODE_GAP);
	// 图片的高度
	private static final int IMG_HEIGHT=SINGLECODE_HEIGHT+3;
	
	
	//定义字符库
	private static final char[] CHARS={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	//定义颜色库
	private static final Color [] COLORS={Color.RED,Color.BLACK,Color.BLUE,Color.GRAY,Color.GREEN,Color.YELLOW,Color.PINK};
	
	//定义 生成随机数据的对象
	private static final Random ran=new Random();

	/**
	 * 生成字符串的验证码
	 * 
	 * @return
	 */
	public static String getCode() {
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < AUTHCODE_LENGTH; i++) {
			sb.append(CHARS[ran.nextInt(CHARS.length)]);
		}
		return sb.toString();
	}

	/**
	 * 根据生成的验证码，生成图片
	 * 
	 * @param code
	 * @return
	 */
	public static BufferedImage getAuthCodeImg(String code) {
		//创建一个空白的图片，设置图片的宽度高度还有颜色类型
		BufferedImage img = new BufferedImage(IMG_WIDHT, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
		//得到当前图片的画笔
		Graphics graphics=img.getGraphics();
		//设置画笔的颜色
		graphics.setColor(Color.WHITE);
		//在空白的图片上画一个矩形填充
		graphics.fillRect(0, 0, IMG_WIDHT, IMG_HEIGHT);
		
		//设置字体    字体 ，   加粗        大小
		graphics.setFont(new Font("宋体", Font.BOLD, SINGLECODE_HEIGHT));
		for (int i = 0; i < code.length(); i++) {
			//开始向图片上画字符
			graphics.setColor(COLORS[ran.nextInt(COLORS.length)]);
			graphics.drawString(code.charAt(i)+"", i*(SINGLECODE_WIDTH+SINGLECODE_GAP)+SINGLECODE_GAP/2, SINGLECODE_HEIGHT);
		}
		
		//添加干扰素
		for (int i = 0; i <15; i++) {
			graphics.setColor(COLORS[ran.nextInt(COLORS.length)]);
			graphics.drawLine(ran.nextInt(IMG_WIDHT), ran.nextInt(IMG_HEIGHT), ran.nextInt(IMG_WIDHT), ran.nextInt(IMG_HEIGHT));
		}
		return img;
	}
}
