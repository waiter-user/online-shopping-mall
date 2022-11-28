package com.java.manager.servlet;

import com.java.manager.pojo.Catelog;
import com.java.manager.pojo.Goods;
import com.java.manager.service.GoodsService;
import com.java.manager.service.impl.GoodsServiceImpl;
import com.java.manager.util.PageBean;
import com.java.manager.util.WebRequestUtil;

import javax.lang.model.element.VariableElement;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;

@WebServlet("/goods")
//servlet3.0支持文件上传的注解
@MultipartConfig
public class GoodsServlet extends ViewBaseServlet {
    /**
     * 分页获取商品列表
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private GoodsService goodsService = new GoodsServiceImpl();

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //要处理的页号
        Integer pageNo = WebRequestUtil.getParamValue(request, "pageNo", PageBean.DEFAULT_PAGENO);
        //要处理的每页记录数
        Integer pageSize = WebRequestUtil.getParamValue(request, "pageSize", PageBean.DEAULT_PAGESIZE);
        //获取输入的名称关键字
        String keyword = request.getParameter("keyword");
        PageBean<Goods> pageBean = goodsService.queryByPage(keyword, pageNo, pageSize);
        request.setAttribute("pb", pageBean);
        request.setAttribute("keyword", keyword);
        super.processTemplate("goods/list", request, response);
    }

    //修改商品前的回显数据处理
    protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        //调用service根据id获取商品
        Goods goods = goodsService.getGoodsById(id);
        //将商品对象存储到request域中
        request.setAttribute("gd", goods);
        //跳转到修改页面
        super.processTemplate("goods/update", request, response);
    }

    //处理修改商品的请求
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取表单提交过来的商品信息
        Integer goodsId = Integer.valueOf(request.getParameter("goodsId"));
        String goodsName = request.getParameter("goodsName");
        Integer marketPrice = Integer.valueOf(request.getParameter("market_price"));
        Integer malltPrice = Integer.valueOf(request.getParameter("mall_price"));
        Integer catelogId = Integer.valueOf(request.getParameter("catelog_id"));
        //把数据封装成Goods对象
        Goods goods = new Goods();
        goods.setGoods_id(goodsId);
        goods.setGoods_name(goodsName);
        goods.setMarket_price(marketPrice);
        goods.setMall_price(malltPrice);
        Catelog catelog = new Catelog();
        catelog.setCatelog_id(catelogId);
        goods.setCatelog(catelog);
        //调用Service中的修改商品方法
        Boolean flag = goodsService.updateGoods(goods);
        //修改成功后跳转到商品列表
        if (flag) {
            response.sendRedirect(request.getContextPath() + "/goods?action=list");
        }
    }

    /**
     * 下载商品图片
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void downloadPic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取待下载的图片路径
        String img = request.getParameter("img");
        //下载：将服务器上的图片下载到本地，就是IO交互，读取服务器上的资源文件的路径，用输出流写到本地文件中
        //1.获取待下载文件的真实路径（部署的工程里面的路径）
        String realPath = request.getServletContext().getRealPath(img);
        //2.创建输出流，用来读取文件的数据
        FileInputStream fis = new FileInputStream(new File(realPath));
        //3.创建输出流
        ServletOutputStream fos = response.getOutputStream();
        //获取文件名
        String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
        //4.弹出下载文件的对话框，提示用户保存到本地
        response.setHeader("Content-Disposition", "attachment;FileName=" + URLEncoder.encode(fileName, "utf-8"));
        //5.通过流来拷贝
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = fis.read(b)) != -1) {
            fos.write(b, 0, len);
        }
        //6.关闭资源
        fis.close();
    }

    /**
     * 准备添加商品，跳转到添加页面
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.getRequestDispatcher("WEB-INF/templates/goods/add.html").forward(request, response);
        super.processTemplate("goods/add",request,response);
    }

    /**
     * 处理添加商品的请求
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取普通表单参数的值（除文件域外的参数）
        String goodsName = request.getParameter("goods_name");
        String goodsMiaoshu = request.getParameter("goods_miaoshu");
        //获取文件域，servlet3.0支持文件上传的方案很简单，需要在servlet类上添加注解
        Part part = request.getPart("goods_pic");
        String header = part.getHeader("Content-Dispositon");
        String fileName = this.getFileName(header);
        //上传到服务器，服务器需要用upload目录来存储，提供存储文件的真实路径,File.separator获取分隔符
        String path = request.getServletContext().getRealPath("upload")+File.separator+fileName;
        //保存文件
        part.write(path);
        Integer marketPrice = Integer.valueOf(request.getParameter("market_price"));
        Integer mallPrice = Integer.valueOf(request.getParameter("mall_price"));
        Integer catelogId = Integer.valueOf(request.getParameter("catelog_id"));
        Integer stockNum = Integer.valueOf(request.getParameter("stock_num"));
        String goodsAddress = request.getParameter("goods_address");
        Date enterDate = Date.valueOf(request.getParameter("enter_date"));
        //封装Goods对象
        Goods goods=new Goods();
        goods.setGoods_name(goodsName);
        goods.setGoods_miaoshu(goodsMiaoshu);
        goods.setGoods_pic("upload/"+fileName);
        goods.setMarket_price(marketPrice);
        goods.setMall_price(mallPrice);
        //catelogId封装到catelog对象上
        Catelog catelog=new Catelog();
        catelog.setCatelog_id(catelogId);
        //将catelog对象与goods对象关联起来
        goods.setCatelog(catelog);
        goods.setStock_num(stockNum);
        goods.setGoods_address(goodsAddress);
        goods.setEnter_date(enterDate);
        Boolean flag = goodsService.addGoods(goods);
        if (flag) {
            //修改成功，跳转到列表页面
            response.sendRedirect(request.getContextPath()+"/goods?action=list");
        }
    }
    private String getFileName(String header){
        return header.substring(header.lastIndexOf("=") + 2, header.length() - 1);
    }
}
