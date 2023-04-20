package com.example.addrbook_servlet.service;

import com.example.addrbook_servlet.dao.AddrbookDAO;
import com.example.addrbook_servlet.dto.AddrbookDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Log4j2
public class AddrbookService {
    AddrbookDAO dao = AddrbookDAO.getInstance();
    private static AddrbookService instance;
    private AddrbookService(){

    }

    public static AddrbookService getInstance(){
        if(instance == null)
            instance = new AddrbookService();
        return instance;
    }
    public boolean addAddr(HttpServletRequest request) throws SQLException, ClassNotFoundException{
        log.info("addAddr...");
        boolean flag = false;

        AddrbookDTO addrbook = new AddrbookDTO();
        addrbook.setAb_name(request.getParameter("name"));
        addrbook.setAb_email(request.getParameter("email"));
        addrbook.setAb_tel(request.getParameter("tel"));
        addrbook.setAb_birth(request.getParameter("birth"));
        addrbook.setAb_comdept(request.getParameter("comdept"));
        addrbook.setAb_memo(request.getParameter("memo"));
        log.info(addrbook);

        return dao.insertAddr(addrbook);
    }

    public List<AddrbookDTO> getList(HttpServletRequest request) throws SQLException, ClassNotFoundException{
        log.info("getList...");
        List<AddrbookDTO> addrlist = dao.selectAddr();
        request.setAttribute("addrlist", addrlist);


        return addrlist;
    }

    public boolean modifyAddr(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        log.info("modifyAddr()...");

        AddrbookDTO addrbook = new AddrbookDTO();
        addrbook.setAb_name(request.getParameter("name"));
        addrbook.setAb_email(request.getParameter("email"));
        addrbook.setAb_tel(request.getParameter("tel"));
        addrbook.setAb_comdept(request.getParameter("comdept"));
        addrbook.setAb_memo(request.getParameter("memo"));
        addrbook.setAb_id(Integer.parseInt(request.getParameter("ab_id")));

        return dao.updateAddr(addrbook);
    }

    public AddrbookDTO getMemberOne(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String ab_id = request.getParameter("ab_id");
        return dao.selectMember(Integer.parseInt(ab_id));
    }



    public boolean removeAddr(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        log.info("removeAddr()...");

        String ab_id = request.getParameter("ab_id");
        return dao.deleteAddr(Integer.parseInt(ab_id));
    }


}
