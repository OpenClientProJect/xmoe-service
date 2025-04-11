package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drama {
    private Integer vodId;
    private String vodPic;
    private String vodName;
    private String vodRemarks;
}
