package com.data;


import com.TestBase;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GroupData implements Comparable<GroupData> {
    private TestBase testBase = new TestBase();
    private String name;
    private String header;
    private String footer;

    public GroupData(String groupname, String header, String footer) {
        this.name = groupname;
        this.header = header;
        this.footer = footer;
    }

    public GroupData() {
    }

    @DataProvider
    public Iterator<Object[]> randomValidGroupGenerator() {
        List<Object[]> list = new ArrayList<Object[]>();
        for (int i = 0; i < 5; i++) {
            GroupData group = new GroupData().withName(TestBase.generateRandomString())
                    .withHeader(TestBase.generateRandomString()).withFooter(TestBase.generateRandomString());
            list.add(new Object[]{group});
        }
        return list.iterator();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GroupData{");
        sb.append("name='").append(name).append('\'');
        sb.append(", header='").append(header).append('\'');
        sb.append(", footer='").append(footer).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        return name != null ? name.equals(groupData.name) : groupData.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public int compareTo(GroupData other) {
        return this.name.toLowerCase().compareTo(other.name.toLowerCase());
    }

    public GroupData withName(String name) {
        this.name = name;
        return this;
    }

    public GroupData withHeader(String header) {
        this.header = header;
        return this;
    }

    public GroupData withFooter(String footer) {
        this.footer = footer;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }
}
