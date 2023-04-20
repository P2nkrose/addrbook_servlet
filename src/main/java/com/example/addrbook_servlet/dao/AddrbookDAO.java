package com.example.addrbook_servlet.dao;

import com.example.addrbook_servlet.dto.AddrbookDTO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Log4j2
public class AddrbookDAO {

    //싱글톤
    private static AddrbookDAO instance;
    private AddrbookDAO(){

    }
    public static AddrbookDAO getInstance(){
        if(instance == null)
            instance = new AddrbookDAO();
        return instance;
    }

    public boolean insertAddr(AddrbookDTO addrbookDTO) throws SQLException, ClassNotFoundException{
        log.info("insertAddr()...");
        String query = "INSERT INTO addrbook VALUES (NULL, ?, ?, ?, ?, ?, ?)";
        int result;

        @Cleanup Connection connection = DBConnection.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, addrbookDTO.getAb_name());
        preparedStatement.setString(2, addrbookDTO.getAb_email());
        preparedStatement.setString(3, addrbookDTO.getAb_tel());
        preparedStatement.setString(4, addrbookDTO.getAb_birth());
        preparedStatement.setString(5, addrbookDTO.getAb_comdept());
        preparedStatement.setString(6, addrbookDTO.getAb_memo());
        result = preparedStatement.executeUpdate();

        return result == 1;
    }

    public ArrayList<AddrbookDTO> selectAddr() throws SQLException, ClassNotFoundException{
        log.info("getList()...");
        String query = "SELECT * FROM addrbook";

        ArrayList<AddrbookDTO> list = new ArrayList<AddrbookDTO>();
        @Cleanup Connection connection = DBConnection.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            AddrbookDTO addrbook = new AddrbookDTO();
            addrbook.setAb_id(resultSet.getInt("ab_id"));
            addrbook.setAb_name(resultSet.getString("ab_name"));
            addrbook.setAb_email(resultSet.getString("ab_email"));
            addrbook.setAb_tel(resultSet.getString("ab_tel"));
            addrbook.setAb_birth(resultSet.getString("ab_birth"));
            addrbook.setAb_comdept(resultSet.getString("ab_comdept"));
            addrbook.setAb_memo(resultSet.getString("ab_memo"));
            log.info(addrbook);
            list.add(addrbook);
        }
        return list;
    }

    public boolean updateAddr(AddrbookDTO addrbookDTO) throws SQLException, ClassNotFoundException {
        log.info("updateAddr...");
        String query = "UPDATE addrbook SET ab_name=?, ab_email=?, ab_tel=?, ab_comdept=?, ab_memo=? WHERE ab_id=?";

        @Cleanup Connection connection = DBConnection.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, addrbookDTO.getAb_name());
        preparedStatement.setString(2, addrbookDTO.getAb_email());
        preparedStatement.setString(3, addrbookDTO.getAb_tel());
        preparedStatement.setString(4, addrbookDTO.getAb_comdept());
        preparedStatement.setString(5, addrbookDTO.getAb_memo());
        preparedStatement.setInt(6, addrbookDTO.getAb_id());
        int result = preparedStatement.executeUpdate();

        return result == 1;
    }

    public AddrbookDTO selectMember(int ab_id) throws SQLException, ClassNotFoundException {
        log.info("selectMember...");
        String query = "SELECT * FROM addrbook WHERE ab_id=?";

        AddrbookDTO addrbook = null;
        @Cleanup Connection connection = DBConnection.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, ab_id);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            addrbook = new AddrbookDTO();
            addrbook.setAb_id(resultSet.getInt("ab_id"));
            addrbook.setAb_name(resultSet.getString("ab_name"));
            addrbook.setAb_email(resultSet.getString("ab_email"));
            addrbook.setAb_tel(resultSet.getString("ab_tel"));
            addrbook.setAb_birth(resultSet.getString("ab_birth"));
            addrbook.setAb_comdept(resultSet.getString("ab_comdept"));
            addrbook.setAb_memo(resultSet.getString("ab_memo"));
        }
        return addrbook;

    }

    public boolean deleteAddr(int ab_id) throws SQLException, ClassNotFoundException {
        log.info("deleteAddr...");
        String query = "DELETE FROM addrbook WHERE ab_id=?";

        @Cleanup Connection connection = DBConnection.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, ab_id);
        return preparedStatement.executeUpdate() == 1;
    }





}
