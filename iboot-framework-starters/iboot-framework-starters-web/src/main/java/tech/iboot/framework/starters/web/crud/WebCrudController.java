package tech.iboot.framework.starters.web.crud;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import tech.iboot.framework.core.bean.Result;
import tech.iboot.framework.core.crud.CrudPrimaryKey;
import tech.iboot.framework.core.crud.CrudService;
import tech.iboot.framework.core.messageSource.AssembleMessageSource;
import tech.iboot.framework.core.utils.ClassUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <strong>CRUD controller层接口</strong>
 * <p></p>
 * Created on 2022/10/14
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
@SuppressWarnings("unchecked")
public interface WebCrudController<T, K> {
    /**
     * crud service层
     * @return CrudService<T, K> service层
     */
    CrudService<T, K> getService();

    /**
     * assembleMessageSource 信息源
     * @return assembleMessageSource 信息源
     */
    default AssembleMessageSource ms() {
        return null;
    }

    /**
     * 查询列表
     * @return Result<List<T>>
     */
    @GetMapping("/crud")
    @Operation(description = "查询列表", summary = "查询列表")
    default Result<List<T>> findList() {
        return Result.success(getService().findList(),
                getMessage("msg.api.success"));
    }

    /**
     * 查询个数
     * @return 个数
     */
    @GetMapping("/crud/count")
    @Operation(description = "查询个数", summary = "查询个数")
    default Result<Long> count() {
        return Result.success(getService().findCount(),
                getMessage("msg.api.success"));
    }

    /**
     * 根据主键id查询
     * @param id 主键id,多个用逗号隔开
     * @return Result<T>
     */
    @GetMapping("/crud/{id}")
    @Operation(description = "根据主键查询", summary = "根据主键查询")
    default Result<List<T>> findById(@PathVariable String id) {
        return Result.success(getService().findByIds(Arrays.stream(id.split(",")).map(i -> (K) i).collect(Collectors.toList())),
                getMessage("msg.api.success"));
    }

    /**
     * 保存数据
     * @param t 实体
     * @return Result<T>
     */
    @PostMapping("/crud")
    @Operation(description = "保存数据", summary = "保存数据")
    default Result<T> insert(@RequestBody T t) {
        K id = ClassUtil.getVariableByFirstField(t, CrudPrimaryKey.class);
        if (id != null) {
            if (getService().findById(id) !=null) {
                return Result.fail(null, getMessage("msg.exist", id.toString()));
            }
        }
        return Result.success(getService().insert(t), getMessage("msg.api.success"));
    }

    /**
     * 更新数据
     * @param t 实体
     * @return Result<T>
     */
    @PutMapping("/crud")
    @Operation(description = "更新数据", summary = "更新数据")
    default Result<T> update(@RequestBody T t) {
        K id = ClassUtil.getVariableByFirstField(t, CrudPrimaryKey.class);
        if (id != null) {
            if (getService().findById(id) ==null) {
                return Result.fail(null, getMessage("msg.not_exist", id.toString()));
            }
        }
        return Result.success(getService().update(t), getMessage("msg.api.success"));
    }

    /**
     * 批量删除数据
     * @param id 主键id,多个用逗号隔开
     * @return Result<String>
     */
    @DeleteMapping("/crud/{id}")
    @Operation(description = "批量删除数据，主键,多个用逗号隔开", summary = "批量删除数据")
    default Result<String> delete(@PathVariable String id) {
        getService().deleteBatchByIds(Arrays.stream(id.split(",")).map(i -> (K) i).collect(Collectors.toList()));
        return Result.success("", getMessage("msg.api.success"));
    }

    /**
     * 获取信息
     * @param code 代码
     * @param args 参数
     * @return 信息
     */
    default String getMessage(String code, String... args) {
        return ms()!=null ? ms().msg(code, args) : "";
    }
}
