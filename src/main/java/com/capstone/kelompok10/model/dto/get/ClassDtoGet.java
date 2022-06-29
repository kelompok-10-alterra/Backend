package com.capstone.kelompok10.model.dto.get;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClassDtoGet {
    private Long classId;
    private String name;
    private String description;
    private Boolean status;
    private Long capacity;
    private String schedule;
    private Long price;
    private String imageUrl;
    private String createdAt;
    private String updatedAt;
    private Long typeId;
    private String typeName;
    private Long instructureId;
    private String instructureName;
    private Long categoryId;
    private String categoryName;
    private Long roomId;
    private String roomName;
}
