package rs.payment.exchange.dto.security;

/**
 * JWT Facade interface, to be used with jwt processing library of choice
 */
public class JWTFWrap<T> implements JWTF<T> {

    private static class _JWTF<T> implements JWTF<T> {

        private String jti; //id
        private String iss; //issuer
        private String sub; //subject
        private String[] aud; //audience
        private Long exp; //expiration time
        private Long nbf; //not before time
        private Long iat; //issued at

        private T payload;

        private _JWTF() {
        }

        @Override
        public boolean canBeWrapped() {
            return true;
        }

        @Override
        public String getJti() {
            return jti;
        }

        @Override
        public void setJti(String jti) {
            this.jti = jti;
        }

        @Override
        public String getIss() {
            return iss;
        }

        @Override
        public void setIss(String iss) {
            this.iss = iss;
        }

        @Override
        public String getSub() {
            return sub;
        }

        @Override
        public void setSub(String sub) {
            this.sub = sub;
        }

        @Override
        public String[] getAud() {
            return aud;
        }

        @Override
        public void setAud(String[] aud) {
            this.aud = aud;
        }

        @Override
        public Long getExp() {
            return exp;
        }

        @Override
        public void setExp(Long exp) {
            this.exp = exp;
        }

        @Override
        public Long getNbf() {
            return nbf;
        }

        @Override
        public void setNbf(Long nbf) {
            this.nbf = nbf;
        }

        @Override
        public Long getIat() {
            return iat;
        }

        @Override
        public void setIat(Long iat) {
            this.iat = iat;
        }

        @Override
        public T getPayload() {
            return payload;
        }

        @Override
        public void setPayload(T payload) {
            this.payload = payload;
        }
    }

    private JWTF<T> wrappedJwtf;

    private JWTFWrap() {
    }

    public static <T> JWTF<T> producePlain() {
        return new _JWTF<>();
    }

    public static <T> JWTF<T> produceWrapped() {
        JWTF<T> jwtf = new JWTFWrap<>();
        jwtf.wrap(new _JWTF<>());
        return jwtf;
    }

    @Override
    public void wrap(JWTF<T> jwtf) {
        if(jwtf.canBeWrapped()) {
            this.wrappedJwtf = jwtf;
        }
    }

    @Override
    public String getJti() {
        return wrappedJwtf.getJti();
    }

    @Override
    public void setJti(String jti) {
        wrappedJwtf.setJti(jti);
    }

    @Override
    public String getIss() {
        return wrappedJwtf.getIss();
    }

    public void setIss(String iss) {
        wrappedJwtf.setIss(iss);
    }

    @Override
    public String getSub() {
        return wrappedJwtf.getSub();
    }

    public void setSub(String sub) {
        wrappedJwtf.setSub(sub);
    }

    @Override
    public String[] getAud() {
        return wrappedJwtf.getAud();
    }

    public void setAud(String[] aud) {
        wrappedJwtf.setAud(aud);
    }

    @Override
    public Long getExp() {
        return wrappedJwtf.getExp();
    }

    public void setExp(Long exp) {
        wrappedJwtf.setExp(exp);
    }

    @Override
    public Long getNbf() {
        return wrappedJwtf.getNbf();
    }

    public void setNbf(Long nbf) {
        wrappedJwtf.setNbf(nbf);
    }

    @Override
    public Long getIat() {
        return wrappedJwtf.getIat();
    }

    public void setIat(Long iat) {
        wrappedJwtf.setIat(iat);
    }

    @Override
    public T getPayload() {
        return wrappedJwtf.getPayload();
    }

    public void setPayload(T payload) {
        wrappedJwtf.setPayload(payload);
    }
}
