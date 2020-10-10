/**
 * 
 */
package model.dao;

import java.util.List;

import model.entity.Client;

/**
 * @author ALLAN
 *
 */
public interface ClientDao {

	void insert (Client client);
	void update (Client client);
	void deleteById (Integer id);
	Client findById (Integer id);
	List<Client> findAll();
	
}
