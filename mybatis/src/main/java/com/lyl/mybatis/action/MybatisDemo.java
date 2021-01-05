package com.lyl.mybatis.action;

import com.lyl.mybatis.mapper.UserMapper;
import com.lyl.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * @ClassName MybatisDemo
 * @Description TODO
 * @Author liyulong
 * @Date 2020/12/18 15:18
 * @Version 1.0
 **/
public class MybatisDemo {

    public static SqlSession sqlSession = null;

    static {
        try {
            //1.加载核心配置文件
            InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            //2.读取配置文件的内容
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
            //3.使用sqlSessionFactory对象，创建sqlSession对象，开启事务自动提交
            sqlSession = sqlSessionFactory.openSession(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *@Author liyulong
     *@Description //TODO
     *@Date 15:43 2020/12/18
     *@param []
     *@return void
     **/
    @Test
    public void addUser(){
        User user = new User();
        user.setId(8);
        user.setUsername("李玉龙");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("来自大唐朝");
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.addUser(user);
    }

   /**
    *@Author liyulong
    *@Description
    *@Date 11:22 2020/12/22
    *@param []
    *@return void
    **/
   @Test
    public void queryUserById(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.queryUserById(4);
       System.out.println(user);
    }


}

