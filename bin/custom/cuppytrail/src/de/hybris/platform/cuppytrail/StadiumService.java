package de.hybris.platform.cuppytrail;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cuppytrail.model.StadiumModel;

import java.util.List;


/**
 * This interface belongs to the Source Code Trail documented at https://wiki.hybris.com/display/pm/Source+Code+Tutorial
 * and describes the interface required to satisfy the
 * {@link de.hybris.platform.cuppytrail.StadiumServiceIntegrationTest}.
 */
public interface StadiumService
{
	/**
	 * Gets all stadiums of the system.
	 * 
	 * @return all stadiums of system
	 */
	List<StadiumModel> getStadiums();

	/**
	 * Gets the stadium for given code.
	 * 
	 * @throws de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException
	 *            in case more then one stadium for given code is found
	 * @throws de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException
	 *            in case no stadium for given code can be found
	 */
	StadiumModel getStadiumForCode(String code);

	List<ProductModel> getAllStadiumProducts();
}