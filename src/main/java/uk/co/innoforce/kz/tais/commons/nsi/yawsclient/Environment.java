package uk.co.innoforce.kz.tais.commons.nsi.yawsclient;

/**
 * @author magzhan.karasayev
 * @since 22.05.14 12:44
 */
public enum Environment implements IEnvironment {
    PROD("http://192.168.203.118:8080/NSI-WS/"),
    TEST("http://192.168.1.211:8080/NSI-WS/");

    private final String urlPart;
    private Environment(String urlPart) {
        this.urlPart = urlPart;
    }

    @Override
    public String getUrlPart() {
        return urlPart;
    }
}
