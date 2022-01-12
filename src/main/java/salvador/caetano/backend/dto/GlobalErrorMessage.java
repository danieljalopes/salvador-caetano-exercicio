package salvador.caetano.backend.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
public class GlobalErrorMessage {
    private String message;
}
