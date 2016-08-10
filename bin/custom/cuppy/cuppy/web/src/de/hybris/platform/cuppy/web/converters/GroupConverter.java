/**
 * 
 */
package de.hybris.platform.cuppy.web.converters;

import de.hybris.platform.cuppy.model.GroupModel;
import de.hybris.platform.cuppy.services.TenantScopedComponent;
import de.hybris.platform.cuppy.web.data.GroupData;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


/**
 * @author andreas.thaler
 * 
 */
@TenantScopedComponent(value = "groupConverter")
public class GroupConverter extends GenericCollectionConverter<GroupModel, GroupData>
{
	@Override
	public GroupData convert(final GroupModel model, final GroupData data) throws ConversionException
	{
		data.setCompetition(model.getCompetition().getCode());
		return super.convert(model, data);
	}

	@Override
	protected GroupData createDestObject() throws ConversionException
	{
		return new GroupData();
	}
}
