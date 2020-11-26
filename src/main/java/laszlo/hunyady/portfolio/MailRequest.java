package laszlo.hunyady.portfolio;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailRequest {
    private String name;
    private String email;
    private String message;

}
