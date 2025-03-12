package co.ccc.pmv.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponse<T> {

    private int code;
    private String message;
    private T data;
}
