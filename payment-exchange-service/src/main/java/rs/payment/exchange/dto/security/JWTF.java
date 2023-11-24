package rs.payment.exchange.dto.security;

public interface JWTF<T> {

    public static class ProcessingState {
        private boolean success;
        private String message;

        private ProcessingState(){
        }

        public static ProcessingState success(){
            ProcessingState suc = new ProcessingState();
            suc.success = true;
            suc.message = "OK";
            return suc;
        }

        public static ProcessingState error(String msg){
            ProcessingState err = new ProcessingState();
            err.success = false;
            err.message = msg;
            return err;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }

    public interface Encoder <T> {
        ProcessingState encodeToJwt(JWTF<T> jwtf, StringBuilder jwt);
    }

    public interface Decoder <T> {
        ProcessingState decodeFromJwt(String jwt, JWTF<T> jwtf);
    }

    static <T> ProcessingState encodeToJwt(JWTF<T> jwtf, StringBuilder jwt, Encoder<T> encoder) {
        return encoder.encodeToJwt(jwtf, jwt);
    }

    static <T> ProcessingState decodeFromJwt(JWTF<T> jwtf, String jwt, Decoder<T> decoder) {
        return decoder.decodeFromJwt(jwt, jwtf);
    }

    default boolean canBeWrapped() {
        return false;
    }

    default void wrap(JWTF<T> jwtf) {
    }

    String getJti();
    void setJti(String jti);
    String getIss();
    void setIss(String iss);
    String getSub();
    void setSub(String sub);
    String[] getAud();
    void setAud(String[] aud);
    Long getExp();
    void setExp(Long exp);
    Long getNbf();
    void setNbf(Long nbf);
    Long getIat();
    void setIat(Long iat);
    T getPayload();
    void setPayload(T payload);

}
