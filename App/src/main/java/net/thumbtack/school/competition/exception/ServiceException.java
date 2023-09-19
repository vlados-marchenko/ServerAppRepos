package net.thumbtack.school.competition.exception;

public class ServiceException extends Exception {

    ServerErrorCode serverErrorCode;

    public ServiceException(ServerErrorCode serverErrorCode) {
        this.serverErrorCode = serverErrorCode;
    }
    public ServerErrorCode getServerErrorCode(){
        return serverErrorCode;
    }


}
