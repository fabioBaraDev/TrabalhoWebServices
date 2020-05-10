package br.com.fiap.trabalho.dto.pagseguro;

public class GatewaySystem {
    private String type;
    RawCode RawCodeObject;
    RawMessage RawMessageObject;
    NormalizedCode NormalizedCodeObject;
    NormalizedMessage NormalizedMessageObject;
    private String authorizationCode;
    private String nsu;
    private String tid;
    private String establishmentCode;
    private String acquirerName;


    // Getter Methods

    public String getType() {
        return type;
    }

    public RawCode getRawCode() {
        return RawCodeObject;
    }

    public RawMessage getRawMessage() {
        return RawMessageObject;
    }

    public NormalizedCode getNormalizedCode() {
        return NormalizedCodeObject;
    }

    public NormalizedMessage getNormalizedMessage() {
        return NormalizedMessageObject;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public String getNsu() {
        return nsu;
    }

    public String getTid() {
        return tid;
    }

    public String getEstablishmentCode() {
        return establishmentCode;
    }

    public String getAcquirerName() {
        return acquirerName;
    }

    // Setter Methods

    public void setType(String type) {
        this.type = type;
    }

    public void setRawCode(RawCode rawCodeObject) {
        this.RawCodeObject = rawCodeObject;
    }

    public void setRawMessage(RawMessage rawMessageObject) {
        this.RawMessageObject = rawMessageObject;
    }

    public void setNormalizedCode(NormalizedCode normalizedCodeObject) {
        this.NormalizedCodeObject = normalizedCodeObject;
    }

    public void setNormalizedMessage(NormalizedMessage normalizedMessageObject) {
        this.NormalizedMessageObject = normalizedMessageObject;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public void setNsu(String nsu) {
        this.nsu = nsu;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public void setEstablishmentCode(String establishmentCode) {
        this.establishmentCode = establishmentCode;
    }

    public void setAcquirerName(String acquirerName) {
        this.acquirerName = acquirerName;
    }
}
