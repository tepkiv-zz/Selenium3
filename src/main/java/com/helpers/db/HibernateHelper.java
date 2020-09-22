package com.helpers.db;

import com.BrowserController;
import com.data.ContactData;
import com.data.GroupData;
import com.helpers.HelperBase;
import com.utils.HibernateUtil;
import com.utils.ModifiedSortedList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateHelper extends HelperBase {

	public HibernateHelper(BrowserController manager) {
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

	public List<ContactData> getListOfContacts() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		try {
          return new ModifiedSortedList<ContactData>(
              (List<ContactData>) session.createQuery("from ContactData").list());
		} finally {
          trans.commit();
		}
	}
}
