package tech.iboot.framework.sample.web.user;

import tech.iboot.framework.sample.web.user.model.User;
import tech.iboot.framework.sample.web.user.service.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.iboot.framework.starters.web.crud.WebCrudController;
import tech.iboot.framework.starters.web.crud.WebCrudControllerImpl;

/**
 * <strong></strong>
 * <p></p>
 * Created on 2022/11/17
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
@RestController
@RequestMapping("/api/user")
public class UserCrudController extends WebCrudControllerImpl<User, String, UserServiceImpl> implements WebCrudController<User, String> {
}
