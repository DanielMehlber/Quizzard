package main;

/**
 * Error Codes used in the Quizzard Project
 * @author mehlber
 *
 */
public enum ErrCode {
	NULL,
	ERR_LOGIN_UNKNOWN_USER, ERR_LOGIN_INCORRECT_PASSWORD,
	ERR_REGISTRATION_REDUNDANT_USERNAME, ERR_REGISTRATION_PASSWORDS_NOT_MATCHING, ERR_REGISTRATION_USERNAME_TOO_LONG, ERR_REGISTRATION_USERNAME_EMPTY, ERR_REGISTRATION_PASSWORD_EMPTY,
}
