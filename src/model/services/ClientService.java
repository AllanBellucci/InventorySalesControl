/**
 * 
 */
package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entity.Client;

/**
 * @author ALLAN
 *
 */
public class ClientService {

	public ClientService() {
	}

	public List<Client> findAll() {

		List<Client> list = new ArrayList<Client>();
		list.add(new Client(1,"Allan"));
		list.add(new Client(2,"Cris"));
		list.add(new Client(3,"Gabriel"));
		list.add(new Client(3,"Lais"));
		
		return list;
	}

}
