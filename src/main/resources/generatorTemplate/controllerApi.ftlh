package ${targetPackageController};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ${serviceName}Service;

@Controller
@RequestMapping("/api/${classSimpleNameUncapitalize}")
public class ${classSimpleName}ApiController {

    @Autowired
    private ${classSimpleName}Service ${classSimpleNameUncapitalize}Service;
    
}
