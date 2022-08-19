package com.weng.business.organization.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 *  <p>
 *
 * @author wengchuanjie
 * @date 2021-11-21
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ImEnterpriseOrganization  {

    private String orgId;

    private List<Long> orgIds;

    private String id;

}
