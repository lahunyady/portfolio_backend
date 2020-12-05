package laszlo.hunyady.portfolio.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailRequest {
    @NotEmpty
    private String languageCode;
    @NotEmpty
    private String name;
    @Email
    private String email;
    @NotEmpty
    private String message;
}
