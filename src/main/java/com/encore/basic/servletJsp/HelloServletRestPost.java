package com.encore.basic.servletJsp;


import com.encore.basic.domain.Hello;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello-servlet-rest-post")
public class HelloServletRestPost extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        BufferedReader br = req.getReader();
//        StringBuilder sb = new StringBuilder();
//        String line = null;
//
//        while ((line = br.readLine()) != null){
//            sb.append(line);
//        }


        ObjectMapper objectMapper = new ObjectMapper();
//      데이터를 전달 받았을 때, json으로 안들어올 경우가 있어 가능하면 한번 json형태인지 확인하고 전달해주는 것이 좋다.
        Hello hello = objectMapper.readValue(req.getReader(), Hello.class);
        System.out.println(hello);

        PrintWriter out = resp.getWriter();

        out.print("ok");
        out.flush();
    }
}
