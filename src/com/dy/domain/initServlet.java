package com.dy.domain;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "initServlet")
public class initServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void destroy() {
        super.destroy();
        //把servletContext保存到文件中
        //从record中读取浏览量
        //得到文件全路径
        FileWriter fileWriter=null;
        BufferedWriter bufferedWriter=null;
        String filePath = this.getServletContext().getRealPath("record");
        //打开文件
        try {
            fileWriter = new FileWriter(filePath);
            bufferedWriter = new BufferedWriter(fileWriter);
            //从servletContext中读取访问量
            String nums= (String) this.getServletContext().getAttribute("nums");
            //写回文件
            bufferedWriter.write(nums);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                bufferedWriter.close();
                fileWriter .close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void init() throws ServletException {
        //从record中读取浏览量
        FileReader fileReader=null;
        BufferedReader bufferedReader=null;
        //得到文件全路径
        String filePath = this.getServletContext().getRealPath("record");
        //打开文件
        try {
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            String nums = bufferedReader.readLine();
            this.getServletContext().setAttribute("nums", nums);

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                fileReader.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
