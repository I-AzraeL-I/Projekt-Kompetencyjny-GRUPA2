package com.mygroup.project.model.dto.specialized;

//projection used to extract data from native postgresql query, works just like a normal class
public interface UserSubjectOfferDTO {

    Long getUserSubjectId();
    String getSubjectName();
    Long getUserId();
    String getFirstName();
    String getCity();
    String getDescription();
}
