package cn.michael.user.dao;

import cn.michael.user.domain.User;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.management.relation.RoleUnresolved;
import java.io.*;

public class UserDao {
    private String path="D:/users.xml";

    public User findByUsername(String username) {
        SAXReader reader = new SAXReader();
        try {
            Document dom = reader.read(path);
            Element ele = (Element) dom.selectSingleNode("//user[@username='"+username+"']");
            if(ele == null){
                return null;
            }
            User user = new User();
            String name = ele.attributeValue("username");
            String passwd = ele.attributeValue("password");
            user.setUsername(name);
            user.setPassword(passwd);
            return user;
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(User user){
        SAXReader reader = new SAXReader();
        try {
            Document dom = reader.read(path);
            Element root = dom.getRootElement();
            Element userEle = root.addElement("user");
            userEle.addAttribute("username", user.getUsername());
            userEle.addAttribute("password", user.getPassword());
            OutputFormat format = new OutputFormat("\t", true);
            format.setTrimText(true);
            XMLWriter xmlWrite = new XMLWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(path), "UTF-8"), format);
            xmlWrite.write(dom);

            xmlWrite.close();
        } catch (DocumentException | IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
}
