package com.helpers.db;

import java.util.List;

import com.ApplicationManager;
import com.data.GroupData;
import com.helpers.HelperBase;
import com.utils.HibernateUtil;
import com.utils.ModifiedSortedList;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateHelper extends HelperBase {

	public HibernateHelper(ApplicationManager manager) {
	  super(manager);
	}

	public List<GroupData> getListOfGroups() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		try {
          return new ModifiedSortedList<GroupData>(
              (List<GroupData>) session.createQuery("from GroupData").list());
		} finally {
          trans.commit();
		}
	}

	public List<GroupData> getListOfContacts() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		try {
          return new ModifiedSortedList<GroupData>(
              (List<GroupData>) session.createQuery("from ContactData").list());
		} finally {
          trans.commit();
		}
	}
}
