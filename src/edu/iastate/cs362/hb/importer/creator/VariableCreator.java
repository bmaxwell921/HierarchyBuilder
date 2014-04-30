package edu.iastate.cs362.hb.importer.creator;

import java.lang.reflect.Type;

import com.google.gson.InstanceCreator;

import edu.iastate.cs362.hb.model.IVariable;
import edu.iastate.cs362.hb.model.impl.HBVariable;

public class VariableCreator implements InstanceCreator<IVariable> {

	@Override
	public IVariable createInstance(Type arg0) {
		return new HBVariable();
	}

}
