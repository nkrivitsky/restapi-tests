package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import models.base.BaseModel;
import models.enums.PetStatusEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pet extends BaseModel {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private Category category = new Category();
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private List<String> photoUrls = new ArrayList<>();
    @Getter
    @Setter
    private List<Tag> tags = new ArrayList<>();
    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private PetStatusEnum status;

    public Pet addPhotoUrlsItem(String photoUrlsItem) {
        this.photoUrls.add(photoUrlsItem);
        return this;
    }

    public Pet addTagsItem(Tag tagsItem) {
        if (this.tags == null) {
            this.tags = new ArrayList<Tag>();
        }
        this.tags.add(tagsItem);
        return this;
    }

    public boolean compareData(Object o){
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pet pet = (Pet) o;
        return  Objects.equals(this.category, pet.category) &&
                Objects.equals(this.name, pet.name) &&
                Objects.equals(this.photoUrls, pet.photoUrls) &&
                Objects.equals(this.tags, pet.tags);/* &&
                Objects.equals(this.status, pet.status);*/
    }

    @Override
    public boolean equals(Object o) {
        if (!compareData(o))
            return false;
        Pet pet = (Pet) o;
        return  Objects.equals(this.id, pet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, name, photoUrls, tags, status);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Pet {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    category: ").append(toIndentedString(category)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    photoUrls: ").append(toIndentedString(photoUrls)).append("\n");
        sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
        sb.append("    status: ").append(toIndentedString(status.getValue())).append("\n");
        sb.append("}");
        return sb.toString();
    }
}

