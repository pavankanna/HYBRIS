package de.hybris.platform.cuppytrail.web.facades.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cuppy.model.MatchModel;
import de.hybris.platform.cuppy.services.TenantScopedComponent;
import de.hybris.platform.cuppytrail.StadiumService;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.cuppytrail.web.data.MatchSummaryData;
import de.hybris.platform.cuppytrail.web.data.StadiumData;
import de.hybris.platform.cuppytrail.web.data.StadiumProductDataTO;
import de.hybris.platform.cuppytrail.web.facades.StadiumFacade;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.product.PriceService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * This class belongs to the Source Code Trail documented at https://wiki.hybris.com/display/pm/Source+Code+Tutorial
 */
@TenantScopedComponent(value = "stadiumFacade")
public class DefaultStadiumFacade implements StadiumFacade
{
	@Autowired
	private StadiumService stadiumService;
	@Autowired
	private PriceService priceService;

	@Override
	public List<StadiumData> getStadiums()
	{
		final List<StadiumModel> stadiumModels = stadiumService.getStadiums();
		final List<StadiumData> stadiumFacadeData = new ArrayList<StadiumData>();
		for (final StadiumModel sm : stadiumModels)
		{
			final StadiumData sfd = new StadiumData(sm.getCode(), sm.getCapacity());
			stadiumFacadeData.add(sfd);
		}
		return stadiumFacadeData;
	}

	@Override
	public StadiumData getStadium(final String name)
	{
		final StadiumModel stadium = stadiumService.getStadiumForCode(name);
		if (stadium == null)
		{
			return null;
		}
		// Create a list of MatchSummaryData from the matches
		final List<MatchSummaryData> matchSummary = new ArrayList<MatchSummaryData>();
		if (stadium.getMatches() != null)
		{
			final Iterator<MatchModel> matchesIterator = stadium.getMatches().iterator();

			while (matchesIterator.hasNext())
			{
				final MatchModel match = matchesIterator.next();
				final MatchSummaryData summary = new MatchSummaryData(match.getHomeTeam().getName(), match.getGuestTeam().getName(),
						match.getDate());
				// If no goals are specified provide a user friendly "-" instead of null
				summary.setGuestGoals(match.getGuestGoals() == null ? "-" : match.getGuestGoals().toString());
				summary.setHomeGoals(match.getHomeGoals() == null ? "-" : match.getHomeGoals().toString());
				matchSummary.add(summary);
			}
		}
		// Now we can create the StadiumData transfer object
		return new StadiumData(stadium.getCode(), stadium.getCapacity(), matchSummary);
	}


	// Phase 1
	public List<StadiumProductDataTO> getAllStadiumProducts1()
	{
		return new ArrayList<StadiumProductDataTO>();
	}

	@Override
	//Phase 2
	public List<StadiumProductDataTO> getAllStadiumProducts()
	{
		// For second TDD testGetAllStadiumProducts:
		final List<ProductModel> productModels = stadiumService.getAllStadiumProducts();
		final List<StadiumProductDataTO> stadiumProductDataTOs = new ArrayList<StadiumProductDataTO>();
		for (final ProductModel pm : productModels)
		{

			final List<PriceInformation> priceInfos = priceService.getPriceInformationsForProduct(pm);
			final double price = priceInfos.get(0).getPriceValue().getValue();
			final StadiumProductDataTO spdto = new StadiumProductDataTO(pm.getPk(), pm.getName(), pm.getDescription(), price);

			stadiumProductDataTOs.add(spdto);
		}
		return stadiumProductDataTOs;
	}

	public void setStadiumService(final StadiumService stadiumService)
	{
		this.stadiumService = stadiumService;
	}
}