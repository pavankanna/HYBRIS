package de.hybris.platform.cuppytrail;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.easymock.classextension.EasyMock.createMock;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.cuppytrail.web.data.StadiumData;
import de.hybris.platform.cuppytrail.web.facades.impl.DefaultStadiumFacade;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


/**
 * This class belongs to the Source Code Trail documented at https://wiki.hybris.com/display/pm/Source+Code+Tutorial
 * 
 * This test file tests and demonstrates the behavior of the StadiumFacada's methods getAllStadiums and getStadium.
 * 
 * The Facade will usually call StadiumService to retrieve its data but we will mock that out to focus our tests on
 * StadiumFacade only. The framework we use to do the mocking is called EasyMock.
 */
public class StadiumFacadeUnitTest
{
	private DefaultStadiumFacade stadiumFacade;

	private StadiumService stadiumService;

	private final static String STADIUM_NAME = "wembley";
	private final static Integer STADIUM_CAPACITY = Integer.valueOf(12345);

	// Convenience method for returning a list of Stadium
	private List<StadiumModel> dummyDataStadiumList()
	{
		final StadiumModel wembley = new StadiumModel();
		wembley.setCode(STADIUM_NAME);
		wembley.setCapacity(STADIUM_CAPACITY);
		final List<StadiumModel> stadiums = new ArrayList<StadiumModel>();
		stadiums.add(wembley);
		return stadiums;
	}

	// Convenience method for returning a Stadium
	private StadiumModel dummyDataStadium()
	{
		final StadiumModel wembley = new StadiumModel();
		wembley.setCode(STADIUM_NAME);
		wembley.setCapacity(STADIUM_CAPACITY);
		return wembley;
	}

	@Before
	public void setUp()
	{
		// We will be testing the POJO StadiumFacadeImpl - the implementation of the StadiumFacade interface.
		stadiumFacade = new DefaultStadiumFacade();

		/*
		 * The facade is expected to make calls to an implementation of StadiumService but in this test we want to verify
		 * the correct behaviour of StadiumFacadeImpl itself and not also implicitly test the behaviour of a
		 * StadiumService. In fact as of writing this class, we do only have the interface IStadiumService and no
		 * implementation. This requires that we mock out the IStadiumService interface. There are several strong
		 * arguments for following this practice:
		 * 
		 * If we were to include a real implementation of StadiumService rather than mocking it out..
		 * 
		 * 1) we will not get "false failures" in StadiumFacadeImpl due to errors in the StadiumService implementation.
		 * Such errors should be caught in tests that are focusing on StadiumService instead.
		 * 
		 * 2) The condition could arise where an error in the facade gets hidden by a complimentary error in the
		 * StadiumService implementation - resulting in a "false positive".
		 * 
		 * By mocking out the interface IStadiumService..
		 * 
		 * 3) we do not actually need an implementation of it. This therefore helps us to focus our tests on this POJO
		 * before having to implement other POJOs on which it depends - allowing us to write tests early.
		 * 
		 * 4) by focusing on the behaviour of the facade and the interfaces it uses, we are forced to focus also on the
		 * those interface, improving them before writing their implementation.
		 * 
		 * 
		 * Therefore we create a mock of the IStadiumService in the next line...
		 */
		stadiumService = createMock(StadiumService.class);
		// We then wire this service into the StadiumFacade implementation..
		stadiumFacade.setStadiumService(stadiumService);
	}

	/**
	 * The aim of this test is to test that:
	 * 
	 * 1) The facade's method getAllStadium makes a call to the StadiumService's method getAllStadium
	 * 
	 * 2) The facade then correctly wraps StadiumModels that are returned to it from the StadiumService's getAllStadium
	 * into Data Transfer Objects of type StadiumData.
	 */
	@Test
	public void testGetAllStadium()
	{
		/*
		 * We instantiate an object that we would like to be returned to StadiumFacade when the mocked out
		 * StadiumService's method getAllStadium is called. This will be a list of two StadiumModels.
		 */
		final List<StadiumModel> stadiums = dummyDataStadiumList();
		final StadiumModel wembley = dummyDataStadium();
		// We tell EasyMock we expect StadiumService's method getAllStadium  to be called, and that when it is, stadiums should be returned
		expect(stadiumService.getStadiums()).andReturn(stadiums);
		// We tell EasyMock to start observing the conversation over the StadiumService interface now
		replay(stadiumService);

		/*
		 * We now make the call to StadiumFacade's getAllStadium. If within this method a call is made to StadiumService's
		 * getAllStadium, EasyMock will return the stadiums instance to it. Easymock will also remember that the call was
		 * made.
		 */
		final List<StadiumData> dto = stadiumFacade.getStadiums();
		// We now check that dto is a DTO version of stadiums..
		assertNotNull(dto);
		assertEquals(dto.size(), stadiums.size());
		assertEquals(dto.get(0).getName(), wembley.getCode());
		assertEquals(dto.get(0).getCapacity(), wembley.getCapacity().toString());
		/*
		 * Finally we verify that stadiumService's method getAllStadium was indeed called, as specified by the
		 * "expect(stadiumService.getAllStadium())" above
		 */
		verify(stadiumService);
	}

	@Test
	public void testGetStadium()
	{
		/*
		 * We instantiate an object that we would like to be returned to StadiumFacade when the mocked out
		 * StadiumService's method getAllStadium is called. This will be a list of two StadiumModels.
		 */
		final StadiumModel wembley = new StadiumModel();
		wembley.setCode(STADIUM_NAME);
		wembley.setCapacity(STADIUM_CAPACITY);

		// We tell EasyMock we expect StadiumService's method getStadium  to be called, and that when it is, wembley should be returned
		expect(stadiumService.getStadiumForCode("wembley")).andReturn(wembley);
		// We tell EasyMock to start observing the conversation over the StadiumService interface now
		replay(stadiumService);

		/*
		 * We now make the call to StadiumFacade's getStadium. If within this method a call is made to StadiumService's
		 * getStadium, EasyMock will return the wembley instance to it. Easymock will also remember that the call was
		 * made.
		 */
		final StadiumData stadium = stadiumFacade.getStadium("wembley");
		// We now check that stadium is a correct DTO representation of the ServiceModel wembley
		assertEquals(stadium.getName(), wembley.getCode());
		assertEquals(stadium.getCapacity(), wembley.getCapacity().toString());
		/*
		 * Finally we verify that stadiumService's method getStadium was indeed called, as specified by the
		 * "expect(stadiumService.getStadium())" above
		 */
		verify(stadiumService);
	}

}