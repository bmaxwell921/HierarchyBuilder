package edu.iastate.cs362.hb.importer.creator;

import java.lang.reflect.Type;

import com.google.gson.InstanceCreator;

import edu.iastate.cs362.hb.model.IObject;

public class ObjectCreator implements InstanceCreator<IObject> {

	@Override
	public IObject createInstance(Type type) {
		System.out.println(type);
		return null;
	}

}
