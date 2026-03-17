package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class User 
{
    private String user, pswd; // 用户名和密码
    private List<String> checkPoints = new ArrayList<String>(); // 关卡列表
    private List<String> carriers = new ArrayList<String>(); // 承运人列表
    private int gears; // 档位
    
    public User(String[] infs)
    {
        user = infs[0]; // 初始化用户名
        pswd = infs[1]; // 初始化密码
        
        for(int i = 2; i <= 10; i++)
        {
            carriers.add(infs[i]); // 初始化承运人列表
        }
        
        for(int i = 11; i <= 19; i++)
        {
            checkPoints.add(infs[i]); // 初始化关卡列表
        }
        
        gears = Integer.parseInt(infs[20]); // 初始化档位
    }

    public String getUser()
    {
        return this.user; // 返回用户名
    }

    public String getPswd()
    {
        return this.pswd; // 返回密码
    }
    
    public List<String> getCheckPointsList()
    {
        return this.checkPoints; // 返回关卡列表
    }

    public List<String> getCarrierList()
    {
        return this.carriers; // 返回承运人列表
    }

    public int getGears()
    {
        return this.gears; // 返回档位
    }

    public void setGears(int gears)
    {
        this.gears = gears; // 设置档位
    }

    public void Save()
    {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/data/gameData.txt"), "UTF-8"))) 
        {
            List<String> infs = new ArrayList<String>(); // 创建一个存储更新后信息的列表
            
            String temp = br.readLine(); // 读取文件中的一行数据
            while (temp != null)  
            {
                String[] infos = temp.split("_"); // 将读取到的一行数据按照"_"进行分割
                
                if(infos[0].equals(user)) // 如果用户名匹配
                {
                    String t = user + "_" + pswd + "_"; // 构建新的一行数据
                    
                    for(String a : this.carriers)
                    {
                        t += a + "_"; // 添加承运人信息
                    }
                    
                    for(String a : this.checkPoints)
                    {
                        t += a + "_"; // 添加关卡信息
                    }
                    
                    t += gears; // 添加档位信息
                    
                    infs.add(t); // 将新的一行数据添加到列表中
                    temp = br.readLine(); // 读取下一行数据
                    continue;
                }
                
                infs.add(temp); // 如果用户名不匹配，则将原有的一行数据添加到列表中
                temp = br.readLine(); // 读取下一行数据
            }
            
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("resources/data/gameData.txt"), "UTF-8")); // 创建一个写入文件的BufferedWriter对象
            for (String inf : infs) 
            {
                bw.write(inf + "\n"); // 将列表中的每一行数据写入文件，每行后面加上换行符
            }
            
            bw.flush(); // 刷新缓冲区，将数据写入文件
        } 
        catch (Exception e) 
        {
            System.out.println(e); // 如果发生异常，打印异常信息
        }
    }
}
