package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserData {
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
