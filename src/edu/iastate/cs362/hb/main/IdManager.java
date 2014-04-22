package edu.iastate.cs362.hb.main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import edu.iastate.cs362.hb.model.attributes.Identifiable;

/**
 * Class used to generate ids for Identifiables in 
 * a HierarchyBuilder project. It also serves to translate
 * between strings to ids.
 * 
 * Singleton class
 * @author Brandon
 *
 */
public class IdManager {
	
	private static final String INFO_DELIM = ".";
	public static final long INVALID_ID = -1l;
	
	/*
	 * -------------------------------------------------------------------------------------
	 * Singleton info
	 * -------------------------------------------------------------------------------------
	 */
	
	// Singleton instance
	private static IdManager instance;
	
	private IdManager() {
		idMap = new HashMap<>();
		nextId = 0;
	}
	
	public static IdManager getInstance() {
		if (instance == null) {
			instance = new IdManager();
		}
		
		return instance;
	}
	
	//-------------------------------------------------------------------------------------
	
	/*
	 * -------------------------------------------------------------------------------------
	 * Instance info
	 * -------------------------------------------------------------------------------------
	 */
	
	// A map from identification string to id
	private Map<String, Long> idMap;
	
	private long nextId;

	/**
	 * Registers the given identifiable into the system.
	 * 
	 * Side effect: Sets the given Identifiable's id
	 * @param i
	 * 		
	 * @param info
	 * @return
	 */
	public void registerObject(Identifiable i, String... info) {
		idMap.put(buildKey(info), nextId);
		i.setId(nextId++);
	}
	
	/**
	 * Returns the id associated with the given information
	 * @param info
	 * @return
	 * 		the id of the given key, or INVALID_ID
	 */
	public long accessId(String...info) {
		String key = buildKey(info);
		if (!idMap.containsKey(key)) {
			return INVALID_ID;
		}
		return idMap.get(key);
	}
	
	/**
	 * Updates information associated with the given id
	 * @param id
	 * @param info
	 */
	public void updateInfo(long id, String...info) {
		for (String key : idMap.keySet()) {
			if (idMap.get(key) != null && idMap.get(key).equals(id)) {
				idMap.put(buildKey(info), id);
				return;
			}
		}
	}
	
	/**
	 * Removes the given id
	 * @param id
	 */
	public void remove(long id) {
		Set<String> keys = idMap.keySet();
		for (Iterator<String> iter = keys.iterator(); iter.hasNext(); ) {
			String key = iter.next();
			if (idMap.get(key).equals(id)) {
				iter.remove();
				return;
			}
		}
	}
	
	/**
	 * Keys are build as fully qualified names for Identifiables,
	 * so if the user knows the fully qualified name they can use
	 * this method
	 * @param key
	 * @return
	 * 		the id of the given key, or INVALID_ID
	 */
	public long accessIdWithKey(String key) {
		if (!idMap.containsKey(key)) {
			return INVALID_ID;
		}
		return idMap.get(key);
	}
	
	private String buildKey(String...info) {
		StringBuilder sb = new StringBuilder();
		for (String iPiece : info) {
			sb.append(iPiece).append(INFO_DELIM);
		}
		return sb.toString();
	}
	
	/**
	 * Method used when reading DesignDocs from file
	 * @param id
	 */
	public void setNextId(long id) {
		this.nextId = id;
	}
}
