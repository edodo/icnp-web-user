package icnp.app.domain.product.res;
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

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project : web-user
 * @FileName : ProductRes.java
 * @Date : 2023. 01. 26.
 * @author : smchoi@in-soft.co.kr
 * @description :
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "상품 응답 파라미터")
public class ProductRes {
    @Schema(description = "상품 고유 ID", example = "1")
    private Long id;
    @Schema(description = "상품 ID (255)", example = "1")
    private String productId;

    @Schema(description = "상품 이름 (255)", example = "사과")
    private String productName;

    @Schema(description = "상품 가격 (19)", example = "1000000000")
    private Long productPrice;

    @Schema(description = "사용자고유 ID (255)", example = "1")
    private String userId;

}
