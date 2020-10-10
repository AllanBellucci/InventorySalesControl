/**
 * 
 */
package model.dao;

import db.DB;
import model.dao.impl.ClientDaoJDBC;

/**
 * @author ALLAN
 *
 */
public class DaoFactory {

	public static ClientDao createClientDao() {
		return new ClientDaoJDBC(DB.getConnection());
	}
	
}
