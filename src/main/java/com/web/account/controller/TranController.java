package com.web.account.controller;

import com.web.account.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;

/**
 * @author aptx
 * @date 2022/07/01 11:32
 */
@Controller
public class TranController {

    @Autowired
    TranService tranService;

}
