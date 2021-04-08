package requests;

public enum RequestUrls {
    PET_POST_NEW("/pet"),
    PET_POST_ADD_IMAGE("/pet/%s/uploadImage"),
    PET_PUT_UPDATE("/pet"),
    PET_GET_BY_ID("/pet/%s"),
    PET_GET_FIND_BY_STATUS("/pet/findByStatus"),
    PET_DELETE("/pet/%s"),
    PET_POST_UPDATE_IN_STORE("/pet/%s");


    private String url;

    RequestUrls(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getUrl(Object... args) {
        return String.format(url, args);
    }
}
