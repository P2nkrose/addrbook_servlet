package com.example.addrbook_servlet.controller;

import com.example.addrbook_servlet.dto.AddrbookDTO;
import com.example.addrbook_servlet.service.AddrbookService;
import com.mysql.cj.xdevapi.JsonArray;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Log4j2
@WebServlet("/address_book/*")
public class AddrbookController extends HttpServlet {
    AddrbookService addrbookService = AddrbookService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String RequestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String command = RequestURI.substring(contextPath.length());

        log.info("command" + command);
        resp.setContentType("text/html; charset=utf-8");
        req.setCharacterEncoding("utf-8");

        switch (command){

            // 주소록 추가
            case "/address_book/addForm":   // 폼 입력
                log.info("addForm...");
                req.getRequestDispatcher("/WEB-INF/template/address_book/addForm.jsp").forward(req, resp);
                break;

            case "/address_book/addAction":    // 폼 입력 처리
                log.info("addForm_process...");
                try{
                    if(addrbookService.addAddr(req)){
                        resp.sendRedirect("list");
                    }
                } catch (Exception e){
                    throw new RuntimeException(e);
                }
                break;

            // 주소록 리스트
            case "/address_book/list":
                log.info("list...");
                try{
                    List<AddrbookDTO> addrlists = addrbookService.getList(req);
                    JSONArray jsonArray = new JSONArray();
                    for(AddrbookDTO addrlist: addrlists){
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("ab_id", addrlist.getAb_id());
                        jsonObject.put("ab_name", addrlist.getAb_name());
                        jsonObject.put("ab_email", addrlist.getAb_tel());
                        jsonObject.put("ab_tel", addrlist.getAb_tel());
                        jsonObject.put("ab_birth", addrlist.getAb_birth());
                        jsonObject.put("ab_comdept", addrlist.getAb_comdept());
                        jsonObject.put("ab_memo", addrlist.getAb_memo());
                        jsonArray.add(jsonObject);
                    }
                    req.setAttribute("addrlists", jsonArray.toJSONString());
                    req.getRequestDispatcher("/WEB-INF/template/address_book/list.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e){
                    throw new RuntimeException(e);
                }
                break;

            case "/address_book/modifyForm":
                log.info("modifyMember...");
                try {
                    AddrbookDTO addrbookDTO = addrbookService.getMemberOne(req);
                    log.info("addrbookDTO", addrbookDTO);
                    req.setAttribute("addrbook", addrbookDTO);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                req.getRequestDispatcher("/WEB-INF/template/address_book/modifyForm.jsp").forward(req, resp);
                break;

            case "/address_book/modifyAction":
                log.info("modifyMemberAction");
                try {
                    if (addrbookService.modifyAddr(req)) {
                        resp.sendRedirect("/address_book/list");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/address_book/removeAction":
                log.info("removeAction");
                try {
                    if (addrbookService.removeAddr(req)) {
                        resp.sendRedirect("/address_book/list");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;




        }
    }
}
