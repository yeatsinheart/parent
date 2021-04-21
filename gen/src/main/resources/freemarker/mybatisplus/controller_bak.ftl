package ${controller.packageName};
import com.global.account.service.IUserService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import com.global.common.GlobalConstants;
import com.global.model.account.User;
import com.global.model.superuser.SuperUser;
import com.global.web.util.SafetyController;
import com.global.web.util.WebResultBase;
import com.global.account.service.IUserService;
import ${entity.fullName};
import ${dto.fullName};
import ${service.fullName};
/**
* ${table.comment!}
* @author ${author}
* @since ${time}
*/

@RestController
@RequestMapping("/dailyact")
public class ${controller.className}  extends SafetyController  {
    private static final Log log = LogFactory.getLog(${controller.className}.class);
    @Autowired
    private ${service.className} ${service.className?uncap_first};
    @Autowired
    private IUserService userService;
    /**
    * 获取数据列表
    */
    @PostMapping("bind.json")
    public WebResultBase list${entity.className}( @Valid @ModelAttribute ${dto.className} ${dto.className?uncap_first}){
        WebResultBase result = new WebResultBase();
        List<${entity.className}> ${entity.className?uncap_first}List = ${service.className?uncap_first}.list${entity.className}( ${dto.className?uncap_first});
        return result;
    }


    /**
    * 获取全部数据
    */
    @GetMapping("/all${entity.className}")
    public WebResultBase all${entity.className}(){
        WebResultBase result = new WebResultBase();
        List<${entity.className}> ${entity.className?uncap_first}List = ${service.className?uncap_first}.all${entity.className}();
        return result;
    }


    /**
    * 根据ID查找数据
    */
    @GetMapping("/get${entity.className}")
    public WebResultBase get${entity.className}( ${dto.className} ${dto.className?uncap_first}){
        WebResultBase result = new WebResultBase();
        ${entity.className} ${entity.className?uncap_first}List = ${service.className?uncap_first}.get${entity.className}( ${dto.className?uncap_first});
        return result;
    }


    /**
    * 根据条件查找数据
    */
    @PostMapping("/find${entity.className}")
    public WebResultBase find${entity.className}(${dto.className} ${dto.className?uncap_first}){
        WebResultBase result = new WebResultBase();
        List<${entity.className}> ${entity.className?uncap_first}List = ${service.className?uncap_first}.find${entity.className}(${dto.className?uncap_first});
        return result;
    }


    /**
    * 添加数据
    */
    @PostMapping("/add${entity.className}")
    public WebResultBase add${entity.className}(@Valid @ModelAttribute  ${dto.className} ${dto.className?uncap_first}){
        WebResultBase result = new WebResultBase();
        int addNum  = ${service.className?uncap_first}.add${entity.className}( ${dto.className?uncap_first});
        return result;
    }


    /**
    * 更新数据
    */
    @PostMapping("/update${entity.className}")
    public WebResultBase update${entity.className}(@Valid @ModelAttribute  ${dto.className} ${dto.className?uncap_first}){
        WebResultBase result = new WebResultBase();
        int updateNum = ${service.className?uncap_first}.update${entity.className}(  ${dto.className?uncap_first});
        return result;
    }
}


