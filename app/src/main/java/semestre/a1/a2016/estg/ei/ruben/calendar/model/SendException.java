package semestre.a1.a2016.estg.ei.ruben.calendar.model;

/**
 * Created by Ruben on 10/07/2017.
 */

class SendException extends RuntimeException {
    private int code;
    private String message;

    public SendException(int code, String message) {
        super("Send execption with code "+code);
        this.code=code;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
