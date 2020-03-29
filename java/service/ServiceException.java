package service;

public class ServiceException extends Exception {
    public ServiceException() {
    }

    public ServiceException(String message) {
        System.out.println(message);
    }
}
