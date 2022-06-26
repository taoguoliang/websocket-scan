package cn.taoguoliang.websocket.scan.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * page
 *
 * @author taogl
 * @date 2022/6/26 16:19
 */
@Controller
public class PageController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/index1")
    public String index1(){
        return "index1";
    }

    @GetMapping("/success")
    public String success(){
        return "success";
    }
}
