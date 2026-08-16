package self.learning.backend.lib.mgmt.dataobject;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_publisher")
public class PublisherDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private Long publisherId;
    @Column(name = "publisher_code")
    private String publisherCode;
    @Column(name = "publisher_name")
    private String publisherName;
    @Column(name = "is_active")
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
