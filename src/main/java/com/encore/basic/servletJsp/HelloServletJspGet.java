package com.encore.basic.servletJsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//controller가 아닌 WebServlet을 통해 라우팅
    @WebServlet("/hello-servlet-jsp-get")
public class HelloServletJspGet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        기본패턴 : req를 받아, res를 return해주는 형식
        req.setAttribute("myData", "jsp test data");
        req.getRequestDispatcher("/WEB-INF/views/hello-jsp.jsp").forward(req, resp);

    }

////   service() 메서드는 서블릿에 들어오는 모든요청(get, post, put, delete등)을 처리
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("myData", "jsp test data");
//        req.getRequestDispatcher("/WEB-INF/views/hello-jsp.jsp").forward(req, resp);
//
//    }


}
