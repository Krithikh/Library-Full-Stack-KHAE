package self.learning.backend.lib.mgmt.dto.response;



public class PublisherResponseDto {
    private Long publisherId;
    private String publisherCode;
    private String publisherName;
    private Boolean active;

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
