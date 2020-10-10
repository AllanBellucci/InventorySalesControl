/**
 * 
 */
package model.services;

import java.util.List;

import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.entity.Client;

/**
 * @author ALLAN
 *
 */
public class ClientService {

	private ClientDao clientDao = DaoFactory.createClientDao();

	public ClientService() {
	}

	public List<Client> findAll() {

		return clientDao.findAll();
	}

	public void saveOrUpdate(Client client) {
		if(client.getId()==null || client.getId()==0 ) {
			clientDao.insert(client);
		}else {
			clientDao.update(client);
		}
		
	}
}
