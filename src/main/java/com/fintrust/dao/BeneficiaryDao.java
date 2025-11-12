package com.fintrust.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.fintrust.model.BeneficiaryModel;

public class BeneficiaryDao {

  
    public static boolean addBeneficiary(BeneficiaryModel b) {
        String sql = "INSERT INTO beneficiary(user_id, name, account_number, bank_name, ifsc_code) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, b.getUserId());
            ps.setString(2, b.getName());
            ps.setString(3, b.getAccountNumber());
            ps.setString(4, b.getBankName());
            ps.setString(5, b.getIfscCode());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public static List<BeneficiaryModel> getBeneficiariesByUserId(int userId) {
        List<BeneficiaryModel> list = new ArrayList<>();
        String sql = "SELECT * FROM beneficiary WHERE user_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BeneficiaryModel b = new BeneficiaryModel();
                b.setBeneficiaryId(rs.getInt("beneficiary_id"));
                b.setUserId(rs.getInt("user_id"));
                b.setName(rs.getString("name"));
                b.setAccountNumber(rs.getString("account_number"));
                b.setBankName(rs.getString("bank_name"));
                b.setIfscCode(rs.getString("ifsc_code"));
                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}


