package de.hybris.platform.cuppytrail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.cuppytrail.web.data.StadiumData;
import de.hybris.platform.cuppytrail.web.data.StadiumProductDataTO;
import de.hybris.platform.cuppytrail.web.facades.StadiumFacade;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;


/**
 * This test file tests and demonstrates the behavior of the StadiumFacade's methods getAllStadiums and getStadium.
 * 
 * The Facade will usually call StadiumService to retrieve its data but we will mock that out to focus our tests on
 * StadiumFacade only. The framework we use to do the mocking is called EasyMock.
 */
public class StadiumFacadeIntegrationTest extends ServicelayerTransactionalTest
{
	@Resource
	private StadiumFacade stadiumFacade;

	@Resource
	private CatalogService catalogService;

	@Resource
	private ModelService modelService;

	private StadiumModel stadiumModel;
	private final String STADIUM_NAME = "wembley";
	private final Integer STADIUM_CAPACITY = Integer.valueOf(90000);

	@Before
	public void setUp()
	{
		// This instance of a StadiumModel will be used by the tests
		stadiumModel = new StadiumModel();
		stadiumModel.setCode(STADIUM_NAME);
		stadiumModel.setCapacity(STADIUM_CAPACITY);
	}


	/**
	 * Tests exception behavior by getting a stadium which doesn't exist
	 */
	@Test(expected = UnknownIdentifierException.class)
	public void testFailBehavior()
	{
		stadiumFacade.getStadium(STADIUM_NAME);
	}


	/**
	 * Tests and demonstrates the Facade's methods
	 */
	@Test
	public void testStadiumFacade()
	{
		List<StadiumData> stadiumListData = stadiumFacade.getStadiums();
		assertNotNull(stadiumListData);
		final int size = stadiumListData.size();
		modelService.save(stadiumModel);

		stadiumListData = stadiumFacade.getStadiums();
		assertNotNull(stadiumListData);
		assertEquals(size + 1, stadiumListData.size());
		assertEquals(stadiumListData.get(size).getName(), STADIUM_NAME);
		assertEquals(stadiumListData.get(size).getCapacity(), STADIUM_CAPACITY.toString());

		final StadiumData persistedStadiumData = stadiumFacade.getStadium(STADIUM_NAME);
		assertNotNull(persistedStadiumData);
		assertEquals(persistedStadiumData.getName(), STADIUM_NAME);
		assertEquals(persistedStadiumData.getCapacity(), STADIUM_CAPACITY.toString());
	}

	@Test
	public void testGetAllStadiumProducts1()
	{
		final List<StadiumProductDataTO> stadiumProductDataTOs = stadiumFacade.getAllStadiumProducts();
		assertNotNull(stadiumProductDataTOs);
	}

	@Test
	public void testGetAllStadiumProducts2()
	{
		catalogService.addSessionCatalogVersion("Catalog1", "Version1");

		final List<StadiumProductDataTO> stadiumProductDataTOs = stadiumFacade.getAllStadiumProducts();
		assertEquals(2, stadiumProductDataTOs.size());
	}
}