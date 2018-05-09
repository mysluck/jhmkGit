//package com.jhmk.earlywaring.controller;
//
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//
///**
// * 使用swagger添加文档内容示例
// * 打开: http://localhost:{port}/swagger-ui.html  port为yml文件中配置的端口号
// * @author  shaofeikang on 2017/9/6.
// */
//@RestController
//@RequestMapping(value="/users")
//public class SwaggerDemoController {
//    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());
//
//    public SwaggerDemoController(){
//        List<User> userList = new ArrayList<>();
//
//        User user1 = new User();
//        user1.setId(1L);
//        user1.setAge(12);
//        user1.setName("zhangsan");
//
//        User user2 = new User();
//        user2.setId(2L);
//        user2.setAge(13);
//        user2.setName("李四");
//
//        User user3 = new User();
//        user3.setId(3L);
//        user3.setAge(14);
//        user3.setName("王五");
//
//        userList.add(user1);
//        userList.add(user2);
//        userList.add(user3);
//
//        for(User user : userList){
//            users.put(user.getId(),user);
//        }
//
//    }
//
//    @ApiOperation(value="【swagger示例】获取用户列表", notes="")
//    @RequestMapping(value={""}, method= RequestMethod.GET)
//    public List<User> getUserList() {
//
//        List<User> r = new ArrayList<>(users.values());
//        return r;
//    }
//
//    @ApiOperation(value="【swagger示例】创建用户", notes="根据User对象创建用户")
//    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
//    @RequestMapping(value="", method= RequestMethod.POST)
//    public String postUser(@RequestBody User user) {
//        users.put(user.getId(), user);
//        return "success";
//    }
//
//    @ApiOperation(value="【swagger示例】获取用户详细信息", notes="根据url的id来获取用户详细信息")
//    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType="path")
//    @RequestMapping(value="/{id}", method= RequestMethod.GET)
//    public User getUser(@PathVariable Long id) {
//        return users.get(id);
//    }
//
//    @ApiOperation(value="【swagger示例】更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType="path"),
//            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
//    })
//    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
//    public String putUser(@PathVariable Long id, @RequestBody User user) {
//        User u = users.get(id);
//        u.setName(user.getName());
//        u.setAge(user.getAge());
//        users.put(id, u);
//        return "success!";
//    }
//
//    @ApiOperation(value="【swagger示例】删除用户", notes="根据url的id来指定删除对象")
//    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType="path")
//    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
//    public String deleteUser(@PathVariable Long id) {
//        users.remove(id);
//        return "success";
//    }
//}
//
//class User{
//    private Long id;
//    private String name;
//    private int age;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//}
