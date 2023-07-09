package com.lunarllc.contentcalendar.model;

import com.lunarllc.contentcalendar.model.Status;
import com.lunarllc.contentcalendar.model.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


import java.time.LocalDateTime;

public record Content(

        Integer id,
        @NotBlank
        String title,
        //@Column(value = "description")
        String desc,
        Status status,
        Type contentType,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdated,
        String url
) {
}