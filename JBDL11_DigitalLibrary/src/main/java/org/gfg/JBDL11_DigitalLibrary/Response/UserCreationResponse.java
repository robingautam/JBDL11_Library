package org.gfg.JBDL11_DigitalLibrary.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gfg.JBDL11_DigitalLibrary.model.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationResponse extends Response {
    private User user;
}
