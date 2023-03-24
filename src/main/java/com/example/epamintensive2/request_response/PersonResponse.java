package com.example.epamintensive2.request_response;

import com.example.epamintensive2.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponse {

    private String fck;
    private String email;
    private Role role;

}
