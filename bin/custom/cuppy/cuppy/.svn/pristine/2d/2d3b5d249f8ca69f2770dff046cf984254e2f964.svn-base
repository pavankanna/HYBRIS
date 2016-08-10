package de.hybris.platform.cuppy.interceptors;

import de.hybris.platform.cuppy.model.PlayerModel;
import de.hybris.platform.cuppy.services.MailService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;


/**
 * @author andreas.thaler
 * 
 */
public class PlayerConfirmedInterceptor implements ValidateInterceptor
{
	private MailService mailService;

	@Override
	public void onValidate(final Object model, final InterceptorContext ctx) throws InterceptorException
	{
		if (!(model instanceof PlayerModel))
		{
			return;
		}
		final PlayerModel player = (PlayerModel) model;
		final boolean curValue = player.isConfirmed();
		if (curValue && !ctx.isNew(player))
		{
			final Object oldValue = player.getValueHistory().getOriginalValue(PlayerModel.CONFIRMED);
			if (Boolean.FALSE.equals(oldValue))
			{
				mailService.sendConfirmationMail(player);
			}

		}
	}

	public void setMailService(final MailService mailService)
	{
		this.mailService = mailService;
	}
}
