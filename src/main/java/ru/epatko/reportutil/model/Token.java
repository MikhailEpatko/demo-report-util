package ru.epatko.reportutil.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {

    String url;
    @SerializedName("access_token")
    String accessToken;   // "fc88aabb-0f96-48a6-b05e-5edb2f5e1a06"
    @SerializedName("token_type")
    String tokenType;     // "bearer"
    @SerializedName("refresh_token")
    String refreshToken;  // "9ceaf88e-ede1-4c8d-81ce-6aad59a0a214"
}
