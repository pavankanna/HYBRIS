package de.hybris.platform.cuppytrail;

import static org.junit.Assert.assertEquals;

import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import org.junit.Test;


/**
 * This class belongs to the Source Code Trail documented at https://wiki.hybris.com/display/pm/Source+Code+Tutorial.
 * Its purpose is to test and demonstrate the behavior of the model interceptors.
 */
public class StadiumInterceptorIntegrationTest extends ServicelayerTransactionalTest
{
	@Resource
	private ModelService modelService;

	@Test
	public void testStadiumInterceptor()
	{
		//create new stadium with a upper case code
		final StadiumModel stadium = modelService.create(StadiumModel.class);
		stadium.setCode("AcOdeNamE");
		//save stadium which calls interceptor
		modelService.save(stadium);
		//check correct conversion
		assertEquals("Conversion was not applied", stadium.getCode(), "acodename");
	}
}
