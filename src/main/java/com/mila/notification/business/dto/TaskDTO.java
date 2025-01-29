package com.mila.notification.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mila.notification.business.enums.NotificationStatusEnum;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTO {

    private String id;
    private String taskName;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime creationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime eventDate;
    private String userEmail;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime modificationDate;
    private NotificationStatusEnum notificationStatusEnum;
}
