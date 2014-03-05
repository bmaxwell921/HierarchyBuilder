package edu.iastate.cs362.hb.commands;

public class Argument {
	public String name;
	public boolean tag;
	public String value;
	public boolean use;
	
	public Argument(String aName, boolean aTag, String aValue)
	{
		name = "-" + aName;
		tag = aTag;
		value = aValue;
		use = false;
	}
	
	/**
	 * Parses the line and the commands out.
	 * @param line
	 * @return
	 */
	public String Parse(String line)
	{
		line = line.trim();
		if(line.toUpperCase().startsWith(name))
		{
			use = true;
			line = line.substring(name.length());
			if(tag)
			{
				int end = line.indexOf('-');

				line = line.trim();
				if(end == -1)
				{
					end = line.length();
				}
				value = line.substring(0, end);
				line = line.substring(value.length());
			}
		}
		return line;
	}
}
