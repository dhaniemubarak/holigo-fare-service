package id.holigo.services.common.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements Serializable {

    static final long serialVersionUID = -5815566940065181210L;
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String type;
    private String oneTimePassword;
    private UserGroupEnum userGroup;

    @Builder.Default
    private Long officialId = null;

    @Builder.Default
    private Long parentId = null;
}
