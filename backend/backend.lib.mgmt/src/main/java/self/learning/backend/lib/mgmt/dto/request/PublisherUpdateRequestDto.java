package self.learning.backend.lib.mgmt.dto.request;



public class PublisherUpdateRequestDto {
    private String publisherCode;
    private String publisherName;

    public String getPublisherCode() {
        return publisherCode;
    }

    public void setPublisherCode(String publisherCode) {
        this.publisherCode = publisherCode;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

}
