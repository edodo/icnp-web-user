/***************************************************
 * Copyright(c) 2023 in-soft All right reserved.
 * This software is the proprietary information of in-soft.
 *
 * Revision History
 * Author                         Date          Description
 * --------------------------     ----------    ----------------------------------------
 * smchoi@in-soft.co.kr           2023. 01. 26.
 *
 ****************************************************/
package icnp.app.domain.user.res;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project : web-sample
 * @FileName : UserRes.java
 * @Date : 2023. 01. 26.
 * @author : smchoi@in-soft.co.kr
 * @description :
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "사용자 응답 파라미터")
public class UserRes {
    @Schema(description = "사용자 고유 ID", example = "1")
    private Long id;
    @Schema(description = "사용자 ID (255)", example = "1")
    private Long userId;

    //@NotBlank(message = "이름은 필수 입력 항목 입니다")
    @Schema(description = "사용자 이름 (255)", required = true, example = "홍길동")
    private String userName;

    @NotBlank(message = "이메일은 필수 입력 항목 입니다")
    @Schema(description = "사용자 Email (50)", example = "abc@naver.com")
    private String userEmail;

    @NotBlank(message = "비밀번호는 필수 입력 항목 입니다")
    @Schema( description = "사용자 Email (50)", required = true, example = "Abcd1!")
    private String userPassword;

}
