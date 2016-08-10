/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2011 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package de.hybris.platform.cuppytrail;

/**
 * Service to reset a password for a user. The logic is like this: A user enters his e-mail address and is then sent a
 * link. The link points to a page where the user can enter his new password. To identify the user the link includes a
 * parameter with a secure reset key. This key is to identify the pending password reset request.
 */
public interface ForgotPasswordService
{

	/**
	 * Triggers the password forgotten process for a given e-mail address. Sends the e-mail message that contains the
	 * link. After that the process goes into the wait state.
	 * 
	 * @param emailAddress
	 *           the e-mail address of the user that wants to reset his password
	 */
	void forgotPassword(String emailAddress);

	/**
	 * Returns true of the given key is a valid reset password key.
	 * 
	 * @key the key to check
	 */
	boolean isValidPasswordResetKey(String key);

	/**
	 * Triggers the rest of the process to set the user's password to the new value. We have to give the key of the
	 * process that we get from the URL parameter of the password reset page.
	 * 
	 * @key the key of the password reset process to continue
	 * @password the new password
	 */
	void resetPassword(String key, String password);

}
