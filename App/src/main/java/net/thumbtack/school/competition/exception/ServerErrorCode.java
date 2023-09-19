package net.thumbtack.school.competition.exception;

public enum ServerErrorCode {
    EMPTY_FIRSTNAME("First name can't be empty"),
    EMPTY_LASTNAME("Last name can't be empty"),
    EMPTY_LIST_OF_DIRECTIONS("List of directions can't be empty"),
    EMPTY_LOGIN("Login can't be empty"),
    EMPTY_PASSWORD("Password can't be empty"),
    EMPTY_COMPANY_NAME("Company name can't be empty"),
    EMPTY_APPLICATION_NAME("Application name can't be empty"),
    EMPTY_APPLICATION_DESCRIPTION("Application description can't be empty"),
    EMPTY_SUM_OF_GRANT("Sum of grant can't be empty"),
    LOGIN_IS_ALREADY_USES("Login is already uses"),
    USER_IS_NOT_LOGGED_IN("User is not logged in"),
    USER_IS_ALREADY_REGISTERED("User is already registered"),
    WRONG_TOKEN("Wrong token"),
    APPLICATION_IS_ALREADY_REGISTERED("Application is already registered"),
    GRANT_APPLICATION_IS_NOT_FOUND("Grant application is not found"),
    USER_IS_NOT_REGISTERED_YET("User isn't registered yet"),
    WRONG_LOGIN("Wrong login"),
    WRONG_PASSWORD("Wrong password"),
    NOT_FOUND("Not found"),
    WRONG_JSON("Wrong json"),
    WRONG_APPLICATION_MARK("Wrong application mark"),
    USER_IS_NOT_BELONG_TO_CLASS("User is not belong to class");

    private String errorCode;
    ServerErrorCode(String errorCode){
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
