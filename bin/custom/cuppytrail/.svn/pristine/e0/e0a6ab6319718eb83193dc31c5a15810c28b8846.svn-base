package de.hybris.platform.cuppytrail;

import static org.junit.Assert.fail;

import de.hybris.bootstrap.annotations.UnitTest;

import org.junit.Test;


/**
 * This test relates to the Tutorial Trail Step found at https://wiki.hybris.com/display/pm/Trail+~+New+Data+Model
 * 
 * Its purpose is to illustrate the classes that are generated for the Data Entity ("item") Stadium that is specified in
 * cuppytrail-items.xml
 */
@UnitTest
public class GeneratedFilesTest
{
	@Test
	public void testStadiumItemsGenerated()
	{

		try
		{
			Class.forName("de.hybris.platform.cuppytrail.model.StadiumModel");

			// The DTOs and Resources are used for the RESTful CRUD support we will uncomment 
			// these lines later in the trail when we discuss webservices
			// Class.forName("de.hybris.platform.cuppytrail.dto.StadiumDTO");
			// Class.forName("de.hybris.platform.cuppytrail.dto.StadiumsDTO");
			// Class.forName("de.hybris.platform.cuppytrail.resource.StadiumResource");
			// Class.forName("de.hybris.platform.cuppytrail.resource.StadiumsResource");
		}
		catch (final ClassNotFoundException e)
		{
			fail("Generated class not found: " + e.getLocalizedMessage());
		}


	}

}