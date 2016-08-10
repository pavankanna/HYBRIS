package de.hybris.platform.cuppytrail.interceptors;

import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;


/**
 * This class belongs to the Source Code Trail documented at https://wiki.hybris.com/display/pm/Source+Code+Tutorial and
 * is used to demonstrate model interceptors. This interceptor will convert a product's code to lower case before
 * saving.
 */
public class StadiumInterceptor implements PrepareInterceptor
{
	/**
	 * Converts code of passed product to lower case. Method will be called for all products before a save gets
	 * performed.
	 */
	@Override
	public void onPrepare(final Object model, final InterceptorContext ctx) throws InterceptorException
	{
		final StadiumModel stadiumModel = (StadiumModel) model;
		stadiumModel.setCode(stadiumModel.getCode().toLowerCase());
	}

}
