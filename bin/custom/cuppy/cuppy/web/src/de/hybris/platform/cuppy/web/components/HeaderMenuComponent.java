package de.hybris.platform.cuppy.web.components;

import de.hybris.platform.cockpit.components.LeftSectionHeaderComponent;
import de.hybris.platform.cuppy.web.data.CompetitionData;
import de.hybris.platform.cuppy.web.facades.PlayerFacade;

import org.zkoss.spring.SpringUtil;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;


public class HeaderMenuComponent extends LeftSectionHeaderComponent
{
	public HeaderMenuComponent()
	{
		super();
		if (dataLanguageEntry != null)
		{
			dataLanguageEntry.detach();
		}
		if (userRoleEntry != null)
		{
			userRoleEntry.detach();
		}

		final Menu competitionMenu = new Menu(Labels.getLabel("perspective.cuppy.frontend.competition"));

		final Menupopup compPop = new Menupopup();
		competitionMenu.appendChild(compPop);

		compPop.addEventListener(Events.ON_OPEN, new EventListener()
		{
			@Override
			public void onEvent(final Event event) throws Exception // NOPMD
			{
				if (event instanceof OpenEvent && ((OpenEvent) event).isOpen())
				{
					compPop.getChildren().clear();
					for (final CompetitionData comp : getPlayerFacade().getActiveCompetitions())
					{
						final Menuitem item = new Menuitem();
						item.setCheckmark(true);

						item.setLabel(comp.getName());
						item.setValue(comp.getCode());

						item.setChecked(comp.isCurrentCompetition());

						item.addEventListener(Events.ON_CLICK, new EventListener()
						{
							@Override
							public void onEvent(final Event event) throws Exception // NOPMD
							{
								getFrontendController().changeCompetition(item.getValue());
							}
						});
						compPop.appendChild(item);
					}

				}
			}
		});


		logoutEntry.getParent().insertBefore(competitionMenu, logoutEntry);
	}

	private PlayerFacade getPlayerFacade()
	{
		return (PlayerFacade) SpringUtil.getBean("playerFacade");
	}

	private CuppyFrontendController getFrontendController()
	{
		return (CuppyFrontendController) SpringUtil.getBean("frontendController");
	}

}
