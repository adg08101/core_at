package general;

public enum Property {
    WEBDRIVER_CHROME_DRIVER,
    WEBDRIVER_CHROME_SILENTOUTPUT,
    CHROME_DRIVER, //(String) Environment variable name for Chrome driver executable file
    STRING_TRUE, //(String) True value
    BOOL_TRUE, //(Boolean) True value
    TIMEOUT_IMPLICIT_KEY, //(String) Navigator timeout key
    TIMEOUT_IMPLICIT_VALUE, //(Int) Navigator timeout value
    TIMEOUT_PAGELOAD_KEY, //(String) Navigator timeout key
    TIMEOUT_PAGELOAD_VALUE, //(Int) Navigator timeout value
    TIMEOUT_SCRIPT_KEY, //(String) Navigator timeout key
    TIMEOUT_SCRIPT_VALUE, //(Int) Navigator timeout value
    STRING_TIMEOUTS,
    INT_SHORT_TIME, //(Int) Short time to wait
    DEFAULT_PROPERTIES_FILE_PATH,
    STRING_DEFAULT_PROPERTIES,
    STRING_USER_NAME,
    STRING_USER_PASSWORD,
    KAHUA_URL,
    KAHUA_LOGIN_TYPE,
    KAHUA_USERNAME_FIELD,
    KAHUA_PASSWORD_FIELD,
    KAHUA_LOGIN_ELEMENTS,
    KAHUA_LOGOFF_ELEMENTS,
    STR_LOGIN_TYPE,
    STR_USERNAME_FIELD,
    STR_PASSWORD_FIELD,
    STR_LOGIN_ELEMENTS,
    STR_LOGOFF_ELEMENTS
}
