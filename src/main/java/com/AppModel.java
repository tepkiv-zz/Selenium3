package com;

import com.data.ContactData;
import com.data.GroupData;
import com.utils.ModifiedSortedList;

import java.util.List;

public class AppModel {
    private ModifiedSortedList<GroupData> groups;
    private ModifiedSortedList<ContactData> contacts;

    public ModifiedSortedList<ContactData> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactData> contacts) {
        this.contacts = new ModifiedSortedList<ContactData>(contacts);
    }


    public AppModel addContact(ContactData contact) {
        contacts.add(contact);
        return this;
    }

    public AppModel removeContact(int index) {
        contacts.remove(index);
        return this;
    }

    /* */
    public ModifiedSortedList<GroupData> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupData> groups) {
        this.groups = new ModifiedSortedList<GroupData>(groups);
    }

    public AppModel addGroup(GroupData group) {
        groups.add(group);
        return this;
    }

    public AppModel removeGroup(int index) {
        groups.remove(index);
        return this;
    }

}
