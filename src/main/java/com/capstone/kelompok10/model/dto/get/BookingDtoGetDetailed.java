package com.capstone.kelompok10.model.dto.get;


import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingDtoGetDetailed {
    private Long bookingId;
    private Boolean status;
    private Long price;
    private String createdAt;
    private String updatedAt;
    private Long userId;
    private String userName;
    private String membership;
    private Long instructureId;
    private String instructureName;
    private Long classId;
    private String className;
    private Long categoryId;
    private String categoryName;
    private Date schedule;
    private String room;
    private String type;
}
