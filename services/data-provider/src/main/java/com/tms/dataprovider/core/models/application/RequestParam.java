package com.tms.dataprovider.core.models.application;
import com.tms.dataprovider.core.enums.Param;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestParam {
    private String name;
    private Object value;
    private Param param;
}
