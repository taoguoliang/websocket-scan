package cn.taoguoliang.websocket.scan.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 二维码
 *
 * @author taogl
 * @date 2022/6/26 16:30
 */
@Data
@Accessors(chain = true)
public class QrCodeVO {

    private String image;

    private String uuid;
}
