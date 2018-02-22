package com.helpers.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ApplicationManager;
import com.data.ContactData;
import com.data.GroupData;
import com.utils.ModifiedSortedList;

public class JdbcHelper {

    private Connection conn;

    public JdbcHelper(ApplicationManager app, String url) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ModifiedSortedList<GroupData> listGroups() {
        ModifiedSortedList<GroupData> groups = new ModifiedSortedList<GroupData>();
        Statement st = null;
        ResultSet res = null;

        try {
            st = conn.createStatement();
            res = st.executeQuery("SELECT group_id,group_name,group_header,group_footer FROM group_list");
            while (res.next()) {
                GroupData gr = new GroupData().setId(res.getString(1)).withName(res.getString(2)).withHeader(res.getString(3)).withFooter(res.getString(4));
                groups.add(gr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                res.close();
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return groups;
    }

    public ModifiedSortedList<ContactData> listContacts() {
        ModifiedSortedList<ContactData> groups = new ModifiedSortedList<ContactData>();
        Statement st = null;
        ResultSet res = null;

        try {
            st = conn.createStatement();
            res = st.executeQuery("SELECT id,firstname,lastname FROM addressbook");
            while (res.next()) {
                ContactData contactData = new ContactData().setId(res.getString(1)).withFirstName(res.getString(2)).withLastName(res.getString(3));
                groups.add(contactData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                res.close();
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return groups;
    }
}
