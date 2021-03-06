/**
 * This file forms part of the Source code trail found at https://wiki.hybris.com/display/pm/Trail
 */
package de.hybris.platform.cuppytrail;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import de.hybris.platform.cuppytrail.daos.StadiumDAO;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;


/**
 * This class belongs to the Source Code Trail documented at https://wiki.hybris.com/display/pm/Source+Code+Tutorial
 * 
 * The purpose of this test is to illustrate DAO best practices and behaviour.
 * 
 * The DAO logic is factored into a separate POJO. Stepping into these will illustrate how to write and execute
 * FlexibleSearchQueries - the basis on which DAOs operate.
 * 
 * @see "https://wiki.hybris.com/display/pm/Trail+~+DAOs"
 */
public class StadiumDAOIntegrationTest extends ServicelayerTransactionalTest
{
	/** As this is an integration test the test to class gets injected here. */
	@Resource
	private StadiumDAO stadiumDAO;

	/** ModelService used for creation of test data. */
	@Resource
	private ModelService modelService;

	/** Name of test stadium. */
	private static final String STADIUM_NAME = "wembley";
	/** Capacity of test stadium. */
	private static final Integer STADIUM_CAPACITY = Integer.valueOf(12345);

	@Test
	public void stadiumDAOTest()
	{
		List<StadiumModel> stadiumsByCode = stadiumDAO.findStadiumsByCode(STADIUM_NAME);
		assertTrue("There should be no Stadium found", stadiumsByCode.isEmpty());

		List<StadiumModel> allStadiums = stadiumDAO.findStadiums();
		final int size = allStadiums.size();

		final StadiumModel stadiumModel = new StadiumModel();
		stadiumModel.setCode(STADIUM_NAME);
		stadiumModel.setCapacity(STADIUM_CAPACITY);
		modelService.save(stadiumModel);

		allStadiums = stadiumDAO.findStadiums();
		assertEquals(size + 1, allStadiums.size());
		assertEquals("Unexpected stadium found", stadiumModel, allStadiums.get(allStadiums.size() - 1));

		stadiumsByCode = stadiumDAO.findStadiumsByCode(STADIUM_NAME);
		assertEquals("Find the one we just saved", 1, stadiumsByCode.size());
		assertEquals("Check the names", STADIUM_NAME, stadiumsByCode.get(0).getCode());
		assertEquals("Check the capacity", STADIUM_CAPACITY, stadiumsByCode.get(0).getCapacity());
	}

	@Test
	public void stadiumDAOCornerCases()
	{
		// Calling findStadium with the empty string returns no results
		List<StadiumModel> stadiums = stadiumDAO.findStadiumsByCode("");
		assertTrue("There should be no Stadium found", stadiums.isEmpty());

		// Calling getStadium with null throws a IllegalArgumentException
		try
		{
			stadiums = stadiumDAO.findStadiumsByCode(null);
			fail("Expected getStadium(null) to throw IllegalArgumentException");
		}
		catch (final IllegalArgumentException e) //NOPMD
		{
			// 
		}

		// Create a StadiumModel and call saveStadiumModel 
		final StadiumModel stadiumModel = new StadiumModel();
		stadiumModel.setCapacity(Integer.valueOf(1000));
		stadiumModel.setCode(STADIUM_NAME);
		modelService.save(stadiumModel);
	}
}
