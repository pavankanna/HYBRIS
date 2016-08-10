package de.hybris.platform.cuppytrail.web.data;

import de.hybris.platform.core.PK;


/**
 * This class belongs to the Source Code Trail documented at https://wiki.hybris.com/display/pm/Source+Code+Tutorial It
 * is a data transfer object for the Stadium Facade
 */
public class StadiumProductDataTO
{

	private final PK pk;
	private final String name;
	private final String description;
	private final double price;

	public StadiumProductDataTO(final PK pk, final String name, final String description, final double price)
	{
		this.pk = pk;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public String getName()
	{
		return name;
	}

	public double getPrice()
	{
		return price;
	}

	public String getDescription()
	{
		return description;
	}

	public PK getPk()
	{
		return pk;
	}

}
