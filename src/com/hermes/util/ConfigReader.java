/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.util;

import com.hermes.client.HCUser;
import com.hermes.common.HUser;
import com.hermes.common.constants.HBrowsable;
import com.hermes.common.constants.HGender;
import com.hermes.common.constants.HLineType;
import com.hermes.common.constants.HLocation;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import javax.swing.ImageIcon;

/**
 *
 * @author jomartinez
 */
public class ConfigReader
{

    private Properties config;
    private String path;
    private static ConfigReader instance;

    public ConfigReader(String path) throws NoSuchAlgorithmException, IOException
    {
        this.path=path;
        File configFile = new File(path);
        config = new Properties();
        
        try
        {
            config.load(new FileReader(configFile));
        }
        catch (IOException ex)
        {
            String anonTail = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
            config.put("username", "anon_" + anonTail);
            config.put("guid", UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16));
            config.put("country", HLocation.UnKnown.getValue()+"");
            config.put("region", "");
            config.put("age", 0+"");
            config.put("gender", HGender.Unknow.getValue()+"");
            config.put("personalMessage", "");
            config.put("avatar", "./avatar.png");
            config.put("browsable", HBrowsable.Not_Browsable.getValue()+"");
            config.put("lineType", HLineType.HLNone.getValue()+"");
            config.put("sharedCount", "0");
            config.put("dataPort", "4000");

            configFile.getParentFile().mkdirs(); 
         
         
           
           
           try
           {
                config.store(new FileWriter(configFile), "");
           }
           catch(IOException IoEx)
           {
               System.err.println(IoEx);
           }
          
           
        }
    }

    public static ConfigReader getInstance() throws IOException, NoSuchAlgorithmException
    {
        if (instance == null)
        {
            String fileSep = File.separator;
            instance = new ConfigReader(System.getProperty("user.home") + fileSep + ".hermes" + fileSep + "client" + fileSep + "hermes.conf");
        }
        return instance;
    }

    public HCUser getUser() throws UnknownHostException
    {
        HLocation country = HLocation.get(Byte.parseByte(config.getProperty("country")));
        HGender gender = HGender.get(Byte.parseByte(config.getProperty("gender")));
        HBrowsable browsable = HBrowsable.get(Byte.parseByte(config.getProperty("browsable")));
        HLineType linetype = HLineType.getValue(Integer.parseInt(config.getProperty("lineType")));

        Random rnd = new Random();

        byte[] bPublicIp = new byte[4];
        byte[] bPrivateIp = new byte[4];
        byte[] bNodeIp = new byte[4];
        byte[] bUploaded = new byte[3];

        rnd.nextBytes(bPublicIp);
        rnd.nextBytes(bPrivateIp);
        rnd.nextBytes(bNodeIp);
        rnd.nextBytes(bUploaded);
        
        HCUser ret=new HCUser(config.getProperty("username"), Short.parseShort(config.getProperty("sharedCount")), linetype, browsable, Byte.parseByte(config.getProperty("age")), gender, country, config.getProperty("region"), InetAddress.getByAddress(bPublicIp), Short.parseShort(config.getProperty("dataPort")), InetAddress.getByAddress(bPrivateIp), InetAddress.getByAddress(bNodeIp), (short) rnd.nextInt(), bUploaded[0], bUploaded[1], bUploaded[2]);
        
        ret.setPersonalMessage(config.getProperty("personalMessage"));
        ret.setAvatar(new ImageIcon(config.getProperty("avatar")));
        return ret;
    }

    public void save(HCUser user) throws IOException
    {
        config.put("username", user.getUsername());
        config.put("guid", user.getGuid());
        config.put("country", user.getCountry().getValue()+"");
        config.put("region", user.getRegion());
        config.put("age", user.getAge()+"");
        config.put("gender", user.getGender().getValue()+"");
        
        if(user.getPersonalMessage()!=null)
        {
            config.put("personalMessage", user.getPersonalMessage());
        }
        else
        {
            config.put("personalMessage", "");
        }
        if(user.getAvatar()!=null)
        {
            config.put("avatar",user.getAvatar().getDescription());
        }
        else
        {
            config.put("avatar","");
        }        
        config.put("browsable", user.getBrowsable().getValue()+"");
        config.put("lineType", user.getLinetype().getValue()+"");
        config.put("sharedCount", user.getFilecount()+"");
        config.put("dataPort", user.getDataport()+"");

        String fileSep = File.separator;
        config.store(new FileWriter(path), "");
    }
}
