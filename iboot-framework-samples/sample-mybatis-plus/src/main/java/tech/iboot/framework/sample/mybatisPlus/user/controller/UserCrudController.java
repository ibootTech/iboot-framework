package tech.iboot.framework.sample.mybatisPlus.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.iboot.framework.core.bean.Result;
import tech.iboot.framework.sample.mybatisPlus.user.entity.User;
import tech.iboot.framework.sample.mybatisPlus.user.service.UserCrudService;
import tech.iboot.framework.starters.web.crud.WebCrudController;
import tech.iboot.framework.starters.web.crud.WebCrudControllerImpl;

import java.util.List;

/**
 * <strong></strong>
 * <p></p>
 * Created on 2022/12/12
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
@RestController
@RequestMapping("/api/user")
@Tag(name = "user", description = "user增删改查接口")
public class UserCrudController extends
        WebCrudControllerImpl<User, String, UserCrudService> implements WebCrudController<User, String> {

    @Resource
    private UserCrudService userCrudService;

    @GetMapping("/list")
    @Operation(description = "查询列表", summary = "查询列表")
    public Result<List<User>> findListBySql() {
        return Result.success(userCrudService.findListBySql(),
                getMessage("msg.api.success"));
    }

}
