package de.hybris.platform.cuppytrail;

import static org.junit.Assert.assertEquals;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cuppytrail.interceptors.StadiumInterceptor;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;

import org.junit.Before;
import org.junit.Test;


/**
 * This class belongs to the Source Code Trail documented at https://wiki.hybris.com/display/pm/Source+Code+Tutorial and
 * is used to demonstrate model interceptors
 */
@UnitTest
public class StadiumInterceptorUnitTest
{
	private StadiumInterceptor stadiumInterceptor;

	@Before
	public void setUp()
	{
		stadiumInterceptor = new StadiumInterceptor();
	}

	@Test
	public void testMyProductModelInterceptor() throws InterceptorException
	{
		final StadiumModel stadiumModel = new StadiumModel();
		stadiumModel.setCode("Intercepted");

		stadiumInterceptor.onPrepare(stadiumModel, null);
		assertEquals("Lower case conversation not applied", "intercepted", stadiumModel.getCode());
	}
}
