package edu.iastate.cs362.hb.importer.creator;

import java.lang.reflect.Type;

import com.google.gson.InstanceCreator;

import edu.iastate.cs362.hb.model.IMethod;
import edu.iastate.cs362.hb.model.impl.HBMethod;

public class MethodCreator implements InstanceCreator<IMethod>{

	@Override
	public IMethod createInstance(Type arg0) {
		return new HBMethod();
	}

}
