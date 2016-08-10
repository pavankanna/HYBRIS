/**
 * 
 */
package de.hybris.platform.cuppy.web.components;

import de.hybris.platform.cuppy.web.data.CompetitionData;
import de.hybris.platform.cuppy.web.facades.PlayerFacade;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;


/**
 * @author andreas.thaler
 * 
 */
public class CompetitionComboBox extends Combobox
{
	public CompetitionComboBox()
	{
		super();

		setReadonly(true);
		for (final CompetitionData comp : getPlayerFacade().getActiveCompetitions())
		{
			final Comboitem item = new Comboitem();
			this.appendChild(item);
			item.setLabel(comp.getName());
			item.setValue(comp);
			if (comp.isCurrentCompetition())
			{
				this.setSelectedItem(item);
			}
		}

		this.addEventListener(Events.ON_CHANGE, new EventListener()
		{
			@Override
			public void onEvent(final Event event)
			{
				getFrontendController().changeCompetition(((CompetitionData) getSelectedItem().getValue()).getCode());
			}
		});
	}

	private CuppyFrontendController getFrontendController()
	{
		return (CuppyFrontendController) SpringUtil.getBean("frontendController");
	}

	private PlayerFacade getPlayerFacade()
	{
		return (PlayerFacade) SpringUtil.getBean("playerFacade");
	}
}
