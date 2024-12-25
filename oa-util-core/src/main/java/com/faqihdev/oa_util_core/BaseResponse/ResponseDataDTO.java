package com.faqihdev.oa_util_core.BaseResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class ResponseDataDTO extends AResponseDataDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public ResponseDataDTO(Integer responseCode, String responseMsg) {
        super(responseCode, responseMsg);
    }

    public ResponseDataDTO(int responseCode, String responseMsg) {
        this.setResponseCode(HttpStatus.valueOf(responseCode));
        this.setResponseMsg(responseMsg);
    }

}
