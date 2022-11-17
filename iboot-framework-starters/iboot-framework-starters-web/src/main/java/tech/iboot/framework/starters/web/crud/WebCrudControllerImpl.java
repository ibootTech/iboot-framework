package tech.iboot.framework.starters.web.crud;

import org.springframework.beans.factory.annotation.Autowired;
import tech.iboot.framework.core.crud.CrudService;
import tech.iboot.framework.core.messageSource.AssembleMessageSource;

/**
 * <strong>CRUD controller层实现类</strong>
 * <p></p>
 * Created on 2022/10/14
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class WebCrudControllerImpl<T, K, S extends CrudService<T, K>> implements WebCrudController<T, K> {
    @Autowired
    S service;

    @Autowired
    AssembleMessageSource assembleMessageSource;

    @Override
    public CrudService<T, K> getService() {
        return service;
    }

    @Override
    public AssembleMessageSource ms() {
        return assembleMessageSource;
    }
}
