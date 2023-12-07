package com.example.demo.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author 종선
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryInfo {

    @JsonProperty("_embedded")
    private Embedded embedded;
    private Page page;

}
