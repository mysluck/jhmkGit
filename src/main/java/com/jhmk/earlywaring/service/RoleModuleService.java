package com.jhmk.earlywaring.service;


import com.jhmk.earlywaring.entity.SmModule;
import com.jhmk.earlywaring.entity.SmRoleModule;
import com.jhmk.earlywaring.entity.repository.service.SmModuleRepService;
import com.jhmk.earlywaring.entity.repository.service.SmRoleModuleRepService;
import com.jhmk.earlywaring.util.CompareUtil;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleModuleService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    SmModuleRepService smModuleRepService;
    @Autowired
    SmRoleModuleRepService smRoleModuleRepService;

    /**
     * 查询父节点操作
     *
     * @param roleId
     * @return
     */
//    public List<Map<String, Object>> getRootModule(String roleId) {
//        StringBuffer sqlstr = new StringBuffer();
//        sqlstr.append("SELECT * from sm_role_module m,sm_module s");
//        sqlstr.append(" where  m.module_id=s.mod_code and s.mod_category_id ='root'");
//        sqlstr.append(" and m.role_id= ?");
//        List<String> params = new ArrayList<>();
//        params.add(roleId);
//        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlstr.toString(), params.toArray());
//        return list;
//    }

    /**
     * 查询所有节点操作
     *
     * @param roleId
     * @return
     */
//    public List<Map<String, Object>> getAllModule(String roleId) {
//        StringBuffer sqlstr = new StringBuffer();
//        sqlstr.append("SELECT s.* from sm_role_module m,sm_module s");
//        sqlstr.append(" where  m.module_id=s.mod_code ");
//        sqlstr.append(" and m.role_id= ?");
//        List<String> params = new ArrayList<>();
//        params.add(roleId);
//        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlstr.toString(), params.toArray());
//        Map<String, Map<String, String>> newMap = new HashMap<>();
//        return list;
//    }
    public List<SmModule> getAllModule() {
        Iterable<SmModule> all = smModuleRepService.findAll();
        List<SmModule> list = new ArrayList<>();
        all.forEach(single -> {
            list.add(single);
        });
        List<SmModule> modules = gradeModule(list);
        return modules;
    }


    /**
     * 查找对应角色下的菜单
     *
     * @param roleId
     */
    public List<SmModule> queryComponetByRole(String roleId) {
        List<SmRoleModule> allByRoleId = smRoleModuleRepService.findAllByRoleId(roleId);
        List<String> moduleNameList = new ArrayList<>();
        for (SmRoleModule s : allByRoleId) {
            moduleNameList.add(s.getModuleId());
        }
        List<SmModule> modules = smModuleRepService.findByModCodeIn(moduleNameList);
        List<SmModule> modules1 = gradeModule(modules);
        return modules1;
    }

    /**
     * 菜单分级  1级菜单下分 2级菜单
     *
     * @return
     */
    private List<SmModule> gradeModule(List<SmModule> modules) {
        List<SmModule> moduleList = new ArrayList<>();

        for (int i = 0; i < modules.size(); i++) {
            SmModule s = modules.get(i);
            if ("root".equals(s.getModCategoryId())) {
                if (moduleList.contains(s)) {
                    continue;
                }
                moduleList.add(s);
            } else {
                String modCategoryId = s.getModCategoryId();
                SmModule module = smModuleRepService.findOne(modCategoryId);
                //如果是二级菜单 moduleList一级菜单
                if (moduleList.contains(module)) {
                    int num = moduleList.indexOf(module);
                    SmModule module1 = moduleList.get(num);
                    List<SmModule> childMdules = module1.getChildMdules();
                    if (childMdules != null) {
                        childMdules.add(s);

                    } else {
                        childMdules = new ArrayList<>();
                        childMdules.add(s);
                    }
                    module1.setChildMdules(childMdules);
                } else {
                    List<SmModule> childMdules = new ArrayList<>();
                    childMdules.add(s);
                    module.setChildMdules(childMdules);
                    moduleList.add(module);
                }

            }
        }
        return moduleList;

    }


    /**
     * 根据序号排序，用于功能排序
     *
     * @param moduleList
     * @return
     */
    public List<SmModule> sortMap(List<SmModule> moduleList) {
        Collections.sort(moduleList, CompareUtil.createComparator(1, "modOrder"));
        for (SmModule smModule : moduleList) {

            Collections.sort(smModule.getChildMdules(), CompareUtil.createComparator(1, "modOrder"));
        }
        return moduleList;
    }

}
