package com.java.manager.servlet;

import com.java.manager.util.AuthCodeUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 生成验证码的Servlet
 * @author lujun
 *
 */
@WebServlet("/authCode.action")
public class AuthCodeServlet  extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code= AuthCodeUtil.getCode();
		//把code放到sesstion里面
		HttpSession session=request.getSession();
		session.setAttribute("code", code);
		//使用这个code生成一张图片
		BufferedImage img=AuthCodeUtil.getAuthCodeImg(code);
		//把这个图响应给客户端
		ImageIO.write(img, "JPEG", response.getOutputStream());
		
	}
}
