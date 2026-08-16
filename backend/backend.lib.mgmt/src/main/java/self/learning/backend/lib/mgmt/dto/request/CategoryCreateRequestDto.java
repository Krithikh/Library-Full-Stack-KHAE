package self.learning.backend.lib.mgmt.dto.request;



public class CategoryCreateRequestDto {
    private String categoryCode;
    private String categoryName;

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
