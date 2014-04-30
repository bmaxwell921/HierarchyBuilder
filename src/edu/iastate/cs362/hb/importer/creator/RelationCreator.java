package edu.iastate.cs362.hb.importer.creator;

import java.lang.reflect.Type;

import com.google.gson.InstanceCreator;

import edu.iastate.cs362.hb.model.IRelationship;
import edu.iastate.cs362.hb.model.impl.HBRelationship;

public class RelationCreator implements InstanceCreator<IRelationship> {

	@Override
	public IRelationship createInstance(Type arg0) {
		return new HBRelationship();
	}

}
