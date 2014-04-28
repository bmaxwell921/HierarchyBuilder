package edu.iastate.cs362.hb.main;

import java.util.ArrayList;

public class CacheManager {

	private ArrayList<Wrapper> list;
	private long nextID;
	private static CacheManager instance;

	private class Wrapper {
		private Class<? extends Object> type;
		private Object object;
		private String name;

		public Wrapper(Object obj, String name) {
			type = obj.getClass();
			object = obj;
			this.name = name;
		}
	}

	private CacheManager() {
		list = new ArrayList<Wrapper>();
		nextID = 0;
	}

	public static CacheManager getInstance() {
		if (instance == null) {
			instance = new CacheManager();
		}
		return instance;
	}

	public long addItem(Object o, String name) {
		list.add(new Wrapper(o, name));
		return nextID++;
	}

	public void clear() {
		// Utilize garbage collector to erase current cache
		list = new ArrayList<Wrapper>();
		nextID = 0;
	}

	public Object getObject(long id) {
		return list.get((int) id).object;
	}

	public Class<?> getType(long id) {
		return list.get((int) id).type;
	}

	public static String getMappings() {
		if (instance == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < instance.list.size(); i++) {
			String str = i + "/t" + instance.list.get(i).name + "\n";
			sb.append(str);
		}
		return sb.toString();
	}

	public static String getMappings(Class<?> type) {
		if (instance == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < instance.list.size(); i++) {
			if (instance.list.get(i).type == type) {
				String str = i + "/t" + instance.list.get(i).name + "\n";
				sb.append(str);
			}
		}
		return sb.toString();
	}
	
	public static String getMappings(long id){
		if(instance == null) return null;
		return id + "/t" + instance.list.get((int) id).name;
	}

}
