package com.productionDataClientProductionData.DAO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.transaction.TransactionalException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.TransactionException;
import org.hibernate.query.Query;

import com.productionDataClientProductionData.hibernationConnection.Connection;
import com.productionDataClientProductionData.impl.SessionDAO;
import com.productionDataClientProductionData.pojo.Equipment;
import com.productionDataClientProductionData.pojo.Trackers;

public class SessionDaoImpl implements SessionDAO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static SessionFactory sessionFactory = null;
	private static Session session;

	private static void getConnection() {
		try {
			session.getSessionFactory().isOpen();

		} catch (NullPointerException ne) {
			Connection.getConnection();
		}
	}

	@Override
	public boolean updateObjects(LinkedList<Object> ObjectList) {

		int c = 0;

		getConnection();

		for (int x = 0; x < ObjectList.size(); x++) {
			try {

				sessionFactory = Connection.getSessionFactory();
				session = sessionFactory.openSession();

				session.getTransaction().begin();

				try {
					ObjectList.get(x).equals(null);
					// System.out.println(ObjectList.get(x));
					session.saveOrUpdate(ObjectList.get(x));
				} catch (NullPointerException ne) {
					continue;
				}

				
				c = x;
				

				
			} catch (TransactionException e) {
				e.printStackTrace();
			} catch (NullPointerException es) {
				es.printStackTrace();
				// ession.close();
			} catch (TransactionalException ex) {
				ex.printStackTrace();
				// if (tx !=null) {
				session.getTransaction().rollback();
				// }
			} catch (HibernateException hb) {
				hb.printStackTrace();
				System.out.println("");
				// if (tx !=null) {
				// tx.rollback();
				// }
			} finally {
				// tx.commit();
				session.getTransaction().commit();
				// sessionFactory.close();//close threads
				// brandsList.clear();

				System.out.println("Successfully Created" + c);

			}
		}
		return true;
	}

	@Override
	public LinkedList<Object> setObject(Map<Integer, Object> documentList2) {
		LinkedList<Object> ObjectsList = new LinkedList<Object>();

		int x = 0;
		for (Entry<Integer, Object> entry : documentList2.entrySet()) {
			ObjectsList.add(entry.getValue());
			try {
				ObjectsList.get(x);
			} catch (NullPointerException ne) {
				ne.printStackTrace();
			}
		}
		try {
			ObjectsList.get(0);
			updateObjects(ObjectsList);
		} catch (IndexOutOfBoundsException in) {
			// logger.fatal("No data For tables: "+ error);
		}
		return ObjectsList;
	}

	public void closeSession() {
		session.close();
		sessionFactory.close();

	}

	@Override
	public Map<Integer, Object> checkForUpdate(Map<Integer, Object> documentList2,boolean tr) {
		getConnection();
		
		sessionFactory = Connection.getSessionFactory();
		session = sessionFactory.openSession();
	Map<Integer,Object> documentList3 = new TreeMap<Integer,Object>();

		for (Entry<Integer, Object> entry1 : documentList2.entrySet()) {
			if(entry1.getValue() instanceof Equipment) {
			Method mI=null;
			Method mD=null;
			String id =null;
			Date date = null;
			try {
				mI = entry1.getValue().getClass().getDeclaredMethod("getId");

				
			} catch (NoSuchMethodException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				id = (String) mI.invoke(entry1.getValue());
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				String hql = "select * from EQUIPMENT where ID in ('"+id+"')";
				System.out.println(hql);
				Query query = session.createNativeQuery(hql);
				//Query query = session.createNativeQuery(hql, entry1.getValue().getClass());
				//List<Date> latestTelemetries = (List<Date>) query.getResultList();
				List<Equipment> tempObject = (List<Equipment>) query.getResultList();
				
				if(tempObject.isEmpty()) {
					documentList3.put(entry1.getKey(), entry1.getValue());
				}
				boolean isItTrue = true;
			
			} catch (TransactionException e) {
				e.printStackTrace();
			} catch (NullPointerException es) {
				documentList3.put(entry1.getKey(), entry1.getValue());
			} catch (TransactionalException ex) {
				ex.printStackTrace();
				
			} catch (HibernateException hb) {
				hb.printStackTrace();
				System.out.println("");
				// if (tx !=null) {
				// tx.rollback();
				// }
			} catch (IndexOutOfBoundsException ie) {

			}
		 }else if(entry1.getValue() instanceof Trackers&tr) {

				Method mI=null;
				Method mD=null;
				String id =null;
				Date date = null;
				try {
					mI = entry1.getValue().getClass().getDeclaredMethod("getId");

					
				} catch (NoSuchMethodException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					id = (String) mI.invoke(entry1.getValue());
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					String hql = "select * from Trackers where mongo_id in ('"+id+"')";
					System.out.println(hql);
					Query query = session.createNativeQuery(hql);
					//Query query = session.createNativeQuery(hql, entry1.getValue().getClass());
					//List<Date> latestTelemetries = (List<Date>) query.getResultList();
					List<Equipment> tempObject = (List<Equipment>) query.getResultList();
					
					if(tempObject.isEmpty()) {
						documentList3.put(entry1.getKey(), entry1.getValue());
					}
					boolean isItTrue = true;
				
				} catch (TransactionException e) {
					e.printStackTrace();
				} catch (NullPointerException es) {
					documentList3.put(entry1.getKey(), entry1.getValue());
				} catch (TransactionalException ex) {
					ex.printStackTrace();
					
				} catch (HibernateException hb) {
					hb.printStackTrace();
					System.out.println("");
					// if (tx !=null) {
					// tx.rollback();
					// }
				} catch (IndexOutOfBoundsException ie) {

				}
			 
		 }
		}

	
		return documentList3;
	}

